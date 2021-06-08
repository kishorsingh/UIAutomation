package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMChangeLocationTest extends HCMBase{
    public static String locNumber="";
    String empName="";

    @Test
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Add Location employee rule")
    public void testChangeLocationRule(){
        configureRule("Change Location","managementhierarchy","");
    }


    @Test(dependsOnMethods = "testChangeLocationRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test add Location")
    public void testCreateLocationrule() {
        //#Pragathi : Make sure bypass isn't enabled for create location rule in configure rule page
        //Its self approval configured by default, so not changing anything in configure rule page.
        configureBypassApproval("Create Location");

    }

    @Test(dependsOnMethods = "testCreateLocationrule")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test add Location")
    public void testAddLocation() {
        //#Pragathi : Correction added
        addLocation();

    }

    @Test(dependsOnMethods = "testAddLocation")
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
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/26/2018", version = "1.0")
    @TestDesc(desc = "Test change Location")
    public void testChangeLocation(){
        locNumber=changeLocation(empName);
        StepReport.info("Change location request is created : " + locNumber );
    }


    @Test(dependsOnMethods = "testChangeLocation")
    @TestDesc(desc = "Test Approve add Location")
    public void testApproveAddLocation() {
        approveAction(locNumber,"First");
    }

    @Test(dependsOnMethods = "testApproveAddLocation")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Test Approve Termination")
    public void testVerifyChangelocation(){
        //verifyTransactionConsole("ChecklistAction-Change Location","Completed");

    }


}
