package com.oracle.fa.qa.selenium.component.fin.page;

import com.oracle.fa.qa.selenium.component.common.page.Attachment;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ManageInvoices extends BasePageObject<ManageInvoices>{
	@FindBy(xpath = "//*[text()='Actions']")
	WebElement invoiceActions;

	@FindBy(xpath = "//*[text()='Invoice Number']/following::input[1]")
	WebElement invoiceNumber;
	
	@FindBy(xpath = "//button[text()='Search']")
	WebElement search;

	@FindBy(xpath = "//h1[text()='Approval and Notification History']")
	WebElement historyTable;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(invoiceNumber) == null) {
            throw new TestErrorException("The Invoice Number is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

	public void searchInvoice(String invoiceNumber) {
		enterInvoiceNumber(invoiceNumber);
		//clickSearch(invoiceNumber);
	}

	public void enterInvoiceNumber(String invoiceNum) {
		StepReport.info("Enter Invoice Number");
		DriverUtil.sleep(2000L);
		invoiceNumber.sendKeys(invoiceNum);
		DriverUtil.sleep(2000L);
		invoiceNumber.sendKeys(Keys.RETURN);
	}
	
	public void clickSearch(String invoiceNum) {
		StepReport.info("Click Search");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(search);
		search.click();
		DriverUtil.sleep(2000L);
		String element="//*[text()='"+invoiceNum+"']";
		WebElement approvalStatus=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementVisible(approvalStatus);
        DriverUtil.sleep(2000L);

	}

	public void clickInvoice(String invoiceNumber) {
		StepReport.info("Click Invoice",invoiceNumber);
		String element="//*[contains(text(),'"+invoiceNumber+"')]";
		WebElement invoice=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementVisible(invoice);
		DriverUtil.sleep(1000L);
		invoice.click();
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(historyTable);
	}

	public void forceApproval() {
		StepReport.info("Force Approval");
		DriverUtil.sleep(2000L);
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        invoiceActions.click();
	    DriverUtil.sleep(2000L);
	    String element="//*[text()='Actions']/following::td[text()='Approval']";
		WebElement approvalElem=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementClickable(approvalElem);
		DriverUtil.sleep(2000L);
		Action mouseClick = builder.click(approvalElem).build();
        mouseClick.perform();
        DriverUtil.sleep(2000L);
        String element1="//*[text()='Force Approve']";
		WebElement forceApproveElem=DriverUtil.getElement(By.xpath(element1));
		PageLoadHelper.waitForElementClickable(forceApproveElem);
        DriverUtil.sleep(2000L);
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", forceApproveElem);
	    DriverUtil.sleep(10000L);
	    //String element2="//td/span[text()='Approval']/following::*[text()='Manually approved']";
		//WebElement approveStatusElem=DriverUtil.getElement(By.xpath(element2));
	    //PageLoadHelper.waitForElementVisible(approveStatusElem);

	}

	public boolean verifyApprovalStatus(String approvalStatus) {
		StepReport.info("Verify Approval Status");
        DriverUtil.sleep(2000L);
        try {
            String element="//*[text()='Approval Status']/following::*[text()='"+approvalStatus+"']";
            WebElement approvalStatusElem=DriverUtil.getElement(By.xpath(element));
            if(approvalStatusElem.isDisplayed()) return true;
            else return false;
        }catch(Exception e) {
            return false;
        }

	}
}
