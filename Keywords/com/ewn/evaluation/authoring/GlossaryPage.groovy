package com.ewn.evaluation.authoring

import org.openqa.selenium.Keys as Keys

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.webui.driver.DriverFactory
import com.ewn.task.management.TaskListsPage
import internal.GlobalVariable

public class GlossaryPage extends BasePage<GlossaryPage> {

	//////////////////// Button ////////////////////////////
	GlossaryPage clickYes() {
		BaseActions.click(webButton("Yes"))
		return this
	}

	GlossaryPage clickAdd(int index=1) {
		BaseActions.click(webButton("Add", index))
		return this
	}

	GlossaryPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	GlossaryPage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	GlossaryPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	GlossaryPage clickDelete(int index=1) {
		BaseActions.click(webButton("Delete", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	GlossaryPage selectOptionSpanishAvailable(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Spanish Available:", index), item, true)
		return this
	}

	//////////////////// Icon ////////////////////////////
	GlossaryPage clickIconPlayPause(String label) {
		BaseActions.click(webIconPlayPause(label))
		return this
	}

	//////////////////// Image ////////////////////////////
	GlossaryPage verifyImage(String label, int index=1, String expectedImageName) {
		BaseActions.verifyImageWithinElement(webImage(label, index), expectedImageName)
		return this
	}

	//////////////////// Label ////////////////////////////
	GlossaryPage verifyLabelTerm(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Term:", index), expectedText)
		return this
	}

	GlossaryPage verifyLabelTag(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Tag:", index), expectedText)
		return this
	}

	GlossaryPage verifyLabelText(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Text:", index), expectedText)
		return this
	}

	GlossaryPage verifyLabelImage(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Image:", index), expectedText)
		return this
	}

	GlossaryPage verifyLabelAudio(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Audio:", index), expectedText)
		return this
	}

	GlossaryPage verifyLabelUseEnglishImage(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Use English Image:", index), expectedText)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	GlossaryPage inputEvaluationTitleID(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation Title / ID:", index), text.toString())
		return this
	}

	GlossaryPage inputTerm(int index=1, Object text) {
		BaseActions.setText(webTextbox("Term:", index), text.toString())
		return this
	}

	GlossaryPage inputText(int index=1, String text) {
		if(DriverFactory.getExecutedBrowser().getName() == "FIREFOX_DRIVER") {
			BaseActions.click(webTextbox("Term:", index + 1))
			BaseActions.pressKey(Keys.chord(Keys.TAB))
			BaseActions.pressKey(Keys.chord(Keys.TAB))
			BaseActions.pressKey(text.toString())
		} else setTextIframe("Text:", index, text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name, int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	GlossaryPage viewTerm(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "Details")
		return this
	}
	
	GlossaryPage viewCBTReference(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "View")
		return this
	}
	//////////////////// Upload File ////////////////////////////
	GlossaryPage uploadFileAudio(int index=1, String audioName) {
		uploadFile("Audio:", index, audioName)
		return this
	}

	GlossaryPage uploadFileImage(int index=1, String imageName) {
		uploadFile("Image:", index, imageName)
		return this
	}

	//////////////////// Actions ////////////////////////////
	GlossaryPage addNewTerm(String term, String text, String imageFile, String audioFile) {
		clickAdd()
		inputTerm(4, term)
		inputText(3, text)
		uploadFileImage(4, imageFile)
		uploadFileAudio(4, audioFile)
		clickSave(2)
		return this
	}

	GlossaryPage deleteTerm(int index=1, String filterBy) {
		Pages.As(OnDataTable).setTable("Results", index).selectCheckboxOnTable(filterBy, 6)
		clickDelete(1)
		clickYes()
		return this
	}

	GlossaryPage searchTerm(String text, int index=1) {
		inputTerm(index, text)
		clickSearch()
		return this
	}
}
