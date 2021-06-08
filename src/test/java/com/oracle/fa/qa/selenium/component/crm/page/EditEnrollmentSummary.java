package com.oracle.fa.qa.selenium.component.crm.page;

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

public class EditEnrollmentSummary extends BasePageObject<EditEnrollmentSummary> {

    @FindBy(xpath ="//*[text()='Activities']")
    WebElement activities;

    @FindBy(xpath ="//span[contains(text(),'Summary')]")
    WebElement summaryText;

    @FindBy(xpath ="//*[text()='S']")
    WebElement saveAndClose;

    @FindBy(xpath ="//*[contains(@id,'cb3::popEl')]")
    WebElement actions;

    @FindBy(xpath ="//*[text()='Submit for Approval']")
    WebElement submitForApproval;

    @FindBy(xpath ="//*[contains(@id,'pt1:r1:0:ot13')]")
    WebElement enrollmentName;

    @FindBy(xpath ="//*[contains(@id,'soc2::content')]")
    WebElement enrollmentStatus;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(summaryText) == null) {
            throw new TestErrorException("The Summary Text is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Edit Enrollment Page is Loaded");
    }

    public void clickActions() {
        StepReport.info("Click Actions");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(actions);
        DriverUtil.sleep(2000L);
        actions.click();
        DriverUtil.sleep(2000L);

    }

    public void clicksubmitForApproval() {
        StepReport.info("Click Submit for Approval");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(submitForApproval);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(submitForApproval);
        DriverUtil.sleep(2000L);

    }

    public ProgramsPage clickSaveAndClose() {
        StepReport.info("Click Save and Close");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(saveAndClose);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(saveAndClose);
        DriverUtil.sleep(10000L);
        ProgramsPage programsPage = PageFactory.getPage(ProgramsPage.class);
        programsPage.isLoaded();
        return programsPage;

    }

    public String getEnrollmentNumber() {
        StepReport.info("Return Enrollment Number");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(activities);
        DriverUtil.sleep(2000L);
        String enrollmentname;
        enrollmentname = enrollmentName.getText();
        return enrollmentname;
    }

    public String getEnrollmentStatus() {
        StepReport.info("Return Enrollment Status");
        DriverUtil.sleep(3000L);
        return enrollmentStatus.getText();
    }
}
