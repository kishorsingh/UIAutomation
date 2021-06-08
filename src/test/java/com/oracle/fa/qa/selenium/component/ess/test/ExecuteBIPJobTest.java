package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class ExecuteBIPJobTest extends ESSBase  {

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "04/14/2019", version = "1.0")
    @TestDesc(desc = "Execute BIP job")
    public void testExecuteBIPJob() {
        //Login as User1
        executeBIPJobs(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"));
    }

}
