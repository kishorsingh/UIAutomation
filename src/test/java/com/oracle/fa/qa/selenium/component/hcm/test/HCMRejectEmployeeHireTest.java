package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMRejectEmployeeHireTest extends HCMBase {
    String empName="";

    @Test
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Create Employee Hire Rule")
    public void testIsHireAnEmployeeRuleConfigured() {

        if (!isHireEmployeeRuleConfigured) {
            configureEmployeeHireRule("Hire an Employee", "managementhierarchy", "");
        }
        System.out.println("Hire an Employee rule configured: "+isHireEmployeeRuleConfigured);

    }


    @Test(dependsOnMethods = "testIsHireAnEmployeeRuleConfigured")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = hireEmployee("Employee");

    }
    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve Employee Hire at Level1")
    public void approveHireLevel1(){
        firstLevelAction("Approve","sballard",empName);

    }
    @Test(dependsOnMethods = "approveHireLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Reject Employee Hire at Level 2")
    public void rejectHireLevel2(){
        secondLevelAction("Reject","byarmouth",empName);

    }
   /* VENKI: Issues with txn complete status
    @Test(dependsOnMethods = "rejectHireLevel2", groups = { "srg" })
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "check if Employee Hire transaction is in completed status")
    public void checkIfTransactionIsCompleted(){
        checkTransactionStatus("Hire an Employee","Completed");


    }*/



}

