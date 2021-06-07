package com.oracle.fa.qa.selenium.component.ess.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ESSUIProcessSearchPage extends BasePageObject<ESSUIProcessSearchPage> {

    @FindBy(xpath ="//*[@id='pt1:USma:0:MAnt1:0:pt1:pw1']")
    WebElement popUp;

    @FindBy(xpath ="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:subradioid:_0']")
    WebElement jobRadioButton;

    @FindBy(xpath="//input[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice2::content']")
    WebElement jobName;

    @FindBy(xpath ="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:snpokbtnid']")
    WebElement okButton;

    @FindBy(xpath ="//a[@title='Search: Name']")
    WebElement searchProcessName;

    @FindBy(xpath ="//*[text()='Search...']")
    WebElement searchLink;

    @FindBy(xpath ="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::lovDialogId::_ttxt']")
    WebElement searchAndSelect;

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice2::_afrLovInternalQueryId:value00::content']")
    WebElement name;

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice2::_afrLovInternalQueryId::search']")
// "//span[text()='Search']")
    WebElement searchButton;

    @FindBy(xpath="/html/body/div[2]/form/div[2]/div[2]/div[3]/div[1]/table/tbody/tr/td/div/div/table/tbody/tr[2]/td[2]/div/div[2]/div/div/div/div/div[2]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]")
            //*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice2_afrLovInternalTableId:0:_afrColChild0']")
    WebElement selectedJob;

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice2::lovDialogId::ok']")
//    @FindBy(xpath="//a/span[text()='OK']")
    WebElement okJobSelectButton;

    /*
       ** JobSet related
     */
    @FindBy(xpath="//*[text()='Job Set']")
    WebElement selectJobSet;

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice3::lovIconId']")
    WebElement searchJobSetName;

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice3::_afrLovInternalQueryId:value00::content']")
    WebElement jsName;

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice3::_afrLovInternalQueryId::search']")
    WebElement jsSeachButton;

//    @FindBy(xpath="//*[contains(@id,'lovDialogId::ok')]")
    @FindBy(xpath ="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice3::lovDialogId::ok']")
    WebElement okJobSetSelectButton;

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:selectOneChoice3_afrLovInternalTableId:0:_afrColChild0']")
    WebElement selectedJobSet;

    @Override
    protected void isLoaded() {
//        DriverUtil.switchToWindow("Schedule New Process");
        if (PageLoadHelper.waitForElementVisible(jobName) == null) {
            throw new TestErrorException("The Jobs radio button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public ESSUIEssSRSPage searchProcess(String processName){
        StepReport.info("Inside ESSUIMonitorPage.searchProcess()");
        PageLoadHelper.waitForElementVisible(jobName);
        DriverUtil.sleep(3000);
        searchProcessName.click();
        DriverUtil.sleep(5000);
        searchLink.click();
        StepReport.info("Inside ESSUIMonitorPage.searchProcess() - Clicked on Search Link");
        DriverUtil.sleep(5000);
        name.sendKeys(processName);
        StepReport.info("Inside ESSUIMonitorPage.searchProcess() : Entered process name for searching");
        DriverUtil.sleep(3000);
        searchButton.click();
        DriverUtil.sleep(5000);
        selectedJob.click();
        StepReport.info("Inside ESSUIMonitorPage.searchProcess() : Searched job Selected");
        DriverUtil.sleep(5000);
        okJobSelectButton.click();
        DriverUtil.sleep(5000);
        okButton.click();
        StepReport.info("Inside ESSUIMonitorPage.searchProcess() : Clicked on OK Button");
        DriverUtil.sleep(5000);
        ESSUIEssSRSPage srsPage = PageFactory.getPage(ESSUIEssSRSPage.class);
        srsPage.isLoaded();
        return srsPage;
    }

    public ESSUIEssSRSPage switchToPopUp(){
        StepReport.info("Inside ESSUIMonitorPage.switchToPopUp()");
        ESSUIEssSRSPage essSRSPage = PageFactory.getPage(ESSUIEssSRSPage.class);
        essSRSPage.isLoaded();
//        DriverUtil.switchToWindow("Schedule New Process");

        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        System.out.print("This is PopUp title" + driver.getTitle());
        return essSRSPage;
    }

    public ESSUIEssSRSPage searchJobSet(String jobSetName){
        StepReport.info("Inside ESSUIProcessSearchPage.searchJobSet()");
        selectJobSet.click();
        DriverUtil.sleep(3000);
//      PageLoadHelper.waitForElementVisible(seachProcessName);
        DriverUtil.sleep(5000);
        searchJobSetName.click();
        DriverUtil.sleep(5000);
        searchLink.click();
        DriverUtil.sleep(7000);
        jsName.sendKeys(jobSetName);
        StepReport.info("Searching for Jobset : " + jobSetName);
        DriverUtil.sleep(3000);
        jsSeachButton.click();
        DriverUtil.sleep(5000);
        selectedJobSet.click();
        DriverUtil.sleep(5000);
        StepReport.info("selected Jobset : " + jobSetName);
        okJobSetSelectButton.click();
        DriverUtil.sleep(5000);
        okButton.click();
        DriverUtil.sleep(5000);
        ESSUIEssSRSPage srsPage = PageFactory.getPage(ESSUIEssSRSPage.class);
        srsPage.isLoaded();
        return srsPage;
    }

}
