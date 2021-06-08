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
//    @FindBy(xpath ="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:subradioid:_0']")
    @FindBy(xpath ="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::lovIconId']")
    WebElement jobRadioButton;

    @FindBy(xpath ="//button[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:snpokbtnid']")
    WebElement okButton;
    @FindBy(xpath = "//a[@title='Search: Name']")
    WebElement seachProcessName;

    @FindBy(xpath ="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::dropdownPopup::popupsearch']")
    WebElement seachLink;
    @FindBy(xpath ="//*[text()='Search...']")
    WebElement searchLink1;
    @FindBy(xpath ="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::lovDialogId::_ttxt']")
    WebElement seachAndSelect;

    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::_afrLovInternalQueryId:value00::content']")
    WebElement name;

//    @FindBy(css = "a[title='Search']")
//    @FindBy(xpath="//*[@id='pt1:USma:0:MAnt1:0:pt1:selectOneChoice2::_afrLovInternalQueryId::search']")
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::_afrLovInternalQueryId::search']")
    WebElement seachButton;

//    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2_afrLovInternalTableId:0:_afrColChild0']")

    @FindBy(xpath="//*[contains(@id, 'selectOneChoice2_afrLovInternalTableId::db')]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]")
    WebElement selectedJob;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::lovDialogId::ok']")
    WebElement okJobSelectButton;

    /*
       ** JobSet related
     */
    @FindBy(xpath="//*[text()='Job Set']")
    WebElement selectJobSet;
    @FindBy(xpath="//*[contains(@id,'_afrLovInternalQueryId:value00::content')]")
    WebElement jsName;
    @FindBy(xpath="//*[contains(@id,'_afrLovInternalQueryId::search')]")
    WebElement jsSeachButton;
    @FindBy(xpath="//*[contains(@id,'lovDialogId::ok')]")
    WebElement okJobSetSelectButton;
    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice3_afrLovInternalTableId::db']/table/tbody/tr/td[2]/div/table/tbody/tr/td[1]/span")
    WebElement selectedJobSet;
    @FindBy(xpath ="//button[text()='OK']")
    WebElement jsOKButton;

    @Override
    protected void isLoaded() {
//        DriverUtil.switchToWindow("Schedule New Process");
        if (PageLoadHelper.waitForElementVisible(jobRadioButton) == null) {
            throw new TestErrorException("The Jobs radio button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public ESSUIEssSRSPage searchProcess(String processName){
        StepReport.info("Inside ESSUIMonitorPage.searchProcess()");
        PageLoadHelper.waitForElementVisible(seachProcessName);
        seachProcessName.click();
        DriverUtil.sleep(5000);
        seachLink.click();
        DriverUtil.sleep(5000);
        name.sendKeys(processName);
        DriverUtil.sleep(3000);
        seachButton.click();
        DriverUtil.sleep(5000);
        selectedJob.click();
        DriverUtil.sleep(5000);
        okJobSelectButton.click();
        DriverUtil.sleep(5000);
        okButton.click();
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
//        PageLoadHelper.waitForElementVisible(seachProcessName);
        DriverUtil.sleep(5000);
        seachProcessName.click();
        DriverUtil.sleep(5000);
        searchLink1.click();
        DriverUtil.sleep(7000);
        jsName.sendKeys(jobSetName);
        DriverUtil.sleep(3000);
        jsSeachButton.click();
        DriverUtil.sleep(5000);
        selectedJobSet.click();
        DriverUtil.sleep(5000);
        okJobSetSelectButton.click();
        DriverUtil.sleep(5000);
        jsOKButton.click();
        DriverUtil.sleep(5000);
        ESSUIEssSRSPage srsPage = PageFactory.getPage(ESSUIEssSRSPage.class);
        srsPage.isLoaded();
        return srsPage;
    }

}
