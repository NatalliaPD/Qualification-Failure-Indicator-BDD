package com.ewn.request.authorizations

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

public class ConnectionsPage extends BasePage<ConnectionsPage> {

	//////////////////// Button ////////////////////////////
	ConnectionsPage clickSearch(int index=1) {
		BaseActions.click(webButton("Search", index))
		return this
	}

	ConnectionsPage clickAdd(int index=1) {
		BaseActions.clickJS(webButton("Add", index))
		return this
	}

	ConnectionsPage clickSave(int index=1) {
		BaseActions.clickJS(webButton("Save", index))
		return this
	}

	ConnectionsPage clickEdit(int index=1) {
		BaseActions.clickJS(webButton("Edit", index))
		return this
	}

	ConnectionsPage clickDelete(int index=1) {
		BaseActions.clickJS(webButton("Delete", index))
		return this
	}

	ConnectionsPage clickCancel(int index=1) {
		BaseActions.click(webButton("Cancel", index))
		return this
	}

	//////////////////// Combobox ////////////////////////////
	ConnectionsPage selectOptionStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Status:", index), item, true)
		return this
	}

	ConnectionsPage selectMultipleOptionSharingWithMe(String items) {
		this.selectMultipleOption("Sharing with Me:", items)
		return this
	}

	ConnectionsPage selectMultipleOptionViewingMyProducts(String items) {
		this.selectMultipleOption("Viewing My Products:", items)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	ConnectionsPage moveAvailableProducts(String listItems) {
		this.moveAvailableItems("Available Products", listItems)
		return this
	}

	ConnectionsPage moveSelectedProducts(String listItems) {
		this.moveSelectedItems("Selected Products", listItems)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	ConnectionsPage inputName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Name:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name="Results", int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

}
