package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.common.page.AddComment;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;


public class DisputesNotificationPage extends BasePageObject<DisputesNotificationPage> {



	@FindBy(xpath = "//*[@id='ctb2']/a/span")
	WebElement clickApprove;

	@FindBy(xpath = "//span[text()='Reject']")
	WebElement clickReject;

	@FindBy(xpath = ".//img[@title='Create']")
	WebElement addComment;

	@FindBy(xpath = "//*[contains(text(),'Dispute Notification')]")
	WebElement disputeNotification;

	@FindBy(xpath = "//*[@id='soc1::content']")
	WebElement selecResolution;

	@FindBy(xpath = "//*[contains(text(),'Request Information')]")
	WebElement reqMoreInfo;

	@FindBy(xpath = "//*[contains(text(),'Submit Information')]")
	WebElement clickSubmitInfo;

	@FindBy(xpath = "//*[contains(text(),'Suspend')]")
	WebElement clickSuspend;

	@FindBy(xpath = "//*[contains(text(),'Save')]")
	WebElement clickSave;

	@FindBy(xpath = "//*[contains(text(),'Resume')]")
	WebElement clickResume;

	@FindBy(xpath = "//*[@id='acknowledge']/a/span")
	WebElement clickDismiss;



	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(disputeNotification) == null) {
			throw new TestErrorException("The partnerName  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Create Dispute Sub Page is Loaded");
	}


	public AddComment addComment() {
		StepReport.info("Add Comments ");
		DriverUtil.sleep(2000L);
		addComment.click();
		DriverUtil.sleep(2000L);
		AddComment addComment = PageFactory.getPage(AddComment.class);
		addComment.isLoaded();
		return addComment;
	}

	public void clickApprove() {
		StepReport.info("Click on Approve Button  ");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickApprove);
		DriverUtil.sleep(2000L);
	}

	public void clickReject() {
		StepReport.info("Click on Reject Button  ");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickReject);
		DriverUtil.sleep(2000L);
	}

	public void clickDismiss() {
		StepReport.info("Click on Approve Button  ");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickDismiss);
		DriverUtil.sleep(2000L);
	}

	public void selectResolution() {
		StepReport.info("Select Line Type : " );
		DriverUtil.sleep(2000L);
		Select statusSelect = new Select(selecResolution);
		statusSelect.selectByVisibleText("Credit rule exception");
		DriverUtil.sleep(2000L);
		selecResolution.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}

	public RequestMoreInfo clickRequestInfo(){
		StepReport.info("Select RequestMoreInfo" );
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", reqMoreInfo);
		RequestMoreInfo requestMoreInfo = PageFactory.getPage(RequestMoreInfo.class);
		requestMoreInfo.isLoaded();
		return requestMoreInfo;
	}

	public void clickSubmitInformation(){
		StepReport.info("Select SubmitInformation" );
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", clickSubmitInfo);
		DriverUtil.sleep(3000L);
		StepReport.info("Wait for Page to Close");
		SelUtil.waitUntilPageClosed();
	}
	public void clickWithdraw() {
		DriverUtil.sleep(3000L);
		StepReport.info("Click Withdraw");
		String xpath="//*[contains(text(),'Withdraw')]";
		WebElement withdrawElem=DriverUtil.getElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", withdrawElem);
		SelUtil.waitUntilPageClosed();
	}
	public void clickSuspend(){
		StepReport.info("Select Suspend" );
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", clickSuspend);
		DriverUtil.sleep(3000L);
	}

	public void clickResume(){
		StepReport.info("Click on Resume Button  ");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickResume);
		DriverUtil.sleep(2000L);
	}
	public void clickSave(){
		StepReport.info("Select Save" );
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", clickSave);
		DriverUtil.sleep(3000L);
	}




}
