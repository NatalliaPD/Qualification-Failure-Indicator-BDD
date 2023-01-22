package com.ewn

import com.ewn.common.page.BasePage
import com.ewn.fw.lib.BaseActions

public class MyProfilePage extends BasePage<MyProfilePage> {

	//////////////////// Button ////////////////////////////
	MyProfilePage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	MyProfilePage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	MyProfilePage clickEdit(int index=1) {
		BaseActions.click(webButton("Edit", index))
		return this
	}

}
