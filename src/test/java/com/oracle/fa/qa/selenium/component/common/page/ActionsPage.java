package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.hcm.page.HCMBasePageObject;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActionsPage extends HCMBasePageObject {

    @FindBy(xpath = "//a[text()='Actions']")
    WebElement actions;

    //this method performs approve and reject actions
    public void performAction(String action) {
        if(action.equalsIgnoreCase("Suspend")||action.equalsIgnoreCase("Withdraw")){
            actions.click();
            DriverUtil.sleep(2000);
            try {
                DriverUtil.waitForElementVisible("//td[text()='" + action + "']", 10);
                StepReport.info(""+action+" Request");
                findElement(By.xpath("//td[text()='" + action + "']")).click();
                DriverUtil.sleep(3000);
            }
            catch (Exception e){
                DriverUtil.clickByJS(findElement(By.xpath("//td[text()='" + action + "']")));
                StepReport.info(""+action+" by Clicking using JS");

            }

        }

        else{
            try {

                DriverUtil.waitForElementVisible("//div/a/span[text()='" + action + "']", 10);
                findElement(By.xpath("//div/a/span[text()='" + action + "']")).click();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                DriverUtil.clickByJS(findElement(By.xpath("//div/a/span[text()='" + action + "']")));
            }

        }
    }

    //this method is to perform actions other than approve and reject
    public void selectMoreOptions(String action, String user) {


        DriverUtil.sleep(2000);


        if (action.equalsIgnoreCase("Request Information...")) {
            actions.click();
            try {
                DriverUtil.waitForElementVisible("//td[text()='" + action + "']",10);
                findElement(By.xpath("//td[text()='" + action + "']")).click();
            }
            catch (Exception e){
                DriverUtil.clickByJS(findElement(By.xpath("//td[text()='" + action + "']")));
            }
            DriverUtil.sleep(5000);
            findElement(By.xpath("//textarea[contains(@id,'commentsForRequestInfo::content')]")).sendKeys("More Info Needed");
            findElement(By.xpath("//button[contains(@id,'eqIfD::ok')]")).click();
            DriverUtil.sleep(4000);

        }
        if (action.equalsIgnoreCase("Submit Information")) {
            executeScript("scroll(0, -450);");
            DriverUtil.sleep(4000);
            findElement(By.xpath("//*[@id='pc2:adCmtBt::icon']")).click();
            DriverUtil.sleep(4000);
            findElement(By.xpath("//*[@id='cmtBox::content']")).sendKeys("Submitted Info");
            findElement(By.xpath("//button[@id='cb1']")).click();
            DriverUtil.sleep(4000);
            executeScript("scroll(0, 450);");
            DriverUtil.sleep(4000);
            actions.click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("//td[text()='Submit Information']")).click();
            StepReport.info("Submitting Information");
            DriverUtil.sleep(4000);

        }
        if (action.equalsIgnoreCase("Reassign...") || action.equalsIgnoreCase("Delegate")) {

            actions.click();
            if (action.equalsIgnoreCase("Delegate")) {
                findElement(By.xpath("//td[text()='Reassign...']")).click();
                DriverUtil.sleep(3000);
                findElement(By.xpath("//input[@id='reAsIdB:reassignOrDelegateSelection:_1']")).click();
                // DriverUtil.sleep(3000);

            } else {
                findElement(By.xpath("//td[text()='" + action + "']")).click();
            }
            DriverUtil.sleep(5000);
            findElement(By.xpath("//input[@id='reAsIdB:idSearchStringField::content']")).sendKeys(user);
            findElement(By.xpath("//button[@id='reAsIdB:idSearchButton']")).click();
            DriverUtil.sleep(6000);
            // findElement(By.xpath("//input[@id='reAsIdB:dc_pc1:idSTable:0:selIdCB::content']")).click();
            try {

                findElement(By.xpath("//input[contains(@value,'reAsIdB')]")).click();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                try {
                    findElement(By.xpath("//input[@id='reAsIdB:dc_pc1:idSTable:0:selIdCB::content']")).click();
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                    findElement(By.xpath("//span[@class='af_selectBooleanRadio_content-input']")).click();
                }
            }
            findElement(By.xpath("//button[@id='reAsDg::ok']")).click();
        }
        if (action.equalsIgnoreCase("Adhoc Route...")) {
            actions.click();
            try {
                DriverUtil.waitForElementVisible("//td[text()='" + action + "']",10);
                findElement(By.xpath("//td[text()='" + action + "']")).click();
            }
            catch (Exception e){
                DriverUtil.clickByJS(findElement(By.xpath("//td[text()='"+action+"']")));
            }
            DriverUtil.sleep(6000);
            findElement(By.xpath("//textarea[contains(@id,'TaskDetailsCommentsField::content')]")).sendKeys("Selecting adhoc Route to vmoss user");
            findElement(By.xpath("//input[@id='rteIdB:idSearchStringField::content']")).sendKeys(user);
            findElement(By.id("rteIdB:idSearchButton")).click();
            DriverUtil.sleep(4000);
            try {
                findElement(By.xpath("//input[@id='rteIdB:dc_pc1:idSTable:0:selIdCB::content']")).click();
                StepReport.info("Selected user to re route request");
                DriverUtil.sleep(2000);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                try {
                    findElement(By.xpath("//input[contains(@value,'reAsIdB')]")).click();
                    StepReport.info("Selected user to re route request");
                    DriverUtil.sleep(4000);
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                    findElement(By.xpath("//span[@class='af_selectBooleanRadio_content-input']")).click();
                    StepReport.info("Selected user to re route request");
                    DriverUtil.sleep(4000);
                }
            }
                findElement(By.id("rteDg::ok")).click();
                StepReport.info("Ad hoc request rooted to vmoss");
                DriverUtil.sleep(3000);
            }

        if(action.equalsIgnoreCase("Push Back...")){
            actions.click();
            StepReport.info("Clicked Actions");
            DriverUtil.sleep(3000);
            findElement(By.xpath("//td[text()='"+action+"']")).click();
            StepReport.info("Selected Push Back Action");
            findElement(By.xpath("//textarea[@id='rqIDgC:commentsForPushBack::content']")).sendKeys("Pushing Back");
            findElement(By.id("reqIfD::ok")).click();
            StepReport.info("PushBack Successful");
            DriverUtil.sleep(3000);

        }

    }
    public void addAttachment(String fileName){

    }
    public void addComments(String comment){

    }
}