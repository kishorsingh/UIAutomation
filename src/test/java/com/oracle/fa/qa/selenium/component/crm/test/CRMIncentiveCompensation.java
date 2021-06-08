package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

public class CRMIncentiveCompensation extends CRMBase {

    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "05/29/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Incentive Compensation")
    public void testIncentiveCompensation() {
        awardCompensation();
        StepReport.info("Sales Territory Management Tests are completed");
    }

}
