package com.ewn.common.page

import java.util.regex.Matcher
import java.util.regex.Pattern
import org.openqa.selenium.Keys
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

import internal.GlobalVariable

public class PasswordResetPage extends BasePage<PasswordResetPage> {
	PasswordResetPage clickLogin() {
		BaseActions.click(webButton("Log In"))
		return this
	}

	PasswordResetPage inputNewPassword(Object newPassword) {
		BaseActions.setEncryptedText(webTextbox("New Password"), newPassword.toString())
		return this
	}
	
	PasswordResetPage inputNonEnryptedPassword(Object newPassword) {
		BaseActions.setText(webTextbox("New Password"), newPassword.toString())
		return this
	}
	
	PasswordResetPage clickNewPassword() {
		BaseActions.click(webTextbox("New Password"))
		return this
	}

	PasswordResetPage inputConfirmPassword(Object ConfirmPassword) {
		BaseActions.setEncryptedText(webTextbox("Confirm Password"), ConfirmPassword.toString())
		return this
	}
	
	PasswordResetPage verifyNewPassWordHiddenDots() {
		BaseActions.verifyElementAttributeValue(webTextbox("New Password"),'type', 'password', 20)
		return this
	}
	
	PasswordResetPage verifyConfirmPassWordHiddenDots() {
		BaseActions.verifyElementAttributeValue(webTextbox("Confirm Password"),'type', 'password', 20)
		return this
	}

	PasswordResetPage inputEmployeeNameID(int index=1, Object text) {
		BaseActions.setText(webTextbox("Employee Name/ID:", index), text.toString())
		return this
	}

	PasswordResetPage clickUsernameField() {
		BaseActions.click(webTextbox("Username"))
		return this
	}

	PasswordResetPage clickNewPasswordField() {
		BaseActions.click(webTextbox("New Password"))
		return this
	}
	
	PasswordResetPage verifyPopUpPresent(int timeOut=5) {
		BaseActions.verifyElementPresent(webPopUp(), timeOut)
		return this
	}

	PasswordResetPage clickConfirmPasswordField() {
		BaseActions.click(webTextbox("Confirm Password"))
		return this
	}

	PasswordResetPage clickUpdatePassword() {
		BaseActions.enhancedClick(webButton("Update Password"))
		return this
	}
	
	PasswordResetPage clickForgetLink() {
		BaseActions.clickJS((webLabel("Forget?")))
		return this
	}
	PasswordResetPage clickBackArrow() {
		BaseActions.back()
		return this
	}
	
	PasswordResetPage clickPreviousArrow() {
		BaseActions.forward()
		return this
	}
	
	PasswordResetPage verifyTextMatch(String actualText , String expectedText) {
		BaseActions.verifyEqual(actualText, expectedText)
		return this
	}
	


	PasswordResetPage clickContactUs() {
		BaseActions.clickJS(webLabel("Contact Us"))
		return this
	}

	PasswordResetPage clickReturnToLoginLink() {
		BaseActions.enhancedClick((webLabel("Return to login")))
		return this
	}

	PasswordResetPage verifyLogoEWNDisplays() {
		BaseActions.verifyElementAttributeValue((webImage("", 1)), "alt", "EWN Logo", GlobalVariable.MediumTime)
		return this
	}

	String getBackgroundImageURL() {
		return BaseActions.getAttribute(webBody(), "style")
	}

	String getURLFromResetPasswordEmail(String emailContent) {
		String expectedURL = ""
		Pattern urlPattern = Pattern.compile("\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",Pattern.CASE_INSENSITIVE);
		Matcher matcher = urlPattern.matcher(emailContent);
		while (matcher.find()) {
			expectedURL = matcher.group()
		}
		return expectedURL
	}

	String waitAndGetUnseenEmail(String emailSubject, String emailAccount, String emailPassword) {
		OutlookEmailHelper emailHelper = new OutlookEmailHelper(emailAccount, emailPassword)

		// TODO: to get the emails past five seconds. This is a workaround the issue getting emails with the same subject from system
		// The email actually is sent ealier this step to we want to check emails past five seconds.
		// Would probally fail if getting serveral emails with the same subject at the same time
		Date pastFiveSeconds = new Date(System.currentTimeMillis() - 5 * 1000);
		String expectedEmailContent = ""
		int timeoutInSeconds = 60
		while(expectedEmailContent == "" && timeoutInSeconds > 0) {
			expectedEmailContent = emailHelper.getRecentUnseenEmailBySubject(emailSubject, true, pastFiveSeconds)
			timeoutInSeconds --
			BaseActions.delay(1)
		}

		if(expectedEmailContent == "") throw new Exception("Unable to fetch email ${emailSubject}")
		return expectedEmailContent
	}
	
	String getPasswordScore() {
		TestObject passwordScore = new TestObject("Password Score")
		passwordScore.addProperty("css", ConditionType.EQUALS, ".password-strength")
		return BaseActions.getText(passwordScore)
	}
	
	String deleteEmail(String emailAccount, String emailPassword,String emailSubject) {
		OutlookEmailHelper deleteEmail = new OutlookEmailHelper(emailAccount, emailPassword).deleteEmailBySubject(emailSubject)
		return this
	}
	
	String waitAndGetEmailSubjectAfterSendingTime(String emailAccount, String emailPassword, Date rightNow = null) {
		OutlookEmailHelper emailHelper = new OutlookEmailHelper(emailAccount, emailPassword)
		if (rightNow == null) rightNow = new Date(System.currentTimeMillis() - 5 * 1000) // past 5 seconds
		String expectedEmailSubject = ""
		int timeoutInSeconds = 60
		while(expectedEmailSubject == "" && timeoutInSeconds > 0) {
			expectedEmailSubject = emailHelper.getEmailSubject(rightNow)
			timeoutInSeconds --
			BaseActions.delay(1)
		}

		if(expectedEmailSubject == "") throw new Exception("Unable to fetch email")
		return expectedEmailSubject
	}
}