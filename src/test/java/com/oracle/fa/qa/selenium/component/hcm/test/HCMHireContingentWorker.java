package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMHireContingentWorker extends HCMBase{
    String empName="";

    @Test
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Create Employee Hire Rule")
    public void testIsHireAnEmployeeRuleConfigured() {

            configureEmployeeHireRule("Add Contingent Worker","managementhierarchy","");

    }
    @Test(dependsOnMethods = "testIsHireAnEmployeeRuleConfigured")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = enterContingentWorkerDetails("Contingent Worker");

    }
    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve Employee Hire at Level1")
    public void approveHireLevel1(){
        firstLevelAction("Approve","sballard", empName);

    }
    @Test(dependsOnMethods = "approveHireLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve Employee Hire at Level2")
    public void approveHireLevel2(){
        secondLevelAction("Approve","byarmouth", empName);

    }
    @Test(dependsOnMethods = "approveHireLevel2")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "check if Employee Hire transaction is in completed status")
    public void checkTransactionStatus(){
        checkTransactionStatus("Add Contingent Worker","Completed", empName);

    }

}
