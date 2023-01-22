package com.ewn

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

import internal.GlobalVariable

public class NotificationCenterPage extends BasePage<NotificationCenterPage> {

	//////////////////// Button ////////////////////////////
	NotificationCenterPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	NotificationCenterPage clickCreate(int index=1) {
		BaseActions.click(webButton("Create", index))
		return this
	}

	NotificationCenterPage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	NotificationCenterPage clickSaveAndContinueEditing(int index=1) {
		BaseActions.click(webButton("Save and continue editing", index))
		return this
	}

	NotificationCenterPage clickSaveAndClose(int index=1) {
		BaseActions.click(webButton("Save and close", index))
		return this
	}

	NotificationCenterPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	NotificationCenterPage clickEdit(int index=1) {
		BaseActions.click(webButton("Edit", index))
		return this
	}

	NotificationCenterPage clickPublish() {
		BaseActions.click(webButton("Publish"))
		return this
	}

	NotificationCenterPage clickYes() {
		BaseActions.click(webButton("Yes"))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	NotificationCenterPage selectOptionViewNotifications(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("View Notifications:", index), item, true)
		return this
	}

	NotificationCenterPage selectOptionNotificationStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Notification Status:", index), item, true)
		return this
	}

	NotificationCenterPage selectOptionPublishedBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Published By:", index), item, true)
		return this
	}

	NotificationCenterPage selectOptionType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Type:", index), item, true)
		return this
	}

	NotificationCenterPage selectOptionPriority(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Priority:", index), item, true)
		return this
	}

	NotificationCenterPage selectOptionActionRequired(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Action Required:", index), item, true)
		return this
	}

	NotificationCenterPage selectOptionSendTo(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Send To:", index), item, true)
		return this
	}

	//////////////////// Notification ////////////////////////////
	NotificationCenterPage clickIconNotification() {
		BaseActions.click(webIcon("Notifications"))
		return this
	}

	NotificationCenterPage verifyNotificationMessage(String message) {
		BaseActions.verifyElementPresent(webNotificationMessage(message), GlobalVariable.LongTime)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	NotificationCenterPage inputSubject(int index=1, Object text) {
		BaseActions.setText(webTextbox("Subject:", index), text.toString())
		return this
	}

	NotificationCenterPage inputReplyToEmail(int index=1, Object text) {
		BaseActions.setText(webTextbox("Reply-To Email:", index), text.toString())
		return this
	}

	NotificationCenterPage inputPublishDate(int index=1, Object text) {
		BaseActions.setText(webTextbox("Publish Date:", index), text.toString())
		return this
	}

	NotificationCenterPage inputTo(int index=1, Object text) {
		BaseActions.setText(webTextbox("to", index), text.toString())
		return this
	}

	NotificationCenterPage inputActionRequired(Object text) {
		if(text.toString() != "false") {
			selectCheckbox("Action Required:", 1)
			BaseActions.setText(webTextbox("Act By:", 1), text.toString())
		}
		return this
	}

	NotificationCenterPage inputAcknowledgeBy(int index=1, Object text) {
		BaseActions.setText(webTextbox("Acknowledge By:", index), text.toString())
		return this
	}

	NotificationCenterPage inputUseCustom(Object text) {
		this.selectRadio("Use Custom")
		BaseActions.setText(webTextbox("Use Custom", 1), text.toString())
		return this
	}

	NotificationCenterPage inputMessageBody(int index=1, Object text) {
		this.setTextIframe("Message Body", text.toString())
		return this
	}

	NotificationCenterPage setTextAssociateEmployeeName(String text, String textAssociate) {
		this.setTextAssociate("Employee Name:", 1, text, textAssociate)
		return this
	}

	//////////////////// Upload File ////////////////////////////
	NotificationCenterPage uploadFileImages(int index=1, String imageName) {
		BaseActions.uploadFile(webUploadFile("Images:", index), imageName)
		return this
	}

	NotificationCenterPage uploadFileAttachments(int index=1, String attachmentName) {
		BaseActions.uploadFile(webUploadFile("Attachments:", index), attachmentName)
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	NotificationCenterPage openNotificationDetails(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "Details")
		return this
	}

	NotificationCenterPage verifyNotificaitonAppearsInResults(String type, String author, String subject, String priority) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${type};${author};${subject};${priority}")
		return this
	}

	//////////////////// Actions ////////////////////////////
	NotificationCenterPage enterNotification(String subject, String type, String priority, Object actionRequired, String email, String message) {
		inputSubject(2, subject)
		selectOptionType(3, type)
		selectOptionPriority(2, priority)
		inputActionRequired(actionRequired)
		inputReplyToEmail(2, email)
		if(message != null) inputMessageBody(message)
		return this
	}

	NotificationCenterPage searchNotification(String status, String publishedBy, String type, String publishDate, String to, String priority, String actionRequired, String subject) {
		expandSection("Advanced Filters")
		selectOptionNotificationStatus(status)
		selectOptionPublishedBy(publishedBy)
		selectOptionType(type)
		inputPublishDate(publishDate)
		inputTo(to)
		BaseActions.selectOptionByLabel(webCombobox("Priority", 1), priority, true)
		selectOptionActionRequired(actionRequired)
		inputSubject(subject)
		clickSearch()
		return this
	}

	NotificationCenterPage editReceipients(String status, String publishedBy, String type, String publishDate, String to, String priority, String actionRequired, String subject) {
		selectTab("Receipients")
		clickEdit()
		selectOptionPublishedBy(publishedBy)
		selectOptionType(type)
		inputPublishDate(publishDate)
		inputTo(to)
		BaseActions.selectOptionByLabel(webCombobox("Priority", 1), priority, true)
		selectOptionActionRequired(actionRequired)
		inputSubject(subject)
		clickSearch()
		return this
	}
}
