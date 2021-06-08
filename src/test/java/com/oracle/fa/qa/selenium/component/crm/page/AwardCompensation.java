package com.oracle.fa.qa.selenium.component.crm.page;


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

public class AwardCompensation extends BasePageObject<AwardCompensation> {



	@FindBy(xpath = "//div[text()='Award Compensation']")
	WebElement awardCompensation;

	@FindBy(xpath = "(//*[text()='Plan']/following::select)[1]")
	WebElement selectPlan;

	@FindBy(xpath = "(//*[text()='Option']/following::select)[2]")
	WebElement selectOption;

	@FindBy(xpath = "(//*[text()='Periodicity']/following::select)[1]")
	WebElement selectPeriodicity;

	@FindBy(xpath = "(//*[text()='Pay Value']/following::input)[1]")
	WebElement typePayValue;

	@FindBy(xpath = "(//*[text()='Amount']/following::input)[1]")
	WebElement typeAmount;

	@FindBy(xpath = "//*[@accesskey='K']")
	WebElement clickOK;


	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(awardCompensation) == null) {
			throw new TestErrorException("The AwardCompensation Page  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("AwardCompensation page is Loaded");
	}

	public void selectPlan(){
		DriverUtil.sleep(3000L);
		StepReport.info("Select Plan","ZCMP Spot Bonus Plan");
		String xpath = "//li[text()='ZCMP Spot Bonus Plan']";
		WebElement plan = DriverUtil.getElement(By.xpath(xpath));
		DriverUtil.clickByJS(plan);
		DriverUtil.sleep(5000L);
	}

	public void selectOption(){
		DriverUtil.sleep(3000L);
		StepReport.info("Select Option","Spot Bonus");
		String xpath = "//li[text()='Spot Bonus']";
		WebElement option = DriverUtil.getElement(By.xpath(xpath));
		DriverUtil.clickByJS(option);
		DriverUtil.sleep(5000L);
	}

	public void selectPeriodicity(){
		DriverUtil.sleep(2000L);
		StepReport.info("Select Periodicity","Annually");
		String xpath = "//li[text()='Annually']";
		WebElement periodicity = DriverUtil.getElement(By.xpath(xpath));
		DriverUtil.clickByJS(periodicity);
		DriverUtil.sleep(5000L);
	}

	public void typeAmount(){
		DriverUtil.sleep(2000L);
		StepReport.info("Type Pay Value","100");
		typePayValue.sendKeys("100");
		DriverUtil.sleep(2000L);
		StepReport.info("Type Amount","100");
		typeAmount.sendKeys("100");
		DriverUtil.sleep(2000L);
	}
	public void clickOK(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click OK");
		clickOK.click();
		DriverUtil.sleep(5000L);

	}


}
