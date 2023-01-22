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

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/a_Reports'))

WebUI.click(findTestObject('StatusReport/Page_Energy Worldnet/div_View the task or evaluation status of users'))

WebUI.selectOptionByValue(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/select_Pipeline Inspector ProgramBlack Hill_6c4e1c'), 
    'number:3486', true)

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_Admin, Adrian - EWN-596338'))

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_Carrera-Car, Sally - EWN-613346_btn btn_83e5d1'))

WebUI.click(findTestObject('StatusReport/Page_Energy Worldnet/a_Advanced Task Filters'))

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_A199 PE_Cond'))

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/div_WE_Non-Exp_btn btn-sm btn-default ri-ar_d49a9a'))

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/button_Run Report'))

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/td_01032023'))

WebUI.click(findTestObject('Object Repository/StatusReport/Page_Energy Worldnet/td_01052023'))

WebUI.verifyElementPresent(findTestObject('StatusReport/Page_Energy Worldnet/td_01052023'), 5)

