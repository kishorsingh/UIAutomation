package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.bpm.page.EMHomePage;
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
public class ZimbraLogin extends BasePageObject<ZimbraLogin> {

    @FindBy(xpath = "//*[@id='sso_username']")
    WebElement ssoUserName;

    @FindBy(xpath = "//*[@id='ssopassword']")
    WebElement ssoPassword;

    @FindBy(xpath = "//*[@id='pageone']/div[2]/div[1]/form/ul/li[4]/a")
    WebElement clickSignIn;

    @FindBy(id = "btnActive")
    WebElement signinButton;

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
        if (PageLoadHelper.waitForElementVisible(ssoUserName) == null) {
            throw new TestErrorException("The UserName field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Launched zimbra login web page");
    }

    public void enterUserName (String userName) {
        StepReport.info("Enter UserName", userName);
        ssoUserName.sendKeys(userName);
    }

    public void enterPassword (String password) {
        StepReport.info("Enter Password", password);
        ssoPassword.sendKeys(password);
    }

    public ZimbraHome clickSignin () {
        StepReport.info("Click Signin Button");
        clickSignIn.click();
        ZimbraHome zimbraHome = PageFactory.getPage(ZimbraHome.class);
        zimbraHome.isLoaded();
        return zimbraHome;
    }



    public EMHomePage clickSigninToEM () {
        StepReport.info("Click Signin Button");
        signinButton.click();
        EMHomePage emHomePage = PageFactory.getPage(EMHomePage.class);
        emHomePage.isLoaded();
        return emHomePage;
    }

    public EMHomePage loginToEM (String userName, String password) {
        PageLoadHelper.waitForElementClickable(signinButton);
        enterUserName(userName);
        enterPassword(password);
        EMHomePage homePage = clickSigninToEM();
        // DriverUtil.sleep(3000);
        return homePage;
    }

    public HomePage loginInAllLanguage(String userName, String password) {
        StepReport.info("LoginPage: login in all language");
        PageLoadHelper.waitForElementClickable(signinButton);
        enterUserName(userName);
        enterPassword(password);
        StepReport.info("Click Signin Button");
        signinButton.click();

        StepReport.info("Loading home page");
        HomePage homePage = PageFactory.getPage(HomePage.class);
        String searchXPath = "//input[contains(@id, 'content')]";
        WebElement search = DriverUtil.getElement(By.xpath(searchXPath));
        if (PageLoadHelper.waitForElementClickable(search) == null) {
            throw new TestErrorException("The Getting Started link is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        return homePage;
    }
}
