package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class HCMTerminateEmpBypassApprovalTest extends HCMBase{
    String empName="";

    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Test create Terminate employee rule")
    public void testCreateTerminationRule()
    {
        configureBypassApproval("Terminate");
    }

    @Test(dependsOnMethods = "testCreateTerminationRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Verify if hireEmployee rule is configured for self approval")
    public void testConfigureAutoHireRule()
    {
        //We need selfapproval here, so that the hired employee can be terminated immediately
        deleteHireEmpRule("Hire an Employee","SelfApproval","");
    }

    @Test(dependsOnMethods = "testConfigureAutoHireRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireEmp()
    {
        empName = hireEmployee("Employee"); }



   @Test(dependsOnMethods = "testHireEmp")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Test Terminate Employee")
    public void testTerminateAnEmployee() {
        terminateEmployee(empName);

    }


    /*@Test(dependsOnMethods = "testTerminateAnEmployee")
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Test Approve Termination")
    public void testVerifyTermination(){
        verifyTransactionConsole(empName,"Completed");

    }*/
}
