package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.hcm.page.HCMBasePageObject;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class WorkForceStructurePage extends HCMBasePageObject {
    @FindBy(xpath="//a[text()='Manage Locations']")
    WebElement manageLocations;
    @FindBy(xpath="//label[text()='Name']/preceding-sibling::span/parent::td/following-sibling::td/input")
    WebElement enterLocationName;
    @FindBy(xpath="//label[text()='Code']/preceding-sibling::span/parent::td/following-sibling::td/input")
    WebElement enterCode;
    @FindBy(xpath="//input[contains(@id,'countrylov::content')]")
    WebElement countryName;
    @FindBy(xpath="//label[text()='Address Line 1']/preceding-sibling::span/parent::td/following-sibling::td/input")
    WebElement locationAddress;
    @FindBy(xpath="//*[contains(@id, 'inputText17::content')]")
    WebElement locationAddress2;
    @FindBy(xpath = "//*[contains(@id,'inputText21::content')]")
    WebElement city;
    @FindBy(xpath="//div[@title='Review']/a")
    WebElement review;
    @FindBy(xpath="//div[@title='Submit']/a")
    WebElement submit;
    @FindBy(xpath = "//*[contains(@id,'countrylov::lovIconId')]")
    WebElement countrySelectIcon;

    public void clickManageLocations(){
        try{
        PageLoadHelper.waitForJetPageReady(driver,10000L);
        StepReport.info("click manage Location");
        manageLocations.click();
        }
        catch (Error e){
            StepReport.info("click manage Location");
            manageLocations.click();

        }

        DriverUtil.sleep(6000);
        findElement(By.xpath("//*[contains(@id,'ctb1::icon')]")).click();
        DriverUtil.sleep(15000);
    }
    public void setEnterLocationName(){
       // PageLoadHelper.waitForJetPageReady(driver,10000L);
        DriverUtil.sleep(3000);
        Random r = new Random();
        int n = r.nextInt(100)+1;
        String locationName="Oracle Lexington Tower"+n;
        DriverUtil.sleep(1000);
        enterLocationName.sendKeys(locationName);
        StepReport.info("Location Name: "+locationName);
        DriverUtil.sleep(2000);
    }
    public String setEnterCode(){
        Random r = new Random();
        int n = r.nextInt(100)+1;
        String code ="BLR"+n;
        enterCode.sendKeys(code);
        DriverUtil.sleep(2000);
        return code;

    }
    public void setCountryName(){
        executeScript("scroll(0, 600);");
        DriverUtil.sleep(2000);
        countryName.clear();
        countryName.sendKeys("United Kingdom");
        DriverUtil.sleep(2000);
        countryName.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000);

    }
    public void setLocationAddress(){
        for(int i=0;i<3;i++) {
            try {
                PageLoadHelper.waitForJetPageReady(driver, 5000);
                locationAddress.sendKeys("Ten Down Street");
                break;

            } catch (Error e) {
                System.out.println(e.getMessage());
                locationAddress.sendKeys("Ten Down Street");
                break;
            } catch (Exception e) {
                locationAddress.sendKeys("Ten Down Street");
                break;
            }
        }


        }

    public void setLocationAddress2(){

            try {
                PageLoadHelper.waitForJetPageReady(driver,5000);
                locationAddress2.sendKeys("Ten Down Street");

            }
            catch (Error e) {
                System.out.println(e.getMessage());
                locationAddress2.sendKeys("Ten Down Street");
            }
            catch (Exception e){
                locationAddress2.sendKeys("Ten Down Street");

            }


    }

    public void setCity(){
        for(int i=0;i<3;i++)
        try {
            city.sendKeys("London");
            break;
        }
        catch(Exception e){
            findElement(By.xpath("//*[contains(@id,'inputText21::content')]")).sendKeys("London");
        }
    }
    public void clickReview(){
        executeScript("scroll(0, -600);");
        try {
            PageLoadHelper.waitForJetPageReady(driver,10000l);
            review.click();
            StepReport.info("Clicked review in first attempt");
        }
        catch (Error e) {
            review.click();
            StepReport.info("Clicked review in second attempt");
        }
        //DriverUtil.sleep(10000);
    }
    public void clickSubmit(){
        try {
            PageLoadHelper.waitForJetPageReady(driver, 10000);
            submit.click();
            StepReport.info("Clicked location submit in first attempt");
        }
        catch (Error e){
            submit.click();
            StepReport.info("Clicked location submit in second attempt");

        }
        DriverUtil.sleep(3000);
        for(int i=0;i<3;i++){
        try{
            findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
            DriverUtil.sleep(3000);
            break;
        }
        catch (Exception e){
            submit.sendKeys(Keys.ENTER);
            StepReport.info("Clicked location submit in 3rd attempt");
            try {
                findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();
                DriverUtil.sleep(3000);
                break;
            }
            catch (Exception e1){
                DriverUtil.clickByJS(submit);
                StepReport.info("Clicked location submit in 4th attempt");
                findElement(By.xpath("//button[contains(@id,':okWarningDialog')]")).click();


            }

            }
        }

        DriverUtil.sleep(3000);
        findElement(By.xpath("//button[contains(@id,'okConfirmation')]")).click();
        DriverUtil.sleep(3000);

    }

}
