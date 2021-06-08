package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.fa.qa.selenium.component.common.page.Attachment;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;

public class ManageEmploymentPage extends HCMBasePageObject {
    @FindBy(xpath = "//h1[text()='Manage Employment']")
    WebElement manageEmployment;

    @FindBy(xpath = "//*[text()='Correct']")
    WebElement correct;

    @FindBy(xpath = "(//*[text()='Correct Employment']/following::button[text()='OK'])[1]")
    WebElement okButton;

    @FindBy(xpath = "//*[text()='Edit Employment: Hire']")
    WebElement editEmployment;

    @FindBy(xpath = "//*[contains(@id,'insert::icon')]")
    WebElement insertIcon;

    @FindBy(xpath = "(//*[text()='Name']/following::input)[1]")
    WebElement managerName;

    @FindBy(xpath = "(//*[text()='Name']/following::input/following::*[text()='Type']/following::select)[1]")
    WebElement managerType;

    @FindBy(xpath = "//*[text()='Review']")
    WebElement reviewButton;

    @FindBy(xpath = "//*[text()='Edit Employment: Review']")
    WebElement reviewPage;

    @FindBy(xpath = "//*[@accesskey='m']")
    WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(),'The request will be submitted. Do you want to continue?')]/following::button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath = "//*[text()='Confirmation']/following::button[@accesskey='K']")
    WebElement confirmationOKButton;

    @FindBy(xpath = "//*[text()='Yarmouth, Bruce']")
    WebElement byarmouth;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(manageEmployment) == null) {

            throw new TestErrorException("The PManage Employment page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public boolean isManagerExists(String managerName) {
        StepReport.info("Check if manager exists already",managerName);
        DriverUtil.sleep(2000L);
        if(SelUtil.isDisplayed(byarmouth,10)) return true;
        else return false;
    }

    public void clickCorrect() {
        StepReport.info("Click Correct");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(correct);
        DriverUtil.sleep(2000L);
    }

    public void clickOK() {
        StepReport.info("Click OK button in Correct Employment pop-up");
        PageLoadHelper.waitForElementVisible(okButton);
        DriverUtil.sleep(1000L);
        okButton.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(editEmployment);
    }

    public void clickAddManager() {
        StepReport.info("Click Add Manager");
        PageLoadHelper.waitForElementVisible(insertIcon);
        DriverUtil.sleep(2000L);
        insertIcon.click();
        DriverUtil.sleep(5000L);
    }

    public void enterName(String name) {
        StepReport.info("Enter Manager Name",name);
        DriverUtil.sleep(2000L);
        managerName.sendKeys(name);
        DriverUtil.sleep(1000L);
        managerName.sendKeys(Keys.TAB);
        DriverUtil.sleep(1000L);
    }

    public void selectManagerType(String type) {
        StepReport.info("Select Manager Type",type);
        DriverUtil.sleep(3000L);

        //Select typeElement = new Select(managerType);
        //typeElement.selectByVisibleText(type);

        String xpathSelect = "//label[text()='Type']//following::input[1]";
        WebElement elemSelect = DriverUtil.getElement(By.xpath(xpathSelect));
        DriverUtil.clickByJS(elemSelect);
        DriverUtil.sleep(3000L);

        String xpathOption = "//*[text()='"+type+"']";
        WebElement elemOption = DriverUtil.getElement(By.xpath(xpathOption));
        DriverUtil.clickByJS(elemOption);
        DriverUtil.sleep(3000L);
    }

    public void clickReview() {
        StepReport.info("Click Review");
        PageLoadHelper.waitForElementVisible(reviewButton);
        DriverUtil.sleep(2000L);
        reviewButton.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(reviewPage);
    }

    public void clickSubmit() {
        StepReport.info("Click Submit");
        PageLoadHelper.waitForElementVisible(submitButton);
        DriverUtil.sleep(2000L);
        submitButton.click();
        DriverUtil.sleep(2000L);
    }

    public void clickYesInWarningPopUp() {
        StepReport.info("Click Yes Button in the warning pop-up");
        PageLoadHelper.waitForElementVisible(yesButton);
        DriverUtil.sleep(2000L);
        yesButton.click();
        DriverUtil.sleep(2000L);
    }

    public void clickOKInConfirmation() {
        StepReport.info("Click OK Button in the confirmation pop-up");
        PageLoadHelper.waitForElementVisible(confirmationOKButton);
        DriverUtil.sleep(2000L);
        confirmationOKButton.click();
        DriverUtil.sleep(2000L);
    }

    public void clickRemoveManager(String managerID) {
        StepReport.info("Click Remove button for managerID: ",managerID);
        String xpath = "//*[text()='"+managerID+"']/following::*[contains(@id,'RemoveImage::icon')]";
        WebElement removeElem = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(removeElem);
        DriverUtil.sleep(2000L);
        removeElem.click();
        DriverUtil.sleep(5000L);
    }


}