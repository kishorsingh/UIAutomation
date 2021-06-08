package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.common.page.ReassignTask;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ManageSandBoxes extends BasePageObject<ManageSandBoxes> {

    @FindBy(css = "img[title='New']")
    WebElement newButton;

    @FindBy(xpath = "//button[text()='Set as Active']")
    WebElement setAsActive;

    @FindBy(xpath = "//button[text()='Publish']")
    WebElement publish;

    @FindBy(xpath = "//button[text()='Close']")
    WebElement close;

    @FindBy(xpath = "//*[contains(text(),'_Description')]")
    WebElement description;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(newButton) == null) {
            throw new TestErrorException("The New button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public CreateSandBox clickNewButton() {
        StepReport.info("Click New Button");
        PageLoadHelper.waitForElementClickable(newButton);
        DriverUtil.sleep(2000L);
        newButton.click();
        CreateSandBox createSandBox = PageFactory.getPage(CreateSandBox.class);
        createSandBox.isLoaded();
        return createSandBox;
    }

    public void clickCloseButton() {
        StepReport.info("Click Close Button");
        PageLoadHelper.waitForElementClickable(close);
        DriverUtil.sleep(2000L);
        close.click();
        DriverUtil.sleep(2000L);
    }

    public void setAsActive(String user, String sbName) {
        StepReport.info("Select Sandbox Name");
        //String xPath="//*[contains(text(),'"+sbName+"')]/following::*[@title='"+user+"'][1]";
        //String xPath="//*[contains(@title,'"+sbName+"')]";
        String xPath="//*[contains(text(),'"+sbName+"_Description"+"')]";
        WebElement reportLine=DriverUtil.getElement(By.xpath(xPath));
        DriverUtil.sleep(3000L);
        DriverUtil.clickByAction(reportLine,true);
        DriverUtil.sleep(10000L);
        StepReport.info("Click on Set as Active");
        PageLoadHelper.waitForElementClickable(setAsActive);
        DriverUtil.sleep(2000L);
        setAsActive.click();
        DriverUtil.sleep(10000L);
    }

    public PublishSandBoxConfirmation publishSandbox(String sbName) {
        StepReport.info("Select Sandbox Name to Publish");
        //String xPath="//*[contains(text(),'"+sbName+"')]/following::*[@title='"+user+"'][1]";
        //String xPath="//*[contains(@title,'"+sbName+"')]";
        String xPath="//*[contains(text(),'"+"_Description"+"')]";
        WebElement reportLine=DriverUtil.getElement(By.xpath(xPath));
        DriverUtil.sleep(3000L);
        DriverUtil.clickByAction(reportLine,true);
        DriverUtil.sleep(10000L);
        StepReport.info("Click on Publish");
        PageLoadHelper.waitForElementClickable(publish);
        DriverUtil.sleep(2000L);
        publish.click();
        DriverUtil.sleep(5000L);
        PublishSandBoxConfirmation publishSandBoxConfirmation = PageFactory.getPage(PublishSandBoxConfirmation.class);
        publishSandBoxConfirmation.isLoaded();
        return publishSandBoxConfirmation;
    }

    public boolean checkIfSandBoxExists(String user, String sbName) {
        Boolean sandBoxExists=false;
        StepReport.info("Select Sandbox Name to check if exists");
        DriverUtil.sleep(3000L);
        try{
            sandBoxExists=description.isDisplayed();
            StepReport.info("Sandbox Exists already ?: "+sandBoxExists);
            return sandBoxExists;
        }catch (Exception e){
           StepReport.info("Exception:"+e.getMessage());
           StepReport.info("Sandbox Exists already ?: "+sandBoxExists);
           return sandBoxExists;
        }
    }

}
