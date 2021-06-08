package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class CreatePartner extends BasePageObject<CreatePartner> {
	@FindBy(xpath = "//*[contains(@name,'StyleName')]")
	WebElement styleName;

	@FindBy(xpath = "//button[text()='Create']")
	WebElement createButton;

	@FindBy(xpath = "//*[contains(@id,'Supplier::content')]")
	WebElement supplier;

	@FindBy(xpath = "//*[contains(@id,'SupplierSite::content')]")
	WebElement supplierSite;

	@FindBy(xpath = "//*[@title='Name']/following::input[1]")
	WebElement partnerName;

	@FindBy(xpath = "//*[contains(@id,'inputText2::content')]")
	WebElement partnerAddressLine1;

	@FindBy(xpath = "//*[contains(@id,'inputComboboxListOfValues1::content')]")
	WebElement partnerState;

	@FindBy(xpath = "//*[contains(@id,'inputComboboxListOfValues4::content')]")
	WebElement partnerPostalCode;

	@FindBy(xpath = "//*[text()='S']")
	WebElement saveAndCloseButton;

	//.//*[@id='_FOpt1:_FOr1:0:_FOSrZPM_PARTNERS_CARD:0:_FOTsr1:0:pt1:r1:1:pt1:AP1:cb1']
	// @FindBy(xpath = "//*[text()='Save and Continue']")
	@FindBy(xpath = "//button[contains(@id,'_FOTsr1:0:pt1:r1:1:pt1:AP1:cb1')]")
	WebElement saveAndContinueButton;

	@FindBy(xpath = "//*[text()='Registered']")
	WebElement selectPartnerStatusAsRegistered;

	@FindBy(xpath = ".//*[@id='_FOpt1:_FOr1:0:_FOSrZPM_PARTNERS_CARD:1:_FOTsr1:0:pt1:r1:1:pt1:it2::content']")
	WebElement URL;


	@FindBy(xpath = "//label[text()='Create Primary Contact']")
	WebElement selectCreatePrimaryContactCheckbox;

	@FindBy(xpath = "//*[text()='First Name']/following::input[1]")
	WebElement typePartnerFirstName;

	@FindBy(xpath = "//*[text()='Last Name']/following::input[1]")
	WebElement typePartnerLastName;

	@FindBy(xpath = "//*[contains(text(),'Email')]/following::input[1]")
	WebElement typePartnerEmailAddress;

	@FindBy(xpath = "//*[contains(text(),'E-Mail')]/following::input[1]")
	WebElement typePartnerEmailAddress2;

	@FindBy(xpath = "//*[contains(text(),'Email')]")
	WebElement emailText;



	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(partnerName) == null) {
			throw new TestErrorException("The partnerName  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Create Partner Page is Loaded");
	}


	public void typeSupplier(String suppl) {
		StepReport.info("Type Supplier");
		DriverUtil.sleep(3000L);
		supplier.sendKeys(suppl);
		DriverUtil.sleep(3000L);
		supplier.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);
		supplier.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);

	}

	public void typeSupplierSite(String supplSite) {
		StepReport.info("Type Supplier Site");
		DriverUtil.sleep(3000L);
		supplierSite.sendKeys(supplSite);
		DriverUtil.sleep(3000L);
		supplierSite.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);
		supplierSite.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);

	}


	public String typePartnerName() {
		StepReport.info("Type Partner Name");
		String pName = "ARPartName";
		int num = 100000;
		Random r = new Random();
		num = num + r.nextInt(10000);
		pName = pName+String.valueOf(num);
		DriverUtil.sleep(3000L);
		partnerName.sendKeys(pName);
		DriverUtil.sleep(3000L);
		partnerName.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);
		partnerName.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);
		return pName;
	}

	public void typePartnerAddressLine1() {
		String pAddrLine1 = "Washington";
		StepReport.info("Type Partner Address");
		DriverUtil.sleep(3000L);
		partnerAddressLine1.sendKeys(pAddrLine1);
		DriverUtil.sleep(3000L);
		partnerAddressLine1.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);
		partnerAddressLine1.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);

	}

	public void typePartnerState(String pState) {
		StepReport.info("Type Partner State");
		DriverUtil.sleep(3000L);
		partnerState.sendKeys(pState);
		DriverUtil.sleep(3000L);
		partnerState.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);
		partnerState.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);

	}

	public void typePartnerPostalCode(String pPostalCode) {
		StepReport.info("Type Partner Postal Code");
		DriverUtil.sleep(3000L);
		partnerPostalCode.sendKeys(pPostalCode);
		DriverUtil.sleep(3000L);
		partnerPostalCode.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);

	}

	public EditPartnerProfile clickSaveAndContinue() {
		StepReport.info("Click Save And Continue Button");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(saveAndContinueButton);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(saveAndContinueButton);
		DriverUtil.sleep(2000L);
		EditPartnerProfile editPartnerProfile = PageFactory.getPage(EditPartnerProfile.class);
		PossibleDuplicates possibleDuplicates = PageFactory.getPage(PossibleDuplicates.class);
		if(possibleDuplicates.pageLoaded())
		{
			possibleDuplicates.clickContinueWithCreate();
		}
		editPartnerProfile.isLoaded();
		return editPartnerProfile;

	}

	public void clickSaveAndClose() {
		StepReport.info("Click Save and Close Button");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(saveAndCloseButton);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(saveAndCloseButton);
		DriverUtil.sleep(2000L);

	}

	public void selectStatus(String statusText) {
		StepReport.info("Select Partner Status as "+statusText);
		DriverUtil.sleep(2000L);
        /*Select statusSelect = new Select(selectPartnerStatusAsRegistered);
        statusSelect.selectByVisibleText(statusText);
        DriverUtil.sleep(2000L);
        selectPartnerStatusAsRegistered.sendKeys(Keys.RETURN);*/
		String xpath = "//li[text()='Registered']";
		WebElement registered = DriverUtil.getElement(By.xpath(xpath));
		DriverUtil.clickByJS(registered);
		DriverUtil.sleep(5000L);


	}

	public  void selectCreatePrimaryContactCheckbox(){
		StepReport.info("Select Create Primary Contact Checkbox ");
		DriverUtil.sleep(2000L);
		selectCreatePrimaryContactCheckbox.click();
		DriverUtil.sleep(2000L);
		selectCreatePrimaryContactCheckbox.sendKeys(Keys.TAB);
	}

	public void typePartnerFirstName() {
		StepReport.info("Type typePartnerFirstName");
		String partnerFirstName = "FirstPartner6";
		DriverUtil.sleep(3000L);
		typePartnerFirstName.sendKeys(partnerFirstName);
		DriverUtil.sleep(3000L);
		typePartnerFirstName.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);

	}
	public void typePartnerLastName() {
		StepReport.info("Type typePartnerLastName");
		String partnerLastName = "SecondP";
		DriverUtil.sleep(3000L);
		typePartnerLastName.sendKeys(partnerLastName);
		DriverUtil.sleep(2000L);
	}
	public void typePartnerEmailAddress() {
		StepReport.info("Type Partner Email Address");
		DriverUtil.sleep(3000L);
		try{
			if(emailText.isDisplayed()){
				String partnerEmailAddress = "venkatesh.babu.vinnakota@oracle.com";
				typePartnerEmailAddress.sendKeys(partnerEmailAddress);
				DriverUtil.sleep(3000L);
				typePartnerEmailAddress.sendKeys(Keys.TAB);
				DriverUtil.sleep(3000L);
			}
		}catch(Exception e){
			StepReport.info("Email Text is displayed as E-Mail");
			String partnerEmailAddress = "venkatesh.babu.vinnakota@oracle.com";
			typePartnerEmailAddress2.sendKeys(partnerEmailAddress);
			DriverUtil.sleep(3000L);
			typePartnerEmailAddress2.sendKeys(Keys.TAB);
			DriverUtil.sleep(3000L);
		}

	}
}
