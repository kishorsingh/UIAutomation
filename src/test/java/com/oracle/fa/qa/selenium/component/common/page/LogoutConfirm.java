package com.oracle.fa.qa.selenium.component.common.page;

import com.github.wiselenium.elements.component.Button;
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

/**
 * Created by bpadhy on 1/17/18.
 */
public class LogoutConfirm extends BasePageObject<LogoutConfirm> {
    @FindBy(id = "Confirm")
    WebElement confirm;


    /**
     * Determine whether or not the component is loaded. When the component is loaded, this method
     * will return, but when it is not loaded, an Error should be thrown. This also allows for complex
     * checking and error reporting when loading a page, which in turn supports better error reporting
     * when a page fails to load.
     * <p>
     * <p>
     * This behaviour makes it readily visible when a page has not been loaded successfully, and
     * because an error and not an exception is thrown tests should fail as expected. By using Error,
     * we also allow the use of junit's "Assert.assert*" methods
     *
     * @throws Error when the page is not loaded.
     */
    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(confirm,20) == null) {
            throw new TestErrorException("The oracle Logo field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Logout page is loaded");
    }

   public void clickLogoutConfirm () {
        DriverUtil.sleep(2000L);
        confirm.click();
    }
    public boolean isLogoutConfirmPageLoaded(){
        if(DriverUtil.isElementPresent(By.xpath("//*[@id='Confirm']"))){
            return true;
        }else{
            return false;
        }
    }
}
