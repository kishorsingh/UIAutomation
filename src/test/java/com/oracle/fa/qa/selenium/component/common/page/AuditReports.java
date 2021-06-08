package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.crm.page.EditContract;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AuditReports extends BasePageObject<AuditReports> {

    @FindBy(xpath="//*[text()='Schedule New Process']/parent::a")
    WebElement scheduleNewProcess;

    @FindBy(xpath="(//*[text()='Date']/following::input)[1]")
    WebElement date;

    @FindBy(xpath="//*[text()='Product']/following::select[1]")
    WebElement product;

    @FindBy(xpath="(//*[text()='Event Type']/following::a)[1]")
    WebElement eventType;

    @FindBy(xpath="//button[text()='Search']")
    WebElement searchButton;

    @Override
    protected void isLoaded(){
        if(PageLoadHelper.waitForElementClickable(searchButton)==null){
            throw new TestErrorException("The Audit Reports page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void typeStartDate() {
        StepReport.info("Enter Date");
        DriverUtil.sleep(2000L);
        date.sendKeys(SelUtil.getSystemDateTime());
        DriverUtil.sleep(2000L);
        date.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }

    public void selectProduct(String productName) {
        StepReport.info("Select Product Name", productName);
        PageLoadHelper.waitForElementVisible(product);
        Select productElement = new Select(product);
        productElement.selectByVisibleText(productName);
        DriverUtil.sleep(2000L);
    }

    public void selectEventType(String event) {
        StepReport.info("Select Event Type",event);
        DriverUtil.sleep(2000L);
        eventType.click();
        String xpath = "//*[text()='"+event+"']/parent::*/label/input";
        WebElement eventCheckBox = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(eventCheckBox);
        eventCheckBox.click();
        DriverUtil.sleep(2000L);
    }

    public void clickSearchButton() {
        StepReport.info("Click Search Button");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(searchButton);
        DriverUtil.sleep(2000L);
    }

}
