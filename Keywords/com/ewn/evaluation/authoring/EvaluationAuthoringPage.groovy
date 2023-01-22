package com.ewn.evaluation.authoring

import org.openqa.selenium.Keys

import com.ewn.common.page.BasePage
import com.ewn.common.page.FileHelper
import com.ewn.common.page.LoginPage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

import internal.GlobalVariable

public class EvaluationAuthoringPage extends BasePage<EvaluationAuthoringPage> {

	//////////////////// Button ////////////////////////////
	EvaluationAuthoringPage clickYes() {
		BaseActions.click(webButton("Yes"))
		return this
	}

	EvaluationAuthoringPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	EvaluationAuthoringPage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	EvaluationAuthoringPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	EvaluationAuthoringPage clickDelete(int index=1) {
		BaseActions.click(webButton("Delete", index))
		return this
	}

	EvaluationAuthoringPage clickEdit(int index=1) {
		BaseActions.click(webButton("Edit", index))
		return this
	}

	EvaluationAuthoringPage clickAddLearningObjective(int index=1) {
		BaseActions.click(webButton("Add Learning Objective", index))
		return this
	}

	EvaluationAuthoringPage clickAddTranslation(int index=1) {
		BaseActions.click(webButton("Add Translation", index))
		return this
	}

	EvaluationAuthoringPage clickUpload(int index=1) {
		BaseActions.click(webButton("Upload", index))
		return this
	}

	EvaluationAuthoringPage clickActive(int index=1) {
		BaseActions.click(webButton("Activate", index))
		return this
	}

	EvaluationAuthoringPage clickInactive(int index=1) {
		BaseActions.click(webButton("Inactivate", index))
		return this
	}

	EvaluationAuthoringPage clickAdd(int index=1) {
		BaseActions.click(webButton("Add", index))
		return this
	}

	EvaluationAuthoringPage clickGlossaryTermsOption(String label) {
		BaseActions.click(webButtonOption(label))
		return this
	}

	EvaluationAuthoringPage clickDeprecate(int index=1) {
		BaseActions.click(webButton("Deprecate", index))
		return this
	}

	EvaluationAuthoringPage clickConfirm(int index=1) {
		BaseActions.click(webButton("Confirm", index))
		return this
	}

	EvaluationAuthoringPage clickConvert(int index=1) {
		BaseActions.click(webButton("Convert", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	EvaluationAuthoringPage selectOptionStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Status:", index), item, true)
		return this
	}

	EvaluationAuthoringPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	EvaluationAuthoringPage selectOptionCategoryType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Category Type:", index), item, true)
		return this
	}

	EvaluationAuthoringPage selectOptionCategory(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Category:", index), item, true)
		return this
	}

	EvaluationAuthoringPage selectOptionDeprecation(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Deprecation:", index), item, true)
		return this
	}

	EvaluationAuthoringPage selectOptionContentLanguage(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Content Language:", index), item, true)
		return this
	}

	EvaluationAuthoringPage selectMultipleOptionSkillFamilies(String items) {
		this.selectMultipleOption("Skill Families:", items)
		return this
	}

	EvaluationAuthoringPage selectInternalSettingsProctoringOptions(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Proctoring Options:", index), item, true)
		return this
	}

	//////////////////// Icon ////////////////////////////
	EvaluationAuthoringPage clickIconPlayPause(String label) {
		BaseActions.click(webIconPlayPause(label))
		return this
	}

	EvaluationAuthoringPage selectOptionType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Type:", index), item, true)
		return this
	}

	EvaluationAuthoringPage clickIconBookGlossary() {
		BaseActions.click(webBookIcon("ewnglossary_icon"))
		return this
	}

	//////////////////// Image ////////////////////////////
	EvaluationAuthoringPage verifyImage(String label, int index=1, String expectedImageName) {
		BaseActions.verifyImageWithinElement(webImage(label, index), expectedImageName)
		return this
	}
	
	EvaluationAuthoringPage verifyImageVisible(String label, int index=1) {
		BaseActions.verifyElementVisible(webImage(label, index))
		return this
	}
	
	EvaluationAuthoringPage verifyImageFilePresent(String label, int index=1, String attribute="src", String expectedContainText=".png") {
		BaseActions.verifyElementAttributeContains(webImage(label, index), attribute, expectedContainText)
		return this
	}

	//////////////////// Label ////////////////////////////
	EvaluationAuthoringPage verifyLabelStatus(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Status:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelSlideTitle(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Slide Title:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelText(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Text:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelImage(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Image:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelAudio(String label) {
		verifyLabelExisting(label)
		return this
	}

	EvaluationAuthoringPage verifyLabelVideo(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Video:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelTrainingTitle(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Training Title:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelAutoAdvanceSlides(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Auto Advance Slides:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelAllowUsersToDownloadTrainingPDF(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Allow Users To Download Training PDF:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelType(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Type:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelName(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Name:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelDescription(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Description:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelActive(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Active:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelEvaluationType(int index=1, String expectedText) {
		BaseActions.verifyElementText(webLabelValue("Evaluation Type:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelEvaluationStatus(int index=1, String expectedText) {
		BaseActions.verifyElementText(webLabelValue("Status:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelTitle(int index=1, String expectedText) {
		BaseActions.verifyElementText(webLabelValue("Title:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelQuestionText(int index=1, String expectedText) {
		BaseActions.verifyElementText(webLabelValue("Question Text:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelGlossaryTerms(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Glossary Terms:", index), expectedText)
		return this
	}

	String getLabelEvaluationId(int index=1) {
		return BaseActions.getText(webLabelValue("Evaluation Id:", index))
	}

	String getLabelEvaluationTitle(int index=1) {
		return BaseActions.getText(webLabelValue("Title:", index))
	}

	EvaluationAuthoringPage verifyLabelVersion(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Version:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelUseEvaluationSpecificScore(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Use Evaluation Specific Score:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelMinimumScore(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Minimum Score:", index), expectedText)
		return this
	}

	EvaluationAuthoringPage verifyLabelInternalSettingsProctoringOptions(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Proctoring Options:", index), expectedText)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	EvaluationAuthoringPage inputEvaluationTitleID(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation Title / ID:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputSlideTitle(int index=1, Object text) {
		BaseActions.setText(webTextbox("Slide Title:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputText(Object text) {
		if(DriverFactory.getExecutedBrowser().getName() == "FIREFOX_DRIVER") {
			BaseActions.click(webTextbox("Slide Title:"))
			BaseActions.pressKey(Keys.chord(Keys.TAB))
			BaseActions.pressKey(text.toString())
		} else setTextIframe("Text:", text.toString())
		return this
	}

	EvaluationAuthoringPage inputTitle(int index=1, Object text) {
		BaseActions.setText(webTextbox("Title:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputDescription(int index=1, Object text) {
		BaseActions.setText(webTextbox("Description:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputQuizTitle(int index=1, Object text) {
		BaseActions.setText(webTextbox("Quiz Title:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputNumberOfQuestions(int index=1, Object text) {
		BaseActions.setText(webTextbox("Number of Questions to Include:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputQuestionText(int index=1, Object text) {
		BaseActions.setText(webTextbox("Question Text:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputPointValue(int index=1, Object text) {
		BaseActions.setText(webTextbox("Point Value:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputAnswerFeedback(int index=1, Object text) {
		BaseActions.setText(webTextbox("Answer Feedback:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Name:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputGlossaryTerms(int index=1, Object text) {
		BaseActions.setText(webTextbox("Term:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputDeprecationDate(int index=1, Object text) {
		BaseActions.setText(webTextbox("Deprecation Date:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputDeprecationReason(int index=1, Object text) {
		BaseActions.setText(webTextbox("Reason:", index), text.toString())
		return this
	}

	EvaluationAuthoringPage inputMinimumScore(int index=1, Object text) {
		BaseActions.setText(webTextbox("Minimum Score:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name, int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	EvaluationAuthoringPage openTrainingContent(String filterBy) {
		Pages.As(OnDataTable).setTable("Evaluation Details", 1).selectIconByTextOnTable(filterBy, "Details")
		return this
	}

	EvaluationAuthoringPage verifyIconDisplayed(String type, String icon) {
		Pages.As(OnDataTable).setTable("Evaluation Details", 1).verifyTableRow("${type};${icon}")
		return this
	}

	EvaluationAuthoringPage createVersion(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "Add Version")
		clickYes()
		return this
	}

	//////////////////// Upload File ////////////////////////////
	EvaluationAuthoringPage uploadFileAudio(int index=1, String audioName) {
		uploadFile("Audio:", index, audioName)
		return this
	}

	EvaluationAuthoringPage uploadFileVideo(int index=1, String videoName) {
		BaseActions.click(webRadio("Video"))
		uploadFile("Video:", index, videoName)
		return this
	}

	EvaluationAuthoringPage uploadFileImage(int index=1, String imageName) {
		uploadFile("Image:", index, imageName)
		return this
	}

	EvaluationAuthoringPage uploadFilePowerPoint(int index=1, String powerPointName) {
		uploadFile("Import PowerPoint presentation (ppt, pptx file)", index, powerPointName)
		return this
	}

	EvaluationAuthoringPage uploadFile(int index=1, String fileName) {
		uploadFile("File:", index, fileName)
		return this
	}

	//////////////////// Slide ////////////////////////////
	EvaluationAuthoringPage selectSlide(Object slide) {
		BaseActions.click(webSlide(slide))
		return this
	}

	//////////////////// Browser ////////////////////////////
	EvaluationAuthoringPage switchToNextWindow() {
		BaseActions.switchToWindowIndex(1)
		this.pleaseWait()
		return this
	}

	EvaluationAuthoringPage switchToPreviousWindow() {
		BaseActions.switchToWindowIndex(0)
		this.pleaseWait()
		return this
	}

	//////////////////// Evaluation Authoring Main ////////////////////////////
	EvaluationAuthoringPage selectEvaluationByTextOnTable(int index=1, String text) {
		Pages.As(OnDataTable).setTable("Evaluation Authoring", index).clickOnCellValueOnTable(text)
		return this
	}

	EvaluationAuthoringPage openEvaluationSettings(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "Details")
		return this
	}

	EvaluationAuthoringPage addNewEvaluation(String evaluationType, String title, String description) {
		selectButtonOption("Add", evaluationType)
		inputTitle(title)
		inputDescription(description)
		clickSave()
		return this
	}

	EvaluationAuthoringPage addNewTrainingWithoutAssessment(String trainingType) {
		selectButtonOption("Add", 2, trainingType)
		selectCheckbox("Auto Advance Slides:", 1, true)
		selectCheckbox("Allow Users To Download Training PDF:", 1, true)
		clickSave()
		return this
	}

	EvaluationAuthoringPage deleteTrainingTypeOnEvaluationAuthoring(String status="Draft", String evaluationTitleID) {
		selectTab("Search")
		selectOptionEvaluationType("All")
		selectOptionStatus(status)
		inputEvaluationTitleID(evaluationTitleID)
		clickSearch()
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(evaluationTitleID, "Delete")
		clickYes()
		return this
	}
	/******************************************************
	 * Add new evaluation from evaluation existed
	 * Use Scroll because because the button is covered
	 */
	EvaluationAuthoringPage addNewEvaluationFromExisting(String trainingType , String evaluationTitle , String titleName) {
		selectButtonOption("Add", trainingType)
		inputEvaluationTitleID(2,evaluationTitle)
		clickSearch(2)
		waitUploading()
		openDetailByText(evaluationTitle)
		clickYes()
		clickEdit()
		inputTitle(2,titleName)
		clickSave(3)
		WebUI.scrollToPosition(500, 0)
		clickActive()
		clickYes()
		return this
	}

	EvaluationAuthoringPage searchEvaluation(String evaluationType, String status, String evaluationTitle) {
		selectOptionEvaluationType(evaluationType)
		selectOptionStatus(status)
		inputEvaluationTitleID(evaluationTitle)
		clickSearch()
		return this
	}

	EvaluationAuthoringPage addNewEvaluationIfNotExist(String evaluationType, String status, String evaluationPrefix, String evaluationTitle, String evaluationDescription, String trainingType, String filterBy) {
		String _ResultsStatus = searchEvaluation(evaluationType, status, evaluationPrefix).onTable("Results").getTableStatus()
		if(_ResultsStatus == "No results returned.") {
			addNewEvaluation("New ${evaluationType}", evaluationTitle, evaluationDescription)
			addNewTrainingWithoutAssessment(trainingType)
		} else {
			openEvaluationSettings(evaluationType)
			String _EvaluationDetailsStatus = Pages.As(OnDataTable).setTable("Evaluation Details", 1).getTableStatus()
			if(_EvaluationDetailsStatus  == "No results returned.") {
				addNewTrainingWithoutAssessment(trainingType)
			}
			else {
				openTrainingContent(filterBy)
			}
		}
		return this
	}

	EvaluationAuthoringPage addNewEvaluationWithoutTrainingAssessmentIfNotExist(String evaluationType, String status, String evaluationPrefix, String evaluationTitle, String evaluationDescription) {
		String _ResultsStatus = searchEvaluation(evaluationType, status, evaluationPrefix).onTable("Results").getTableStatus()
		if(_ResultsStatus == "No results returned.") {
			addNewEvaluation("New ${evaluationType}", evaluationTitle, evaluationDescription)
		} else
			openEvaluationSettings(evaluationType)
		return this
	}

	EvaluationAuthoringPage addNewEnglishAssessmentDocumentIfExisting() {
		Boolean _checkExisting = BaseActions.waitForElementVisible(Pages.As(OnDataTable).setTable("Evaluation Details", 1).webTableRow("Document;English", false),  GlobalVariable.ShortTime, FailureHandling.OPTIONAL)
		if (_checkExisting) deleteTrainingTypeOnSettings("Document")
		selectButtonOption("Add", 2, "New English Assessment Document")
		return this
	}

	EvaluationAuthoringPage verifyEvaluationDeprecated(String filterByRow, String title) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyCellAttribute(filterByRow, "Title", "outerText", "${title}  DEPRECATED")
		return this
	}

	String getEvaluationIdBasedOnEvaluationTitleContent(String username, String password, String evaluationType, String evaluationStatus, String evaluationTitleContent) {
		Pages.As(LoginPage).login(username, password).selectMenu('Evaluation Authoring->Create, Edit, and Preview your evaluations')
		selectOptionEvaluationType(evaluationType)
		selectOptionStatus(evaluationStatus)
		inputEvaluationTitleID(evaluationTitleContent)
		clickSearch()
		return Pages.As(OnDataTable).setTable("Results").getCellAttribute(1, 1, "textContent")
	}

	//////////////////// Training Content ////////////////////////////
	EvaluationAuthoringPage viewTrainingContent(String filterBy) {
		Pages.As(OnDataTable).setTable("Training Content", 2).selectIconByTextOnTable(filterBy, "View", "JavaScript")
		return this
	}

	EvaluationAuthoringPage previewTrainingContent(String filterBy) {
		BaseActions.scrollToElement(webTab("Training Content"), GlobalVariable.ShortTime)
		Pages.As(OnDataTable).setTable("Training Content", 1).selectIconByTextOnTable(filterBy, "Preview", "selenium")
		return this
	}

	EvaluationAuthoringPage addNewSlide(String title, String text, String imageFile, String audioFile, String videoFile=null) {
		selectButtonOption("Add New", "Slide")
		inputSlideTitle(title)
		inputText(text)
		if(videoFile != null) uploadFileVideo(1, videoFile)
		else {
			if(imageFile !=null) uploadFileImage(3, imageFile)
			if(audioFile !=null) uploadFileAudio(3, audioFile)
		}
		selectButtonUnderLabel("Save", "Media Type:")
		return this
	}

	EvaluationAuthoringPage addNewSlideWithImageOnly(String slideTitle, String imageFile) {
		addNewSlide(slideTitle, "Automate iframe text", imageFile, null, null)
		return this
	}

	EvaluationAuthoringPage addNewQuiz(String title, String numberOfQuestions = null, Object randomizeQuestion = true, Object dealBreaker  = false, Object replayLastQuestion = false) {
		selectButtonOption("Add New", "Quiz")
		inputQuizTitle(title)
		if (numberOfQuestions != null) {
			inputNumberOfQuestions(numberOfQuestions)
			if (replayLastQuestion != false) selectCheckbox("Replay Last Section On Failure:", 1, replayLastQuestion)
		}
		if(randomizeQuestion != true) selectCheckbox("Randomize Questions:", 1, randomizeQuestion)
		if(dealBreaker != false) selectCheckbox("Always Include Deal Breakers:", 1, dealBreaker)
		selectButtonUponLabel("Save", "Quiz Title:", 4)
		return this
	}

	EvaluationAuthoringPage addNewDefaultQuiz(String title) {
		addNewQuiz(title, null, true, false, false)
		return this
	}

	EvaluationAuthoringPage importPowerPoint(String powerPointName) {
		selectButtonOption("Add New", "Import Power Point")
		uploadFilePowerPoint(powerPointName)
		clickUpload();
		return this
	}

	EvaluationAuthoringPage addTrueFalseToQuiz(String text, String imageFile, String audioFile, String correct, String pointValue, Object dealBreaker = false, Object showFeedback = false, String answerFeedBack = null) {
		selectButtonOption("Add", 4, "True / False", true)
		inputQuestionText(2, text)
		if(imageFile != null) uploadFileImage(3, imageFile)
		if(audioFile != null) uploadFileAudio(1, audioFile)
		if(correct == "False") selectRadio("False")
		if(pointValue != null) inputPointValue(2, pointValue)
		if(dealBreaker != false) selectCheckbox("Deal Breaker:", 1, dealBreaker)
		if(showFeedback != false) {
			selectCheckbox("Show Feedback:", 1, showFeedback)
			inputAnswerFeedback("Answer Feedback")
		}
		selectButtonUnderLabel("Save", "Question Number:", 2)
		return this
	}

	EvaluationAuthoringPage addDefaultTrueFalseToQuiz(String text) {
		selectButtonOption("Add", 3, "True / False", false)
		inputQuestionText(1, text)
		selectButtonUnderLabel("Save", "Question Number:", 2)
		return this
	}

	EvaluationAuthoringPage deleteTrainingContent(index=1, String filterBy) {
		Pages.As(OnDataTable).setTable("Training Content", index).selectCheckboxOnTable(filterBy, 8)
		clickDelete()
		clickYes()
		return this
	}

	EvaluationAuthoringPage verifyAudioTrainingPlaying(int index=1) {
		switchToNextWindow()
		// in Firefox, audio would not automatically play in slide preview
		if (DriverFactory.getExecutedBrowser().getName() == "FIREFOX_DRIVER") {
			BaseActions.verifyElementAttributeContains(webButton("", index), "title", "Pause")
		} else {
			BaseActions.verifyElementAttributeContains(webButton("", index), "title", "Pause")
		}	
		return this
	}

	EvaluationAuthoringPage verifyVideoTrainingPlaying(int index=1) {
		switchToNextWindow()
		// in Firefox, video would not automatically play in slide preview
		if (DriverFactory.getExecutedBrowser().getName() == "FIREFOX_DRIVER") {
			BaseActions.verifyElementAttributeContains(webButton("", index), "title", "Play")
		} else {
			BaseActions.verifyElementAttributeContains(webButton("", index), "title", "Pause")
		}
		return this
	}

	EvaluationAuthoringPage verifyAudioFilePresent(int index=1) {
		BaseActions.verifyElementPresent(webLabel("00:00"), GlobalVariable.ShortTime)
		BaseActions.verifyElementAttributeContains(webButton("", index), "title", "Play")
		return this
	}

	//////////////////// Evaluation Settings ////////////////////////////
	EvaluationAuthoringPage addNewEnglishTraining(String title, Object autoAdvanceSlides, Object allowUsersToDownloadTrainingPDF) {
		selectButtonOption("Add", 2, "New English Training")
		inputTitle(4, title)
		selectCheckbox("Auto Advance Slides:", 1, autoAdvanceSlides)
		selectCheckbox("Allow Users To Download Training PDF:", 1, allowUsersToDownloadTrainingPDF)
		clickSave()
		return this
	}

	EvaluationAuthoringPage deleteTrainingTypeOnSettings(String type) {
		Pages.As(OnDataTable).setTable("Evaluation Details", 1).selectIconByTextOnTable(type, "Delete")
		clickYes()
		return this
	}

	EvaluationAuthoringPage addNewEnglishTrainingIfExisting(String title, Object autoAdvanceSlides, Object allowUsersToDownloadTrainingPDF) {
		Boolean _checkExisting = BaseActions.waitForElementVisible(Pages.As(OnDataTable).setTable("Evaluation Details", 1).webTableRow("Training;English", false), GlobalVariable.ShortTime, FailureHandling.OPTIONAL)
		if (_checkExisting) deleteTrainingTypeOnSettings("Training")
		addNewEnglishTraining(title, autoAdvanceSlides, allowUsersToDownloadTrainingPDF)
		return this
	}

	EvaluationAuthoringPage addNewEnglishAssessmentIfExisting() {
		Boolean _checkExisting = BaseActions.waitForElementVisible(Pages.As(OnDataTable).setTable("Evaluation Details", 1).webTableRow("Assessment;English", false), GlobalVariable.ShortTime, FailureHandling.OPTIONAL)
		if (_checkExisting) deleteTrainingTypeOnSettings("Assessment")
		selectButtonOption("Add", 2, "New English Assessment")
		return this
	}

	EvaluationAuthoringPage addQuestionPool(String questionText, String answer1, String answer2, String answer3, String answer4, String audioFile=null) {
		selectTab("Question Pool")
		selectButtonOption("Add", 4, "Multiple Choice",true)
		inputQuestionText(2, questionText)
		Pages.As(OnDataTable).setTable("Correct_Header", 5).setTextOnTable(1, 2, answer1)
		Pages.As(OnDataTable).setTable("Correct_Header", 5).setTextOnTable(2, 2, answer2)
		Pages.As(OnDataTable).setTable("Correct_Header", 5).setTextOnTable(3, 2, answer3)
		Pages.As(OnDataTable).setTable("Correct_Header", 5).setTextOnTable(4, 2, answer4)
		if(audioFile != null) uploadFileAudio(4, audioFile)
		selectButtonUponLabel("Save", "Answer Feedback:", 6)
		waitUploading()
		return this
	}

	EvaluationAuthoringPage deleteAssessmentContent(index=1, String filterBy) {
		Pages.As(OnDataTable).setTable("Assessment Content", index).selectCheckboxOnTable(filterBy, 8)
		selectButtonUnderLabel("Delete", "total record(s).", 3)
		clickYes()
		return this
	}

	EvaluationAuthoringPage enterMatchingAnswer(String listValue) {
		def _listMatchingAnswer = listValue.split(';')
		Pages.As(OnDataTable).setTable("Order_Header", 3).setTextOnTable(1, 2, _listMatchingAnswer[0])
				.setTextOnTable(1, 5, _listMatchingAnswer[1])
				.setTextOnTable(2, 2, _listMatchingAnswer[2])
				.setTextOnTable(2, 5, _listMatchingAnswer[3])
				.setTextOnTable(3, 2, _listMatchingAnswer[4])
				.setTextOnTable(3, 5, _listMatchingAnswer[5])
				.setTextOnTable(4, 2, _listMatchingAnswer[6])
				.setTextOnTable(4, 5, _listMatchingAnswer[7])
		return this
	}

	EvaluationAuthoringPage addMatchingQuestion() {
		selectButtonOption("Add", 4, "Matching", true)
		return this
	}

	EvaluationAuthoringPage activeEvaluation() {
		selectTab("Evaluation Settings")
		clickActive()
		clickYes()
		return this
	}

	EvaluationAuthoringPage addPerformanceAssessmentQuestion(String questionText) {
		selectTab("Assessment Form")
		selectButtonUnderLabel("Add", "Copy Assessment")
		inputQuestionText(3, questionText)
		selectButtonUponLabel("Save", "Question Text:", 7)
		return this
	}

	EvaluationAuthoringPage previewDocument(String filterBy) {
		Pages.As(OnDataTable).setTable("Evaluation Details", 1).selectIconByTextOnTable(filterBy, "Preview")
		return this
	}

	EvaluationAuthoringPage uploadDocument(String fileName) {
		uploadFile(2, fileName)
		clickUpload(3);
		return this
	}

	EvaluationAuthoringPage verifyDocumentDownload(String fileName) {
		Pages.As(FileHelper).waitFileExist(fileName)
		String fileDownload = Pages.As(FileHelper).getDownloadFile("pdf");
		BaseActions.verifyMatch(fileDownload, fileName, true)
		return this
	}

	EvaluationAuthoringPage addNewSpanishAssessmentDocumentIfExisting() {
		Boolean _checkExisting = BaseActions.waitForElementVisible(Pages.As(OnDataTable).setTable("Evaluation Details", 1).webTableRow("Document;Spanish", false), GlobalVariable.ShortTime, FailureHandling.OPTIONAL)
		if (_checkExisting) deleteTrainingTypeOnSettings("Document")
			selectButtonOption("Add", 2, "New Spanish Assessment Document")
		return this
	}

	EvaluationAuthoringPage addNewEnglishAssessment() {
		selectButtonOption("Add", 2, "New English Assessment")
		return this
	}

	//////////////////// Assessment Content ////////////////////////////
	EvaluationAuthoringPage previewAssessmentContent(String filterBy) {
		BaseActions.scrollToElement(webTab("Assessment Content"), GlobalVariable.ShortTime)
		Pages.As(OnDataTable).setTable("Assessment Content", 1).selectIconByTextOnTable(filterBy, "Preview", "selenium")
		return this
	}

	EvaluationAuthoringPage openDetailByText(String taskListName) {
		Pages.As(OnDataTable).setTable("Results", 2).selectTextOnTable(taskListName, "Title")
		return this
	}

	List<String> getListColumnOnResults(Object columnName) {
		return Pages.As(OnDataTable).setTable("Results", 1).getListValuesTableColumn(columnName)
	}
	EvaluationAuthoringPage scrollUpAndClickSave(int index=1) {
		BaseActions.scrollUpAndClick(webButton("Save", index))
		return this
	}
}
