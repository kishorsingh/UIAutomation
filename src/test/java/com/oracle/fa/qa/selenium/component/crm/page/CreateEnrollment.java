package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateEnrollment extends BasePageObject<CreateEnrollment> {

    @FindBy(xpath ="//input[contains(@id,'partnerName')]")
    WebElement partnerName;

    @FindBy(xpath ="//h1[text()=\"Create Enrollment\"]")
    WebElement createEnrollmentHeader;

    @FindBy(xpath ="//*[contains(@id,'id3::content')]")
    WebElement startDate;

    @FindBy(xpath ="//*[contains(@id,'id2::content')]")
    WebElement endDate;

    @FindBy(xpath ="//*[text()='Save and Continue']")
    WebElement saveAndContinue;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(createEnrollmentHeader) == null) {
            throw new TestErrorException("The Create Enrollment link is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Create Enrollment Page is Loaded");
    }

    public void typePartnerName(String pName) {
        StepReport.info("Type Partner Name:",pName);
        DriverUtil.sleep(3000L);
        partnerName.sendKeys(pName);
        DriverUtil.sleep(3000L);
        partnerName.sendKeys(Keys.RETURN);
        DriverUtil.sleep(3000L);
        partnerName.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

    }

    public void typeStartDate() {
        StepReport.info("Type Start Date","8/16/2018");
        DriverUtil.sleep(3000L);
        startDate.sendKeys("02/16/2018");
        DriverUtil.sleep(3000L);
        startDate.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

    }

    public void typeEndDate() {
        StepReport.info("Type End Date","12/30/2019");
        DriverUtil.sleep(3000L);
        endDate.sendKeys("6/30/2018");
        DriverUtil.sleep(3000L);
        endDate.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

    }

    public EditEnrollmentSummary clickSaveAndContinue() {
        StepReport.info("Click Save and Continue");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(saveAndContinue);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(saveAndContinue);
        DriverUtil.sleep(5000L);
        EditEnrollmentSummary editEnrollmentSummary = PageFactory.getPage(EditEnrollmentSummary.class);
        editEnrollmentSummary.isLoaded();
        return editEnrollmentSummary;

    }
}
