package com.oracle.fa.qa.selenium.component.fin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.common.page.HomePage;
import com.oracle.fa.qa.selenium.component.common.page.Navigator;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class TravelandExpenses extends BasePageObject<TravelandExpenses>{
	@FindBy(xpath = "//*[text()='Actions'][1]")
	WebElement actions;
	
	@FindBy(xpath = "//*[text()='Create Expense Report']")
	WebElement createExpenseReport;
	
	@FindBy(xpath = "//*[text()='Request Cash Advance']")
	WebElement requestCashAdvance;

	@FindBy(xpath = "//a[contains(@title,'Cash Advances')]")
	WebElement selectCashAdvances;
	
	 @Override
	    public void isLoaded() {
	        if (PageLoadHelper.waitForElementVisible(actions) == null) {
	            throw new TestErrorException("The createExpenseReport field is not visible after " +
	                    Timeout.pageLoadValue().seconds() + " seconds.");
	        }

	        StepReport.info("Travel and Expenses Page is loaded ");
	    }
	public CreateExpenseReport clickCreateExpenseReport() {
		StepReport.info("Click Create Expense Report");
		actions.click();
		PageLoadHelper.waitForElementVisible(createExpenseReport);
		createExpenseReport.click();
		CreateExpenseReport createExpenseReport=PageFactory.getPage(CreateExpenseReport.class);
		createExpenseReport.isLoaded();
		return createExpenseReport;
	}
	
	public RequestCashAdvance clickRequestCashAdvance() {
		StepReport.info("Click Request Cash Advance");
		actions.click();
		PageLoadHelper.waitForElementVisible(requestCashAdvance);
		requestCashAdvance.click();
		DriverUtil.sleep(2000L);
		RequestCashAdvance requestCashAdvance=PageFactory.getPage(RequestCashAdvance.class);
		requestCashAdvance.isLoaded();
		return requestCashAdvance;
	}

	public void clickSelectCashAdvances() {
		StepReport.info("Click Select Cash Advances");
		PageLoadHelper.waitForElementVisible(selectCashAdvances);
		selectCashAdvances.click();
		DriverUtil.sleep(2000L);
	}

    public RequestCashAdvance clickcashAdvReport(String cashAdvReportName) {
        StepReport.info("Click Cash Advance Report");
        String xPath="//*[text()='"+cashAdvReportName+"']";
        WebElement cashAdvReport=DriverUtil.getElement(By.xpath(xPath));
        PageLoadHelper.waitForElementVisible(cashAdvReport);
        cashAdvReport.click();
        DriverUtil.sleep(2000L);
        RequestCashAdvance requestCashAdvance=PageFactory.getPage(RequestCashAdvance.class);
        requestCashAdvance.isLoaded();
        return requestCashAdvance;
    }

}
