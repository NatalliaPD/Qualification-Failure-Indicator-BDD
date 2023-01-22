package com.ewn.common.page

import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.BaseElements
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.exception.StepFailedException

import internal.GlobalVariable

public class BasePage <T> extends BaseElements {

	//////////////////// General ////////////////////////////
	protected T pleaseWait(int waitTime=GlobalVariable.LongTime) {
		BaseActions.waitForPresent(webPleaseWait(), GlobalVariable.MediumTime - 2)
		BaseActions.waitForNotPresent(webPleaseWait(), waitTime)
		KeywordUtil.markPassed("Object: 'Please Wait ...' is closed");
		return this
	}

	protected T waitUploading(int waitTime=GlobalVariable.LongTime) {
		BaseActions.waitForPresent(webUploading(), GlobalVariable.MediumTime)
		BaseActions.waitForNotPresent(webUploading(), waitTime)
		KeywordUtil.markPassed("Object: 'Uploading ...' is closed");
		return this
	}

	protected T switchToMainIframe (String label) {
		boolean isSwitched = BaseActions.verifyElementPresent(webIframe(label), 5, FailureHandling.OPTIONAL)
		if (isSwitched) {
			BaseActions.switchToFrame(webIframe(label), 5)
		}
		return this
	}

	protected T switchOutIframe() {
		BaseActions.switchToDefaultContent()
		return this
	}

	String downloadFile(String extensionFile=".pdf") {
		BaseActions.delay(2) // waiting for show Downloading File dialog
		BaseActions.waitForNotPresent(webDownloadingFile(), GlobalVariable.LongTime)
		if(DriverFactory.getExecutedBrowser().getName() == "FIREFOX_DRIVER" && extensionFile == ".pdf") {
			BaseActions.closeWindowIndex(1, FailureHandling.OPTIONAL)
			BaseActions.switchToWindowIndex(0)
		}
		return new FileHelper().getDownloadFile(extensionFile)
	}

	/******************************************************
	 * Export file to Download folder
	 * @param itemName - item name to export
	 * @param extensionFile - the extension file type .txt; .pdf; .mp3; .xlsx; ...
	 */
	String exportTo(String itemName, String extensionFile=".pdf") {
		BaseActions.click(webButton("Export to"))
		BaseActions.click(webButtonOption(itemName))
		return downloadFile(extensionFile)
	}

	String selectButtonDownloadFile(String buttonName, String extensionFile=".pdf") {
		BaseActions.click(webButton(buttonName))
		return downloadFile(extensionFile)
	}

	//////////////////// Button ////////////////////////////
	/******************************************************
	 * Select Button option
	 * @param label - the label to find ButtonOption object
	 * @param index - the index to find ButtonOption object
	 * @param option - option item to select
	 */
	protected T selectButtonOption(String label, int index=1, String option, boolean isAssessment=false) {
		BaseActions.scrollUpAndClick(webButton(label, index))
		BaseActions.click(webButtonOption(option, isAssessment))
		return this
	}

	protected T verifyButtonPresent(String label, int index=1) {
		BaseActions.verifyElementClickable(webButton(label, index))
		return this
	}

	protected T verifyButtonTagPresent(String label, int index=1) {
		BaseActions.verifyElementClickable(webButtonTag(label, index))
		return this
	}

	protected T verifyButtonNotPresent(String label, int index=1) {
		BaseActions.verifyElementNotPresent(webButton(label, index),GlobalVariable.MediumTime)
		return this
	}

	/******************************************************
	 * Select Button Upon Label
	 * @param buttonName - button name to select
	 * @param label - the label to find Button object
	 * 
	 */
	protected T selectButtonUponLabel(String buttonName, String label, int index=1) {
		BaseActions.clickJS(webButtonUponLabel(buttonName, label, index))
		return this
	}

	/******************************************************
	 * Select Button Under Label
	 * @param buttonName - button name to select
	 * @param label - the label to find Button object
	 *
	 */
	protected T selectButtonUnderLabel(String buttonName, String label, int index=1) {
		BaseActions.clickJS(webButtonUnderLabel(buttonName, label, index))
		return this
	}

	//////////////////// Checkbox ////////////////////////////
	/******************************************************
	 * Select Checkbox
	 * @param label - the label Checkbox name to select
	 */
	protected T selectCheckbox(String label, int index=1, Object status=true) {
		BaseActions.selectCheckbox(webCheckbox(label,index), status)
		return this
	}

	protected T selectCheckboxOnEvaluator(String label, int index=1, Object status=true) {
		BaseActions.selectCheckbox(webCheckboxOnEvaluator(label,index), status)
		return this
	}
	
	protected T verifyCheckboxStatus(String label, int index=1, Object expectedStatus=true) {
		BaseActions.verifyCheckboxStatus(webCheckbox(label,index), expectedStatus)
		return this
	}

	protected T selectCheckboxUnderLabel(String checkboxLabel, String label, Object status=true) {
		BaseActions.selectCheckbox(webCheckboxUnderLabel(checkboxLabel, label), status)
		return this
	}

	//////////////////// Combobox ////////////////////////////
	/******************************************************
	 * Select multiple Option by Label (Support Combobox Object only)
	 * @param label - the label to find ComboboxMultiple object
	 * @param texts - the string list text to select (separate by ;): Ex "Select All", "Deselect All", "Expired;Expiring Soon;Suspended"
	 */
	protected T selectMultipleOption(String label, String texts) {
		TestObject _to = webComboboxMultiple(label)
		BaseActions.click(_to);
		String _label = _to.getObjectId().replace("_ComboboxMultiple", "");
		if (texts=="Select All" || texts=="Deselect All") BaseActions.click(webComboboxItem(_label, texts))
		else {
			BaseActions.click(webComboboxItem(_label, 'Deselect All'))
			texts.split(';').each {
				BaseActions.click(webComboboxItem(_label, it))
			}
		}
		return this
	}

	/******************************************************
	 * Verify item in the Combobox is exiting or not
	 * @param label - the label to find Combobox object
	 * @param item - item name need to verify
	 */
	protected T verifyItemExistingInDropdown(String label, String item, Object status=true) {
		if(status.toBoolean()) BaseActions.verifyElementPresent(webComboboxItem(label,item), GlobalVariable.LongTime)
		else BaseActions.verifyElementNotPresent(webComboboxItem(label,item), GlobalVariable.MediumTime)
		return this
	}

	//////////////////// Menu ////////////////////////////
	/******************************************************
	 * Select menus
	 * @param menus - the string list menus to select (separate by ->)
	 */
	protected T selectMenu(String menus, int index=1) {
		menus.split("->").each {
			if(it == "Activity" || it == "Organizational Tool Status") BaseActions.clickJS(webMenu(it, index))
			else if (it == "Create, Edit, and Preview your evaluations" || it == "Tasks") {
				BaseActions.clickJS(webMenu(it))
				//TODO: The popup does not display now, we will follow ups a time, if this popup does not  happen again, we will remove
				// BaseActions.click(webCloseIcon(), FailureHandling.OPTIONAL)
			}
			else BaseActions.clickJS(webMenu(it))
		}
		this.pleaseWait()
		return this
	}

	/*****************************************************
	 * Verify menu item not exist
	 */
	protected T verifyMenuNotExist(String menu) {
		BaseActions.waitForElementNotPresent(webMenu(menu),GlobalVariable.MediumTime)
	}


	//////////////////// Label ////////////////////////////
	/******************************************************
	 * Verify Label existing
	 */
	protected T verifyLabelExisting(String label, Object status=true) {
		if(status.toBoolean()) BaseActions.verifyElementPresent(webLabel(label), GlobalVariable.LongTime)
		else BaseActions.verifyElementNotPresent(webLabel(label), GlobalVariable.MediumTime)
		return this
	}

	protected T verifyLabelColor(String labelName, String expectedColor) {
		BaseActions.verifyColor(webLabel(labelName), "color", expectedColor)
		return this
	}

	protected T verifyButtonBackgroundColor(String labelName, String expectedColor) {
		BaseActions.verifyColor(webLabel(labelName), "background", expectedColor)
		return this
	}

	//////////////////// Header ////////////////////////////
	/******************************************************
	 * Verify Header exist
	 */
	protected T verifyHeader(String header) {
		BaseActions.verifyElementPresent(webHeader(header), GlobalVariable.LongTime)
		return this
	}

	//////////////////// Radio ////////////////////////////
	/******************************************************
	 * Select Radio
	 * @param label - the label Radio name to select
	 */
	protected T selectRadio(String label) {
		BaseActions.clickJS(webRadio(label))
		return this
	}

	protected T verifyRadioStatus(String label, Object expectedStatus=true) {
		BaseActions.verifyCheckboxStatus(webRadio(label), expectedStatus)
		return this
	}


	protected T selectRadioUnderLabel(String radioLabel, String label) {
		BaseActions.clickJS(webRadioUnderLabel(radioLabel, label))
		return this
	}

	//////////////////// Signature ////////////////////////////
	/******************************************************
	 * Signature C
	 * @param index - the index to find Signature object
	 * @param word - the word to Signature. Ex: "C" or "N"
	 */
	protected T signature(int index=1, String word) {
		TestObject _to = webSignature(index)
		String _xpathClearButton = _to.findPropertyValue("xpath") + "//../..//button"
		BaseActions.click(BaseActions.createTestObject("Clear_Button", "xpath", _xpathClearButton))
		BaseActions.draw(_to, word)
		return this
	}

	/******************************************************
	 * Verify signature
	 * @param label - the label to find Signature object
	 * @param word - the expected word signature. Ex: "C" or "N"
	 */
	protected T verifySignature(int index=1, String word) {
		BaseActions.verifyImageWithinElement(webSignature(index), "Signature${word}.png", true)
		return this
	}

	//////////////////// Tab ////////////////////////////
	/******************************************************
	 * Select Tab
	 * @param label - the label tab name to select
	 */
	protected T selectTab(String label) {
		BaseActions.scrollUpAndClick(webTab(label))
		return this
	}

	protected T verifyTabPresent(String label) {
		BaseActions.verifyElementPresent(webTab(label), GlobalVariable.MediumTime)
		return this
	}

	protected T verifyTabActive(String label, Object expectedStatus=true) {
		String _xpathObject = webTab(label).findPropertyValue("xpath") + "/../.."
		String _classValue = BaseActions.getAttribute(BaseActions.createTestObject("${label}_Tab", "xpath", _xpathObject), "class")
		BaseActions.verifyEqual(_classValue.endsWith("active"), expectedStatus.toBoolean())
		return this
	}

	//////////////////// Textbox ////////////////////////////
	/******************************************************
	 * Set Text Iframe (Support Textbox Object in Iframe)
	 * @param label - the label to find TextIframe object
	 * @param text - the text to type
	 */
	protected T setTextIframe(String label, int index=1, String text) {
		BaseActions.switchToFrame(webIframe(label, index), GlobalVariable.LongTime)
		BaseActions.click(webTextIframe())
		for (String s : text.split("")) {
			BaseActions.pressKey(s)
			Thread.sleep(50)
		}
		BaseActions.switchToDefaultContent()
		return this
	}

	/******************************************************
	 * Verify Text Iframe (Support Textbox Object in Iframe)
	 * @param label - the label to find TextIframe object
	 * @param ecpectedText - the expected text
	 */
	protected T verifyTextIframe(String label, String ecpectedText) {
		BaseActions.switchToFrame(webIframe(label), GlobalVariable.LongTime)
		BaseActions.verifyElementTextContent(webTextIframe(), ecpectedText)
		BaseActions.switchToDefaultContent()
		return this
	}

	/******************************************************
	 * Set Text Associate (Support Text Associate Object only)
	 * @param label - the label to find TextAssociate object
	 * @param index - the index to find TextAssociate object
	 * @param text - the text to type
	 * @param item - the item name to select
	 */
	protected T setTextAssociate(String label, int index=1, String text, String item) {
		BaseActions.setText(webTextbox(label, index), text)
		BaseActions.waitForElementVisible(webTextAssociate(item), GlobalVariable.LongTime)
		BaseActions.click(webTextAssociate(item))
		return this
	}

	//////////////////// Audio ////////////////////////////
	protected T verifyAudioStatus(String label, String expectedStatus="Playing") {
		String _currentStatus = "Pausing"
		String _getStatus = BaseActions.getAttribute(webIconPlayPause(label), "class")
		if (_getStatus.contains("-pause")) _currentStatus = "Playing"
		BaseActions.verifyEqual(_currentStatus, expectedStatus)
		return this
	}

	//////////////////////////// Move Item ////////////////////////////
	/******************************************************
	 * Move Available Items
	 * @param label - the label to find Right Move object
	 * @param listItems - list checkbox label items to move
	 */
	protected T moveAvailableItems(String label, String listItems) {
		BaseActions.waitForElementVisible(webSearch(label), GlobalVariable.ShortTime)
		BaseActions.scrollIntoEWNView(webCheckbox(label))
		if(listItems == "All") {
			BaseActions.check(webCheckbox(label))
			BaseActions.click(webButtonArrowRight(label))
		} else {
			listItems.split(";").each {
				BaseActions.setText(webSearch(label), it)
				BaseActions.waitForElementVisible(webCheckboxContainsText(it), GlobalVariable.ShortTime)
				BaseActions.check(webCheckboxContainsText(it))
				BaseActions.click(webButtonArrowRight(label))
			}
		}
		return this
	}

	List<String> getListGroupItems(String label, String searchBy = null) {
		if (searchBy != null) BaseActions.setText(webSearch(label), searchBy)
		return BaseActions.getAttributeOnList(webListGroupItems(label), "innerText")
	}

	/******************************************************
	 * Move Selected Items
	 * @param index - index number to find Left Move object
	 * @param listItems - list checkbox label items to move
	 */
	protected T moveSelectedItems(String label, String listItems) {
		BaseActions.waitForElementVisible(webSearch(label), GlobalVariable.ShortTime)
		BaseActions.scrollIntoEWNView(webCheckbox(label))
		if(listItems == "All") {
			BaseActions.check(webCheckbox(label))
			BaseActions.click(webButtonArrowLeft(label))
		} else {
			listItems.split(";").each {
				BaseActions.setText(webSearch(label), it)
				BaseActions.waitForElementVisible(webCheckboxContainsText(it), GlobalVariable.ShortTime)
				BaseActions.check(webCheckboxContainsText(it))
				BaseActions.click(webButtonArrowLeft(label))
			}
		}
		return this
	}

	/******************************************************
	 * Expand Section
	 * @param label - the label to find Section
	 * @param status - true (expand); false (collapse)
	 */
	protected T expandSection(String label, boolean status=true) {
		TestObject _sectionObject = webSection(label)
		BaseActions.scrollIntoEWNView(_sectionObject)
		if (BaseActions.getAttribute(_sectionObject, "aria-expanded") != status.toString()) BaseActions.click(_sectionObject)
		return this
	}

	protected T verifyCompanyHomeDisplayed(String companyName) {
		verifyLabelExisting(companyName)
		BaseActions.verifyEqual(BaseActions.getUrl().contains("/PROIINET/Help"), true);
		return this
	}

	protected T verifyUserNameDisplayed(String userName) {
		verifyLabelExisting(userName)
		BaseActions.verifyEqual(BaseActions.getUrl().contains("/PROIINET/Help"), true);
		return this
	}

	protected T verifyPageTitle(String expectedPageTitle) {
		BaseActions.verifyEqual(BaseActions.getWindowTitle(), expectedPageTitle)
		return this
	}

	protected T uploadFile(String label, int index=1, String fileAbsolutePath) {
		BaseActions.uploadFile(webUploadFile(label, index), fileAbsolutePath)
		waitUploading()
		return this
	}

	protected T uploadFileAndRetry(String label, int index=1, String fileAbsolutePath) {
		int retryTime = 5
		int retries = 0
		String errorMessage = ""
		boolean isUploadedSuccess = false
		while (retries < retryTime) {
			try {
				this.uploadFile(label, index, fileAbsolutePath)
				isUploadedSuccess = true
				break
			} catch (StepFailedException e) {
				File file = new File(fileAbsolutePath)
				if(file.exists()) {
					KeywordUtil.markWarning("Give upload file a second chance to find to file")
					BaseActions.delay(1)
				}
				retries ++
				errorMessage = e.getMessage()
			}
		}
		if (!isUploadedSuccess) throw new StepFailedException(errorMessage)
	}

	/******************************************************
	 * Assessment Questions
	 * @param label - the label to find object
	 * @param answerOption - answer string need to verify
	 */

	protected T verifyQuestionDisplayed(String label) {
		BaseActions.verifyElementPresent(webLabel(label), GlobalVariable.LongTime)
		waitUploading()
		return this
	}

	protected T verifyRadioAnswerOption(String answerOption) {
		BaseActions.verifyElementPresent(webRadio(answerOption),GlobalVariable.LongTime)
		waitUploading()
		return this
	}

	protected T verifyCheckBoxAnswerOption(String answerOption) {
		BaseActions.verifyElementPresent(webCheckbox(answerOption),GlobalVariable.LongTime)
		waitUploading()
		return this
	}
}
