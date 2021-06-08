package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class ExecutePLSQLJobTest extends ESSBase  {

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Execute process")
    public void testExecutePLSQLJob() {
        //Login as User1
        executePLSSQLJobs(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"));
    }
}
