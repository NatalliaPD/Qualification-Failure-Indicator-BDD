package com.ewn.fw.lib

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.ewn.common.page.FileHelper
import com.ewn.common.page.Helper
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

public class BaseActions extends WebUiBuiltInKeywords {
	private static longTime = 30;

	/******************************************************
	 * Press a key without element
	 * @param key - The key to press
	 */
	static void pressKey(String key) {
		WebDriver _driver = DriverFactory.getWebDriver()
		Actions _pressKey = new Actions(_driver)
		_pressKey.sendKeys(key).build().perform()
	}

	/******************************************************
	 * Wait for Element present
	 * @param iTimeOut - the second time to wait
	 * @return true (Present) false (not present)
	 */
	static boolean waitForPresent(TestObject to, int iTimeOut) {
		WebDriver _driver = DriverFactory.getWebDriver();
		try {
			String _xpath = to.findPropertyValue("xpath");
			_driver.manage().timeouts().implicitlyWait(iTimeOut, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(_driver, iTimeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(_xpath)));
			_driver.manage().timeouts().implicitlyWait(longTime, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			_driver.manage().timeouts().implicitlyWait(longTime, TimeUnit.SECONDS);
			return false;
		}
	}

	/******************************************************
	 * Wait for Element not present
	 * @param iTimeOut : the second time to wait
	 * @return false (Present) true (not present)
	 */
	static boolean waitForNotPresent(TestObject to, int iTimeOut) {
		WebDriver _driver = DriverFactory.getWebDriver();
		try {
			String _xpath = to.findPropertyValue("xpath");
			_driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			int count = _driver.findElements(By.xpath(_xpath)).size();
			int newTime = iTimeOut;
			while(count>0 && newTime>0){
				--newTime;
				count = _driver.findElements(By.xpath(_xpath)).size();
				Thread.sleep(950);
			}
			_driver.manage().timeouts().implicitlyWait(longTime, TimeUnit.SECONDS);
			return count==0;
		} catch (Exception e) {
			_driver.manage().timeouts().implicitlyWait(longTime, TimeUnit.SECONDS);
			return true;
		}
	}

	static scrollUpAndClick(TestObject to) {
		WebElement _element = WebUiBuiltInKeywords.findWebElement(to);
		WebUiBuiltInKeywords.executeJavaScript('arguments[0].scrollIntoView(false);', Arrays.asList(_element));
		WebUiBuiltInKeywords.click(to);
	}

	// this function calculates EWN header's height and make sure that the target element is visible in viewport
	static scrollIntoEWNView(TestObject elementToScrollTo) {
		TestObject headerObject = createTestObject("Header", "css", ".main-header .container-fluid")
		int headerHeight = BaseActions.getElementHeight(headerObject)	
		int elementHeight = BaseActions.getElementHeight(elementToScrollTo)		
		WebElement targetElement = BaseActions.findWebElement(elementToScrollTo)	
		
		BaseActions.scrollToElement(elementToScrollTo, GlobalVariable.ShortTime)
		WebDriver _driver = DriverFactory.getWebDriver();
		((JavascriptExecutor) _driver).executeScript("window.scrollBy(0, -${headerHeight + elementHeight + 50});", targetElement); // buffer 50px
	}

	/******************************************************
	 * Get Color on Element
	 * @param attribute - the CSS attribute name: color; background-color; border-color
	 * @return the color (hex string)
	 */
	static String getColor(TestObject to, String attribute) {
		String _colorRGB = WebUiBuiltInKeywords.getCSSValue(to, attribute);
		String[] _arrRGB = _colorRGB.replace("(", ",").replace(")", "").split(",");
		String _hexFormat = String.format("#%02x%02x%02x", Integer.valueOf(_arrRGB[1].trim()),Integer.valueOf(_arrRGB[2].trim()),Integer.valueOf(_arrRGB[3].trim()));
		return _hexFormat.toLowerCase();
	}

	/******************************************************
	 * Verify Color on Element
	 * @param attribute - the CSS attribute name: color; background-color; border-color
	 * @param expectedColor - the color (hex string)
	 */
	static verifyColor(TestObject to, String attribute, String expectedColor) {
		String _currentColor = getColor(to, attribute);
		WebUiBuiltInKeywords.verifyEqual(_currentColor, expectedColor);
	}

	static TestObject createTestObject(String name, String attributeName, String attributeValue) {
		TestObject _dynamic = new TestObject(name);
		_dynamic.addProperty(attributeName, ConditionType.EQUALS, attributeValue);
		return _dynamic;
	}

	/******************************************************
	 * Click element by JavaScript
	 * @param clickType - click; doubleClick; rightClick; mouseOver; scroll
	 */
	static clickJS(TestObject to, clickType='click') {
		WebDriver _driver = DriverFactory.getWebDriver();
		WebElement _element = WebUiBuiltInKeywords.findWebElement(to);
		//Click on element
		switch(clickType) {
			case 'doubleClick': ((JavascriptExecutor) _driver).executeScript('arguments[0].dblclick();', _element);
				break
			case 'rightClick' : ((JavascriptExecutor) _driver).executeScript('arguments[0].contextmenu();', _element);
				break
			case 'mouseOver'  : ((JavascriptExecutor) _driver).executeScript('arguments[0].mouseover();', _element);
				break
			case 'scroll'     : ((JavascriptExecutor) _driver).executeScript('arguments[0].scrollIntoView();', _element);
				break
			case 'click'      : ((JavascriptExecutor) _driver).executeScript('arguments[0].click();', _element);
				break
		}
		KeywordUtil.markPassed("Object: '${to.getObjectId()}' is clicked JS on");
	}

	/******************************************************
	 * Verify image within Element
	 * @param expectedImageName - expected image file name (baseline in <projectPath>\Data Files\Pictures\)
	 * @param expectedStatus - expected status : true(found); false(not found)
	 */
	static verifyImageWithinElement(TestObject to, String imageName, expectedStatus=true) {
		Thread.sleep(2000); // waiting for ready
		FileHelper fileHelper = new FileHelper();
		String _pathPictures = fileHelper.getFileProjectPath("Data Files/Pictures/");
		String _fileName = _pathPictures + "Current/Image_" + new SimpleDateFormat('yyMMddhhmmss').format(new Date()) + ".png";
		String _imageBaseline = "${_pathPictures}Baseline/${imageName}";
		WebUiBuiltInKeywords.takeElementScreenshot(_fileName, to);
		fileHelper.waitFileExist(_fileName);
		boolean _currentImage = fileHelper.doesImageWithinAnotherImage(_imageBaseline, _fileName);
		if(_currentImage == expectedStatus) {
			KeywordUtil.markPassed("Object '${to.getObjectId()}' is present: ${_imageBaseline}");
			fileHelper.deleteFile(_fileName);
		} else WebUiBuiltInKeywords.verifyEqual("Object '${to.getObjectId()}' is not present: - Actual: ${_fileName} <> Expected: ${_imageBaseline}", "FAILED");
	}

	/******************************************************
	 * Draw character on element
	 * @param character - N or C character
	 */
	static draw(TestObject to, String character) {
		WebDriver _driver = DriverFactory.getWebDriver();
		WebElement _element = WebUiBuiltInKeywords.findWebElement(to);
		Actions _builder = new Actions(_driver);
		if (character == "C") {
			for(int x=1; x<=20; x++) {
				_builder.moveToElement(_element, x, 1).click().perform();
				_builder.moveToElement(_element, -x, 20).click().perform();
				_builder.moveToElement(_element, -x, x).click().perform();
			}
		} else {
			for(int y=1; y<=20; y++) {
				_builder.moveToElement(_element, 1, y).click().perform();
				_builder.moveToElement(_element, 20, y).click().perform();
				_builder.moveToElement(_element, y, y).click().perform();
			}
		}
		KeywordUtil.markPassed("Object '${to.getObjectId()}' is signatured : ${character}");
	}

	//////////////////// Checkbox ////////////////////////////
	/******************************************************
	 * Select Checkbox
	 * @param status - true (check); false (uncheck)
	 */
	static selectCheckbox(TestObject to, Object status=true) {
		WebElement _element = WebUiBuiltInKeywords.findWebElement(to);
		boolean _expectedStatus = status.toBoolean();
		String _status = (_expectedStatus)? "checked": "unchecked";
		if (_expectedStatus != _element.isSelected()) WebUiBuiltInKeywords.executeJavaScript('arguments[0].click();', Arrays.asList(_element));
		KeywordUtil.markPassed("Object '${to.getObjectId()}' is ${_status}");
	}

	static verifyCheckboxStatus(TestObject to, Object expectedStatus) {
		if (expectedStatus.toBoolean()) WebUiBuiltInKeywords.verifyElementChecked(to, longTime);
		else WebUiBuiltInKeywords.verifyElementNotChecked(to, longTime);
	}

	/******************************************************
	 * Verify Text content
	 */
	static verifyElementTextContent(TestObject to, String expectedText) {
		WebUiBuiltInKeywords.verifyEqual(WebUiBuiltInKeywords.getAttribute(to, "textContent").trim(), expectedText)
	}

	static verifyElementNotText(TestObject to, String expectedText) {
		WebUiBuiltInKeywords.verifyNotEqual(WebUiBuiltInKeywords.getAttribute(to, "textContent").trim(), expectedText)
	}

	static verifyElementTextContains(TestObject to, String expectedText) {
		String _currentText = WebUiBuiltInKeywords.getAttribute(to, "textContent")
		WebUiBuiltInKeywords.verifyEqual(_currentText.contains(expectedText), true)
	}

	static verifyElementTextNotContains(TestObject to, String expectedText) {
		String _currentText = WebUiBuiltInKeywords.getAttribute(to, "textContent")
		WebUiBuiltInKeywords.verifyEqual(_currentText.contains(expectedText), false)
	}

	static verifyElementAttributeContains(TestObject to, String attribute, String expectedText) {
		String _currentAttributeValue = WebUiBuiltInKeywords.getAttribute(to, attribute)
		WebUiBuiltInKeywords.verifyEqual(_currentAttributeValue.contains(expectedText), true)
	}

	static verifyElementAttributeNotContains(TestObject to, String attribute, String expectedText) {
		String _currentAttributeValue = WebUiBuiltInKeywords.getAttribute(to, attribute)
		WebUiBuiltInKeywords.verifyEqual(_currentAttributeValue.contains(expectedText), false)
	}

	///////////////////////////////////// List keyword /////////////////////////////////////////////
	/******************************************************
	 * Get list values (Support List Object only)
	 * @param attribute - The attribute name: textContent; outerHTML; clientWidth ; value ...
	 * @return list values
	 */
	static List<String> getAttributeOnList(TestObject listObj, String attribute) {
		List<String> _valueList = [];
		List<WebElement> _elements = WebUiBuiltInKeywords.findWebElements(listObj, longTime);
		for(WebElement element in _elements) {
			_valueList.add(element.getAttribute(attribute).trim());
		}
		return _valueList;
	}

	/******************************************************
	 * Verify list cell values (Support List Object only)
	 * @param expectedListValues - the expected list values
	 */
	static verifyListValues(TestObject listObj, List<String> expectedListValues) {
		WebUiBuiltInKeywords.verifyEqual(getAttributeOnList(listObj, "textContent").toString(), expectedListValues.toString());
	}

	/******************************************************
	 * Get list CSS item on List element (Support List Object only)
	 * @param attribute - the attribute of element: color; border-left-color; background-color; ....
	 * @return list attribute of each elements
	 */
	static List<String> getCSSOnList(TestObject listObj, String attribute) {
		List<String> _valueList = [];
		List<WebElement> _elements = WebUiBuiltInKeywords.findWebElements(listObj, longTime);
		for(WebElement element in _elements) {
			_valueList.add(element.getCssValue(attribute));
		}
		return _valueList;
	}

	/******************************************************
	 * Find item index on list by attribute (Support List Object only)
	 * @param attributeName - The attribute name to find
	 * @param attributeValue - The attribute value to find
	 * @return The index number
	 */
	static Integer findItemIndexOnListByAttribute(TestObject listObj, String attributeName, String attributeValue) {
		int _findIndex = 0;
		int _Count = 0;
		for (WebElement elementItem: WebUiBuiltInKeywords.findWebElements(listObj, longTime)) {
			_Count++;
			if (elementItem.getAttribute(attributeName).trim() == attributeValue) {
				_findIndex = _Count;
				break;
			}
		}
		return _findIndex;
	}

	/******************************************************
	 * Select item by index on List Elements (Support List Object only)
	 * @param index - the number index to select
	 */
	static selectListByIndex(TestObject listObj, int index) {
		List<WebElement> _elements = WebUiBuiltInKeywords.findWebElements(listObj, longTime);
		_elements.get(index -1).click();
	}

	/******************************************************
	 * Select list item by text (Support List Object only)
	 * @param text - The text of item to select
	 */
	static selectListItemByText(TestObject listObj, String text) {
		String _xpath = String.format("%s[normalize-space(.)='%s']", listObj.findPropertyValue("xpath"), text);
		WebUiBuiltInKeywords.click(createTestObject("${text}_Item", "xpath", _xpath));
	}

	/******************************************************
	 * Count item on List (Support List Object only)
	 * @return The total number of items
	 */
	static Integer countVisibleItemsOnList(TestObject listObj) {
		return WebUiBuiltInKeywords.findWebElements(listObj, longTime).findAll{it.isDisplayed()}.size();
	}

	/******************************************************
	 * Get attribute item at index on List (Support List Object only)
	 * @param attribute - The attribute name to get
	 * @param index - The index to select
	 * @return The attribute value at item index
	 */
	static String getAttributeItemIndexOnList(TestObject listObj, String attribute, Object index) {
		String _xpath = String.format("(%s)[%s]", listObj.findPropertyValue("xpath"), index);
		return WebUiBuiltInKeywords.getAttribute(createTestObject("${index}_Item", "xpath", _xpath), attribute);
	}

	/******************************************************
	 * Verify list values sort order (Support List Object only)
	 * @param dataType - number; string; dateFormat(yyyy-MM-dd hh:mm; dd/MM/yyyy;...)
	 * @param expectedStatus - normal; descending; ascending
	 */
	static verifyListSortOrder(TestObject listObj, String dataType, String expectedStatus="ascending") {
		List<String> currentListValues = getAttributeOnList(listObj,"textContent");
		String _currentStatus = new Helper().getSortOrderStatus(currentListValues, dataType);
		KeywordUtil.logInfo("verifyListSortOrder:: listValue${currentListValues.toString()} current[${_currentStatus}] expected[${expectedStatus}]");
		WebUiBuiltInKeywords.verifyEqual(_currentStatus.contains(expectedStatus), true);
	}

	///////////////////////////////////// Table /////////////////////////////////////////////
	/******************************************************
	 * Get content of row (Support Table Object only)
	 * @param range - The index column range: Ex: 1..5
	 * @return content of row
	 */
	private static String getRowContent(String xpathRow, Object range) {
		List<String> _valueList = [];
		range.each {
			TestObject _IconObject = createTestObject("${it}_Icon", "xpath", "${xpathRow}/td[${it}]//span[contains(@class,'ri-')]");
			if (waitForPresent(_IconObject, 1))  _valueList.add(WebUiBuiltInKeywords.getAttribute(_IconObject, "class"));
			else {
				String _text = WebUiBuiltInKeywords.getAttribute(createTestObject("${it}_Column", "xpath", "${xpathRow}/td[${it}]"), "textContent").trim();
				if(_text != "") _valueList.add(_text);
			}
		}
		return _valueList.join(";");
	}

	/******************************************************
	 * Get content of row (Support Table Object only)
	 * @param range - The index column range: Ex: 1..5
	 * @return list values delimiter by ";" (Ex: value1;value2;ri-check-line;ri-alert-line ri-orange)
	 */
	static String getRowContentOnTable(TestObject tableRow, Object range) {
		String _xpathRow = tableRow.findPropertyValue("xpath");
		return getRowContent(_xpathRow, range);
	}

	/******************************************************
	 * Get list Rows content (Support List Object only)
	 * @param range - The index column range: Ex: 1..5
	 * @return list content values
	 */
	static List<String> getListRowsContentOnTable(TestObject listRowsObj, Object range) {
		List<String> _valueList = [];
		int _totalRow = WebUiBuiltInKeywords.findWebElements(listRowsObj, longTime).size();
		String _xpathRows = listRowsObj.findPropertyValue("xpath");
		for(int i=1; i<=_totalRow; i++) {
			_valueList.add(getRowContent("${_xpathRows}[${i}]", range));
		}
		return _valueList;
	}

	/******************************************************
	 * Get selected option value
	 * @param to - The select test object
	 * @return value of selected option
	 */
	static String getValueOfSelectedOption(TestObject to) {
		WebElement _element = BaseActions.findWebElement(to);
		List<WebElement> _listE = _element.findElements(By.xpath("./child::option"))
		String optionValue = ""
		for(WebElement e in _listE) {
			if(e.isSelected()) {
				optionValue = e.getText()
			}
		}
		return optionValue
	}

	static dragAndDrop(TestObject sourceObject, TestObject destinationObject) {
		WebElement sourceElement = WebUiBuiltInKeywords.findWebElement(sourceObject);
		WebElement destinationElement = WebUiBuiltInKeywords.findWebElement(destinationObject);
		WebDriver webDriver = DriverFactory.getWebDriver();
		((JavascriptExecutor) webDriver).executeScript(getJsDndHelper() + "simulateDragDrop(arguments[0], arguments[1])", sourceElement, destinationElement);
	}
	
	/******************************************************
	 * Navigate browser to back or forward
	 * @param navigateType - 'back' or 'forward'
	 * @return None
	 */
	static browserNavigation(String navigateType) {
		switch(navigateType.toLowerCase()) {
			case "back":
				WebUiBuiltInKeywords.back()
				break;
			case "forward":
				WebUiBuiltInKeywords.forward()
				break;
			default:
				throw new Exception("Navigation type ${navigateType} is unknown")
				break;
		}
	}
	
	private static String getJsDndHelper() {
		return '''
function simulateDragDrop(sourceNode, destinationNode) {
var EVENT_TYPES = {
DRAG_END: 'dragend',
DRAG_START: 'dragstart',
DROP: 'drop'
}
 
function createCustomEvent(type) {
var event = new CustomEvent("CustomEvent")
event.initCustomEvent(type, true, true, null)
event.dataTransfer = {
data: {
},
setData: function(type, val) {
this.data[type] = val
},
getData: function(type) {
return this.data[type]
}
}
return event
}
 
function dispatchEvent(node, type, event) {
if (node.dispatchEvent) {
return node.dispatchEvent(event)
}
if (node.fireEvent) {
return node.fireEvent("on" + type, event)
}
}
 
var event = createCustomEvent(EVENT_TYPES.DRAG_START)
dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, event)
 
var dropEvent = createCustomEvent(EVENT_TYPES.DROP)
dropEvent.dataTransfer = event.dataTransfer
dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent)
 
var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END)
dragEndEvent.dataTransfer = event.dataTransfer
dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent)
}
 
''';
	}
	
	static void openNewTab() {
		WebUiBuiltInKeywords.executeJavaScript("window.open('','_blank');", [])
	}
	
	static void switchToTab(int tabIndex) {
		WebUiBuiltInKeywords.switchToWindowIndex(tabIndex)
	}
	
	static int getCurrentWindowIndex() {
		return WebUiBuiltInKeywords.getWindowIndex()
	}
	
	static void openNewTabAndNavigateTo(String URL) {
		int currentIndex = getCurrentWindowIndex()
		openNewTab()
		switchToTab(currentIndex + 1)
		navigateToUrl(URL)
	}
}
