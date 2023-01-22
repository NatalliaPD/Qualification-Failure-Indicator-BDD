package com.ewn.common.page

import com.ewn.fw.lib.BaseActions
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil

public class OnDataTable extends BasePage<OnDataTable> {

	private String tableName
	private Object tableIndex
	private TestObject __table = null

	/******************************************************
	 * Set Table object
	 * @param name - the label to find Table object
	 * @param index - index number or section name to find Table object
	 */
	OnDataTable setTable(String name, Object index = 1) {
		this.tableName = name
		this.tableIndex = index
		BaseActions.waitForElementVisible(webTable(), GlobalVariable.ShortTime, FailureHandling.STOP_ON_FAILURE)
		return this
	}

	/////////////////////// TestObject ///////////////////////
	static List<String> getListXpathColumn(String listTexts) {
		List<String> _listXpath = []
		listTexts.split(';').each {
			if (it.toString().contains('ri-')) _listXpath.add("[.//*[self::i or self::span][contains(@class,'${it}')]]")
			else _listXpath.add("[./td[normalize-space(.)='${it}']]")
		}
		return _listXpath
	}

	private String getXpathTable() {
		if (tableName.contains('_Header')) {
			// table name = header, find table by header of the table
			String _labelHeader = tableName.replace('_Header', '')
			return "(//table[.//th[normalize-space(.)='${_labelHeader}']])[${tableIndex}]"
		}
		if (tableIndex instanceof String || tableIndex instanceof GString) {
			// base on cell value = table index
			return "//*[self::h1 or self::h2 or self::span or self::h3 or self::h4][normalize-space(text())='${tableName}']/following::*[self::td or self::span][text()='${tableIndex}'][1]/following::table[1]"
		}
		// base on label of the table
		return "//*[self::h1 or self::h2 or self::span or self::h3 or self::h4][normalize-space(text())='${tableName}']/following::table[${tableIndex}]"
	}

	private String getXpathColumnByValue(String columnValue) {
		return "count(${getXpathTable()}/thead/tr[1]/th[normalize-space(.)='${columnValue}']/preceding-sibling::th) + 1"
	}

	private String getXpathCell(Object row, Object column) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())
		// using row as index
		if (row instanceof Number) {
			String rowXpath = "${getXpathTable()}/tbody/tr[${row}]"
			boolean isRowVisible = isElementVisibleFromXpath(rowXpath)
			if(!isRowVisible) KeywordUtil.markErrorAndStop("Unable to find row index ${row} on table ${tableName} with selector ${rowXpath}")

			return "${rowXpath}/td[${columnIndex}]"
		}
		// find by row value and index of column
		String rowXpath = "${getXpathTable()}/tbody/tr/td[normalize-space(.)='${row}']"
		boolean isRowVisible = isElementVisibleFromXpath(rowXpath)
		if(!isRowVisible) KeywordUtil.markFailedAndStop("Unable to find row value ${row} on table ${tableName} with selector ${rowXpath}")

		return "${rowXpath}/../td[${columnIndex}]"
	}

	/******************************************************
	 * Get Table object
	 * @return Table object
	 */
	private TestObject webTable() {
		if (__table == null) {
			__table = BaseActions.createTestObject("${tableName}_Table", 'xpath', getXpathTable())
		}
		return __table
	}

	/******************************************************
	 * Get Cell object
	 * @param row - the row number index or text to find row
	 * @param column - the column number index or name
	 * @return Cell object
	 */
	private TestObject webTableCell(Object row, Object column) {
		return BaseActions.createTestObject("${tableName}_TableCell[${row}][${column}]", 'xpath', getXpathCell(row, column))
	}

	private TestObject webTableIcon(Object row, Object column) {
		return BaseActions.createTestObject("${tableName}_TableCell[${row}][${column}]", 'xpath', "${getXpathCell(row, column)}//*[self::i or self::span]")
	}

	private TestObject webTableImage(Object row, Object column) {
		return BaseActions.createTestObject("${tableName}_TableCell[${row}][${column}]", 'xpath', "${getXpathCell(row, column)}//img")
	}

	/******************************************************
	 * Get CellValue object
	 * @param value - the value on cell
	 * @return Cell object
	 */
	private TestObject webTableCellValue(String value) {
		return BaseActions.createTestObject("${tableName}_TableCell[${value}]", 'xpath', "${getXpathTable()}/tbody/tr/td[normalize-space(.)='${value}']")
	}

	private TestObject webTableCellValueArchived(String value) {
		return BaseActions.createTestObject("${tableName}_TableCell[${value}]", "xpath", "${getXpathTable()}/tbody[@class='ng-scope condition-archived']");
	}

	private TestObject webTableCellValueNotArchived(String value) {
		return BaseActions.createTestObject("${tableName}_TableCell[${value}]", "xpath", "${getXpathTable()}/tbody[@class='ng-scope']");
	}

	/******************************************************
	 * Get CellValueAtColumn object
	 * @param column - the column number index or name
	 * @param value - the value on cell
	 * @return Cell object
	 */
	private TestObject webTableCellValueAtColumn(Object column, String value, boolean shouldBeVisible = true) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())
		String columnXpath = "${getXpathTable()}/tbody/tr/td[${columnIndex}]"
		TestObject cellObject = BaseActions.createTestObject("${tableName}_TableCellValue[${value}][${column}]", 'xpath', "${columnXpath}[normalize-space(.)='${value}']")

		if (shouldBeVisible) {
			boolean isColumnVisible = isElementVisibleFromXpath(columnXpath)
			if(!isColumnVisible) KeywordUtil.markFailedAndStop("Unable to find column index ${columnIndex} on table ${tableName} with selector ${columnXpath}")
		}
		return cellObject
	}

	/******************************************************
	 * Get CellAtColumnFilterByText object
	 * @param text - the text to find row number index
	 * @param column - the column number index or name
	 * @return Cell object
	 */
	private TestObject webTableCellAtColumnFilterByText(String text, Object column) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())

		String columnXpath = "${getXpathTable()}/tbody/tr/td[normalize-space(.)='${text}']"
		boolean isColumnVisible = isElementVisibleFromXpath(columnXpath)
		if(!isColumnVisible) KeywordUtil.markFailedAndStop("Unable to find column by value ${text} on table ${tableName} with selector ${columnXpath}")
		return BaseActions.createTestObject("${tableName}_TableCellFilter[${text}][${column}]", 'xpath', "${columnXpath}/../td[${columnIndex}]")
	}

	/******************************************************
	 * Get IconCellAtColumnFilterByText object
	 * @param text - the text to find row number index
	 * @param columnValue - the column number index or name
	 * @return IconCell object
	 */
	private TestObject webIconCellAtColumnFilterByText(String text, Object column) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())

		String columnXpath = "${getXpathTable()}/tbody/tr/td[normalize-space(.)='${text}']"
		boolean isColumnVisible = isElementVisibleFromXpath(columnXpath)
		if(!isColumnVisible) KeywordUtil.markFailedAndStop("Unable to find column by value ${text} on table ${tableName} with selector ${columnXpath}")
		return BaseActions.createTestObject("${tableName}_TableIconCellFilter[${text}][${columnIndex}]", 'xpath', "${columnXpath}/../td[${columnIndex}]//*[self::i or self::span[@class!='ng-scope']]")
	}

	private TestObject webLockedIconCellAtColumnFilterByText(String text, Object column) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())

		String columnXpath = "${getXpathTable()}/tbody/tr/td[normalize-space(.)='${text}']"
		boolean isColumnVisible = isElementVisibleFromXpath(columnXpath)
		if(!isColumnVisible) KeywordUtil.markFailedAndStop("Unable to find column by value ${text} on table ${tableName} with selector ${columnXpath}")
		return BaseActions.createTestObject("${tableName}_TableIconCellFilter[${text}][${column}]", 'xpath', "${columnXpath}/../td[${columnIndex}]//*[self::i or self::span[@uib-popover!='Unlock']]")
	}

	private TestObject webIconCellAtColumnFilterByTextForMultipleBody(int bodyIndex=1, String text, Object column) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())

		String columnXpath = "${getXpathTable()}/tbody[${bodyIndex}]/tr/td[normalize-space(.)='${text}']"
		boolean isColumnVisible = isElementVisibleFromXpath(columnXpath)
		if(!isColumnVisible) KeywordUtil.markFailedAndStop("Unable to find column by value ${text} on table ${tableName} with selector ${columnXpath}")
		return BaseActions.createTestObject("${tableName}_TableIconCellFilter[${text}][${columnIndex}]", 'xpath', "${columnXpath}/../td[${columnIndex}]//*[self::i or self::span[@class!='ng-scope']]")
	}

	private TestObject webCellAtColumnFilterByText(String text) {
		return BaseActions.createTestObject("${tableName}_TableIconCellFilter[${text}]", 'xpath', "${getXpathTable()}/tbody/tr/td[normalize-space(.)='${text}']")
	}

	/******************************************************
	 * Get ListTableHeader object
	 * @return ListTableHeader object
	 */
	private TestObject webListTableHeader() {
		return BaseActions.createTestObject("${tableName}_ListTableHeader", 'xpath', "${getXpathTable()}/thead/tr/th")
	}

	private TestObject webCheckboxOnHeader(int index = 1) {
		return BaseActions.createTestObject("${tableName}_ListTableHeader", 'xpath', "(${getXpathTable()}/thead/tr/th//input[@type='checkbox'])[${index}]")
	}

	/******************************************************
	 * Get TableRow object
	 * @param row - the row index or content
	 * @return TableRow object
	 */
	TestObject webTableRow(Object row, boolean shouldVisible  = true) {
		if (row instanceof Number) return BaseActions.createTestObject("${tableName}_TableRow", 'xpath', "${getXpathTable()}/tbody/tr[@ng-repeat or @ng-repeat-start][${row}]")
		if (row instanceof String || row instanceof GString) {
			List<String> listXpath = getListXpathColumn(row as String)
			String rootXpath = "${getXpathTable()}/tbody/tr[@ng-repeat or @ng-repeat-start]"
			TestObject tableRow = BaseActions.createTestObject("${tableName}_TableRow", 'xpath', "${rootXpath}${listXpath.join('')}")

			// don't have to check whether the row is visible or not
			if(!shouldVisible) return tableRow
			if (BaseActions.waitForElementVisible(tableRow, GlobalVariable.MediumTime, FailureHandling.OPTIONAL)) return tableRow
			// handle row not found error
			throwErrorOnTable(listXpath, rootXpath)
		}
		throw new Exception("Unsupported type of row, row should be Number or String, instead it is ${row.getClass()}")
	}

	/******************************************************
	 * Get ListTableRows object
	 * @return ListTableRows object
	 */
	private TestObject webListTableRows() {
		return BaseActions.createTestObject("${tableName}_ListTableRows", 'xpath', "${getXpathTable()}/tbody/tr[@ng-repeat or @ng-repeat-start]")
	}

	private TestObject webListTableRowsUser() {
		return BaseActions.createTestObject("${tableName}_ListTableRows", 'xpath', "${getXpathTable()}/tbody/tr")
	}

	/******************************************************
	 * Get ListCellAtColumn object
	 * @param column - the column number index or name
	 * @return ListCellAtColumn object
	 */
	private TestObject webListCellAtColumn(Object column) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())
		return BaseActions.createTestObject("${tableName}_ListTableCell[${column}]", 'xpath', "${getXpathTable()}/tbody/tr/td[${columnIndex}]")
	}

	/******************************************************
	 * Get ListCellAtRow object
	 * @param rowIndex - the row number index
	 * @return ListCellAtRow object
	 */
	private TestObject webListCellAtRow(int rowIndex) {
		return BaseActions.createTestObject("${tableName}_ListTableCellAtRow[${rowIndex}]", 'xpath', "${getXpathTable()}/tbody/tr[${rowIndex}]/td")
	}

	/******************************************************
	 * Get CheckboxAtColumnFilterByText object
	 * @param text - the text to find row number index
	 * @param column - the column number index or name
	 * @return CheckboxCell object
	 */
	private TestObject webCheckboxAtColumnFilterByText(String text, Object column) {
		String columnIndex = column instanceof Number ? column as String : getXpathColumnByValue(column.toString())
		return BaseActions.createTestObject("${tableName}_TableCheckboxFilter[${text}][${column}]", 'xpath', "${getXpathTable()}/tbody/tr/td[normalize-space(.)='${text}']/../td[${columnIndex}]//input[@type='checkbox']")
	}

	/******************************************************
	 * Get TableCheckbox object
	 * @param row - the row index number or text on row
	 * @param column - the column number index or name
	 * @return TableCheckbox object
	 */
	private TestObject webTableCheckbox(Object row, Object column) {
		return BaseActions.createTestObject("${tableName}_TableTextbox", 'xpath', "${getXpathCell(row, column)}//input[@type='checkbox']")
	}

	/******************************************************
	 * Get TableCombobox object
	 * @param row - the row index number or text on row
	 * @param column - the column number index or name
	 * @return TableCombobox object
	 */
	private TestObject webTableCombobox(Object row, Object column) {
		return BaseActions.createTestObject("${tableName}_TableTextbox", 'xpath', "${getXpathCell(row, column)}//select")
	}

	/******************************************************
	 * Get TableTextbox object
	 * @param row - the row index number or text on row
	 * @param column - the column number index or name
	 * @return TableTextbox object
	 */
	TestObject webTableTextbox(Object row, Object column) {
		return BaseActions.createTestObject("${tableName}_TableTextbox", 'xpath', "${getXpathCell(row, column)}//*[self::input or self::textarea]")
	}

	/////////////////////// Actions ///////////////////////
	String getTableContent() {
		return BaseActions.getAttribute(webTable(), 'outerHTML').trim()
	}

	OnDataTable verifyTableContent(String expectedContent) {
		String _currentContent = BaseActions.getAttribute(webTable(), 'outerHTML').trim()
		BaseActions.verifyEqual(_currentContent, expectedContent)
		return this
	}

	String getTableStatus() {
		return BaseActions.getAttribute(webTableCell(1, 1), 'textContent').trim()
	}

	OnDataTable verifyListValuesTableHeader(List<String> expectedListValues) {
		BaseActions.verifyListValues(webListTableHeader(), expectedListValues)
		return this
	}

	OnDataTable clickOnHeader(Object column) {
		if (column instanceof String || column instanceof GString) BaseActions.selectListItemByText(webListTableHeader(), column)
		else BaseActions.selectListByIndex(webListTableHeader(), column)
		return this
	}

	OnDataTable selectCheckboxOnHeader(int index = 1) {
		BaseActions.clickJS(webCheckboxOnHeader(index))
		return this
	}

	/////////////////////// ACTIONS FOR CELLS///////////////////////
	/******************************************************
	 * Check whether the cell is existing or not
	 * @param row - the row index or text to find row
	 * @param column - the column number index or name
	 */
	Boolean isTableCellExisting(Object row, Object column) {
		return BaseActions.waitForPresent(webTableCell(row, column), GlobalVariable.MediumTime)
	}

	/******************************************************
	 * Click on Cell
	 * @param row - the row index or text to find row
	 * @param column - the column number index or name
	 */
	OnDataTable clickOnCell(Object row, Object column, String clickType='selenium') {
		if (clickType == 'selenium') BaseActions.click(webTableCell(row, column))
		else BaseActions.clickJS(webTableCell(row, column))
		return this
	}

	/******************************************************
	 * Verify Cell attribute
	 * @param row - the row index or text to find row
	 * @param column - the column number index or name
	 * @param attributeName - the attribute name (Ex: textContent; class; id; ...)
	 * @param expectedValue - the expected value
	 */
	OnDataTable verifyCellAttribute(Object row, Object column, String attributeName, String expectedValue) {
		BaseActions.verifyElementAttributeValue(webTableCell(row, column), attributeName, expectedValue, GlobalVariable.LongTime)
		return this
	}

	String getCellAttribute(Object row, Object column, String attributeName) {
		return BaseActions.getAttribute(webTableCell(row, column), attributeName).trim()
	}

	/******************************************************
	 * Select icon by text on row
	 * @param text - the text to find row index
	 * @param column - the column number index or name
	 */
	OnDataTable selectIconByTextOnTable(String text, String column, String clickType = "selenium") {
		if (clickType == 'selenium') BaseActions.click(webIconCellAtColumnFilterByText(text, column))
		else BaseActions.clickJS(webIconCellAtColumnFilterByText(text, column))
		return this
	}

	/******************************************************
	 * @param text - the text to find row index
	 * @param column - the column number index or name
	 */
	OnDataTable hoverIconByTextOnTable(String text, String column) {
		BaseActions.scrollToElement(webIconCellAtColumnFilterByText(text, column), GlobalVariable.ShortTime)
		BaseActions.mouseOver(webIconCellAtColumnFilterByText(text, column))
		return this
	}

	OnDataTable selectTextOnTable(String text, String clickType = "selenium") {
		if (clickType == 'selenium') BaseActions.click(webCellAtColumnFilterByText(text))
		else BaseActions.clickJS(webCellAtColumnFilterByText(text))
		return this
	}

	OnDataTable verifyIconAttributeByText(String text, String column, String attributeName, String expectedValue) {
		BaseActions.verifyElementAttributeValue(webIconCellAtColumnFilterByText(text, column), attributeName, expectedValue, GlobalVariable.LongTime)
		return this
	}

	OnDataTable verifyIconAttributeByTextForMultipleBody(int bodyIndex=1, String text, String column, String attributeName, String expectedValue) {
		BaseActions.verifyElementAttributeValue(webIconCellAtColumnFilterByTextForMultipleBody(bodyIndex, text, column), attributeName, expectedValue, GlobalVariable.LongTime)
		return this
	}

	OnDataTable verifyLockedIconAttributeByText(String text, Object column, String attributeName, String expectedValue) {
		BaseActions.verifyElementAttributeValue(webLockedIconCellAtColumnFilterByText(text, column), attributeName, expectedValue, GlobalVariable.LongTime)
		return this
	}

	OnDataTable clickOnIcon(Object row, Object column, String clickType = 'selenium') {
		if (clickType == 'selenium') BaseActions.click(webTableIcon(row, column))
		else BaseActions.clickJS(webTableIcon(row, column))
		return this
	}

	OnDataTable verifyIconAttribute(Object row, Object column, String attributeName, String expectedValue) {
		BaseActions.verifyElementAttributeValue(webTableIcon(row, column), attributeName, expectedValue, GlobalVariable.LongTime)
		return this
	}

	OnDataTable verifyIconOnTable(Object row, Object column, String expectedIcon) {
		TestObject _iconObject = BaseActions.createTestObject("${expectedIcon}_Icon", 'xpath', "${getXpathCell(row, column)}//*[self::i or self::span][contains(@class,'${expectedIcon}')]")
		BaseActions.verifyElementPresent(_iconObject, GlobalVariable.LongTime)
		return this
	}

	OnDataTable verifyImageOnTable(Object row, Object column) {
		BaseActions.verifyElementPresent(webTableImage(row, column), GlobalVariable.LongTime)
		return this
	}

	/******************************************************
	 * Verify Cell value at column existing // FIXME: should check if the Test Object is visible or not instead of present or not
	 * @param value - the value on cell
	 * @param isPresent - true (exist) ; false (not exist) 
	 */

	/******************************************************
	 * Maybe , It is not working
	 * Because , It can pass wrong parameter
	 */

	OnDataTable verifyValueAtColumnOnTable(Object column, String value, Object isPresent = true) {
		if (isPresent) BaseActions.verifyElementPresent(webTableCellValueAtColumn(column, value), GlobalVariable.LongTime)
		else BaseActions.verifyElementNotPresent(webTableCellValueAtColumn(column, value, isPresent), GlobalVariable.MediumTime)
		return this
	}

	/******************************************************
	 * Verify Value on Table // FIXME: should check if the Test Object is visible or not instead of present or not
	 * @param value - the value on cell
	 * @param isPresent - true (exist) ; false (not exist)
	 */
	OnDataTable verifyValueOnTable(String value, boolean isPresent = true) {
		if (isPresent) BaseActions.verifyElementPresent(webTableCellValue(value), GlobalVariable.LongTime)
		else BaseActions.verifyElementNotPresent(webTableCellValue(value), GlobalVariable.MediumTime)
		return this
	}

	OnDataTable clickOnCellValueOnTable(String cellValue, String clickType = 'selenium') {
		if (clickType == 'selenium') BaseActions.click(webTableCellValue(cellValue))
		else BaseActions.clickJS(webTableCellValue(cellValue))
		return this
	}

	OnDataTable selectCheckboxOnTable(Object row, Object column, Object status=true) {
		BaseActions.selectCheckbox(webTableCheckbox(row, column), status)
		return this
	}

	OnDataTable verifyCheckboxStatusOnTable(Object row, Object column, Object status=true) {
		BaseActions.verifyCheckboxStatus(webTableCheckbox(row, column), status)
		return this
	}

	OnDataTable selectComboboxOnTable(Object row, Object column, String text) {
		BaseActions.selectOptionByLabel(webTableCombobox(row, column), text, true)
		return this
	}

	OnDataTable verifySelectedComboboxOnTable(Object row, Object column, String selectedText) {
		BaseActions.verifyOptionSelectedByValue(webTableCombobox(row, column), selectedText, true, GlobalVariable.MediumTime)
		return this
	}

	OnDataTable setTextOnTable(Object row, Object column, Object text) {
		BaseActions.setText(webTableTextbox(row, column), text.toString())
		return this
	}

	OnDataTable verifyTextOnTable(Object row, Object column, String expectedText) {
		BaseActions.verifyElementTextContent(webTableTextbox(row, column), expectedText)
		return this
	}

	OnDataTable verifyTextValueOnTable(Object row, Object column, String expectedText) {
		BaseActions.verifyElementAttributeValue(webTableTextbox(row, column), 'value', expectedText, GlobalVariable.MediumTime)
		return this
	}

	/////////////////////// ACTIONS FOR COLUMN ///////////////////////
	List<String> getListValuesTableColumn(Object column) {
		return BaseActions.getAttributeOnList(webListCellAtColumn(column), 'textContent')
	}

	OnDataTable verifyListValuesTableColumn(Object column, List<String> expectedListValues) {
		BaseActions.verifyEqual(BaseActions.getAttributeOnList(webListCellAtColumn(column), 'textContent').toString(), expectedListValues.toString())
		return this
	}

	List<String> getListRowsContentOnTableUser(Object range) {
		return BaseActions.getListRowsContentOnTable(webListTableRowsUser(), range)
	}

	/******************************************************
	 * Verify Column sort order on Table
	 * @param column - the column number index or name
	 * @param dataType - number; string; dateFormat(yyyy-MM-dd hh:mm; dd/MM/yyyy;...)
	 * @param expectedStatus - normal; descending; ascending
	 */
	OnDataTable verifyColumnSortOrder(String column, String dataType, String expectedStatus='ascending') {
		BaseActions.verifyListSortOrder(webListCellAtColumn(column), dataType, expectedStatus)
		return this
	}

	/////////////////////// ACTIONS FOR ROWS ///////////////////////
	Boolean isTableRowExisting(Object row) {
		return BaseActions.verifyElementPresent(webTableRow(row, false), GlobalVariable.MediumTime, FailureHandling.OPTIONAL)
	}

	/******************************************************
	 * Verify row content on Table // FIXME: should check if the Test Object is visible or not instead of present or not
	 * @param row - the row number index or list values delimiter by ";" (Ex: value1;value2;ri-check-line;ri-alert-line ri-orange)
	 * @param isPresent - true (exist) ; false (not exist)
	 */
	OnDataTable verifyTableRow(Object row, Object isPresent = true) {
		if (isPresent) BaseActions.verifyElementPresent(webTableRow(row), GlobalVariable.LongTime)
		else BaseActions.verifyElementNotPresent(webTableRow(row, isPresent), GlobalVariable.MediumTime)
		return this
	}
	
	OnDataTable verifyVisibleTableRow(Object row, Object isVisible = true) {
		if (isVisible) BaseActions.verifyElementVisible(webTableRow(row))
		else BaseActions.verifyElementNotVisible(webTableRow(row, isVisible))
		return this
	}

	/******************************************************
	 * Get list content on one row
	 * @param row - the row index or text to find row
	 * @param range - the range number (Ex: 2..6)
	 * @return list values delimiter by ";" (Ex: value1;value2;ri-check-line;ri-alert-line ri-orange)
	 */
	String getListValuesTableRow(Object row, Object range) {
		return BaseActions.getRowContentOnTable(webTableRow(row), range)
	}

	OnDataTable verifyListValuesTableRow(Object row, Object range, String expectedListValues) {
		BaseActions.verifyEqual(BaseActions.getRowContentOnTable(webTableRow(row), range).toString(), expectedListValues)
		return this
	}

	List<String> getListRowsContentOnTable(Object range) {
		return BaseActions.getListRowsContentOnTable(webListTableRows(), range)
	}

	Integer countTableRows(String itemToCount = '') {
		return BaseActions.countVisibleItemsOnList(webTableRow(itemToCount, false))
	}

	OnDataTable verifyTotalRows(int expectedNumber) {
		BaseActions.verifyEqual(countTableRows(), expectedNumber)
		return this
	}

	OnDataTable verifyTableRowCollapse(String filterBy) {
		String _class = BaseActions.getAttribute(webIconCellAtColumnFilterByText(filterBy, 1), 'class')
		BaseActions.verifyEqual(_class.contains('ri-arrow-down'), true)
		return this
	}

	OnDataTable moveTableRow(Object sourceRow, Object destinationRow) {
		BaseActions.dragAndDrop(webTableRow(sourceRow), webTableRow(destinationRow))
		return this
	}

	OnDataTable verifyTableRowExpanded(String filterBy) {
		String _class = BaseActions.getAttribute(webIconCellAtColumnFilterByText(filterBy, "1"), 'class')
		BaseActions.verifyEqual(_class.contains('ri-arrow-up'), true)
		return this
	}

	static void throwErrorOnTable(List<String> itemToLoop, String rootXpath = null) {
		String notFoundItems = ""
		itemToLoop.each {
			TestObject itemToCheck = new TestObject(it)
			itemToCheck.addProperty("xpath", ConditionType.EQUALS, "${rootXpath}${it}")
			if (BaseActions.waitForElementNotPresent(itemToCheck, GlobalVariable.ShortTime, FailureHandling.OPTIONAL)) notFoundItems += "${it}\n"
		}
		KeywordUtil.markFailedAndStop("Unable to locate following items on table: ${notFoundItems}")
	}

	boolean isElementVisibleFromXpath(String xpathElement) {
		TestObject itemToCheck = new TestObject()
		itemToCheck.addProperty("xpath", ConditionType.EQUALS, xpathElement)
		return BaseActions.waitForElementVisible(itemToCheck, GlobalVariable.ShortTime, FailureHandling.OPTIONAL)
	}

}
