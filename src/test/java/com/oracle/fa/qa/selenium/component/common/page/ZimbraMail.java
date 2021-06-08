package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bpadhy on 1/17/18.
 */
public class ZimbraMail extends BasePageObject<ZimbraMail> {

    @FindBy(xpath = "//*[text()='Subject']")
    WebElement subject;

    @FindBy(xpath = "//*[text()='Close']")
    WebElement closeEmail;

    @FindBy(xpath = "//*[@id='zb__MSG__CLOSE_title']")
    WebElement closeTitle;

    @FindBy(xpath = "//a[text()='Approve']")
    WebElement actionApprove;

    @FindBy(xpath = "//a[text()=' Workspace Application ']")
    WebElement worklistApp;

    @FindBy(xpath = "//*[contains(@id,'textarea_DWT')]")
    WebElement emailBody;

    @FindBy(xpath = "//*[@id='zb__COMPOSE__SEND_title']")
    WebElement sendEmail;

    @FindBy(xpath = "//a[text()='Log Out']")
    WebElement logOut;

    @FindBy(xpath = "//*[@id='zb__COMPOSE__ATTACHMENT_title']")
    WebElement attachments;

    @FindBy(xpath = "//*[@id='zv__MSG__MSG_highlightObjects_link']")
    WebElement highlightObjectsLink;


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
        if (PageLoadHelper.waitForElementVisible(closeTitle) == null) {
            throw new TestErrorException("The UserName field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Launched zimbra mail home page");
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
        StepReport.info("Action on email",action);
        DriverUtil.sleep(2000);
        String xpathAction = "//a[text()='"+action+"']";
        WebElement actionOnEmail = DriverUtil.getElement(By.xpath(xpathAction));
        DriverUtil.switchToFrame("zv__MSG__MSG_body__iframe");
        actionOnEmail.click();
        //DriverUtil.clickByJS(actionOnEmail);
        DriverUtil.sleep(2000);
    }

    public void clickOnApprove(String actionType){
        StepReport.info("Click on :"+ actionType);
        DriverUtil.sleep(5000);
        String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        DriverUtil.switchToFrame("zv__MSG__MSG_body__iframe");
        String xpathforAction="//a[text()='"+actionType+"']";
        WebElement actionOnEmail = DriverUtil.getElement(By.xpath(xpathforAction));
        actionOnEmail.click();
        DriverUtil.sleep(3000);
        driver.switchTo().window(mainWindow);
        DriverUtil.sleep(1000);
    }

    public void clickOnHighlightObjects(){
        StepReport.info("Click on HighlightObjects");
        DriverUtil.sleep(5000);
       // String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
       // WebDriver driver = FrameworkContext.getInstance().getWebDriver();
       // DriverUtil.switchToFrame("zv__MSG__MSG_body__iframe");
        highlightObjectsLink.click();
        DriverUtil.sleep(3000);
        //driver.switchTo().window(mainWindow);
        DriverUtil.sleep(1000);
    }


    public void addComments(String actionType){

        StepReport.info("Adding comments");
        emailBody.sendKeys(Keys.PAGE_UP);
        DriverUtil.sleep(2000);
        emailBody.sendKeys( actionType+": comments added by Selenium Automation Code");
        emailBody.sendKeys(Keys.ENTER);
        DriverUtil.sleep(1000);
    }

    public void sendEmail(){

        DriverUtil.sleep(3000);
        StepReport.info("Click Send Email");
        DriverUtil.clickByAction(sendEmail,true);
        DriverUtil.sleep(2000);
    }
    public void logout () {
        StepReport.info("Logout the email");
        DriverUtil.sleep(2000);
        PageLoadHelper.waitForElementClickable(logOut);
        logOut.click();
        DriverUtil.sleep(2000);
    }

    public ZimbraAttachments clickAttachments () {
        StepReport.info("Click on Attachments");
        DriverUtil.sleep(2000);
        PageLoadHelper.waitForElementClickable(attachments);
        attachments.click();
        DriverUtil.sleep(2000);
        ZimbraAttachments zimbraAttachments = PageFactory.getPage(ZimbraAttachments.class);
        zimbraAttachments.isLoaded();
        return zimbraAttachments;
    }
}