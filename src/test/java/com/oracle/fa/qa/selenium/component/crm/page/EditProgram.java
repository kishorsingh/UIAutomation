package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.common.page.AdhocRoute;
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

public class EditProgram extends BasePageObject<EditProgram> {

    //@FindBy(xpath ="//*[text()='Enrollments']")
    @FindBy(xpath ="//*[@id='_FOpt1:_FOr1:0:_FOSrZPM_PROGRAMS_CARD:0:_FOTr0:0:pt1:r1:0:pt1:ENROLLMENTS::text']")
    WebElement enrollments;

    @FindBy(xpath ="//*[text()='Create Enrollment']")
    WebElement createEnrollment;

    @FindBy(xpath ="//*[text()='S']")
    WebElement saveAndClose;

    @FindBy(xpath = "//*[contains(@id,'object-subtitle:ctb1')]")
    WebElement actions;

    @FindBy(xpath ="//*[contains(@id,'soc3::content')]")
    WebElement programStatus;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(enrollments) == null) {
            throw new TestErrorException("The Enrollment link is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Edit Programs Page is Loaded");
    }

    public void clickEnrollments() {
        StepReport.info("Click Enrollment Link");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(enrollments);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(enrollments);
        DriverUtil.sleep(2000L);

    }

    public CreateEnrollment clickCreateEnrollments() {
        StepReport.info("Click Create Enrollment Button");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(createEnrollment);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(createEnrollment);
        DriverUtil.sleep(2000L);
        CreateEnrollment createEnrollment = PageFactory.getPage(CreateEnrollment.class);
        createEnrollment.isLoaded();
        return createEnrollment;

    }

        public ProgramsPage clickSaveAndClose() {
        StepReport.info("Click Save And Close Button");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(saveAndClose);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(saveAndClose);
        DriverUtil.sleep(2000L);
        ProgramsPage programs = PageFactory.getPage(ProgramsPage.class);
        programs.isLoaded();
        return programs;
    }

    public void clickActions() {
        StepReport.info("Click Actions");
        /*actions.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", actions);*/
        PageLoadHelper.waitForElementVisible(actions);
        DriverUtil.sleep(2000L);
        actions.click();
        DriverUtil.sleep(3000L);
    }

    public void clickPublish() {
        clickActions();
        DriverUtil.sleep(3000L);
        StepReport.info("Click Publish");
        String xpath="//*[text()='Publish']";
        WebElement publishElem=DriverUtil.getElement(By.xpath(xpath));
        /*JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", publishElem);*/
        PageLoadHelper.waitForElementVisible(publishElem);
        DriverUtil.clickByJS(publishElem);
        DriverUtil.sleep(5000L);
    }

    public String getEnrollmentStatus() {
        StepReport.info("Return Enrollment Status");
        DriverUtil.sleep(3000L);
        return programStatus.getText();
    }

}
