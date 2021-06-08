package com.oracle.fa.qa.selenium.component.ess.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActionsPage extends BasePageObject<ActionsPage> {

    @FindBy(xpath = "//a[text()='Actions']")
    WebElement actions;
//this method performs approve and reject actions
    public void performAction(String action){
        DriverUtil.waitForElementVisible("//div/a/span[text()='"+action+"']",10);
        findElement(By.xpath("//div/a/span[text()='"+action+"']")).click();

    }
    //this method is to perform actions other than approve and reject
    public void selectMoreOptions(String action,String user) {

        DriverUtil.sleep(2000);

        if (action.equalsIgnoreCase("Request Information...")) {
            actions.click();
            findElement(By.xpath("//td[text()='" + action + "']")).click();
            DriverUtil.sleep(3000);
            findElement(By.xpath("//textarea[contains(@id,'commentsForRequestInfo::content')]")).sendKeys("More Info Needed");
            findElement(By.xpath("//button[contains(@id,'eqIfD::ok')]")).click();
            DriverUtil.sleep(4000);

        }
        if (action.equalsIgnoreCase("Submit Information")) {
            executeScript("scroll(0, -450);");
            DriverUtil.sleep(2000);
            findElement(By.xpath("//*[@id='pc2:adCmtBt::icon']")).click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("//*[@id='cmtBox::content']")).sendKeys("Submitted Info");
            findElement(By.xpath("//button[@id='cb1']")).click();
            DriverUtil.sleep(2000);
            executeScript("scroll(0, 450);");
            DriverUtil.sleep(2000);
            actions.click();
            DriverUtil.sleep(2000);

        }
        if (action.equalsIgnoreCase("Reassign...") || action.equalsIgnoreCase("Delegate")) {

            actions.click();
            if (action.equalsIgnoreCase("Delegate")) {
                findElement(By.xpath("//td[text()='Reassign...']")).click();
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

    }
}
