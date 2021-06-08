package com.oracle.fa.qa.selenium.component.bpm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.fa.qa.selenium.base.SeleniumBase;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class contains configuration and UI action related to Login Page in Worklist App
 *
 *
 * @author ashwaraj
 * @version 1.0
 */
public class LoginPage extends BasePageObject<LoginPage> {

    @FindBy(xpath = ".//input[contains(@id,'username')]")
    WebElement userNameField;
    @FindBy(xpath = ".//input[contains(@id,'password')]")
    WebElement passwordField;
    @FindBy(xpath = "//button[.='Login']")
    WebElement loginButton;

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
        if (PageLoadHelper.waitForElementVisible(userNameField) == null) {
            throw new TestErrorException("The UserName field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    /**
     * Login to BPM page
     *
     */
    public BPMHomePage loginToBPMLocal (String userName, String password) {

        System.out.println("########## Inside BPM login method");
        StepReport.info("Logging in to BPM Page");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);

        String bpmURL=FrameworkContext.getInstance().getTestConfigParams().getString("test.base.url");
        loginPage.get(bpmURL);

        PageLoadHelper.waitForElementClickable(loginButton);

        StepReport.info("UserName", userName);
        userNameField.sendKeys(userName);

        StepReport.info("Password", password);
        passwordField.sendKeys(password);

        StepReport.info("Click Login Button");
        loginButton.click();
        BPMHomePage homePage = PageFactory.getPage(BPMHomePage.class);
        homePage.isLoaded();

        return homePage;
    }


    public EMHomePage loginToEMLocal(String userName,String password) {

        StepReport.info("Start EM Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);

        String emURL=FrameworkContext.getInstance().getTestConfigParams().getString("test.em.url");
        loginPage.get(emURL);
        System.out.println("########## Inside EM login method");

        PageLoadHelper.waitForElementClickable(loginButton);
        StepReport.info("UserName", userName);
        userNameField.sendKeys(userName);

        StepReport.info("Password", password);
        passwordField.sendKeys(password);

        StepReport.info("Click Login Button");
        loginButton.click();

        EMHomePage emHomePage = PageFactory.getPage(EMHomePage.class);
        emHomePage.isLoaded();

        return emHomePage;
    }

    public BPMWorklistHomePage loginToBpmWorkList(String userName,String password) {

        StepReport.info("Start BPM Worklist Login");
/*
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);

        String bpmWorklistURL=FrameworkContext.getInstance().getTestConfigParams().getString("test.em.worklistURL");
        loginPage.get(bpmWorklistURL);
*/

        PageLoadHelper.waitForElementClickable(loginButton);
        StepReport.info("UserName", userName);
        userNameField.sendKeys(userName);

        StepReport.info("Password", password);
        passwordField.sendKeys(password);

        StepReport.info("Click Login Button");
        loginButton.click();

        BPMWorklistHomePage bpmWorklistHomePage = PageFactory.getPage(BPMWorklistHomePage.class);
        bpmWorklistHomePage.isLoaded();

        return bpmWorklistHomePage;
    }
}
