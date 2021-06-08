package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubmitResignationPage extends BasePageObject<SubmitResignationPage> {

    @FindBy(xpath = "//*[text()='Review']")
    WebElement reviewButton;

    @FindBy(xpath="//span[text()='m']")
    WebElement submitButton;

    @FindBy(xpath="//span[text()='Y']")
    WebElement yesButton;

    @FindBy(xpath="//span[text()='K']")
    WebElement okButton;

    @FindBy(xpath="//span[text()='Save']")
    WebElement saveButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(reviewButton) == null) {
            throw new TestErrorException("The submit resignation page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void clickReviewButton() {
        StepReport.info("Click review button");
        PageLoadHelper.waitForElementClickable(reviewButton);
        reviewButton.click();
        DriverUtil.sleep(30000L);
    }

    public void clickSubmitButton() {
        StepReport.info("Click submit button");
        PageLoadHelper.waitForElementClickable(submitButton);
        submitButton.click();
        DriverUtil.sleep(10000L);
    }

    public void clickYesButton() {
        StepReport.info("Click Yes button");
        PageLoadHelper.waitForElementClickable(yesButton);
        yesButton.click();
        DriverUtil.sleep(10000L);
    }

    public void clickOkButton() {
        StepReport.info("Click Ok button");
        PageLoadHelper.waitForElementClickable(okButton);
        okButton.click();
        DriverUtil.sleep(10000L);
    }

    public void clickSaveButton() {
        StepReport.info("Click save button");
        PageLoadHelper.waitForElementClickable(saveButton);
        saveButton.click();
        DriverUtil.sleep(10000L);
    }

}
