package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ChangeManagerPage extends HCMBasePageObject {

    @FindBy(xpath="//div[@title='Review']/a")
    WebElement review;
    @FindBy(xpath = "//div[@title='Submit']/a")
    WebElement submitButton;
    @FindBy(xpath = "//label[text()='Person Number']/following::td[1]")
    WebElement personNum;


    protected void isLoaded() {
        // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(review) == null) {

            throw new TestErrorException("The Change Manager page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
    public void clickReview(){
        try {
            PageLoadHelper.waitForJetPageReady(driver, 20000L);
            DriverUtil.sleep(5000);
            review.click();
            review.click();
            StepReport.info("Clicked Review in First Attempt");
        }
        catch (Error e){
            review.sendKeys(Keys.ENTER);
            StepReport.info("Clicked Review in Second Attempt");

        }
        catch (Exception e){
            review.sendKeys(Keys.ENTER);
            StepReport.info("Clicked Review in Second Attempt");

        }
        DriverUtil.sleep(5000);
    }

    public String getPersonNumber() {
        StepReport.info("Get Person Number");
        String personNumber=personNum.getText();
        return personNumber;
    }



    public void SubmitChangeManager(){
        //PageLoadHelper.waitForJetPageReady(driver, 10000L);
        submitButton.click();
        StepReport.info("Clicked first submit button");
        DriverUtil.sleep(5000);
    }


    public void clickSubmit(){
        try {
            PageLoadHelper.waitForJetPageReady(driver, 10000L);
            submitButton.click();
            StepReport.info("Clicked submit in first attempt");
        }
        catch (Error e){
            submitButton.click();
            StepReport.info("Clicked submit in second attempt");

        }
        catch (Exception e){
            submitButton.click();
            StepReport.info("Clicked submit in second attempt");

        }
        for(int i =0;i<3;i++){
            try {
                findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
                DriverUtil.sleep(5000);
                break;
            }
            catch (Exception e){
                try {
                    submitButton.sendKeys(Keys.ENTER);
                    findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
                    StepReport.info("Clicked submit in third attempt");
                    DriverUtil.sleep(3000);
                    break;
                }
                catch (Exception e1){
                    submitButton.sendKeys(Keys.ENTER);
                    findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
                    StepReport.info("Clicked submit in fourth attempt");
                    DriverUtil.sleep(3000);


                }

            }

        }
        findElement(By.xpath("//button[contains(@id,'okConfirmation')]")).click();
        DriverUtil.sleep(3000);
    }


}
