package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GoalSubmitPage extends HCMBasePageObject {

    @FindBy(xpath = "//span[contains(text(),'Career Development')]")
    WebElement Careerdev;




    protected void isLoaded() {
        // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(Careerdev) == null) {

            throw new TestErrorException("The Change Location page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }



}

