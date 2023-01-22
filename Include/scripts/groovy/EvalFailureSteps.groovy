import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import internal.GlobalVariable

public class EvalFailureSteps {


	@Given ('Admin user logs in')
	def OpenLoginPage (String username, String password) {
		println ("\n On EWN Login Page")
		WebUI.callTestCase(findTestCase('Login'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	@When ("Admin user navigates to Report")
	def NavigateToStatusReport () {
		println ("\n Accessing Task Status Report")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/a_Reports'))
	}

	@And ("clicks Status")
	def ClickStatus () {
		println ("\n Status of Task")
		WebUI.click(findTestObject('StatusReport/Page_Energy Worldnet/div_View the task or evaluation status of users'))
	}

	@And ("selects Task List")
	def SelectTaskList () {
		println ("\n Selecting Task List")
		WebUI.selectOptionByValue(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/select_Pipeline Inspector ProgramBlack Hill_6c4e1c'),
				'number:3486', true)
	}

	@And ("selects employee")
	def SelectEmployee () {
		println ("\n Selecting Employee")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_Admin, Adrian - EWN-596338'))
	}

	@And ("moves employee to Available")
	def MoveEmployeeToAvailable () {
		println ("\n Moving Selected Employee to Available")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_Carrera-Car, Sally - EWN-613346_btn btn_83e5d1'))
	}

	@And ("expands Task Filters")
	def ClickTaksFilters () {
		println ("\n Available Tasks display")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/a_Advanced Task Filters'))
	}
	@Then ("should be able to select a task")
	def SelectTask () {
		println ("\n Selecting Task")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_A199 PE_Cond'))
	}

	@And ("moves task to Available")
	def MoveTaskAvailable () {
		println ("\n Task to Available")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_WE_Non-Exp_btn btn-sm btn-default ri-ar_d49a9a'))
	}
	@And ("runs Task Status Report")
	def RunTaskStatusReport () {
		println ("\n Clicking Run Report")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/button_Run Report'))
	}

	@And ("expands task")
	def ClickOnTask () {
		println ("\n Opening Task")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/td_01032023'))
	}

	@Then ("clicks on evaluation")
	def ClickEvaluation () {
		println ("\n On failed Evaluation")
		WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/td_01052023'))
	}

	@Then ("Evaluation failure date should be the new Valid Until date in red")
	def FindEvaluationFailureIndicator () {
		println ("\n Red Failure Date")
		WebUI.verifyElementPresent(findTestObject('StatusReport/Page_Energy Worldnet/td_01052023'), 5)
		WebUI.closeBrowser()
	}
}
