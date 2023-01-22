package com.ewn.printsubmit.evaluations

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.kms.katalon.core.testobject.TestObject
import com.ewn.fw.lib.BaseActions
import internal.GlobalVariable
import com.ewn.fw.lib.Pages    

public class EditEvaluationsPage extends BasePage<EditEvaluationsPage> {
	//////////////////// Button ////////////////////////////
	EditEvaluationsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	EditEvaluationsPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	EditEvaluationsPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}
	
	EditEvaluationsPage clickCalendarButton(String label) {
		BaseActions.click(calendarButton(label))
		return this
	}

	EditEvaluationsPage clickCalendarSubButton(String label, String buttonName) {
		BaseActions.click(calendarSubButton(label, buttonName))
		return this
	}
	//////////////////// Combobox ////////////////////////////
	EditEvaluationsPage selectOptionSelectCompany(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Select Company:", index), item, true)
		return this
	}

	EditEvaluationsPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	EditEvaluationsPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	EditEvaluationsPage selectOptionOrderBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order By:", index), item, true)
		return this
	}

	EditEvaluationsPage selectOptionTrainingStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Training Status:", index), item, true)
		return this
	}

	EditEvaluationsPage selectOptionEvaluator(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluator:", index), item, false)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	EditEvaluationsPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	EditEvaluationsPage moveSelectedEvaluations(String listItems) {
		this.moveSelectedItems("Selected Evaluations", listItems)
		return this
	}

	EditEvaluationsPage moveAvailableEmployees(String listItems) {
		this.moveAvailableItems("Available Employees", listItems)
		return this
	}

	EditEvaluationsPage moveSelectedEmployees(String listItems) {
		this.moveSelectedItems("Selected Employees", listItems)
		return this
	}

	List<String> getAvailableEvaluations(String searchBy=null) {
		return this.getListGroupItems("Available Evaluations", searchBy)
	}

	//////////////////// Textbox ////////////////////////////
	EditEvaluationsPage inputEvaluationDate(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation Date:", index), text.toString())
		return this
	}

	EditEvaluationsPage inputLocation(int index=1, Object text) {
		BaseActions.setText(webTextbox("Location:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Edit Evaluations", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	EditEvaluationsPage inputScore(Object score) {
		Pages.As(OnDataTable).setTable("Edit Evaluations", 1).setTextOnTable(1, "Score", score)
		return this
	}

	EditEvaluationsPage inputDate(Object evaluationName, Object date) {
		Pages.As(OnDataTable).setTable("Edit Evaluations", 1).setTextOnTable(evaluationName, "Date", date)
		return this
	}

	EditEvaluationsPage clickEditIcon(Object evaluationName) {
		BaseActions.waitForElementVisible(Pages.As(OnDataTable).setTable("Edit Evaluations", 1).webTableIcon(evaluationName, "Edit"), GlobalVariable.ShortTime)
		Pages.As(OnDataTable).setTable("Edit Evaluations", 1).clickOnIcon(evaluationName, "Edit")
		return this
	}

	EditEvaluationsPage verifySubmitedEvaluationDisplayed(String employee, String status, String score) {
		Pages.As(OnDataTable).setTable("Submit Evaluations", 1).verifyTableRow("${employee};${status};${score}")
		return this
	}

	/////////////////Confirm Dialog////////////
	EditEvaluationsPage inputReasonChange(String reason) {
		BaseActions.waitForElementVisible(webCommentsAnswer("Please provide a reason for the change."), GlobalVariable.ShortTime)
		BaseActions.setText(webCommentsAnswer("Please provide a reason for the change."), reason.toString())
		return this
	}

	////////////////Action/////////////////////
	EditEvaluationsPage EditSubmittedEvaluationDate(Object evaluationName, String date) {
		this.clickEditIcon(evaluationName)
		this.inputDate(evaluationName,date)
		this.clickEditIcon(evaluationName)
		this.inputReasonChange("Change to setup date for expiring report")
		this.clickYes()
		return this
	}

}
