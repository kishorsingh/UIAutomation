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

public class ManageCompensationPage extends BasePageObject<ManageCompensationPage> {



	@FindBy(xpath = "//h1[contains(text(),'Manage Compensation')]")
	WebElement ManageCompensationPage;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSrCMP_HCMCOMPTOP_FUSE_TEAM_COMPENSATION:0:_FOTsr1:1:r41:0:sp1:cabt1']")
	WebElement actionButton;

	@FindBy(xpath = "//span[text()='Award Compensation']")
	WebElement awardCompensation;

	@FindBy(xpath = "//*[@accesskey='u']")
	WebElement clickContinue;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(ManageCompensationPage) == null) {
			throw new TestErrorException("The ManageCompensationPage Page  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("ManageCompensationPage page is Loaded");
	}

	public AwardCompensation clickAwardCompensation(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click on Award Compensation:");
		awardCompensation.click();
		DriverUtil.sleep(2000L);
		AwardCompensation awardCompensation= PageFactory.getPage(AwardCompensation.class);
		awardCompensation.isLoaded();
		return awardCompensation;
	}

	public ReviewCompensationPage clickContinue(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click on Conitnue on Manage Compensation Page");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(clickContinue);
        clickContinue.click();
		DriverUtil.sleep(2000L);
		ReviewCompensationPage reviewCompensationPage= PageFactory.getPage(ReviewCompensationPage.class);
		reviewCompensationPage.isLoaded();
		return reviewCompensationPage;
	}

}
