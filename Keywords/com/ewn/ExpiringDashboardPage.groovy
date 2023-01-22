package com.ewn

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

import internal.GlobalVariable

public class ExpiringDashboardPage extends BasePage<ExpiringDashboardPage> {

	//////////////////// Button ////////////////////////////
	ExpiringDashboardPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	ExpiringDashboardPage selectOptionJobTitle(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Job Title:", index), item, true)
		return this
	}

	ExpiringDashboardPage selectMultipleOptionFacility(String items) {
		this.selectMultipleOption("Facility:", items)
		return this
	}

	ExpiringDashboardPage selectMultipleOptionGroup(String items) {
		this.selectMultipleOption("Group:", items)
		return this
	}

	ExpiringDashboardPage selectMultipleOptionProject(String items) {
		this.selectMultipleOption("Project:", items)
		return this
	}

	ExpiringDashboardPage selectMultipleOptionSupervisor(String items) {
		this.selectMultipleOption("Supervisor:", items)
		return this
	}

	ExpiringDashboardPage selectMultipleOptionEvaluationType(String items) {
		this.selectMultipleOption("Evaluation Type:", items)
		return this
	}

	ExpiringDashboardPage selectMultipleOptionTaskList(String items) {
		this.selectMultipleOption("Task List", items)
		return this
	}

	//////////////////// Expiring ////////////////////////////
	String getExpiringNumber(String label) {
		BaseActions.getAttribute(webExpiringNumber(label), "textContent")
	}

	ExpiringDashboardPage verifyExpiringNumber(String label, Object expectedNumber) {
		BaseActions.verifyElementTextContent(webExpiringNumber(label), expectedNumber.toString())
		return this
	}

	//////////////////// Textbox ////////////////////////////
	ExpiringDashboardPage inputEmployeeName(int index=1, Object text) {
		BaseActions.setText(webTextbox("EmployeeName", index), text.toString())
		return this
	}

	ExpiringDashboardPage inputEvaluationIDName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation ID/Name:", index), text.toString())
		return this
	}

	ExpiringDashboardPage inputExpiresBefore(int index=1, Object text) {
		BaseActions.setText(webTextbox("Expires Before:", index), text.toString())
		return this
	}

	ExpiringDashboardPage inputTaskCodeName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Task Code/Name:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

}
