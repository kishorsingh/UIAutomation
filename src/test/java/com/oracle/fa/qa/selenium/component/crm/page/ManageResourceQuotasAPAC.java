package com.oracle.fa.qa.selenium.component.crm.page;


import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ManageResourceQuotasAPAC extends BasePageObject<ManageResourceQuotasAPAC> {



	@FindBy(xpath = "//div[text()='Manage Resource Quotas: APAC']")
	WebElement manageResourceQuotasAPAC;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_sales_quotas:0:_FOTsr1:0:pt1:AP1:r2:0:AT5:_ATp:table4:0:it9::content']")
	WebElement inputAPACOwnersQuota;

	@FindBy(xpath = "//*[@id='pt1:_FOr1:1:_FOSritemNode_sales_quotas:0:_FOTsr1:0:pt1:AP1:ATT1:_ATTp:ATTt1:1:it2::content']")
	WebElement inputAPACTerritoryQuota;

	@FindBy(xpath = "//span[text()='Publish Quota']")
	WebElement clickPublish;

	@FindBy(xpath = "//button[text()='OK']")
	WebElement clickOK;


	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(manageResourceQuotasAPAC) == null) {
			throw new TestErrorException("The ManageResourceQuotasAPAC Page  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("ManageResourceQuotasAPAC page is Loaded");
	}

	public void inputAPACQuota(){
		DriverUtil.sleep(2000L);
		StepReport.info("Enter Owners Quota for APAC","100");
		inputAPACOwnersQuota.sendKeys("100");
		DriverUtil.sleep(2000L);
	}

	public void clickOK(){
		DriverUtil.sleep(2000L);
		StepReport.info("Click OK");
		clickOK.click();
		DriverUtil.sleep(5000L);
	}


}
