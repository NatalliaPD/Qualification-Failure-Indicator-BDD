package com.ewn.common.page

import com.ewn.fw.lib.BaseActions

import internal.GlobalVariable

public class CompanyHomePage extends BasePage<CompanyHomePage> {

	CompanyHomePage verifyMenuNotExist(String menuItem) {
		BaseActions.verifyElementNotPresent(webMenu(menuItem), GlobalVariable.MediumTime)
		return this
	}

	CompanyHomePage clickIconLogOut() {
		BaseActions.click(webIcon("Log Out"))
		return this
	}

	CompanyHomePage clickOK() {
		BaseActions.acceptAlert()
		return this
	}

	CompanyHomePage clickCancel() {
		BaseActions.dismissAlert()
		return this
	}

	CompanyHomePage verifyAlertText(String text) {
		BaseActions.verifyEqual(BaseActions.getAlertText().contains(text), true)
		return this
	}
}
