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

public class TerritoriesPage extends BasePageObject<TerritoriesPage> {



	@FindBy(xpath = "//h1[text()='Active Territories']")
	WebElement activeTerritories;

	@FindBy(xpath = "//button[contains(text(),'Manage Proposals')]")
	WebElement manageProposals;

	@FindBy(xpath = "//button[contains(text(),'Show Dimensions')]")
	WebElement clickShowDimensions;

	@FindBy(xpath = "//button[contains(text(),'Show Metrics')]")
	WebElement clickShowMetrics;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_sales_territories_and_quotas:0:_FOTsr1:0:pt1:AP1:r1:0:pc1:np1:colGroupLink2']")
	WebElement DimensionsLink;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_sales_territories_and_quotas:0:_FOTsr1:0:pt1:AP1:r1:0:pc1:np1:colGroupLink3']")
	WebElement MetricsLink;


	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(manageProposals) == null) {
			throw new TestErrorException("The partnerName  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Active Territories page is Loaded");
	}






	public void clickShowDimensions() {
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickShowDimensions);
		DriverUtil.sleep(2000L);
		if (PageLoadHelper.waitForElementClickable(DimensionsLink) == null) {
			throw new TestErrorException("The Dimensions Link  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("Click Show Dimensions ");
	}

	public void clickShowMetrics() {
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickShowMetrics);
		DriverUtil.sleep(2000L);
		if (PageLoadHelper.waitForElementClickable(MetricsLink) == null) {
			throw new TestErrorException("The Metrics Link  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("Click Show Metrics ");
	}




}
