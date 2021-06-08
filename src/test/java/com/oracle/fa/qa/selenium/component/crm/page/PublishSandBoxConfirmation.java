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

public class PublishSandBoxConfirmation extends BasePageObject<PublishSandBoxConfirmation> {

    @FindBy(xpath = "//button[text()='Yes']")
    WebElement YesButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(YesButton) == null) {
            throw new TestErrorException("The SandBox Confirmation is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void clickYesButton() {
        StepReport.info("Click Yes Button");
        PageLoadHelper.waitForElementClickable(YesButton);
        DriverUtil.sleep(2000L);
        YesButton.click();
        DriverUtil.sleep(2000L);
    }
}
