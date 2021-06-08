package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class  CancelProcessTest extends ESSBase{

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Cancel Java Job")
    public void testCancelJavaJob() {
        //Login as User1
       cancelProcess(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Cancel PLSQL Job")
    public void testCancelPLSQLJob() {
        //Login as User1
        cancelProcess(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Cancel BIP Job")
    public void testCancelBIPJob() {
        //Login as User1
        cancelProcess(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "06/28/2019", version = "1.0")
    @TestDesc(desc = "Cancel BIP Job")
    public void testCancelCJob() {
        //Login as User1
        cancelProcess(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"));
    }

}
