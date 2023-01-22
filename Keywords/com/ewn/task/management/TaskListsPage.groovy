package com.ewn.task.management

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

public class TaskListsPage extends BasePage<TaskListsPage> {

	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	//////////////////// Button ////////////////////////////
	TaskListsPage clickSearch() {
		BaseActions.click(webButton("Search", 1))
		return this
	}

	TaskListsPage clickAdd() {
		BaseActions.click(webButton("Add", 1))
		return this
	}

	TaskListsPage clickSave(int index=1) {
		BaseActions.scrollIntoEWNView(webButton("Save", index))
		BaseActions.click(webButton("Save", index))
		return this
	}

	TaskListsPage clickEdit(int index=1) {
		BaseActions.click(webButton("Edit", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	TaskListsPage selectOptionTaskListStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task List Status:", index), item, true)
		return this
	}

	TaskListsPage selectOptionTaskListVisibility(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task List Visibility:", index), item, true)
		return this
	}

	TaskListsPage selectOptionStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Status:", index), item, true)
		return this
	}

	TaskListsPage selectOptionVisibility(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Visibility:", index), item, true)
		return this
	}

	//////////////////// Label ////////////////////////////
	TaskListsPage verifyLabelTaskListName(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Task List Name:", index), expectedText)
		return this
	}

	TaskListsPage verifyLabelStatus(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Status:", index), expectedText)
		return this
	}

	TaskListsPage verifyLabelVisibility(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Visibility:", index), expectedText)
		return this
	}

	TaskListsPage verifyLabelDefault(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Default:", index), expectedText)
		return this
	}

	TaskListsPage verifyLabelDescription(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Description:", index), expectedText)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	TaskListsPage moveAvailableTasks(String listItems) {
		this.moveAvailableItems("Available Tasks", listItems)
		return this
	}

	TaskListsPage moveSelectedTasks(String listItems) {
		this.moveSelectedItems("Selected Tasks", listItems)
		return this
	}

	List<String> getSelectedTasks(String searchBy=null) {
		return this.getListGroupItems("Selected Tasks", searchBy)
	}

	//////////////////// Textbox ////////////////////////////
	TaskListsPage inputSearchByKeyword(int index=1, Object text) {
		BaseActions.setText(webTextbox("Search by Keyword:", index), text.toString())
		return this
	}

	TaskListsPage inputTaskListName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Task List Name:", index), text.toString())
		return this
	}

	TaskListsPage inputDescription(int index=1, Object text) {
		BaseActions.setText(webTextbox("Description:", index), text.toString())
		return this
	}

	//////////////////// Actions ////////////////////////////
	TaskListsPage searchTaskList(String status, String visibility, String byKeyword) {
		selectOptionTaskListStatus(status)
		selectOptionTaskListVisibility(visibility)
		inputSearchByKeyword(byKeyword)
		clickSearch()
		return this
	}

	TaskListsPage addTaskList(String name, String status, String visibility, Object defaultvalue, String description) {
		clickAdd()
		inputTaskListName(name)
		selectOptionStatus(3, status)
		selectOptionVisibility(3, visibility)
		selectCheckbox("Default:", 1, defaultvalue)
		inputDescription(3, description)
		clickSave(2)
		return this
	}
	
	TaskListsPage addTaskIntoTaskList(String taskListName, List taskNames) {
		this.clickEdit(2)
		taskNames.each { this.moveAvailableTasks(it) }
		clickSave(2)
		return this
	}

	TaskListsPage editTasks(String listAvailableTaskList=null, String listAssignedTaskList=null) {
		selectTab("Tasks")
		clickEdit(2)
		if(listAvailableTaskList != null) moveAvailableTasks(listAvailableTaskList)
		if(listAssignedTaskList != null) moveSelectedTasks(listAssignedTaskList)
		clickSave(2)
		return this
	}

	TaskListsPage openTaskListDetail(String taskListName) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(taskListName, "Details")
		return this
	}

	TaskListsPage verifyTaskListOnTable(String taskListName, String status, String visibility) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${taskListName};${status};${visibility}")
		return this
	}

	TaskListsPage verifyTaskOnTable(String taskCode, String taskName, Object status=true) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${taskCode};${taskName}", status.toBoolean())
		return this
	}

	List<String> getListTaskOnTable(Object range) {
		return Pages.As(OnDataTable).setTable("Code_Header", 1).getListRowsContentOnTable(range)
	}

	TaskListsPage addNewTaskListIfNotExist(String statusList, String visibilityList, String byKeyword, String name, String status, String visibility, Object defaultvalue, String description) {
		String _ResultsStatus = searchTaskList(statusList, visibilityList, byKeyword).onTable("Results").getTableStatus()
		if(_ResultsStatus == "No results returned.") {
			addTaskList(name, status, visibility, defaultvalue, description)
		}
		return this
	}
}
