package com.ewn.account.management

import com.ewn.common.page.BasePage
import com.ewn.fw.lib.BaseActions

public class CompanyProfilePage extends BasePage<CompanyProfilePage> {

	//////////////////// Button ////////////////////////////
	CompanyProfilePage clickEdit(int index=1) {
		BaseActions.scrollUpAndClick(webButton("Edit", index))
		return this
	}

	CompanyProfilePage clickAddCertificateTemplate() {
		BaseActions.click(webButton("Add Certificate Template", 1))
		return this
	}

	CompanyProfilePage clickSave(int index=1) {
		BaseActions.click(webButton("Save", index))
		return this
	}

	//////////////////// Radio Button ////////////////////////////
	CompanyProfilePage selectDefaultView(String label) {
		selectRadio(label)
		return this
	}

	//////////////////// Combobox ////////////////////////////
	CompanyProfilePage selectOptionProctoringOptions(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Proctoring Options:", index), item, true)
		return this
	}

	CompanyProfilePage selectOptionCountry(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Country:", index), item, true)
		return this
	}

	CompanyProfilePage selectOptionState(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("State:", index), item, true)
		return this
	}

	CompanyProfilePage selectOptionStatus(int index=1, String item) {
		BaseActions.selectOptionByLabel(webCombobox("Status:", index), item, true)
		return this
	}

	//////////////////// Label ////////////////////////////
	CompanyProfilePage verifyLabelAssessmentWaitPeriod(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Assessment Wait Period:", index), expectedText)
		return this
	}

	CompanyProfilePage verifyLabelMaximumTriesPerAssessment(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Maximum Tries Per Assessment:", index), expectedText)
		return this
	}

	CompanyProfilePage verifyLabelProctoringOptions(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Proctoring Options:", index), expectedText)
		return this
	}

	CompanyProfilePage verifyLabelRequireTrainingPrerequisite(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Require Training Prerequisite:", index), expectedText)
		return this
	}

	CompanyProfilePage verifyLabelWaitAfter(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Wait After:", index), expectedText)
		return this
	}

	CompanyProfilePage verifyLabelWaitFor(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Wait For:", index), expectedText)
		return this
	}

	CompanyProfilePage verifyLabelTrainingCompletionExpiration(int index=1, String expectedText) {
		BaseActions.verifyElementTextContent(webLabelValue("Training Completion Expiration:", index), expectedText)
		return this
	}

	//////////////////// Textbox ////////////////////////////
	CompanyProfilePage inputTrainingCompletionExpiration(int index=1, Object text) {
		BaseActions.setText(webTextbox("Training Completion Expiration:", index), text.toString())
		return this
	}

	CompanyProfilePage inputPhysicalAddress1(int index=1, Object text) {
		BaseActions.setText(webTextbox("Physical Address 1:", index), text.toString())
		return this
	}

	CompanyProfilePage inputPhysicalAddress2(int index=1, Object text) {
		BaseActions.setText(webTextbox("Physical Address 2:", index), text.toString())
		return this
	}

	CompanyProfilePage inputCity(int index=1, Object text) {
		BaseActions.setText(webTextbox("City:", index), text.toString())
		return this
	}

	CompanyProfilePage inputEmail(int index=1, Object text) {
		BaseActions.setText(webTextbox("Email:", index), text.toString())
		return this
	}

	CompanyProfilePage inputPhoneNumber(int index=1, Object text) {
		BaseActions.setText(webTextbox("Phone Number:", index), text.toString())
		return this
	}

	CompanyProfilePage inputExtension(int index=1, Object text) {
		BaseActions.setText(webTextbox("Extension:", index), text.toString())
		return this
	}

	CompanyProfilePage inputFaxNumber(int index=1, Object text) {
		BaseActions.setText(webTextbox("Fax Number:", index), text.toString())
		return this
	}

	CompanyProfilePage inputName(int index=1, Object text) {
		BaseActions.setText(webTextbox("Name:", index), text.toString())
		return this
	}

	CompanyProfilePage inputMaximumTriesPerAssessment(int index=1, Object text) {
		BaseActions.setText(webTextbox("Maximum Tries Per Assessment:", index), text.toString())
		return this
	}

	CompanyProfilePage inputProctoringOptions(int index=1, Object text) {
		BaseActions.setText(webTextbox("Proctoring Options:", index), text.toString())
		return this
	}

	CompanyProfilePage inputWaitAfter(int index=1, Object text) {
		BaseActions.setText(webTextbox("Wait After:", index), text.toString())
		return this
	}

	CompanyProfilePage inputWaitFor(int index=1, Object text) {
		BaseActions.setText(webTextbox("Wait For:", index), text.toString())
		return this
	}

	//////////////////// Actions ////////////////////////////
	CompanyProfilePage editExternalSettings(Object waitAfter, Object waitFor, Object maximumTriesPerAssessment, String proctoringOptions, Object trainingCompletionExpiration) {
		this.selectTab("External Settings")
		clickEdit(4)
		selectCheckbox("Assessment Wait Period:", 1)
		inputWaitAfter(waitAfter)
		inputWaitFor(waitFor)
		inputMaximumTriesPerAssessment(2, maximumTriesPerAssessment)
		selectOptionProctoringOptions(2, proctoringOptions)
		selectCheckbox("Require Training Prerequisite:", 1)
		inputTrainingCompletionExpiration(trainingCompletionExpiration)
		clickSave(6)
		return this
	}
}

