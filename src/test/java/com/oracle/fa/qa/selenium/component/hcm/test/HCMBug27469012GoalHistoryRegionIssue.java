package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups = { "bug27469012" })
public class HCMBug27469012GoalHistoryRegionIssue extends HCMBase {
    String goalName = "";

    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/06/2018", version = "1.0")
    @TestDesc(desc = "Create a goal")
    public void testCreateaGoal() {
        goalName=createGoal();
    }

    @Test(dependsOnMethods = "testCreateaGoal")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Test Approve Goal request")
    public void testApprovegoal(){
        approveAction(goalName,"FirstGoal");

    }

    @Test(dependsOnMethods = "testApprovegoal")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Test History page")
    public void testhistoryTree(){
        verifyHistoryTree(goalName,"Goal");

    }


}