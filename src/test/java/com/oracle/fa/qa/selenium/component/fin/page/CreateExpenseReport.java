package com.oracle.fa.qa.selenium.component.fin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.oracle.fa.qa.selenium.component.common.page.Attachment;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class CreateExpenseReport extends BasePageObject<CreateExpenseReport>{
	@FindBy(xpath = "//input[@type='text'][1]")
	WebElement purpose;
	
	@FindBy(xpath = "//*[text()='Payment Method']/following::select[1]")
	WebElement paymentMethodElement;
	
	@FindBy(xpath = "//*[text()='Payment Method']/following::select[2]")
	WebElement paymentCurrency;
	
	@FindBy(css ="input[type='checkbox']")
	WebElement readAcceptCheckBox;
	
	@FindBy(css ="a[title='Create']")
	WebElement addExpenseItem;
	
	@FindBy(css ="a[accesskey='m']")
	WebElement submitReport;
	
	@FindBy(css ="div[title*='Expense Report']")
	WebElement expReportInfo;
	
	@FindBy(xpath ="//a[@title='Manage Attachments']")
	WebElement attachmentLink;
	
	 @Override
	    protected void isLoaded() {
	        if (PageLoadHelper.waitForElementVisible(paymentMethodElement) == null) {
	            throw new TestErrorException("The Payment Method field is not visible after " +
	                    Timeout.pageLoadValue().seconds() + " seconds.");
	        }
	    }
	 
	 public void typePurpose(String purposeText) {
			StepReport.info("Type Purpose");
			purpose.sendKeys(purposeText);
		}
	
	public void selectPaymentMethod(String method) {
		StepReport.info("Select Payment Method");
		Select paymentMethod = new Select(paymentMethodElement);
		paymentMethod.selectByVisibleText(method);
		
	}
	
	public void selectPaymentCurrency(String currency) {
		StepReport.info("Select Payment Currency");
		Select paymentCurrencyElement = new Select(paymentCurrency);
		paymentCurrencyElement.selectByVisibleText(currency);
		DriverUtil.sleep(1000L);
	}
	
	public void clickReadAcceptCheckBox(){
		StepReport.info("Click read and accept check box");
		readAcceptCheckBox.click();
	}
	
	public Confirmation submitExpenseReport(){
		StepReport.info("Click submit button");
		PageLoadHelper.waitForElementClickable(submitReport);
		submitReport.click();
		Confirmation confirmation = null;
		try {
			confirmation = PageFactory.getPage(Confirmation.class);
			confirmation.isLoaded();
		} catch (Exception e) {
			confirmation = null;
		}
		return confirmation;
	}
	
	public CreateExpenseItem addExpenseItem() {
		StepReport.info("Click Create Expense Report Item");
		DriverUtil.sleep(3000L);
		addExpenseItem.click();
		DriverUtil.sleep(3000L);
		CreateExpenseItem createExpenseItem = PageFactory.getPage(CreateExpenseItem.class);
		createExpenseItem.isLoaded();
        return createExpenseItem;
	}
	
	public String getExpenseReportName() {
		StepReport.info("Get Expense Report Name");
		String expRepInfo=expReportInfo.getAttribute("title");
		expRepInfo=expRepInfo.replace("Expense Report: ", "");
		return expRepInfo;
	}
	
	public Attachment clickAttachment() {
		StepReport.info("Click Attachment");
		attachmentLink.click();
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
