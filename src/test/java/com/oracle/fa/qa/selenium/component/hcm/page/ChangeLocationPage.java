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



public class ChangeLocationPage extends HCMBasePageObject {

    @FindBy(xpath="//div[@title='Review']/a")
    WebElement review;
    @FindBy(xpath = "//div[@title='Submit']/a")
    WebElement submitButton;
    @FindBy(xpath ="//a[@title='Location']")
    WebElement locationSelect;
    @FindBy(xpath="//button[text()='Search']")
    WebElement search;
    @FindBy(xpath = "//label[text()='Person Number']/following::td[1]")
    WebElement locationNum;


    protected void isLoaded() {
        // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(locationSelect) == null) {

            throw new TestErrorException("The Change Location page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
    public void selectLocation(String locationCode){
        try {
            PageLoadHelper.waitForJetPageReady(driver, 1000);
            locationSelect.click();
        }
        catch (Error e){
            locationSelect.click();

        }
        catch (Exception e){
            locationSelect.click();
        }


        DriverUtil.sleep(4000);
        findElement(By.xpath("//a[text()='Search...']")).click();
        DriverUtil.sleep(5000);
        findElement(By.xpath("//label[text()=' Name']/preceding-sibling::input")).sendKeys("Oracle");
        search.click();
        DriverUtil.sleep(6000);
       // WebElement searchResult= findElement(By.xpath("//span[contains(text(),'BLR48')]/parent::span/parent::td/parent::tr"));
        WebElement searchResult =findElement(By.xpath("//span[text()='"+locationCode+"']"));
        DriverUtil.clickByJS(searchResult);
        DriverUtil.sleep(2000);
        findElement(By.xpath("//button[contains(@id,':lovDialogId::ok')]")).click();
        DriverUtil.sleep(3000);
    }
    public void clickReview(){
        try {
            PageLoadHelper.waitForJetPageReady(driver, 10000L);
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

    public String getLocationNumber() {
        StepReport.info("Get Person Number");
        String personNumber=locationNum.getText();
        return personNumber;
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
