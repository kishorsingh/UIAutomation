package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.crm.page.EditEnrollmentSummary;
import com.oracle.fa.qa.selenium.component.fin.page.ManageInvoiceOptions;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ImplementationProjects extends BasePageObject<ImplementationProjects>{

    @FindBy(xpath = "//*[text()='Name']/preceding::input[@type='text']")
    WebElement searchField;

    @FindBy(xpath = "//button[text()='Search']")
    WebElement searchButton;

    @FindBy(xpath = "//*[text()='Task']/following::*[@title='Search'][1]")
    WebElement seachIcon;

    @FindBy(xpath = "//*[text()='Task']/following::input[@type='text']")
    WebElement taskInput;

    @FindBy(xpath = "//*[text()='Manage Invoice Options']/following::a[@title='Go to Task'][1]")
    WebElement gotToTask;

    @FindBy(xpath = "(//a[text()='Vision Operations '])[1]")
    WebElement visionOperations;

    @FindBy(xpath = "(//*[text()='Manage Invoice Options']/following::a)[2]")
    WebElement scope;

    @FindBy(xpath = "//*[text()='Business Unit']/following::select")
    WebElement businessUnit;

    @FindBy(xpath = "(//button[text()='Apply and Go to Task'])[1]")
    WebElement applyAndGoToTask;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(searchField) == null) {
            throw new TestErrorException("The search field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void typeTask(String taskName) {
        StepReport.info("Type Task");
        DriverUtil.sleep(3000L);
        searchField.sendKeys(taskName);
        DriverUtil.sleep(3000L);
        searchField.sendKeys(Keys.RETURN);
        DriverUtil.sleep(3000L);
        searchField.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

    }

    public void clickSearchButton() {
        StepReport.info("Click Search Button");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(searchButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(searchButton);
        DriverUtil.sleep(2000L);

    }

    public void clickSearchResults(String Name) {
        StepReport.info("Click Search Results");
        DriverUtil.sleep(2000L);
        String xpath="//*[text()='"+Name+"']";
        WebElement taskNameElem=DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(taskNameElem);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(taskNameElem);
        DriverUtil.sleep(2000L);

    }

    public void searchAndClickTask(String taskName) {
        StepReport.info("Search Task");
        DriverUtil.sleep(2000L);
        taskInput.sendKeys(taskName);
        DriverUtil.sleep(2000L);
        JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", seachIcon);
        DriverUtil.sleep(5000L);
        String xpath="//*[text()='"+taskName+"']";
        WebElement taskElem=DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(taskElem);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(taskElem);
        DriverUtil.sleep(2000L);

    }

    public ManageInvoiceOptions clickGoToTask() {
        if(!SelUtil.isDisplayed(visionOperations,20)){
            scope.click();
            DriverUtil.sleep(2000L);
            StepReport.info("Select Scope","Vision Operations");
            PageLoadHelper.waitForElementVisible(businessUnit);
            DriverUtil.sleep(5000L);
            Select typeElement = new Select(businessUnit);
            typeElement.selectByVisibleText("Vision Operations");
            DriverUtil.sleep(5000L);
            DriverUtil.clickByJS(applyAndGoToTask);

        }else{
            StepReport.info("Click Go To Task");
            DriverUtil.sleep(3000L);
            PageLoadHelper.waitForElementClickable(gotToTask);
            DriverUtil.sleep(2000L);
            DriverUtil.clickByJS(gotToTask);
            DriverUtil.sleep(5000L);
        }

        ManageInvoiceOptions manageInvoiceOptions = PageFactory.getPage(ManageInvoiceOptions.class);
        manageInvoiceOptions.isLoaded();
        return manageInvoiceOptions;
    }
}
