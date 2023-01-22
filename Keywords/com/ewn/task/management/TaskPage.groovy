package com.ewn.task.management
import com.ewn.account.management.UserAssignmentsPage
import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable
import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.WebElement

public class TaskPage extends BasePage<TaskPage> {

	//////////////////// Button ////////////////////////////
	TaskPage clickSearch(int index=1) {
		BaseActions.clickJS(webButton("Search", index))
		return this
	}

	TaskPage clickSearchIcon() {
		BaseActions.click(webSearchIcon("Add Evaluation:"))
		return this
	}

	TaskPage clickAdd(int index=1) {
		BaseActions.clickJS(webButton("Add", index))
		return this
	}

	TaskPage clickSave(int index=1) {
		BaseActions.clickJS(webButton("Save", index))
		return this
	}

	TaskPage clickFilter(int index=1) {
		BaseActions.click(webButton("Filter", index))
		return this
	}

	TaskPage clickEdit(int index=1) {
		BaseActions.clickJS(webButton("Edit", index))
		return this
	}

	TaskPage clickDelete(int index=1) {
		BaseActions.clickJS(webButton("Delete", index))
		return this
	}

	TaskPage clickYes(int index=1) {
		BaseActions.click(webButton("Yes", index))
		return this
	}

	TaskPage clickCopy(int index=1) {
		BaseActions.clickJS(webButton("Copy", index))
		return this
	}

	TaskPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	TaskPage selectOptionTaskList(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task List:", index), item, true)
		return this
	}

	TaskPage selectOptionShowTasks(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Show Tasks:", index), item, true)
		return this
	}

	TaskPage selectOptionStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Status:", index), item, true)
		return this
	}

	TaskPage selectOptionVisibility(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Visibility:", index), item, true)
		return this
	}

	TaskPage selectOptionTaskClassification(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task Classification:", index), item, true)
		return this
	}

	TaskPage selectOptionCovered(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Covered:", index), item, true)
		return this
	}

	TaskPage selectOptionTaskType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Task Type:", index), item, true)
		return this
	}

	TaskPage selectOptionEvaluationAuthor(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Author:", index), item, true)
		return this
	}

	TaskPage selectOptionEvaluationCatalog(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Catalog:", index), item, true)
		return this
	}

	TaskPage selectOptionEvaluationType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Evaluation Type:", index), item, true)
		return this
	}

	TaskPage selectOptionOrderBy(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Order By:", index), item, true)
		return this
	}

	TaskPage selectMultipleOptionTaskAssociation(String items) {
		this.selectMultipleOption("Task Association:", items)
		return this
	}

	TaskPage selectMultipleOptionConditionStatus(String items) {
		this.selectMultipleOption("Condition Status:", items)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	TaskPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	TaskPage moveSelectedEvaluations(String listItems) {
		this.moveSelectedItems("Selected Evaluations", listItems)
		return this
	}

	TaskPage moveAvailableTaskLists(String listItems) {
		this.moveAvailableItems("Available Task Lists", listItems)
		return this
	}

	TaskPage moveAssignedTaskLists(String listItems) {
		this.moveSelectedItems("Assigned Task Lists", listItems)
		return this
	}

	//////////////////// Icon ////////////////////////////
	TaskPage clickPlusIcon() {
		BaseActions.click(webAddIcon("Certificate Template:"))
		return this
	}

	//////////////////// Label ////////////////////////////
	TaskPage verifyLabelApplyPrerequisiteOrder(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Apply Prerequisite Order:", index), expectedText)
		return this
	}

	TaskPage verifyLabelStatus(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Status:", index), expectedText)
		return this
	}

	TaskPage verifyLabelArchived(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Archived:", index), expectedText)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	TaskPage inputEvaluationIDName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Evaluation ID/Name:", index), text.toString())
		return this
	}

	TaskPage inputTaskCodeName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Task Code/Name:", index), text.toString())
		return this
	}

	TaskPage inputTaskName(String text) {
		BaseActions.setText(webVisibleTextbox("Task Name:"), text)
		return this
	}

	TaskPage inputTaskCode(String text) {
		BaseActions.setText(webVisibleTextbox("Task Code:"), text)
		return this
	}

	TaskPage inputSOC(int index=1, Object text) {
		BaseActions.setText(webTextbox(":", index), text.toString())
		return this
	}

	TaskPage inputGracePeriod(int index=1, Object text) {
		BaseActions.setText(webTextbox("Grace Period:", index), text.toString())
		return this
	}

	TaskPage inputTaskDescription(int index=1, Object text) {
		BaseActions.setText(webTextbox("Task Description:", index), text.toString())
		return this
	}

	TaskPage inputTaskNotes(int index=1, Object text) {
		BaseActions.setText(webTextbox("Task Notes:", index), text.toString())
		return this
	}

	TaskPage inputCopyExistingTask(int index=1, Object text) {
		BaseActions.setText(webTextbox("Copy Existing Task:", index), text.toString())
		return this
	}

	TaskPage inputArchived(int index=1, Object text) {
		BaseActions.setText(webTextbox("Archived:", index), text.toString())
		return this
	}

	TaskPage setTextAssociateCopyExistingTask(String text, int index=1,String selectedItem) {
		this.setTextAssociate("Copy Existing Task:", index, text, selectedItem)
		return this
	}

	TaskPage inputTemplateName(Object text) {
		BaseActions.setText(webTextbox("Template Name"), text.toString())
		return this
	}

	TaskPage inputBackground(Object text) {
		BaseActions.setText(webTextbox("Background"), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	//////////////////// Main Task Management ////////////////////////////
	TaskPage openTaskDetails(String filterBy) {
		Pages.As(OnDataTable).setTable("Results", 1).selectIconByTextOnTable(filterBy, "Details")
		return this
	}

	TaskPage verifyTaskExistedOnTable(int tableIndex=1, Object rowValues, Boolean expectedStatus=true) {
		Pages.As(OnDataTable).setTable("Results", tableIndex).verifyTableRow(rowValues, expectedStatus)
		return this
	}

	TaskPage searchTask(String taskList, String showTasks, String status, String classification, String covered, String visibility, String type, String taskCodeName) {
		selectOptionTaskList(taskList)
		selectOptionShowTasks(showTasks)
		selectOptionStatus(status)
		selectOptionTaskClassification(classification)
		selectOptionCovered(covered)
		selectOptionVisibility(visibility)
		selectOptionTaskType(type)
		inputTaskCodeName(taskCodeName)
		clickSearch()
		return this
	}

	TaskPage addNewTask(String name, String code, Object SOC, Object gracePeriod, String status, Object covered, Object partTest1, Object partTest2, Object partTest3, Object partTest4, String association, String desscription, String note) {
		selectButtonOption("Add", "New Task")
		inputTaskName(name)
		inputTaskCode(code)
		inputSOC(SOC)
		inputGracePeriod(2, gracePeriod)
		selectOptionStatus(4, status)
		selectCheckbox("Covered Task:", 1, covered)
		selectCheckbox("Performed on a pipeline facility", 1, partTest1)
		selectCheckbox("Operations or maintenance task", 1, partTest2)
		selectCheckbox("Performed as a requirement of this part", 1, partTest3)
		selectCheckbox("Affects the operation or integrity of the pipeline", 1, partTest4)
		selectMultipleOptionTaskAssociation(association)
		inputTaskDescription(2, desscription)
		inputTaskNotes(2, note)
		clickSave(4)
		return this
	}

	TaskPage addNewTaskIfNotExist(String taskList="All", String showTasks="All", String statusTask="All", String classification="All", String coveredTask="All", String visibilityTask="All", String type="All", String taskCodeName, String name, String code, Object SOC, Object gracePeriod, String status, Object covered=false, Object partTest1=false, Object partTest2=false, Object partTest3=false, Object partTest4=false, String association, String desscription="desscription", String note="note") {
		String _ResultsStatus = searchTask(taskList, showTasks, statusTask, classification, coveredTask, visibilityTask, type, taskCodeName).onTable("Results").getTableStatus()
		if(_ResultsStatus == "No results returned.") {
			addNewTask(name, code, SOC, gracePeriod, status, covered, partTest1, partTest2, partTest3, partTest4, association, desscription, note)
		}
		return this
	}

	//////////////////// Task Lists Tab////////////////////////////
	TaskPage editTaskLists(String listAvailableTaskLists=null, String listAssignedTaskLists=null) {
		selectTab("Task Lists")
		clickEdit(3)
		if (listAvailableTaskLists != null) moveAvailableTaskLists(listAvailableTaskLists)
		if (listAssignedTaskLists != null) moveAssignedTaskLists(listAssignedTaskLists)
		clickSave(2)
		return this
	}

	//////////////////// Condition Tab ////////////////////////////
	TaskPage addConditions(String evaluationAuthor, String evaluationCatalog=null, String evaluationType=null, String orderBy=null, String listAvailableEvaluations) {
		selectTab("Conditions")
		clickAdd(2)
		selectOptionEvaluationAuthor(evaluationAuthor)
		if(evaluationCatalog != null) selectOptionEvaluationCatalog(evaluationCatalog)
		if(evaluationType != null) selectOptionEvaluationType(evaluationType)
		if(orderBy != null) selectOptionOrderBy(orderBy)
		clickFilter()
		moveAvailableEvaluations(listAvailableEvaluations)
		clickSave()
		return this
	}
	TaskPage addConditionss(String listAvailableEvaluations,String evaluationType="Computer Based Training") {
		selectTab("Conditions")
		clickAdd(2)
		selectOptionEvaluationType(evaluationType)
		clickFilter();
		moveAvailableEvaluations(listAvailableEvaluations)
		clickSave()
		return this
	}

	TaskPage addTaskList(String listAvailableEvaluations) {
		selectTab("Task Lists")
		clickEdit(3)
		moveAvailableTaskLists(listAvailableEvaluations)
		clickSave(2)
		return this
	}

	TaskPage addOneConditionWithoutSaving(String evaluationAuthor, String evaluationCatalog=null, String evaluationType, String orderBy=null, String listAvailableEvaluations) {
		selectOptionEvaluationAuthor(evaluationAuthor)
		if(evaluationCatalog != null) selectOptionEvaluationCatalog(evaluationCatalog)
		if(evaluationType != null) selectOptionEvaluationType(evaluationType)
		if(orderBy != null) selectOptionOrderBy(orderBy)
		clickFilter()
		//need to wait to refresh the evaluation list
		Thread.sleep(3000)
		moveAvailableEvaluations(listAvailableEvaluations)
		return this
	}
	
	TaskPage addConditionsWithGracePeriod(String evaluationAuthor, String evaluationType=null, String orderBy=null, String listAvailableEvaluations, String evaluationId, String interval, String gracePeriod) {
		BaseActions.clickJS(webTab("Conditions"))
		pleaseWait(GlobalVariable.ShortTime)
		clickAdd(2)
		selectOptionEvaluationAuthor(evaluationAuthor)
		if(evaluationType != null) selectOptionEvaluationType(evaluationType)
		if(orderBy != null) selectOptionOrderBy(orderBy)
		clickFilter()
		moveAvailableEvaluations(listAvailableEvaluations)
		clickSave()
		pleaseWait(GlobalVariable.ShortTime)
		clickEdit(2)
		pleaseWait(GlobalVariable.ShortTime)
		OnDataTable newTable = new OnDataTable()
		newTable.setTable("1", 2).setTextOnTable(evaluationId, "Interval (months)", interval)
		newTable.setTable("1", 2).setTextOnTable(evaluationId, "Grace Period (days)", gracePeriod)
		clickSave()
		return this
	}

	/******************************************************
	 * Delete an evaluation on 1 Condition Table by ConditionIndex
	 * @param conditionIndex - Condition Table Index. Note: The 1st condition starts "index=2"
	 * Example: deleteEvaluationOfCondition(2,"116243") - tick off the checkbox of the evaluation having value matched "116243" on the 2nd condition table
	 */
	TaskPage deleteEvaluationOfCondition(int conditionIndex=1, String text) {
		Pages.As(OnDataTable).setTable("Task Management", conditionIndex+1).selectCheckboxOnTable(text, 12, true) // index of  'checkbox' column=12
		clickDelete(conditionIndex)
		clickYes()
		return this
	}

	/******************************************************
	 * Delete all evaluation on 1 Condition Table by ConditionIndex
	 * @param conditionIndex - Condition Table Index. Note: The 1st condition starts "index=2"
	 * Example: deleteAllEvaluationsOfCondition(2) - select the checkbox on header of the 2nd condition table then delete
	 */
	TaskPage deleteAllEvaluationsOfCondition(int conditionIndex=1) {
		OnDataTable taskManagementTable = Pages.As(OnDataTable).setTable("Task Management", conditionIndex + 1)
		TestObject tableObject = new TestObject("taskManagementTable")
		tableObject.addProperty("xpath", ConditionType.EQUALS, taskManagementTable.getXpathTable())

		if(BaseActions.verifyElementVisible(tableObject, FailureHandling.OPTIONAL)) {
			taskManagementTable.selectCheckboxOnHeader()
			clickDelete(conditionIndex)
			clickYes()
			pleaseWait()
		}

		return this
	}

	TaskPage deleteAllConditions() {
		TestObject deleteButton = BaseActions.createTestObject("Delete task button", "css", "button.button-danger")
		TestObject checkAll = BaseActions.createTestObject("check all conditions object", "css", "table th input")
		
		List<WebElement> checkAllElements = BaseActions.findWebElements(checkAll, GlobalVariable.MediumTime)
		List<WebElement> deleteButtonElements = BaseActions.findWebElements(deleteButton, GlobalVariable.MediumTime).findAll{it.isDisplayed()}

		while (deleteButtonElements.size() > 0) {
			checkAllElements.each{it.click()}
			BaseActions.scrollUpAndClick(BaseActions.convertWebElementToTestObject(deleteButtonElements.head()))
			this.clickYes()
			pleaseWait()
			
			// update elements
			checkAllElements = BaseActions.findWebElements(checkAll, GlobalVariable.MediumTime)
			deleteButtonElements = BaseActions.findWebElements(deleteButton, GlobalVariable.MediumTime).findAll{it.isDisplayed()}	
		}
		return this
	}

	/******************************************************
	 * Verify if an Evaluation Info existing on Condition is or not.
	 * @param  - conditionIndex - Condition Table Index. Note: The 1st condition starts "index=2"
	 * @@param - expectedStatus - true to check existing row, false to check non-existing row
	 * Example: verifyEvaluationExistedOnCondition(2,"${evaluationID};${evaluationName}",true)
	 */
	TaskPage verifyEvaluationExistedOnCondition(int conditionIndex=1, Object rowValues, Boolean expectedStatus=true) {
		Pages.As(OnDataTable).setTable("Task Management", conditionIndex+1).verifyTableRow(rowValues, expectedStatus)
		return this
	}

	TaskPage addEvaluationIntoCondition(int conditionIndex=1, String evaluationID, String evaluationName, String evaluationAuthor) {
		String _evaluationSelected = "${evaluationAuthor} - (${evaluationID}) ${evaluationName}"
		clickEdit(conditionIndex+1)
		setTextAssociate("Add Evaluation:", 1, evaluationID, _evaluationSelected)
		clickSave()
		return this
	}

	TaskPage addEvaluationIntoConditionIfNotExisting(int conditionIndex=1, String evaluationID, String evaluationName, String evaluationAuthor) {
		boolean _status = Pages.As(OnDataTable).setTable("Task Management", conditionIndex+1).isTableRowExisting("${evaluationID};${evaluationName}")
		if (_status) deleteEvaluationOfCondition(conditionIndex, evaluationID)
		addEvaluationIntoCondition(conditionIndex, evaluationID, evaluationName, evaluationAuthor)
		return this
	}

	/******************************************************
	 * Verify table column has green check icon
	 * @param  - row - the text search to find your row (eg: ID or Evaluation Title...)
	 * @param - columnName the column that you wan to check it has green check icon
	 * Example: verifyColumnlHasGreenCheck(evaluationID,"Initial")
	 */
	TaskPage verifyColumnlHasGreenCheck (int conditionIndex=1,Object row, String columnName) {
		Pages.As(OnDataTable).setTable("Task Management",conditionIndex+1).verifyIconAttributeByText(row, columnName, "class", "ri-check-line ri-green ng-scope")
		return this
	}

	/******************************************************
	 * Set "Apply Prerequisite Order" option of 1 condition
	 * @param - status - true if checked,false if unchecked
	 * Example: setPrerequisiteOrder(2,false) --> uncheck Apply Prerequisite Order:of the 2nd condition
	 */
	TaskPage setPrerequisiteOrder(int conditionIndex=1, Object status) {
		clickEdit(conditionIndex+1)
		selectCheckbox("Apply Prerequisite Order:", 1, status)
		clickSave(1)
		return this
	}

	/******************************************************
	 * Set "Preferred" option of 1 condition
	 * @param - status - true if checked,false if unchecked
	 * Example: setPreferred(1,false) --> uncheck Preferred of the 1st condition
	 */
	TaskPage setPreferred(int conditionIndex=1, Object status) {
		clickEdit(conditionIndex+1)
		selectCheckbox("Preferred:", 1, status)
		clickSave(1)
		return this
	}

	TaskPage verifyIconDisplayedOnRow(int conditionIndex=1,String rowValues, String icon) {
		Pages.As(OnDataTable).setTable("Task Management",conditionIndex+1).verifyTableRow("${type};${icon}")
		return this
	}

	/******************************************************
	 * Change Status of one condition
	 * @param - status - expected condition status such as "Archived" or "Inactive" or "Active"
	 * Example: changStatusCondition("Archived",2,"05/30/2022") --> change status of the 2nd condition to "Archived" 
	 */
	TaskPage changeStatusCondition(String status, int conditionIndex=1, String archivedDate="01/18/2022") {
		clickEdit(conditionIndex+1)
		selectOptionStatus(conditionIndex+3, status)
		if (status=="Archived") inputArchived(1, archivedDate)
		clickSave(1)
		return this
	}

	/******************************************************
	 * Select/un-select checkbox on Table Cell by Row and Column
	 * @param - status - true if check,false if unchecked
	 */
	TaskPage selectCheckboxOnConditionTable(int conditionIndex=1,Object row, Object column, Object status=true) {
		clickEdit(conditionIndex+1)
		Pages.As(OnDataTable).setTable("Task Management",conditionIndex+1).selectCheckboxOnTable(row,column,status)
		clickSave(1)
		return this
	}

	List<String> getListOfConditions() {
		return Pages.As(OnDataTable).setTable("Order_Header", 1).getListRowsContentOnTable(3..4)
	}

	//////////////////// Properties Tab ////////////////////////////
	TaskPage verifyTaskInfo(String name, String code, Object SOC, Object gracePeriod, String status, Object covered, Object partTest1, Object partTest2, Object partTest3, Object partTest4, String association, String desscription, String note) {
		BaseActions.verifyElementTextContent(webLabelValue("Task Name:",1), name)
		BaseActions.verifyElementTextContent(webLabelValue("Task Code:",2), code)
		BaseActions.verifyElementTextContent(webLabelValue("Span of Control Ratio (SOC):",2), SOC)
		BaseActions.verifyElementTextContent(webLabelValue("Grace Period:",1), gracePeriod)
		BaseActions.verifyElementTextContent(webLabelValue("Status:",3), status)
		BaseActions.verifyElementTextContent(webLabelValue("Covered Task:",2), covered)
		BaseActions.verifyElementTextContent(webLabelValue("‐ Performed on a pipeline facility",1), partTest1)
		BaseActions.verifyElementTextContent(webLabelValue("‐ Operations or maintenance task",1), partTest2)
		BaseActions.verifyElementTextContent(webLabelValue("‐ Performed as a requirement of this part",1), partTest3)
		BaseActions.verifyElementTextContent(webLabelValue("‐ Affects the operation or integrity of the pipeline",1), partTest4)
		BaseActions.verifyElementTextContent(webLabelValue("Task Association:",2), association)
		BaseActions.verifyElementTextContent(webLabelValue("Task Description:",1), desscription)
		BaseActions.verifyElementTextContent(webLabelValue("Task Notes:",1), note)
		return this
	}

	TaskPage updateTaskStatus(String status, int index=1) {
		clickEdit(index)
		selectOptionStatus(3,status)
		clickSave()
		return this
	}

	//////////////////// Certificates Tab////////////////////////////
	TaskPage addNewCertificate(String templateName, String imageFile) {
		clickPlusIcon()
		inputTemplateName(templateName)
		uploadFileAndRetry("Background", 1, imageFile)
		clickSave(1)
		return this
	}

	TaskPage verifyCertificateInfo(String templateName, String taskCode="Yes",String completedDate="Yes",String expirationDate="Yes") {
		BaseActions.verifyElementTextContent(webLabelValue("Use Task Certificate:", 1), "Yes")
		BaseActions.verifyElementTextContent(webLabelValue("Certificate Template:", 1), templateName)
		BaseActions.verifyElementTextContent(webLabelValue("Task Code:", 3), taskCode)
		BaseActions.verifyElementTextContent(webLabelValue("Completed Date:", 1), completedDate)
		BaseActions.verifyElementTextContent(webLabelValue("Expiration Date:", 1), expirationDate)
		return this
	}

	List<String> getNoResultsMessageFromTableOfTask() {
		return Pages.As(OnDataTable).setTable("Name_Header", 2).getListRowsContentOnTableUser(2..2)
	}

	TaskPage deleteEvaluationFromTask(String taskName , int index=12 ) {
		selectMenu("Task Management->Tasks")
		inputTaskCodeName(taskName)
		clickSearch()
		openTaskDetails(taskName)
		selectTab("Conditions")
		clickSearch()
		String _getContent = getNoResultsMessageFromTableOfTask()
		if(_getContent != "[No results returned.]")
		{
			Pages.As(OnDataTable).setTable("Results", 2).selectCheckboxOnTable(1, index)
			clickDelete()
			clickYes()
		}
		return this
	}

}
