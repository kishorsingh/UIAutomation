package com.oracle.fa.qa.selenium.component.bpm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class contains configuration and UI action related to Application Preferences Page in Worklist App
 *
 *
 * @author ashwaraj
 * @version 1.0
 */
public class ApplicationPreferencesPage extends BasePageObject<ApplicationPreferencesPage> {

    @FindBy(xpath = "//*[text()='Task Configuration']")
    WebElement taskConfiguration;

    /**
     * method to perform action in DTRT Worklist APP
     *
     *
     */
    public void clickOnTaskConfigurationTab() {
        /*PageLoadHelper.waitForJetPageReady(driver);
        StepReport.info("Click on user manu");
        signOutdropdown.click();
        DriverUtil.sleep(2000);
        StepReport.info("Click on Adminstation");
        administration.click();
        DriverUtil.sleep(4000);*/
        StepReport.info("Click on Task Configuration");
        taskConfiguration.click();
        DriverUtil.sleep(2000);
    }
}
