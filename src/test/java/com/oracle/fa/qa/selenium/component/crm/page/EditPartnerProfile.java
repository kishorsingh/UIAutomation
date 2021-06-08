package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPartnerProfile extends BasePageObject<EditPartnerProfile>{
	
	@FindBy(xpath = "//*[text()='Supplier']/following::input[1]")
	WebElement supplier;
	
	@FindBy(xpath = "//*[text()='Supplier Site']/following::input[1]")
	WebElement supplierSite;
	
	@FindBy(xpath = "//*[@title='Add Row']")
	WebElement addItem;
	
	@FindBy(xpath = "//*[@accesskey='m']")
	WebElement submit;
	
	@FindBy(xpath = "//*[text()='Schedules']")
	WebElement schedules;

	@FindBy(xpath = "//*[text()='Profile']")
	WebElement profile;

	@FindBy(xpath = "//*[contains(@id,'object-subtitle:ctb1')]")
	WebElement saveAndCloseButton;

	@FindBy(xpath = "//*[text()='Partner Contacts']")
	WebElement partnerContacts;

	@FindBy(xpath = "//button[text()='Create Contact']")
	WebElement createContact;


	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(profile) == null) {
            throw new TestErrorException("The Profile Tab is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
		StepReport.info("EditPartnerProfile Page is Loaded");
    }
	
	public void clickAddItem() {
		StepReport.info("Click Add Item");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(addItem);
		DriverUtil.sleep(5000L);
		
	}

	
	public void addItemLine(String item,String Qty,String amt) {
		StepReport.info("Enter item Number");
		String itemNameXpath="//*[contains(@id,'Item::content')]";
		WebElement itemNameElem=DriverUtil.getElement(By.xpath(itemNameXpath)); 
		DriverUtil.sleep(2000L);
	    itemNameElem.sendKeys(item);
        DriverUtil.sleep(3000L);
         itemNameElem.sendKeys(Keys.RETURN);
        DriverUtil.sleep(10000L);
        StepReport.info("Enter item Qty");
        String itemQtyXpath="//*[contains(@id,'Quantity::content')]";
		WebElement itemQtyElem=DriverUtil.getElement(By.xpath(itemQtyXpath)); 
	    DriverUtil.sleep(2000L);
	    
        itemQtyElem.sendKeys(Qty);
        DriverUtil.sleep(3000L);
        itemQtyElem.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
        String itemPriceXpath="//*[contains(@id,'UnitPrice::content')]";
		WebElement itemPriceElem=DriverUtil.getElement(By.xpath(itemPriceXpath)); 
        DriverUtil.sleep(2000L);
        
        itemPriceElem.sendKeys(amt);
        DriverUtil.sleep(3000L);
        itemPriceElem.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
	}
	
	public String getPONumber() {
		StepReport.info("Get PO Number");
		String panelHeaderXpath="//*[contains(text(),'Edit Document')  and contains(@class,'panelHeader')]";
		WebElement panelHeaderElem=DriverUtil.getElement(By.xpath(panelHeaderXpath)); 
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(panelHeaderElem);
        DriverUtil.sleep(2000L);
        String poNumber=panelHeaderElem.getText();
        poNumber=poNumber.replace("Edit Document (Purchase Order): ", "");
        poNumber=poNumber.trim();
        return poNumber;
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
	
	public void typeReqDate(String date) {
		StepReport.info("Type Req Date");
		DriverUtil.sleep(3000L);
		DriverUtil.clickByJS(schedules);
		DriverUtil.sleep(3000L);
		String reqDateXpath="//*[contains(@id,'NeedByDt::content')]";
		WebElement reqDateElem=DriverUtil.getElement(By.xpath(reqDateXpath));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(reqDateElem);
        DriverUtil.sleep(3000L);
        reqDateElem.sendKeys(date);
        DriverUtil.sleep(3000L);
        reqDateElem.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

	}




	public PartnersPage saveAndCloseEditPartnerProfile() {
		StepReport.info("Click on Save and Close button on Edit Partner Profile page");
		DriverUtil.sleep(3000L);
		DriverUtil.clickByJS(saveAndCloseButton);
		DriverUtil.sleep(10000L);
		PartnersPage partners = PageFactory.getPage(PartnersPage.class);
		partners.isLoaded();
		return partners;
	}

	public void clickPartnerContact(){
		StepReport.info("Click Parnter Contact");
		DriverUtil.sleep(2000L);
		partnerContacts.click();
		DriverUtil.sleep(2000L);
	}

	public CreateContactPage createContact() {
		StepReport.info("Click Create Contact Button");
		DriverUtil.sleep(2000L);
		createContact.click();
		DriverUtil.sleep(20000L);
		CreateContactPage createContactPage = PageFactory.getPage(CreateContactPage.class);
		createContactPage.isLoaded();
		return createContactPage;
	}

}
