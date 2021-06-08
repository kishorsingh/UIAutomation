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

public class CashBalances extends BasePageObject<CashBalances>{
	
	@FindBy(xpath = "//*[text()='Create Ad Hoc Payment']")
	WebElement createAdhocPayment;

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
	
	public CreateAdhocPayment clickCreateAdHocPayment() {
		StepReport.info("Click Create Adhoc Payment");
		DriverUtil.sleep(2000L);
		tasks.click();
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(createAdhocPayment);
		DriverUtil.sleep(2000L);
	    JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", createAdhocPayment);
	    DriverUtil.sleep(3000L);
	    CreateAdhocPayment createAdhocPayment = PageFactory.getPage(CreateAdhocPayment.class);
		createAdhocPayment.isLoaded();
	    return createAdhocPayment;
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
