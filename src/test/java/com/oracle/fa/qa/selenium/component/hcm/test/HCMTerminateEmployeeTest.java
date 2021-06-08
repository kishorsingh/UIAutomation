package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMTerminateEmployeeTest extends HCMBase{
    String empName="";

    @Test
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test create Terminate employee rule")
    public void testCreateTerminationRule()
    {
        configureRule("Terminate","managementhierarchy","");
    }

    @Test(dependsOnMethods = "testCreateTerminationRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Verify if hireEmployee rule is pre-configured")
    public void testConfigureAutoHireRule()
    {
          deleteHireEmpRule("Hire an Employee","SelfApproval","");
            }

    @Test(dependsOnMethods = "testConfigureAutoHireRule")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = hireEmployee("Employee");

    }

    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Terminate Employee")
    public void testTerminateAnEmployee() {
        terminateEmployee(empName);

    }

    @Test(dependsOnMethods = "testTerminateAnEmployee")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Approve Termination")
    public void testApproveTermination(){
        approveAction(empName,"First");

    }
}
