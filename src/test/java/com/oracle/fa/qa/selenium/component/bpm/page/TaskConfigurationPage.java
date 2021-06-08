package com.oracle.fa.qa.selenium.component.bpm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.WebElement;

/**
 * This class contains configuration and UI action related to Task Configuration Page in Worklist App
 *
 *
 * @author ashwaraj
 * @version 1.0
 */

public class TaskConfigurationPage extends BasePageObject<TaskConfigurationPage> {

    /**
     * method to perform action in DTRT Worklist APP
     *
     *
     */
    public void clickOnComposite (WebElement simpleApproval) {
        //PageLoadHelper.waitForJetPageReady(driver);
        PageLoadHelper.waitForElementClickable(simpleApproval);
        StepReport.info("Click on composite");
        simpleApproval.click();
        DriverUtil.sleep(2000);
    }

    public void clickOnCompositeLink (WebElement composite) {
        //PageLoadHelper.waitForJetPageReady(driver);
        PageLoadHelper.waitForElementClickable(composite);
        StepReport.info("Click on composite");
        composite.click();
        DriverUtil.sleep(2000);
    }

    public void editComposite (WebElement editComposite) {
        //PageLoadHelper.waitForJetPageReady(driver);
        StepReport.info("Click on edit composite");
        editComposite.click();
        DriverUtil.sleep(2000);
    }

    public void editAssignee (WebElement assignee) {
        //PageLoadHelper.waitForJetPageReady(driver);
        StepReport.info("Click on asignee tab");
        assignee.click();
        DriverUtil.sleep(2000);
    }

    public void editParticipant (WebElement participant) {
        //PageLoadHelper.waitForJetPageReady(driver);
        StepReport.info("Click on participant");
        participant.click();
        DriverUtil.sleep(2000);
    }

}
