package com.oracle.fa.qa.selenium.component.fin.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class CreateExpenseItem extends BasePageObject<CreateExpenseItem>{
	@FindBy(css ="select[id*='ExpenseTemplate']")
	WebElement expenseTemplate;
	
	@FindBy(css ="select[id*='ExpenseType']")
	WebElement expenseType;
	
	@FindBy(css ="input[id*='ReceiptAmount']")
	WebElement receiptAmount;
	
	@FindBy(css ="div[title='Save and Close']")
	WebElement saveAndCloseDropDown;
	
	@FindBy(css ="select[id*='ReceiptCurrencyCode']")
	WebElement receiptCurrency;
	
	@FindBy(xpath ="//span[contains(text(),'Close')]")
	WebElement close;

	@Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(expenseTemplate) == null) {
            throw new TestErrorException("The Expense Template field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void selectTemplate(String templateName) {
		StepReport.info("Select Template");
		Select template = new Select(expenseTemplate);
		template.selectByVisibleText(templateName);
		//Page Load template values. Wait for this.
		DriverUtil.sleep(7000L);
	}
	
	public void selectReceiptCurrency(String currencyCode) {
		StepReport.info("Select Recepit Currency");
		DriverUtil.sleep(2000L);
		Select currencyElement = new Select(receiptCurrency);
		currencyElement.selectByVisibleText(currencyCode);
	}
	
	public CreateItemDetails selectType(String typeName) {
		StepReport.info("Select Type");
		DriverUtil.sleep(2000L);
		Select type = new Select(expenseType);
		type.selectByVisibleText(typeName);
		DriverUtil.sleep(5000L);
		CreateItemDetails createItemDetails=PageFactory.getPage(CreateItemDetails.class);
		createItemDetails.isLoaded();
        return createItemDetails;
	}
	
	public void enterReceiptAmount(String amount) {
		StepReport.info("Enter Receipt Amount");
		DriverUtil.sleep(2000L);
		receiptAmount.sendKeys(amount);
		DriverUtil.sleep(2000L);
		receiptAmount.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}
	
	
	
	public CreateExpenseReport clickSaveAndClose() {
		StepReport.info("Click Close");
		//PageLoadHelper.waitForElementClickable(saveAndClose);
		//DriverUtil.sleep(1000L);
		//saveAndCloseDropDown.click();
		DriverUtil.sleep(3000L);
		close.click();
		CreateExpenseReport createExpenseReport = PageFactory.getPage(CreateExpenseReport.class);
		createExpenseReport.isLoaded();
        return createExpenseReport;
	}
}
