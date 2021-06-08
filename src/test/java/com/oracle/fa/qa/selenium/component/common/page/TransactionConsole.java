package com.oracle.fa.qa.selenium.component.common.page;

import com.github.wiselenium.factory.annotation.Component;
import com.oracle.fa.qa.selenium.component.hcm.page.HCMBasePageObject;
import com.oracle.fa.qa.selenium.component.hcm.page.ApprovalRulesPage;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Component
public class TransactionConsole extends HCMBasePageObject {

    @FindBy(xpath = "//*[@title='Approval Rules']")
    WebElement approvalRules;
    @FindBy(xpath = "//a[text()='Completed']")
    WebElement completedTransactions;
    @FindBy(xpath ="//a[text()='Pending']")
    WebElement pendingTransactions;
    @FindBy(xpath ="//input[contains(@id,'keywordSearchBox::content')]")
    WebElement transactionSearchBox;
    @FindBy(xpath = "//div[@title='Search']/a")
    WebElement transactionSearchIcon;
    @FindBy(xpath= "//div/span[text()='Submitted On']/parent::div/preceding-sibling::div/table/tbody/tr/td[2]/a[@title='Sort Descending']")
    WebElement descending;

    //public static ApprovalRulesPage apprvRulPage;

    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(approvalRules) == null) {
            throw new TestErrorException("The approvalRules field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
   public  ApprovalRulesPage navigateToRules(){
        isLoaded();
       StepReport.info("Navigate to Rules");
       approvalRules.click();
       ApprovalRulesPage ap = PageFactory.getPage(ApprovalRulesPage.class);
       return ap;
       }
       public void clickTransactions(String status){
       if(status.equalsIgnoreCase("Completed")) {
           completedTransactions.click();
           DriverUtil.sleep(4000L);
       }
       else if(status.equalsIgnoreCase("Pending")){
           pendingTransactions.click();
           DriverUtil.sleep(4000L);
           }
       }

       public void checkTransactionStatus(String employeeName){
           transactionSearchBox.sendKeys(employeeName);
           DriverUtil.sleep(2000);
           transactionSearchIcon.click();
//           PageLoadHelper.waitForJetPageReady(driver);
           DriverUtil.sleep(4000);

           findElement(By.xpath("//a[text()='"+employeeName+"']")).click();
           DriverUtil.sleep(8000);
//           PageLoadHelper.waitForJetPageReady(driver);
           System.out.println(findElement(By.xpath("//span[contains(@id,'soc1::content')]")).getText());
           Assert.assertTrue(findElement(By.xpath("//span[contains(@id,'soc1::content')]")).getText().equalsIgnoreCase("Completed"));

       }

    public void checkTransactionStatusPromote(String employeeName){
        transactionSearchBox.sendKeys(employeeName);
        DriverUtil.sleep(2000);
        transactionSearchIcon.click();
//           PageLoadHelper.waitForJetPageReady(driver);
        DriverUtil.sleep(4000);
        findElement(By.xpath("//div/span[text()='Submitted On']/parent::div/preceding-sibling::div/table/tbody/tr/td[2]/a[@title='Sort Descending']")).click();
        DriverUtil.sleep(4000);
        findElement(By.xpath("//a[text()='"+employeeName+"'][1]")).click();
        DriverUtil.sleep(8000);
//           PageLoadHelper.waitForJetPageReady(driver);
        System.out.println(findElement(By.xpath("//span[contains(@id,'soc1::content')]")).getText());
        Assert.assertTrue(findElement(By.xpath("//span[contains(@id,'soc1::content')]")).getText().equalsIgnoreCase("Completed"));

    }

    public void checkTransactionStatusforTasks(String transactioname){
        transactionSearchBox.sendKeys(transactioname);
        DriverUtil.sleep(2000);
        transactionSearchIcon.click();
        DriverUtil.sleep(4000);
        descending.click();
        DriverUtil.sleep(10000);
        //boolean isPresent = driver.findElements(By.xpath("//*[contains(text(),'"+transactioname+"')]")).size() > 0;
        boolean isPresent = isReportDisplayed(transactioname);
        if (isPresent==true) {
            StepReport.info("Terminated successfully, the task is in completed status");
            DriverUtil.sleep(8000);
        }
        else{
            throw new TestErrorException("Terminate action is not completed");
        }
    }

    public boolean isReportDisplayed(String transactioname) {
        StepReport.info("Wait for : "+transactioname);

            try {
                String element="//a[contains(text(),'"+transactioname+"')]";
                WebElement report=DriverUtil.getElement(By.xpath(element));
                if(report.isDisplayed()) {
                    StepReport.info("Report displayed.");
                    return true;
                }else {
                    StepReport.info("Report not displayed");
                    return false;
                }
            }catch(Exception e) {
                StepReport.info("Report not displayed");
                return false;
            }
        }


       public void searchTransaction(String transactionName){
           transactionSearchBox.sendKeys();
           DriverUtil.sleep(2000);
           transactionSearchIcon.click();
           DriverUtil.sleep(2000);
       }
}
