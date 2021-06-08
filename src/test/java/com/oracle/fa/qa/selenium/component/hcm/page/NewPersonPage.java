package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPersonPage extends BasePageObject<NewPersonPage> {
    @FindBy(xpath = "//a/img[@title='Tasks']")
    WebElement tasks;

    @Override
    public void isLoaded() {
        // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(tasks) == null) {

            throw new TestErrorException("The Hire Employee page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void clickTask() {
        StepReport.info("Select Task");
        tasks.click();

    }

    public HireEmployeePage selectTask(String taskName) {
        if(taskName.equalsIgnoreCase("Employee")){
            StepReport.info("Select hire an employee task");
            DriverUtil.waitForElementVisible("//ul/li/a[text()='Hire an Employee']", 10);
            findElement(By.xpath("//ul/li/a[text()='Hire an Employee']")).click();

        }
        else{
            StepReport.info("Add a "+taskName+"");
            DriverUtil.waitForElementVisible("//ul/li/a[text()='Add a "+taskName+"']", 10);
            findElement(By.xpath("//ul/li/a[text()='Add a "+taskName+"']")).click();
        }

        HireEmployeePage hireEmployeePage = PageFactory.getPage(HireEmployeePage.class);
        return hireEmployeePage;


    }

}
