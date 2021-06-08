package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.common.page.ReassignTask;
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

public class CreateSandBox extends BasePageObject<CreateSandBox> {

    @FindBy(xpath = "//*[@id='pt1:_UISmsr:0:AT1:it2::content']")
    WebElement sandBoxName;

    @FindBy(xpath = ".//*[@id='pt1:_UISmsr:0:AT1:it41::content']")
    WebElement sandBoxDescription;

    @FindBy(xpath = "//button[@title='Save and Close']")
    WebElement saveAndClose;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(saveAndClose) == null) {
            throw new TestErrorException("The SandBox Name is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void typeSandBoxName(String sbName) {
        StepReport.info("Type SandBox Name");
        DriverUtil.sleep(3000L);
        sandBoxName.sendKeys(sbName);
        DriverUtil.sleep(3000L);
        sandBoxName.sendKeys(Keys.RETURN);
        DriverUtil.sleep(3000L);
        sandBoxName.sendKeys(Keys.TAB);
        sandBoxDescription.sendKeys(sbName+"_Description");
        DriverUtil.sleep(3000L);

    }

    public SandBoxConfirmation clickSaveAndClose() {
        StepReport.info("Click Save and Close Button");
        PageLoadHelper.waitForElementClickable(saveAndClose);
        DriverUtil.sleep(2000L);
        saveAndClose.click();
        SandBoxConfirmation sandBoxConfirmation = PageFactory.getPage(SandBoxConfirmation.class);
        sandBoxConfirmation.isLoaded();
        return sandBoxConfirmation;
    }
}
