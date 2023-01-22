package com.ewn.common.page

import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

import com.ewn.fw.lib.BaseActions
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable


public class UploadFile {
	static int timeoutSecond = 10

	/**
	 * Upload file using robot.
	 *
	 * @param object The object from which we can click to choose files.
	 * @param file The full path of the file that needs to upload.
	 */
	static void uploadFileUsingRobot(TestObject browseFileObject, String file) {
		BaseActions.clickJS(browseFileObject)
		
		Robot robot = new Robot()
		StringSelection path = new StringSelection(file)
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null)
		BaseActions.delay(timeoutSecond)
		robot.keyPress(KeyEvent.VK_CONTROL)
		robot.keyPress(KeyEvent.VK_V)
		robot.keyRelease(KeyEvent.VK_CONTROL)
		robot.keyRelease(KeyEvent.VK_V)
		BaseActions.delay(timeoutSecond)
		robot.keyPress(KeyEvent.VK_ENTER)
		BaseActions.delay(GlobalVariable.ShortTime)
		robot.keyRelease(KeyEvent.VK_ENTER)
	}
}