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

WebUI.callTestCase(findTestCase('Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/span_Reports'))

WebUI.click(findTestObject('OrgToolReport/Page_Energy Worldnet/div_Organizational Tool Status'))

WebUI.selectOptionByValue(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/select_NTC (by Natallia) Natallia-Contracto_786096'), 
    'number:6162', true)

WebUI.click(findTestObject('OrgToolReport/Page_Energy Worldnet/input_Report View_form-control ng-pristine ng-untouched ng-valid ng-empty'))

WebUI.setText(findTestObject('OrgToolReport/Page_Energy Worldnet/input_Report View_form-control ng-pristine ng-untouched ng-valid ng-empty'), 
    'Some_LCMS-86')

WebUI.check(findTestObject('CheckboxinOrgTools/CheckBox In Available Org Tools'))

WebUI.click(findTestObject('Elems/Page_Energy Worldnet/div_Quantum-Operator LLC (by Natallia)  - Work_Project_btn btn-sm btn-default ri-arrow-right-s-line'))

WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/a_Advanced Employee Filters'))

WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/input_Filter_pull-right ng-pristine ng-unto_0c5e03'))

WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/div_Pickles, Ollie - EWN-285207_btn btn-sm _f9d84a'))

WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/button_Run Report'))

WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/span_Expand All'))

WebUI.click(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/span_2Conditions (10)'))

not_run: WebUI.verifyElementVisible(findTestObject('Object Repository/OrgToolReport/Page_Energy Worldnet/span_Failed Qualification'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('OrgToolReport/Page_Energy Worldnet/span_Failed Qualification'), 3, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

