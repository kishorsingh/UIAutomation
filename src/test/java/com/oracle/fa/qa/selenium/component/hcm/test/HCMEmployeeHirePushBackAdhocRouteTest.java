package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMEmployeeHirePushBackAdhocRouteTest extends HCMBase{
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
    @TestDesc(desc = "Approve at Level 1")
    public void approveAtLevel1(){
        firstLevelAction("Approve","sballard", empName);

    }
    @Test(dependsOnMethods = "approveAtLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Push back the request to Level 1 user")
    public void pushBackToLevel1(){
        secondLevelAction("Push Back...","byarmouth", empName);

    }
    @Test(dependsOnMethods = "pushBackToLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "use adhoc route at Level1")
    public void adhocRouteAtLevel1(){

        firstLevelAction("Adhoc Route...","vmoss", empName);
    }
    @Test(dependsOnMethods = "adhocRouteAtLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve as root User vmoss")
    public void approveAsRootUser(){
        rootUserAction("Approve","vmoss", empName);
    }

/* VENKI: Transaction is withdrawn but status is incorrect in Transaction console
    @Test(dependsOnMethods = "approveAsRootUser", groups = { "srg" })
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve as root User vmoss")
    public void verifyWorklistTrasactionState(){
        verifyTransactionStatusInWorkList("Withdrawn");
    }

*/

}
