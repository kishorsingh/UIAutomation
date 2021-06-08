package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.fa.qa.selenium.component.common.page.Attachment;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PersonManagementPage extends HCMBasePageObject {
    @FindBy(xpath = "//*[text()='Person Management: Search']")
    WebElement personManagement;

    @FindBy(xpath = "(//*[text()='Name']/following::input)[1]")
    WebElement name;

    @FindBy(xpath = "//button[text()='Search']")
    WebElement searchButton;



    @FindBy(xpath = "//label[text()='Person Number']/following::td[1]")
    WebElement personNum;

    @FindBy(xpath = "//button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath = "//button[@accesskey='K']")
    WebElement confirmButton;


    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(personManagement) == null) {

            throw new TestErrorException("The Person Management page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void enterName(String empName) {
        StepReport.info("Enter Name");
        DriverUtil.sleep(2000L);
        name.sendKeys(empName);
        DriverUtil.sleep(1000L);
        name.sendKeys(Keys.TAB);
        DriverUtil.sleep(1000L);
    }

    public void clickSearch() {
        StepReport.info("Click Search");
        DriverUtil.sleep(2000L);
        searchButton.click();
        DriverUtil.sleep(2000L);
    }

    public ManageEmploymentPage clickEmployee(String employeeName) {
        StepReport.info("Click Employee",employeeName);
        String xpath = "//*[text()='"+employeeName+"']";
        WebElement employee = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(employee);
        employee.click();
        DriverUtil.sleep(2000L);
        ManageEmploymentPage manageEmployment = PageFactory.getPage(ManageEmploymentPage.class);
        manageEmployment.isLoaded();
        return manageEmployment;
    }


}