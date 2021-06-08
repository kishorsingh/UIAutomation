package com.oracle.fa.qa.selenium.component.bpm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.fest.assertions.core.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class contains configuration and UI action related to Login Page in Worklist App
 *
 *
 * @author ashwaraj
 * @version 1.0
 */
public class BPMWorklistHomePage extends BasePageObject<BPMWorklistHomePage> {

    @FindBy(xpath = "//div[text()='BPM Worklist']")
    WebElement BPMWorkListHomePage;

    @FindBy(xpath = "//td[text()='Administration']")
    WebElement administration;

    @FindBy(xpath = "//*[@id='homePageTemplate:usrD1']")
    WebElement adminMenu;

    @FindBy(xpath = "//input[contains(@id,'senderNameText')]")
    WebElement senderNameText;

    @FindBy(xpath = "//*[@id='homePageTemplate:r12:0:appPreferencesSaveButton']")
    WebElement save;

    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logout;

    @FindBy(xpath = "//a[text()='Task Configuration']")
    WebElement taskConfiguration;

    @FindBy(xpath = "//span[text()='Notifications']")
    WebElement notifications;

    @FindBy(xpath = "//*[@title='Expand More']")
    WebElement expandMore;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc05:sor_senderNameSourceOption']/tbody/tr/td[1]/label")
    WebElement emailLabelText;

    @FindBy(xpath = "//*[text()='Not Applicable']")
    WebElement notApplicableoption1;

    @FindBy(xpath = ".//input[contains(@id,'senderNameTextField')]")
    WebElement notApplicableoption2;

    @FindBy(xpath = "//*[text()='Previous Approver']")
    WebElement notApplicableoption3;


    @FindBy(xpath = "//td[contains(text(),'Error: The format is incorrect')]")
    WebElement nonSupportSplChars;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:applyButton::icon']")
    WebElement clickSaveIcon;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc05:sor_senderNameSourceOption:_1']")
    WebElement selectInputOption2;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc05:sor_senderNameSourceOption:_2']")
    WebElement selectInputOption3;

    @FindBy(xpath = "//textarea[@id='homePageTemplate:r1:0:cmtsTxt::content']")
    WebElement addComments;

    @FindBy(xpath = "//*[contains(@id,'cmtsDlg::ok')]")
    WebElement clickOK;

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
        if (PageLoadHelper.waitForElementVisible(BPMWorkListHomePage) == null) {
            throw new TestErrorException("The BPMWorkListHomePage field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void clickAdministration(){

        DriverUtil.sleep(2000L);
        StepReport.info("Click on Administration Page");
        PageLoadHelper.waitForElementVisible(administration);
        administration.click();
    }
    public void clickAdminMenu(){

        DriverUtil.sleep(2000L);
        StepReport.info("Click on Administration Menu");
        PageLoadHelper.waitForElementVisible(adminMenu);
        adminMenu.click();
    }


    public void inputSenderNameAlias(String alias){
        DriverUtil.sleep(2000L);
        StepReport.info("Input SenderName Alias",alias);
        senderNameText.clear();
        DriverUtil.sleep(2000L);
        senderNameText.sendKeys(alias);
        DriverUtil.sleep(2000L);
    }

    public void inputSenderNameAliasHT(String alias){
        DriverUtil.sleep(2000L);
        StepReport.info("Input SenderName Alias","\""+alias+"\"");
        notApplicableoption2.clear();
        DriverUtil.sleep(2000L);
        notApplicableoption2.sendKeys("\""+alias+"\"");
        DriverUtil.sleep(2000L);
    }

    public boolean clickSaveButton(String alias){
        DriverUtil.sleep(2000L);
        StepReport.info("Click Save");
        save.click();
        DriverUtil.sleep(2000L);
        if(alias.contains("<")||alias.contains(">")|| alias.contains(",")){
            boolean error=nonSupportSplChars.isDisplayed();
            StepReport.info("Error Message displayed:"+error);
            return false;
        }else{
            StepReport.info("Changes are saved");
            return true;
        }
    }

    public void logout(){
        DriverUtil.sleep(2000L);
        StepReport.info("Click Logout");
        logout.click();
        DriverUtil.sleep(2000L);
    }

    public void clickTaskConfiguration(){
        DriverUtil.sleep(2000L);
        StepReport.info("Click TaskConfiguration");
        PageLoadHelper.waitForElementVisible(taskConfiguration);
        taskConfiguration.click();
        DriverUtil.sleep(2000L);
    }

    public void clickHumanTask(String humanTaskName){
        DriverUtil.sleep(2000L);
        StepReport.info("Click HumanTask");
        String xpathHT = "//span[text()='"+humanTaskName+"']";
        WebElement humanTask=findElement(By.xpath(xpathHT));
        PageLoadHelper.waitForElementVisible(humanTask);
        humanTask.click();
        DriverUtil.sleep(2000L);
    }

    public void clickNotifications(){
        DriverUtil.sleep(2000L);
        StepReport.info("Click Notifications");
        PageLoadHelper.waitForElementVisible(notifications);
        notifications.click();
        DriverUtil.sleep(2000L);
    }
    public void clickMore(){
        DriverUtil.sleep(2000L);
        StepReport.info("Click Expand More");
        PageLoadHelper.waitForElementVisible(expandMore);
        expandMore.click();
        DriverUtil.sleep(2000L);
    }

    public String getEmailDisplayNameText(){
        DriverUtil.sleep(2000L);
        StepReport.info("Get email display label name text");
        PageLoadHelper.waitForElementVisible(emailLabelText);
        String labelText=emailLabelText.getText();
        DriverUtil.sleep(2000L);
        return labelText;
    }

    public boolean checkEmailOptions1(){
        DriverUtil.sleep(2000L);
        StepReport.info("Check Not-Applicable Option");
        PageLoadHelper.waitForElementVisible(notApplicableoption1);
        boolean option1=notApplicableoption1.isDisplayed();
        return option1;
    }

    public boolean checkEmailOptions2(){
        DriverUtil.sleep(2000L);
        StepReport.info("Check Input Text Field Option");
        PageLoadHelper.waitForElementVisible(notApplicableoption2);
        boolean option2=notApplicableoption2.isDisplayed();
        return option2;
    }

    public boolean checkEmailOptions3(){
        DriverUtil.sleep(2000L);
        StepReport.info("Check Previous Approver Option");
        PageLoadHelper.waitForElementVisible(notApplicableoption3);
        boolean option3=notApplicableoption3.isDisplayed();
        return option3;
    }

    public String readSenderNameAlias(){
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(senderNameText);
        String senderNameAlias=senderNameText.getAttribute("value");
        DriverUtil.sleep(2000L);
        StepReport.info("Sender Name Alias from WorklistApp:",senderNameAlias);
        return senderNameAlias;
    }

    public Boolean clickSaveIcon(String alias){
        DriverUtil.sleep(2000L);
        StepReport.info("click Save Icon");
        PageLoadHelper.waitForElementVisible(clickSaveIcon);
        clickSaveIcon.click();
        DriverUtil.sleep(2000L);
        if(alias.contains("<")||alias.contains(">")|| alias.contains(",")){
            boolean error=nonSupportSplChars.isDisplayed();
            StepReport.info("Error Message displayed:"+error);
            return false;
        }else
        {
            StepReport.info("Changes are successfully saved");
            return true;
        }
    }

    public void selectInputOption2(){
        DriverUtil.sleep(2000L);
        StepReport.info("Select input option");
        selectInputOption2.click();
        DriverUtil.sleep(2000L);
    }

    public void selectInputOption3(){
        DriverUtil.sleep(2000L);
        StepReport.info("Select input option3:Previous Owner");
        selectInputOption3.click();
        DriverUtil.sleep(2000L);
    }

    public void addComments(){
        DriverUtil.sleep(2000L);
        StepReport.info("Add comments");
        addComments.sendKeys("Updated SenderName at HumanTask level");
        DriverUtil.sleep(2000L);
    }
    public void clickOK(){
        DriverUtil.sleep(2000L);
        StepReport.info("click OK Button");
        clickOK.click();
        DriverUtil.sleep(2000L);
    }
}
