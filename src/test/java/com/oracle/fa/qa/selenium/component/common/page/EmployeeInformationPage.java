package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.hcm.page.OrgChartPage;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.Driver;

public class EmployeeInformationPage extends BasePageObject<EmployeeInformationPage> {
    @FindBy(xpath = "//span[text()='Veda Moss']")
    WebElement employeeName;

    @FindBy(xpath = "//*[contains(@id,'pt1:sp2:pcb1')]")
    WebElement employeeInfoPageButton;

    @FindBy(xpath="//a/span[text()='Scott Ballard']")
    WebElement manager;

    //@FindBy(xpath="(//a/img[@title='View in Organization Chart'])[2]")
    @FindBy(xpath="//a[contains(text(),'View in Organization Chart')]")
    WebElement organizationChart;

    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(employeeInfoPageButton) == null) {
            throw new TestErrorException("The employee information page is not loaded " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Employee Info Page is loaded");
    }
    public void selectManager(){
        manager.click();
    }

    public OrgChartPage clickOrganizationChart(){
        StepReport.info("Click on Organization Chart");
        organizationChart.click();
        DriverUtil.sleep(5000);
        OrgChartPage orgChartPage = PageFactory.getPage(OrgChartPage.class);
        return orgChartPage;
    }
}
