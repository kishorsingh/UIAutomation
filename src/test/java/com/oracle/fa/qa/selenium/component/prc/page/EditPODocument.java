package com.oracle.fa.qa.selenium.component.prc.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.common.page.Attachment;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class EditPODocument extends BasePageObject<EditPODocument>{
	
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
	
	@FindBy(xpath = "//*[text()='Notes and Attachments']")
	WebElement notesAttachment;
	
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(submit) == null) {
            throw new TestErrorException("The Submit Button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void clickAddItem() {
		StepReport.info("Click Add Item");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(addItem);
		DriverUtil.sleep(5000L);
		
	}
	
	public POConfirmation clickSubmit() {
		StepReport.info("Click Submit");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(submit);
		DriverUtil.sleep(2000L);
		POConfirmation poConfirmation = PageFactory.getPage(POConfirmation.class);
		poConfirmation.isLoaded();
		return poConfirmation;
		
	}
	
	public void addItemLine(String item,String Qty,String amt) {
		
		
		StepReport.info("Enter item Number");
		String itemNameXpath="//*[contains(@id,'Item::content')]";
		WebElement itemNameElem=DriverUtil.getElement(By.xpath(itemNameXpath)); 
		DriverUtil.sleep(2000L);
	    itemNameElem.sendKeys(item);
        DriverUtil.sleep(3000L);
		itemNameElem=DriverUtil.getElement(By.xpath(itemNameXpath));
        itemNameElem.sendKeys(Keys.RETURN);
        DriverUtil.sleep(10000L);
        StepReport.info("Enter item Qty");
        String itemQtyXpath="//*[contains(@id,'Quantity::content')]";
		WebElement itemQtyElem=DriverUtil.getElement(By.xpath(itemQtyXpath)); 
	    DriverUtil.sleep(2000L);
	    PageLoadHelper.waitForElementVisible(itemQtyElem);
		itemQtyElem=DriverUtil.getElement(By.xpath(itemQtyXpath));
		itemQtyElem.sendKeys(Qty);
        DriverUtil.sleep(3000L);
		itemQtyElem=DriverUtil.getElement(By.xpath(itemQtyXpath));
		itemQtyElem.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
        String itemPriceXpath="//*[contains(@id,'UnitPrice::content')]";
		WebElement itemPriceElem=DriverUtil.getElement(By.xpath(itemPriceXpath)); 
        DriverUtil.sleep(2000L);
        
        itemPriceElem.sendKeys(amt);
        DriverUtil.sleep(3000L);
		itemPriceElem=DriverUtil.getElement(By.xpath(itemPriceXpath));
        itemPriceElem.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
	}
	
	public String getPONumber() {
		StepReport.info("Get PO Number");
		String panelHeaderXpath="//h1[contains(text(),'Edit Document')]";
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
	
	public void clickNotesAttachment() {
		StepReport.info("Click Notes and Attachment");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(notesAttachment);
		DriverUtil.sleep(5000L);
		
	}

	public Attachment clickAttachment() {
		StepReport.info("Click Attachment");
		String attachmentXpath="//*[@title='Manage Attachments']";
		WebElement attachmentElem=DriverUtil.getElement(By.xpath(attachmentXpath)); 
     	PageLoadHelper.waitForElementVisible(attachmentElem);
     	attachmentElem.click();
		DriverUtil.sleep(2000L);
		Attachment attachment = PageFactory.getPage(Attachment.class);
		attachment.isLoaded();
        return attachment;
	}
	public void waitForAttachmentToDisplay(String fileName) {
		String xpath="//*[text()='"+fileName+"']";
		WebElement attachedFile=DriverUtil.getElement(By.xpath(xpath)); 
		PageLoadHelper.waitForElementVisible(attachedFile);
	}

}
