package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LegalEmployerSearchPopUp extends BasePageObject<LegalEmployerSearchPopUp> {

    @FindBy(xpath = "//label[text()=' Legal Employer']/preceding-sibling::input")
    WebElement searchBox;
    @FindBy(xpath = "//button[text()='Search']")
    WebElement searchButton;
    @Override
    protected void isLoaded() {
        // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(searchButton) == null) {

            throw new TestErrorException("The Legal Employee  search page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    //public void enterLegal
}
