package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;

@Test(groups = { "bug27568308" })
public class HCMBug27568308ChangeMgrWithdraw extends HCMBase{
    public static String personNumber="";
    String empName="";

    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "06/22/2018", version = "1.0")
    @TestDesc(desc = "Edit Change manager rule")
    public void testChangeManagerRule(){
        configureRule("Change Manager","managementhierarchy","");
    }

    @Test(dependsOnMethods = "testChangeManagerRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/03/2018", version = "1.0")
    @TestDesc(desc = "Create Employee Hire Rule")
    public void testIsHireAnEmployeeRuleConfigured() {

            deleteHireEmpRule("Hire an Employee","SelfApproval","");
        }



    @Test(dependsOnMethods = "testIsHireAnEmployeeRuleConfigured")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/03/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
      empName = hireEmployee("Employee");

    }
    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "pkanlet", createdOn = "06/22/2018", version = "1.0")
    @TestDesc(desc = "Test Change manager")
    public void testChangeManager() {
       // personNumber=changeManager(empName);
        personNumber=changeManager(empName);
        StepReport.info("Change Manager request is created for Person Number: " + personNumber );
    }

    @Test(dependsOnMethods = "testChangeManager")
    @TestAuthor(createdBy = "pkanlet", createdOn = "7/03/2018", version = "1.0")
    @TestDesc(desc = "Withdraw Change manager request")
    public void testWithdrawChgManagerReq() {
        rootUserActionPromote("Withdraw","vmoss", personNumber);

    }

     @Test(dependsOnMethods = "testWithdrawChgManagerReq")
    @TestAuthor(createdBy = "pkanlet", createdOn = "7/03/2018", version = "1.0")
    @TestDesc(desc = "Test Change Manager")
    public void testVerifyHistoryForWithdrawn(){
        verifyHistoryTreeforChangmgr(empName);

    }
}


