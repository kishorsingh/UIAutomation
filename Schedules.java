package com.oracle.fa.qa.selenium.component.ess.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Schedules extends BasePageObject<Schedules> {

    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:r1:0:r1:requestBtns:advButton']")
//    @FindBy(xpath="pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:r1:0:r1:requestBtns:basic2Button']")
    WebElement advanceButton;
    @FindBy( xpath="//div[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:scheduleTab11::ti']")
    WebElement scheduleTab;
    @FindBy(xpath="//input[contains(@id, 'advRequestBody:selectOneRadio1:_0')]")  //"pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:selectOneRadio1:_0")
    WebElement noScheduleRadioButton;
    @FindBy(xpath="//input[contains(@id, 'advRequestBody:selectOneRadio1:_1')]") //*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:selectOneRadio1:_1']")
    //label[text()='Using a schedule']")
    WebElement scheduleRadioButton;
    @FindBy(xpath="//a[@title='Select Date and Time']")
    WebElement clickOnCalendar;
    @FindBy(xpath="//a[@title='Next Month']")
    WebElement onceScheduleNextMonth;
    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:schRqRn:1:startdate::pop::dlg::ok']")
    WebElement scheduleOKButton;
    @FindBy(xpath="//*[@id='pt1:_FOr1:1:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:r1:0:r1:advRequestBody:schRqRn:1:startdate::pop::dlg::cd::cg']/tbody/tr[2]/td[1]]")
    WebElement firstDayOfDisplayedCalendar;

    @FindBy(xpath="//*[contains(@id,'selectOneChoice1::content')]")
    WebElement frequency;
    @FindBy(xpath="//*[text()='Manage Times']")
    WebElement manageButton;
    @FindBy(xpath="//div[contains(@id,'panelCollection1:table1::db')]//table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]")
    WebElement firstTime;

             /**
              * Once frequency
              **/
    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
    WebElement startDate;
    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
    WebElement endDate;

            /**
            * Hourly / Minutely frequency
             **/
    @FindBy( xpath="//a[@title='increment'][2]")
    WebElement incrementMinute;
    @FindBy( xpath="//*[contains(@id,'hours::decrement')]")
    WebElement decrementHours;
    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
    WebElement startDateHM;
    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
    WebElement endDateHM;

            /**
             * Daily frequency
             **/
    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
    WebElement startDateD;
    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
    WebElement endDateD;

            /**
            * Weekly frequency
            **/
    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
    WebElement startDateW;
    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
    WebElement endDateW;

           /**
             * Monthly frequency
           **/
    @FindBy(xpath="//*[contains(@id,'selectManyCheckbox20::content')]/div[3]/span/label")
    WebElement thirdWeekOfMonth;
    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
    WebElement startDateM;
    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
    WebElement endDateM;

            /**
             * Yearly frequency
            **/
    @FindBy(xpath="//*[contains(@id,'electManyCheckbox15::content')]/div[2]/span/label")
    WebElement fifthMonthOfYear;
    @FindBy(xpath ="//*[contains(@id,'startdate::content')]")
    WebElement startDateY;
    @FindBy(xpath ="//*[contains(@id,'enddate::content')]")
    WebElement endDateY;

    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(advanceButton) == null) {
            throw new TestErrorException("The approvalRules field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void addSchedule(){
        DriverUtil.sleep(5000);
        StepReport.info("adding schedule()");
        advanceButton.click();
        DriverUtil.sleep(2000);
        scheduleTab.click();
        DriverUtil.sleep(3000);
        scheduleRadioButton.click();
        DriverUtil.sleep(5000);
    }

    public void addOnceSchedule(){
        StepReport.info("adding addOnceSchedule()");
        addSchedule();
//        DriverUtil.sleep(4000);
        startDate.clear();
        DriverUtil.sleep(2000);
        startDate.sendKeys("5/15/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);
        Select sel = new Select(frequency);
//        sel.selectByIndex(2);
//        DriverUtil.sleep(5000);
//        sel.selectByIndex(0);
//        DriverUtil.sleep(5000);

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
        StepReport.info("adding addMinutelySchedule()");
        addSchedule();
        Select sel = new Select(frequency);
        sel.selectByIndex(1);
        DriverUtil.sleep(5000);
        startDateHM.clear();
        DriverUtil.sleep(2000);
        startDateHM.sendKeys("5/15/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);
        endDateHM.sendKeys("5/16/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);

    }

    public void addDailySchedule(){
        StepReport.info("adding addMinutelySchedule()");
        addSchedule();
        Select sel = new Select(frequency);
        sel.selectByIndex(1);
        DriverUtil.sleep(5000);
        startDateHM.clear();
        DriverUtil.sleep(2000);
        startDateHM.sendKeys("5/15/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);
        endDateHM.sendKeys("5/16/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);
    }

    public void addWeeklySchedule(){
        StepReport.info("adding addWeeklySchedule()");
        addSchedule();
        DriverUtil.sleep(4000);
        startDateW.clear();
        DriverUtil.sleep(2000);
        startDateW.sendKeys("5/15/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);
        Select sel = new Select(frequency);
        sel.selectByIndex(2);
        DriverUtil.sleep(5000);
        sel.selectByIndex(0);
        DriverUtil.sleep(5000);
    }

    public void addMonthlySchedule(){
        StepReport.info("adding addMonthlySchedule()");
        addSchedule();
        DriverUtil.sleep(4000);
        startDateM.clear();
        DriverUtil.sleep(2000);
        startDateM.sendKeys("5/15/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);
        Select sel = new Select(frequency);
        sel.selectByIndex(2);
        DriverUtil.sleep(5000);
        sel.selectByIndex(0);
        DriverUtil.sleep(5000);
    }

    public void addYearlySchedule(){
        StepReport.info("adding addYearlySchedule()");
        addSchedule();
        DriverUtil.sleep(4000);
        startDateY.clear();
        DriverUtil.sleep(2000);
        startDateY.sendKeys("5/15/23 3:06 PM GMT+00:00");
        DriverUtil.sleep(2000);
        Select sel = new Select(frequency);
        sel.selectByIndex(2);
        DriverUtil.sleep(5000);
        sel.selectByIndex(0);
        DriverUtil.sleep(5000);
    }

    public String getStartDate(){
        String strtDate=startDateW.getText();
        return strtDate;
    }

    public void setStartDate(String strtDate){
        String startdate=startDateW.getText();

    }
}
