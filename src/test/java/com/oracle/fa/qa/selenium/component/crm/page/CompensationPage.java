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

public class CompensationPage extends BasePageObject<CompensationPage> {



	@FindBy(xpath = "//h1[contains(text(),': Compensation')]")
	WebElement compensationPage;

	@FindBy(xpath = "//span[text()='Actions']/parent::button")
	WebElement actionsButton;

	@FindBy(xpath = "//td[text()='Manage Compensation']")
	WebElement clickManageCompensation;

	@FindBy(xpath = "//img[@src='/hcmUI/images/func_contextpop_orange_20_hov.png']")
	WebElement orangeIcon;

	@FindBy(xpath = "//a[text()='Compensation']")
	WebElement compensationLink;

	@FindBy(xpath = "//a[text()='Manage Compensation']")
	WebElement manageCompensationLink;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(compensationPage) == null) {
			throw new TestErrorException("The Compensation Page  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Compensation page is Loaded");
	}

	public void clickAction(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click on Action:");
		actionsButton.click();
		DriverUtil.sleep(2000L);
	}

	public ManageCompensationPage clickManageCompensation(){
		StepReport.info("Click on orange icon:");
		PageLoadHelper.waitForElementVisible(orangeIcon);
		orangeIcon.click();

		PageLoadHelper.waitForElementVisible(compensationLink);
		DriverUtil.sleep(2000L);
		compensationLink.click();
		DriverUtil.sleep(2000L);
		StepReport.info("Click on ManageCompensation:");
		PageLoadHelper.waitForElementVisible(manageCompensationLink);
		manageCompensationLink.click();

		DriverUtil.sleep(2000L);
		ManageCompensationPage manageCompensationPage= PageFactory.getPage(ManageCompensationPage.class);
		manageCompensationPage.isLoaded();
		DriverUtil.sleep(5000L);
		return manageCompensationPage;
	}

}
