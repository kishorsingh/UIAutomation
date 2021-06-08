package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMDelegateReassignTest extends HCMBase {
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
    @TestDesc(desc = "Reassign to vmoss user")
    public void reassignAtLevel1(){
        firstLevelAction("Reassign...","vmoss", empName);

    }
    @Test(dependsOnMethods = "reassignAtLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Delegate at Level0 as root User")
    public void delegateAtLevel0(){
        rootUserAction("Delegate","byarmouth", empName);

    }
    @Test(dependsOnMethods = "delegateAtLevel0")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve at Second Level")
    public void approveAtLevel2PostDelegation(){
        secondLevelAction("Approve","byramouth", empName);
    }

}


