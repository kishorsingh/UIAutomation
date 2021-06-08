package com.oracle.fa.qa.selenium.component.hcm.test;
import com.oracle.fa.qa.selenium.component.common.page.*;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class HCMNonWorkerDelegateTest extends HCMBase{
    String empName="";

    @Test
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/09/2018", version = "1.0")
    @TestDesc(desc = "Create Nonworker Rule")
    public void testChangeNonworkerRule(){
        configureRule("Add NonWorker","managementhierarchy","sballard");

    }

    @Test(dependsOnMethods = "testChangeNonworkerRule")
    @TestAuthor(createdBy = "pkanlet", createdOn = "5/7/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testAddnonworker() {
        empName = enterNonWorkerDetails("Nonworker");

    }


    @Test(dependsOnMethods = "testAddnonworker")
    @TestAuthor(createdBy = "pkanlet", createdOn = "5/9/2018", version = "1.0")
    @TestDesc(desc = "Delegate action from sballard to vmoss")
    public void delegateAtLevel0(){
        // Switching to Parent window i.e Main Window.
        firstLevelAction("Delegate","vmoss", empName);

    }

    @Test(dependsOnMethods = "delegateAtLevel0")
    @TestAuthor(createdBy = "pkanlet", createdOn = "5/9/2018", version = "1.0")
    @TestDesc(desc = "check delegate action")
    public void verifyDelegateActionBellnotifi(){
        String str="Delegated: Nonworker Added (" + empName;
        verifyActionBellNotif(str,"");
    }
}
