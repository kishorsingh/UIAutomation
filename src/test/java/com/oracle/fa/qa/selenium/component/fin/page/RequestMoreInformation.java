package com.oracle.fa.qa.selenium.component.fin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class RequestMoreInformation extends BasePageObject<RequestMoreInformation>{
	@FindBy(css = "textarea[id*='commentsForRequestInfo::content']")
	WebElement comment;
	
	@FindBy(css = "button[id='reqIfD::ok']")
	WebElement okButton;

	@FindBy(xpath = "//*[@id='r1:0:bip_up:UPsp1:bip_rpp:0:soc_reqinfo::content']/fieldset/div[2]/span/label")
	WebElement chooseApprovalFlow;

	@FindBy(xpath ="//*[contains(text(),'Comment')]/following::textarea[1]")
	WebElement addcomments;

	@FindBy(xpath ="//*[contains(@id,'submit')]")
	WebElement clickSubmit;
	
	@Override
    public void isLoaded() {

		if (PageLoadHelper.waitForElementClickable(addcomments) == null ) {
			throw new TestErrorException("The Req more info page is not available " +
						Timeout.pageLoadValue().seconds() + " seconds.");
		}

    }
	
	public void typeComments(String text) {
		StepReport.info("Type Comments");
		PageLoadHelper.waitForElementVisible(addcomments);
		DriverUtil.sleep(1000L);
		addcomments.sendKeys(text);
		DriverUtil.sleep(2000L);
	}


	public void typeComment(String text) {
		StepReport.info("Type Comments");
		PageLoadHelper.waitForElementVisible(addcomments);
		DriverUtil.sleep(1000L);
		addcomments.sendKeys(text);
		DriverUtil.sleep(2000L);
	}
	
	public void clickOK() {
		StepReport.info("Click OK");
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        Action mouseClick = builder.click(okButton).build();
        mouseClick.perform();
		DriverUtil.sleep(7000L);
		SelUtil.waitUntilPageClosed();
	}
	public void clickSubmit() {
		StepReport.info("Click Submit");
		DriverUtil.sleep(1000L);
		chooseApprovalFlow.click();
		DriverUtil.sleep(2000L);
		clickSubmit.click();
		DriverUtil.sleep(2000L);
		SelUtil.waitUntilPageClosed();
	}
}
