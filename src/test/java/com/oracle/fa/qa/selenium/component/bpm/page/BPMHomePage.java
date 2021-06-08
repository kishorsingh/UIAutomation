package com.oracle.fa.qa.selenium.component.bpm.page;

import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.testng.Assert;

/**
 * This class contains configuration and UI action related to Home Page in Worklist App
 *
 *
 * @author ashwaraj
 * @version 1.0
 */
public class BPMHomePage extends BasePageObject<BPMHomePage> {

    @FindBy(xpath = "//*[@id='homePageTemplate:usrD1']")
    WebElement LogOutdropdown;

    @FindBy(xpath = "//*[text()='Logout']")
    WebElement Logout;

    @FindBy(id = "//button[.='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id='homePageTemplate:usrD1']")
    WebElement userMenu;


    @FindBy(xpath = "//*[text()='Administration']")
    WebElement administration;

    //@FindBy(xpath = "//*[@id=homePageTemplate:adminTabPanel:taskEdiorTab']")
    @FindBy(xpath = "//*[text()='Task Configuration']")
    WebElement taskConfiguration;

    //@FindBy(xpath = "//*[@id='homePageTemplate:r1:0:tskTree:0:pgl6']")
    @FindBy(xpath = "//*[text()='FA_SOAParallelTask_HT']")
    WebElement simpleApproval;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:editTaskButton::icon']")
    WebElement editComposite;

    @FindBy(xpath = "//*[text()='Assignees']")
    WebElement assignee;

    // @FindBy(xpath = "//*[text()='Stage1.Partici...']")
    //WebElement participant;

    @FindBy(xpath = "//*[contains(text(),'Stage1.Partici')]")
    WebElement participant;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:lbType::content']")
    WebElement assigneeDropDown;

    @FindBy(xpath = "//*[text()='Names and expressions'")
    WebElement assigneeDropDownOption;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:dc2:addAsgn::icon'")
    WebElement addAssignee;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:dc2:nmeExTl:0:idbrwsr::icon'")
    WebElement searchUser;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:dc2:nmeExTl:0:idbrwsr::icon'")
    WebElement enterUsername;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:hierarchyIdb:idSearchButton'")
    WebElement search;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:hierarchyIdb:dc_pc1:idSTable::sm'")
    WebElement selectUser;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:hierarchyIdentityBrowserDlg::ok'")
    WebElement okButton;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:dc2:nmeExTl:0:resourceUserField'")
    WebElement userNameField;

    @FindBy(xpath = "//*[@id='homePageTemplate:r1:0:r1:1:dc02:r1:participant:bscDetails:dc2:nmeExTl:0:resourceUserField::content'")
    WebElement userInputField;




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
        if (PageLoadHelper.waitForElementClickable(userMenu) == null) {
            throw new TestErrorException("The tools Menu is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
    /**
     * method to perform signout action in Worklist APP
     *
     *
     */
    public void signout() {
        StepReport.info("Click on user drop down:");
        LogOutdropdown.click();
        DriverUtil.sleep(2000);
        StepReport.info("Click Logout");
        Logout.click();
        DriverUtil.sleep(2000);
    }

    /**
     * method to perform action in DTRT Worklist APP
     *
     *
     */
    public void goToDTRT() {
        //PageLoadHelper.waitForJetPageReady(driver);
        StepReport.info("Click on user manu");
        LogOutdropdown.click();
        DriverUtil.sleep(2000);
        StepReport.info("Click on Adminstation");
        administration.click();
        DriverUtil.sleep(4000);
        StepReport.info("Click on Task Configuration");
        taskConfiguration.click();
        DriverUtil.sleep(2000);
        //clickOnTaskConfigurationTab();
    }

    public void goToAssignee() {
        //PageLoadHelper.waitForJetPageReady(driver);
        StepReport.info("Click on Simple approval");
        //simpleApproval.click();
        simpleApproval.click();
        DriverUtil.sleep(8000);
        StepReport.info("Click on Edit Composite");
        boolean flag = editComposite.isDisplayed();
        StepReport.info("Flag"+flag);
        editComposite.click();
        //editComposite.submit();
        DriverUtil.sleep(4000);
        StepReport.info("Click on Assignee");
        assignee.click();
        DriverUtil.sleep(2000);
        StepReport.info("Click on participant");
        participant.click();
        //participant.sendKeys(Keys.ENTER);
        DriverUtil.sleep(2000);
        StepReport.info("Click on asignee dropdown");
        assigneeDropDown.click();
        DriverUtil.sleep(2000);
        StepReport.info("select option");
        assigneeDropDownOption.click();
        DriverUtil.sleep(2000);
        StepReport.info("Add asignee");
        addAssignee.click();
        DriverUtil.sleep(2000);
        StepReport.info("Search user");
        searchUser.click();
        DriverUtil.sleep(2000);
        StepReport.info("Enter username");
        enterUsername.sendKeys("James");
        DriverUtil.sleep(2000);
        StepReport.info("Clcik on search");
        search.click();
        DriverUtil.sleep(2000);
        StepReport.info("select user");
        selectUser.click();
        DriverUtil.sleep(2000);
        StepReport.info("click on user");
        okButton.click();
        String user = userInputField.getText();
        Assert.assertEquals(user, "James");
    }


}
