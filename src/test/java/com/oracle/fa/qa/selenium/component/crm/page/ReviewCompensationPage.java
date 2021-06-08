package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewCompensationPage extends BasePageObject<ReviewCompensationPage> {



	@FindBy(xpath = "//h1[contains(text(),'Review Compensation')]")
	WebElement reviewCompensationPage;

	@FindBy(xpath = "//*[@accesskey='m']")
	WebElement submitButton;

	@FindBy(xpath = "//td[contains(text(),'Manage Compensation')]")
	WebElement clickManageCompensation;

	@FindBy(xpath = "//div[text()='Warning']")
	WebElement clickOKWarningMsg;

	@FindBy(xpath = "//div[text()='Warning']/following::button[@accesskey='Y']")
	WebElement clickYes;

	@FindBy(xpath = "//*[text()='The request was submitted.']")
	WebElement clickOKConfirmMsg;

	@FindBy(xpath = "//*[text()='Confirmation']/following::button[@accesskey='K']")
	WebElement clickConfirmOK;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(reviewCompensationPage) == null) {
			throw new TestErrorException("The Review Compensation Page is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Review Compensation page is Loaded");
	}

	public void clickSubmit(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click on Submit");
		submitButton.click();
		DriverUtil.sleep(2000L);
	}

	public void clickWarningOK(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click YES on Warning Page:");
		PageLoadHelper.waitForElementVisible(clickOKWarningMsg);
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(clickYes);
		clickYes.click();
	}

	public void clickConfirmOK(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click OK on Confirmation Page:");
		PageLoadHelper.waitForElementVisible(clickOKConfirmMsg);
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(clickConfirmOK);
		clickConfirmOK.click();
		DriverUtil.sleep(5000L);
	}
}
