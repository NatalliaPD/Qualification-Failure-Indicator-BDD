package com.ewn

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement

import com.ewn.common.page.BasePage
import com.ewn.common.page.FileHelper
import com.ewn.common.page.Icon
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

public class MyRequirementsPage extends BasePage<MyRequirementsPage> {

	//////////////////// Button ////////////////////////////
	MyRequirementsPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	MyRequirementsPage clickLaunchExam(String language = "English") {
		waitUploading()
		if (language == "English") {
			BaseActions.click(webButton("Launch Exam in English", 1))
		} else {
			BaseActions.click(webButton("Lanzar el Examen en Español", 1))
		}
		return this
	}

	MyRequirementsPage clickContinueTraining() {
		BaseActions.click(webButton("Continue Training", 1))
	}

	MyRequirementsPage clickSendRequest() {
		BaseActions.click(webButton("Send Request", 1))
		return this
	}

	MyRequirementsPage clickExpandAll() {
		BaseActions.click(webLabel("Expand All"))
		return this
	}

	MyRequirementsPage clickCollapseAll() {
		BaseActions.click(webButton("Collapse All", 1))
		return this
	}

	MyRequirementsPage clickPreview(int index=1) {
		BaseActions.click(webButton("Preview", index))
		return this
	}

	MyRequirementsPage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	MyRequirementsPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	MyRequirementsPage clickLaunchTraining(String language = "English") {
		waitUploading()
		if (language == "English") {
			BaseActions.click(webButtonTag("Launch Training in English", 1))
		} else {
			BaseActions.click(webButtonTag("Lanzar el entrenamiento en Español", 1))
		}
		return this
	}

	MyRequirementsPage clickReturnToLaunchPage(String language = "English", int index=1) {
		waitUploading()
		if(language == "English") {
			BaseActions.click(webButtonTag("Return to Launch Page", index))
		}else {
			BaseActions.click(webButtonTag("Regreso a Página de Lanzamiento", index))
		}
		return this
	}

	MyRequirementsPage clickLaunchAssessment(String language = "English") {
		waitUploading()
		if (language == "English") {
			BaseActions.click(webButton("Launch Assessment", 1))
		} else {
			BaseActions.click(webButton("Lanzamiento de la Evaluación", 1))
		}

		return this
	}

	MyRequirementsPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}

	MyRequirementsPage clickOK(int index=1) {
		BaseActions.click(webButton("OK", index))
		return this
	}

	MyRequirementsPage clickCancelButton(int index = 1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	MyRequirementsPage clickNo(int index=1) {
		BaseActions.click(webButton("No", index))
		return this
	}

	MyRequirementsPage clickSubmitAnswers(String language = "English") {
		if(language == "English" ) {
			BaseActions.click(webButton("Submit Answers", 1))
		} else {
			BaseActions.click(webButton("Presentar Respuestas", 1))
		}
		return this
	}

	MyRequirementsPage clickMyRequirements(String language = "English") {
		if (language == "English") {
			BaseActions.click(webButtonTag("My Requirements", 1))
		} else {
			BaseActions.click(webButtonTag("Mis Requisitos", 1))
		}
		return this
	}

	MyRequirementsPage verifyMyRequirementsButtonOnLastSlide(String language = "English") {
		if (language == "English") {
			BaseActions.verifyElementPresent(webButtonTag("My Requirements", 1), GlobalVariable.ShortTime)
		} else {
			BaseActions.verifyElementPresent(webButtonTag("Mis Requisitos", 1), GlobalVariable.ShortTime)
		}
		return this
	}

	MyRequirementsPage verifySubmitAnswersButtonPresent() {
		BaseActions.verifyElementPresent(webButton("Submit Answers", 1), GlobalVariable.ShortTime)
		return this
	}

	MyRequirementsPage doubleClickNextSlide(String language = "English") {
		if (language == "English") {
			BaseActions.doubleClick(webNextSlide())
		} else {
			BaseActions.doubleClick(webNextSlide("Spanish"))
		}
		return this
	}

	MyRequirementsPage clickNextSlide(String language = "Spanish") {
		BaseActions.click(webNextSlide(language))
		return this
	}

	MyRequirementsPage verifyActionButtonAvailable(int index=1) {
		BaseActions.verifyElementClickable(webButton("Action", index))
		return this
	}
	
	MyRequirementsPage hoverOnLockIcon(String label) {
		BaseActions.scrollToElement(weblockedLineIcon(label), GlobalVariable.ShortTime)
		BaseActions.mouseOver(weblockedLineIcon(label))
		return this
	}

	MyRequirementsPage hoverOnNotLaunchableIcon(String label) {
		BaseActions.scrollToElement(webNotLaunchableIcon(label), GlobalVariable.ShortTime)
		BaseActions.mouseOver(webNotLaunchableIcon(label))
		return this
	}

	MyRequirementsPage clickHereToOpenInTaskView() {
		def driver = DriverFactory.getWebDriver()
		String javascript = "document.querySelector('.popover a').click();"
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver
		(WebElement) jsExecutor.executeScript(javascript)
		return this
	}

	//////////////////// Combobox ////////////////////////////
	MyRequirementsPage selectOptionRequirementsView(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Requirements View:", index), item, true)
		return this
	}

	MyRequirementsPage selectOptionRequirementsFor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Requirements For:", index), item, true)
		return this
	}

	MyRequirementsPage selectOptionTaskStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task Status:", index), item, true)
		return this
	}

	MyRequirementsPage selectOptionOrderBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order By:", index), item, true)
		return this
	}

	MyRequirementsPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	MyRequirementsPage selectOptionCatalog(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Catalog:", index), item, true)
		return this
	}

	MyRequirementsPage selectOptionSubscriptionStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Subscription Status:", index), item, true)
		return this
	}

	MyRequirementsPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	MyRequirementsPage selectMultipleOptionEvaluationStatus(String items) {
		this.selectMultipleOption("Evaluation Status:", items)
		return this
	}

	MyRequirementsPage verifyIfTaskListPresentInRequirementsFor(String expectedOption, Boolean isPresent = true) {
		if (isPresent)
			BaseActions.verifyOptionPresentByLabel(webCombobox("Requirements For:", 1), expectedOption, false, GlobalVariable.MediumTime)
		else
			BaseActions.verifyOptionNotPresentByLabel(webCombobox("Requirements For:", 1), expectedOption, false, GlobalVariable.MediumTime)
		return this
	}

	MyRequirementsPage verifySelectedItemInRequirementsView(String expectedOption) {
		String actualSelectedOption = BaseActions.getValueOfSelectedOption(webCombobox("Requirements View:",))
		BaseActions.verifyEqual(actualSelectedOption, expectedOption)
		return this
	}

	MyRequirementsPage selectOptionTaskCodeName(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task Code / Name:", index), item, true)
		return this
	}

	MyRequirementsPage clickExpandTask(String taskName) {
		Pages.As(OnDataTable).setTable("Results", 2).selectIconByTextOnTable(taskName,"Status")
		return this
	}

	MyRequirementsPage clickLaunchIcon(String evaluationTitle) {
		Pages.As(OnDataTable).setTable("Results", 3).selectIconByTextOnTable(evaluationTitle,"Launch")
		return this
	}

	MyRequirementsPage clickOnACheckboxOfDirectAssignments(String text, int index=8) {
		Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable(text, index, true)
		return this
	}

	MyRequirementsPage inputTaskNameCode(Object text) {
		BaseActions.setText(webTextbox("Task Name/Code:", 1), text.toString())
		return this
	}
	
	String verifyLockTooltipTextDisplayed(String message) {
		TestObject tooltipPopup = BaseActions.createTestObject("Lock tooltip", "css", ".popover")
		BaseActions.verifyEqual(BaseActions.getText(tooltipPopup), message)
		return this
	}
	
	String verifyLockTooltipTextDisplayedOnEvaluationView(String message) {
		def driver = DriverFactory.getWebDriver()
		String javascript = "return document.querySelector('.popover').innerText"
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver
		def text = (String) jsExecutor.executeScript(javascript)
		BaseActions.verifyEqual(text, message)
		return this
	}

	//////////////////// Radio ////////////////////////////
	MyRequirementsPage selectRadioListAnswers(String listAnswers) {
		listAnswers.split(";").each {
			if(it.contains("//")) {
				def _radioUnderLabel = it.split("//")
				this.selectRadioUnderLabel(_radioUnderLabel[0], _radioUnderLabel[1])
			} else this.selectRadio(it)
		}
		return this
	}

	//////////////////// Textbox ////////////////////////////
	MyRequirementsPage inputForecast(int index=1, Object text) {
		BaseActions.setText(webTextbox("Forecast:", index), text.toString())
		return this
	}

	MyRequirementsPage inputTaskCodeName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Task Code / Name:", index), text.toString())
		return this
	}

	MyRequirementsPage inputEvaluationTitleID(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation Title / ID:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	MyRequirementsPage verifyValueAtColumnOnResultsTable(int index=1, Object column, String value, Object status) {
		Pages.As(OnDataTable).setTable("Results", index).verifyValueAtColumnOnTable(column, value, status)
		return this
	}

	MyRequirementsPage verifyTaskViewResultsDisplayed(boolean shouldBeDisplayed=true) {
		List<String> _expectedListValues = ["", "Task Code", "Task Name", "Current Completion Date", "Valid Until", "Status", "Certificate"]
		if(shouldBeDisplayed) {
			Pages.As(OnDataTable).setTable("Results", 2).verifyListValuesTableHeader(_expectedListValues)
		} else {
			Pages.As(OnDataTable).setTable("Results", 2).verifyLabelExisting("No results returned.")
		}
		return this
	}

	MyRequirementsPage verifyAssignedTasks(Object expectedResult, Object range) {
		BaseActions.verifyEqual(this.getListRowsContentTasksOnResults(range).sort(), expectedResult)
		return this
	}
	
	List<String> getNoResultsMessageFromTable() {
		return Pages.As(OnDataTable).setTable("Results", 1).getListRowsContentOnTableUser(1..1)
	}

	MyRequirementsPage verifyEvaluationViewResultsDisplayed() {
		List<String> _expectedListValues = ["EWN ID", "Employee ID", "Type", "ID", "Title", "Est. Duration", "Completed", "Valid Until", "Training", "Due Date", "Training Required", "Status", "Launch", "", "", "", "", "", "", "", "", "", "", "", "", ""]
		Pages.As(OnDataTable).setTable("Results", 1).verifyListValuesTableHeader(_expectedListValues)
		return this
	}

	MyRequirementsPage launchTraining(String titleID) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(titleID, "Launch")
		return this
	}

	MyRequirementsPage verifyTaskViewResultsSortOrder(String columnName, String dataType, String expectedStatus="ascending") {
		Pages.As(OnDataTable).setTable("Results", 2).verifyColumnSortOrder(columnName, dataType, expectedStatus)
		return this
	}

	List<String> getListRowsContentEvaluationsOnResults(Object range) {
		return Pages.As(OnDataTable).setTable("Results", 1).getListRowsContentOnTable(range)
	}

	List<String> getListRowsContentTasksOnResults(int index=2 ,Object range) {
		return Pages.As(OnDataTable).setTable("Results", index).getListRowsContentOnTable(range)
	}

	List<String> getListRowsContentTasksAndEvaluations() {
		List<String> _returnTasksList = Pages.As(OnDataTable).setTable("Results", 2).getListRowsContentOnTable(2..7)
		clickExpandAll()
		List<String> _returnEvaluationsList = []
		_returnTasksList.each{
			String _taskName = it.split(";")[1]
			_returnEvaluationsList.add(Pages.As(OnDataTable).setTable("Results", _taskName).getListRowsContentOnTable(3..13))
		}
		return _returnTasksList.add(_returnEvaluationsList)
	}

	//////////////////// Actions ////////////////////////////
	MyRequirementsPage searchTaskView(String requirementsFor, String status, String orderBy, String forecast=null, String codeName) {
		selectOptionRequirementsFor(requirementsFor)
		selectOptionRequirementsView("Task View")
		selectOptionTaskStatus(status)
		selectOptionOrderBy(orderBy)
		if(forecast != null) inputForecast(forecast)
		inputTaskCodeName(codeName)
		clickSearch()
		return this
	}

	MyRequirementsPage searchEvaluationView(String requirementsFor, String author, String catalog=null, String status, String subscriptionStatus, String type, String forecast=null, String titleID) {
		selectOptionRequirementsFor(requirementsFor)
		selectOptionRequirementsView("Evaluation View")
		selectOptionEvaluationAuthor(author)
		if(catalog != null) selectOptionCatalog(catalog)
		selectMultipleOptionEvaluationStatus(status)
		selectOptionSubscriptionStatus(subscriptionStatus)
		selectOptionEvaluationType(type)
		inputEvaluationTitleID(titleID)
		clickSearch()
		return this
	}

	MyRequirementsPage verifyDownloadedPDFFile(String downloadedFilePath, String expectedContent) {
		FileHelper helper = new FileHelper()
		helper.verifyFileContent(downloadedFilePath, expectedContent)
		return this
	}

	List<String> getListTaskOnTable(Object range) {
		return Pages.As(OnDataTable).setTable("Name_Header", 1).getListRowsContentOnTable(range)
	}

	//////////////////// Table ////////////////////////////
	MyRequirementsPage verifyTaskAssignmentDisplayed(int index=1, String taskCode, String taskName, String iconStatus) {
		Pages.As(OnDataTable).setTable("Results", index).verifyTableRow("${taskCode};${taskName};${iconStatus}")
		return this
	}

	MyRequirementsPage verifyEvaluationAssignmentDisplayed(int index=1, String assignedType, String assignedId, String assignedTitle, String iconStatus) {
		Pages.As(OnDataTable).setTable("Results", index).verifyTableRow("${assignedType};${assignedId};${assignedTitle};${iconStatus}")
		return this
	}

	MyRequirementsPage verifyTrainingCompleted(String taskListName, String evaluationTitle, String completedDate, Object trainingDate, String completedEvaluationStatus) {
		selectTab("View Requirements")
		selectOptionRequirementsFor(taskListName)
		clickSearch()
		clickExpandAll()
		verifyEvaluationAndStatus(evaluationTitle,completedDate,trainingDate,completedEvaluationStatus)
		return this
	}

	MyRequirementsPage verifyEvaluationAndStatus(String evaluationTitle, String completedDate, Object trainingDate, String completedEvaluationStatus, int index =3) {
		Pages.As(OnDataTable).setTable("Results", index).verifyTableRow("${evaluationTitle};${completedDate};${trainingDate};${completedEvaluationStatus}")
		return this
	}

	MyRequirementsPage verifyEvaluationIsLocked(String evaluationTitle, String NotLaunchableIconClassValue, int index=1 ) {
		Pages.As(OnDataTable).setTable("Results", index).verifyLockedIconAttributeByText(evaluationTitle, "Launch", "class", NotLaunchableIconClassValue)
		return this
	}

	MyRequirementsPage verifyEvaluationIsLaunchable(String evaluationTitle, int index=1) {
		String _LaunchableIconClassValue = Pages.As(Icon).launchable
		Pages.As(OnDataTable).setTable("Results", index).verifyIconAttributeByText(evaluationTitle, "Launch", "class", _LaunchableIconClassValue)
		return this
	}
	
	MyRequirementsPage verifyEvaluationIsLaunchableForMultipleCondition(int conditionIndex=1, String evaluationTitle, int index=1) {
		String _LaunchableIconClassValue = Pages.As(Icon).launchable
		Pages.As(OnDataTable).setTable("Results", index).verifyIconAttributeByTextForMultipleBody(conditionIndex, evaluationTitle, "Launch", "class", _LaunchableIconClassValue)
		return this
	}

	MyRequirementsPage verifyEvaluationIsLockedForMultipleCondition(int conditionIndex=1, String evaluationTitle, int index=1) {
		String _LockedIconClassValue = Pages.As(Icon).lockedLine
		Pages.As(OnDataTable).setTable("Results", index).verifyIconAttributeByTextForMultipleBody(conditionIndex, evaluationTitle, "Launch", "class", _LockedIconClassValue)
		return this
	}
	
	MyRequirementsPage verifyEvaluationIsNotLaunchable(String evaluationTitle, int index=1) {
		String _NotLaunchableIconClassValue = Pages.As(Icon).notLaunchable
		Pages.As(OnDataTable).setTable("Results", index).verifyIconAttributeByText(evaluationTitle, "Launch", "class", _NotLaunchableIconClassValue)
		return this
	}

	///////////////Assessment/////////////////////////

	MyRequirementsPage selectRadioAnwserOption(String answerOption) {
		BaseActions.click(webRadio(answerOption))
		return this
	}

	MyRequirementsPage selectCheckboxAnwserOption(String answerOption) {
		BaseActions.click(webCheckbox(answerOption))
		return this
	}

	MyRequirementsPage verifyQuestionAndAnswerRadioOptionsDisplayed(String question, List answerOptions) {
		this.verifyQuestionDisplayed(question)
		answerOptions.each {this.verifyRadioAnswerOption(it)}

		return this
	}

	MyRequirementsPage verifyQuestionAndAnswerCheckboxOptionsDisplayed(String question, List answerOptions) {
		this.verifyQuestionDisplayed(question)
		answerOptions.each{ this.verifyCheckBoxAnswerOption(it) }
		return this
	}

	MyRequirementsPage verifyTextOnSlide(String expectedText) {
		TestObject bodySlide = new TestObject("body slide")
		bodySlide.addProperty("css", ConditionType.EQUALS, ".training-player-slide-text-container  p")

		BaseActions.verifyElementText(bodySlide, expectedText)
		return this
	}

	MyRequirementsPage verifyAssessmentQuestionsAndAnswersPresent() {
		TestObject assessmentObject = new TestObject("Assessment Questions And Answers")
		assessmentObject.addProperty("css", ConditionType.EQUALS, ".assessment-question-list")
		BaseActions.verifyElementPresent(assessmentObject, GlobalVariable.ShortTime)
		return this
	}

	MyRequirementsPage verifySubmitAnswerButtonPresent(String language = "English") {
		TestObject submitButtonObject = new TestObject("Submit Answer Button")
		if (language == "English") {
			BaseActions.verifyElementPresent(webButton("Submit Answers", 1), GlobalVariable.ShortTime)
		} else {
			BaseActions.verifyElementPresent(webButton("Presentar Respuestas", 1), GlobalVariable.ShortTime)
		}
		return this
	}

	MyRequirementsPage verifyAudioIsPlaying() {
		TestObject playButton = new TestObject("Play Button")
		playButton.addProperty("css", ConditionType.EQUALS, "#mep_1 .mejs-playpause-button button")

		// When the  audio is playing, the button has title Pause and vice versa "Play" when pausing
		// in Firefox, audio would not automatically play in slide preview
		if (DriverFactory.getExecutedBrowser().getName() == "FIREFOX_DRIVER") {
			BaseActions.verifyElementAttributeContains(playButton, "title", "Play")
		} else {
			BaseActions.verifyElementAttributeContains(playButton, "title", "Pause")
		}
		
		return this
	}

	MyRequirementsPage verifyExamResultInformation(String expectedStatus, String expectedScore, String date) {
		BaseActions.verifyElementTextContent(webLabelValue("Status:"), expectedStatus)
		BaseActions.verifyElementTextContent(webLabelValue("Score:"), expectedScore)
		BaseActions.verifyElementTextContains(webLabelValue("Date:"), date)
		if (expectedStatus == "Passed")
			BaseActions.verifyElementPresent(webLabel("You answered all questions correctly"), GlobalVariable.ShortTime)
		else
			BaseActions.verifyElementNotPresent(weblabel("You answered all questions correctly"), GlobalVariable.ShortTime)
		return this
	}
	
	MyRequirementsPage verifyLaunchingEvaluationPresent(String evaluationTitle) {
		BaseActions.verifyElementPresent(webLabel(evaluationTitle), GlobalVariable.ShortTime)
		return this
	}
	

}
