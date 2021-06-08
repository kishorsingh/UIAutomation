package com.oracle.fa.qa.selenium.component.crm.page;

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

import java.util.Random;

public class CreateProgram extends BasePageObject<CreateProgram> {

    @FindBy(xpath ="//*[text()='S']")
    WebElement saveAndClose;

    @FindBy(xpath ="//*[contains(@id,'it1::content')]")
    WebElement programName;

    @FindBy(xpath ="//*[text()='Level']")
    WebElement typeDropdown;

    @FindBy(xpath ="//*[contains(@id,'it2::content')]")
    WebElement description;

    @FindBy(xpath = "//input[contains(@id,'id1::content')]")
    WebElement startDate;

    @FindBy(xpath = "//input[contains(@id,'id2::content')]")
    WebElement typeEndDate;


    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(saveAndClose) == null) {
            throw new TestErrorException("The createPartner field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Create Programs Page is Loaded");
    }

    public String typeProgramName() {
        StepReport.info("Enter Program Name");
        DriverUtil.sleep(3000L);
        int num = 10000;
        Random r = new Random();
        num = num + r.nextInt(10000);
        String pName = "ARTest8";
        pName = pName+String.valueOf(num);
        programName.sendKeys(pName);
        DriverUtil.sleep(3000L);
        programName.sendKeys(Keys.RETURN);
        DriverUtil.sleep(3000L);
        programName.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
        return pName;
    }

    public void selectType(String type) {
        StepReport.info("Select Program Type",type);
        DriverUtil.sleep(2000L);
        /*Select statusSelect = new Select(typeDropdown);
        statusSelect.selectByVisibleText(type);
        DriverUtil.sleep(2000L);
        typeDropdown.sendKeys(Keys.RETURN);*/
        String xpath = "//li[text()='Level']";
        WebElement level = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(level);
        DriverUtil.sleep(2000L);

    }

    public void typeDescription(String desc) {
        StepReport.info("Enter Description");
        DriverUtil.sleep(3000L);
        description.sendKeys(desc);
        DriverUtil.sleep(3000L);
        description.sendKeys(Keys.RETURN);
        DriverUtil.sleep(3000L);
        description.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

    }

    public void typeStartDate(String ecStartDate) {
        StepReport.info("Type StartDate");
        DriverUtil.sleep(2000L);
        if(ecStartDate.equalsIgnoreCase("SysDate"))
        {
            ecStartDate= SelUtil.getSystemDateTime();
            StepReport.info("System Date Time " + ecStartDate);
        }
        startDate.sendKeys(ecStartDate);
        DriverUtil.sleep(2000L);
        startDate.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);

    }
    public void typeEndDate(String ecEndDate) {
        StepReport.info("Enter End Date  ");
        DriverUtil.sleep(2000L);
        typeEndDate.sendKeys(ecEndDate);
        DriverUtil.sleep(2000L);
        typeEndDate.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);

    }

    public ProgramsPage clickSaveAndClose() {
        StepReport.info("Click Save and Close");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(saveAndClose);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(saveAndClose);
        DriverUtil.sleep(2000L);
        ProgramsPage programsPage = PageFactory.getPage(ProgramsPage.class);
        programsPage.isLoaded();
        return programsPage;

    }
}
