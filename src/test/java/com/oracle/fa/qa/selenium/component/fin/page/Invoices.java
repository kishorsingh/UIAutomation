package com.oracle.fa.qa.selenium.component.fin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.support.ui.Select;

public class Invoices extends BasePageObject<Invoices>{
	
	@FindBy(xpath = "//*[text()='Create Invoice']")
	WebElement createInvoice;

	@FindBy(xpath = "//*[text()='Manage Invoices']")
	WebElement manageInvoice;
	
	@FindBy(xpath = "//img[@title='Tasks']")
	WebElement tasks;

	@FindBy(xpath = "//a[text()='Pending']")
	WebElement invoicePageLoaded;


	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(invoicePageLoaded) == null) {
            throw new TestErrorException("The Create invoice is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("invoices page is loaded");
    }
	
	public CreateInvoice createInvoice() {
		//StepReport.info("Click Tasks");
		//PageLoadHelper.waitForElementClickable(tasks);
		//DriverUtil.sleep(2000L);
		//DriverUtil.sleep(2000L);
		//DriverUtil.doubleClick(tasks);
		DriverUtil.sleep(5000L);
		PageLoadHelper.waitForElementClickable(createInvoice,20);
		DriverUtil.sleep(2000L);
		StepReport.info("Click Create Invoice");
	    /*JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", createInvoice);*/
	    DriverUtil.clickByAction(createInvoice,true);
	    DriverUtil.sleep(3000L);
	    CreateInvoice createInvoice = PageFactory.getPage(CreateInvoice.class);
	    createInvoice.isLoaded();
	    return createInvoice;
	}

	public ManageInvoices manageInvoices() {
		StepReport.info("Click Manage Invoice");
		DriverUtil.sleep(2000L);
		tasks.click();
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(manageInvoice);
		DriverUtil.sleep(2000L);
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", manageInvoice);
		DriverUtil.sleep(3000L);
		ManageInvoices manageInvoices = PageFactory.getPage(ManageInvoices.class);
		manageInvoices.isLoaded();
		return manageInvoices;
	}

	public void clickTasks() {
		StepReport.info("Click Tasks");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(tasks);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByAction(tasks,true);
		DriverUtil.sleep(2000L);
		StepReport.info("Click Tasks Completed");

	}




}
