package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMEmployeeHireSuspendWithdrawTest extends HCMBase{
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
    @TestDesc(desc = "Suspend request at Level2")
    public void suspendAtLevel2(){
        secondLevelAction("Suspend","byarmouth", empName);

    }
    @Test(dependsOnMethods = "suspendAtLevel2")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Resume request at Level2")
    public void resumeAtLevel2(){
        secondLevelAction("Resume","byarmouth", empName);

    }

    @Test(dependsOnMethods = "resumeAtLevel2")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Withdraw as Root User")
    public void withdrawAtLevel1(){
        rootUserAction("Withdraw","vmoss", empName);
    }


}
