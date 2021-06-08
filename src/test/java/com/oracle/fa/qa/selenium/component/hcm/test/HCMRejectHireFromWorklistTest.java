package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class HCMRejectHireFromWorklistTest extends HCMBase {
    String empName="";

    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/18/2018", version = "1.0")
    @TestDesc(desc = "Hire an employee")
    public void testConfigureHireAnEmpRule(){

            configureRule("Hire an Employee","managementhierarchy","");

    }
    @Test(dependsOnMethods = "testConfigureHireAnEmpRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/18/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = hireEmployee("Employee");

    }

    //@Test
    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/18/2018", version = "1.0")
    @TestDesc(desc = "Reject Employee Hire at Level1")
    public void rejectHirelevel1() {
           firstLevelWorklist("Reject", "sballard",empName);
    }


    @Test(dependsOnMethods = "rejectHirelevel1")
    @TestAuthor(createdBy = "pkanlet", createdOn = "5/23/2018", version = "1.0")
    @TestDesc(desc = "Verify Reject action")
    public void verifyRejectAction(){
        String str="Rejected: Employee Hired";
        verifyActionBellNotif(str,"");
    }
}




