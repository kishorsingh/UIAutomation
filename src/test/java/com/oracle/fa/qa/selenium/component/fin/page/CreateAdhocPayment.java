package com.oracle.fa.qa.selenium.component.fin.page;

import com.oracle.fa.qa.selenium.component.common.page.AdhocRoute;
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

public class CreateAdhocPayment extends BasePageObject<CreateAdhocPayment>{

	@FindBy(xpath = "//*[text()='From Account']/following::input[1]")
	WebElement fromAccount;

	@FindBy(xpath = "//*[text()='Payee']/following::input[1]")
	WebElement payee;

    @FindBy(xpath = "//*[text()='Payee Account']/following::input[1]")
    WebElement payeeAccount;

    @FindBy(xpath = "//*[text()='Payment Amount']/following::input[1]")
    WebElement paymentAmount;

    @FindBy(xpath = "//*[text()='Business Unit']/following::input[1]")
    WebElement businessUnit;

    @FindBy(xpath = "//*[text()='Payment Method']/following::input[1]")
    WebElement paymentMethod;

    @FindBy(xpath = "//*[text()='Payment Profile']/following::input[1]")
    WebElement paymentProfile;

	@FindBy(xpath = "//button[@accesskey='S'] | //button[text()='Submit']")
	WebElement submit;

    @FindBy(xpath = "//button[text()='Yes']")
    WebElement yesButton;

    @FindBy(xpath ="//*[text()='Ad Hoc Payment Number']/following::td[1]")
    WebElement paymentNumber;

	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(submit) == null) {
            throw new TestErrorException("The Submit button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void enterFromAccountName(String name) {
        StepReport.info("Enter From Account",name);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(fromAccount);
        DriverUtil.sleep(2000L);
        fromAccount.sendKeys(name);
        DriverUtil.sleep(2000L);
        fromAccount.sendKeys(Keys.TAB);
        DriverUtil.sleep(5000L);
    }

	public void enterPayeeName(String text) {
		StepReport.info("Enter Payee",text);
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(payee);
        DriverUtil.sleep(2000L);
        payee.sendKeys(text);
	    DriverUtil.sleep(2000L);
        payee.sendKeys(Keys.TAB);
	    DriverUtil.sleep(2000L);
	}

    public void enterPayeeAccount(String text) {
        StepReport.info("Enter Payee Account",text);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(payeeAccount);
        DriverUtil.sleep(2000L);
        payeeAccount.sendKeys(text);
        DriverUtil.sleep(2000L);
        payeeAccount.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterPaymentAmount(String amount) {
        StepReport.info("Enter Payment Amount",amount);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(paymentAmount);
        DriverUtil.sleep(2000L);
        paymentAmount.sendKeys(amount);
        DriverUtil.sleep(2000L);
        paymentAmount.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterBusinessUnit(String bu) {
        StepReport.info("Enter Business Unit",bu);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(businessUnit);
        DriverUtil.sleep(2000L);
        businessUnit.sendKeys(bu);
        DriverUtil.sleep(2000L);
        businessUnit.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterPaymentMethod(String name) {
        StepReport.info("Enter Payment Method",name);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(paymentMethod);
        DriverUtil.sleep(2000L);
        paymentMethod.sendKeys(name);
        DriverUtil.sleep(2000L);
        paymentMethod.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterPaymentProfile(String name) {
        StepReport.info("Enter Payment Profile",name);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(paymentProfile);
        DriverUtil.sleep(2000L);
        paymentProfile.sendKeys(name);
        DriverUtil.sleep(2000L);
        paymentProfile.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

	public void clickSubmit() {
		StepReport.info("Click Submit");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(submit);
		submit.click();
		DriverUtil.sleep(2000L);
		String element="//*[text()='Confirmation']";
		WebElement submitStatus=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementVisible(submitStatus);
        DriverUtil.sleep(2000L);
	}

    public CashBalances clickYes() {
        StepReport.info("Click Yes");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(yesButton);
        yesButton.click();
        DriverUtil.sleep(2000L);
        DriverUtil.sleep(3000L);
        CashBalances cashBalances = PageFactory.getPage(CashBalances.class);
        cashBalances.isLoaded();
        return cashBalances;
    }

    public String getPaymentNumber() {
        StepReport.info("Get Payment Number");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(paymentNumber);
        return paymentNumber.getText();
    }

}
