package com.oracle.fa.qa.selenium.component.bpm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;


/**
 * This class contains configuration and UI action related to EM Home Page
 *
 *
 * @author vevinnak
 * @version 1.0
 */
public class EMDailoguePage extends BasePageObject<EMDailoguePage> {

    @FindBy(xpath = "//*[text()='Send Test Notification']")
    WebElement sendTestNotificationTitle;

    @FindBy(xpath = "//input[contains(@id,'recipientAddressTextID')]")
    WebElement inputRecipientAddress;

    @FindBy(xpath = "//input[contains(@id,'subjectTextID')]")
    WebElement inputSubject;

    @FindBy(xpath = "//textarea[contains(@id,'messageTextID')]")
    WebElement inputBody;

    @FindBy(xpath = "//select[contains(@id,'channelTypesID')]")
    WebElement inputChannelType;

    @FindBy(xpath = "//button[text()='Send']")
    WebElement sendButton;

    @FindBy(xpath = "//span[contains(@id,'responseTextID')]")
    WebElement response;

    @FindBy(xpath = "//*[@id='emTemplate:taskEngineMailerContentTemplate:popupTestNotificationDlg::close']")
    WebElement close;



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
    public void isLoaded() {
        if ((PageLoadHelper.waitForElementClickable(sendTestNotificationTitle,240) == null)) {
            throw new TestErrorException("EM sendTestNotificationTitle is not loaded" +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void typeRecipientAddress(String recipientAddressEmailID){
        StepReport.info("Input Recipient Address Email ID");
        DriverUtil.sleep(2000L);
        inputRecipientAddress.sendKeys(recipientAddressEmailID);
        DriverUtil.sleep(2000L);
    }

    public void selectChannelType(){
        StepReport.info("Select Channel Type as Email");
        Select statusSelect = new Select(inputChannelType);
        statusSelect.selectByVisibleText("Email");
        DriverUtil.sleep(1000L);
        inputChannelType.sendKeys(Keys.RETURN);
      }

    public void typeSubject(){
        StepReport.info("Input Subject");
        DriverUtil.sleep(200L);
        inputSubject.sendKeys("Verifying Send Test Notifications");
    }

    public void typeEmailBody(){
        StepReport.info("Input Subject");
        DriverUtil.sleep(200L);
        inputBody.sendKeys("Sample Text");
    }
    public void clickSendButton(){
        StepReport.info("Click Send Button");
        DriverUtil.sleep(200L);
        sendButton.click();
        DriverUtil.sleep(2000L);
    }

    public void checkResponse(){
        StepReport.info("Check Response");
        DriverUtil.sleep(2000L);
        String emailResponse=response.getText();
        StepReport.info("Email Response: ",emailResponse);
        Assert.assertEquals(emailResponse,"SENT");
        DriverUtil.sleep(2000L);
    }

    public void clickClose(){
        StepReport.info("Click Close");
        DriverUtil.sleep(2000L);
        close.click();
        DriverUtil.sleep(2000L);
    }


}