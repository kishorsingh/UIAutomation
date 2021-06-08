package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.fin.page.RequestMoreInformation;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RequestMoreInfo extends BasePageObject<RequestMoreInfo> {
    @FindBy(css = "textarea[id*='commentsForRequestInfo::content']")
    WebElement comment;

    @FindBy(css = "button[id='reqIfD::ok']")
    WebElement okButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(okButton) == null) {
            throw new TestErrorException("The Ok Button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void typeComment(String text) {
        StepReport.info("Type Comment");
        PageLoadHelper.waitForElementVisible(comment);
        DriverUtil.sleep(1000L);
        comment.sendKeys(text);
        DriverUtil.sleep(2000L);
    }

    public void clickOK() {
        StepReport.info("Click OK");
        Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        Action mouseClick = builder.click(okButton).build();
        mouseClick.perform();
        DriverUtil.sleep(7000L);
        SelUtil.waitUntilPageClosed();
    }

}
