package com.oracle.fa.qa.selenium.component.fin.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Transactions extends BasePageObject<Transactions>{
	
	@FindBy(xpath = "//*[text()='Create Transaction']")
	WebElement createTransaction;

	@FindBy(xpath = "//*[text()='Manage Invoices']")
	WebElement manageInvoice;
	
	@FindBy(xpath = "//img[@title='Tasks']")
	WebElement tasks;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(tasks) == null) {
            throw new TestErrorException("The Create Transactions is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public CreateIntercompanyBatch createTransaction() {
		StepReport.info("Click Create Transaction");
		DriverUtil.sleep(2000L);
		tasks.click();
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(createTransaction);
		DriverUtil.sleep(2000L);
	    JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", createTransaction);
	    DriverUtil.sleep(3000L);
	    CreateIntercompanyBatch createIntercompanyBatch = PageFactory.getPage(CreateIntercompanyBatch.class);
		createIntercompanyBatch.isLoaded();
	    return createIntercompanyBatch;
	}

	public ManageInvoices manageInvoices() {
		StepReport.info("Click Manage Create Invoice");
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
}
