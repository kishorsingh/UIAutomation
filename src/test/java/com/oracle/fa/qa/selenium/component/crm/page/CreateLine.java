package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class CreateLine extends BasePageObject<CreateLine> {



	@FindBy(xpath = "//*[contains(@id,'inputText1::content')]")
	WebElement typeLineNumber;

	@FindBy(xpath = "//*[contains(@id,'LineTypeId::content')]")
	WebElement selectLineType;

	@FindBy(xpath = "//*[@accesskey='K']")
	WebElement clickOK;

	@FindBy(xpath = "(//*[text()='Create Line']/following::*[text()='Name']/following::input)[1]")
	WebElement typeLineName;

	@FindBy(xpath = "//div[text()='Create Line']")
	WebElement createLine;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(createLine) == null) {
			throw new TestErrorException("The partnerName  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Create Cline Sub Page is Loaded");
	}


	public void typeLineNumber() {
		DriverUtil.sleep(2000L);
		int num = 10000;
		Random r = new Random();
		num = num + r.nextInt(10000);
		StepReport.info("Type Line Number : ",String.valueOf(num) );
		typeLineNumber.sendKeys(String.valueOf(num));
		DriverUtil.sleep(2000L);
		typeLineNumber.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}
	public void selectLineType() {
		StepReport.info("Select Line Type : ","Item" );
		DriverUtil.sleep(2000L);
		Select statusSelect = new Select(selectLineType);
		statusSelect.selectByVisibleText("Item");
		DriverUtil.sleep(5000L);
	}
	public void typeLineName() {
		StepReport.info("Type Line Name : ","AS55888" );
		PageLoadHelper.waitForElementVisible(typeLineName);
		DriverUtil.sleep(2000L);
		typeLineName.sendKeys("AS55888");
		DriverUtil.sleep(2000L);
		typeLineName.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}

	public EditContract clickOK() {
		StepReport.info("Click OK : " );
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickOK);
		DriverUtil.sleep(5000L);
		EditContract editContract = PageFactory.getPage(EditContract.class);
		editContract.isLoaded();
		return editContract;
	}


}
