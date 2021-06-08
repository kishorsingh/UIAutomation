package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.FindBy;

public class ContactsPage extends BasePageObject<ContactsPage> {



	@FindBy(xpath = "//h1[text()='Contacts']")
	WebElement contactsHeader;

	@FindBy(xpath = "//button[text()='Create Contact']")
	WebElement createContact;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSrHZ_FOUNDATIONPARTIES_CONTACTS_CRM_CARD:0:_FOTsr1:0:pt1:lsc1:searchf:srchit::content']")
	WebElement searchContactName;

	@FindBy(xpath = ".//*[@id='_FOpt1:_FOr1:0:_FOSrHZ_FOUNDATIONPARTIES_CONTACTS_CRM_CARD:0:_FOTsr1:0:pt1:lsc1:searchf:searchb']")
	WebElement findButton;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(contactsHeader) == null) {
			throw new TestErrorException("The Contracts page is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Contacts page is Loaded");
	}



	public CreateContactPage clickCreateContact() {
		StepReport.info("Click Create Contracts");
		DriverUtil.sleep(2000L);
		createContact.click();
		DriverUtil.sleep(2000L);
		CreateContactPage createContactPage = PageFactory.getPage(CreateContactPage.class);
		createContactPage.isLoaded();
		return createContactPage;
	}

	public EditContactPage searchName(String contactName){
		StepReport.info("Search for Contact Name:", contactName);
		DriverUtil.sleep(2000L);
		searchContactName.sendKeys(contactName);
		DriverUtil.sleep(2000L);
		String elementXpath="//span[contains(text(),'"+contactName+"')]";
		WebElement searchElement=DriverUtil.getElement(By.xpath(elementXpath));
		if (searchElement.isDisplayed()){
			StepReport.info("Search Element is Displayed:");
			//searchElement.click();
			DriverUtil.clickByAction(searchElement,true);
			DriverUtil.sleep(3000L);
		}else{
			StepReport.info("Search Element is NOT Displayed:");
		}

		EditContactPage editContactPage = PageFactory.getPage(EditContactPage.class);
		editContactPage.isLoaded();
		return editContactPage;

		}




}
