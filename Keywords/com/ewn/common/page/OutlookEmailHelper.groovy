@Grapes([
	@Grab(group='javax.mail', module='mail', version='1.4.7')
])
package com.ewn.common.page

import javax.mail.BodyPart
import javax.mail.Flags
import javax.mail.Folder
import javax.mail.FolderClosedException
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Store
import javax.mail.internet.MimeMultipart
import javax.mail.search.FlagTerm

public class OutlookEmailHelper {

	public OutlookEmailHelper(String username, String password) {
		this.username = username
		this.password = password
	}

	private String username = ""
	private String password = ""
	private final String host = "outlook.office365.com"
	private Folder emailFolder
	private Store store

	private Message[] checkEmails(boolean unseenOnly = false) {
		try {
			//create properties field
			Properties properties = new Properties()

			properties.put("mail.imap.host", this.host)
			properties.put("mail.imap.port", "993")
			properties.put("mail.imap.starttls.enable", "true")
			properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory")
			properties.setProperty("mail.imap.socketFactory.fallback", "false")
			properties.setProperty("mail.imap.socketFactory.port",String.valueOf(993))
			Session emailSession = Session.getDefaultInstance(properties)

			//create the imap store object and connect with the pop server
			this.store = emailSession.getStore("imap")

			store.connect(this.host, this.username, this.password)

			//create the folder object and open it
			this.emailFolder = store.getFolder("INBOX")
			emailFolder.open(Folder.READ_WRITE)

			// retrieve the messages from the folder in an array
			Message[] messages = []

			// workaround a bug in javax.mail
			// https://stackoverflow.com/questions/13082349/java-lang-arrayindexoutofboundsexceptionat-com-sun-mail-imap-messagecache-getme
			boolean getMessagesWorked = false
			while (!getMessagesWorked) {
				try {
					messages = unseenOnly ? emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)) : emailFolder.getMessages()
					getMessagesWorked = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					try {
						emailFolder.getMessage(emailFolder.getMessageCount());
					} catch (ArrayIndexOutOfBoundsException ex) {
						/* There really isn't much you can do here, except try again.
						 * the good news is that this should hardly ever happen!!
						 * Good in this case is a relative term! */
					}
				}
			}

			return messages

		} catch (FolderClosedException e) {
			e.printStackTrace()
		}
		catch (Exception e) {
			e.printStackTrace()
			throw new Exception(e.message)
		}
	}

	private closeEmailFolder() {
		if (this.emailFolder && this.store) {
			//close the store and folder objects
			this.emailFolder.close(false)
			this.store.close()
		}
	}

	/**
	 * Get last email body by email subject
	 * @param emailSubject email subject to determine the email
	 * @param sentTime due to testing purpose, most of the emails have the same subject, so we use sent time as a factor to determine the email
	 * @param isRawHTML return raw HTML or plain text
	 * @return body of the email
	 */
	public String getRecentEmailBySubject(String emailSubject, Date sentTime, boolean isRawHTML = true) {
		Message[] messages = checkEmails(false)
		Message[] sortedMessages = messages.findAll{it.getSubject() == emailSubject && it.getSentDate().after(sentTime)}.sort {m1, m2 -> m2.getSentDate().compareTo(m1.getSentDate())}
		String bodyMessage = sortedMessages.length > 0 ? this.getTextFromMessage(sortedMessages[0], isRawHTML) : ""
		closeEmailFolder()
		return bodyMessage
	}

	/**
	 * Get last unseen email body by email subject
	 * @param emailSubject email subject to determine the email
	 * @param sentTime due to testing purpose, most of the emails have the same subject, so we use sent time as a factor to determine the email
	 * @param isRawHTML return raw HTML or plain text
	 * @return body of the email
	 */
	public String getRecentUnseenEmailBySubject(String emailSubject, boolean isRawHTML = true , Date sentTime) {
		Message[] messages = checkEmails(true)
		Message[] sortedMessages = messages.findAll{it.getSubject() == emailSubject && it.getSentDate().after(sentTime)}.sort {m1, m2 -> m2.getSentDate().compareTo(m1.getSentDate())}
		String bodyMessage = sortedMessages.length > 0 ? this.getTextFromMessage(sortedMessages[0], isRawHTML) : ""
		closeEmailFolder()
		return bodyMessage
	}
	
	/**
	 * Get last unseen email subject 
	 * @param emailSubject email subject to determine the email
	 * @param sentTime due to testing purpose, most of the emails have the same subject, so we use sent time as a factor to determine the email
	 * @param isRawHTML return raw HTML or plain text
	 * @return body of the email
	 */
	public String getEmailSubject(Date sentTime) {
		if (sentTime == null) sentTime = new Helper().getTodayDate()
		Message[] messages = checkEmails(true)
		Message[] sortedMessages = messages.findAll{it.getSentDate().after(sentTime)}.sort {m1, m2 -> m2.getSentDate().compareTo(m1.getSentDate())}
		String messageSubject = sortedMessages.length > 0 ? sortedMessages[0].getSubject() : ""
		closeEmailFolder()
		return messageSubject
	}

	public void deleteEmailBySubject(String emailSubject) {
		Message[] messages = checkEmails()
		List<Message> messagesToDelete = messages.findAll{it.getSubject() == emailSubject }
		messagesToDelete.each {
			it.setFlag(Flags.Flag.DELETED, true)
		}
		closeEmailFolder()
	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart, boolean isRawHTML)  throws MessagingException, IOException {
		String result = ""
		int count = mimeMultipart.getCount()
		if (count == 0) throw new MessagingException("Multipart with no body parts not supported.")
			
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i)
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent()
				break // without break same text appears twice
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent()
				result = result + "\n" + isRawHTML ? html : org.jsoup.Jsoup.parse(html).text()
			} else if (bodyPart.getContent() instanceof MimeMultipart){
				result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent(), isRawHTML)
			} else {
			throw new MessagingException("unsupported email type ${message.getContentType()}")
		} 
		}
		return result
	}

	private String getTextFromMessage(Message message, boolean isRawHTML = false) throws MessagingException, IOException {
		String result = ""
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString()
		} else if (message.isMimeType("text/html")) {
	        String html = (String) message.getContent();
	        result = isRawHTML ? html : org.jsoup.Jsoup.parse(html).text()
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent()
			result = getTextFromMimeMultipart(mimeMultipart, isRawHTML)
		} else {
			throw new MessagingException("unsupported email type ${message.getContentType()}")
		}
		return result
	}
}
