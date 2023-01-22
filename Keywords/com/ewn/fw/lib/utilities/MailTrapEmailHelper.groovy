/*+----------------------------------------------------------------------
 ||
 ||  Class MailTrapEmailHelper 
 ||
 ||         Author:  kms-sonnguyen
 ||
 ||        Purpose:  when working on test cases using email, we need a fast and easy way to retrieve emails. 
 ||                  For automation testing, we chose MailTrap rather than popular mail services like Gmail 
 ||                  with various security/privacy settings enhanced that cause trouble. 
 ||                  MailTrap makes it very easy to create an email, and also nice API to fetch email directly instead of using SMTP/pop3 
 ||
 ||     How to use: MailTrapEmailHelper email = new MailTrapEmailHelper()
 ||					email.APIToken = "YourTokenHere"
 ||
 ||
 ++-----------------------------------------------------------------------*/
package com.ewn.fw.lib.utilities

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper


public class MailTrapEmailHelper {
	private final MailtrapEndpoint = "https://mailtrap.io/api/v1"
	private String APIToken = null
	private String InboxId = null

	void setAPIToken(String token) {
		this.APIToken = token
	}

	String getAPIToken() {
		if (this.APIToken == null) {
			throw new Exception("APIToken is null")
		}
		return this.APIToken
	}

	private ArrayList convertResponseContentToArrayList(String bodyContent) {
		JsonSlurper slurper = new JsonSlurper()
		ArrayList jsonObject = slurper.parseText(bodyContent)
		return jsonObject
	}

	private Map convertResponseContentToMap(String bodyContent) {
		JsonSlurper slurper = new JsonSlurper()
		Map jsonObject = slurper.parseText(bodyContent)
		return jsonObject
	}

	enum RequestMethod {
		GET,
		POST,
		DELETE,
		PUT,
		PATCH
	}

	private String sendRequest(RequestMethod method, String endpoint) {
		String _endpoint = "${this.MailtrapEndpoint}${endpoint}"

		TestObjectProperty authHeader = new TestObjectProperty("Api-Token", ConditionType.EQUALS, this.getAPIToken())
		ArrayList defaultHeaders = Arrays.asList(authHeader)
		RequestObject ro = new RequestObject("objectId")
		ro.setRestUrl(_endpoint)
		ro.setHttpHeaderProperties(defaultHeaders)
		ro.setRestRequestMethod(method as String)

		ResponseObject respObj = WS.sendRequest(ro)
		return respObj.responseBodyContent
	}

	private String getInboxId() {
		if(this.InboxId == null) {
			String endpoint = "/inboxes"
			String responseObject = this.sendRequest(RequestMethod.GET, endpoint)
			ArrayList jsonResponse = this.convertResponseContentToArrayList(responseObject)

			// assuming an email account for testing has 1 inbox only
			this.InboxId = jsonResponse[0].id
			return jsonResponse[0].id
		}
		return this.InboxId
	}

	ArrayList getAllMessages() {
		String endpoint = "/inboxes/${this.getInboxId()}/messages"
		String responseObject = this.sendRequest(RequestMethod.GET, endpoint)
		return this.convertResponseContentToArrayList(responseObject)
	}

	Map getMessageByID(String ID) {
		String endpoint = "/inboxes/${this.getInboxId()}/messages/${ID}"
		String responseObject = this.sendRequest(RequestMethod.GET, endpoint)
		return this.convertResponseContentToMap(responseObject)
	}

	String getRawTextMessageBody(String ID) {
		String endpoint = "/inboxes/${this.getInboxId()}/messages/${ID}/body.txt"
		return this.sendRequest(RequestMethod.GET, endpoint)
	}

	String getRawMessageBody(String ID) {
		String endpoint = "/inboxes/${this.getInboxId()}/messages/${ID}/body.raw"
		return this.sendRequest(RequestMethod.GET, endpoint)
	}

	ArrayList getMessageIDsBySubject(String subject) {
		String endpoint = "/inboxes/${this.getInboxId()}/messages"
		String responseObject = this.sendRequest(RequestMethod.GET, endpoint)
		ArrayList allMessagesObject = this.convertResponseContentToArrayList(responseObject)
		ArrayList messagesContainSubject = allMessagesObject.findAll({it.subject.contains(subject)})
		ArrayList messageIDs = []
		messagesContainSubject.each { messageIDs.push(it.id) }
		return messageIDs
	}

	ArrayList getMessagesRawTextBySubject(String subject) {
		ArrayList messageIDs = this.getMessageIDsBySubject(subject)
		ArrayList messagesRawText = []
		messageIDs.each {
			String endpoint = "/inboxes/${this.getInboxId()}/messages/${it}/body.txt"
			messagesRawText.push(this.sendRequest(RequestMethod.GET, endpoint))
		}
		return messagesRawText
	}

	ArrayList getRawMessageBodyBySubject(String subject) {
		ArrayList messageIDs = this.getMessageIDsBySubject(subject)
		ArrayList messagesRawText = []
		messageIDs.each {
			String endpoint = "/inboxes/${this.getInboxId()}/messages/${it}/body.raw"
			messagesRawText.push(this.sendRequest(RequestMethod.GET, endpoint))
		}
		return messagesRawText
	}

	ArrayList getUnreadMessagesID() {
		ArrayList allMessages = this.getAllMessages()
		List unreadMessages = allMessages.findAll({!it.is_read})
		ArrayList unreadMessageIDs = []
		unreadMessages.each { unreadMessageIDs.push(it.id) }
		return unreadMessageIDs
	}

	void deleteMessageByID(String ID) {
		String endpoint = "/inboxes/${this.getInboxId()}/messages/${ID}"
		this.sendRequest(RequestMethod.DELETE, endpoint)
	}
	
	void deleteMessagesBySubject(String subject) {
		ArrayList messageIDs = this.getMessageIDsBySubject(subject)
		messageIDs.each { 
			String endpoint = "/inboxes/${this.getInboxId()}/messages/${it}"
			this.sendRequest(RequestMethod.DELETE, endpoint)
		}
		
	}
}
