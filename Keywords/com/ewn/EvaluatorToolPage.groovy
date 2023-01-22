package com.ewn

import com.ewn.common.page.BasePage
import com.ewn.common.page.Helper
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

import internal.GlobalVariable

public class EvaluatorToolPage extends BasePage<EvaluatorToolPage> {

	//////////////////// Button ////////////////////////////
	EvaluatorToolPage clickSubmitAnswers() {
		BaseActions.click(webButton("Submit Answers", 1))
		return this
	}

	EvaluatorToolPage clickContinueToEvaluation() {
		BaseActions.click(webButton("Continue to Evaluation", 1))
		return this
	}

	EvaluatorToolPage clickChange() {
		BaseActions.click(webButton("Change", 1))
		return this
	}
	
	EvaluatorToolPage clickReturnToLaunchPage() {
		BaseActions.click(webButtonTag("Return to Launch Page", 1))
		return this
	}
	//////////////////// Combobox ////////////////////////////
	EvaluatorToolPage selectOptionCompany(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Company:", index), item, true)
		return this
	}

	//////////////////// Label ////////////////////////////
	EvaluatorToolPage evaluatorCertificationStatement(String statement="Yes") {
		if(statement=="Yes") BaseActions.click(webLabel("As Evaluator I certify that Evaluatee has demonstrated the proper and correct methods in a timely manner and is qualified by skill and ability to perform this task in its entirety."))
		else BaseActions.click(webLabel("As Evaluator I have determined that the Evaluatee requires additional training and/or requires improvement in order to qualify for this sub-task."))
		return this
	}

	//////////////////// Textbox ////////////////////////////
	EvaluatorToolPage inputEvaluatorComments(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluator Comments:", index), text.toString())
		return this
	}

	EvaluatorToolPage inputLocation(int index=1, Object text) {
		BaseActions.setText(webTextbox("Location:", index), text.toString())
		return this
	}

	EvaluatorToolPage setTextAssociateEmployeeName(int index=1, String text, String item) {
		//need to wait to Evaluator Qualifications is effective
		Thread.sleep(4000)
		this.setTextAssociate("Employee Name:", index, text, item)
		pleaseWait(GlobalVariable.ShortTime)
		return this
	}

	EvaluatorToolPage answerQuestion(Object question, int indexButton, String comments=null) {
		BaseActions.click(webButtonAnswer(question, indexButton))
		if (comments != null) BaseActions.setText(webCommentsAnswer(question), comments)
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	EvaluatorToolPage launchEvaluation(String titleID) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(titleID, "Launch")
		return this
	}

	EvaluatorToolPage verifyEvaluationLaunchable(String evaluationId, String evaluationName, String launchableIcon) {
		Pages.As(OnDataTable).setTable("Results").verifyTableRow("${evaluationId};${evaluationName};${launchableIcon}")
		return this
	}

	EvaluatorToolPage verifyDetailEvaluator(String evaluationId, String evaluationName, String completedDate, String expireDate, String statusIcon) {
		Pages.As(OnDataTable).setTable("Results").verifyTableRow("${evaluationId};${evaluationName};${completedDate};${expireDate};${statusIcon}")
		return this
	}

	EvaluatorToolPage verifyPreRequireToolTipByEvaluation(String evaluationId, String expectedToolTipContent) {
		Pages.As(OnDataTable).setTable("Results", 1).hoverIconByTextOnTable(evaluationId, "Launch")
		BaseActions.verifyEqual(getLaunchIconTooltipText(), expectedToolTipContent)
		return this
	}

	EvaluatorToolPage verifyPreRequireToolTipIsNotDisplay(String evaluationId) {
		Pages.As(OnDataTable).setTable("Results", 1).hoverIconByTextOnTable(evaluationId, "Launch")
		TestObject alertModal = modalPreRequireAlert()
		BaseActions.verifyElementNotPresent(alertModal, GlobalVariable.ShortTime)
		return this
	}

	EvaluatorToolPage verifyPrereqNotMetDisplay(String evaluationId) {
		String reRequireMsg = Pages.As(OnDataTable).setTable("Results", 1).getListValuesTableRow(evaluationId, 2..2)
		BaseActions.verifyEqual(reRequireMsg.contains("Prereq Not Met"), true)
		return this
	}

	String getLaunchIconTooltipText() {
		TestObject tooltipPopup = BaseActions.createTestObject("Launch tooltip", "css", ".popover")
		return BaseActions.getText(tooltipPopup)
	}

	EvaluatorToolPage verifyPrereqNotMetNotDisplay(String evaluationId) {
		String reRequireMsg = Pages.As(OnDataTable).setTable("Results", 1).getListValuesTableRow(evaluationId, 2..2)
		BaseActions.verifyEqual(reRequireMsg.contains("Prereq Not Met"), false)
		return this
	}
	
	EvaluatorToolPage verifyExpireDate(String evaluationId, int interval, int grace) {
		OnDataTable newTable = new OnDataTable()
		String completedDate = newTable.setTable("Results", 1).getCellAttribute(evaluationId, "Completed", "textContent")
		def expectedDate = Pages.As(Helper).plusDateTime(completedDate, "MM/dd/yyyy", "month", interval)
		expectedDate =  Pages.As(Helper).plusDateTime(expectedDate, "MM/dd/yyyy", "day", grace)
		String expectedExpireDate = completedDate
		String actualDate = newTable.setTable("Results", 1).getCellAttribute(evaluationId, "Expires", "textContent")
		BaseActions.verifyEqual(actualDate, expectedDate)
		return this
	}
	
	//////////////////// Modal Label //////////////////////
	TestObject modalPreRequireAlert() {
		TestObject modalPreRequire = new TestObject("Modal PreRequire")
		modalPreRequire.addProperty("css", ConditionType.EQUALS, ".popover")
		return modalPreRequire
	}

	String getModalPreRequireAlertText() {
		return BaseActions.findWebElement(this.modalPreRequireAlert()).getText()
	}

	EvaluatorToolPage verifyModalPreRequireAlertText(String expectedText) {
		BaseActions.verifyEqual(Pages.As(EvaluatorToolPage).getModalPreRequireAlertText(), expectedText)
		return this
	}
	
	TestObject modalPreRequireNotCompleted() {
		TestObject modalPreRequirNotComplete = new TestObject("Modal PreRequire Not Completed")
		modalPreRequirNotComplete.addProperty("css", ConditionType.EQUALS, ".alert-warning")
		return modalPreRequirNotComplete
	}

	String getmodalPreRequireNotCompletedText() {
		return BaseActions.findWebElement(this.modalPreRequireNotCompleted()).getText()
	}

	EvaluatorToolPage verifymodalPreRequireNotCompletedText(String expectedText) {
		BaseActions.verifyEqual(Pages.As(EvaluatorToolPage).getmodalPreRequireNotCompletedText(), expectedText)
		return this
	}

	//////Actions///////////
	EvaluatorToolPage verifyEvaluationNotPresent(String column, String expectedText) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyValueAtColumnOnTable(column, expectedText, false)
		return this
	}

	EvaluatorToolPage verifyEvaluationPresent(String column, String expectedText) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyValueAtColumnOnTable(column, expectedText, true)
		return this
	}
	
	//////////////////// Action //////////////////////
	EvaluatorToolPage launchAndCompleteSimplePE(String _evaluationID) {
		OnDataTable newTable = new OnDataTable()
		newTable.setTable("Results", 1).selectIconByTextOnTable(_evaluationID, "Launch")
		signature(1, "C")
		signature(2, "C")
		clickSubmitAnswers()
		clickReturnToLaunchPage()
		pleaseWait(GlobalVariable.ShortTime)
		return this
	}

}
