
package com.ewn.account.management
import org.openqa.selenium.support.Color;

import com.ewn.common.page.BasePage
import com.ewn.common.page.FileHelper
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.testobject.TestObject

public class UsersAdministrationPage extends BasePage<UsersAdministrationPage>{

	//////////////////// Button ////////////////////////////
	UsersAdministrationPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	UsersAdministrationPage clickSave(int index=1) {
		BaseActions.clickJS(webButton("Save", index))
		return this
	}
	
	UsersAdministrationPage clickVisibleSaveButton() {
		BaseActions.enhancedClick(webVisibleButton("Save"))
		return this
	}
	
	UsersAdministrationPage clickUpload(int index=1) {
		BaseActions.click(webButton("Upload", index))
		return this
	}
	
	UsersAdministrationPage scrollUpAndClickSave(int index=1) {
		BaseActions.scrollUpAndClick(webButton("Save", index))
		return this
	}

	UsersAdministrationPage clickEdit(int index=1) {
		BaseActions.clickJS(webButton("Edit", index))
		return this
	}
	
	UsersAdministrationPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}
	
	UsersAdministrationPage clickAdd(int index=1) {
		BaseActions.click(webButton("Add", index))
		return this
	}
	
	EvaluationRequirementsPage clickDelete(int index=1) {
		BaseActions.click(webButton("Delete", index))
		return this
	}

	UsersAdministrationPage clickConvertToUser(int index=1) {
		BaseActions.click(webButton("Convert to User", index))

		return this
	}

	UsersAdministrationPage clickSendPasswordResetEmail(int index=1) {
		TestObject webButtonRestPassword = BaseActions.createTestObject("Button", "xpath", "//button[@class='btn button-primary btn-sm ng-scope'][${index}]")
		BaseActions.click(webButtonRestPassword)
		return this
	}
	
	UsersAdministrationPage clickButtonDelete(int index=1) {
		String attributeButtonAdd =   BaseActions.getAttribute(webButtonIcon("User Roles:", 2), "class")
		if(attributeButtonAdd == "btn ri-delete-bin-line ng-scope") {
			BaseActions.clickJS(webButtonIcon("User Roles:", 2))
		}
		return this
	}
	
	UsersAdministrationPage clickButtonPlusAdd(int index=1) {
		clickButtonDelete()
		TestObject webButtonPlusAdd = BaseActions.createTestObject("Button", "xpath", "//span[@class='btn ri-add-line text-success'][${index}]")
		BaseActions.click(webButtonPlusAdd)
		return this
	}
	
	UsersAdministrationPage clickEditButton(int index=1) {
		TestObject webButtonEdit = BaseActions.createTestObject("Button", "xpath", "(//div[@class='pull-right']//button[@class='btn button-secondary'])[${index}]")
		BaseActions.clickJS(webButtonEdit)
		return this
	}
	
	UsersAdministrationPage clickSunIcon(String label , int index=1) {
		BaseActions.click(webButtonIcon(label , index))
		return this
	}
	
	
	UsersAdministrationPage clickCheckBoxAssignCompany(int index=1) {
		TestObject webCheckBoxAssignCompany = BaseActions.createTestObject("Button", "xpath", "(//legend[.='Assign Proctor to External Company']/following::*[self::input or self::label])[${index}]")
		BaseActions.clickJS(webCheckBoxAssignCompany)
		return this
	}
	
	UsersAdministrationPage clickButtonArrowLeftAssignCompany(int index=1) {
		TestObject webButtonArrowLeft = BaseActions.createTestObject("Button", "xpath", "//span[text()='Selected Companies']/following::div[@class='btn btn-sm btn-default ri-arrow-left-s-line ng-scope'][${index}]")
		BaseActions.clickJS(webButtonArrowLeft)
		return this
	}
	
	UsersAdministrationPage inputNameCompanies(String label , String listItem , int index =1) {
		TestObject webSearchAssignCompany = BaseActions.createTestObject("${label}_AvailableCompanies", "xpath", "//legend[.='${label}']/following::*[self::input or self::label][${index}]")
		BaseActions.setText(webSearchAssignCompany, listItem)
		return this
	}

	//////////////////// Combobox ////////////////////////////
	UsersAdministrationPage selectOptionJobTitle(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Job Title:", index), item, true)
		return this
	}

	UsersAdministrationPage selectOptionProject(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Project:", index), item, true)
		return this
	}
	
	UsersAdministrationPage selectOptionUserRoles(int index=1, String item) {
		
		BaseActions.selectOptionByLabel(webCombobox("User Roles:", index), item, true)
		
		return this
	}

	UsersAdministrationPage selectOptionTestingPool(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Testing Pool:", index), item, true)
		return this
	}

	UsersAdministrationPage selectOptionUserType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("User Type:", index), item, true)
		return this
	}
	
	UsersAdministrationPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	UsersAdministrationPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}
	
	UsersAdministrationPage selectOptionUserStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("User Status:", index), item, true)
		return this
	}

	UsersAdministrationPage selectOptionHasPhoto(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Has Photo:", index), item, true)
		return this
	}

	UsersAdministrationPage selectMultipleOptionFacility(String items) {
		this.selectMultipleOption("Facility:", items)
		return this
	}

	UsersAdministrationPage selectMultipleOptionGroup(String items) {
		this.selectMultipleOption("Group:", items)
		return this
	}

	UsersAdministrationPage selectMultipleOptionSupervisor(String items) {
		this.selectMultipleOption("Supervisor:", items)
		return this
	}

	UsersAdministrationPage selectMultipleOptionSubscription(String items) {
		this.selectMultipleOption("Subscription:", items)
		return this
	}
	
	UsersAdministrationPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}
	UsersAdministrationPage unSelectCheckBox(String label, int index=1) {
		BaseActions.uncheck(webCheckbox(label,index))
		return this
	}

	//////////////////// Textbox ////////////////////////////
	UsersAdministrationPage inputEmployeeNameID(int index=1, Object text) {
		BaseActions.setText(webTextbox("Employee Name/ID:", index), text.toString())
		return this
	}
	
	UsersAdministrationPage inputFirstName(int index=1, String firstName) {
		BaseActions.setText(webTextbox("First Name:", index),firstName)
		return this
	}

	UsersAdministrationPage inputLastName(int index=1, String lastName) {
		BaseActions.setText(webTextbox("Last Name:", index), lastName)
		return this
	}

	UsersAdministrationPage inputEmployeeID(int index=1, String employeeId) {
		BaseActions.setText(webTextbox("Employee ID:", index), employeeId)
		return this
	}
	
	UsersAdministrationPage inputHireDate(int index=1, String hireDate) {
		BaseActions.setText(webTextbox("Hire Date:", index), hireDate)
		return this
	}
	
	UsersAdministrationPage inputUserName(int index=1, String userName) {
		BaseActions.setText(webTextbox("Username:", index), userName)
		return this
	}
	
	UsersAdministrationPage inputNameOfFile(int index=1, String userName) {
		BaseActions.setText(webTextbox("Name of File:", index), userName)
		return this
	}


	//////////////////// MoveItems ////////////////////////////
	UsersAdministrationPage moveAvailableOrganizations(String availableOrganizations, String listItems) {
		this.moveAvailableItems(availableOrganizations, listItems,)
		return this
	}
	
	UsersAdministrationPage moveAvailableCompany(String listItems) {
		inputNameCompanies("Assign Proctor to External Company",listItems)
		clickCheckBoxAssignCompany(2)
		BaseActions.click(webButtonArrowRight("Available Companies" ,2))
		return this
	}
	
	UsersAdministrationPage moveSelectedCompany(String listItems , int index =1) {
		inputNameCompanies("Assign Proctor to External Company",listItems,3)
		clickCheckBoxAssignCompany(4)
		clickButtonArrowLeftAssignCompany()
		return this
	}
	
	UsersAdministrationPage moveAvailableSubordinates(String listItems) {
		this.moveAvailableItems("Available Subordinates", listItems)
		return this
	}

	UsersAdministrationPage moveSelectedOrganizations(String selectedOrganizations, String listItems) {
		this.moveSelectedItems(selectedOrganizations, listItems)
		return this
	}
	
	UsersAdministrationPage quickAssign(String availableOrganizations, String listAvailableOrganizations) {
		moveAvailableOrganizations(availableOrganizations, listAvailableOrganizations)
		return this
	}

	UsersAdministrationPage quickUnAssign( String selectedOrganizations, String listUnAssignedOrganizations) {
		moveSelectedOrganizations(selectedOrganizations, listUnAssignedOrganizations)
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	UsersAdministrationPage openUsersDetails(String filterBy) {
		this.onTable().selectIconByTextOnTable(filterBy, "View")
		return this
	}
	
	String getNoResultsMessageFromTable() {
		return Pages.As(OnDataTable).setTable("Name_Header", 1).getListRowsContentOnTableUser(1..1)
	}
	
	UsersAdministrationPage deleteSubordinates(String filterBy) {
		String _getContent = getNoResultsMessageFromTable()
		
		if(_getContent != "[No results returned.]")
		{
			Pages.As(OnDataTable).setTable("Subordinates", 1).selectIconByTextOnTable(filterBy, "Remove")
		}
		return this
	}
	
	UserAssignmentsPage deleteEvaluatorQualifications (int index=8 ) {
		String _getContent = getNoResultsMessageFromTable()
		if(_getContent != "[No results returned.]")
		{
			Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnHeader(1)
			clickDelete()
		}
		return this
	}

	UsersAdministrationPage deleteUploadedDocuments(String filterBy) {
		String _getContent = getNoResultsMessageFromTable()
		if(_getContent != "[No results returned.]")
		{
			Pages.As(OnDataTable).setTable("Uploaded Documents", 1).selectIconByTextOnTable(filterBy, "Delete")
		}
		return this
	}

	UsersAdministrationPage openUserProfile(String username) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(username, "View")
		return this
	}
	
	UsersAdministrationPage verifyDetailSubordinate(String subordinatesName , String dateAdded , String addedBy) {
		Pages.As(OnDataTable).setTable("Subordinates", 1).verifyTableRow("${subordinatesName};${dateAdded};${addedBy}")
		return this
	}
	

	UsersAdministrationPage verifyUploadedDocuments(String nameFile , String uploadedBy , String iconDowload) {
		Pages.As(OnDataTable).setTable("Uploaded Documents", 1).verifyTableRow("${nameFile};${uploadedBy};${iconDowload}")
		return this
	}

	UsersAdministrationPage verifyAssignProctorToExternalCompany(String assignEvaluator) {
		Pages.As(OnDataTable).setTable("Quick Assignments", 9).verifyTableRow("${assignEvaluator}")

		return this
	}

	List<String> getAvailableSubordinates(String searchBy=null) {
		return this.getListGroupItems("Available Subordinates", searchBy)
	}
	
	UsersAdministrationPage verifyUserDisplayed(String userStatus , String employeeName , String userType=null) {
		if(userType !=null) {
			selectOptionUserType(userType)
		}
		selectOptionUserStatus(userStatus)
		inputEmployeeNameID(employeeName)
		clickSearch()
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow(employeeName)
		return this
	}
	
	UsersAdministrationPage verifyEvaluatorQualifications(String evaluationId , String evaluationName , String evaluationType) {
		Pages.As(OnDataTable).setTable("Results", 1).verifyTableRow("${evaluationId};${evaluationName};${evaluationType}")
		return this
	}
	
	List<String> getAvailableEvaluations(String searchBy=null) {
		return this.getListGroupItems("Available Evaluations", searchBy)
	}
	
	List<String> getListUsersOnResultTable(int index=1 ,Object range) {
		return Pages.As(OnDataTable).setTable("Results", index).getListRowsContentOnTable(range)
	}
	
	UsersAdministrationPage verifyLabelStatus(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Status:", index), expectedText)
		return this
	}
	
	UsersAdministrationPage uploadFile(String imageFile) {
		uploadFileAndRetry("File to Upload:", 1, imageFile)
		return this
	}
	
	UsersAdministrationPage verifyDownloadedPDFFile(String downloadedFilePath, String expectedContent) {
		FileHelper helper = new FileHelper()
		helper.verifyFileContent(downloadedFilePath, expectedContent)
		return this
	}
	
	UsersAdministrationPage verifyExportFileSuccessfully(String fileName) {
		def _exportFile = new File(fileName)
		BaseActions.verifyEqual(_exportFile.exists(), true)
		BaseActions.verifyNotEqual(_exportFile.size(), 0)
		return this
	}
	
	UsersAdministrationPage verifyLogOutButtonColor(String label , int index=1, String expected) {
		String currentButtonColor = BaseActions.getCSSValue(webLabelItem(label,index), "background-color")
		String hex = Color.fromString(currentButtonColor).asHex();
		BaseActions.verifyMatch(hex, expected, false)
		return this
	}
	
	UsersAdministrationPage verifyLabelEvaluator (int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Evaluator:", index), expectedText)
		return this
	}
	

}
