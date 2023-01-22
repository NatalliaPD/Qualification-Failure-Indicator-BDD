package com.ewn.suspensions

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

public class SuspensionsPage extends BasePage<SuspensionsPage> {
	//////////////////// Button ////////////////////////////
	SuspensionsPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	SuspensionsPage clickSuspend(int index=1) {
		BaseActions.clickJS(webButton("Suspend", index))
		return this
	}

	SuspensionsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	SuspensionsPage clickEdit(int index=1) {
		BaseActions.clickJS(webButton("Edit", index))
		return this
	}

	SuspensionsPage clickPreview(int index=1) {
		BaseActions.click(webButton("Preview", index))
		return this
	}

	SuspensionsPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	SuspensionsPage selectOptionCompanyOfEmployees(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Company of Employees:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionTaskList(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task List:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionReason(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Reason:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionCovered(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Covered:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionJobTitle(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Job Title:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionOrderTasksBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order Tasks By:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionProject(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Project:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionGroup(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Group:", index), item, true)
		return this
	}

	SuspensionsPage selectOptionFacility(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Facility:", index), item, true)
		return this
	}

	SuspensionsPage selectMultipleOptionSuspensionStatus(String items) {
		this.selectMultipleOption("Suspension Status:", items)
		return this
	}

	SuspensionsPage selectMultipleTask(String items) {
		this.selectMultipleOption("Task:", items)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	SuspensionsPage moveAvailableEmployees(String listItems) {
		this.moveAvailableItems("Available Employees", listItems)
		return this
	}

	SuspensionsPage moveSelectedEmployees(String listItems) {
		this.moveSelectedItems("Selected Employees", listItems)
		return this
	}

	SuspensionsPage moveAvailableTasks(String listItems) {
		this.moveAvailableItems("Available Tasks", listItems)
		return this
	}

	SuspensionsPage moveSelectedTasks(String listItems) {
		this.moveSelectedItems("Selected Tasks", listItems)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	SuspensionsPage inputInitialActionDateRange(int index=1, Object text) {
		BaseActions.setText(webTextbox("Initial Action Date Range:", index), text.toString())
		return this
	}

	SuspensionsPage inputInitialActionDate(int index=1, Object text) {
		BaseActions.setText(webTextbox("Initial Action Date:", index), text.toString())
		return this
	}

	SuspensionsPage inputTo(int index=1, Object text) {
		BaseActions.setText(webTextbox("to", index), text.toString())
		return this
	}

	SuspensionsPage inputTaskCodeName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Task Code/Name:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

}
