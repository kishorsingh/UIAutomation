package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMEmployeePromotionTest extends HCMBase{
    public static String personNumber="";
    String empName="";

    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test create promote employee rule")
    public void testCreatePromotionRule(){
        configureRule("Promote", "managementhierarchy","");
    }

    @Test(dependsOnMethods = "testCreatePromotionRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Verify if hireEmployee rule is pre-configured")
    public void testConfigureAutoHireRule()
    {

            deleteHireEmpRule("Hire an Employee","SelfApproval","");
        }


    @Test(dependsOnMethods = "testConfigureAutoHireRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = hireEmployee("Employee");

    }

    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test promote Employee at Level1")
     public void testPromoteAnEmployee() {
        //personNumber = promoteEmployee(empName);
        promoteEmployee(empName);
        StepReport.info("Promotion request is created for : " + empName );

    }


    @Test(dependsOnMethods = "testPromoteAnEmployee")
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Approve promotion")
    public void testApprovePromotion(){
        approveAction(empName,"First");

    }

    @Test(dependsOnMethods = "testApprovePromotion")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "vmoss verifies the Bell Notification")
    public void verifyApproveActionBellNotification(){
        String str="Approved: Promotion for "+empName;
        verifyActionBellNotif(str,"");
    }


    /*@Test(dependsOnMethods = "testApprovePromotion")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Check status post approval")
    public void checkTransactionStatusPostApproval(){
        checkTransactionStatusPromotion(empName,"Completed");

    }*/
}
