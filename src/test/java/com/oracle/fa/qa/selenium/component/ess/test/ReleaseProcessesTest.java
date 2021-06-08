package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class ReleaseProcessesTest extends ESSBase{


    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "04/19/2019", version = "1.0")
    @TestDesc(desc = "Release Java Job")
    public void testPutOnHoldJavaJob() {
        //Login as User1
        releaseProcess(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "04/19/2019", version = "1.0")
    @TestDesc(desc = "Release PLSQL Job")
    public void testPutOnHoldPLSQLJob() {
        //Login as User1
        releaseProcess(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "04/19/2019", version = "1.0")
    @TestDesc(desc = "Release BIP Job")
    public void testPutOnHoldBIPJob() {
        //Login as User1
        releaseProcess(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "06/19/2019", version = "1.0")
    @TestDesc(desc = "Release C Job")
    public void testPutOnHoldCJob() {
        //Login as User1
        releaseProcess(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"));
    }
}
