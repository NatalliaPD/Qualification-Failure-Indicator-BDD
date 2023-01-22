import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class BaseTest {
	
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		WebUI.openBrowser('')
		
		WebUI.maximizeWindow()
		
		WebUI.navigateToUrl('https://test-auth.energyworldnet.com/static/login?redirect_uri=%2Fconnect%2Fauthorize%3Fresponse_type%3Dcode%2520id_token%26response_mode%3Dform_post%26client_id%3DD612BD2A-ADC1-4F8F-B161-7E9D082F7042%26scope%3Dopenid%2520offline_access%26redirect_uri%3Dhttps%253A%252F%252Ftest.energyworldnet.com%252FPROIINET%252FApiProxy%252FLogin%26state%3DeyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZnAiOiJZNjJpbFBXSkxmNG9QbXR1ZnpqVzF5RmlaWE5VaVpITkNYTC9XWFVBTTJJPSIsInRhcmdldF9saW5rX3VyaSI6Imh0dHBzOi8vdGVzdC5lbmVyZ3l3b3JsZG5ldC5jb20vUFJPSUlORVQvVGFza1N0YXR1cyIsIm5iZiI6MTY3Mjk1MDkyMCwiZXhwIjoxNjczMDM3MzIwLCJpYXQiOjE2NzI5NTA5MjB9.V6ETTKuaI6A9HeOyVFgLVmBeXce7MIIr_sPzyCcnkubEuQNLFgnpBnwcHTPYjoHiYFfK7oZ6qZB6U-YSr9nQ4dLRTbyGRi9ntKg_5UIxDNy8dTedswMjw-ojTmtwsve4PXeR-nqt6KBOT1knmhwbEd51TLETlGRFfE059nZNqdIn3QnQYwOfx4rpiac7ng_fRiRQvcBwCj4pkmW-FFe0wJnUSWUBWOMEFEiF4WF4N-KpM4k2CakuAYG42JYhist0eePHaUc5xrLefsYrZdhWiyOMrsyOjoCMp3MftKAWcO-J4_piK35i5-__5khYjGuCgpXMySCcGrfnYmNQj_Iqcg%26nonce%3DY62ilPWJLf4oPmtufzjW1yFiZXNUiZHNCXL%252FWXUAM2I%253D%26max_age%3D1800')
		
	}

	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		WebUI.closeBrowser()
	}
}