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

public class SalesCompensation extends BasePageObject<PartnersPage> {



    @FindBy(xpath = "//*[contains(@id,'FUSE_DISPUTES::icon')]")
    WebElement disputesIcon;


    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(disputesIcon) == null) {
            throw new TestErrorException("The dispute icon is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Sales Compenstaion Page is Loaded");
    }




    public DisputesPage clickDispute() {
        StepReport.info("Click Disputes Flag");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(disputesIcon,20);
        disputesIcon.click();
        DriverUtil.sleep(5000L);
        DisputesPage disputesPage = PageFactory.getPage(DisputesPage.class);
        disputesPage.isLoaded();

        return disputesPage;
    }
}
