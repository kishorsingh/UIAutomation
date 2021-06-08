package com.oracle.fa.qa.selenium.component.bpm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * This class contains configuration and UI action related to EM Home Page
 *
 *
 * @author ashwaraj
 * @version 1.0
 */
public class EMFlowTracePage extends BasePageObject<EMFlowTracePage> {

    @FindBy(xpath = ".//span[text()='Flow Trace']")
    WebElement flowTraceTitle;

    @FindBy(xpath = "//*[@id='taskAuditTemplate:taskAuditTrailTemplate:taskNumber']")
    WebElement taskNumber;




//    @FindBy(xpath = ".//a[.='WebLogic Domain']/following::td[text()='Application Deployment']")
//    WebElement  applicationDeployment;

    /**
     * Determine whether or not the component is loaded. When the component is loaded, this method
     * will return, but when it is not loaded, an Error should be thrown. This also allows for complex
     * checking and error reporting when loading a page, which in turn supports better error reporting
     * when a page fails to load.
     * <p>
     * <p>
     * This behaviour makes it readily visible when a page has not been loaded successfully, and
     * because an error and not an exception is thrown tests should fail as expected. By using Error,
     * we also allow the use of junit's "Assert.assert*" methods
     *
     * @throws Error when the page is not loaded.
     */
    @Override
    public void isLoaded() {
        if ((PageLoadHelper.waitForElementClickable(flowTraceTitle,240) == null)) {
            throw new TestErrorException("EM page is not loaded" +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }



    public void clickHumanTask(String humanTask){
        String xpathHumanTask = "//span[text()='"+humanTask+"']";
        WebElement clickHumanTask = DriverUtil.getElement(By.xpath(xpathHumanTask));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(clickHumanTask);
        clickHumanTask.click();
    }

    public String getTaskNumber(){
        String taskNum="";
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(taskNumber);
        taskNum=taskNumber.getText();
        String[] strings = taskNum.split(":");
        taskNum=strings[1];
        return taskNum;
    }

}