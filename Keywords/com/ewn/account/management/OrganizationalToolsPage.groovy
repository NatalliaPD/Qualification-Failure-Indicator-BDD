package com.ewn.account.management

import com.ewn.MyRequirementsPage
import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.ewn.task.management.TaskPage
import com.ewn.common.page.Helper

public class OrganizationalToolsPage extends BasePage<OrganizationalToolsPage> {
	//////////////////// Button ////////////////////////////
	OrganizationalToolsPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	OrganizationalToolsPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	OrganizationalToolsPage clickSave(int index=1) {
		BaseActions.scrollIntoEWNView(webButton("Save", index))
		BaseActions.click(webButton("Save", index))
		return this
	}

	OrganizationalToolsPage clickDelete(int index=1) {
		BaseActions.click(webButton("Delete", index))
		return this
	}

	OrganizationalToolsPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}

	OrganizationalToolsPage clickEdit(int index=1) {
		BaseActions.click(webButton("Edit", index))
		return this
	}

	OrganizationalToolsPage clickAdd(int index=1) {
		BaseActions.click(webButton("Add", index))
		return this
	}

	OrganizationalToolsPage clickPreview(int index=1) {
		BaseActions.scrollIntoEWNView(webButton("Preview", index))
		BaseActions.click(webButton("Preview", index))
		return this
	}

	OrganizationalToolsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
	}

	OrganizationalToolsPage clickOK(int index=1) {
		BaseActions.click(webButton("OK", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	OrganizationalToolsPage selectOptionType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Type:", index), item, true)
		return this
	}

	OrganizationalToolsPage selectOptionStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Status:", index), item, true)
		return this
	}

	OrganizationalToolsPage selectCompany(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Company:", index), item, true)
		return this
	}

	OrganizationalToolsPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	OrganizationalToolsPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	OrganizationalToolsPage moveSelectedEvaluations(String listItems) {
		this.moveSelectedItems("Selected Evaluations", listItems)
		return this
	}

	OrganizationalToolsPage moveAvailableEmployees(String listItems) {
		this.moveAvailableItems("Available Employees", listItems)
		return this
	}

	OrganizationalToolsPage moveSelectedEmployees(String listItems) {
		this.moveSelectedItems("Selected Employees", listItems)
		return this
	}

	OrganizationalToolsPage moveAvailableTasks(String listItems) {
		this.moveAvailableItems("Available Tasks", listItems)
		return this
	}

	OrganizationalToolsPage moveSelectedTasks(String listItems) {
		this.moveSelectedItems("Selected Tasks", listItems)
		return this
	}

	List<String> getAvailableTasks(String searchBy=null) {
		return this.getListGroupItems("Available Tasks", searchBy)
	}

	//////////////////// Textbox ////////////////////////////
	OrganizationalToolsPage inputNameCode(int index=1, Object text) {
		BaseActions.setText(webTextbox("Name/Code:", index), text.toString())
		return this
	}

	OrganizationalToolsPage inputCode(int index=1, Object text) {
		BaseActions.setText(webTextbox("Code:", index), text.toString())
		return this
	}

	OrganizationalToolsPage inputName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Name:", index), text.toString())
		return this
	}

	OrganizationalToolsPage inputDescription(int index=1, Object text) {
		BaseActions.setText(webTextbox("Description:", index), text.toString())
		return this
	}

	OrganizationalToolsPage inputRequalificationPeriod(int index=1, Object text) {
		BaseActions.setText(webTextbox("Requalification Period:", index), text.toString())
		return this
	}

	OrganizationalToolsPage inputRequalificationPeriodInEvaluation(Object row, Object requalificationPeriodNumber) {
		Pages.As(OnDataTable).setTable("Organizational Tools", 4).setTextOnTable(row, "Interval", requalificationPeriodNumber)
		return this
	}

	OrganizationalToolsPage inputDueInDaysInTaskEdit(int index=1, Object text) {
		selectCheckbox("No Due date", 1, false)
		BaseActions.setText(webTextbox("Due in (Days):", index), text.toString())
		return this
	}

	OrganizationalToolsPage inputStartDate(int index=1, Object text) {
		BaseActions.setText(webTextbox("Start Date:", index), text.toString())
		return this
	}

	OrganizationalToolsPage inputEndDate(int index=1, Object text) {
		BaseActions.setText(webTextbox("End Date:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	List<String> getListOrgToolsContentsOnResults(Object range) {
		return this.onTable("Results", 1).getListRowsContentOnTable(range)
	}

	OrganizationalToolsPage sortByColumnName(String columnName) {
		this.onTable("Results", 1).clickOnHeader(columnName)
		return this
	}

	//////////////////// Search ////////////////////////////
	OrganizationalToolsPage verifyOrganizationalToolsDisplayed(String group, String name, String code, String status) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${group};${name};${code};${status}")
		return this
	}

	OrganizationalToolsPage verifyTaskOnTableDisplayed(String code, String name, String date, String addedby , Object dueInDay = null) {
		if (dueInDay == null) Pages.As(OnDataTable).setTable("Tasks", 1).verifyTableRow("${code};${name};${date};${addedby}")
		else {
			Pages.As(OnDataTable).setTable("Tasks", 1).verifyTableRow("${code};${name};${date};${addedby};${dueInDay}")
		}
		return this
	}

	OrganizationalToolsPage deleteOrganizationalTool(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(filterBy, 6)
		clickDelete()
		clickYes()
		return this
	}

	OrganizationalToolsPage openOrganizationalTool(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "View")
		return this
	}

	OrganizationalToolsPage removeEmployee(String filterBy) {
		Pages.As(OnDataTable).setTable("Users", 1).selectIconByTextOnTable(filterBy, "Remove")
		clickYes()
		return this
	}

	OrganizationalToolsPage copyAssginTask(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "Copy")
		waitUploading()
		clickYes()
		return this
	}

	OrganizationalToolsPage copyAssignTaskFromOrganizationalTool(int index=1, String filterBy) {
		inputNameCode(index, filterBy)
		clickSearch(index)
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "Copy")
		waitUploading()
		clickYes()
		return this
	}
	
	OrganizationalToolsPage searchOrganizationalTool(String nameCode, String type, String status) {
		inputNameCode(nameCode)
		selectOptionType(type)
		selectOptionStatus(status)
		clickSearch()
		return this
	}

	OrganizationalToolsPage addOrganizationalTool(String type, String name, String code, String description, Object status=true) {
		selectButtonOption("Add", type)
		inputName(2, name)
		inputCode(2, code)
		inputDescription(2, description)
		selectCheckbox("Active:", 1, status)
		clickSave()
		return this
	}

	//////////////////// Evaluations ////////////////////////////
	OrganizationalToolsPage verifyEvaluationDisplayed(String code, String name, String addOnDate, String addedby, String interval, Boolean shouldBeDisplayed=true) {
		Pages.As(OnDataTable).setTable("Evaluations", 1).verifyTableRow("${code};${name};${addOnDate};${addedby};${interval}", shouldBeDisplayed)
		return this
	}

	OrganizationalToolsPage deleteEvaluation(String filterBy) {
		Pages.As(OnDataTable).setTable("Evaluations", 1).selectCheckboxOnTable(filterBy, 7)
		clickDelete(2)
		clickYes()
		return this
	}

	OrganizationalToolsPage addEvaluation(String listEvaluations, Object requalificationPeriod=null) {
		clickAdd(2)
		moveAvailableEvaluations(listEvaluations)
		if(requalificationPeriod != null) {
			selectCheckbox("Non-Expiring", 1, false)
			inputRequalificationPeriod(requalificationPeriod)
		}
		else selectCheckbox("Non-Expiring", 1, true)
		selectButtonUnderLabel("Preview", "Requalification Period:")
		waitUploading()
		selectButtonUnderLabel("Save", "Evaluations")
		return this
	}

	OrganizationalToolsPage updateEvaluation(String evaluationCode, String evaluationName, Object requalificationPeriod=null) {
		if(BaseActions.waitForPresent(webLabel(evaluationName), 1)) deleteEvaluation(evaluationName)
		addEvaluation("${evaluationCode}: ${evaluationName}", requalificationPeriod)
	}

	//////////////////// ADDING TASK INTO ORG ////////////////////////////
	OrganizationalToolsPage verifyTaskAvailableInDropdown( String taskName, Object status=true) {
		def actualTasks = this.getAvailableTasks()
		BaseActions.verifyEqual(actualTasks.contains(taskName), status.toBoolean())
		return this
	}

	OrganizationalToolsPage verifyTaskOnTable(String taskCode, String taskName, Object status=true) {
		Pages.As(OnDataTable).setTable("Task Owner_Header", 1).verifyTableRow("${taskCode};${taskName}", status.toBoolean())
		return this
	}

	OrganizationalToolsPage verifyEmployeesOnTable(String employeesName, String ewnId, String addOnDate, Object status=true) {
		Pages.As(OnDataTable).setTable("Users", 1).verifyTableRow("${employeesName};${ewnId};${addOnDate}", status.toBoolean())
		return this
	}

	OrganizationalToolsPage deleteTask(String filterBy) {
		Pages.As(OnDataTable).setTable("Task Owner_Header", 1).selectCheckboxOnTable(filterBy, 7)
		selectButtonOption("Action", "Delete")
		clickYes()
		return this
	}

	OrganizationalToolsPage inputDueInDays(int index=1, String row, Object col, Object text) {
		selectCheckbox("No Due date", 1, false)
		Pages.As(OnDataTable).setTable("Tasks", 1).setTextOnTable(row, col, text)
		waitUploading()
		clickSave(1)
		return this
	}

	OrganizationalToolsPage clickSaveTasks(int index=1) {
		waitUploading()
		selectButtonUnderLabel("Save", "Tasks")
		return this
	}

	//////////////////// Actions ////////////////////////////
	OrganizationalToolsPage addTasks(String listAvailableTaskList=null, String listAssignedTaskList=null) {
		if(listAvailableTaskList != null) moveAvailableTasks(listAvailableTaskList)
		if(listAssignedTaskList != null) moveSelectedTasks(listAssignedTaskList)
		clickPreview(2)
		waitUploading()
		return this
	}

	OrganizationalToolsPage editTaskDueInDays(String filterBy, int dueInDays) {
		Pages.As(OnDataTable).setTable("Task Owner_Header", 1).selectCheckboxOnTable(filterBy, 7)
		selectButtonOption("Action", "Edit")
		inputDueInDaysInTaskEdit(dueInDays)
		clickOK(1)
		return this
	}

	OrganizationalToolsPage deleteTaskIfExistedOnTaskTab(String taskCode, String taskName, String filterBy) {
		boolean _isTaskExist = Pages.As(OnDataTable).setTable("Task Owner_Header", 1).isTableRowExisting("${taskCode};${taskName}")
		if (_isTaskExist){
			deleteTask(filterBy)
		}
		return this
	}

	OrganizationalToolsPage deleteExistingProjectOnTable(String projectName) {
		boolean _isProjectExist = Pages.As(OnDataTable).setTable("Results", 1).isTableRowExisting(projectName)
		if (_isProjectExist){
			deleteOrganizationalTool(projectName)
		}
		return this
	}
	
	OrganizationalToolsPage addProjectIfNotExisting(int conditionIndex=1, String projectCode, String ProjectName, String description, String type) {
		boolean _isProjectExist = Pages.As(OnDataTable).setTable("Organizational Tools", conditionIndex).isTableRowExisting("${projectCode};${ProjectName}")
		if (!_isProjectExist) {
			addOrganizationalTool(type, ProjectName, projectCode, description)
			selectTab("Search")
		}
		return this
	}
	OrganizationalToolsPage verifyDateInfoInPropetiesTab(String startDate, String endDate) {
		BaseActions.verifyElementTextContent(webLabelValue("Start Date:", 1), startDate)
		BaseActions.verifyElementTextContent(webLabelValue("End Date:", 1), endDate)
		return this
	}

	OrganizationalToolsPage verifyLabelActiveStatus(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Active:", index), expectedText)
		return this
	}

	//////////////////// Verification ////////////////////////////
	OrganizationalToolsPage verifyOrganizationSortBy(String columnName, String sortedBy) {
		List<String> dataValue = Pages.As(OnDataTable).setTable("Results", 1).getListValuesTableColumn(columnName)
		BaseActions.verifyEqual(Pages.As(Helper).getSortOrderStatus(dataValue, "string"), sortedBy)
		return this
	}

	OrganizationalToolsPage uncheckNonExpiringAndInputRequalificationForItem(int index=1, String evaluationId, String evaluationName, String interval) {
		boolean _isEvaluationExist = Pages.As(OnDataTable).setTable("Evaluations", 1).isTableRowExisting("${evaluationId};${evaluationName}")
		if (_isEvaluationExist){
			selectCheckboxOnEvaluator("Non-Expiring", index, false)
			inputRequalificationPeriodInEvaluation(evaluationId, interval)
			clickSave(1)
		}
		return this
	}

	OrganizationalToolsPage addTaskToOrgTooltIfNotExisting(int conditionIndex=1, String taskCode, String taskName, String companyTaskList) {
		boolean _isTaskExist = Pages.As(OnDataTable).setTable("Tasks", conditionIndex).isTableRowExisting("${taskCode};${taskName}")
		if (!_isTaskExist) {
			clickAdd(2)
			waitUploading()
			selectCompany(2, companyTaskList)
			addTasks(taskName)
			clickSaveTasks(2)
		}
		return this
	}

	///////////checkbox//////////////
	OrganizationalToolsPage checkAndUncheckCheckboxActive(int index=1, boolean checkbox) {
		selectCheckbox("Active:", index, checkbox)
		return this
	}

}
