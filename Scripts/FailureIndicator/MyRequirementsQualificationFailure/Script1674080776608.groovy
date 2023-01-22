import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.navigateToUrl(url)

WebUI.setText(findTestObject('Object Repository/PrintSubmit_MyReq/Page_Energy Worldnet Login/input_Username_username'), 
    username)

WebUI.setEncryptedText(findTestObject('Object Repository/PrintSubmit_MyReq/Page_Energy Worldnet Login/input_Password_password'), 
    password)

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/Page_Energy Worldnet Login/button_Log In'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/Print_SubmitEvaluations ModuleName'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/Submit_Evaluations_Submodule'))

WebUI.setText(findTestObject('PrintSubmit_MyReq/PrintSubmitEvals_MyReq/SearchAvailableEmployeeField'), employee)

WebUI.waitForElementVisible(findTestObject('PrintSubmit_MyReq/PrintSubmitEvals_MyReq/EmployeeCheckbox'), 3)

WebUI.check(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/EmployeeCheckbox'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/MoveRightArrow1Employee'))

WebUI.selectOptionByValue(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/EvaluationAuthor'), 
    'number:2', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/EvaluationType'), 'number:2', 
    true)

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/Filter'))

WebUI.setText(findTestObject('PrintSubmit_MyReq/PrintSubmitEvals_MyReq/SearchAvailableEvaluationField'), evaluation)

WebUI.click(findTestObject('PrintSubmit_MyReq/PrintSubmitEvals_MyReq/EvaluationCheckbox'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/MoveRightArrow2Evaluation'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/DatePicker'))

WebUI.setText(findTestObject('PrintSubmit_MyReq/PrintSubmitEvals_MyReq/Page_Energy Worldnet/Input_Evaluation Date'), '01/03/2023')

WebUI.selectOptionByValue(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/Training Status _914866'), 
    'Passed', true)

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/EvaluatorNhung, test (3rd Party)'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/Preview'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/button_Save'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/span_Save_ri-close-line'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/span_My Requirements'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/span_MRA-010_Task (11)'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/span_MRA-010_Task (11)_ri-checkbox-circle-f_673c6c'))

WebUI.click(findTestObject('Object Repository/PrintSubmit_MyReq/PrintSubmitEvals_MyReq/span_01022026'))

not_run: WebUI.closeBrowser()

