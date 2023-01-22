package com.ewn.other.adminreports

import com.ewn.common.page.BasePage
import com.ewn.fw.lib.BaseActions

public class EmployeeCredentialsPage extends BasePage<EmployeeCredentialsPage> {

	//////////////////// Combobox ////////////////////////////
	EmployeeCredentialsPage selectOptionFacility(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Facility:", index), item, true)
		return this
	}
	
	EmployeeCredentialsPage selectOptionJobTitle(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Job Title:", index), item, true)
		return this
	}
	
	EmployeeCredentialsPage selectOptionGroup(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Group:", index), item, true)
		return this
	}

	EmployeeCredentialsPage selectOptionUserType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("User Type:", index), item, true)
		return this
	}

	EmployeeCredentialsPage selectOptionSupervisor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Supervisor:", index), item, true)
		return this
	}

	EmployeeCredentialsPage selectOptionUserStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("User Status:", index), item, true)
		return this
	}

}
