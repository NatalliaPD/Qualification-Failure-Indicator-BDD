package com.ewn.common.page
import com.ewn.fw.lib.BaseActions
import org.openqa.selenium.support.Color;

import com.kms.katalon.core.testobject.TestObject

import internal.GlobalVariable

public class LogoutPage extends BasePage<LogoutPage> {

	TestObject LogoutIcon() {
		return webIcon("Log Out")
	}

	TestObject LogoutIconOnTracerAssetManagement() {
		return webIconOnTracerAssetManagement("logout-button")
	}

	TestObject EWNHeaderLogo() {
		return BaseActions.createTestObject("EWN Header logo", "css", ".header-logo-normal")
	}

	String getLogoutTooltipText() {
		TestObject tooltipPopup = BaseActions.createTestObject("Log Out tooltip", "css", ".popover")
		return BaseActions.getText(tooltipPopup)
	}

	LogoutPage hoverOnLogoutIcon() {
		BaseActions.mouseOver(LogoutIcon())
		return this
	}

	LogoutPage clickLogoutIcon() {
		BaseActions.click(LogoutIcon())
		
		// attempt to workaround issue with alert not found
		try {
			BaseActions.verifyAlertPresent(3)
		} catch (Exception e) {
			BaseActions.click(LogoutIcon())
		}
		return this
	}

	LogoutPage clickLogoutIconOnTracerAssetManagement() {
		BaseActions.click(LogoutIconOnTracerAssetManagement())
		return this
	}

	LogoutPage clickLogoutButton() {
		BaseActions.click(webButton("Log Out"))
		return this
	}
	LogoutPage clickSupportIcon() {
		BaseActions.click(webIcon("Support"))
		return this
	}

	LogoutPage clickContextOption(String option) {
		BaseActions.click(webLabel(option))
		return this
	}

	LogoutPage waitForContactUsPopupPresent() {
		BaseActions.waitForElementVisible(webLabel("Contact Us"), GlobalVariable.LongTime)
		return this
	}

	LogoutPage verifyLogoEWNDisplays() {
		BaseActions.verifyElementAttributeValue((webImage("", 1)), "alt", "EWN Logo", GlobalVariable.ShortTime)
		return this
	}

	LogoutPage verifyBodyHasBackgroundImage() {
		String _currentBackground = BaseActions.getAttribute(webBody(), "style")
		BaseActions.verifyMatch(_currentBackground, ".*background-image.*", true)
		return this
	}

	LogoutPage clickEWNLogo() {
		BaseActions.click(EWNHeaderLogo())
		return this
	}

	String waitAndGetCurrentURL() {
		BaseActions.delay(5) // page could be redirected
		return BaseActions.getUrl()
	}

	LogoutPage verifyElementPresent(Object label) {
		BaseActions.verifyElementPresent(webLabel("${label}"), GlobalVariable.ShortTime)
		return this
	}

	LogoutPage verifyLogOutButtonColor(String expected) {
		String currentButtonColor = BaseActions.getCSSValue(webLabel("Log Out"), "background-color")
		String hex = Color.fromString(currentButtonColor).asHex();
		BaseActions.verifyMatch(hex, expected, false)
		return this
	}
}
