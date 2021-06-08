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

public class ManageSalesQuota extends BasePageObject<ManageSalesQuota> {



	@FindBy(xpath = "//h1[contains(text(),'Manage Sales Quotas')]")
	WebElement manageSalesQuota;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSrCMP_HCMCOMPTOP_FUSE_TEAM_COMPENSATION:0:_FOTsr1:1:r41:0:sp1:cabt1']")
	WebElement actionButton;

	@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSrCMP_HCMCOMPTOP_FUSE_TEAM_COMPENSATION:0:_FOTsr1:1:r41:0:sp1:cmi2']/td[2]")
	WebElement clickManageCompensation;

    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_sales_quotas:0:_FOTsr1:0:pt1:AP1:ATT1:_ATTp:ATTt1:1:cil11::icon']")
    WebElement clickEditAPAC;

    @FindBy(xpath = "//*[@id='pt1:_FOr1:1:_FOSritemNode_sales_quotas:0:_FOTsr1:0:pt1:AP1:ATT1:_ATTp:ATTt1:1:it2::content']")
    WebElement inputAPACTerritoryQuota;

    @FindBy(xpath = "//span[text()='Publish Quota']")
    WebElement clickPublish;



	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(manageSalesQuota) == null) {
			throw new TestErrorException("The Compensation Page  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("ManageSalesQuota page is Loaded");
	}

	public ManageResourceQuotasAPAC clickEditAPAC(){

		DriverUtil.sleep(2000L);
		StepReport.info("Click on Edit Icon for APAC:");
		clickEditAPAC.click();
		DriverUtil.sleep(2000L);
        ManageResourceQuotasAPAC manageResourceQuotasAPAC= PageFactory.getPage(ManageResourceQuotasAPAC.class);
        manageResourceQuotasAPAC.isLoaded();
        return manageResourceQuotasAPAC;
	}

    public void inputAPACTerriotryQuota(){
        DriverUtil.sleep(2000L);
        StepReport.info("Enter Territory Quota for APAC","90");
        DriverUtil.sleep(2000L);
        inputAPACTerritoryQuota.sendKeys("90");
        DriverUtil.sleep(2000L);
    }


    public void clickPublish(){
        DriverUtil.sleep(2000L);
        StepReport.info("Click Publish");
        clickPublish.click();
    }


}
