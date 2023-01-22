package com.ewn.evaluation.authoring

import com.ewn.common.page.BasePage
import com.ewn.common.page.OnDataTable
import com.ewn.fw.lib.BaseActions
import com.ewn.fw.lib.Pages

public class SkillFamiliesPage extends BasePage<SkillFamiliesPage> {

	//////////////////// Button ////////////////////////////
	SkillFamiliesPage clickAdd(int index=1) {
		BaseActions.click(webButton("Add", index))
		return this
	}
	
	SkillFamiliesPage clickSave(int index=1) {
		BaseActions.click(webVisibleButton("Save"))
		return this
	}
	
	SkillFamiliesPage clickYes() {
		BaseActions.click(webButton("Yes"))
		return this
	}
	
	SkillFamiliesPage clickDelete() {
		BaseActions.click(webButton("Delete"))
		return this
	}
	
	SkillFamiliesPage clickSearch() {
		BaseActions.click(webButton("Search"))
		return this
	}
	
	//////////////////// Label ////////////////////////////
	SkillFamiliesPage verifyLabelType(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Type:", index), expectedText)
		return this
	}

	SkillFamiliesPage verifyLabelName(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Name:", index), expectedText)
		return this
	}

	SkillFamiliesPage verifyLabelDescription(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Description:", index), expectedText)
		return this
	}

	SkillFamiliesPage verifyLabelActive(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Active:", index), expectedText)
		return this
	}
	
	SkillFamiliesPage verifyTotalRecords(int index=1, Object expectedRecords) {
		BaseActions.verifyElementText(webTotalRecords(index), expectedRecords.toString())
		return this
	}
	
	//////////////////// Combobox ////////////////////////////
	SkillFamiliesPage selectOptionType(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Type:", index), item, true)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	SkillFamiliesPage inputDescription(int index=1, Object text) {
		BaseActions.setText(webTextbox("Description:", index), text.toString())
		return this
	}

	SkillFamiliesPage inputName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Name:", index), text.toString())
		return this
	}

	//////////////////// Table ////////////////////////////
	OnDataTable onTable(String name, int index=1) {
		return Pages.As(OnDataTable).setTable(name, index)
	}

	SkillFamiliesPage verifyEvaludationAdded(String id, String title) {
		Pages.As(OnDataTable).setTable("Evaluations", 1).verifyValueOnTable(id).verifyValueOnTable(title)
		return this
	}

	//////////////////// MoveItems ////////////////////////////
	SkillFamiliesPage moveAvailableEvaluations(String listItems) {
		this.moveAvailableItems("Available Evaluations", listItems)
		return this
	}

	//////////////////// Action ////////////////////////////
	SkillFamiliesPage deleteSkillFamily(String skillFamilyName, String Type) {
		selectTab("Search").inputName(skillFamilyName).selectOptionType(Type).clickSearch()
		Pages.As(OnDataTable).setTable("Results", 1).selectCheckboxOnTable("", 5)
		clickDelete()
		clickYes()
		return this
	}

	SkillFamiliesPage createSkillFamily(String name, String description) {
		clickAdd()
		inputName(2, name).inputDescription(1, description)
		clickSave()
		return this
	}

	SkillFamiliesPage viewSkillFamily(Object name) {
		Pages.As(OnDataTable).setTable("Results", 1).clickOnIcon(name, "View")
		return this
	}
}
