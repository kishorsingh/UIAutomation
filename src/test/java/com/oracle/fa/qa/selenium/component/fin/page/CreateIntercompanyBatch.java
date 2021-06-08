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

public class CreateIntercompanyBatch extends BasePageObject<CreateIntercompanyBatch>{
	
	@FindBy(xpath = "//*[text()='Provider']/following::input[1]")
	WebElement provider;
	
	@FindBy(xpath = "//*[text()='Legal Entity']/following::span[1]")
	WebElement legalEntity;

    @FindBy(xpath = "//*[text()='Transaction Type Name']/following::input[1]")
    WebElement transactionTypeName;
	
	@FindBy(xpath = "//h1[text()='Transactions']/following::label[text()='Receiver']/preceding::input[1]")
	WebElement receiver;

    @FindBy(xpath = "//h1[text()='Transactions']/following::label[text()='Debit'][1]/preceding::input[1]")
    WebElement debitTransactions;

	@FindBy(xpath = "//a[@accesskey='m']")
	WebElement submit;

    @FindBy(xpath ="//h1[text()='Transactions']/following::img[@title='Add Row'][1]")
    WebElement addTransactions;

    @FindBy(xpath ="//a[text()='Provider']/following::img[@title='Add Row'][1]")
    WebElement addRow;

    @FindBy(xpath ="//a[text()='Provider']/following::label[text()='Account']/preceding::input[1]")
    WebElement account;

    @FindBy(xpath ="//a[text()='Provider']/following::label[text()='Debit']/preceding::input[1]")
    WebElement debit;

    @FindBy(xpath ="//a[text()='Provider']/following::label[text()='Credit']/preceding::input[1]")
    WebElement credit;

    @FindBy(xpath ="//h1[text()='Transaction : Distributions']/following::a[text()='Receiver'][1]")
    WebElement receiverLink;

    @FindBy(xpath ="//*[text()='Information']/following::tbody[2]/tr/td/div")
    WebElement infoMsg;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(submit) == null) {
            throw new TestErrorException("The Submit button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void fillProvider(String providerName) {
		StepReport.info("Fill Up Provider");
		PageLoadHelper.waitForElementVisible(provider);
		DriverUtil.sleep(2000L);
        provider.sendKeys(providerName);
	    DriverUtil.sleep(2000L);
        provider.sendKeys(Keys.RETURN);
	    DriverUtil.sleep(2000L);
        provider.sendKeys(Keys.TAB);
        waitUntilLegalEntityPopulated();
	    DriverUtil.sleep(2000L);
	}
	
	private void waitUntilLegalEntityPopulated() {
		boolean isValueNotPopulated=true;
		int counter=1;
		while((isValueNotPopulated) && (counter<4)) {
		try {
			String val=legalEntity.getAttribute("value");
			System.out.println("Legal Entity value="+val);
			if(val.equals("Vision Operations")) {
				isValueNotPopulated=false;
				System.out.println("Legal Entity Populated");
			}else {
				System.out.println("Legal Entity Not Populated");
			}
			}catch(Exception e) {
				System.out.println("Legal Entity Not Populated");
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
	   }

    public void enterTransactionTypeName(String name) {
        StepReport.info("Enter Transaction Type Name");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(transactionTypeName);
        DriverUtil.sleep(2000L);
        transactionTypeName.sendKeys(name);
        DriverUtil.sleep(2000L);
        transactionTypeName.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

	public void enterReceiverName(String text) {
		StepReport.info("Enter Receiver");
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(receiver);
        DriverUtil.sleep(2000L);
        receiver.sendKeys(text);
	    DriverUtil.sleep(2000L);
        receiver.sendKeys(Keys.TAB);
	    DriverUtil.sleep(2000L);
	}

    public void enterDebitTransactionsValue(String text) {
        StepReport.info("Enter Debit Transactions Value");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(debitTransactions);
        DriverUtil.sleep(2000L);
        debitTransactions.sendKeys(text);
        DriverUtil.sleep(2000L);
        debitTransactions.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void clickAddRow() {
        StepReport.info("Click Add Row");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(addRow);
        addRow.click();
        DriverUtil.sleep(2000L);
    }

    public void clickReceiver() {
        StepReport.info("Click Receiver");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(receiverLink);
        receiverLink.click();
        DriverUtil.sleep(5000L);
    }

    public void enterAccountNumber(String number) {
        StepReport.info("Enter Account Name");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(account);
        DriverUtil.sleep(2000L);
        account.sendKeys(number);
        DriverUtil.sleep(2000L);
        account.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterDebitValue(String value) {
        StepReport.info("Enter Debit Value");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(debit);
        DriverUtil.sleep(2000L);
        debit.sendKeys(value);
        DriverUtil.sleep(2000L);
        debit.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterCreditValue(String value) {
        StepReport.info("Enter Credit Value");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(credit);
        DriverUtil.sleep(2000L);
        credit.sendKeys(value);
        DriverUtil.sleep(2000L);
        credit.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }
	
	public void clickSubmit() {
		StepReport.info("Click Submit");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(submit);
		submit.click();
		DriverUtil.sleep(2000L);
		String element="//*[text()='Information']";
		WebElement submitStatus=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementVisible(submitStatus);
        DriverUtil.sleep(2000L);
	}

    public String getBatchNumber() {
        StepReport.info("Get Batch Number");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(infoMsg);
        String information = infoMsg.getText();
        String[] x = information.split(" ");
        String txnNum=x[1];
        return txnNum;
    }
	
	public void clickAddTransactions() {
		StepReport.info("Click Add Transactions");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(addTransactions);
        addTransactions.click();
		DriverUtil.sleep(2000L);
	}

}
