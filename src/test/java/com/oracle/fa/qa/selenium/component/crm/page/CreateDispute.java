package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
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
import org.openqa.selenium.support.ui.Select;

import javax.sound.sampled.Line;

public class CreateDispute extends BasePageObject<CreateDispute> {




	@FindBy(xpath = "//h1[contains(text(),'Create Dispute')]")
	WebElement createDisputePage;

	@FindBy(xpath = "//*[contains(@id,'pt1:it1::content')]")
	WebElement typeSummary;

	@FindBy(xpath = "//*[contains(@id,'it24::content')]")
	WebElement typeJustification;

	@FindBy(xpath = "//*[contains(@id,'it2::content')]")
	WebElement typeDescription;

	@FindBy(xpath = "//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTr2:2:pt1:soc3::content']/option[9]")
	WebElement selectTxtType;

	@FindBy(xpath = "//*[contains(@id,'pt1:it15::content')]")
	WebElement typeActualCreditAmount;

	@FindBy(xpath = "//*[contains(@id,'it14::content')]")
	WebElement typeExpectedCreditAmount;

	@FindBy(xpath = "//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTr2:2:pt1:it5::content']")
	WebElement typeTxnNumber;

	@FindBy(xpath = "//select[@id='pt1:_FOr1:1:_FONSr2:0:_FOTr2:2:pt1:soc2::content']/option[220]")
	WebElement selectCurrency;

	@FindBy(xpath = "//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTr2:2:pt1:cb2']")
	WebElement clickSaveAndClose;


	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(createDisputePage) == null) {
			throw new TestErrorException("The partnerName  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Create Dispute Page is Loaded");
	}



	public String typeSummary() {
		StepReport.info("Type Summary");
		DriverUtil.sleep(2000L);
		String DisputeSummary="CN Dispute ";
		DisputeSummary= DisputeSummary + SelUtil.getCurrentLocalDateTimeStamp();
		typeSummary.sendKeys(DisputeSummary);
		DriverUtil.sleep(2000L);
		typeSummary.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		return DisputeSummary;
	}
	public void typeJustification() {
		StepReport.info("Type Justification");
		DriverUtil.sleep(2000L);
		typeJustification.sendKeys("CreateDisputeTestJustification");
		DriverUtil.sleep(2000L);
		typeJustification.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}

	public void typeDescription() {
		StepReport.info("Type Description");
		DriverUtil.sleep(2000L);
		typeDescription.sendKeys("CreateDisputeTestDescription");
		DriverUtil.sleep(2000L);
		typeDescription.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}

	public void selectTxnType() {

		StepReport.info("Select Transaction Type as Invoice");
		WebElement wele = DriverUtil.getElement(By.xpath("//*[text()='Transaction Type']/following::select[1]"));
		Select select = new Select(wele);
		select.selectByVisibleText("Invoice");
		DriverUtil.sleep(2000L);
		selectTxtType.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);

	}


	public void typeActualCreditAmount() {
		StepReport.info("Type ActualCreditAmount");
		DriverUtil.sleep(2000L);
		typeActualCreditAmount.sendKeys("100");
		DriverUtil.sleep(2000L);
		typeActualCreditAmount.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);

	}

	public void typeExpectedCreditAmount() {
		StepReport.info("Type ExpectedCreditAmount");
		DriverUtil.sleep(2000L);
		typeExpectedCreditAmount.sendKeys("150");
		DriverUtil.sleep(2000L);
		typeExpectedCreditAmount.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);

	}
	public void typeTxnNumber() {
		StepReport.info("Type Txn Number");
		DriverUtil.sleep(2000L);
		typeTxnNumber.sendKeys("123456");
		DriverUtil.sleep(2000L);
		typeTxnNumber.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}

	public void selectCurrency() {
		StepReport.info("Select Currency Type as USD");
		WebElement webElement = DriverUtil.getElement(By.id("pt1:_FOr1:1:_FONSr2:0:_FOTr2:2:pt1:soc2::content"));
		Select select = new Select(webElement);
		select.selectByVisibleText("USD");
		DriverUtil.sleep(2000L);
		selectCurrency.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}

	public Information clickSaveAndClose() {
		StepReport.info("Click Save and Close");
		DriverUtil.sleep(3000L);
		DriverUtil.clickByJS(clickSaveAndClose);
		DriverUtil.sleep(3000L);
		Information information = PageFactory.getPage(Information.class);
		information.isLoaded();
		return information;
	}

}
