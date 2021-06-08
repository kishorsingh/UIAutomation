package com.oracle.fa.qa.selenium.component.fin.page;

import org.openqa.selenium.By;
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

public class RequestCashAdvance extends BasePageObject<RequestCashAdvance>{

	@FindBy(xpath = "//*[contains(@id,'ReceiptAmountLabel')]//input")
	WebElement amount;

	@FindBy(css = "td[id*='contentContainer']")
	WebElement content;

	@FindBy(xpath = "//*[contains(@id,'ReceiptAmountLabel')]/following::textarea")
	WebElement purpose;
	
	@FindBy(css ="a[accesskey='m']")
	WebElement submitReport;
	
	@FindBy(xpath ="//a[@title='Manage Attachments']")
	WebElement attachmentLink;
	
	@Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(amount) == null) {
            throw new TestErrorException("The Amount field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void typeAmount(String amt) {
		StepReport.info("Type Amount");
		PageLoadHelper.waitForElementClickable(amount);
		DriverUtil.sleep(3000L);
		amount.clear();
		amount.sendKeys(amt);
	}
	
	public void typePurpose(String text) {
		StepReport.info("Type Purpose",text);
		purpose.sendKeys(text);
	}
	
	public CashAdvanceConfirmation submitCashAdvanceReport(){
		StepReport.info("Click submit report");
		PageLoadHelper.waitForElementClickable(submitReport);
		submitReport.click();

		CashAdvanceConfirmation cashAdvanceConfirmation = PageFactory.getPage(CashAdvanceConfirmation.class);
		cashAdvanceConfirmation.isLoaded();
		return cashAdvanceConfirmation;
	}

	public String getCashAdvanceReportName() {
		StepReport.info("Get Cash Advance Report Name");
		String repInfo=content.getText();
		String[] x = repInfo.split(" ");
		String repName=x[2];
		return repName;
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
