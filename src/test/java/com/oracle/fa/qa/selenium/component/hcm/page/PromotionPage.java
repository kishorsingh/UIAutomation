package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PromotionPage extends HCMBasePageObject {
    @FindBy(xpath = "//select[@title ='Promotion']")
    WebElement promotionSelectBox;

    @FindBy(xpath = "//div[@title='Next']/a")
    WebElement nextHref;

    @FindBy(xpath = "//div[@title='Submit']/a")
    WebElement submitButton;

    @FindBy(xpath = "//a[contains(.,'Submit')]")
    WebElement submitPromote;

    @FindBy(xpath="//input[contains(@placeholder,'m/d/yy')]")
    WebElement inputDate;

    @FindBy(xpath="//span[text()='Save']/parent::a")
    WebElement save;

    @FindBy(xpath="//button[contains(@id,'okConfirmationDialog')]")
    WebElement okButton;

    @FindBy(xpath = "//label[text()='Person Number']/following::td[1]")
    WebElement personNum;

    @FindBy(xpath="//button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath="//button[@accesskey='K']")
    WebElement confirmButton;

    @FindBy(xpath="//a[contains(@class,'dropdown-icon-style')]")
    WebElement dropDown;

    @FindBy(xpath="//*[text()='GHR Promote']")
    WebElement promotionReason;





    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(nextHref) == null) {

            throw new TestErrorException("The Hire Employee page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }


    public void clickNextHref() {
        //PageLoadHelper.waitForJetPageReady(driver);
        Actions ac = new Actions(FrameworkContext.getInstance().getWebDriver());
       // DriverUtil.scrollIntoView(nextHref);
       // DriverUtil.sleep(3000);
        System.out.println("clicked next");
        //nextHref.click();
       // nextHref.sendKeys(Keys.ENTER);
        nextHref.sendKeys(Keys.RETURN);
    //    findElement(By.xpath(""))
       // DriverUtil.sleep(10000);
    }
    public  void clickNextButton(){
        //PageLoadHelper.waitForJetPageReady(driver);
       //DriverUtil.scrollIntoView(findElement(By.xpath("//button[@accesskey='x']")));
       //findElement(By.xpath("//button[@accesskey='x']")).click();
       //DriverUtil.sleep(5000);
        StepReport.info("Click next button");
        DriverUtil.sleep(5000);
        String xpath = "//span[text()='x']";
        WebElement nextButton = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(nextButton);
        DriverUtil.sleep(5000);
    }

    public void clickSubmitButton() {
        StepReport.info("Click submit button");
        DriverUtil.sleep(5000);
        String xpath = "//span[text()='m']";
        WebElement nextButton = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(nextButton);
        DriverUtil.sleep(5000);
    }

    public void clickContinueButtonInPage() {
        StepReport.info("Click submit button");
        DriverUtil.sleep(5000);
        String xpath = "//span[text()='u']";
        WebElement nextButton = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(nextButton);
        DriverUtil.sleep(5000);
    }

    public void clickYesButton() {
        StepReport.info("Click yes button");
        DriverUtil.sleep(5000);
        String xpath = "//span[text()='Y']";
        WebElement nextButton = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(nextButton);
        DriverUtil.sleep(5000);
    }

    public void ContinuetoNextpage() {
        String proceed = "//span[contains(text(),'Continue')]";
        WebElement continuetonext = DriverUtil.getElement(By.xpath(proceed));
        DriverUtil.clickByJS(continuetonext);
        DriverUtil.sleep(2000);
    }

    public void selectPromotionReason(){
        /*findElement(By.xpath("//td/label[text()='Promotion Reason']/parent::td/following-sibling::td/select")).click();
        DriverUtil.sleep(2000);
        findElement(By.xpath("//td/label[text()='Promotion Reason']/parent::td/following-sibling::td/select/option[@value='GHR PROMOTE']")).click();*/

        //#Pragathi : Entering yesterdays date (as setting todays date is causing conflict in timezones and delay in approval flow)
        Date dt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, -1);
        dt = calendar.getTime();
        String prevDate = new SimpleDateFormat("MM/dd/yy").format(dt);
        inputDate.clear();
        DriverUtil.sleep(500);
        inputDate.sendKeys(prevDate);
        DriverUtil.sleep(500);
        StepReport.info("Entered this date as promotion Start date :" + prevDate);
        //#Pragathi : Enter promotion reason
        //String xpath = "//*[text()='GHR Promote']";
        //WebElement promotionReason = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(500);
        dropDown.click();
        StepReport.info("** Entering promotion reason **");
        promotionReason.click();
        DriverUtil.sleep(2000);

    }
    public void clickSave(){
        //PageLoadHelper.waitForElementClickable(save);
        //PageLoadHelper.waitForJetPageReady(driver);

        try {
            PageLoadHelper.waitForJetPageReady(driver,10000);
            StepReport.info("Clicked Save Employee Request in 1st Attempt");
            for(int i=0;i<2;i++){
                try {
                    save.click();
                    DriverUtil.sleep(3000);
                    break;
                }
                catch (Exception e2){
                    findElement(By.xpath("//span[text()='Save']/parent::a")).click();
                }
            }


        }
        catch (Error e){
            DriverUtil.clickByJS(save);
            StepReport.info("Clicked Save Employee Hire Request in 2nd Attempt");
            DriverUtil.sleep(3000);
        }

        //DriverUtil.sleep(3000);
        try{
            findElement(By.xpath("//button[contains(@id,'okConfirmationDialog')]")).click();
            DriverUtil.sleep(3000);
        }
        catch (Exception e){
            save.click();
            StepReport.info("Clicked Save Employee Hire Request in 3rd Attempt");
            DriverUtil.sleep(3000);
            try {
                findElement(By.xpath("//button[contains(@id,'okConfirmationDialog')]")).click();
                DriverUtil.sleep(3000);
            }
            catch (Exception e1){
                save.sendKeys(Keys.RETURN);
                StepReport.info("Clicked Save Employee Hire Request in 4th Attempt");
                DriverUtil.sleep(3000);
                findElement(By.xpath("//button[contains(@id,'okConfirmationDialog')]")).click();
                DriverUtil.sleep(3000);
            }

        }

        StepReport.info("Clicked OK Dialog After Saving Employee Promotion Request");
        DriverUtil.sleep(3000);

    }

    public String getPersonNumber() {
        StepReport.info("Get Person Number");
        String personNumber=personNum.getText();
        return personNumber;
    }

    public  void clickPromoteSubmit() {
        submitPromote.click();
        StepReport.info("*** Submitting promote request *** ");

    }



    public  void clickSubmit(){

        try {
            PageLoadHelper.waitForJetPageReady(driver,10000);
            //DriverUtil.sleep(3000);
            submitButton.click();
            StepReport.info("Submitting Employee Hire Request in attempt 1");
        }
        catch (Error e){
            submitButton.click();
            StepReport.info("Submitting Employee Hire Request in attempt 2");
        }
        DriverUtil.sleep(4000);

        /*StepReport.info("Click Submit");
        PageLoadHelper.waitForElementClickable(submitButton, 20 );
        DriverUtil.clickByJS(submitButton);*/
        DriverUtil.sleep(3000);
        StepReport.info("Click Yes");
        PageLoadHelper.waitForElementClickable(yesButton, 20 );
        DriverUtil.clickByJS(yesButton);
        DriverUtil.sleep(3000);
        StepReport.info("Click OK");
        PageLoadHelper.waitForElementClickable(confirmButton, 20 );
        DriverUtil.clickByJS(confirmButton);
        DriverUtil.sleep(3000);

        /*findElement(By.xpath("//button[@accesskey='Y']")).click();
       // DriverUtil.waitForElementVisible("//button[@accesskey='K']",10);
        DriverUtil.sleep(5000);
        findElement(By.xpath("//button[@accesskey='K']")).click();
        DriverUtil.sleep(4000);*/

    }

    public void clickContinueButton() {
        StepReport.info("Click Continue button");
        DriverUtil.sleep(5000);
        String xpath = "//span[text()='Continue']";
        WebElement element = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(element);
        DriverUtil.sleep(5000);
    }

}
