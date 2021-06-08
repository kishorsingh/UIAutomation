package com.oracle.fa.qa.selenium.component.bpm.page;

import com.github.wiselenium.core.test.Driver;
import com.oracle.fa.qa.selenium.component.bpm.test.EmailNotificationsTest;
import com.oracle.fa.qa.selenium.component.crm.page.AddAttachments;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

import java.util.List;


/**
 * This class contains configuration and UI action related to EM Home Page
 *
 *
 * @author ashwaraj
 * @version 1.0
 */
public class EMHomePage extends BasePageObject<EMHomePage> {

    @FindBy(xpath = "//*[text()='Log Out']")
    WebElement signOut;

    @FindBy(xpath = "//*[@id='emTemplate:cpTree:2::di']")
    WebElement soaMenu;

    @FindBy(xpath = "//*[@id='emTemplate:cpTree:6::di']")
    WebElement soaInfraMenu;

    //@FindBy(xpath = "//*[@id='emTemplate:cpTree:7::di']")
    @FindBy(xpath = "//*[@id='emTemplate:cpTree:7:grypgl1']")
    //@FindBy(xpath = "//*[text()='Default']" )
            WebElement soaDefaultPatititionMenu;

    @FindBy(xpath = "//*[text()='Deployment']")
    WebElement soaDeployementMenu;

    //@FindBy(xpath = "//*[text()='Deploy To This Partition...']")
    @FindBy(xpath = "//*[text()='Deployment']/following::td[text()='Deploy To This Partition...']")
    WebElement deployToThisPartitionMenu;

    @FindBy(xpath = "//label[.='Archive Location']/preceding::input[1]")
    WebElement browseArchive;

    @FindBy(xpath = "//*[text()='File name:']")
    WebElement fileNameInputField;

    @FindBy(xpath = "//*[@id='emTrainTemplate:myNextButton1']")
    WebElement deploymentNextButton;

    @FindBy(xpath = "//*[text()='Error']")
    WebElement errorMsg;

    @FindBy(xpath = "(//div[contains(@id,'msgDlg')])[5]")
    WebElement msgDialogInfoOnPopup;

    @FindBy(xpath = "//*[@id='emTrainTemplate:deployConfig3']")
    WebElement deployButton;

    @FindBy(xpath = "//*[@id='adaPreferencescb1']")
    WebElement accessibilityPreference;

    @FindBy(xpath ="//*[text()='Confirmation']")
    WebElement deploymentConfirmation;

    @FindBy(xpath = "//*[@id='emTemplate:cpTree:6:targetLink']")
    WebElement soaInfraLink;

    //@FindBy(xpath = "//*[text()='SOA Infrastructure']")
    @FindBy(xpath = "//*[@id='emTemplate:oracle_soainfra']")
    WebElement soaInfrastuctureMenu;

    @FindBy(xpath = "//*[@id='emTemplate:defaultTarget_logs']/td[2]")
    WebElement soaInfrastuctureLogsMenu;

    @FindBy(xpath = "//div[contains(@id,'defaultTarget_logs')]//td[.='Log Configuration']")
    WebElement soaInfrastuctureLogConfiguration;

    @FindBy(xpath = "//*[@id='emTemplate:logConfig11::content']")
    WebElement soaInfrastuctureLogConfigurationViewMenu;

    @FindBy(xpath = "//*[text()='Loggers With Persistent Log Level State']")
    WebElement soaInfrastuctureLogConfigurationOption;

    @FindBy(xpath = "//*[@id='emTemplate:searchField::content']")
    WebElement soaInfrastuctureLogConfigurationInputSearch;

    @FindBy(xpath = "//*[@id='emTemplate:loggerSearchBtn::icon']")
    WebElement soaInfrastuctureLogConfigurationSearchButton;

    @FindBy(xpath = "//*[text()='INCIDENT_ERROR:1 (SEVERE+100)']")
    WebElement soaInfrastuctureIncidentError1;

    @FindBy(xpath = "//*[text()='ERROR:1 (SEVERE)']")
    WebElement soaInfrastuctureError1;

    @FindBy(xpath = "//*[text()='WARNING:1 (WARNING)']")
    WebElement soaInfrastuctureWarning1;

    @FindBy(xpath = "//*[text()='NOTIFICATION:1 (INFO)")
    WebElement soaInfrastuctureNotification1;

    @FindBy(xpath = "//*[text()='NOTIFICATION:16 (CONFIG)']")
    WebElement soaInfrastuctureNotification16;

    @FindBy(xpath = "//*[text()='NOTIFICATION:32']")
    WebElement soaInfrastuctureNotification32;

    @FindBy(xpath = "//*[text()='TRACE:1 (FINE)']")
    WebElement soaInfrastuctureTrace1;

    @FindBy(xpath = "//*[text()='TRACE:16 (FINER)']")
    WebElement soaInfrastuctureTrace16;

    @FindBy(xpath = "//*[text()='TRACE:32 (FINEST)']")
    WebElement soaInfrastuctureTrace32;

    @FindBy(xpath = "//*[text()='NOTIFICATION:1 (INFO) [Inherited from parent]']")
    WebElement soaInfrastuctureNotification1InheritedFromParents;

    @FindBy(xpath = "//*[@id='emTemplate:applyChanges1']")
    WebElement applyLogConfigurationChanges;

    @FindBy(xpath = "//*[text()='Update Log Levels - Completed Successfully']")
    WebElement logConfigurationUpdatedConfirmation;

    @FindBy(xpath = "//*[@id='okCommand']")
    WebElement closeButtion;

    @FindBy(xpath = "//span[text()='default']")
    WebElement defaultLink;

    @FindBy(xpath = "//*[text()='Deployment']")
    WebElement deploymentMenu;

    @FindBy(xpath = "//button[text()='OK'][contains(@id,'msgDlg')]")
    WebElement okButtonMsgDlg;

    @FindBy(xpath = "//button[contains(@id,'cancelButton')]")
    WebElement cancelButton;

    @FindBy(xpath = "//*[contains(@id,'SSOLoginUsername::content')]")
    WebElement userName;

    @FindBy(xpath = "//*[contains(@id,'SSOLoginUsernamePassword::content')]")
    WebElement password;

    @FindBy(xpath = "//a[text()='SOA Composite']")
    WebElement soaComposite;

    @FindBy(xpath = "//td[text()='Export...']")
    WebElement export;

    @FindBy(xpath = "//*[text()='Search']/following::input[1]")
    WebElement searchField;

    @FindBy(xpath = "//*[text()='Search']/following::a[1]")
    WebElement searchIcon;

    @FindBy(xpath = ".//span[@title='Farm_WLS_SOAWC']")
    WebElement farmSOA;

    @FindBy(xpath = "//span[.='Test']")
    WebElement test;

    @FindBy(xpath = ".//*[text()='payload']/following::input[3]")
    WebElement payload;

    @FindBy(xpath = "(//button[text()='Test Web Service'])[1]")
    WebElement testWebService;

    @FindBy(xpath = "id('emTemplate:testexecstatus')")
    WebElement testExecStatus;

    @FindBy(xpath = "//button[text()='Launch Flow Trace']")
    WebElement launchFlowTrace;

    //@FindBy(xpath = ".//*[text()='Show Instance IDs']/following::input[1]")
    @FindBy(xpath = "id('flowTraceTemplate:auditTreeContentTemplate:instanceCheckBox::content')")
    WebElement showInstanceIds;

    @FindBy(xpath = "//*[contains(@title,'HumantaskFGA')]")
    WebElement humanTask;

    @FindBy(xpath = ".//a[.='SOA Composite']")
    WebElement soaCompositeMenu;

    @FindBy(xpath = ".//a[.='SOA Composite']/following::td[text()='SOA Deployment']")
    WebElement soaCompositeMenuSoaDeploymentLink;

    @FindBy(xpath = ".//a[.='SOA Composite']/following::td[text()='SOA Deployment']/following::td[text()='Undeploy...']")
    WebElement soaCompositeMenuSoaDeploymentUndeployLink;

    @FindBy(xpath = ".//button[.='Undeploy']")
    WebElement unDeploy;

    @FindBy(xpath = "//*[text()='Abort...']")
    WebElement abort;

    @FindBy(xpath = "//a[.='soa_server1']")
    WebElement soaServer;

    @FindBy(xpath = "//a[.='soa_server1']/following::a[.='WebLogic Server']")
    WebElement weblogicServer;


    @FindBy(xpath = "//a[.='soa_server1']/following::a[.='WebLogic Server']/following::td[.='Application Deployment']")
    WebElement  applicationDeployment;

    @FindBy(xpath = "(//td[text()='Deploy...'])[1]")
    WebElement  applicationDeploymentDeploy;

    @FindBy(xpath = "//a[.='WLS_SOAWC']")
    WebElement weblogicDomainHomeLink;

    @FindBy(xpath = "//a[.='WLS_SOAWC']/following::a[.='WebLogic Domain']")
    WebElement weblogicDomainLink;

    @FindBy(xpath = "//a[.='WLS_SOAWC']/following::a[.='WebLogic Domain']/following::td[.='Application Deployment']")
    WebElement weblogicDomainAppDepLink;

    @FindBy(xpath = "(//a[.='WLS_SOAWC']/following::a[.='WebLogic Domain']/following::td[.='Application Deployment']/following::td[.='Deploy...'])[2]")
    WebElement weblogicDomainAppDepDeployLink;

    @FindBy(xpath = "//td[.='soa_server1']/descendant::input[@type='checkbox']")
    WebElement soaServerCheckboxDeploy;

    @FindBy(xpath = "(//span[contains(@id,'deployprogress' )])[1]")
    WebElement earDeployProgress;

    @FindBy(xpath = "//*[text()='Clustered Application Deployment']/following::*[text()='Logs']")
    WebElement logs;

    @FindBy(xpath = "(//*[text()='View Log Messages'])[2]")
    WebElement viewLogMessages;

    @FindBy(xpath = "(//*[text()='Message']/following::input)[1]")
    WebElement message;

    @FindBy(xpath = "//button[@title='Search']")
    WebElement searchButton;

    @FindBy(xpath = "//a[text()='Clustered Application Deployment']")
    WebElement clusteredApplicationDeployment;

    @FindBy(xpath = "//*[@title='Collapse Pane']/img")
    WebElement collapsePane;

    @FindBy(xpath = "//*[contains(text(),'soa-infra')]")
    WebElement soainfraAdminServer;

    @FindBy(xpath = "//a[@title='Human Workflow Engine']")
    WebElement humanWorkflow;

    @FindBy(xpath = "//*[@title='Expand Service Engines']")
    WebElement expandServiceEngines;

    @FindBy(xpath = "//a[text()='Notification Management']")
    WebElement notificationManagement;

    @FindBy(xpath = "//button[text()='Send Test Notification...']")
    WebElement clickSendTestNotification;

    @FindBy(xpath = "//*[text()='Administration']")
    WebElement Administration;

    @FindBy(xpath = "//*[text()='System MBean Browser']")
    WebElement clickSystemMBeanBrowser;

    @FindBy(xpath = "//img[@id='emTemplate:idSearchHidShowBtn::icon']")
    WebElement clickSearch;

    @FindBy(xpath = "//*[@id='emTemplate:search1:searchInputText1::content']")
    WebElement inputMBeanBrowserSearch;

    @FindBy(xpath = "//*[@id='emTemplate:search1::search_icon']")
    WebElement clickSearchMBeanButton;

    @FindBy(xpath = "//input[contains(@id,'MBeanAttrTable:0')]")
    WebElement emailFromAddress;

    @FindBy(xpath = "//button[text()='Apply']")
    WebElement applyButton;

    @FindBy(xpath = "//a[text()='ASNSDriverEmailFromAddress']")
    WebElement ASNSDriverEmailFromAddress;

    @FindBy(xpath = "//*[@id='emTemplate:idAttributeValueTextInput::content']")
    WebElement editASNSDriverEmailFromAddress;




//    @FindBy(xpath = ".//a[.='WebLogic Domain']/following::td[text()='Application Deployment']")
//    WebElement  applicationDeployment;

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
        if ((PageLoadHelper.waitForElementClickable(signOut,240) == null)) {
            throw new TestErrorException("EM page is not loaded" +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
    /**
     * method to perform signout action in Worklist APP
     *
     *
     */
    public void signout() {
        //StepReport.info("Click Signout Dropdown");
        //DriverUtil.sleep(8000);
        PageLoadHelper.waitForElementClickable(signOut);
        StepReport.info("Click Signout");
        signOut.click();
    }

    public void clickContinueIfAccessibilityPrefDialogExist(){
        if(isElementVisible("//*[@id='adaPreferencescb1']"))
        {
            accessibilityPreference.click();
            DriverUtil.sleep(8000);
        }
    }


    /***
     *
     * @param componentName
     * @param logLevel
     *
     * Method to update the Log Level for selected component
     * Valid input for Log levels are
     * 1. INCIDENT_ERROR:1 (SEVERE+100)
     * 2. ERROR:1 (SEVERE)
     * 3. WARNING:1 (WARNING)
     * 4. NOTIFICATION:1 (INFO)
     * 5. NOTIFICATION:16 (CONFIG)
     * 6. NOTIFICATION:32
     * 7. TRACE:1 (FINE)
     * 8. TRACE:16 (FINER)
     * 9. TRACE:32 (FINEST)
     * 10. NOTIFICATION:1 (INFO) [Inherited from parent]
     *
     */
    public void changeLogLevel(String componentName, String logLevel) {
        StepReport.info("Changing the Log Level for component "+componentName+" to "+logLevel+".");
        DriverUtil.sleep(8000);
        DriverUtil.sleep(8000);
        PageLoadHelper.waitForElementClickable(soaMenu);
        StepReport.info("Found the SOA server");
        DriverUtil.sleep(2000);
        soaMenu.click();
        StepReport.info("Found soa-Infra configuration");
        DriverUtil.sleep(2000);
        soaInfraLink.click();
        StepReport.info("Updating SOA Infrastucure configuration");
        DriverUtil.sleep(2000);
        soaInfrastuctureMenu.click();
        DriverUtil.sleep(2000);
        StepReport.info("Updating SOA Infrastucure configuration Log Level");
        soaInfrastuctureLogsMenu.click();
        DriverUtil.sleep(10000);
        soaInfrastuctureLogConfiguration.click();
        DriverUtil.sleep(2000);
        soaInfrastuctureLogConfigurationInputSearch.sendKeys(componentName);
        DriverUtil.sleep(2000);
        soaInfrastuctureLogConfigurationSearchButton.click();
        DriverUtil.sleep(2000);
        StepReport.info("Searching for Log Level "+logLevel);
        if(logLevel.equalsIgnoreCase("INCIDENT_ERROR:1 (SEVERE+100)"))
            soaInfrastuctureIncidentError1.click();
        else if (logLevel.equalsIgnoreCase("ERROR:1 (SEVERE)"))
            soaInfrastuctureIncidentError1.click();
        else if (logLevel.equalsIgnoreCase("WARNING:1 (WARNING)"))
            soaInfrastuctureWarning1.click();
        else if (logLevel.equalsIgnoreCase("NOTIFICATION:1 (INFO)"))
            soaInfrastuctureNotification1.click();
        else if (logLevel.equalsIgnoreCase("NOTIFICATION:16 (CONFIG)"))
            soaInfrastuctureNotification16.click();
        else if (logLevel.equalsIgnoreCase("NOTIFICATION:32"))
            soaInfrastuctureNotification32.click();
        else if (logLevel.equalsIgnoreCase("TRACE:1 (FINE)"))
            soaInfrastuctureTrace1.click();
        else if (logLevel.equalsIgnoreCase("TRACE:16 (FINER)"))
            soaInfrastuctureTrace16.click();
        else if (logLevel.equalsIgnoreCase("TRACE:32 (FINEST)"))
            soaInfrastuctureTrace32.click();
        else if (logLevel.equalsIgnoreCase("NOTIFICATION:1 (INFO) [Inherited from parent]"))
            soaInfrastuctureNotification1InheritedFromParents.click();
        DriverUtil.sleep(2000);
        StepReport.info("Found the Log Level");
        applyLogConfigurationChanges.click();
        DriverUtil.sleep(2000);
        StepReport.info("Saving the changes");
        if (logConfigurationUpdatedConfirmation.isDisplayed())
            StepReport.info("Log configuration updated successfully");
        else
            StepReport.info("Log configuration could not be updated successfully");
        closeButtion.click();
    }

    public void expand(String text){
        StepReport.info("Expand",text);
        String xpath = "//*[text()='"+text+"']/parent::*/parent::*/preceding-sibling::span/a";
        WebElement link = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(link);
        String title = link.getAttribute("title");
        DriverUtil.sleep(2000);
        if(title.equalsIgnoreCase("Expand")) {
            xpath = "//*[text()='"+text+"']/parent::*/parent::*/preceding-sibling::span/a[@title='Expand']";
            link = DriverUtil.getElement(By.xpath(xpath));
            PageLoadHelper.waitForElementVisible(link);
            DriverUtil.sleep(2000);
            link.click();
            DriverUtil.sleep(2000L);
        }
    }

    public void restoreMenu(){
        DriverUtil.sleep(2000L);
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        driver.switchTo().frame("afr::PushIframe");
        try {
            if(collapsePane.isDisplayed()) return;
            else{
                String xpath = "(//img[@title='Restore Pane'])[2]";
                WebElement restore = DriverUtil.getElement(By.xpath(xpath));
                PageLoadHelper.waitForElementVisible(restore);
                StepReport.info("Restore Menu");
                restore.click();
                DriverUtil.sleep(2000L);
            }
        }catch(Exception e) {
            String xpath = "(//img[@title='Restore Pane'])[2]";
            WebElement restore = DriverUtil.getElement(By.xpath(xpath));
            PageLoadHelper.waitForElementVisible(restore);
            StepReport.info("Restore Menu");
            restore.click();
            DriverUtil.sleep(2000L);
        }
        finally {
            driver.switchTo().defaultContent();
        }
    }

    public void clickDeployInWeblogicHomeMenu(){
        weblogicDomainHomeLink.click();
        DriverUtil.sleep(2000L);
        weblogicDomainLink.click();
        DriverUtil.sleep(2000L);
        weblogicDomainAppDepLink.click();
        DriverUtil.sleep(2000L);
        weblogicDomainAppDepDeployLink.click();
        DriverUtil.sleep(2000L);
    }

    public void clickSOAServerLink(){
        PageLoadHelper.waitForElementVisible(soaServer);
        soaServer.click();
        DriverUtil.sleep(5000L);
    }

    public void clickWeblogicServerLink(){
        PageLoadHelper.waitForElementVisible(weblogicServer);
        weblogicServer.click();
        DriverUtil.sleep(3000);
    }

    public void clickApplicationDeploymentLink(){
        PageLoadHelper.waitForElementVisible(applicationDeployment);
        applicationDeployment.click();
        DriverUtil.sleep(2000L);
    }

    public void clickApplicationDeploymentDeployLink(){
        PageLoadHelper.waitForElementVisible(applicationDeploymentDeploy);
        applicationDeploymentDeploy.click();
        DriverUtil.sleep(2000L);
    }

    public void clickSOAMenu(){
        DriverUtil.sleep(20000);
        PageLoadHelper.waitForElementClickable(soaMenu);
        StepReport.info("Click on SOA Menu");
        soaMenu.click();
        DriverUtil.sleep(2000);
    }

    public void clickSOAInfraMenu() {
        StepReport.info("Click on  SOA Infra Menu");
        soaInfraMenu.click();
        DriverUtil.sleep(2000);
    }

    public void clickSOADefaultPatititionMenu() {
        StepReport.info("Click on SOA default partition Menu");
        soaDefaultPatititionMenu.click();
        DriverUtil.sleep(2000);
    }

    public void clickComposite(String compositeName){
        StepReport.info("Click Composite",compositeName);
        String xpath = "//a[text()='"+compositeName+"']";
        WebElement link = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(link);
        link.click();
        DriverUtil.sleep(5000L);
    }

    public boolean testInvokeCompositeStatus(String compositeName){
        PageLoadHelper.waitForElementVisible(testExecStatus);
        //Verify if the invoke is successful.
        if (testExecStatus.getText().equalsIgnoreCase("Request successfully received."))
        {
            StepReport.info("Invoked the composite successfully: "+compositeName);
            return true;
        }
        else {
            StepReport.info("Failed to invoke the composite: " + compositeName);
            return false;
        }

    }


    public void clickLinkElement(String element){
        String xpath = "//a[.='"+element+"']";
        System.out.println("Xpath of the link is :"+xpath);
        WebElement webElement = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(webElement);
        webElement.click();
        StepReport.info("Clicked on the above Element");
        DriverUtil.sleep(2000L);
    }

    public void clickSOACompositeMenu() {
        PageLoadHelper.waitForElementVisible(soaCompositeMenu);
        soaCompositeMenu.click();
        DriverUtil.sleep(2000L);
    }


    public void clickSOACompositeMenuSoaDeploymentLink() {
        PageLoadHelper.waitForElementVisible(soaCompositeMenuSoaDeploymentLink);
        soaCompositeMenuSoaDeploymentLink.click();
        DriverUtil.sleep(2000L);
    }

    public void clickSOACompositeMenuSoaDeploymentUndeployLink() {
        PageLoadHelper.waitForElementVisible(soaCompositeMenuSoaDeploymentUndeployLink);
        soaCompositeMenuSoaDeploymentUndeployLink.click();
        DriverUtil.sleep(2000L);
    }

    public void clickSOAUnDeployButton() {
        PageLoadHelper.waitForElementVisible(unDeploy);
        unDeploy.click();
        DriverUtil.sleep(5000);
    }

    public void clickTest() {
        StepReport.info("Click Test");
        PageLoadHelper.waitForElementVisible(test);
        test.click();
        DriverUtil.sleep(5000);
    }

    public void inputPayload(String payloadText) {
        StepReport.info("Enter payload",payloadText);
        PageLoadHelper.waitForElementVisible(payload);
        payload.sendKeys(payloadText);
        DriverUtil.sleep(2000);
    }

    public void clickTestWebService() {
        StepReport.info("Click Test Web Service");
        PageLoadHelper.waitForElementVisible(testWebService);
        //Invoke Test Webservice.
        testWebService.click();
        DriverUtil.sleep(2000);
    }

    public void clickDefault(){
        StepReport.info("Click default");
        PageLoadHelper.waitForElementVisible(defaultLink);
        DriverUtil.sleep(2000L);
        defaultLink.click();
        DriverUtil.sleep(2000L);
    }

    public void clickDeployment(){
        StepReport.info("Click Deployment");
        PageLoadHelper.waitForElementVisible(deploymentMenu);
        DriverUtil.sleep(2000L);
        deploymentMenu.click();
        DriverUtil.sleep(2000L);
    }

    public void clickDeployToThisPartition(){
        StepReport.info("Click Deploy To This Partition");
        PageLoadHelper.waitForElementVisible(deployToThisPartitionMenu);
        DriverUtil.sleep(2000L);
        deployToThisPartitionMenu.click();
        DriverUtil.sleep(2000L);
    }

    public void enterJARorWARlocation(String location){
        StepReport.info("Enter Composite Name");
        DriverUtil.sleep(8000);
        browseArchive.sendKeys(location);
        DriverUtil.sleep(2000);
    }

    public void clickDeploymentNextButton() {
        StepReport.info("Click on next button on deployment page");
        deploymentNextButton.click();
        deploymentNextButton.isEnabled();
        DriverUtil.sleep(8000);
    }

    public boolean verifyDeployButton(){
        return isElementVisible("//*[@id='emTrainTemplate:deployConfig3']");
    }

    public String getErrorMsgIfSeen() {
        String msg;
        StepReport.info("Verify if there is any error and display the message in the error");

        //if(isElementVisible("//*[text()='Error']")){
        if(isElementVisible(errorMsg)){
            msg = msgDialogInfoOnPopup.getText();
            StepReport.info(msg);
            return msg;
        }
        else {
            StepReport.info("No error present");
            msg = "No Error";
            return msg;
        }
    }
    /**
     * Returns whether an element is visible
     *
     * @param xpath the locator to find the desired element
     * @return true if the element exists and is visible, false otherwise
     */
    public boolean isElementVisible(String xpath)
    {
        List<WebElement> elements = DriverUtil.getElements(By.xpath(xpath));
        if (elements.isEmpty())
        {
            // element doesn't exist
            StepReport.info("Element does not exist");
            return false;
        }
        else
        {
            // element exists, check for visibility
            return elements.get(0).isDisplayed();
        }
    }

    public boolean isElementVisible(WebElement element)
    {
        try {
            StepReport.info("----Start time----");
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Error, element not found ------------------"+e.getMessage());
            return false;
        }
    }


    public void clickDeployButton() {
        StepReport.info("Click on deploy button");
        deployButton.click();
        DriverUtil.sleep(8000);
    }

    public boolean verifyDeploymentConfirmation() {
        DriverUtil.sleep(2000);
        if(deploymentConfirmation.isDisplayed()) {
            StepReport.info("Deployment Successful");
            return true;
        }
        else
        {
            StepReport.info("Deployment failed.");
            return false;
        }
    }

    public void clickButton(String button){
        StepReport.info("Click Button",button);
        String xpath = "//button[text()='"+button+"']";
        WebElement buttonElement = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(buttonElement);
        buttonElement.click();
        DriverUtil.sleep(2000L);
    }

    public void selectSoaServerDeployCheckbox(){
        PageLoadHelper.waitForElementClickable(soaServerCheckboxDeploy);
        soaServerCheckboxDeploy.click();
        DriverUtil.sleep(8000L);
    }

    public boolean verifyEarDeployProgress(){
        if(isElementVisible(earDeployProgress)) {
            DriverUtil.sleep(5000L);
            String msg = earDeployProgress.getText();
            StepReport.info(msg);
            while(msg.contains("is now being deployed to target soa_server1")){
                DriverUtil.sleep(5000L);
                msg = earDeployProgress.getText();
                StepReport.info(msg);
            }
            if (msg.contains("has been successfully deployed")) {
                StepReport.info("The deployment is successful");
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    public void clickOKButtonOnMsgDlg(){
        PageLoadHelper.waitForElementClickable(okButtonMsgDlg);
        okButtonMsgDlg.click();
        DriverUtil.sleep(1000L);
    }

    public void clickCancelButton(){
        PageLoadHelper.waitForElementClickable(cancelButton);
        cancelButton.click();
        DriverUtil.sleep(1000L);
    }

    public void enterUsername(String ssousename) {
        StepReport.info("Enter SSO Username");
        PageLoadHelper.waitForElementVisible(userName);
        DriverUtil.sleep(1000L);
        userName.sendKeys(ssousename);
        userName.sendKeys(Keys.RETURN);
        DriverUtil.sleep(1000L);
    }

    public void enterPassword(String ssoPassword) {
        StepReport.info("Enter SSO Password");
        DriverUtil.sleep(1000L);
        password.sendKeys(ssoPassword);
        password.sendKeys(Keys.RETURN);
        DriverUtil.sleep(1000L);
    }

    public void confirmDeployment(){
        if ((PageLoadHelper.waitForElementClickable(deploymentConfirmation,300) == null)) {
            throw new TestErrorException("Composite not deployed in" +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        else
            StepReport.info("Deployment successful");
        DriverUtil.sleep(2000L);
    }

    /**
     *
     * @param value of either buttons, links
     *
     */
    public void findXpathsOfAllElements(String value)
    {
        switch (value) {
            case "buttons":
                List<WebElement> buttons = findElements(By.tagName("button"));
                for (int i = 0; i <= buttons.size(); i = i++)
                {
                    System.out.println(buttons.get(i).getText());
                }
                break;
            case "links":
                List<WebElement> links = findElements(By.tagName("a"));
                System.out.println(links.size());
                for (int i = 0; i <= links.size(); i = i++)
                {
                    System.out.println(links.get(i).getText());
                }
                break;
        }

    }

    public boolean confirmUnDeployment(String composite){
        if ((PageLoadHelper.waitForElementClickable(deploymentConfirmation,300) == null)) {
            StepReport.info("Undeployment of composite "+ composite +"is not successful");
            return false;
        }
        else {
            StepReport.info("Undeployment of composite " + composite + "is successful");
            return true;
        }
    }

    public void clickSOAComposite(){
        StepReport.info("Click SOA Composite");
        PageLoadHelper.waitForElementVisible(soaComposite);
        DriverUtil.sleep(2000L);
        soaComposite.click();
        DriverUtil.sleep(2000L);
    }

    public void clickExport(){
        StepReport.info("Click Export");
        PageLoadHelper.waitForElementVisible(export);
        DriverUtil.sleep(2000L);
        export.click();
        DriverUtil.sleep(2000L);
    }

    public void saveFile() {
        StepReport.info("Save File");
        DriverUtil.sleep(5000L);
        //Alert alert = driver.switchTo().alert();
        //alert.sendKeys("TAB");

        DriverUtil.sleep(50000L);
    }

    public void searchComposite(String composite){
        StepReport.info("Enter Composite Name");
        DriverUtil.sleep(2000);
        String[] compositeName = composite.split("\\s+");
        PageLoadHelper.waitForElementVisible(searchField);
        searchField.clear();
        DriverUtil.sleep(2000);
        searchField.sendKeys(compositeName[0]);
        DriverUtil.sleep(2000);
        searchIcon.click();
        DriverUtil.sleep(2000);
    }


    public void clickOnFarm_WLS_SOAWCLink() {
        PageLoadHelper.waitForElementClickable(farmSOA);
        StepReport.info("Found Farm_WLS_SOAWC element");
        //Click on Farm_WLS_SOAWC link in EM.
        farmSOA.click();
        DriverUtil.sleep(3000);
    }

    public void clickLink(String text){
        StepReport.info("Click Link",text);
        String xpath = "//a[text()='"+text+"']";
        WebElement link = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(link);
        DriverUtil.sleep(1000L);
        link.click();
        DriverUtil.sleep(2000L);
    }

    public void selectInstance(String user){
        StepReport.info("Select instance created by user:",user);
        String xpath = "(//span[text()='USERNAME="+user.toUpperCase()+"'])[1]";
        WebElement instance = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(instance);
        DriverUtil.sleep(1000L);
        instance.click();
        DriverUtil.sleep(2000L);
    }

    public void clickAbort(){
        StepReport.info("Click Abort");
        PageLoadHelper.waitForElementVisible(abort);
        DriverUtil.sleep(2000L);
        abort.click();
        DriverUtil.sleep(2000L);
    }

    public void clickLogs(){
        StepReport.info("Click Logs");
        PageLoadHelper.waitForElementClickable(logs);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(logs);
        DriverUtil.sleep(2000L);
    }

    public void clickViewLogMessages(){
        StepReport.info("Click View Log Messages");
        PageLoadHelper.waitForElementClickable(clusteredApplicationDeployment);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(viewLogMessages);
        DriverUtil.sleep(2000L);
    }

    public void enterMessage(String text) {
        StepReport.info("Enter in Message field",text);
        PageLoadHelper.waitForElementVisible(message);
        DriverUtil.sleep(1000L);
        message.sendKeys(text);
        DriverUtil.sleep(1000L);
    }

    public void clickSearch(){
        StepReport.info("Click Search");
        PageLoadHelper.waitForElementVisible(searchButton);
        DriverUtil.sleep(2000L);
        searchButton.click();
        DriverUtil.sleep(2000L);
    }

    public void isErrorDisplayed() {
        StepReport.info("Check if the Error is displayed");
        try {
            String xpath = "//*[text()='java.lang.StringIndexOutOfBoundsException: String index out of range: -1']";
            WebElement errorMsg = DriverUtil.getElement(By.xpath(xpath));
            if(errorMsg.isDisplayed()){
                throw new TestErrorException("Bug#26679675: Error displayed: java.lang.StringIndexOutOfBoundsException: String index out of range: -1");
            }
            else {
                StepReport.info("Error not displayed");
                return;
            }
        }catch(NoSuchElementException e) {
            e.printStackTrace();
            StepReport.info("Error not displayed");
            return;
        }
    }

    public void clickonSoaDomain(String domainName){
        StepReport.info("Click on Soa Domain",domainName);
        String xpathSoaDomain = "//span[contains(@title,'"+domainName+"')]";
        WebElement SoaDomain = DriverUtil.getElement(By.xpath(xpathSoaDomain));
        PageLoadHelper.waitForElementVisible(SoaDomain);
        DriverUtil.sleep(1000L);
        SoaDomain.click();
        DriverUtil.sleep(2000L);
    }

    public void clicklaunchFlowTrace(){
        StepReport.info("Click on Launch Flow Trace");
        PageLoadHelper.waitForElementVisible(launchFlowTrace);
        DriverUtil.sleep(1000L);
        launchFlowTrace.click();
        DriverUtil.sleep(5000L);
    }

    public void clickSOAInfraAdminServer(){
        StepReport.info("Click on soa-infra Admin Server");
        DriverUtil.sleep(3000L);
        DriverUtil.clickByJS(soainfraAdminServer);
        DriverUtil.sleep(5000L);
    }
    public void clickHumanWorkflow(){
        StepReport.info("Expand Service Engines");
        expandServiceEngines.click();
        DriverUtil.sleep(5000L);
        StepReport.info("Click on Human Work Flow");
        humanWorkflow.click();
        DriverUtil.sleep(2000L);
    }
    public void clickNotificationManagement(){
        StepReport.info("Click on NotificationManagement");
        DriverUtil.sleep(5000L);
        notificationManagement.click();
        DriverUtil.sleep(2000L);
    }

    public EMDailoguePage clickSendTestNotification(){
        StepReport.info("Click Send Test Notification Button");
        DriverUtil.sleep(2000L);
        clickSendTestNotification.click();
        DriverUtil.sleep(2000L);
        EMDailoguePage emDailoguePage= PageFactory.getPage(EMDailoguePage.class);
        emDailoguePage.isLoaded();
        return emDailoguePage;
    }

    public void clickSOAInfractureMenu(){
        StepReport.info("Click on Soa Infrastructure Menu");
        DriverUtil.sleep(2000);
        soaInfrastuctureMenu.click();
        DriverUtil.sleep(3000);
    }

    public void clickAdministration(){
        StepReport.info("Click on Soa Infrastructure Menu>Administration");
        DriverUtil.sleep(3000);
        PageLoadHelper.waitForElementVisible(Administration);
        Administration.click();
        DriverUtil.sleep(2000);
    }

    public void clickSystemMBeanBrowser(){
        StepReport.info("Click on Soa Infrastructure Menu>Administration>System MBean Browser");
        DriverUtil.sleep(2000);
        PageLoadHelper.waitForElementVisible(clickSystemMBeanBrowser);
        clickSystemMBeanBrowser.click();
        DriverUtil.sleep(2000);
    }

    public void clickSearchMBeanBrowser(){
        StepReport.info("Click Search on System MBean Browser");
        DriverUtil.sleep(2000);
        clickSearch.click();
        DriverUtil.sleep(2000);
    }

    public void inputMBeanBrowserSearch(String inputName){
        StepReport.info("Input MBeanBrowser",inputName);
        DriverUtil.sleep(2000);
        inputMBeanBrowserSearch.sendKeys(inputName);
        DriverUtil.sleep(2000);
    }

    public void clickSearchMBeanBrowserButton(){
        StepReport.info("Click Search on System MBean Browser Button");
        DriverUtil.sleep(2000);
        PageLoadHelper.waitForElementClickable(clickSearchMBeanButton);
        clickSearchMBeanButton.click();
        DriverUtil.sleep(2000);
    }

    public String getEmailFromAddress(){
        StepReport.info("Get Email From Address saved on System MBean Browser");
        DriverUtil.sleep(2000);
        String emailAlias=editASNSDriverEmailFromAddress.getAttribute("value");
        String[] strings = emailAlias.split(" <");
        emailAlias=strings[0];
        DriverUtil.sleep(2000);
        return emailAlias;
    }

    public void updateEmailFromAddress(String newAlias, String emailSender){
        StepReport.info("Get Email From Address saved on System MBean Browser");
        DriverUtil.sleep(2000);
        String emailAlias=editASNSDriverEmailFromAddress.getAttribute("value");
        String[] strings = emailAlias.split(" <");
        editASNSDriverEmailFromAddress.clear();
        editASNSDriverEmailFromAddress.sendKeys(newAlias+" <"+strings[1]);
        StepReport.info("Update Email Address: ", newAlias+" <"+strings[1]);
    }

    public void clickSenderEmailAddresslink(){
        StepReport.info("Click clickSenderEmailAddresslink");
        DriverUtil.sleep(2000);
        PageLoadHelper.waitForElementClickable(ASNSDriverEmailFromAddress);
        ASNSDriverEmailFromAddress.click();
        DriverUtil.sleep(2000);
    }

    public void clickApply(){
        StepReport.info("Click Apply");
        DriverUtil.sleep(2000);
        applyButton.click();
        DriverUtil.sleep(2000);
    }


}