package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class PutOnHoldTest extends ESSBase{

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "04/19/2019", version = "1.0")
    @TestDesc(desc = "Put On Hold Java Job")
    public void testPutOnHoldJavaJob() {
        //Login as User1
        putOnHoldProcess(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "04/19/2019", version = "1.0")
    @TestDesc(desc = "Put On Hold PLSQL Job")
    public void testPutOnHoldPLSQLJob() {
        //Login as User1
        putOnHoldProcess(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "04/19/2019", version = "1.0")
    @TestDesc(desc = "Put On Hold BIP Job")
    public void testPutOnHoldBIPJob() {
        //Login as User1
        putOnHoldProcess(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "06/25/2019", version = "1.0")
    @TestDesc(desc = "Put On Hold C Job")
    public void testPutOnHoldCJob() {
        //Login as User1
        putOnHoldProcess(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"));
    }


}
