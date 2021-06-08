package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.hcm.page.AddGoal;
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

import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Random;

public class CareerPerformance extends BasePageObject {

    @FindBy(xpath = "//span[contains(text(),'Career Development')]")
    WebElement careerdev;

    @FindBy(xpath = "//span[contains(text(),'Development Plan:')]")
    WebElement devplan;

    @FindBy(xpath = "//span[contains(text(),'Add Goal')]")
    WebElement addgoal;

    public static String gname;
    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(careerdev) == null) {
            throw new TestErrorException("The Career development page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public AddGoal addGoal() {
        DriverUtil.sleep(4000);
        for (int i = 0; i < 3; i++) {
            try {
                careerdev.click();
                DriverUtil.sleep(3000);
                break;
            } catch (Exception e2) {
                DriverUtil.sleep(3000);
            }
        }
       // careerdev.click();
        StepReport.info("Navigating to click add Goal..");
        DriverUtil.sleep(8000);
        //DriverUtil.waitForElementVisible("//span[contains(text(),'Add Goal')] ", 10);
        for (int i = 0; i < 3; i++) {
            try {
                addgoal.click();
                DriverUtil.sleep(3000);
                break;
            } catch (Exception e2) {
                DriverUtil.sleep(3000);
            }
        }
        //addgoal.click();
        StepReport.info("Added Goal..");
        DriverUtil.sleep(4000);
        AddGoal addGoal = PageFactory.getPage(AddGoal.class);
        addGoal.isLoaded();
        return addGoal;
    }
}
