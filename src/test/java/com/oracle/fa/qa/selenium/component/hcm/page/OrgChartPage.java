package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrgChartPage extends HCMBasePageObject {


    @FindBy(xpath = ".//*[@id='_FOpt1:_FOr1:0:_FOSrPER_HCMPEOPLETOP_FUSE_DIRECTORY:0:_FOTsr1:1:r1:0:pt1:r1:0:sp1:ctb1']")
    WebElement matrixchart;


    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(matrixchart) == null) {

            throw new TestErrorException("The Hire Employee page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("OrgChart Page loaded");
    }

    public void clickActionLinkForPromotion(String employeename) {
        String path="//img[@title='View More Details'][1]";
        try {
            //DriverUtil.sleep(2000L);
            //ac.moveToElement(employeeTobePromotedLink).build().perform();
            //PageLoadHelper.waitForElementClickable(employeeTobePromotedLink);
            //findElement(By.xpath(path)).click();
            StepReport.info("Click Action Link for Promotion");

            //ac.moveToElement(findElement(By.xpath(path))).build().perform();
            findElement(By.xpath(path)).click();
            DriverUtil.sleep(2000L);
            DriverUtil.waitForElementVisible("//div/a[@title='Actions']",20);
            findElement(By.xpath("//div/a[@title='Actions']")).click();
            DriverUtil.sleep(2000L);

        }
        catch (Error e){
            findElement(By.xpath(path)).click();
            DriverUtil.sleep(2000L);
        }
        catch (Exception e){
            findElement(By.xpath(path)).click();
            DriverUtil.sleep(2000L);
        }

        DriverUtil.waitForElementVisible("//div/a[@title='Actions']", 4);
        findElement(By.xpath("//div/a[@title='Actions']")).click();
        DriverUtil.sleep(2000L);

    }

    public void clickLinkforChangeManager() {
        StepReport.info("Click Action Link for Change Manager");
        String path="//img[@title='View More Details'][1]";
        //ac.moveToElement(findElement(By.xpath(path))).build().perform();
        findElement(By.xpath(path)).click();
        DriverUtil.waitForElementVisible("//div/a[@title='Actions']",20);
        findElement(By.xpath("//div/a[@title='Actions']")).click();


    }

    public void clickActionLinkforTermination() {
        StepReport.info("Click Action Link for Employee Termination");

        String path="//img[@title='View More Details'][1]";
        //ac.moveToElement(findElement(By.xpath(path))).build().perform();
        findElement(By.xpath(path)).click();
        DriverUtil.waitForElementVisible("//div/a[@title='Actions']",20);
        findElement(By.xpath("//div/a[@title='Actions']")).click();


    }

    public void clickActionLinkForChangeLocation(String name) {
        StepReport.info("Click Action Link for Change Location");
        String path="//img[@title='View More Details'][1]";
        //ac.moveToElement(findElement(By.xpath(path))).build().perform();
        findElement(By.xpath(path)).click();
        DriverUtil.waitForElementVisible("//div/a[@title='Actions']",20);
        findElement(By.xpath("//div/a[@title='Actions']")).click();


    }

    public void clickEmploymentLink() {
        DriverUtil.waitForElementVisible("//div/a[text()='Personal and Employment']", 3);
        findElement(By.xpath("//div/a[text()='Personal and Employment']")).click();
    }

    public PromotionPage clickPromote() {
        DriverUtil.scrollIntoView(findElement(By.xpath("//div/a[text()='Promote']")));
        DriverUtil.sleep(2000L);
        //ac.moveToElement(findElement(By.xpath("//*[text()='Promote']"))).build().perform();
        findElement(By.xpath("//*[text()='Promote']")).click();
        PromotionPage prmPage = PageFactory.getPage(PromotionPage.class);
        return prmPage;
    }

    public ChangeManagerPage clickChangeManager() {
        DriverUtil.scrollIntoView(findElement(By.xpath("//div/a[text()='Change Manager']")));
        DriverUtil.sleep(2000L);
        //ac.moveToElement(findElement(By.xpath("//*[text()='Promote']"))).build().perform();
        findElement(By.xpath("//*[text()='Change Manager']")).click();
        ChangeManagerPage cmPage = PageFactory.getPage(ChangeManagerPage.class);
        return cmPage;
    }
    public TerminateEmployeePage clickTerminate() {
        StepReport.info("Click Terminate");
        DriverUtil.scrollIntoView(findElement(By.xpath("//div/a[text()='Terminate']")));
        DriverUtil.sleep(2000L);
        //ac.moveToElement(findElement(By.xpath("//div/a[text()='Terminate']"))).build().perform();
        findElement(By.xpath("//div/a[text()='Terminate']")).click();
        TerminateEmployeePage terminateEmployeePage = PageFactory.getPage(TerminateEmployeePage.class);
        return terminateEmployeePage;

    }

    public ChangeLocationPage clickChangeLocation() {
        DriverUtil.scrollIntoView(findElement(By.xpath("//div/a[text()='Change Location']")));
        //ac.moveToElement(findElement(By.xpath("//div/a[text()='Change Location']"))).build().perform();
        findElement(By.xpath("//div/a[text()='Change Location']")).click();
        ChangeLocationPage changeLocationPage = PageFactory.getPage(ChangeLocationPage.class);
        return changeLocationPage;
    }

}
