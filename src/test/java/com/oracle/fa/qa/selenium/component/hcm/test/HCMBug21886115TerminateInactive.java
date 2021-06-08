package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "bug21886115", "participant_assignment" })
public class HCMBug21886115TerminateInactive extends HCMBase {
    String empName="";
    String NonWorkerName="";

    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Hire an employee")
    public void testIsHireAnEmployeeRuleConfigured() {
        if (!isAutoHireEmployeeRuleConfigured) {
            configureEmployeeHireRule("Hire an Employee", "SelfApproval", "");
        }
        System.out.println("Hire an Employee rule Auto approval configured: "+isAutoHireEmployeeRuleConfigured);
    }



    @Test(dependsOnMethods = "testIsHireAnEmployeeRuleConfigured")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = hireEmployee("Employee");

    }


    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Add new user in the rule")
    public void testChangeNonworkerRule(){
        configureRule("Add NonWorker","managementhierarchy", empName);

    }


    @Test(dependsOnMethods = "testChangeNonworkerRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Terminate the employee who has been added in the nonworker rule ")
    public void testTerminateAnEmployee(){
        terminateEmployee(empName);

    }

    @Test(dependsOnMethods = "testTerminateAnEmployee")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Add new user in the rule")
    public void testAddnonworker(){
        NonWorkerName=enterNonWorkerDetails("Nonworker");

    }

    @Test(dependsOnMethods = "testAddnonworker")
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/19/2018", version = "1.0")
    @TestDesc(desc = "Verify if the task is assigned to terminated user")
    public void testTerminateStatus(){
        verifyHistoryTree(NonWorkerName,"Terminate");

    }


}