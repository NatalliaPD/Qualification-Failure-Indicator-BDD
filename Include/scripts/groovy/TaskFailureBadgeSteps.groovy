import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class TaskFailureBadgeSteps {

	@Given("Admin user logs in (.*) and (.*)")
	def LoginToAccount (String username, String password) {
		WebUI.callTestCase(findTestCase('Login'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	@And ("Admin User cicks on Reports")
	def ClickOnReport () {
		WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/span_Reports'))
		WebUI.click(findTestObject('OrgToolReport/Page_Energy Worldnet/div_Organizational Tool Status'))
	}

	@Then ("should select View Results for employees of a Connected Company")
	def SelectConnectedCompany () {
		WebUI.selectOptionByValue(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/select_NTC (by Natallia) Natallia-Contracto_786096'),
				'number:6162', true)
	}

	@And ("selects available Project")
	def SelectProject () {
		WebUI.click(findTestObject('OrgToolReport/Page_Energy Worldnet/input_Report View_form-control ng-pristine ng-untouched ng-valid ng-empty'))
		WebUI.setText(findTestObject('OrgToolReport/Page_Energy Worldnet/input_Report View_form-control ng-pristine ng-untouched ng-valid ng-empty'),
				'Some_LCMS-86')
		WebUI.check(findTestObject('CheckboxinOrgTools/CheckBox In Available Org Tools'))
		WebUI.click(findTestObject('Elems/Page_Energy Worldnet/div_Quantum-Operator LLC (by Natallia)  - Work_Project_btn btn-sm btn-default ri-arrow-right-s-line'))
	}

	@And ("selects employees")
	def SelectEmployees () {

		WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/a_Advanced Employee Filters'))
		WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/input_Filter_pull-right ng-pristine ng-unto_0c5e03'))
		WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/div_Pickles, Ollie - EWN-285207_btn btn-sm _f9d84a'))
	}

	@And ("presses Run Report button")
	def PressRunReport () {
		WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/button_Run Report'))
	}

	@When("Admin user clicks Expand All button")
	def ClickExpandAll () {
		WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/span_Expand All'))
	}

	@Then("Task failure badge should be displayed on failed tasks")
	def FindTaskFailureBadge () {
		println status ("\n Qualification Failure")
		WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/span_2Conditions (10)'))
		WebUI.verifyElementPresent(findTestObject('OrgToolReport/Page_Energy Worldnet/span_Failed Qualification'), 3, FailureHandling.STOP_ON_FAILURE)

		WebUI.closeBrowser()
	}
}