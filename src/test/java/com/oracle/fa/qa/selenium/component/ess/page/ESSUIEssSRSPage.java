package com.oracle.fa.qa.selenium.component.ess.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.sql.Driver;
import java.util.List;
import java.util.ArrayList;

public class ESSUIEssSRSPage extends BasePageObject<ESSUIEssSRSPage> {

    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:srspw1::_ttxt']")
    WebElement srsPageTitle;
    @FindBy(xpath="//*[contains(@id,'reqDesc::content')]")
    WebElement submitNotes;
    @FindBy(xpath = "//button[text()='Process Option']")
    WebElement processOptionsButton;
    @FindBy(xpath="//span[text()='Advanced']")
    WebElement advanceButton;
    @FindBy(xpath="//div[contains(@id,'requestBtns:submitButton')]")
            //@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:requestBtns:submitButton']")
    WebElement submitButton;
    @FindBy(xpath="//*[text()='Sub']")
    WebElement submitButton1;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:requestBtns:cancelButton']")
    WebElement cancelButton;

    @FindBy( xpath="//a[text()='Schedule']")
    WebElement scheduleTab;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:selectOneRadio1:_0']")
    WebElement noScheduleRadioButton;
    @FindBy(xpath="//label[text()='Using a schedule']")
    WebElement scheduleRadioButton;

    @FindBy( xpath="//a[text()='Put On Hold']")
    WebElement putOnHold;

    /*
       * Schedules
     */
    @FindBy(xpath="//*[text()='Manage Times']")
    WebElement manageButton;
    @FindBy(xpath="//*[contains(@id,'panelCollection1:table1::db')]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]/span")
    WebElement firstScheduledTime;

    /*** Once Schedule ***/
    @FindBy(xpath="//a[@title='Select Date and Time']")
    WebElement clickOnCalendar;
    @FindBy(xpath="//a[@title='Next Month']")
    WebElement onceScheduleNextMonth;
    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:schRqRn:1:startdate::pop::dlg::ok']")
    WebElement scheduleOKButton;
    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:schRqRn:1:startdate::pop::dlg::cd::cg']/tbody/tr[2]/td[1]]")
    WebElement firstDayOfDisplayedCalendar;
//    @FindBy( xpath="//a[@title='increment']")
//    WebElement incrementMinute;
    @FindBy(xpath="//*[contains(@id,'selectOneChoice1::content')]")
    WebElement frequency;
    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
    WebElement startDate;

    /**
     * Hourly / Minutely frequency
     **/
    @FindBy( xpath="//*[contains(@id,'minutes::increment')]")
    WebElement incrementMinute;
    @FindBy( xpath="//*[contains(@id,'hours::decrement')]")
    WebElement decrementHours;
//    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
//    WebElement startDateHM;
    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
    WebElement endDate;

    /**
     * Daily frequency
     **/
//    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
//    WebElement startDateD;
//    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
//    WebElement endDateD;

    /**
     * Weekly frequency
     **/
//    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
//    WebElement startDateW;
//    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
//    WebElement endDateW;

    /**
     * Monthly frequency
     **/
    @FindBy(xpath="//*[contains(@id,'schRqRn:1:selectManyCheckbox20:_1')]/following::label")
    WebElement thirdWeekOfMonth;
    @FindBy(xpath="//*[contains(@id,'schRqRn:1:selectManyCheckbox19:_5')]/following::label")
    WebElement dayOfWeek;

    /**
     * Yearly frequency
     **/
    @FindBy(xpath="//*[contains(@id,'schRqRn:1:selectManyCheckbox15:_1')]/following::label")
    WebElement fifthMonthOfYear;
    @FindBy(xpath="//*[contains(@id,'schRqRn:1:selectManyCheckbox10:_1')]/following::label")
    WebElement dateOfMonth;
//    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
//    WebElement startDateY;
//    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
//    WebElement endDateY;


    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:startdate::glyph']")
    WebElement startDateCalendar;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:startdate::glyph']")
    WebElement startDateMonth;
    @FindBy(xpath="//*[@id='pt1:USma:0:MAnt1:0:pt1:r1:0:r1:advRequestBody:schRqRn:1:startdate::pop::dlg::cd::ys::increment']")
    WebElement startDateIncYear;
    @FindBy(xpath="//*[@id='pt1:USma:0:MAnt1:0:pt1:r1:0:r1:advRequestBody:schRqRn:1:startdate::pop::dlg::cd::cg']/tbody/tr[2]/td[1]")
    WebElement SelectedStartDate;

    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:hours::content']")
    WebElement everyHourly;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:minutes::content']")
    WebElement everyMinutely;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:enddate::glyph']")
    WebElement endDateCalendar;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:enddate::pop::dlg::cd::hs::content']")
    WebElement endDateCalendarHour;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:enddate::pop::dlg::cd::ap:_0']")
    WebElement calendarAM;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:0:r1:advRequestBody:schRqRn:1:enddate::pop::dlg::cd::ap:_1']")
    WebElement calendarPM;

    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:requestBtns:confirmationPopup:confirmSubmitDialog::ok']")
    WebElement OKButton;
    @FindBy(xpath ="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBtns:confirmationPopup:confirmSubmitDialog::ok']")
    WebElement OKButton2;
    @FindBy(xpath ="//td[@class='af_dialog_footer-content']//button[contains(@id,'confirmationPopup:confirmSubmitDialog::ok')]")
    WebElement OKButton1;

    @FindBy(xpath="//span[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:requestBtns:confirmationPopup:pt_ol1']")
    WebElement confirmMessage;

//    Specific to BIP Jobs-- Create Mass Additions Report
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:outputTab::disAcr']")
    WebElement outputTag;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:basicReqBody:paramDynForm_ATTRIBUTE1_ATTRIBUTE1::content']")
    WebElement bookField;

//    Specific to Java job--
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:basicReqBody:paramDynForm_ATTRIBUTE1_ATTRIBUTE1::content']")
    WebElement accDate;
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:basicReqBody:paramDynForm_ATTRIBUTE2_ATTRIBUTE2::content']")
    WebElement assetBook;

//    Specific to BIP job--
    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:basicReqBody:paramDynForm_ATTRIBUTE1_ATTRIBUTE1::content']")
    WebElement book;

    /*
     *  JobSets related
    */
    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:subradioid::content']/fieldset/span[2]/label")
    WebElement selectJobSet;

    /*
     * Specific to jobset " Create Mass Additions Job Set"
     */
    @FindBy(xpath = "//span[text()='Update Asset Tracking']/following::span")
    WebElement firstJob;
    @FindBy(xpath="//*[contains(@id,'aTTRIBUTE1Id::content')]")
    WebElement firstAttribute1;
    @FindBy(xpath="//*[contains(@id,'id1::content')]")
    WebElement secondAttribute1;
    @FindBy(xpath="//*[contains(@id,'id2::content')]")
    WebElement thirdAttribute1;

    @FindBy(xpath = "//span[text()='Create Mass Additions']/following::span")
    WebElement secondJob;
    @FindBy(xpath="//*[contains(@id,'paramDynForm_ATTRIBUTE1_ATTRIBUTE1::content')]")
    WebElement firstAttribute2;
    @FindBy(xpath="//*[contains(@id,'paramDynForm_ATTRIBUTE2_ATTRIBUTE2::content')]")
    WebElement secondAttribute2;

    @FindBy(xpath = "//span[text()='Update Asset Type']/following::span")
    WebElement thirdJob;
    @FindBy(xpath="//*[@title='Search: Ledger']")
    WebElement firstAttribute3;
    @FindBy(xpath="//*[contains(@id,'soc1::content')]")
    WebElement secondAttribute3;

    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(submitButton1) == null) {
            throw new TestErrorException("The approvalRules field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void verifySRSPageTitle(){
        StepReport.info("verifying verifySRSPageTitle()");
       String title= srsPageTitle.getText();
        Assert.assertEquals(title, "Process Details");
    }

    public void verifyBasicParameter(){
        StepReport.info("verifying verifyBasicParameter()");

    }
    public void verifyAdvanceParameter(){
        StepReport.info("verifying verifyAdvanceParameter()");

    }

    public void addNotes(){
        DriverUtil.sleep(2000);
        StepReport.info("adding notes at addNotes()");
        submitNotes.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("submissionNotes"));
    }

    public void addSchedule(){
        StepReport.info("adding schedule()");
        advanceButton.click();
        DriverUtil.sleep(2000);
        scheduleTab.click();
        DriverUtil.sleep(3000);
        scheduleRadioButton.click();
        DriverUtil.sleep(3000);
    }

    public void addOnceSchedule() {
        StepReport.info("adding addOnceSchedule()");
        addSchedule();
        DriverUtil.sleep(4000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("startDate"));
        DriverUtil.sleep(2000);
        Select sel = new Select(frequency);
        sel.selectByIndex(2);
        DriverUtil.sleep(5000);
        sel.selectByIndex(0);
        DriverUtil.sleep(5000);

    }

    public void addOnceSchedule1(){
        StepReport.info("adding addOnceSchedule()");
        addSchedule();
//        DriverUtil.sleep(4000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("startDate"));
        DriverUtil.sleep(2000);


/***  Working with Calendar--
        clickOnCalendar.click();
        DriverUtil.sleep(5000);
        String path = "//a[@title='increment']";
        List<WebElement> inc = findElements(By.xpath(path));
        DriverUtil.sleep(2000);
        inc.get(6).click();
***/
    }

    public void addHMSchedule(){
        StepReport.info("adding addHMSchedule()");
        addSchedule();
        Select sel = new Select(frequency);
        sel.selectByIndex(1);
        DriverUtil.sleep(3000);
        decrementHours.click();
        DriverUtil.sleep(2000);
        for(int i=0; i <5; i++){
            DriverUtil.sleep(1000);
            incrementMinute.click();
        }
        DriverUtil.sleep(5000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("startDate"));
        DriverUtil.sleep(2000);
        endDate.clear();
        DriverUtil.sleep(2000);
        endDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("endDate"));
        DriverUtil.sleep(3000);
//        manageButton.click();
//        DriverUtil.sleep(3000);
//        System.out.print("This is first shceduled time: " + firstScheduledTime.getText());
//        DriverUtil.sleep(2000);

    }

    public void addDailySchedule(){
        StepReport.info("adding addDailySchedule()");
        addSchedule();
        Select sel = new Select(frequency);
        sel.selectByIndex(2);
        DriverUtil.sleep(4000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("startDate"));
        DriverUtil.sleep(2000);
        endDate.clear();
        DriverUtil.sleep(2000);
        endDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("endDate"));
        DriverUtil.sleep(2000);
    }

    public void addWeeklySchedule(){
        StepReport.info("adding addWeeklySchedule()");
        addSchedule();
        Select sel = new Select(frequency);
        sel.selectByIndex(3);
        DriverUtil.sleep(5000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("startDate"));
        DriverUtil.sleep(2000);
        endDate.clear();
        DriverUtil.sleep(2000);
        endDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("endDate"));
        DriverUtil.sleep(2000);
    }

    public void addMonthlySchedule(){
        StepReport.info("adding addMonthlySchedule()");
        addSchedule();
        Select sel = new Select(frequency);
        sel.selectByIndex(4);
        DriverUtil.sleep(5000);
        thirdWeekOfMonth.click();
        DriverUtil.sleep(2000);
        dayOfWeek.click();
        DriverUtil.sleep(2000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("startDate"));
        DriverUtil.sleep(2000);
        endDate.clear();
        DriverUtil.sleep(2000);
        endDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("endDate"));
        DriverUtil.sleep(2000);
    }

    public void addYearlySchedule(){
        StepReport.info("adding addYearlySchedule()");
        addSchedule();
        Select sel = new Select(frequency);
        sel.selectByIndex(5);
        DriverUtil.sleep(5000);
        fifthMonthOfYear.click();
        DriverUtil.sleep(2000);
        dateOfMonth.click();
        DriverUtil.sleep(2000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("startDate"));
        DriverUtil.sleep(2000);
        endDate.clear();
        DriverUtil.sleep(2000);
        endDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("endDate"));
        DriverUtil.sleep(2000);
    }

    public String getStartDate(){
        String strtDate=startDate.getText();
        return strtDate;
    }

    public void setStartDate(String strtDate){
        String startdate=startDate.getText();

    }

    public void addOutput(){

    }
    public void changeBasicParameter(){

    }

    /**
     * submitJob() method is designed for the verifying execution of different jobs
     * @return object of ESSUIMonitorPage
     */
    public static String processIdFromConfirmationPopUp;
    public ESSUIMonitorPage submitJob(){
        addNotes();
        DriverUtil.sleep(2000);
        submitButton.click();
        DriverUtil.sleep(5000);
        processIdFromConfirmationPopUp = getSubmittedPID();
        System.out.print("This is PID--" + processIdFromConfirmationPopUp);
        OKButton.click();
        DriverUtil.sleep(1000);
        ESSUIMonitorPage essuiMonitorPage = PageFactory.getPage(ESSUIMonitorPage.class);
        essuiMonitorPage.isLoaded();
        return essuiMonitorPage;
    }

    public ESSUIMonitorPage submitJob1(){
        addNotes();
        DriverUtil.sleep(2000);
        submitButton1.click();
        DriverUtil.sleep(5000);
        OKButton2.click();
        DriverUtil.sleep(1000);
        ESSUIMonitorPage essuiMonitorPage = PageFactory.getPage(ESSUIMonitorPage.class);
        essuiMonitorPage.isLoaded();
        return essuiMonitorPage;
    }

    /**
     * submitJobForCancellation() method is designed for the verifying cancellation feature for different jobs
     * @return object of ESSUIMonitorPage
     */
    public ESSUIMonitorPage submitJobwithOnceSchedule(){
        StepReport.info("Inside submitJobForCancellation() ");
        Schedules sch = new Schedules();
        sch.addOnceSchedule();
//        addOnceSchedule();
        addNotes();
        DriverUtil.sleep(6000);
        StepReport.info("Clicking on Submit button");
        submitButton1.click();
        DriverUtil.sleep(4000);
        System.out.print("This is PID--" + processIdFromConfirmationPopUp);
        StepReport.info("Clicking on Ok button");
        DriverUtil.sleep(6000);
        OKButton1.click();
        DriverUtil.sleep(3000);
        ESSUIMonitorPage essuiMonitorPage = PageFactory.getPage(ESSUIMonitorPage.class);
        essuiMonitorPage.isLoaded();
        return essuiMonitorPage;
    }

    /**
     * submitJobSet() method is designed for the submitting different jobsets
     * @return object of ESSUIMonitorPage
     */
    public ESSUIMonitorPage submitJobSet(){
        StepReport.info("Inside submitJobSet() ");
        if(FrameworkContext.getInstance().getTestConfigParams().getString("JobSet1").trim().equalsIgnoreCase("Create Mass Additions Job Set")){
            DriverUtil.sleep(8000);
            firstJob.click();
            DriverUtil.sleep(5000);
            Select sel = new Select(firstAttribute1);
            sel.selectByIndex(0);
            DriverUtil.sleep(2000);
            secondAttribute1.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("Date1"));
            DriverUtil.sleep(2000);
            thirdAttribute1.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("Date2"));
            DriverUtil.sleep(2000);

            secondJob.click();
            DriverUtil.sleep(5000);
            firstAttribute2.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("Date1"));
            DriverUtil.sleep(2000);
            Select sel1 = new Select(secondAttribute2);
            sel1.selectByIndex(1);
            DriverUtil.sleep(2000);

            thirdJob.click();
            DriverUtil.sleep(5000);
            Select sel2 = new Select(firstAttribute3);
            sel2.selectByIndex(0);
            DriverUtil.sleep(2000);
            Select sel3 = new Select(secondAttribute3);
            sel3.selectByIndex(0);
            DriverUtil.sleep(2000);
        }
        addNotes();

        DriverUtil.sleep(3000);
        StepReport.info("Clicking on Submit button");
        submitButton1.click();
        DriverUtil.sleep(4000);
        System.out.print("This is PID--" + processIdFromConfirmationPopUp);
        StepReport.info("Clicking on Ok button");
        DriverUtil.sleep(6000);
        OKButton1.click();
        DriverUtil.sleep(3000);
        ESSUIMonitorPage essuiMonitorPage = PageFactory.getPage(ESSUIMonitorPage.class);
        essuiMonitorPage.isLoaded();
        return essuiMonitorPage;
    }

    public String getEntryFromDropDown(WebElement locator, String fieldValue) {
        ArrayList<WebElement> list = new ArrayList<WebElement>();
//         list= bookField.findElements(By.);
//         findElements(By.xpath(locator.toString())
//    }
        return null;
    }

    /**
     * getSubmittedPID() method is designed to return confirmation id after successful submittion of different jobs
     * @return object of ESSUIMonitorPage
     */
        public String getSubmittedPID(){
        StepReport.info("inside getSubmittedPID()");
        System.out.print(confirmMessage);
        String [] confMessage = confirmMessage.getText().split(" ");
        System.out.print("This is confirmation message after clicking on Submit button " + confirmMessage);
        String pId=confMessage[1];
        StepReport.info("This is Pid : " + pId);
        return pId;
    }

    public void setJavaJobParam(){
//        accDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("accDate"));
//        Select sel = new Select(assetBook);
//        sel.selectByIndex(1);

    }

    public void setPLSQLJobParam(){
        accDate.sendKeys(FrameworkContext.getInstance().getTestConfigParams().getString("accDate"));
        Select sel = new Select(assetBook);
        sel.selectByIndex(1);
    }

    public void setBIPJobParam(){
        Select sel = new Select(book);
        sel.selectByIndex(4);
    }

    public void setCJobParam(){
        Select sel = new Select(book);
        sel.selectByIndex(4);
    }
}
