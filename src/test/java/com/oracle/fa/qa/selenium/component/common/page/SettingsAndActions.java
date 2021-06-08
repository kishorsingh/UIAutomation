package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.prc.page.Requisitions;
import com.oracle.fa.qa.selenium.component.crm.page.*;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsAndActions extends BasePageObject<ReassignTask> {

    @FindBy(xpath = "//*[text()='Sign Out']")
    WebElement signOut;

    @FindBy(xpath = "//*[contains(text(),'Manage Sandboxes')]")
    WebElement manageSandBoxes;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(signOut) == null) {
            throw new TestErrorException("The SignOut link is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public ManageSandBoxes clickManageSandBoxes() {
        StepReport.info("Click Manage Sandboxes...");
        PageLoadHelper.waitForElementClickable(manageSandBoxes);
        DriverUtil.sleep(2000L);
        manageSandBoxes.click();
        ManageSandBoxes manageSandBoxes = PageFactory.getPage(ManageSandBoxes.class);
        manageSandBoxes.isLoaded();
        return manageSandBoxes;
    }
}
