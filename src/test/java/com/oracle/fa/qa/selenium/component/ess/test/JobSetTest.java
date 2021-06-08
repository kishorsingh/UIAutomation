package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class  JobSetTest extends ESSBase {

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "05/19/2019", version = "1.0")
    @TestDesc(desc = "Execute Jobset1")
    public void testExecuteJobSet() {
        //Login as User1
        executeJobSet(FrameworkContext.getInstance().getTestConfigParams().getString("JobSet1"));
    }

}
