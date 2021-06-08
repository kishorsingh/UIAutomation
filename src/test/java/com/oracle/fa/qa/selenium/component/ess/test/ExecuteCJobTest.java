package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class ExecuteCJobTest extends ESSBase{

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "06/28/2019", version = "1.0")
    @TestDesc(desc = "Execute C job")
    public void testExecuteBIPJob() {
        //Login as User1
        executeCJobs(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"));
    }
}
