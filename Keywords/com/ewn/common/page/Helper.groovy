package com.ewn.common.page

import static java.util.stream.Collectors.joining;

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver

import com.kms.katalon.core.webui.driver.DriverFactory

public class Helper {
	/******************************************************
	 * Plus date time
	 * @param date - the date1 format of text
	 * @param formatDate - the date format of text "yyyy/MM/dd HH:mm:ss"
	 * @param plusType - year;month;week;month;day;hour;minute;second;millisecond
	 * @param number - the number to plus
	 * @return the new date
	 */
	String plusDateTime(String date, String formatDate, String plusType, int number) {
		try {
			SimpleDateFormat ft = new SimpleDateFormat(formatDate);
			Date date1 = ft.parse(date);
			date1 = plusDateTime(date1, plusType, number);
			return ft.format(date1);
		} catch (Exception e) {
			return null;
		}
	}

	Date plusDateTime(Date date, String plusType, int number) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			switch (plusType.toLowerCase()) {
				case "year":
					c.add(Calendar.YEAR, number);
					break;
				case "month":
					c.add(Calendar.MONTH, number);
					break;
				case "week":
					c.add(Calendar.MONTH, number);
					break;
				case "day":
					c.add(Calendar.DAY_OF_MONTH, number);
					break;
				case "hour":
					c.add(Calendar.HOUR, number);
					break;
				case "minute":
					c.add(Calendar.MINUTE, number);
					break;
				case "second":
					c.add(Calendar.SECOND, number);
					break;
				case "millisecond":
					c.add(Calendar.MILLISECOND, number);
					break;
			}
			return c.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/******************************************************
	 * Get difference Second between two date string
	 * @param date1 - the date1 format of text
	 * @param date2 - the date2 format of text
	 * @param formatDate - the date format of text "yyyy/mm/dd"
	 * @return the second number
	 */
	Long diffInSecond(String date1, String date2, String formatDate) {
		try {
			DateTimeFormatter ft = DateTimeFormatter.ofPattern(formatDate);
			LocalDateTime firstDate = LocalDateTime.parse(date1, ft);
			LocalDateTime secondDate = LocalDateTime.parse(date2, ft);
			return Math.abs(ChronoUnit.SECONDS.between(firstDate,secondDate));
		} catch(Exception e){
			return null;
		}
	}

	/******************************************************
	 * Generate unique string by system date
	 * @param formatDate - The date format // "E yyyy.MM.dd 'at' HH:mm:ss a zzz" => Sat 2018.08.11 at 05:09:21 PM UTC
	 * @return The string Ex: "yyyyMMDD" => 20181025
	 */
	String getUnique(Date date=null, String formatDate) {
		SimpleDateFormat _ft = new SimpleDateFormat (formatDate);
		if(date) return _ft.format(date);
		else return _ft.format(new Date( ));
	}

	/******************************************************
	 * Get format Date string to other one format
	 * @param text - the text "2020/05/15"
	 * @param formatDate - the date format of text "yyyy/mm/dd"
	 * @param newFormatDate - the new date format to convert "dd-MMM-yy"
	 * @return the new date format: "15-May-20"
	 */
	String formatDateString(String text, String formatDate, String newFormatDate) {
		try {
			Date date = new SimpleDateFormat(formatDate).parse(text);
			return getUnique(date, newFormatDate);
		} catch(Exception e){
			return null;
		}
	}

	/******************************************************
	 * Get random numeric
	 * @param min - The min number
	 * @param max - The max number
	 * @return The numeric between min and max number
	 */
	Integer getRandomInt(int min, int max){
		return (int)((Math.random()*((max-min)+1)) + min);
	}

	/******************************************************
	 * Get numeric in string
	 * @param string - The string
	 * @return The numeric Ex: "You are 24 years old" => 24
	 */
	def getNumericInString(String string) {
		return Integer.parseInt(string.replaceAll("[^0-9]", ""));
	}

	/******************************************************
	 * Convert RGB to hex
	 * @param rgbFormat - the rgb(123, 34, 223)
	 * @return hex (#fffff)
	 */
	String convertRGBtoHEX(String rgbFormat) {
		String[] arrRGB = rgbFormat.replace("(", ",").replace(")", "").split(",");
		String hexFormat = String.format("#%02x%02x%02x", Integer.valueOf(arrRGB[1].trim()),Integer.valueOf(arrRGB[2].trim()),Integer.valueOf(arrRGB[3].trim()));
		return hexFormat.toLowerCase();
	}
	
	List<String> naturalSort(List<String> listValues) {
		// https://stackoverflow.com/questions/1262239/natural-sort-order-string-comparison-in-java-is-one-built-in/39737502#39737502
		RuleBasedCollator localRules = (RuleBasedCollator) Collator.getInstance()

		String extraRules = IntStream.range(0, 100).mapToObj(String.&valueOf).collect(joining(" < "))
		println "extraRules=" + extraRules
		RuleBasedCollator c = new RuleBasedCollator(localRules.getRules() + " & " + extraRules)
		Collections.sort(listValues, c)
		return listValues
	}

	/******************************************************
	 * Get Sort order status
	 * @param listValues - the list values
	 * @param dataType - number; string; dateFormat(yyyy-MM-dd hh:mm; dd/MM/yyyy;...)
	 * @return normal; descending; ascending
	 */
	String getSortOrderStatus(List<String> listValues, String dataType){
		if(listValues.stream().distinct().count() <= 1) return "normal,descending,ascending";
		String status = "normal";
		try {
			List<Object> convertList;
			List<Object> descendingList;
			List<Object> ascendingList;
			switch (dataType.toLowerCase()) {
				case "number":
					List<Double> convertN = new ArrayList<Double>();
					List<Double> descendingN  = new ArrayList<Double>();
					List<Double> ascendingN  = new ArrayList<Double>();
					for (String value : listValues) {
						Double newValue = Double.parseDouble(value);
						convertN.add(newValue);
						descendingN.add(newValue);
						ascendingN.add(newValue);
					}
					ascendingN.sort(Comparator.naturalOrder());
					descendingN.sort(Comparator.reverseOrder());
					convertList = Collections.singletonList(convertN);
					descendingList = Collections.singletonList(descendingN);
					ascendingList = Collections.singletonList(ascendingN);
					break;
				case "string":
					List<String> convert = new ArrayList<String>();
					List<String> descending  = new ArrayList<String>();
					List<String> ascending  = new ArrayList<String>();
					for (String value : listValues) {
						convert.add(value);
						descending.add(value);
						ascending.add(value);
					}
					ascending.sort(Comparator.naturalOrder());
					descending.sort(Comparator.reverseOrder());
					convertList = Collections.singletonList(convert);
					descendingList = Collections.singletonList(descending);
					ascendingList = Collections.singletonList(ascending);
					break;
				default:
					List<Date> convertD = new ArrayList<Date>();
					List<Date> descendingD  = new ArrayList<Date>();
					List<Date> ascendingD  = new ArrayList<Date>();
					for (String value : listValues) {
						Date newValue = new SimpleDateFormat(dataType).parse(value);
						convertD.add(newValue);
						descendingD.add(newValue);
						ascendingD.add(newValue);
					}
					ascendingD.sort(Comparator.naturalOrder());
					descendingD.sort(Comparator.reverseOrder());
					convertList = Collections.singletonList(convertD);
					descendingList = Collections.singletonList(descendingD);
					ascendingList = Collections.singletonList(ascendingD);
					break;
			}
			if(descendingList.equals(convertList)) return "descending";
			if(ascendingList.equals(convertList)) return "ascending";
		} catch (Exception e) {
			return "normal";
		}
		return status;
	}

	void setTextToClipboard(String text){
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	WebDriver getNewDriver() {
		switch(DriverFactory.getExecutedBrowser().getName()) {
			case "FIREFOX_DRIVER":
				System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverPath())
				return new FirefoxDriver()
			case "CHROME_DRIVER":
				System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())
				return new ChromeDriver()
			case "EDGE_CHROMIUM_DRIVER":
				System.setProperty("webdriver.edge.driver", DriverFactory.getEdgeChromiumDriverPath())
				return new EdgeDriver()
			default: throw new Exception("Unsupported browser type ${DriverFactory.getExecutedBrowser().getName()}")
		}
	}
}
