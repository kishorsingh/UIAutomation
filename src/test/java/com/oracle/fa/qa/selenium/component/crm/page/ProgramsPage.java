package com.oracle.fa.qa.selenium.component.crm.page;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProgramsPage extends BasePageObject<ProgramsPage> {

    @FindBy(xpath ="//*[contains(@id,'srchit::content')]")
    WebElement searchProgram;

    @FindBy(xpath = "//*[@title='Find']")
    WebElement findButton;

    @FindBy(xpath ="//*[text()='Create Program']")
    WebElement createProgramButton;

    //@FindBy(xpath = "//*[@title='PRM Test Program 9']")
    //@FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSrZPM_PROGRAMS_CARD:0:_FOTr0:0:pt1:lstSrch:atlspc:t1:0:cl1']")
   // @FindBy(xpath ="//*[contains(text(),'PRM Test Program 9')]")
   // WebElement searchResult;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(searchProgram) == null) {
            throw new TestErrorException("The createPartner field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Programs Page is Loaded");
    }

    public EditProgram searchProgram(String crmProgramName) {
        StepReport.info("Enter program name");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(searchProgram,10);
        searchProgram.clear();
        DriverUtil.sleep(2000L);
        searchProgram.sendKeys(crmProgramName);
        DriverUtil.sleep(2000L);
        StepReport.info("Click find button");
        findButton.click();
        DriverUtil.sleep(10000L);
        String xpath="//a[contains(text(),'" + crmProgramName + "')]";
        WebElement searchResult=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", searchResult);
        DriverUtil.sleep(2000L);
        StepReport.info("Opening Edit Program");
        EditProgram editProgram = PageFactory.getPage(EditProgram.class);
        editProgram.isLoaded();
        return editProgram;
    }

    public CreateProgram clickCreateProgram() {
        StepReport.info("Click Create Program Button");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(createProgramButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(createProgramButton);
        DriverUtil.sleep(2000L);
        CreateProgram createProgram = PageFactory.getPage(CreateProgram.class);
        createProgram.isLoaded();
        return createProgram;
    }
}
