package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.common.page.ReassignTask;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SandBoxConfirmation extends BasePageObject<SandBoxConfirmation> {

    @FindBy(xpath = "//button[text()='OK']")
    WebElement okButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(okButton) == null) {
            throw new TestErrorException("The SandBox Name is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public ManageSandBoxes clickOkButton() {
        StepReport.info("Click OK Button");
        PageLoadHelper.waitForElementClickable(okButton);
        DriverUtil.sleep(2000L);
        okButton.click();
        ManageSandBoxes manageSandBoxes = PageFactory.getPage(ManageSandBoxes.class);
        manageSandBoxes.isLoaded();
        return manageSandBoxes;
    }
}
