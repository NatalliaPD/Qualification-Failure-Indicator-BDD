@Grapes([
	@Grab(group='org.apache.pdfbox', module='pdfbox', version='2.0.25'),
	@Grab(group='org.sikuli', module='sikuli-api', version='1.2.0')
])

package com.ewn.common.page

import java.awt.image.BufferedImage
import java.awt.image.DataBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.zip.ZipFile

import javax.imageio.ImageIO

import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.sikuli.api.ImageTarget
import org.sikuli.api.ScreenRegion
import org.sikuli.api.StaticImageScreenRegion
import org.sikuli.api.Target

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

public class FileHelper {
	private Helper helper = new Helper();

	String getFolderProjectPath(String folderName){
		String separator = System.getProperty("file.separator")
		return "${RunConfiguration.getProjectDir()}/".concat(folderName).replace('/', separator).replace('\\', separator)
	}

	String getFileProjectPath(String fileName){
		return getFolderProjectPath(fileName);
	}

	/******************************************************
	 * Get Data Row from Data File
	 * @param dataFile - The data file name in Data Files folder
	 * @param rowIndex - The row index to get
	 */
	def getDataRow(String dataFile, int rowIndex) {
		def _dataDict = [:]
		TestData testData = TestDataFactory.findTestData(dataFile)
		try{
			String[] headerNames = testData.getColumnNames()
			for (header in headerNames) {
				_dataDict[header] = testData.getValue(header, rowIndex)
			}
		} catch (Exception ex){
			KeywordUtil.markWarning("WARNING :" + ex.getMessage())
			return null
		}
		return _dataDict
	}

	/******************************************************
	 * Create new folder
	 * @param folderName - the folder name
	 * @return the folder path
	 */
	String createFolder(String folderName){
		File file = new File(folderName);

		// true if the directory was created, false otherwise
		if (file.mkdirs()) {
			return folderName;
		} else {
			KeywordUtil.markWarning(String.format("createFolder:: [%s] invalid format", folderName));
			return null;
		}
	}

	/******************************************************
	 * Does file exist
	 * @param fileName - the file name
	 * @return true(exist); false(Not exist)
	 */
	boolean doesFileExist(String fileName){
		File file = new File(fileName);
		return file.exists();
	}

	/******************************************************
	 * Wait file exist
	 * @param fileName: the full file name
	 * @param timeOut: the second time to wait
	 * @return None
	 */
	boolean waitFileExist(String fileName, int timeOut=20) {
		File _file = new File(fileName);
		int _countTime = timeOut;
		while(!_file.exists() && _countTime>0){
			Thread.sleep(1000);
			_countTime--;
		}
	}

	/******************************************************
	 * Delete file
	 * @param fileName - the file name
	 * @return None
	 */
	void deleteFile(String fileName){
		File file = new File(fileName);
		file.deleteOnExit();
	}

	void deleteFileOrFolder(String folderPath){
		FileUtils.deleteDirectory(new File(folderPath));
	}

	/******************************************************
	 * Copy file
	 * @param sourcePath - the source name
	 * @param destPath - the destination name
	 * @return None
	 */
	String copyFile(String sourcePath, String destPath){
		File source = new File(sourcePath);
		File dest = new File(destPath);
		Files.copy(source.toPath(), dest.toPath());
		return destPath;
	}

	/******************************************************
	 * Get List Files in Folder
	 * @param folderName - the folder name
	 * @return list file names
	 */
	List<String> getListFiles(String folderName){
		List<String> results = new ArrayList<String>();

		File[] files = new File(folderName).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null.

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		return results;
	}

	/******************************************************
	 * Search last modified File in Folder
	 * @param folderName - the folder name
	 * @param extensionFile - the extension file type
	 * @return last modified file name
	 */
	String searchLastModifiedFile(String folderName, String extensionFile=".pdf") {
		return new File(folderName).listFiles()?.findAll{it.getName().contains(extensionFile)}?.sort { -it.lastModified() }?.head()
	}

	String getDownloadFile(String extensionFile = ".pdf") {
		// _downloadFolder is user's download folder
		// TODO: should download file into project's folder and clean up before/after tests
		String _downloadFolder = FileUtils.getUserDirectoryPath() + "/Downloads";
		return searchLastModifiedFile(_downloadFolder, extensionFile);
	}

	/******************************************************
	 * Delete File in Folder
	 * @param folderName - the folder name
	 * @param fileName - the file name: .*\\.xlsx, TestData.xlsx
	 * @return list file names
	 */
	void deleteFileInFolder(String folderName, String fileName){
		File[] files = new File(folderName).listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().matches(fileName)) {
				new File(folderName + "\\" + file.getName()).deleteOnExit();
			}
		}
	}

	/******************************************************
	 * Write to file
	 * @param fileName - the file name
	 * @param textContent - the text content
	 */
	void writeToFile(String fileName, String textContent){
		// Define text file location path
		Path path = Paths.get(fileName);

		// Writing to the expected txt file
		Files.write(path,textContent.getBytes(StandardCharsets.UTF_8),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	}

	/******************************************************
	 * Get content file
	 * @param strFileLocation - the file name
	 * @return content file
	 */
	String readFile(String strFileLocation) {
		FileInputStream inputStream = new FileInputStream(strFileLocation);
		return IOUtils.toString(inputStream,"UTF-8");
	}

	/******************************************************
	 * Get Test Text line
	 * @param strFileLocation - the file name
	 * @param rowIndex - the row index
	 * @return Line text
	 */
	String getTextFileAtLine(String strFileLocation, Integer rowIndex) {
		String returnText = "";
		// Define text file location path
		Path path = Paths.get(strFileLocation);

		// Read all lines in text file
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		if (rowIndex != null) returnText = lines.get(rowIndex-1);
		else {
			// Convert List item to string
			for (String line : lines) {
				returnText += line + "\n";
			}
		}
		return returnText;
	}

	/******************************************************
	 * Compare Image
	 * @param baselinePath - the image baseline
	 * @param currentImagePath - the current image
	 * @return result true(match); false(not match)
	 */
	boolean compareImage (String baselinePath, String currentImagePath) {
		try {
			//Get file from ImagePath location
			File baseline = new File(baselinePath);
			File currentImage = new File(currentImagePath);

			//Read BufferedImage of baseline
			BufferedImage biA = ImageIO.read(baseline);
			DataBuffer dbA = biA.getData().getDataBuffer();
			int sizeA = dbA.getSize();

			//Read BufferedImage of captured image
			BufferedImage biB = ImageIO.read(currentImage);
			DataBuffer dbB = biB.getData().getDataBuffer();
			int sizeB = dbB.getSize();

			//Compare data-buffer objects
			if (sizeA == sizeB) {
				for (int i = 0; i < sizeA; i++) {
					if (dbA.getElem(i) != dbB.getElem(i)) {
						return false;
					}
				}
			} else {
				return false;
			}
		} catch (Exception e) { return false; }
		return true;
	}

	/******************************************************
	 * Does image within another Image
	 * @param subImagePath - the sub image path
	 * @param imagePath - The image path
	 * @return true(found) false(not found)
	 */
	boolean doesImageWithinAnotherImage (String subImagePath, Object imagePath) {
		ScreenRegion _result = findImageRegion(subImagePath, imagePath);
		if(_result != null) return true;
		return false;
	}

	/******************************************************
	 * Find image within another Image
	 * @param subImagePath - the sub image path
	 * @param imagePath - The image path
	 * @return not null(found), null(not found)
	 */
	ScreenRegion findImageRegion(String subImagePath, Object imageName){
		try {
			BufferedImage refImage = null;
			if(imageName instanceof String) refImage = ImageIO.read(new File(imageName));
			else refImage = imageName;
			File _targetFile = new File(subImagePath)
			Target target = new ImageTarget(_targetFile);
			target.setMinScore(0.85); // Precision of recognition from 0 to 1.

			ScreenRegion screenRegion = new StaticImageScreenRegion(refImage);
			return screenRegion.find(target);
		} catch(e) { return null;}
	}

	/******************************************************
	 * Verify image
	 * @param actualImageName - actual image file name
	 * @param expectedImageName - expected image file name
	 * @param expectedStatus - expected status : true(match); false(not match)
	 */
	void verifyImage(String actualImageName, String expectedImageName, expectedStatus=true) {
		boolean _matchtStatus = doesImageWithinAnotherImage(expectedImageName, actualImageName)
		if(_matchtStatus == expectedStatus) KeywordUtil.markPassed("verifyImage:: Actual: ${actualImageName} == Expected: ${expectedImageName}")
		else WebUiBuiltInKeywords.verifyEqual("verifyImage:: Actual: ${actualImageName} <> Expected: ${expectedImageName}", "FAILED");
	}

	/******************************************************
	 * Compare File
	 * @param firstFile: the first file
	 * @param secondFile : the second file
	 * @return result (true/false)
	 */
	boolean compareFile (String firstFile, String secondFile) {
		try {
			File f1 = new File(firstFile); //OUTFILE
			File f2 = new File(secondFile); //INPUT

			FileReader fR1 = new FileReader(f1);
			FileReader fR2 = new FileReader(f2);

			BufferedReader reader1 = new BufferedReader(fR1);
			BufferedReader reader2 = new BufferedReader(fR2);

			String line1 = null;
			String line2 = null;
			int flag=1;
			while ((flag==1) &&((line1 = reader1.readLine()) != null)&&((line2 = reader2.readLine()) != null))
			{
				if (!line1.equalsIgnoreCase(line2))
					flag=0;
				else
					flag=1;
			}
			reader1.close();
			reader2.close();
			return flag;
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/******************************************************
	 * Verify File
	 * @param actualFileName - actual file name
	 * @param expectedFileName - expected file name
	 * @param expectedStatus - expected status : true(match); false(not match)
	 */
	void verifyFile(String actualFileName, String expectedFileName, expectedStatus=true) {
		boolean _currentStatus = false;
		if (actualFileName.endsWith(".pdf")) _currentStatus = comparePDFFile(actualFileName, expectedFileName);
		else if (actualFileName.endsWith(".xlsx")) _currentStatus = compareExcel(actualFileName, expectedFileName);
		else if (actualFileName.toLowerCase().endsWith(".png") || actualFileName.toLowerCase().endsWith(".jpg")) _currentStatus = compareImage(actualFileName, expectedFileName);
		else _currentStatus = compareFile(actualFileName, expectedFileName);
		if(_currentStatus == expectedStatus) KeywordUtil.markPassed("verifyFile:: Actual: ${actualFileName} == Expected: ${expectedFileName}");
		else WebUiBuiltInKeywords.verifyEqual("verifyFile:: Actual: ${actualFileName} <> Expected: ${expectedFileName}", "FAILED");
	}

	/******************************************************
	 * Verify file content
	 * @param actualFilePath - actual file name
	 * @param expectedContent - expected content file name
	 */
	void verifyFileContent(String actualFilePath, String expectedContent) {
		String fileContent = ""
		if (actualFilePath.endsWith(".pdf")) fileContent = readPDFFile(actualFilePath)
		else fileContent = readFile(actualFilePath)
		if(fileContent.contains(expectedContent)) KeywordUtil.markPassed("verifyFileContent:: Actual: ${actualFilePath} == Expected content: ${expectedContent}")
		else KeywordUtil.markFailed("File ${actualFilePath} does not contain ${expectedContent}")
	}

	/////////////////////////// XLSX keywords/////////////////////////////////
	/******************************************************
	 * Get Sheet Object
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @return SheetObject
	 */
	private XSSFSheet getSheetObject(String excelFileName, Object sheet) {
		FileInputStream _file = new FileInputStream (new File(excelFileName));
		XSSFWorkbook _wb = new XSSFWorkbook(_file);
		XSSFSheet _sheetObject = null;
		if (sheet instanceof Number) _sheetObject = _wb.getSheetAt(sheet -1);
		else _sheetObject =  _wb.getSheet(sheet);
		_wb.close();
		_file.close();
		return _sheetObject;
	}

	private String convertCellValueToString(Cell cell) {
		if(cell == null) return null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING: return cell.getStringCellValue().trim();
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue().toString();
				} else {
					return cell.getNumericCellValue().toString();
				}
			case Cell.CELL_TYPE_BLANK: return "";
			case Cell.CELL_TYPE_BOOLEAN: return Boolean.toString(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_FORMULA: return cell.getCellFormula();
		}
	}

	/******************************************************
	 * Set Cell Value
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param rowIndex - the row index (start from 1)
	 * @param columnIndex - the column index (start from 1)
	 * @param value - the value to set to cell
	 */
	void setCellValue(String excelFileName, Object sheet, int rowIndex=1, int columnIndex=1, String value) {
		FileInputStream _file = new FileInputStream (new File(excelFileName));
		XSSFWorkbook _wb = new XSSFWorkbook(_file);
		XSSFSheet _sheet = null;
		if (sheet instanceof Number) _sheet = _wb.getSheetAt(sheet);
		else _sheet =  _wb.getSheet(sheet - 1);
		'Write data to excel'
		_sheet.getRow(rowIndex - 1).createCell(columnIndex -1).setCellValue(value);
		_file.close();
		FileOutputStream _outFile =new FileOutputStream(new File(excelFileName));
		_wb.write(_outFile);
		_outFile.close();
	}

	/******************************************************
	 * Get Cell Value
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param rowIndex - the row index (start from 1)
	 * @param columnIndex - the column index (start from 1)
	 * return the value of cell
	 */
	String getCellValue(String excelFileName, Object sheet, int rowIndex=1, int columnIndex=1) {
		XSSFSheet _sheet = getSheetObject(excelFileName, sheet);
		return convertCellValueToString(_sheet.getRow(rowIndex-1).getCell(columnIndex-1));
	}

	/******************************************************
	 * Change Sheet name
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheetIndex - the sheet index (start from 1)
	 * @param newSheetName - the new sheet name to change
	 */
	void changeExcelSheetName(String excelFileName, int sheetIndex=1, String newSheetName) {
		InputStream _file = new FileInputStream(excelFileName);
		XSSFWorkbook _wb = new XSSFWorkbook(_file);
		_wb.setSheetName(sheetIndex-1, newSheetName)
		FileOutputStream _outFile = new FileOutputStream(new File(excelFileName));
		_wb.write(_outFile);
		_file.close();
		_outFile.close();
	}

	/******************************************************
	 * Get Sheet name
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheetIndex - the sheet index (start from 1)
	 * @return the sheet name
	 */
	String getExcelSheetName(String excelFileName, int sheetIndex=1) {
		InputStream _file = new FileInputStream(excelFileName);
		XSSFWorkbook _wb = new XSSFWorkbook(_file);
		String _sheetName = _wb.getSheetName(sheetIndex-1)
		_wb.close();
		_file.close();
		return _sheetName;
	}

	boolean compareExcelFilesHaveSameNumberAndNameOfSheets(Workbook workbook1, Workbook workbook2) {
		// Get total sheets count from first excel file
		int sheetsInWorkbook1 = workbook1.getNumberOfSheets();
		// Get total sheets count from second excel file
		int sheetsInWorkbook2 = workbook2.getNumberOfSheets();

		// Compare if both excel files have same number of sheets
		if(sheetsInWorkbook1 != sheetsInWorkbook2) return false;

		// Verify if sheets have same name in both workbooks
		// Sheets may not be in same order in both excel. So I am relaxing order of sheets condition.
		// Change i as required.
		List<String> sheetsNameOfWb1 = new ArrayList<String>();
		List<String> sheetsNameOfWb2 = new ArrayList<String>();
		// Since we have already verified that both work books have same number of sheets so iteration can be done against any workbook sheet count
		for (int i = 0; i < sheetsInWorkbook1; i++) {
			// Retrieving sheet names from both work books and adding to different lists
			sheetsNameOfWb1.add(workbook1.getSheetName(i));
			sheetsNameOfWb2.add(workbook2.getSheetName(i));
		}
		if(!sheetsNameOfWb1.toString().equals(sheetsNameOfWb2.toString())) return false;
		return true;
	}

	// Check if both work books have same number of rows and columns in all sheets............."
	boolean compareSheetsInExcelFilesHaveSameRowsAndColumns(Workbook workbook1, Workbook workbook2) {
		int sheetCounts = workbook1.getNumberOfSheets();
		for (int i = 0; i < sheetCounts; i++) {
			Sheet s1 = workbook1.getSheetAt(i);
			Sheet s2 = workbook2.getSheetAt(i);
			int rowsInSheet1 = s1.getPhysicalNumberOfRows();
			int rowsInSheet2 = s2.getPhysicalNumberOfRows();
			if(rowsInSheet1 != rowsInSheet2) return false;

			Iterator<Row> rowInSheet1 = s1.rowIterator();
			Iterator<Row> rowInSheet2 = s2.rowIterator();
			while (rowInSheet1.hasNext()) {
				int cellCounts1 = rowInSheet1.next().getPhysicalNumberOfCells();
				int cellCounts2 = rowInSheet2.next().getPhysicalNumberOfCells();
				if(cellCounts1 != cellCounts2) return false;
			}
		}
		return true;
	}

	// Check if both work books have same data............."
	boolean compareDataInExcelBookAllSheets(Workbook workbook1, Workbook workbook2) {
		// Since we have already verified that both work books have same number of sheets so iteration can be done against any workbook sheet count
		int sheetCounts = workbook1.getNumberOfSheets();
		// So we will iterate through sheet by sheet
		for (int i = 0; i < sheetCounts; i++) {
			// Get sheet at same index of both work books
			Sheet s1 = workbook1.getSheetAt(i);
			Sheet s2 = workbook2.getSheetAt(i);

			// Iterating through each row
			int rowCounts = s1.getPhysicalNumberOfRows();
			for (int j = 0; j < rowCounts; j++) {
				// Iterating through each cell
				int cellCounts = s1.getRow(j).getPhysicalNumberOfCells();
				for (int k = 0; k < cellCounts; k++) {
					// Getting individual cell
					Cell c1 = s1.getRow(j).getCell(k);
					Cell c2 = s2.getRow(j).getCell(k);
					// Since cell have types and need o use different methods
					if (c1.getCellType() == (c2.getCellType())) {
						if (c1.getCellType() == Cell.CELL_TYPE_STRING) {
							String v1 = c1.getStringCellValue();
							String v2 = c2.getStringCellValue();
							if(!v1.equals(v2)) return false;
						}
						if (c1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							// If cell type is numeric, we need to check if data is of Date type
							if (DateUtil.isCellDateFormatted(c1) | DateUtil.isCellDateFormatted(c2)) {
								// Need to use DataFormatter to get data in given style otherwise it will come as time stamp
								DataFormatter df = new DataFormatter();
								String v1 = df.formatCellValue(c1);
								String v2 = df.formatCellValue(c2);
								if(!v1.equals(v2)) return false;
							} else {
								double v1 = c1.getNumericCellValue();
								double v2 = c2.getNumericCellValue();
								if(v1 != v2) return false;
							}
						}
						if (c1.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
							boolean v1 = c1.getBooleanCellValue();
							boolean v2 = c2.getBooleanCellValue();
							if(v1 != v2) return false;
						}
					} else
					{
						// If cell types are not same, exit comparison
						return false;
					}
				}
			}
		}
		return true;
	}

	boolean compareExcel(String actualExcelPath, String expectedExcelPath){
		try {
			String _sheetName = getExcelSheetName(expectedExcelPath, 1);
			changeExcelSheetName(actualExcelPath, 1, _sheetName);
			Workbook _wb1 = WorkbookFactory.create(new File(actualExcelPath));
			Workbook _wb2 = WorkbookFactory.create(new File(expectedExcelPath));
			if(!compareExcelFilesHaveSameNumberAndNameOfSheets(_wb1, _wb2)) return false;
			if(!compareSheetsInExcelFilesHaveSameRowsAndColumns(_wb1, _wb2)) return false;
			if(!compareSheetsInExcelFilesHaveSameRowsAndColumns(_wb1, _wb2)) return false;
			_wb1.close();
			_wb2.close();
		} catch (Exception e) {
			KeywordUtil.markWarning(String.format("compareExcel:: [%s] [%s] %s", actualExcelPath, expectedExcelPath, e.getMessage()));
			return false;
		}
		return true;
	}

	/******************************************************
	 * Verify Cell Value
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param rowIndex - the row index (start from 1)
	 * @param columnIndex - the column index (start from 1)
	 * @param expectedValue - the expected value
	 */
	void verifyCellValue(String excelFileName, Object sheet, int rowIndex=1, int columnIndex=1, expectedValue) {
		String currentValue = getCellValue(excelFileName, sheet, rowIndex, columnIndex);
		WebUiBuiltInKeywords.verifyEqual(currentValue, expectedValue)
	}

	/******************************************************
	 * Get list Values at Row
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param rowIndex - the row index (start from 1)
	 * @return the list values
	 */
	List<String> getListValuesAtRow(String excelFileName, Object sheet, int rowIndex=1) {
		List<String> _valueList = [];
		XSSFSheet _sheet = getSheetObject(excelFileName, sheet);
		int _columnCounts = _sheet.getRow(0).getPhysicalNumberOfCells();
		for (int i=0; i<_columnCounts; i++) {
			_valueList.add(convertCellValueToString(_sheet.getRow(rowIndex-1).getCell(i)));
		}
		return _valueList;
	}

	/******************************************************
	 * Verify list Values at Row
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param rowIndex - the row index (start from 1)
	 * @param listExpectedValues -  the list expected values
	 * @param sortOrder true (sort); false (not sort)
	 */
	void verifyListValuesAtRow(String excelFileName, Object sheetName, int rowIndex, List<String> listExpectedValues, boolean sortOrder=false) {
		if(sortOrder) WebUiBuiltInKeywords.verifyEqual(getListValuesAtRow(excelFileName,sheetName,rowIndex).sort(), listExpectedValues.sort());
		else WebUiBuiltInKeywords.verifyEqual(getListValuesAtRow(excelFileName,sheetName,rowIndex).toString(), listExpectedValues.toString());
	}

	/******************************************************
	 * Get list Values at Column
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param columnIndex - the column index (start from 1)
	 * @return the list values
	 */
	List<String> getListValuesAtColumn(String excelFileName, Object sheet, int columnIndex=1) {
		List<String> _valueList = [];
		XSSFSheet _sheet = getSheetObject(excelFileName, sheet);
		int _rowCounts = _sheet.getPhysicalNumberOfRows();
		for (int i=1; i<_rowCounts; i++) {
			_valueList.add(convertCellValueToString(_sheet.getRow(i).getCell(columnIndex-1)));
		}
		return _valueList;
	}

	/******************************************************
	 * Verify list Values at Column
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param columnIndex - the column index (start from 1)
	 * @param listExpectedValues -  the list expected values
	 * @param sortOrder true (sort); false (not sort)
	 */
	void verifyListValuesAtColumn(String excelFileName, Object sheet, int columnIndex, List<String> listExpectedValues, boolean sortOrder=false) {
		if(sortOrder) WebUiBuiltInKeywords.verifyEqual(getListValuesAtColumn(excelFileName,sheet,columnIndex).sort(), listExpectedValues.sort());
		else WebUiBuiltInKeywords.verifyEqual(getListValuesAtColumn(excelFileName,sheet,columnIndex).toString(), listExpectedValues.toString());
	}

	/******************************************************
	 * Get total Row
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @return the total number
	 */
	int countExcelRows(String excelFileName, Object sheet) {
		XSSFSheet _sheet = getSheetObject(excelFileName, sheet);
		int _countRows = _sheet.getPhysicalNumberOfRows() -1;
		return _countRows;
	}

	/******************************************************
	 * Verify total Row
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param expectedNumber - the expected number
	 */
	void verifyTotalExcelRows(String excelFileName, Object sheetName, int expectedNumber) {
		WebUiBuiltInKeywords.verifyEqual(countExcelRows(excelFileName,sheetName), expectedNumber);
	}

	/******************************************************
	 * Get total Column
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 */
	int countExcelColumns(String excelFileName, Object sheet) {
		XSSFSheet _sheet = getSheetObject(excelFileName, sheet);
		int _countColumns = _sheet.getRow(0).getPhysicalNumberOfCells();
		return _countColumns;
	}

	/******************************************************
	 * Verify total Column
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param expectedNumber - the expected number
	 */
	void verifyTotalExcelColumns(String excelFileName, Object sheetName, int expectedNumber) {
		WebUiBuiltInKeywords.verifyEqual(countExcelColumns(excelFileName,sheetName), expectedNumber);
	}

	/******************************************************
	 * Get list row values by filter value
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param findValue - the value to find row index
	 * @return the list values of row at findValue
	 */
	List<String> getListRowValuesByFilter(String excelFileName, Object sheet, String findValue) {
		List<String> _valueList = [];
		XSSFSheet _sheet = getSheetObject(excelFileName, sheet);
		int _columnCounts = _sheet.getRow(0).getPhysicalNumberOfCells();
		int _countRows = _sheet.getPhysicalNumberOfRows();
		for (int i=0; i<_countRows; i++) {
			for (int j=0; j<_columnCounts; j++) {
				if (convertCellValueToString(_sheet.getRow(i).getCell(j)) == findValue.trim()) {
					return getListValuesAtRow(excelFileName, sheet, i+1);
				}
			}
		}
		return _valueList;
	}

	/******************************************************
	 * Verify list Values at Row by filter
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param findValue - the value to find row index
	 * @param listExpectedValues - the list expected values
	 * @param sortOrder true (sort); false (not sort)
	 */
	void verifyListRowValuesByFilter(String excelFileName, Object sheet, String findValue, List<String> listExpectedValues, boolean sortOrder=false) {
		if(sortOrder) WebUiBuiltInKeywords.verifyEqual(getListRowValuesByFilter(excelFileName,sheet,findValue).sort(), listExpectedValues.sort());
		else WebUiBuiltInKeywords.verifyEqual(getListRowValuesByFilter(excelFileName,sheet,findValue).toString(), listExpectedValues.toString());
	}

	/******************************************************
	 * Verify list contains values at Row by filter
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param findValue - the value to find row index
	 * @param listExpectedValues - the list expected values
	 */
	void verifyListContainsRowValuesByFilter(String excelFileName, Object sheet, String findValue, List<String> listExpectedValues) {
		WebUiBuiltInKeywords.verifyEqual(getListRowValuesByFilter(excelFileName,sheet,findValue).containsAll(listExpectedValues), true);
	}

	/******************************************************
	 * Get list column values by filter value
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param findValue - the value to find column index
	 * @return the list values of column at findValue
	 */
	List<String> getListColumnValuesByFilter(String excelFileName, Object sheet, String findValue) {
		List<String> _valueList = [];
		XSSFSheet _sheet = getSheetObject(excelFileName, sheet);
		int _columnCounts = _sheet.getRow(0).getPhysicalNumberOfCells();
		int _countRows = _sheet.getPhysicalNumberOfRows();
		for (int i=0; i<_countRows; i++) {
			for (int j=0; j<_columnCounts; j++) {
				if (convertCellValueToString(_sheet.getRow(i).getCell(j)) == findValue.trim()) {
					return getListValuesAtColumn(excelFileName, sheet, j+1);
				}
			}
		}
		return _valueList;
	}

	/******************************************************
	 * Verify list Values at Column by filter
	 * @param excelFileName - the excel file name (*.xlsx ; *.xls)
	 * @param sheet - the sheet name or index (start from 1)
	 * @param findValue - the value to find column index
	 * @param listExpectedValues -  the list expected values
	 * @param sortOrder true (sort); false (not sort)
	 */
	void verifyListColumnValuesByFilter(String excelFileName, Object sheet, String findValue, List<String> listExpectedValues, boolean sortOrder=false) {
		if(sortOrder) WebUiBuiltInKeywords.verifyEqual(getListColumnValuesByFilter(excelFileName,sheet,findValue).sort(), listExpectedValues.sort());
		else WebUiBuiltInKeywords.verifyEqual(getListColumnValuesByFilter(excelFileName,sheet,findValue).toString(), listExpectedValues.toString());
	}

	/////////////////////////// PDF keywords/////////////////////////////////
	/******************************************************
	 * Get pdf content file
	 * @param pdfFilePath - the PDF file name
	 * @return content file
	 */
	String readPDFFile(String pdfFilePath) {
		String _text = null
		PDDocument _document = PDDocument.load(new File(pdfFilePath));
		if (!_document.isEncrypted()) {
			PDFTextStripper stripper = new PDFTextStripper();
			_text = stripper.getText(_document);
		}
		_document.close();
		_text = _text.replace(" ", " ") //Replace ANSI character
		// TODO: move below logic to another function
		// Remove dynamic date
		List<String> _newlist = [];
		String _keyDate = helper.getUnique("yyyy") + " Energy Worldnet";
		_text.split("\n").each{
			if(!it.contains(_keyDate)) _newlist.add(it);
		}
		return _newlist.join("\n");
	}

	/******************************************************
	 * Compare PDF File
	 * @param firstFile - the first pdf file
	 * @param secondFile - the second pdf file
	 * @return result (true/false)
	 */
	boolean comparePDFFile (String firstFile, String secondFile) {
		try {
			String _pdf1 = readPDFFile(firstFile);
			String _pdf2 = readPDFFile(secondFile);
			return _pdf1==_pdf2
		} catch (Exception e) {
			KeywordUtil.markWarning(String.format("comparePDFFile:: [%s][&s] %s", firstFile, secondFile, e.getMessage()));
			return false;
		}
	}

	/******************************************************
	 * Unzip file
	 * @param zipFileName - the zip file name
	 * @param destination - the directory path to unzip
	 */
	void unzipFile(String zipFileName, String destination) {
		def zipFile = new ZipFile(new File(zipFileName))
		zipFile.entries().each { it ->
			def path = Paths.get(destination + '\\' + it.name)
			if(it.directory){
				Files.createDirectories(path)
			}
			else {
				def parentDir = path.getParent()
				if (!Files.exists(parentDir)) {
					Files.createDirectories(parentDir)
				}
				Files.copy(zipFile.getInputStream(it), path)
			}
		}
	}
}
