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
public class ZimbraHome extends BasePageObject<ZimbraHome> {

    @FindBy(xpath = "//*[@id='z_banner']/table/tbody/tr/td/a/div")
    WebElement homePage;


    @FindBy(xpath = "//*[@id='zb__TV__CHECK_MAIL_title']")
    WebElement getEmailRefresh;

    @FindBy(xpath = "//*[text()='Close']")
    WebElement closeEmail;



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
        if (PageLoadHelper.waitForElementVisible(homePage) == null) {
            throw new TestErrorException("The UserName field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Launched zimbra home page");
    }

    public void checkHomePage () {
        StepReport.info("Check Zimbra HomePage");
    }

    public void clickGetMail () {
        StepReport.info("Click for new email");
        DriverUtil.sleep(2000);
        getEmailRefresh.click();
        DriverUtil.sleep(2000);
    }

    public void closeEmail () {
        StepReport.info("Close the email");
        DriverUtil.sleep(2000);
        PageLoadHelper.waitForElementVisible(closeEmail);
        PageLoadHelper.waitForElementClickable(closeEmail);
        closeEmail.click();
        DriverUtil.sleep(2000);
    }

    public void actionOnEmail (String action) {
        StepReport.info("Action on email");
        DriverUtil.sleep(2000);
        String xpathAction = "//a[text()='"+action+"']";
        WebElement actionOnEmail = DriverUtil.getElement(By.xpath(xpathAction));
        PageLoadHelper.waitForElementClickable(actionOnEmail);
        DriverUtil.clickByJS(actionOnEmail);
        DriverUtil.sleep(2000);
    }

    public ZimbraMail openEmail (String subject) {
        StepReport.info("Open Email with Subject",subject);
        String xpathSubject = "//td[contains(text(),'"+subject+"')]";
        WebElement openEmail = DriverUtil.getElement(By.xpath(xpathSubject));
        DriverUtil.sleep(5000L);
        PageLoadHelper.waitForElementClickable(openEmail);
        DriverUtil.clickByAction(openEmail,true);
        DriverUtil.doubleClick(openEmail);
        StepReport.info("New Emails Arrived");
        ZimbraMail zimbraMail = PageFactory.getPage(ZimbraMail.class);
        zimbraMail.isLoaded();
        return zimbraMail;
    }
}
