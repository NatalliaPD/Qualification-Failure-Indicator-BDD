package com.ewn.printsubmit.evaluations

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

public class SubmitEvaluationsPage extends BasePage<SubmitEvaluationsPage> {
	//////////////////// Button ////////////////////////////
	SubmitEvaluationsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	SubmitEvaluationsPage clickPreview(int index=1) {
		BaseActions.click(webButton("Preview", index))
		return this
	}

	SubmitEvaluationsPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	SubmitEvaluationsPage clickClose(int index=1) {
		BaseActions.click(webButton("Close", index))
		return this
	}

	SubmitEvaluationsPage clickSave(int index=1) {
		BaseActions.clickJS(webButton("Save", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	SubmitEvaluationsPage selectOptionSelectCompany(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Select Company:", index), item, true)
		return this
	}

	SubmitEvaluationsPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	SubmitEvaluationsPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	SubmitEvaluationsPage selectOptionOrderBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order By:", index), item, true)
		return this
	}

	SubmitEvaluationsPage selectOptionTrainingStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Training Status:", index), item, true)
		return this
	}

	SubmitEvaluationsPage selectOptionEvaluator(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluator:", index), item, false)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	SubmitEvaluationsPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	SubmitEvaluationsPage moveSelectedEvaluations(String listItems) {
		this.moveSelectedItems("Selected Evaluations", listItems)
		return this
	}

	SubmitEvaluationsPage moveAvailableEmployees(String listItems) {
		this.moveAvailableItems("Available Employees", listItems)
		return this
	}

	SubmitEvaluationsPage moveSelectedEmployees(String listItems) {
		this.moveSelectedItems("Selected Employees", listItems)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	SubmitEvaluationsPage inputEvaluationDate(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation Date:", index), text.toString())
		return this
	}

	SubmitEvaluationsPage inputLocation(int index=1, Object text) {
		BaseActions.setText(webTextbox("Location:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Submit Evaluations", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}
	
	SubmitEvaluationsPage inputScore(Object score, int index=1 ,int indexScore=1) {
		Pages.As(OnDataTable).setTable("Submit Evaluations", index).setTextOnTable(indexScore, "Score", score)
		return this
	}
	
	SubmitEvaluationsPage verifySubmitedEvaluationDisplayed(String employee="", String status="", String score="", int index) {
		Pages.As(OnDataTable).setTable("Submit Evaluations", index).verifyTableRow("${employee};${status};${score}")
		return this
	}
	
	SubmitEvaluationsPage verifyUsersDisplayed(String employee, int index) {
		Pages.As(OnDataTable).setTable("Submit Evaluations", index).verifyTableRow("${employee}")
		return this
	}
	
	SubmitEvaluationsPage verifyDateDisplayed(String date, int index) {
		Pages.As(OnDataTable).setTable("Submit Evaluations", index).verifyTableRow("${date}")
		return this
	}
	//////////////////// Action ////////////////////////////
	SubmitEvaluationsPage moveEvaluationWithoutSaving(String evaluationType, String orderBy, String evaluationTitle) {
		selectOptionEvaluationType(evaluationType)
		selectOptionOrderBy(orderBy)
		clickFilter(2)
		moveAvailableEvaluations(evaluationTitle)
		return this
	}
}
