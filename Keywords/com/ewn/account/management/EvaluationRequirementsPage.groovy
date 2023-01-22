package com.ewn.account.management

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

public class EvaluationRequirementsPage extends BasePage<EvaluationRequirementsPage> {
	//////////////////// Button ////////////////////////////
	EvaluationRequirementsPage clickEdit(int index=1) {
		BaseActions.click(webButton("Edit", index))
		return this
	}

	EvaluationRequirementsPage clickAdd(int index=1) {
		BaseActions.click(webButton("Add", index))
		return this
	}

	EvaluationRequirementsPage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	EvaluationRequirementsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	EvaluationRequirementsPage clickPreview(int index=1) {
		BaseActions.click(webButton("Preview", index))
		return this
	}

	EvaluationRequirementsPage clickDelete(int index=1) {
		BaseActions.click(webButton("Delete", index))
		return this
	}

	EvaluationRequirementsPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	EvaluationRequirementsPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	EvaluationRequirementsPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	EvaluationRequirementsPage selectOptionOrderBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order By:", index), item, true)
		return this
	}

	EvaluationRequirementsPage selectOptionInternalSettings(int index=1,String item) {
		BaseActions.selectOptionByLabel(webCombobox("Proctoring Options:", index), item, true)
		return this
	}

	EvaluationRequirementsPage selectOptionExternalSettings(int index=1,String item) {
		BaseActions.selectOptionByLabel(webCombobox("Proctoring Options:", index), item, true)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	EvaluationRequirementsPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	EvaluationRequirementsPage moveSelectedEvaluations(String listItems) {
		this.moveSelectedItems("Selected Evaluations", listItems)
		return this
	}

	EvaluationRequirementsPage moveAvailableEmployees(String listItems) {
		this.moveAvailableItems("Available Employees", listItems)
		return this
	}

	EvaluationRequirementsPage moveSelectedEmployees(String listItems) {
		this.moveSelectedItems("Selected Employees", listItems)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	EvaluationRequirementsPage inputEvaluationTitleID(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation Title / ID:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	EvaluationRequirementsPage verifyEvaluationRequirementDisplayed(String id, String title, String internalProctoring, String externalProctoring) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${id};${title};${internalProctoring};${externalProctoring}")
		return this
	}

	//////////////////// Actions ////////////////////////////
	EvaluationRequirementsPage deleteEvaluation(String text) {
		Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(text, 8)
		clickDelete()
		clickYes()
		return this
	}
}
