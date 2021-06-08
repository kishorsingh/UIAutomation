package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReassignTask extends BasePageObject<ReassignTask>{

    @FindBy(xpath = "//*[text()='Delegate (allow specified user to act on my behalf)']/preceding::input[1]")
    WebElement delegateChoice;

    @FindBy(xpath = "//*[text()='Reassign (transfer task to another user or group)']/preceding::input[1]")
    WebElement reassignChoice;

    @FindBy(xpath = "//button[@accesskey='K'] | (//button[text()='OK'])[2]")
    WebElement okButton;

    @FindBy(xpath = "//button[text()='Search']")
    WebElement searchButton;

    @FindBy(xpath = ".//h1[text()='Reassign']")
    WebElement reAssignPage;

    @FindBy(xpath = "//*[text()='Users']/following::input[1]")
    WebElement userInput;

    @FindBy(xpath = "//*[text()='Name']/following::input[1]")
    WebElement inputUserName;

    @FindBy(xpath = "//*[text()='Name']/following::textarea[1]")
    WebElement addCommentstextArea;

    @FindBy(xpath = "//*[text()='Submit']")
    WebElement clickSubmit;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(addCommentstextArea) == null) {
            throw new TestErrorException("The Ok button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void delegateUser(String user) {
        StepReport.info("Click delegate");
        DriverUtil.sleep(2000L);
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", delegateChoice);
        StepReport.info("Enter User to search");
        DriverUtil.sleep(5000L);
        userInput.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
        userInput.sendKeys(user);
        StepReport.info("Click Search");
        DriverUtil.sleep(1000L);
        searchButton.click();
        DriverUtil.sleep(10000L);
        StepReport.info("Select User");
        int counter=1;
        boolean isUserNotFound=true;
        while((counter<10) && (isUserNotFound)) {
            try {
                String xpath="//*[text()='"+user+"']/preceding::input[1]";
                DriverUtil.waitForElementPresent(By.xpath(xpath),60);
                WebElement userChoice=DriverUtil.getElement(By.xpath(xpath));
                executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
                executor.executeScript("arguments[0].scrollIntoView(true);", userChoice);
                if(userChoice.isDisplayed()) {
                    isUserNotFound=false;
                }
                userChoice.click();
            }catch(Exception e) {
            }
            DriverUtil.sleep(3000L);
            counter++;
        }
        StepReport.info("Click Ok");
        okButton.click();
        DriverUtil.sleep(10000L);
    }
    public void inputUserName(String user){
        StepReport.info("ReAssingUser: "+user);
        DriverUtil.sleep(2000L);
        inputUserName.sendKeys(user);
        DriverUtil.sleep(2000L);
    }

    public void addComments(String comments){
        StepReport.info("Comments: "+comments);
        DriverUtil.sleep(2000L);
        addCommentstextArea.sendKeys(comments);
        DriverUtil.sleep(2000L);
    }

    public void clickSubmit(){
        DriverUtil.sleep(2000L);
        clickSubmit.click();
        DriverUtil.sleep(5000L);
        SelUtil.waitUntilPageClosed();
    }

    public void reassignUser(String user) {
        StepReport.info("Click Reassign");
        DriverUtil.sleep(2000L);
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", reassignChoice);
        StepReport.info("Enter User to search");
        DriverUtil.sleep(5000L);
        userInput.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
        userInput.sendKeys(user);
        StepReport.info("Click Search");
        DriverUtil.sleep(1000L);
        searchButton.click();
        DriverUtil.sleep(10000L);
        StepReport.info("Select User");
        int counter=1;
        boolean isUserNotFound=true;
        while((counter<10) && (isUserNotFound)) {
            try {
                String xpath="//*[text()='"+user+"']/preceding::input[1]";
                DriverUtil.waitForElementPresent(By.xpath(xpath),60);
                WebElement userChoice=DriverUtil.getElement(By.xpath(xpath));
                executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
                executor.executeScript("arguments[0].scrollIntoView(true);", userChoice);
                if(userChoice.isDisplayed()) {
                    isUserNotFound=false;
                }
                userChoice.click();
            }catch(Exception e) {
            }
            DriverUtil.sleep(3000L);
            counter++;
        }
        StepReport.info("Click Ok");
        okButton.click();
        DriverUtil.sleep(10000L);
    }

}

