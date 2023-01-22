package com.ewn.account.management

import com.ewn.common.page.BasePage
import com.ewn.common.page.Helper
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import internal.GlobalVariable
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

public class UserAssignmentsPage extends BasePage<UserAssignmentsPage> {

	//////////////////// Button ////////////////////////////
	UserAssignmentsPage clickSearch(int index=1) {
		BaseActions.scrollIntoEWNView(webButton("Search", index))
		BaseActions.click(webButton("Search", index))
		return this
	}

	UserAssignmentsPage clickPreview(int index=1) {
		BaseActions.click(webButton("Preview", index))
		return this
	}

	UserAssignmentsPage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	UserAssignmentsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	UserAssignmentsPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}

	UserAssignmentsPage clickOK(int index=1) {
		BaseActions.click(webButton("OK", index))
		return this
	}
	
	UserAssignmentsPage clickCancelButton(int index = 1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}
	
	UserAssignmentsPage clickViewTaskListButton(int index = 1) {
		BaseActions.click(webButton("View Task List", index))
		return this
	}
	
	UserAssignmentsPage clickWebTab(String tabName) {
		BaseActions.enhancedClick(webTab(tabName))
		return this
	}

	UserAssignmentsPage clickNo(int index=1) {
		BaseActions.click(webButton("No", index))
		return this
	}

	UserAssignmentsPage verifyActionButtonAvailable(int index=1) {
		BaseActions.verifyElementClickable(webButton("Action", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	UserAssignmentsPage selectOptionAssignmentType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Assignment Type:", index), item, true)
		return this
	}

	UserAssignmentsPage selectOptionAssignedBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Assigned By:", index), item, true)
		return this
	}

	UserAssignmentsPage selectOptionTaskList(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task List:", index), item, true)
		return this
	}

	UserAssignmentsPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	UserAssignmentsPage selectOptionType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Type:", index), item, true)
		return this
	}
	
	UserAssignmentsPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	UserAssignmentsPage selectOptionOrderBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order By:", index), item, true)
		return this
	}

	UserAssignmentsPage clickOnACheckboxOfDirectAssignments(String text, int index=8) {
		Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(text, index, true)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	UserAssignmentsPage moveAvailableEmployees(String listItems) {
		this.moveAvailableItems("Available Employees", listItems)
		return this
	}

	UserAssignmentsPage moveSelectedEmployees(String listItems) {
		this.moveSelectedItems("Selected Employees", listItems)
		return this
	}

	UserAssignmentsPage moveAvailableTasks(String listItems) {
		this.moveAvailableItems("Available Tasks", listItems)
		return this
	}

	UserAssignmentsPage moveSelectedTasks(String listItems) {
		this.moveSelectedItems("Selected Tasks", listItems)
		return this
	}

	UserAssignmentsPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	UserAssignmentsPage moveSelectedEvaluations(String listItems) {
		this.moveSelectedItems("Selected Evaluations", listItems)
		return this
	}

	List<String> getAvailableTasks(String searchBy=null) {
		return this.getListGroupItems("Available Tasks", searchBy)
	}

	//////////////////// Textbox ////////////////////////////
	UserAssignmentsPage inputDueInDays(int index=1, Object text) {
		BaseActions.setText(webTextbox("Due in (Days):", index), text.toString())
		return this
	}

	UserAssignmentsPage inputRequalificationPeriod(int index=1, Object text) {
		BaseActions.setText(webTextbox("Requalification Period:", index), text.toString())
		return this
	}

	UserAssignmentsPage inputTaskNameCode(Object text) {
		selectCheckbox("No Due date", 1, false)
		BaseActions.setText(webTextbox("Task Name/Code:", 1), text.toString())
		return this
	}

	UserAssignmentsPage inputEvaluationNameId(Object text) {
		selectCheckbox("No Due date", 1, false)
		BaseActions.setText(webTextbox("Evaluation Name/ID:", 1), text.toString())
		return this
	}
	
	//////////////////// Modal Label //////////////////////
	TestObject modalPreviewAlert() {
		TestObject modalPreview = new TestObject("Modal Preview")
		modalPreview.addProperty("css", ConditionType.EQUALS, ".modal-body-alert .ng-binding")
		return modalPreview
	}
	
	String getModalPreviewAlertText() {
		return BaseActions.findWebElement(this.modalPreviewAlert()).getText()
	}
	
	UserAssignmentsPage verifyModalPreviewAlertText() {
		BaseActions.verifyEqual(Pages.As(UserAssignmentsPage).getModalPreviewAlertText(), "Please select at least one employee and at least one task.")
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}
	
	UserAssignmentsPage verifyUserAssignmentDisplayed(String employeeName, String taskCode, String taskName, String assignedDate,String assignedMethod=null) {
		String assignedMethodString = ""
		if (assignedMethod != null) assignedMethodString = ";" + assignedMethod
		Pages.As(OnDataTable).setTable("Results", 1).verifyVisibleTableRow("${employeeName};${taskCode};${taskName};${assignedDate}${assignedMethodString}")
		return this
	}

	//////////////////// Actions ////////////////////////////
	UserAssignmentsPage searchUserAssignments(String type, String assignedBy, String taskList, String taskNameCode, String listAvailableEmployees) {
		selectOptionAssignmentType(type)
		selectOptionAssignedBy(assignedBy)
		selectOptionTaskList(taskList)
		inputTaskNameCode(taskNameCode)
		moveAvailableEmployees(listAvailableEmployees)
		clickSearch()
		return this
	}

	UserAssignmentsPage assignTasks(String listAvailableEmployees, String taskList, String listAvailableTasks, Object dueInDays=null) {
		selectButtonOption("Assign", "Tasks")
		moveAvailableEmployees(listAvailableEmployees)
		selectOptionTaskList(2, taskList)
		moveAvailableTasks(listAvailableTasks)
		if (dueInDays == null) selectCheckbox("No Due date", 1)
		else {
			selectCheckbox("No Due date", 1, false)
			inputDueInDays(dueInDays)
		}
		clickPreview()
		clickSave(2)
		return this
	}

	UserAssignmentsPage assignEvaluations(String listAvailableEmployees, String evaluationAuthor, String type, String orderBy, String listAvailableEvaluations, Object requalificationPeriod=null) {
		selectButtonOption("Assign", "Evaluations")
		moveAvailableEmployees(listAvailableEmployees)
		selectOptionEvaluationAuthor(evaluationAuthor)
		selectOptionType(type)
		selectOptionOrderBy(orderBy)	
		clickFilter(3)
		moveAvailableEvaluations(listAvailableEvaluations)
		if (requalificationPeriod == null) selectCheckbox("Non-Expiring", 1)
		else {
			selectCheckbox("Non-Expiring", 1, false)
			inputRequalificationPeriod(requalificationPeriod)
		}
		
		clickPreview(2)
		clickSave()
		return this
	}

	UserAssignmentsPage deleteAssignment(String listText, int index=8) {
		listText.split(";").each {
			Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(it, index, true)
		}
		selectButtonOption("Action", "Delete")
		clickYes()
		return this
	}
	
	UserAssignmentsPage deleteAllAssignment(int index=8) {
		BaseActions.selectCheckbox(webTableFirstCell(index))
		selectButtonOption("Action", "Delete")
		clickYes()
		return this
	}
	
	UserAssignmentsPage deleteAssignmentFromUser(String nameTaskList , String listItems , int index=8 ) {
		selectOptionTaskList(nameTaskList)
		moveAvailableEmployees(listItems)
		clickSearch()
		String _getContent = getNoResultsMessageFromTable()
		if(_getContent != "[No results returned.]")
		{
			Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(1, index)
			selectButtonOption("Action", "Delete")
			clickYes()
		}
		return this
	}

	UserAssignmentsPage searchUserAssignmentsForEvaluation(String type, String assignedBy, String evaluationAuthor, String evaluationId, String listAvailableEmployees) {
		selectOptionAssignmentType(type)
		selectOptionAssignedBy(assignedBy)
		selectOptionEvaluationAuthor(evaluationAuthor)
		inputEvaluationNameId(evaluationId)
		moveAvailableEmployees(listAvailableEmployees)
		clickSearch()
		return this
	}

	List<String> getListTaskOnTable(Object range) {
		return Pages.As(OnDataTable).setTable("Name_Header", 1).getListRowsContentOnTable(range)
	}

	List<String> getNoResultsMessageFromTable() {
		return Pages.As(OnDataTable).setTable("Name_Header", 1).getListRowsContentOnTableUser(1..1)
	}

	UserAssignmentsPage verifyNoResultsDisplay(List<String> listMessage) {
		BaseActions.verifyEqual(Pages.As(UserAssignmentsPage).getNoResultsMessageFromTable(), listMessage)
		return this
	}

	UserAssignmentsPage verifyTaskAvailableInDropdown( String taskName, Object status=true) {
		def _actualTasks = getAvailableTasks()
		BaseActions.verifyEqual(_actualTasks.contains(taskName), status.toBoolean())
		return this
	}

	UserAssignmentsPage verifyEvaluationAssignmentsDisplayed(String employeeName, String evaluationAuthor, String evaluationId, String evaluationTitle, String assignedMethod) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${employeeName};${evaluationAuthor};${evaluationId};${evaluationTitle};${assignedMethod}")
		return this
	}

	UserAssignmentsPage verifyEvaluationSortedBy(String orderBy="Evaludation ID", String sortedBy="ascending") {
		pleaseWait(GlobalVariable.ShortTime)
		List evaluationlist = Pages.As(UserAssignmentsPage).getListGroupItems("Available Evaluations")
		if (orderBy == "Title") {evaluationlist = evaluationlist.collect{it.replaceFirst(/^\d*:/, "").trim()}}
		BaseActions.verifyEqual(Pages.As(Helper).getSortOrderStatus(evaluationlist, "string"), sortedBy)
		return this
	}

	UserAssignmentsPage verifyViewTaskListButtonClickable() {
		BaseActions.verifyElementClickable(webButton("View Task List"), FailureHandling.STOP_ON_FAILURE)
		return this
	}
	
	//////////////////// Label ////////////////////////////
	UserAssignmentsPage verifyTotalRecords(int index=1, Object expectedRecords) {
		BaseActions.verifyElementText(webTotalRecords(index), expectedRecords.toString())
		return this
	}

	UserAssignmentsPage verifyColorForNonExpiringField(String expectedColor) {
		String _xpath = webCheckbox("Non-Expiring").findPropertyValue("xpath") + "/.."
		BaseActions.verifyColor(BaseActions.createTestObject("Non-Expiring_Label", "xpath", _xpath), "color", expectedColor)
		return this
	}

	UserAssignmentsPage inputRequalificationPeriodInPreviewEvaluationAssignments(Object requalificationPeriodNumber) {
		Pages.As(OnDataTable).setTable("User Assignments", 3).setTextOnTable(1, "Interval", requalificationPeriodNumber)
		return this
	}

	UserAssignmentsPage inputDueInDaysInPreviewTaskAssignments(Object dueInDaysNumber) {
		Pages.As(OnDataTable).setTable("User Assignments", 2).setTextOnTable(1, "Due In (Days)", dueInDaysNumber)
		clickSave(2)
		return this
	}

	UserAssignmentsPage verifyDueInDaysInPreviewTaskAssignments(String expectedDueInDays) {
		Pages.As(OnDataTable).setTable("User Assignments", 2).verifyTextValueOnTable(1, "Due In (Days)", expectedDueInDays)
		return this
	}

	UserAssignmentsPage VerifyRequalificationPeriodTextboxGrayout(int index=1) {
		def _IntervalTextboxObject = Pages.As(OnDataTable).setTable("User Assignments", 3).webTableTextbox(index, "Interval")
		BaseActions.verifyElementAttributeValue(_IntervalTextboxObject, "disabled", "true", GlobalVariable.MediumTime)
		return this
	}
}
