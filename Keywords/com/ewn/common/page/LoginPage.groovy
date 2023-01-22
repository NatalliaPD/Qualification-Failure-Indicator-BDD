package com.ewn.common.page

import org.openqa.selenium.Keys

import com.ewn.fw.lib.BaseActions

import internal.GlobalVariable

public class LoginPage extends BasePage<LoginPage> {
	LoginPage clickLogin() {
		BaseActions.click(webButton("Log In"))
		return this
	}

	LoginPage inputUsername(Object user) {
		BaseActions.setText(webTextbox("Username"), user.toString())
		return this
	}

	LoginPage inputPassword(Object password) {
		BaseActions.setEncryptedText(webTextbox("Password"), password.toString())
		return this
	}
	
	LoginPage pressEnter() {
		BaseActions.sendKeys(webButton("Log In"), Keys.chord(Keys.ENTER))
		return this
	}
	
	LoginPage verifyUserNameExist() {
		BaseActions.verifyElementClickable(webTextbox("Username"))
		return this
	}
	
	LoginPage clickUsernameField() {
		BaseActions.click(webTextbox("Username"))
		return this
	}

	LoginPage clickPasswordField() {
		BaseActions.click(webTextbox("Password"))
		return this
	}
	
	LoginPage VerifyTheLogInButtonIsInactive() {
		BaseActions.verifyElementHasAttribute(webButton("Log In"), "disabled", GlobalVariable.MediumTime)
		return this
	}

	LoginPage clickContactUs() {
		BaseActions.clickJS(webLabel("Contact Us"))
		return this
	}
	
	LoginPage clickForgetLink() {
		BaseActions.clickJS((webLabel("Forget?")))
		return this
	}

	LoginPage clickReturnToLoginLink() {
		BaseActions.clickJS((webLabel("Return to login")))
		return this
	}
	
	LoginPage verifyLogoEWNDisplays() {
		BaseActions.verifyElementAttributeValue((webImage("", 1)), "alt", "EWN Logo", GlobalVariable.MediumTime)
		return this
	}
	
	LoginPage verifyBackgroundChangesAfterRefresh(List<String> expectedListBackground, Boolean existStatus=false) {
		BaseActions.refresh()
		String _currentBackground = BaseActions.getAttribute(webBody(), "style")
		BaseActions.verifyEqual(expectedListBackground.contains(_currentBackground), existStatus)
		expectedListBackground.add(_currentBackground)
		return this
	}
	
	//////////////////// Actions ////////////////////////////
	LoginPage navigateURL(String URL=GlobalVariable.URL) {
		BaseActions.navigateToUrl(URL)
		BaseActions.maximizeWindow()
		return this
	}

	LoginPage login(String URL=GlobalVariable.URL, Object user, Object password) {
		navigateURL(URL)
		inputUsername(user)
		inputPassword(password)
		clickLogin()
		return this
	}
}
