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

public class ScheduledProcesses extends BasePageObject<ScheduledProcesses> {

    @FindBy(xpath="//*[text()='Schedule New Process']/parent::a")
    WebElement scheduleNewProcess;

    @FindBy(xpath="//*[@alt='Refresh']")
    WebElement refreshButton;

    @Override
    protected void isLoaded(){
        if(PageLoadHelper.waitForElementClickable(scheduleNewProcess)==null){
            throw new TestErrorException("The Scheduled Processes page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public String getProcessStatusAfterCompletion(String processID, int waitTimeInMinutes, String expectedStatus) {
        StepReport.info("Get the process status once it is completed : "+processID);
        String processStatus="";
        for(double time=waitTimeInMinutes*0.5;time<=waitTimeInMinutes;time++) {
            String element = "//*[text()='" + processID + "']/following::a[1]";
            WebElement statusElement = DriverUtil.getElement(By.xpath(element));
            processStatus = statusElement.getText().trim();
            if (processStatus.equalsIgnoreCase("Running") || processStatus.equalsIgnoreCase("Pending")){
                System.out.println("Process is not completed after "+time+" minutes");
                DriverUtil.sleep(60000);
                refreshButton.click();
            }
            else{
                StepReport.info("Process completed in "+time+" minutes. Status of the job is "+processStatus+"");
                break;
            }
            }
            return processStatus;
    }

}
