package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class ExecuteJobsWithSchedulesTest extends ESSBase{

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "07/10/2019", version = "1.0")
    @TestDesc(desc = "Execute Java Job with Once Schedule")
    public void testJobWithOnceSchedule() {
        //Login as User1
        executeJobsWithSchedule(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency1"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "07/10/2019", version = "1.0")
    @TestDesc(desc = "Execute Java Job with HM Schedule")
    public void testJobWithHMSchedule() {
        //Login as User1
        executeJobsWithSchedule(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency2"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "07/10/2019", version = "1.0")
    @TestDesc(desc = "Execute Java Job with Daily Schedule")
    public void testJobWithDailySchedule() {
        //Login as User1
        executeJobsWithSchedule(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency3"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "07/10/2019", version = "1.0")
    @TestDesc(desc = "Execute Java Job with Weekly Schedule")
    public void testJobWithWeeklySchedule() {
        //Login as User1
        executeJobsWithSchedule(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency4"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "07/10/2019", version = "1.0")
    @TestDesc(desc = "Execute Java Job with Monthly Schedule")
    public void testJobWithMonthlySchedule() {
        //Login as User1
        executeJobsWithSchedule(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency5"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "07/10/2019", version = "1.0")
    @TestDesc(desc = "Execute Java Job with Yearly Schedule")
    public void testJobWithYearlySchedule() {
        //Login as User1
        executeJobsWithSchedule(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency6"));
    }

}
