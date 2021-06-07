package com.oracle.fa.qa.selenium.component.ess.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Date;

public class ESSUIMonitorPage extends BasePageObject<ESSUIMonitorPage> {

    // Search Criteria items

    @FindBy(xpath = "//input[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:value00::content']")
    WebElement processName;
    @FindBy(xpath = "//input[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:value10::content']")
    WebElement processId;
    @FindBy(xpath = "//select[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl::saveSearch::content']")
    WebElement savedSearch;
    @FindBy(xpath = "//select[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:value20::content']")
    WebElement status;
    @FindBy(xpath = "//select[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:operator3::content']")
    WebElement submissionTimeDropDown;
    @FindBy(xpath = "//input[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:operator4::content']")
    WebElement submissionTimeTextBox;
    @FindBy(xpath = "//select[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:operator4::content']")
            WebElement submissionNotesDropDown;
    @FindBy(xpath = "//input[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:value40::content']")
    WebElement submissionNotesTextBox;
    @FindBy(xpath = "//button[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl::search']")
    WebElement searchButton;
    @FindBy(xpath = "//button[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl::reset']")
    WebElement resetButton;
    @FindBy(xpath = "//button[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl:exportcsv']")
    WebElement downloadResults;

    //Search Results

    @FindBy(css = "//span[contains(text(),'Logout')]")
    WebElement userIcon;
//    @FindBy(css = "a[title='Expand Search']")
    @FindBy(xpath="//a[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:srRssdfl::_afrDscl']")
    WebElement expandSearchIcon;
    @FindBy(css = "a[title='Collapse Search']")
    WebElement collapseSearchIcon;
    @FindBy(xpath = "//span[text()='Schedule New Process']")
    WebElement scheduleNewProcess;
    @FindBy(xpath = "//button[text()='Put On Hold']")
    WebElement putOnHold;
    @FindBy(xpath = "//span[text()='Resubmit']")
    WebElement reSubmit;
    @FindBy(xpath = "//*[text()='Yes']")
    WebElement reSubmitWarningYes;


    /*
     *** Cancel Process
     */
    @FindBy(xpath = "//button[text()='Cancel Process']")
    WebElement cancelProcess;
    @FindBy(xpath = "//button[text()='Release Process']")
    WebElement releaseProcess;
    @FindBy(xpath = "//button[text()='View Log']")
    WebElement viewLog;
//    @FindBy(xpath = "//img[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:panel:processRefreshId::icon']")
    @FindBy(css = "img[title='Refresh']")
    WebElement refreshButton;

    // Flat and Heirarchical view
    @FindBy(xpath = "//input[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:sorid:_0']")
    WebElement flatListRadioButton;
    @FindBy(css = "//input[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:sorid:_1']")
    WebElement hierarchyRadioButton;

    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:panel:_vw']/div/table/tbody/tr/td[1]/a")
    WebElement actionsDropDown;
    @FindBy(xpath = "//*[@id='pt1:USma:0:MAnt1:0:pt1:panel:_vw']")
    WebElement viewDropDown;
    @FindBy(xpath = "//tr[@id='pt1:USma:0:MAnt1:0:pt1:panel:_clmns']")
    WebElement columns;
    @FindBy(xpath = "//tr[@id='pt1:USma:0:MAnt1:0:pt1:panel:_shwClmsubmnotes']")
    WebElement selectSubmissionNotes;

    @FindBy(xpath ="//*[contains(@id,'pt1:panel:result::db')]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]")
    WebElement fistScheduledtime;
    @FindBy(xpath = "//a[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:ps1::i']")
    WebElement collapseAndRestoreIcon;

    /*
     ** Process Table verifications
     */

    //    @FindBy (xpath="//table[@class='af_table_data-table af_table_data-table-VH-lines']")
//    WebElement processTable;
    @FindBy(xpath = "//table[@class='af_table_data-table af_table_data-table-VH-lines']/tbody/tr")
    WebElement actualRecord;
    @FindBy(xpath = "//span[@id='pt1:_FOr1:1:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:panel:result:0:ot1']")
    WebElement firstRecordName;

    @FindBy(xpath = "//span[text()='Import Blanket Agreements']//ancestor::td/following::td/following::td")
             //following::span/following::span")
    WebElement recordStatus_JavaJob;
    @FindBy(xpath = "//span[text()='Import Awards']//ancestor::td/following::td/following::td")
            //following::span/following::span")
    WebElement recordStatus_JavaJob1;
    @FindBy(xpath = "//span[text()='Create Mass Additions']//ancestor::td/following::td/following::td")
            //following::span/following::span")
    WebElement recordStatus_PLSQLJob;
    @FindBy(xpath = "//span[text()='Create Mass Additions Report']//ancestor::td/following::td/following::td")
            //following::span/following::span")
    WebElement recordStatus_BIPJob;
    @FindBy(xpath = "//span[text()='Create Mass Additions Report']//ancestor::td/following::td/following::td")
            //following::span/following::span")
    WebElement recordStatus_CJob;
    @FindBy(xpath = "//span[text()='Import Blanket Agreements']")
    WebElement actualProcessName_Java;
    @FindBy(xpath = "//span[text()='Create Mass Additions']")
    WebElement actualProcessName_PLSQL;
    @FindBy(xpath = "//span[text()='Create Mass Additions Report']")
    WebElement actualProcessName_BIP;
    @FindBy(xpath = "//span[text()='Import Blanket Agreements']//ancestor::td/following::td")
            //following::span")
    WebElement actualProcessID_JavaJob;
//    @FindBy(xpath = "//span[text()='Import Awards']/following::span")
//    WebElement actualProcessID_JavaJob1;
    @FindBy(xpath = "//span[text()='Create Mass Additions']//ancestor::td/following::td")
            //following::span")
    WebElement actualProcessID_PLSQLJob;
    @FindBy(xpath = "//span[text()='Create Mass Additions Report']//ancestor::td/following::td")
            //following::span")
    WebElement actualProcessID_BIPJob;
    @FindBy(xpath = "//span[text()='Create Mass Additions Report']//ancestor::td/following::td")
            //following::span")
    WebElement actualProcessID_CJob;
    @FindBy(xpath = "//*[@id='pt1:USma:0:MAnt1:0:pt1:panel:result::db']/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[6]")
    WebElement actualSubmissionNotes;
    @FindBy(xpath = "//*[@id=//*[@id='pt1:USma:0:MAnt1:0:pt1:srRssdfl:value30::content']")
    WebElement submissionTime;
    @FindBy(xpath = "//*[@id=//*[@id='pt1:USma:0:MAnt1:0:pt1:panel:result:7:ot4']")
    WebElement actualSubmissionTime;

    /*
     *** Processes search page
     */
    @FindBy(xpath = "//*[@id='pt1:USma:0:MAnt1:0:pt1:pw1']")
    WebElement popUp;
    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:subradioid:_0']")
    WebElement jobRadioButton;

    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:snpokbtnid']")
    WebElement okButton;
    @FindBy(css = "a[title='Search: Name']")
    WebElement seachProcessName;

    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::dropdownPopup::popupsearch']")
    WebElement seachLink;
    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::lovDialogId::_ttxt']")
    WebElement seachAndSelect;

    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::_afrLovInternalQueryId:value00::content']")
    WebElement name;
    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::_afrLovInternalQueryId::search']")
    WebElement seachButton;

    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2_afrLovInternalTableId:0:_afrColChild0']")
    WebElement selectedJob;
    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:selectOneChoice2::lovDialogId::ok']")
    WebElement okJobSelectButton;

    /*
       ** Jobsets
     */
    @FindBy(xpath = "//span[text()='Create Mass Additions Job Set']/following::span")
    WebElement JobSet1;
    @FindBy(xpath = "//span[text()='Create Mass Additions Job Set']/following::span/following::span")
    WebElement recordStatus_jobSet;


    public static String actualPName;
    public static String actualPID;
    public static String actualSubNotes;
    public static String actualSubTime;

    @Override
    public void isLoaded() {

        if (PageLoadHelper.waitForElementVisible(scheduleNewProcess) == null) {
            throw new TestErrorException("The scheduleNewProcess is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void selectRecord() {
        actualRecord.click();
        System.out.print("Clicked on First record ");
    }

    public String getPID(String processType) {
        String pid = null;
        if (processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))) {
            pid = actualProcessID_JavaJob.getText();
        }
        if (processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))) {
            pid = actualProcessID_PLSQLJob.getText();
        }
        if (processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))) {
            pid = actualProcessID_BIPJob.getText();
        }
        if (processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))) {
            pid = actualProcessID_CJob.getText();
        }
        return pid;
    }

    public void searchByName(String name) {
        StepReport.info("Click on Search Icon");
        DriverUtil.sleep(6000);
        expandSearchIcon.click();
        PageLoadHelper.waitForElementVisible(processName);
        StepReport.info("Process Name is :" + name);
        DriverUtil.sleep(5000);
        if (name.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))) {
            processName.sendKeys(name);
            StepReport.info("Entered process Name : " + name);
            DriverUtil.sleep(5000);
            searchButton.click();
            StepReport.info("Clicked on Search button");
            DriverUtil.sleep(8000);
            actualPName = actualProcessName_Java.getText();
        }
        if (name.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))) {
            processName.sendKeys(name);
            StepReport.info("Entered process Name : " + name);
            DriverUtil.sleep(5000);
            searchButton.click();
            StepReport.info("Clicked on Search button");
            DriverUtil.sleep(8000);
            actualPName = actualProcessName_PLSQL.getText();
        }
        if (name.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))) {
            processName.sendKeys(name);
            StepReport.info("Entered process Name : " + name);
            DriverUtil.sleep(5000);
            searchButton.click();
            StepReport.info("Clicked on Search button");
            DriverUtil.sleep(8000);
            actualPName = actualProcessName_BIP.getText();
        }
//        actualPName = actualProcessName_Java.getText();
        DriverUtil.sleep(1000);
        System.out.print("Actual Process Name : " + actualPName);

    }

    public void searchByPID(String pid) {
        StepReport.info("Click on Search Icon");
        expandSearchIcon.click();
        DriverUtil.sleep(5000);
        PageLoadHelper.waitForElementVisible(processId);
        DriverUtil.sleep(5000);
        processId.sendKeys("" + pid);
        DriverUtil.sleep(5000);
        searchButton.click();
        DriverUtil.sleep(5000);
//        actualPID= actualProcessID.getText();
    }

    public void searchBySubmissionTime() {
        StepReport.info("Click on Search Icon");
        expandSearchIcon.click();
        PageLoadHelper.waitForElementVisible(processId);
        DriverUtil.sleep(2000);
        Select select = new Select(submissionNotesDropDown);
        select.selectByValue("Before");
        String time = submissionTime.getText(); //4/16/18 7:11 AM
        System.out.print("This is time set in Search criteria");
//        submissionTime.sendKeys(""+sTime);
        DriverUtil.sleep(2000);
        searchButton.click();
        DriverUtil.sleep(2000);
//        viewDropDown.click();
//        DriverUtil.sleep(2000);
        columns.click();
        DriverUtil.sleep(2000);
        selectSubmissionNotes.click();
        DriverUtil.sleep(2000);
        actualSubTime = actualSubmissionTime.getText();
    }

    public void searchBySubmissionNotes(String sNotes) {
        StepReport.info("Click on Search Icon");
        expandSearchIcon.click();
        PageLoadHelper.waitForElementVisible(processId);
        DriverUtil.sleep(2000);
//        Select select= new Select(submissionNotesDropDown);
//        select.selectByValue("Equals");
        submissionNotesTextBox.sendKeys("" + sNotes);
        DriverUtil.sleep(2000);
        searchButton.click();
        DriverUtil.sleep(2000);
//        viewDropDown.click();
//        DriverUtil.sleep(2000);
        columns.click();
        DriverUtil.sleep(2000);
        selectSubmissionNotes.click();
        DriverUtil.sleep(2000);
        actualSubNotes = actualSubmissionNotes.getText();
    }

    public ArrayList<Integer> searchBySubmissionTime(String filter, Date date) {
        StepReport.info("Click on Search Icon");
        expandSearchIcon.click();
        PageLoadHelper.waitForElementVisible(processId);
        processId.sendKeys("");
        searchButton.click();
        ArrayList<Integer> allProcessIds = null;
        return allProcessIds;
    }

    public ArrayList<String> searchBySubmissionNotes(String filter, String partialNote) {
        StepReport.info("Inside ESSUIMonitorPage.searchBySubmissionNotes()");
        expandSearchIcon.click();
        PageLoadHelper.waitForElementVisible(processId);
        processId.sendKeys("");
        ArrayList<String> allProcessesNames = null;
        searchButton.click();
        return allProcessesNames;
    }

    public ESSUIProcessSearchPage submitNewProcess() {
        StepReport.info("Inside ESSUIMonitorPage.createNewProcess()");
        DriverUtil.sleep(10000);
        PageLoadHelper.waitForElementVisible(scheduleNewProcess);
        StepReport.info("Got scheduleNewProcess element");
        scheduleNewProcess.click();
        DriverUtil.sleep(5000);
        ESSUIProcessSearchPage essProcSearch = PageFactory.getPage(ESSUIProcessSearchPage.class);
        essProcSearch.isLoaded();
        return essProcSearch;
    }

    /*
     *** Resubmit Process
     */


    /*
     *** Put On Hold Process
     */
    public void putOnHoldProcess(String processType) {
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() Java Job");
            actualProcessID_JavaJob.click();
            DriverUtil.sleep(5000);
            if (putOnHold.isEnabled()) {
                putOnHold.click();
            }else{
                StepReport.info("Put on Hold Process button is not enabled");
            }
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() PLSQL Job");
            actualProcessID_PLSQLJob.click();
            DriverUtil.sleep(5000);
            if (putOnHold.isEnabled()) {
                putOnHold.click();
            }else{
                StepReport.info("Put on Hold Process button is not enabled");
            }
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() BIP Job");
            actualProcessID_BIPJob.click();
            DriverUtil.sleep(5000);
            if (putOnHold.isEnabled()) {
                putOnHold.click();
            }else{
                StepReport.info("Put on Hold Process button is not enabled");
            }
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() C Job");
            actualProcessID_CJob.click();
            DriverUtil.sleep(5000);
            if (putOnHold.isEnabled()) {
                putOnHold.click();
            }else{
                StepReport.info("Put on Hold Process button is not enabled");
            }
        }
    }


    public void releaseProcesses(String processType) {
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() Java Job");
            actualProcessID_JavaJob.click();
            DriverUtil.sleep(5000);
            if (releaseProcess.isEnabled()) {
                StepReport.info("Inside Release Process");
                releaseProcess.click();
                DriverUtil.sleep(10000);
                refreshProcessTable();
                DriverUtil.sleep(5000);
                reSelectJavaProcess();
            }else{
                StepReport.info("Release Process button is not enabled");
            }
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() PLSQL Job");
            actualProcessID_PLSQLJob.click();
            DriverUtil.sleep(5000);
            if (releaseProcess.isEnabled()) {
                StepReport.info("Inside Release Process");
                releaseProcess.click();
                DriverUtil.sleep(15000);
                refreshProcessTable();
                DriverUtil.sleep(5000);
                reSelectPLSQLProcess();
            }else{
                StepReport.info("Release Process button is not enabled");
            }
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() BIP Job");
            actualProcessID_BIPJob.click();
            DriverUtil.sleep(5000);
            if (releaseProcess.isEnabled()) {
                StepReport.info("Inside Release Process");
                releaseProcess.click();
                DriverUtil.sleep(15000);
                refreshProcessTable();
                DriverUtil.sleep(5000);
                reSelectBIPProcess();
            }else{
                StepReport.info("Release Process button is not enabled");
            }
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            StepReport.info("Inside ESSUIMonitorPage.putOnHoldProcess() C Job");
            actualProcessID_CJob.click();
            DriverUtil.sleep(5000);
            if (releaseProcess.isEnabled()) {
                StepReport.info("Inside Release Process");
                releaseProcess.click();
                DriverUtil.sleep(15000);
                refreshProcessTable();
                DriverUtil.sleep(5000);
                reSelectBIPProcess();
            }else{
                StepReport.info("Release Process button is not enabled");
            }
        }
    }

    /*
     *** Cancel Processes
     */

    public void cancelJavaProcess() {
        StepReport.info("Inside ESSUIMonitorPage.cancelProcess() Java Job");
        actualProcessID_JavaJob.click();
        DriverUtil.sleep(10000);
        if (cancelProcess.isEnabled()) {
            cancelProcess.click();
        }else{
            StepReport.info("Canceled Process button is not enabled");
        }
    }

    public void cancelPLSQLProcess() {
        StepReport.info("Inside ESSUIMonitorPage.cancelProcess() PLSQL Job");
        actualProcessID_PLSQLJob.click();
        DriverUtil.sleep(10000);
        if (cancelProcess.isEnabled()) {
            cancelProcess.click();
        }else{
            StepReport.info("Canceled Process button is not enabled");
        }
    }

    public void cancelBIPProcess() {
        StepReport.info("Inside ESSUIMonitorPage.cancelProcess() BIP Job");
        actualProcessID_BIPJob.click();
        DriverUtil.sleep(10000);
        if (cancelProcess.isEnabled()){
           cancelProcess.click();
        } else{
           StepReport.info("Canceled Process button is not enabled");
        }
      }
    public void cancelCProcess() {
        StepReport.info("Inside ESSUIMonitorPage.cancelProcess() C Job");
        actualProcessID_CJob.click();
        DriverUtil.sleep(10000);
        if (cancelProcess.isEnabled()){
            cancelProcess.click();
        } else{
            StepReport.info("Canceled Process button is not enabled");
        }
    }

    /**
     * Put on Hold processes verification
     */
    public void putOnHoldJavaProcesses() {
        StepReport.info("Inside ESSUIMonitorPage.cancelProcess() Java Job");
        actualProcessID_JavaJob.click();
        DriverUtil.sleep(5000);
        if (putOnHold.isEnabled()) {
            putOnHold.click();
        }else{
            StepReport.info("Canceled Process button is not enabled");
        }
    }

    public void putOnHoldPLSQLProcesses() {
        StepReport.info("Inside ESSUIMonitorPage.cancelProcess() PLSQL Job");
        actualProcessID_PLSQLJob.click();
        DriverUtil.sleep(5000);
        if (putOnHold.isEnabled()) {
            putOnHold.click();
        }else{
            StepReport.info("Canceled Process button is not enabled");
        }
    }

    public void putOnHoldBIPProcesses() {
        StepReport.info("Inside ESSUIMonitorPage.cancelProcess() BIP Job");
        actualProcessID_BIPJob.click();
        DriverUtil.sleep(5000);
        if (putOnHold.isEnabled()){
            putOnHold.click();
        } else{
            StepReport.info("Canceled Process button is not enabled");
        }
    }
    public void reSelectJavaProcess() {
//        searchByPID(pid);
        DriverUtil.sleep(5000);
        StepReport.info("Inside ESSUIMonitorPage.reSelectProcess()");
        actualProcessID_JavaJob.click();
    }

    public void reSelectPLSQLProcess() {
        StepReport.info("Inside ESSUIMonitorPage.reSelectProcess()");
        actualProcessID_PLSQLJob.click();
    }

    public void reSelectBIPProcess() {
        StepReport.info("Inside ESSUIMonitorPage.reSelectProcess()");
        actualProcessID_BIPJob.click();
    }

    public void reSelectCProcess() {
        StepReport.info("Inside ESSUIMonitorPage.reSelectProcess()");
        actualProcessID_CJob.click();
    }

    public void reSubmitJavaProcess(){
//        searchByPID(pid);
        actualProcessID_JavaJob.click();
        DriverUtil.sleep(5000);
        if (reSubmit.isEnabled()){
            reSubmit.click();
            StepReport.info("clicked on resubmit button");
            DriverUtil.sleep(5000);
            reSubmitWarningYes.click();
            StepReport.info("clicked on Yes button");
            DriverUtil.sleep(15000);
            refreshButton.click();
            StepReport.info("clicked on Refreshed button");
            DriverUtil.sleep(5000);
            actualProcessID_JavaJob.click();
        }
    }

    public void reSubmitPLSQLProcess(){
//        searchByPID(pid);
        actualProcessID_PLSQLJob.click();
        DriverUtil.sleep(5000);
        if (reSubmit.isEnabled()){
            reSubmit.click();
            StepReport.info("clicked on resubmit button");
            DriverUtil.sleep(5000);
            reSubmitWarningYes.click();
            StepReport.info("clicked on Yes button");
            DriverUtil.sleep(15000);
            refreshButton.click();
            StepReport.info("clicked on Refreshed button");
            DriverUtil.sleep(5000);
            actualProcessID_PLSQLJob.click();
        }
    }

    public void reSubmitBIPProcess(){
//        searchByPID(pid);
        actualProcessID_BIPJob.click();
        DriverUtil.sleep(5000);
        if (reSubmit.isEnabled()){
            reSubmit.click();
            StepReport.info("clicked on resubmit button");
            DriverUtil.sleep(5000);
            reSubmitWarningYes.click();
            StepReport.info("clicked on Yes button");
            DriverUtil.sleep(15000);
            refreshButton.click();
            StepReport.info("clicked on Refreshed button");
            DriverUtil.sleep(5000);
            actualProcessID_BIPJob.click();
        }
    }

    public void reSubmitCProcess(){
//        searchByPID(pid);
        actualProcessID_CJob.click();
        DriverUtil.sleep(5000);
        if (reSubmit.isEnabled()){
            reSubmit.click();
            StepReport.info("clicked on resubmit button");
            DriverUtil.sleep(5000);
            reSubmitWarningYes.click();
            StepReport.info("clicked on Yes button");
            DriverUtil.sleep(15000);
            refreshButton.click();
            StepReport.info("clicked on Refreshed button");
            DriverUtil.sleep(5000);
            actualProcessID_CJob.click();
        }
    }
    /*
     *** Release Process
     */


//    public ESSUIEssSRSPage searchProcess(){
//        StepReport.info("Inside ESSUIMonitorPage.searchProcess()");
//        PageLoadHelper.waitForElementVisible(seachProcessName);
//        seachProcessName.click();
//        DriverUtil.sleep(5000);
//        seachLink.click();
//        DriverUtil.sleep(5000);
//        //        seachAndSelect.click();
//        name.sendKeys("Evaluate Absences");
//        DriverUtil.sleep(5000);
//        seachButton.click();
//        DriverUtil.sleep(5000);
//        selectedJob.click();
//        DriverUtil.sleep(5000);
//        okJobSelectButton.click();
//        DriverUtil.sleep(5000);
//        okButton.click();
//        DriverUtil.sleep(5000);
//        ESSUIEssSRSPage srsPage = PageFactory.getPage(ESSUIEssSRSPage.class);
//        srsPage.isLoaded();
//        return srsPage;
//    }


    public void verifyRecordInProcessTable(String processName){


    }

    public void verifyRecordInProcessTable(int processId){


    }

    public String getRecordNameFromProcessTable(){

        return "";
    }

    public String resetFunctionality(String pName, int pid){

        return "";
    }

    public void verifyCancelFunctionality(){
//        searchByPID();
    }

    public void verifyReSubmitFunctinality(String pid){

    }

    public void refreshProcessTable(){
        StepReport.info("Clicking on Refresh button");
        refreshButton.click();
        DriverUtil.sleep(10000);
    }

//    Create Mass Additions Report 932 Succeeded 4/24/18 11:14 AM UTC APP_IMPL_CONSULTANT 4/24/18 11:14 AM UTC

    public String getRecordName(){
        String record= firstRecordName.getText();
        System.out.println("this is record value : "+ record);
        return record;
    }

    public String getJavaJobStatus(){
        System.out.println("this is record status inside getRecordStatus ");
        DriverUtil.sleep(5000);
        String recordStatus= recordStatus_JavaJob.getText();
        System.out.println("this is record status : "+ recordStatus);
        return recordStatus;
    }

    public String getCancelJobStatus(String processType){
        System.out.println("this is record status inside getRecordStatus ");
        DriverUtil.sleep(5000);
        String recordStatus= null;
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            recordStatus= recordStatus_JavaJob.getText();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            recordStatus= recordStatus_PLSQLJob.getText();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            recordStatus= recordStatus_BIPJob.getText();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            recordStatus= recordStatus_CJob.getText();
        }
        System.out.println("this is record status : "+ recordStatus);
        return recordStatus;
    }

    public String getPutOnHoldJobStatus(String processType){
        System.out.println("this is record status inside getRecordStatus ");
        DriverUtil.sleep(5000);
        String recordStatus= null;
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            recordStatus= recordStatus_JavaJob.getText();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            recordStatus= recordStatus_PLSQLJob.getText();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            recordStatus= recordStatus_BIPJob.getText();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            recordStatus= recordStatus_CJob.getText();
        }
        System.out.println("this is record status : "+ recordStatus);
        return recordStatus;
    }

    public String getPLSQLJobStatus(){
        System.out.println("this is record status inside getRecordStatus ");
        DriverUtil.sleep(5000);
        String recordStatus= recordStatus_PLSQLJob.getText();
        System.out.println("this is record status : "+ recordStatus);
        return recordStatus;
    }

    public String getBIPJobStatus(){
        System.out.println("this is record status inside getRecordStatus ");
        DriverUtil.sleep(7000);
        String recordStatus= recordStatus_BIPJob.getText();
        System.out.println("this is record status : "+ recordStatus);
        return recordStatus;
    }
    public String getCJobStatus(){
        System.out.println("this is record status inside getRecordStatus ");
        DriverUtil.sleep(7000);
        String recordStatus= recordStatus_CJob.getText();
        System.out.println("this is record status : "+ recordStatus);
        return recordStatus;
    }

    public String [] getRecord(){
        actualRecord.click();
        String record= actualRecord.getText();
        String [] recordValues= record.split("^[A-Za-z.*]");
        System.out.println("this is record value : "+ record);
        return recordValues;
    }

    public String getJobSetStatus(){
        System.out.println("this is record status inside getRecordStatus ");
        DriverUtil.sleep(5000);
        String jobSetStatus= recordStatus_jobSet.getText();
        System.out.println("this is record status : "+ jobSetStatus);
        return jobSetStatus;
    }

    public String getRecordFromProcessTable(int row, int col){

        return null;
    }

    public String getfirstScheduledTime(){
        StepReport.info("Inside getfirstScheduledTime() ");
        refreshProcessTable();
        DriverUtil.sleep(8000);
        String fScheduleTime= fistScheduledtime.getText();
        DriverUtil.sleep(2000);
        return fScheduleTime;
    }
}
