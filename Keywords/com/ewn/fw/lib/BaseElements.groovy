package com.ewn.fw.lib

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

public class BaseElements {

	private TestObject createTestObject(String name, String attributeName, String attributeValue) {
		TestObject _dynamic = new TestObject(name);
		_dynamic.addProperty(attributeName, ConditionType.EQUALS, attributeValue);
		return _dynamic;
	}

	/******************************************************
	 * Get Button TestObject
	 * @param label - the label to find Button object
	 * @param index - index number to find Button object
	 * @return Button object
	 */
	TestObject webButton(String label, int index=1) {
		return createTestObject("${label}_Button", "xpath", "(//button[normalize-space(.)='${label}'][@type='button' or @class='primary' or starts-with(@class,'btn ')])[${index}]")
	}
	
	TestObject webVisibleButton(String label) {
		TestObject buttonObject = createTestObject("${label}_Label", "xpath", "(//button[normalize-space(.)='${label}'][@type='button' or @class='primary' or starts-with(@class,'btn ')])")
		List<WebElement> buttonElements = BaseActions.findWebElements(buttonObject, GlobalVariable.ShortTime)
		List<WebElement> visibleButtons = buttonElements.findAll{it.isDisplayed()}
		
		WebDriver _driver = DriverFactory.getWebDriver()
		
		//assuming the only visible element is the one we want, so we will warn if we found two or more visible labels on the page
		if(visibleButtons.size() > 1) KeywordUtil.markWarning("Found ${visibleButtons.size()} elements of Button ${label} on the page. Return the first one")
		
		TestObject toReturnButton = BaseActions.convertWebElementToTestObject(visibleButtons.head())
		BaseActions.scrollIntoEWNView(toReturnButton)
		return toReturnButton
	}
	
	TestObject webButtonIcon(String label , int index=1) {
		return createTestObject("Button", "xpath", "(//*[label[normalize-space(text())='${label}']]//span)[${index}]")
	}
	
	TestObject webLabelItem(String label , int index=1) {
		return createTestObject("Button", "xpath", "(//*[normalize-space(text())='${label}'])//following::div[${index}]")
	}

	TestObject webTableFirstCell(Object index) {
		return BaseActions.createTestObject("TableCell[${index}]", "xpath", "(//th[contains(@class,'header')])[${index}]//input[@type='checkbox']");
	}

	TestObject webButtonTag(String label, int index=1) {
		return createTestObject("${label}_Button", "xpath", "(//a[normalize-space(.)='${label}'][@type='button' or @class='primary' or starts-with(@class,'btn ')])[${index}]")
	}

	TestObject webBody(int index=1) {
		return createTestObject("${index}_Body", "xpath", "(//body)[${index}]");
	}

	TestObject webButtonOption(String label, Boolean isAssessment=false) {
		if (isAssessment == false){
			return createTestObject("${label}_ButtonOption", "xpath", "//ul[starts-with(@class,'dropdown-menu')]//a[normalize-space(.)='${label}']")
		}
		else {
			return createTestObject("${label}_ButtonOption", "xpath", "//ul[starts-with(@class,'dropdown-menu')]//a[normalize-space(.)='${label}' and contains(@ng-click,'vmAssessment')]")
		}
	}

	/******************************************************
	 * Get CalendarButton TestObject
	 * @param label - is "from" or "to"
	 * @return calendarButton object
	 */
	TestObject calendarButton(String label) {
		String xpath = ""
		if (label.toLowerCase()=="from") {
			xpath = "//ewn-datepicker[@name='uxCalStartExpDate']//div[@class='input-group-btn']"
		}
		if (label.toLowerCase()=="to") {
			xpath = "//ewn-datepicker[@name='uxCalEndExpDate']//div[@class='input-group-btn']"
		}
		return createTestObject("calendar_${label}_Button", "xpath", xpath)
	}

	/******************************************************
	 * Get calendarSubButton TestObject
	 * @param label - is "from" or "to"
	 * @param buttonName - Button Name such as "Today" or "Clear" or "Close"
	 * @return calendarSubButton object
	 */
	TestObject calendarSubButton(String label, String buttonName) {
		String xpath = ""
		if (label.toLowerCase()=="from") {
			xpath = "//ewn-datepicker[@name='uxCalStartExpDate']//button[text()='${buttonName}']"
		}
		if (label.toLowerCase()=="to") {
			xpath = "//ewn-datepicker[@name='uxCalEndExpDate']//button[text()='${buttonName}']"
		}
		return createTestObject("calendar_${label}_${buttonName}_Button", "xpath", xpath)
	}
	/******************************************************
	 * Get ButtonAnswer TestObject
	 * @param label - the label to find ButtonAnswer object
	 * @param index - index number to find ButtonAnswer object
	 * @return ButtonAnswer object
	 */
	TestObject webButtonAnswer(Object label, int index) {
		return createTestObject("${label}_ButtonAnswer", "xpath", "//*[self::strong or self::span][normalize-space(text())='${label}']/following::div[starts-with(@class,'radio btn btn-lg')][${index}]");
	}

	/******************************************************
	 * Get ButtonArrowLeft TestObject
	 * @param label - the label to find ButtonArrowLeft object
	 * @return - ButtonArrowLeft object
	 */
	TestObject webButtonArrowLeft(String label) {
		return createTestObject("${label}_ButtonArrowLeft", "xpath", "//span[text()='${label}']/preceding::div[@class='btn btn-sm btn-default ri-arrow-left-s-line ng-scope'][1]");
	}

	/******************************************************
	 * Get ButtonArrowRight TestObject
	 * @param label - the label to find ButtonArrowRight object
	 * @return - ButtonArrowRight object
	 */
	TestObject webButtonArrowRight(String label, int index=1) {
		return createTestObject("${label}_ButtonArrowRight", "xpath", "//span[text()='${label}']/following::div[@class='btn btn-sm btn-default ri-arrow-right-s-line'][${index}]");
	}
	
	/******************************************************
	 * Get Button under Label TestObject
	 * @param buttonName - button name
	 * @param label - the label to find Button object
	 * @return Button object
	 */
	TestObject webButtonUnderLabel(String buttonName, String label, int index=1) {
		return createTestObject("${buttonName}_ButtonUnderLabel", "xpath", "(//*[normalize-space(text())='${label}']/following::button[normalize-space(.)='${buttonName}'][@type='button' or @class='primary' or starts-with(@class,'btn button')])[${index}]");
	}

	/******************************************************
	 * Get Button upon Label TestObject
	 * @param buttonName - button name
	 * @param label - the label to find Button object
	 * @return Button object
	 */
	TestObject webButtonUponLabel(String buttonName, String label, int index=1) {
		return createTestObject("${buttonName}_ButtonUponLabel", "xpath", "(//*[normalize-space(text())='${label}']/preceding::button[normalize-space(.)='${buttonName}'][@type='button' or @class='primary' or starts-with(@class,'btn button')])[${index}]");
	}

	/******************************************************
	 * Get Checkbox TestObject
	 * @param label - the label to find Chexbox object
	 * @param index - index number to find Chexbox object
	 * @return - Chexbox object
	 */
	TestObject webCheckbox(String label, int index=1) {
		return createTestObject("${label}_Checkbox", "xpath", "//*[self::span or self::label][normalize-space(.)='${label}']/..//input[@type='checkbox'][${index}]");
	}
	
	TestObject webCheckboxContainsText(String label, int index=1) {
		return createTestObject("${label}_Checkbox", "xpath", "//*[self::span or self::label][text()[contains(.,'${label}')]]/..//input[@type='checkbox'][${index}]");
	}
	
	TestObject webCheckboxOnEvaluator(String label, int index=1) {
		return createTestObject("${label}_Checkbox", "xpath", "(//*[self::span or self::label][normalize-space(.)='${label}']/..//input[@type='checkbox'])[${index}]");
	}

	TestObject webCheckboxUnderLabel(String checkboxLabel, String label) {
		return createTestObject("${label}_CheckboxUnderLabel", "xpath", "//*[normalize-space(text())='${label}']/following::*[self::span or self::label][normalize-space(text())='${checkboxLabel}']/..//input[@type='checkbox']");
	}

	/******************************************************
	 * Get AssignPermissionCheckbox TestObject
	 * @param label - the label to find AssignPermissions object
	 * @param index - index number to find AssignPermissions object
	 * @param labelCheckbox - the labelCheckbox to find AssignPermissionCheckbox object
	 * @return AssignPermissionCheckbox object
	 */
	TestObject webAssignPermissionCheckbox(String label, int index=1, String labelCheckbox) {
		return createTestObject("${label}_${labelCheckbox}_AssignPermission", "xpath", "(//td[normalize-space(text())='${label}'])[${index}]/..//label[normalize-space(.)='${labelCheckbox}']/input[@type='checkbox']")
	}

	/******************************************************
	 * Get Combobox TestObject
	 * @param label - the label to find Combobox object
	 * @param index - index number to find Combobox object
	 * @return - Combobox object
	 */
	TestObject webCombobox(String label, int index=1) {
		return createTestObject("${label}_Combobox", "xpath", "(//*[label[normalize-space(text())='${label}']])[${index}]//select");
	}

	/******************************************************
	 * Get Combobox Multiple TestObject
	 * @param label - the label to find ComboboxMultiple object
	 * @return - Combobox object
	 */
	TestObject webComboboxMultiple(String label) {
		return createTestObject("${label}_ComboboxMultiple", "xpath", "//*[label[normalize-space(text())='${label}']]//button");
	}

	/******************************************************
	 * Get Combobox Item TestObject
	 * @param label - the label to find ComboboxItem object
	 * @return - ComboboxItem object
	 */
	TestObject webComboboxItem(String label, String item) {
		return createTestObject("${item}_ComboboxItem", "xpath", "//*[label[normalize-space(text())='${label}']]//*[text()='${item}']");
	}

	/******************************************************
	 * Get CommentsAnswer TestObject
	 * @param label - the label to find CommentsAnswer object
	 * @return - CommentsAnswer object
	 */
	TestObject webCommentsAnswer(Object label) {
		return createTestObject("${label}_CommentsAnswer", "xpath", "//*[self::strong or self::span][normalize-space(text())='${label}']/following::textarea[1]");
	}

	/******************************************************
	 * Get Header TestObject
	 * @param label - the label to find Header object
	 * @return - Header object
	 */
	TestObject webHeader(String label) {
		return createTestObject("${label}_Header", "xpath", "//div[@id='layout-content']//*[self::h4 or self::h1 or self::a][normalize-space(text())='${label}']");
	}

	/******************************************************
	 * Get Icon TestObject
	 * @param iconName - Find the icon by @uib-popover 
	 * @return - Icon object
	 */
	TestObject webIcon(String label) {
		return createTestObject("${label}_Icon", "xpath", "//*[@uib-popover='${label}']");
	}

	TestObject webIconOnTracerAssetManagement(String label) {
		return createTestObject("${label}_Icon", "xpath", "//*[@class='${label}']");
	}

	/******************************************************
	 * Get IconPlayPause TestObject
	 * @param index - the index to find IconPlayPause object
	 * @return - IconPlayPause object
	 */
	TestObject webIconPlayPause(String label) {
		return createTestObject("${label}_IconPlayPause", "xpath", "//*[normalize-space(text())='${label}']/following::div[contains(@ng-show,'audio')]//div[starts-with(@class,'mejs-button mejs-playpause-button mejs')]");
	}

	/******************************************************
	 * Get TimeSlider TestObject
	 * @return - IimeSlider object
	 */
	TestObject webTimeSlider() {
		return createTestObject("TimeSlider", "xpath", "//a[@class='mejs-time-total mejs-time-slider'][@aria-valuetext]");
	}

	/******************************************************
	 * Get Iframe TestObject
	 * @param label - the label to find Iframe object
	 * @return - IframeText object
	 */
	TestObject webIframe(String label, int index=1) {
		return createTestObject("${label}_Iframe", "xpath", "(//*[self::legend or self::label][normalize-space(text())='${label}'])[${index}]/..//iframe");
	}

	/******************************************************
	 * Get TextIframe TestObject
	 */
	TestObject webTextIframe() {
		return createTestObject("TextIframe", "xpath", "//body/p");
	}

	/******************************************************
	 * Get Image TestObject
	 * @param label - the label to find Image object
	 * @param index - index number to find Image object
	 * @return - Image object
	 */
	TestObject webImage(String label, int index=1) {
		return createTestObject("${label}_Image", "xpath", "//*[normalize-space(text())='${label}']/following::img[${index}]");
	}

	/******************************************************
	 * Get Label TestObject
	 * @param label - the label to find Label object
	 * @return - Label object
	 */
	TestObject webLabel(String label) {
		return createTestObject("${label}_Label", "xpath", "//*[normalize-space(text())='${label}']");
	}

	/******************************************************
	 * Get Label TestObject
	 * @param label - the label to find LabelValue object
	 * @return - LabelValue object
	 */
	TestObject webLabelValue(String label, int index=1) {
		return createTestObject("${label}_LabelValue", "xpath", "(//*[normalize-space(text())='${label}'])[${index}]/..//*[self::p or self::span or self::li][text()]|(//label[text()='${label}'])[${index}]/../div//*[self::label or self::p]");
	}

	/******************************************************
	 * Get ExpiringNumber TestObject
	 * @param label - the label to find LabelValue object
	 * @return - ExpiringNumber object
	 */
	TestObject webExpiringNumber(String label) {
		return createTestObject("${label}_ExpiringNumber", "xpath", "//div[@class='expiring-summary-box-users ng-binding']/following-sibling::div[1][text()='${label}']/preceding-sibling::div[1]");
	}

	/******************************************************
	 * Get Menu TestObject
	 * @param label - the label to find Menu object
	 * @return - Menu object
	 */
	TestObject webMenu(String label, int index=1) {
		return createTestObject("${label}_Menu", "xpath", "(//div[@ng-repeat='sec in vmMain.menu.sections']//a[not(@role)][.//*[text()='${label}']])[${index}]");
	}

	/******************************************************
	 * Get NotificationMessage TestObject
	 * @param label - the label to find NotificationMessage object
	 * @return - NotificationMessage object
	 */
	TestObject webNotificationMessage(String label) {
		return createTestObject("${label}_Menu", "xpath", "//li[@ng-repeat='notification in vmMain.notifications'][.='${label}']");
	}

	/******************************************************
	 * Get Radio TestObject
	 * @param label - the label to find Radio object
	 * @return - Radio object
	 */
	TestObject webRadio(String label) {
		return createTestObject("${label}_Radio", "xpath", "//label[normalize-space(.)='${label}']/input[@type='radio']|//label[normalize-space(.)='${label}']/preceding-sibling::input[@type='radio']");
	}

	/******************************************************
	 * Get Radio TestObject
	 * @param label - the label to find Radio object
	 * @return - Radio object
	 */
	TestObject webRadioUnderLabel(String radiolabel, String label) {
		return createTestObject("${radiolabel}_RadioUnderLabel", "xpath", "//*[normalize-space(text())='${label}']/following::label[normalize-space(.)='${radiolabel}'][1]/input[@type='radio']");
	}

	/******************************************************
	 * Get Section TestObject
	 * @param label - the label to find Section object
	 * @return - Section object
	 */
	TestObject webSection(String label) {
		return createTestObject("${label}_Section", "xpath", "//a[contains(.,'${label}')][@aria-expanded]");
	}

	/******************************************************
	 * Get Slide TestObject
	 * @param slide - the label or index to find Slide object
	 * @return - Slide object
	 */
	TestObject webSlide(Object slide) {
		if (slide instanceof String) return createTestObject("${slide}_Slide", "xpath", "//li[@ng-repeat='item in training.items'][./span[1][text()='${slide}']]");
		else return createTestObject("${slide}_Slide", "xpath", "//ol/li[${slide}]");
	}

	/******************************************************
	 * Get NextSlide TestObject
	 * @return - NextSlide object
	 */
	TestObject webNextSlide(String language = "English") {
		if (language == "English") {
			return createTestObject("NextSlide", "xpath", "//span[@uib-tooltip='Next Slide'][contains(@ng-class,'current.item')]")
		}
		return createTestObject("NextSlide", "xpath", "//span[@uib-tooltip='Siguiente diapositiva'][contains(@ng-class,'current.item')]")
	}

	/******************************************************
	 * Get PreviousSlide TestObject
	 * @return - PreviousSlide object
	 */
	TestObject webPreviousSlide(String language = "English") {
		if (language == "English") {
			return createTestObject("PreviousSlide", "xpath", "//span[@uib-tooltip='Previous Slide'][contains(@ng-class,'current.item')]")
		}
		return createTestObject("PreviousSlide", "xpath", "//span[@uib-tooltip='Diapositiva anterior'][contains(@ng-class,'current.item')]");
	}

	/******************************************************
	 * Get Search TestObject
	 * @param label - the label to find Search object
	 * @return - Search object
	 */
	TestObject webSearch(String label, int index =1) {
		return createTestObject("${label}_Search", "xpath", "//span[text()='${label}']/preceding::input[@placeholder='Search'][${index}]");
	}
	
	TestObject webSearchForAvailableTasks(String label, int index =1) {
		return createTestObject("${label}_Search", "xpath", "(//input[@placeholder='Search'])[${index}]");
	}
	
	TestObject webSearchOnEvaluator(String label, int index =1) {
		return createTestObject("${label}_Search", "xpath", "(//span[text()='${label}']/preceding::input[@placeholder='Search'])[${index}]");
	}

	TestObject webListGroupItems(String label) {
		return createTestObject("${label}_ListGroupItems", "xpath", "//div[@class='list-group'][.//span[text()='${label}']]/div[2]/div[starts-with(@class,'list-group-item')]");
	}

	/******************************************************
	 * Get Signature TestObject
	 * @param index - the index to find Signature object
	 * @return - Signature object
	 */
	TestObject webSignature(int index=1) {
		return createTestObject("${index}_Signature", "xpath", "(//canvas[contains(@id,'Signature')])[${index}]");
	}

	/******************************************************
	 * Get Tab TestObject
	 * @param label - the label to find Tab object
	 * @return - Tab object
	 */
	TestObject webTab(String label, int index=1) {
		return createTestObject("${label}_Tab", "xpath", "(//uib-tab-heading[normalize-space(.)='${label}'])[${index}]");
	}

	/******************************************************
	 * Get Textbox TestObject
	 * @param label - the label to find Textbox object
	 * @param index - index number to find Textbox object
	 * @return - Textbox object
	 */
	TestObject webTextbox(String label, int labelIndex = 1) {
		return createTestObject("${label}_Textbox", "xpath", "(//label[normalize-space(.)='${label}'])[${labelIndex}]/following::*[self::input or self::textarea][@id or @uib-datepicker-options or @typeahead-on-select][1]");
	}
	
	TestObject webVisibleTextbox(String label) {
		TestObject labelObject = createTestObject("${label}_Label", "xpath", "//label[normalize-space(.)='${label}']")
		List<WebElement> labelElements = BaseActions.findWebElements(labelObject, GlobalVariable.MediumTime)
		List<WebElement> visibleLabels = labelElements.findAll{it.isDisplayed()}
		
		WebDriver _driver = DriverFactory.getWebDriver()
		
		//assuming the only visible element is the one we want, so we will warn if we found two or more visible labels on the page
		if(visibleLabels.size() > 1) KeywordUtil.markWarning("Found ${visibleLabels.size()} elements of label ${label} on the page. Using the first one to locate textbox")
			
		WebElement textboxElement = ((JavascriptExecutor) _driver).executeScript('return arguments[0].control;', visibleLabels.head())
		return BaseActions.convertWebElementToTestObject(textboxElement)
	}

	TestObject webPopUp() {
		return createTestObject("PopUp_Textbox", "xpath", "//div[@class='popover b-popover bs-popover-right']");
	}


	/******************************************************
	 * Get TexAssociate TestObject
	 * @param label - the label to find TextAssociate object
	 * @return - TextAssociate object
	 */
	TestObject webTextAssociate(String label) {
		return createTestObject("${label}_TextAssociate", "xpath", "//a[@class='ng-binding ng-scope'][@title='${label}']");
	}

	/******************************************************
	 * Get UploadFile TestObject
	 * @param label - the label to find UploadFile object
	 * @param index - the index to find UploadFile object
	 * @return - UploadFile object
	 */
	TestObject webUploadFile(String label, int index=1) {
		return createTestObject("${label}_UploadFile", "xpath", "(//*[normalize-space(text())='${label}']/following::input[@type='file'])[${index}]");
	}

	/******************************************************
	 * Get PleaseWait TestObject
	 * @return - PleaseWait object
	 */
	TestObject webPleaseWait() {
		return createTestObject("PleaseWait", "xpath", "//div[@class='cg-busy cg-busy-animation ng-scope'][.//div[text()='Please Wait...']]");
	}

	TestObject webUploading() {
		return createTestObject("PleaseWait", "xpath", "//h3[text()='Uploading...']");
	}

	/******************************************************
	 * Get DownloadingFile TestObject
	 * @return - DownloadingFile object
	 */
	TestObject webDownloadingFile() {
		return createTestObject("DownloadingFile", "xpath", "//h3[@class='modal-title ng-binding'][text()='Downloading File']");
	}

	/******************************************************
	 * Get Custom Icon TestObject
	 * @param label - the label to find Custom Icon object
	 * @param index - the index to find Custom Icon object
	 * @return - Icon object
	 */

	TestObject webSearchIcon(String label) {
		return createTestObject("Button_Icon", "xpath", "//label[normalize-space(text())='${label}']/..//button[./span[@class='ri-search-line']]");
	}

	TestObject webAddIcon(String label) {
		return createTestObject("Button_Icon", "xpath", "//label[normalize-space(text())='${label}']/..//button[./span[@class='ri-add-line']]");
	}

	TestObject webTotalRecords(int index=1) {
		return createTestObject("${index}_TotalRecords", "xpath", "(//span[text()='total record(s).']/../span[1])[${index}]");
	}

	TestObject webBookIcon(String label, int index=1) {
		return createTestObject("${index}_BookIcon", "xpath", "(//span[contains(@class,'${label}')]/../span[${index}])");
	}

	TestObject webCloseIcon() {
		return createTestObject("X_Icon", "xpath", "//button[.//*[@fill='black']]");
	}

	TestObject weblockedLineIcon(String label) {
		return createTestObject("Button_Icon", "xpath", "//span[normalize-space(text())='${label}']/ancestor::td//span[@class='ri-lock-line ri-grey cursor-pointer']");
	}

	TestObject webNotLaunchableIcon(String label) {
		return createTestObject("Button_Icon", "xpath", "//td[normalize-space(text())='${label}']/../td//span[@class='ri-information-line cursor-pointer ng-scope']");
	}
}

