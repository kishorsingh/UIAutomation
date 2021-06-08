package com.oracle.fa.qa.selenium.component.common.page;

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

public class DirectoryPage extends BasePageObject<DirectoryPage> {
    @FindBy(xpath="//input[contains(@id,'_FOTsr1:0:it1::content')]")
    WebElement directorySearchBox;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchInput;

    @FindBy(xpath="//img[@alt='Search']")
    WebElement searchIcon;

    @FindBy(xpath="//a[contains(text(),'Veda')]")
    WebElement mossVeda;

    @FindBy(xpath="//*[text()='Show Filters']")
    WebElement showFilters;


    @Override
    public void isLoaded(){
        if(PageLoadHelper.waitForElementVisible(searchIcon)==null){
            throw new TestErrorException("The Directory page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void searchEmployee(String empName){
        directorySearchBox.clear();
        DriverUtil.sleep(2000L);
        directorySearchBox.sendKeys(empName);
        DriverUtil.sleep(4000L);
        StepReport.info("Click on emp",empName);
        searchIcon.click();
        DriverUtil.sleep(3000);

        searchEmployeeUntilAppear(empName);
    }

    public void searchEmployeeUntilAppear(String employeeName) {
        for(int i=0; i<10; i++) {
            PageLoadHelper.waitForElementClickable(showFilters);
            DriverUtil.sleep(2000L);
            String element="//a[text()='"+employeeName+"']";
            if(DriverUtil.isElementPresent(By.xpath(element))) {
                break;
            }
            StepReport.info("retry to search employee: " + employeeName);
            searchInput.clear();
            searchInput.sendKeys(employeeName);
            searchIcon.click();
            DriverUtil.sleep(20000L);
        }
    }

    //#Pragathi : This is an old method which used Moss Veda for everytransaction (Promote, change locationn, change manager etc)
    public EmployeeInformationPage selectEmployee(){
        PageLoadHelper.waitForElementClickable(mossVeda);
        DriverUtil.sleep(4000L);
        StepReport.info("Click on Moss Veda");
        mossVeda.click();
        //DriverUtil.waitForElementVisible("//span/a[@title='Moss,Veda']",10);
        //findElement(By.xpath("//span/a[@title='Moss,Veda']")).click();
        EmployeeInformationPage employeeInformationPage = PageFactory.getPage(EmployeeInformationPage.class);
        employeeInformationPage.isLoaded();
        return employeeInformationPage;
    }

    public EmployeeInformationPage selectEmployee2(String employeeName){
        PageLoadHelper.waitForElementClickable(showFilters);
        DriverUtil.sleep(2000L);
        String element="//a[contains(text(),'"+employeeName+"')]";
        WebElement webElement=DriverUtil.getElement(By.xpath(element));
        PageLoadHelper.waitForElementClickable(webElement);
        DriverUtil.sleep(4000L);
        StepReport.info("Click on Employee Name: "+employeeName);
        DriverUtil.clickByJS(webElement);
        EmployeeInformationPage employeeInformationPage = PageFactory.getPage(EmployeeInformationPage.class);
        employeeInformationPage.isLoaded();
        return employeeInformationPage;

    }



}
