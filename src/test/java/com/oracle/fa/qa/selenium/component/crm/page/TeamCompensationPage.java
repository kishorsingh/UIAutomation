package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamCompensationPage extends BasePageObject<TeamCompensationPage> {



	@FindBy(xpath = "//*[contains(@id,'_FOSrCMP_HCMCOMPTOP_FUSE_TEAM_COMPENSATION')]/div/h1")
	WebElement myTeamCompensation;

	@FindBy(xpath = "//a[text()='GIL EFF_MGR']")
	WebElement employee;


	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(myTeamCompensation) == null) {
			throw new TestErrorException("The myTeamCompensation  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Team Compensation page is Loaded");
	}

	public CompensationPage clickEmployee(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click on Employee:","GIL EFF_MGR");
		employee.click();
		DriverUtil.sleep(2000L);
		CompensationPage compensationPage=PageFactory.getPage(CompensationPage.class);
		compensationPage.isLoaded();
		return compensationPage;
	}


}
