package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMRequestMoreInfoTest extends HCMBase{
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
        firstLevelAction("Approve","sballard", empName);

    }
    @Test(dependsOnMethods = "approveHireLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Request More Info at Level 2")
    public void requestMoreInfoLevel2(){
        secondLevelAction("Request Information...","byarmouth",empName);

    }
    @Test(dependsOnMethods = "requestMoreInfoLevel2")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Resubmit by providing information")
    public void resubmitRequestWithInfo(){

        firstLevelAction("Submit Information","sballard",empName);
    }
    @Test(dependsOnMethods = "resubmitRequestWithInfo")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve employee Hire at Level2")
    public void approveAtLevel2PostInformation(){
        secondLevelAction("Approve","byarmouth",empName);
    }

/*  VENKI: Don't have an option to approve, transaction is assigned to vmoss.
    @Test(dependsOnMethods = "approveAtLevel2PostInformation", groups = { "srg" })
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "approve post more Info Requested")
    public void approveAtLevel1(){

        firstLevelAction("Approve","sballard");
    }
*/


}
