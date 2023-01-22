package com.ewn

import static groovy.json.JsonParserType.LAX as RELAX

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.ewn.common.page.FileHelper

import groovy.json.JsonSlurper
import internal.GlobalVariable

public class ExecutiveDashboardPage extends BasePage<ExecutiveDashboardPage> {

	//////////////////// Button ////////////////////////////
	ExecutiveDashboardPage clickApply(int index=1) {
		BaseActions.click(webButton("Apply", index))
		return this
	}

	ExecutiveDashboardPage selectKPIBoards(String KPI) {
		BaseActions.clickJS(webLabel("KPI Boards"))
		selectRadio(KPI)
		return this
	}

	ExecutiveDashboardPage clickFiltersOffButton(String chartName) {
		if(chartName == "Pass/Fail Rates") {
			clickFiltersOffButton_PassFailChart()
			return this
		}
		String css = "[header='${chartName}'] .chart-filter-icon"
		BaseActions.click(BaseActions.createTestObject("Filters Off Button", "css", css))
		return this
	}

	private ExecutiveDashboardPage clickFiltersOffButton_PassFailChart() {
		String _xpath = "//span[normalize-space(.)='Pass/Fail Rates: All']/ancestor::*[contains(@class,'chart-overall-container')]//span[contains(@class,'filter-3-line')]"
		BaseActions.click(BaseActions.createTestObject("Filters Off Button", "xpath", _xpath))
	}

	ExecutiveDashboardPage clickGroupDropdownButton(String chartName) {
		String css = "[header='${chartName}'] .dropdown-toggle"
		TestObject to = BaseActions.createTestObject("Group Dropdown Button", "css", css)
		if(!BaseActions.waitForElementPresent(to, GlobalVariable.ShortTime)) {
			clickGroupDropdownButtonByChartName(chartName)
			return this
		}
		BaseActions.click(to)
		return this
	}
	
	private ExecutiveDashboardPage clickGroupDropdownButtonByChartName(String chartName) {
		String _xpath = "//span[contains(normalize-space(.),'${chartName}')]/ancestor::*[contains(@class,'chart-overall-container')]//div[@class='btn-group dropdown']"
		BaseActions.click(BaseActions.createTestObject("Group Dropdown Button", "xpath", _xpath))
	}

	ExecutiveDashboardPage clickPaginationButton(int recordPerPage) {
		BaseActions.click(webButton(recordPerPage as String))
		pleaseWait()
		return this
	}

	//////////////////// Combobox ////////////////////////////
	ExecutiveDashboardPage selectMultipleOptionCompany(String items) {
		this.selectMultipleOption("Company", items)
		return this
	}

	ExecutiveDashboardPage selectOptionCovered(String item) {
		BaseActions.selectOptionByLabel(webCombobox("Covered", 1), item, true)
		return this
	}

	ExecutiveDashboardPage selectOptionTaskList(String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task List", 1), item, true)
		return this
	}

	ExecutiveDashboardPage selectOptionOrganizationalTools(String item) {
		BaseActions.selectOptionByLabel(webCombobox("Organizational Tools", 1), item, true)
		return this
	}

	ExecutiveDashboardPage selectMultipleOptionTasks(String items) {
		this.selectMultipleOption("Tasks", items)
		return this
	}

	ExecutiveDashboardPage selectOptionPeriodTime(String chartName, String item) {
		String _xpath = "//span[normalize-space(.)='${chartName}']/ancestor::*[contains(@class,'chart-overall-container')]//select[@id='uxDdlOrgToolType']"
		TestObject _to = BaseActions.createTestObject("Period Time", "xpath", _xpath)
		BaseActions.selectOptionByLabel(_to, item, false)
		return this
	}

	ExecutiveDashboardPage selectOptionOrgTools(String item) {
		BaseActions.selectOptionByLabel(webCombobox("Organizational Tools", 1), item, true)
		return this
	}

	ExecutiveDashboardPage selectOptionOrgToolName(String item) {
		BaseActions.selectOptionByLabel(webCombobox("Organizational Tool Name", 1), item, true)
		return this
	}

	ExecutiveDashboardPage selectMultipleOptionProjectOwner(String items) {
		this.selectMultipleOption("Project Owner", items)
		return this
	}

	////////////////////////////Menu Item//////////////////////////////

	ExecutiveDashboardPage selectChartContextMenuItem(String chartName, String menuItemToClick) {
		String css = "[header='${chartName}'] .dropdown-menu a"
		String _xpath = "//span[contains(normalize-space(.),'${chartName}')]/ancestor::*[contains(@class,'chart-overall-container')]//ul[starts-with(@class,'dropdown-menu')]//a[normalize-space(.)='${menuItemToClick}']"
		TestObject menuItemObject = BaseActions.createTestObject("Chart Item", "css", css)
		List<WebElement> menuItems = BaseActions.findWebElements(menuItemObject, GlobalVariable.ShortTime)
		if(menuItems.size() == 0) {
			menuItemObject = BaseActions.createTestObject("Chart Item", "xpath", _xpath)
			menuItems = BaseActions.findWebElements(menuItemObject, GlobalVariable.ShortTime)
		}
		WebElement menuItem = menuItems.find{it.getText().contains(menuItemToClick)}
		if(menuItem) {
			menuItem.click()
			if(menuItemToClick.contains("Export")) downloadFile()
		}
		else  throw new StepFailedException("Unable to find menu item ${menuItemToClick}")
		return this
	}
	
	//////////////////////////// Label /////////////////////////////////

	String getDataCardValue(String cardName) {
		TestObject cardValueObject = BaseActions.createTestObject(cardName, "css", "[header='${cardName}'] .data-card-value")
		return BaseActions.getText(cardValueObject)
	}

	////////////////////////////Verify/////////////////////////////////
	ExecutiveDashboardPage verifyChartValue(String chartName, String value) {
		String _xpath = "//div[contains(@class,'data-card-header')][text()='${chartName}']//following-sibling::div[@class='data-card-value']"
		String _actualValue = BaseActions.getText(BaseActions.createTestObject("Chart Value", "xpath", _xpath))
		BaseActions.verifyEqual(_actualValue, value)
		return this
	}

	ExecutiveDashboardPage verifyChartTable(String chartTableName, String columnValue1="", String columnValue2="", String columnValue3="", String columnValue4="", String columnValue5="", int index=1) {
		Pages.As(OnDataTable).setTable(chartTableName, index).verifyTableRow("${columnValue1};${columnValue2};${columnValue3};${columnValue4};${columnValue5}")
		return this
	}

	ExecutiveDashboardPage verifyTotalRecords(int index = 1, Object expectedRecords) {
		BaseActions.verifyElementText(webTotalRecords(index), expectedRecords.toString())
		return this
	}

	ExecutiveDashboardPage verifyExportFileSuccessfully(String fileName) {
		def _exportFile = new File(fileName)
		BaseActions.verifyEqual(_exportFile.exists(), true)
		BaseActions.verifyNotEqual(_exportFile.size(), 0)
		return this
	}

	ExecutiveDashboardPage verifyTableRecords(String tableName, List<String> itemToVerify) {
		Pages.As(OnDataTable).setTable(tableName).verifyTableRow(itemToVerify.join(";"))
		return this
	}

	ExecutiveDashboardPage verifyDownloadedPDFFile(String downloadedFilePath, String expectedContent) {
		FileHelper helper = new FileHelper()
		helper.verifyFileContent(downloadedFilePath, expectedContent)
		return this
	}

	//////////////////////////// Chart data /////////////////////////////////

	String getChartData(ChartDataType dataType, String customFilter = '') {
		// get any canvas on the page
		TestObject canvasObject = BaseActions.createTestObject("Canvas chart", "css", "canvas")
		WebElement chartElement = BaseActions.findWebElement(canvasObject, GlobalVariable.ShortTime)

		WebDriver _driver = DriverFactory.getWebDriver()
		return ((JavascriptExecutor) _driver).executeScript("return angular.element(arguments[0]).controller().chartData.${dataType as String}.getData(${customFilter})", chartElement)
	}

	Object getDataChartEmployeeStatus() {
		return new JsonSlurper().setType(RELAX).parseText(getChartData(ChartDataType.employeeStatus).replace("=", ":"))
	}

	Object getFilterAssignedTasksStatusChartData(String taskListId, List<String> taskIds) {
		String query = "true, {'companyIds':[],'isCovered':null,'taskListId':${taskListId},'taskIds':${taskIds}}"
		return new JsonSlurper().setType(RELAX).parseText(getChartData(ChartDataType.assignedTasksStatus, query).replace("=", ":"))
	}

	enum ChartDataType {
		activeUsers,
		activeSuspensions,
		assignedTasksStatus,
		assignedTasksStatusConnectedCompanies,
		completedTasks,
		connectedCompanies,
		connectedCompaniesVsQualifications,
		currentlySuspendedIndividualsByCompany,
		currentMasterEvaluatorCertificates,
		employeesByOrganizationalTool,
		employeesExpiringByOrganizationalTool,
		employeeStatus,
		employeeToEvaluatorRatio,
		evaluationsTakenByEmployees,
		evaluatorsBySkillFamily,
		individualsByProject,
		individualsExpiringByProject,
		masterEvaluatorCertificateStatus,
		onlineTestingTime,
		passFailRates,
		performanceEvaluationsByEvaluator,
		qualifiedIndividuals,
		totalEvaluators
	}

	//////////////////////////// Record Page /////////////////////////////////

	int countRecordPerPage(String tableName, String recordToCount) {
		return Pages.As(OnDataTable).setTable(tableName).countTableRows(recordToCount)
	}

	int getCurrentPage() {
		TestObject paginationObject = BaseActions.createTestObject("Pagination Object", "css", ".pagination a")
		List<WebElement> paginationElements = BaseActions.findWebElements(paginationObject, GlobalVariable.ShortTime)

		WebElement currentPageElement = paginationElements.tail().find { it.getCssValue("cursor") == "not-allowed" }
		return currentPageElement.getText() as Integer
	}

	ExecutiveDashboardPage clickPageNumber(int pageNumber) {
		TestObject paginationObject = BaseActions.createTestObject("Pagination Object", "css", ".pagination a")
		List<WebElement> paginationElements = BaseActions.findWebElements(paginationObject, GlobalVariable.ShortTime)

		WebElement pageElement = paginationElements.find { it.getText() == pageNumber as String }
		pageElement.click()
		return this
	}

	int countRecordOnAllPages(String tableName, String recordToCount) {
		this.clickPaginationButton(100)

		int countItem = 0
		for (int page = 1; page <= totalPageCount(); page++) {
			if(getCurrentPage() != page) clickPageNumber(page)
			countItem += countRecordPerPage(tableName, recordToCount)
		}
		return countItem
	}

	int totalPageCount() {
		TestObject paginationObject = BaseActions.createTestObject("Pagination Object", "css", ".pagination a")
		List<WebElement> paginationElements = BaseActions.findWebElements(paginationObject, GlobalVariable.ShortTime)

		return paginationElements.size() - 2 // minus previous and next
	}

	int getTotalRecordsOnPage() {
		TestObject totalRecord = BaseActions.createTestObject("Total Record", "css", ".ewn-blue > span.ng-binding")
		return BaseActions.getText(totalRecord) as Integer
	}

	ExecutiveDashboardPage verifyRecordsNotEmpty() {
		String _totalRecords = getTotalRecordsOnPage()
		BaseActions.verifyNotEqual(_totalRecords, 0)
		return this
	}
}
