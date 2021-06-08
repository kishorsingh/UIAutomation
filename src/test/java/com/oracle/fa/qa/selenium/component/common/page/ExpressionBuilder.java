package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ExpressionBuilder extends BasePageObject<ExpressionBuilder> {

    @FindBy(xpath = "(//*[text()='Expression']/following::textarea)[1]")
    WebElement expressionText;

    @FindBy(xpath = "//*[@id='ok']")
    WebElement okButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(expressionText) == null) {
            throw new TestErrorException("The Expression text area is not visible after" +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void typeExpression(String text) {
        StepReport.info("Type Expression",text);
        PageLoadHelper.waitForElementVisible(expressionText);
        expressionText.sendKeys(text);
        DriverUtil.sleep(2000);
    }

    public void clickOKButton(WebDriver driver){
        StepReport.info("Click OK Button");
        PageLoadHelper.waitForElementVisible(okButton);
        DriverUtil.sleep(2000L);
        System.out.println( ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okButton));
    }
}
