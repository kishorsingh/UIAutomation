package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import java.sql.Driver;

public class TerminateEmployeePage extends HCMBasePageObject {

    //@FindBy(xpath = "//*[contains(@id,'Action::content')]")
    @FindBy(xpath = "//*[contains(@id,'Action::popWrap')]/ul")
    WebElement selectAction;

    @FindBy(xpath = "//label[text()='Immediately']//preceding-sibling::input")
    WebElement revokeUserAccess;

    @FindBy(xpath = "//button[text()='Review']")
    WebElement reviewButton;

    @FindBy(xpath = "//*[text()='Printable Page']")
    WebElement printablePage;

    @FindBy(xpath="//div[@title='Submit']/a")
    WebElement submit;

    @FindBy(xpath = "//*[contains(text(),'The request will be submitted. Do you want to continue?')]/following::button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath = "//*[text()='Confirmation']/following::button[@accesskey='K']")
    WebElement Confirm;


    @Override
    protected void isLoaded() {
        // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(selectAction) == null) {

            throw new TestErrorException("The Terminate Employee page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void selectAction(){
        StepReport.info("Select Action","Termination");
        String xpath = "//*[text()='Termination']";
        WebElement clickTermination = DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", clickTermination);
        DriverUtil.sleep(5000);
    }
    public void clickRevokeUserAccess(){
        StepReport.info("Click Revoke Access Immediately");
        DriverUtil.sleep(3000);
//        DriverUtil.clickByAction(revokeUserAccess,true);
//        DriverUtil.sleep(3000);

        try {
            ac.moveToElement(revokeUserAccess).build().perform();
            PageLoadHelper.waitForElementVisible(revokeUserAccess);
            ac.click(revokeUserAccess).build().perform();
        }
        catch (Error e){
            StepReport.info("Error:  "+ e.getMessage());

        }
        catch (Exception e){
            StepReport.info("Exception:  "+ e.getMessage());
            JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
            executor.executeScript("arguments[0].click();", revokeUserAccess);
        }
        DriverUtil.sleep(5000);
    }
    public void reviewTermination(){
        StepReport.info("Click Review Button");
        DriverUtil.scrollIntoView(reviewButton);
        DriverUtil.sleep(5000);
        reviewButton.click();
        DriverUtil.sleep(5000);
        PageLoadHelper.waitForElementVisible(printablePage);
        DriverUtil.sleep(2000);
    }

    public void clickYesInWarningPopUp() {
        StepReport.info("Click Yes Button in the warning pop-up");
        PageLoadHelper.waitForElementVisible(yesButton);
        DriverUtil.sleep(2000L);
        yesButton.click();
        DriverUtil.sleep(2000L);
    }
    public void ConfirmationDialog() {
        StepReport.info("Click OK in the confirmation pop-up");
        PageLoadHelper.waitForElementVisible(Confirm);
        DriverUtil.sleep(2000L);
        Confirm.click();
        DriverUtil.sleep(2000L);
    }


    public void submitTermination(){
        StepReport.info("Click Submit");
        try{
            //PageLoadHelper.waitForJetPageReady(driver);
            PageLoadHelper.waitForElementVisible(submit);
            DriverUtil.scrollIntoView(submit);
            submit.click();
            StepReport.info("Submitted Termination in first Attempt 1");
        }
        catch (Error e){
             submit.click();
             StepReport.info("Submitted Termination in first Attempt 2");
            }
            catch (Exception e1){
             DriverUtil.clickByJS(submit);
              StepReport.info("Submitted Termination in first Attempt 3");

        }
       /*for(int i =0;i<3;i++){
            try {
                findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
                DriverUtil.sleep(5000);
                break;
            }
            catch (Exception e){
                try {
                    submit.sendKeys(Keys.ENTER);
                    findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
                    StepReport.info("Clicked submit in third attempt");
                    DriverUtil.sleep(3000);
                    break;
                }
                catch (Exception e1){
                    submit.sendKeys(Keys.ENTER);
                    findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
                    StepReport.info("Clicked submit in fourth attempt");
                    DriverUtil.sleep(3000);


                }

            }

        }*/


    }

    public void clickSubmitButton() {
        StepReport.info("Click Submit Button");
        DriverUtil.sleep(5000L);
        String xpath = "//a[@accesskey='m']";
        WebElement submitButton = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(submitButton);
        DriverUtil.clickByJS(submitButton);
        DriverUtil.sleep(5000L);
    }

    public void clickContinueButtonInPage() {
        StepReport.info("Click submit button");
        DriverUtil.sleep(5000);
        String xpath = "//span[text()='u']";
        WebElement nextButton = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(nextButton);
        DriverUtil.sleep(5000);
    }


}
