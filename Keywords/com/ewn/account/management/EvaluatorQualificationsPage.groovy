package com.ewn.account.management
import com.ewn.fw.lib.BaseActions
import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.Pages

public class EvaluatorQualificationsPage extends BasePage<EvaluatorQualificationsPage> {
	//////////////////// Button ////////////////////////////
	EvaluatorQualificationsPage clickEdit(int index=1) {
		BaseActions.click(webButton("Edit", index))
		return this
	}

	EvaluatorQualificationsPage scrollUpAndClickAdd(int index=1) {
		BaseActions.scrollUpAndClick(webButton("Add", index))
		return this
	}

	EvaluatorQualificationsPage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	EvaluatorQualificationsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	EvaluatorQualificationsPage clickPreview(int index=1) {
		BaseActions.click(webButton("Preview", index))
		return this
	}

	EvaluatorQualificationsPage clickDelete(int index=1) {
		BaseActions.click(webButton("Delete", index))
		return this
	}

	EvaluatorQualificationsPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}
	
	EvaluatorQualificationsPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	
	EvaluatorQualificationsPage selectOptionTaskList(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task List:", index), item, true)
		return this
	}
	
	EvaluatorQualificationsPage selectMultipleOptionTask(int index=1, String items) {
		this.selectMultipleOption("Task:", items)
		return this
	}
	
	EvaluatorQualificationsPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	EvaluatorQualificationsPage selectOptionOrderBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order By:", index), item, true)
		return this
	}

	EvaluatorQualificationsPage selectOptionType(int index=1,String item) {
		BaseActions.selectOptionByLabel(webCombobox("Type:", index), item, true)
		return this
	}

	EvaluatorQualificationsPage selectOptionEvaluationType(int index=1,String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	EvaluatorQualificationsPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	EvaluatorQualificationsPage moveSelectedEvaluations(String listItems) {
		this.moveSelectedItems("Selected Evaluations", listItems)
		return this
	}

	EvaluatorQualificationsPage moveAvailableEmployees(String listItems) {
		this.moveAvailableItems("Available Employees", listItems)
		return this
	}

	EvaluatorQualificationsPage moveSelectedEmployees(String listItems) {
		this.moveSelectedItems("Selected Employees", listItems)
		return this
	}


	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	EvaluatorQualificationsPage verifyAssignedEvaluatorQualificationsDisplay(String userName, String evaluationId, String evaluationTile, String assignedDate) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${userName};${evaluationId};${evaluationTile};${assignedDate}")
		return this
	}
	
	EvaluatorQualificationsPage verifyEvaluatorQualificationsDisplay(String userName, String evaluationId, String evaluationTile) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${userName};${evaluationId};${evaluationTile}")
		return this
	}

	//////////////////// Actions ////////////////////////////
	EvaluatorQualificationsPage deleteEvaluation(String text) {
		Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(text, 8)
		clickDelete()
		clickYes()
		return this
	}
	
	EvaluatorQualificationsPage assignEvaluatorQualificationToUser(String EvaluationType, List evaluationList) {
		this.selectOptionType(EvaluationType)
		this.clickFilter(3)
		evaluationList.each { this.moveAvailableEvalationOnEvaluator(5,it) }
		this.clickPreview()
		this.clickSave()
		return this
	}
	
	EvaluatorQualificationsPage moveAvailableEmployeeOnEvaluator(int index, String listItems) {
		listItems.split(";").each {
			BaseActions.setText(webSearchOnEvaluator("Available Employees", 3), it)
			Thread.sleep(1000)
			BaseActions.check(webCheckboxOnEvaluator("Available Employees", 2))
			BaseActions.click(webButtonArrowRight("Available Employees", 2))
		}
		return this
	}
	
	EvaluatorQualificationsPage moveAvailableEvalationOnEvaluator(int index, String listItems) {
		listItems.split(";").each {
				BaseActions.setText(webSearchOnEvaluator("Available Evaluations", 5), it)
				Thread.sleep(1000)
				BaseActions.check(webCheckboxOnEvaluator("Available Evaluations", 1))
				BaseActions.click(webButtonArrowRight("Available Evaluations", 1))
			}
		return this
	}
	List<String> getNoResultsMessageFromTable() {
		return Pages.As(OnDataTable).setTable("Name_Header", 1).getListRowsContentOnTableUser(1..1)
	}
	
	EvaluatorQualificationsPage deleteEvalationOnEvaluator(int index =8, String userId, String taskList, String taskCodeName, String evaluationId) {
		this.selectOptionTaskList(taskList)
		this.selectMultipleOptionTask(taskCodeName)
		this.moveAvailableEmployees(userId)
		this.clickSearch()
		String _getContent = getNoResultsMessageFromTable()
		if(_getContent != "[No results returned.]")
		{
		Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(evaluationId, index, true)
		this.clickDelete()
		this.clickYes()
		}
		return this
	}
}
