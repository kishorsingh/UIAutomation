package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;
import java.util.Random;

public class EditContactPage extends BasePageObject<EditContactPage> {



	@FindBy(xpath = "//h1[contains(text(),'Edit Contact')]")
	WebElement editContactHeader;

	@FindBy(xpath = "//button[@accesskey='S']")
	WebElement cSaveAndClose;

	@FindBy(xpath = "//button[text()='Save and Continue']")
	WebElement saveAndContinueButton;

	@FindBy(xpath = "//div[text()='Profile']")
	WebElement clickProfile;

	@FindBy(xpath = "//a[text()='Yellow Driste (NEW YORK, US)']")
	WebElement currentAccount;

	@FindBy(xpath = "(//*[text()='Yellow Driste (NEW YORK, US)']/following::img[2]")
	WebElement removeIcon;

	@FindBy(xpath = "(//*[text()='Account']/following::input)[1]")
	WebElement typeAccount;

	@FindBy(xpath = "//*[contains(text(),'Edit Contact:')]")
	WebElement accountText;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(editContactHeader) == null) {
			throw new TestErrorException("The EditContact Page  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("Edit Contact page is Loaded");
	}



	public void clickSaveAndClose() {
		StepReport.info("Click Save and Close");
		DriverUtil.sleep(2000L);
		cSaveAndClose.click();
		DriverUtil.sleep(10000L);
	}

	public void clickProfile(){
		String CurrentAcc="";
		StepReport.info("Click Profile");
		DriverUtil.sleep(2000L);
		clickProfile.click();
		CurrentAcc = currentAccount.getText();
		StepReport.info("Currect Account:",CurrentAcc);
	}

	public void removeCurrentAccount(){
		StepReport.info("Remove Current Account");
		DriverUtil.sleep(2000L);
		try{
			removeIcon.click();
		}catch (Exception e){
			e.getMessage();
			StepReport.info("Error:", e.getMessage());
		}

	}

	public String typeAccount(){
		String NewAccount="BIQA_PRM_Cooper_Design2 (SEATTLE, US)";
		StepReport.info("Type New Account:",NewAccount);
		DriverUtil.sleep(2000L);
		typeAccount.sendKeys(NewAccount);
		DriverUtil.sleep(2000L);
		typeAccount.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		return NewAccount;
	}

	public String getText(){
		String AccountText="";
		StepReport.info("Check Account Text");
		DriverUtil.sleep(2000L);
		AccountText=accountText.getText();
		String[] x = AccountText.split(":");
		AccountText=x[1].trim();
		StepReport.info("New Account = ",AccountText);
		return AccountText;
	}



}
