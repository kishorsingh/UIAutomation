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

import java.util.Random;

public class CreateContactPage extends BasePageObject<CreateContactPage> {



	@FindBy(xpath = "//h1[text()='Create Contact']")
	WebElement createContactHeader;

	@FindBy(xpath = "//*[contains(@id,'pt1:it1::content')]")
	WebElement cLastName;

	@FindBy(xpath = "//*[contains(@id,'pt1:it2::content')]")
	WebElement cFirstName;

	@FindBy(xpath = "//*[contains(@id,'inputText2::content')]")
	WebElement cAddressLine1;

	@FindBy(xpath = "//*[contains(@id,'inputComboboxListOfValues1::content')]")
	WebElement cStateCode;

	@FindBy(xpath = "//*[contains(@id,'inputComboboxListOfValues4::content')]")
	WebElement cPostCode;

	@FindBy(xpath = "(//*[text()='Account']/following::input)[1]")
	WebElement cAccount;

	@FindBy(xpath = "//*[contains(@id,'pt1:r2:1:pt1:AP1:cb1')]")
	WebElement cSaveAndClose;

	@FindBy(xpath = "//button[text()='Save and Continue']")
	WebElement saveAndContinueButton;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSrZPM_PARTNERS_CARD:0:_FOTsr1:0:pt1:r3:0:pt1:r3:0:r4:1:pt1:it11::content']")
	WebElement typePartnerEmailAddress;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(createContactHeader) == null) {
			throw new TestErrorException("The partnerName  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("Create Contact page is Loaded");
	}

	public String typeContactLastName() {
		StepReport.info("Type Contact Last Name");
		String cName = "TestContact";
		int num = 100;
		Random r = new Random();
		num = num + r.nextInt(10000);
		cName = cName+String.valueOf(num);
		cLastName.sendKeys(cName);
		DriverUtil.sleep(2000L);
		cLastName.sendKeys(Keys.RETURN);
		return cName;
	}
	public String typeContactFirstName() {
		StepReport.info("Type Contact First Name");
		String cName = "Test";
		int num = 100;
		Random r = new Random();
		num = num + r.nextInt(10000);
		cName = cName+String.valueOf(num);
		cFirstName.sendKeys(cName);
		DriverUtil.sleep(2000L);
		cFirstName.sendKeys(Keys.RETURN);
		return cName;
	}

	public void typeContactAddressLine1() {
		String cAddrLine1 = "Manhattan";
		StepReport.info("Type Contact Address",cAddrLine1);
		DriverUtil.sleep(2000L);
		cAddressLine1.sendKeys(cAddrLine1);
		DriverUtil.sleep(2000L);
		cAddressLine1.sendKeys(Keys.RETURN);
	}

	public void typeContactState() {
		String cState = "NY";
		StepReport.info("Type Contact State",cState);
		DriverUtil.sleep(2000L);
		cStateCode.sendKeys(cState);
		DriverUtil.sleep(2000L);
		cStateCode.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		cPostCode.sendKeys(Keys.TAB);
	}

	public void typeContactPostCode() {
		StepReport.info("Type Contact State","10020");
		DriverUtil.sleep(2000L);
		cPostCode.sendKeys("10020");
		DriverUtil.sleep(2000L);
		cPostCode.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		cPostCode.sendKeys(Keys.TAB);
	}

	public void typeContactAccount() {
		StepReport.info("Type Contact Account","Yellow Driste (NEW YORK, US)");
		DriverUtil.sleep(2000L);
		cAccount.sendKeys("Yellow Driste (NEW YORK, US)");
		DriverUtil.sleep(2000L);
		cAccount.sendKeys(Keys.RETURN);
	}

	public void clickSaveAndClose() {
		StepReport.info("Click Save and Close");
		DriverUtil.sleep(2000L);
		cSaveAndClose.click();
		DriverUtil.sleep(3000L);
	}

	public EditContactPage clickSaveAndContinue() {
		StepReport.info("Click Save And Continue Button");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(saveAndContinueButton);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(saveAndContinueButton);
		DriverUtil.sleep(2000L);
		EditContactPage editContactPage = PageFactory.getPage(EditContactPage.class);
		PossibleDuplicates possibleDuplicates = PageFactory.getPage(PossibleDuplicates.class);
		if(possibleDuplicates.pageLoaded())
		{
			possibleDuplicates.clickContinueWithCreateContact();
		}
		editContactPage.isLoaded();
		return editContactPage;

	}
	public void typePartnerEmailAddress() {
		StepReport.info("Type Partner Email Address");
		DriverUtil.sleep(3000L);
		String partnerEmailAddress = "venkatesh.babu.vinnakota@oracle.com";
		typePartnerEmailAddress.sendKeys(partnerEmailAddress);
		DriverUtil.sleep(3000L);
		typePartnerEmailAddress.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);
	}
}
