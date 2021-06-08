package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class ExecuteJavaJobTest extends ESSBase {

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Execute Java Job")
    public void testExecuteJavaJob() {
        //Login as User1
        executeJavaJobs(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

//    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2018", version = "1.0")
    @TestDesc(desc = "Execute PLSQL Job")
    public void testExecutePLSQLJob() {
        //Login as User1
        executePLSSQLJobs(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"));
    }

//    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2018", version = "1.0")
    @TestDesc(desc = "Execute BIP Job")
    public void testExecuteBIPJob() {
        //Login as User1
        executeBIPJobs(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"));
    }
}

