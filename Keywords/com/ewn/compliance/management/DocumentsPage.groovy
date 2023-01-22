package com.ewn.compliance.management

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

public class DocumentsPage extends BasePage<DocumentsPage> {
	//////////////////// Button ////////////////////////////
	DocumentsPage clickRunUploadDocument(int index=1) {
		BaseActions.click(webButton("Upload Document", index))
		return this
	}

	DocumentsPage clickSearch(int index=1) {
		BaseActions.clickJS(webButton("Search", index))
		return this
	}

	DocumentsPage clickUpload(int index=1) {
		BaseActions.click(webButton("Upload", index))
		return this
	}
	
	DocumentsPage clickAdd(int index=1) {
		BaseActions.click(webButton("Add", index))
		return this
	}
	
	DocumentsPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}
	
	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}
}
