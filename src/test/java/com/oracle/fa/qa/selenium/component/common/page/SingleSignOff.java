package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleSignOff extends BasePageObject<SingleSignOff> {

    @FindBy(xpath = "//*[text()='User logged out successfully']")
    WebElement confirmation;

    public boolean pageLoaded() {
        if (PageLoadHelper.waitForElementClickable(confirmation,15L) == null) {
            return false;
        }
        else{
            return true;
        }
    }
}
