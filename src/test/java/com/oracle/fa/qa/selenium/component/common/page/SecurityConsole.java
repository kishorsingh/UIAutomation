package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SecurityConsole extends BasePageObject<SecurityConsole> {

    @FindBy(xpath="(//*[text()='Warning']/following::button[text()='OK'])[1]")
    WebElement warningOK;

    @FindBy(xpath="//*[text()='Users']/parent::a")
    WebElement users;

    @FindBy(xpath="//input[@placeholder='Enter 3 or more characters to search']")
    WebElement searchInput;

    @FindBy(xpath="//*[text()='Search']/following::a[@title='Search']")
    WebElement searchButton;

    @FindBy(xpath="//button[text()='Add User Account']")
    WebElement addUserAccount;

    @FindBy(xpath="//button[@accesskey='S']")
    WebElement saveAndClose;


    @Override
    protected void isLoaded(){
        if(PageLoadHelper.waitForElementClickable(users)==null){
            throw new TestErrorException("The Security Console page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void acceptWarning() {
        boolean isWarningPopupNotExists=true;
        int counter=1;
        while((isWarningPopupNotExists) && (counter<2)) {
            try {
                if(warningOK.isDisplayed()) {
                    StepReport.info("Warning displayed");
                    DriverUtil.clickByJS(warningOK);
                    DriverUtil.sleep(1000L);
                    isWarningPopupNotExists=false;
                }else {
                    System.out.println("Warning Popup not Displayed");
                }
            }catch(Exception e) {
                System.out.println("Warning Popup not Displayed");
            }
            DriverUtil.sleep(1000L);
            counter++;
        }
    }

    public void clickUsers() {
        StepReport.info("Click Users");
        PageLoadHelper.waitForElementVisible(users);
        DriverUtil.sleep(2000L);
        users.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(addUserAccount);
    }

    private void searchUser(String userName) {
        StepReport.info("Search User", userName);
        PageLoadHelper.waitForElementVisible(searchInput);
        searchInput.sendKeys(userName);
        DriverUtil.sleep(1000L);
        searchButton.click();
        DriverUtil.sleep(2000L);
    }

    public boolean isUserCreated(String userName) {
        searchUser(userName);
        try {
            WebElement rule1 = DriverUtil.getElement(By.xpath("//a[text()='"+userName+"']"));

            if(rule1.isDisplayed()) {
                StepReport.info("User is already created", userName);
            }}
        catch(Exception e) {
            System.out.println("User is not created");
            return false;
        }
        return true;
    }

    public void clickAddUserAccount() {
        StepReport.info("Click Add User Account");
        PageLoadHelper.waitForElementVisible(addUserAccount);
        DriverUtil.sleep(1000L);
        addUserAccount.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(saveAndClose);
    }

    public void enterValue(String fieldName, String value) {
        StepReport.info("Enter "+fieldName+"",value);
        String xpath = "(//*[text()='"+fieldName+"']/following::input)[1]";
        WebElement field = DriverUtil.getElement(By.xpath(xpath));
        field.clear();
        DriverUtil.sleep(1000L);
        field.sendKeys(value);
        DriverUtil.sleep(1000L);
        field.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void clickSaveAndClose() {
        StepReport.info("Click Save and Close");
        PageLoadHelper.waitForElementVisible(saveAndClose);
        DriverUtil.sleep(1000L);
        saveAndClose.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(addUserAccount);
    }


}
