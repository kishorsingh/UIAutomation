package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class ReSubmitTest extends ESSBase{

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "05/19/2019", version = "1.0")
    @TestDesc(desc = "Resubmit Java process")
    public void testResubmitJavaJob() {
        //Login as User1
        reSubmitProcess(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "05/19/2019", version = "1.0")
    @TestDesc(desc = "Resubmit PLSQL process")
    public void testResubmitPLSQLJob() {
        //Login as User1
        reSubmitProcess(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "05/19/2019", version = "1.0")
    @TestDesc(desc = "Resubmit BIP process")
    public void testResubmitBIPJob() {
        //Login as User1
        reSubmitProcess(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"));
    }

    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "06/19/2019", version = "1.0")
    @TestDesc(desc = "Resubmit C process")
    public void testResubmitCJob() {
        //Login as User1
        reSubmitProcess(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"));
    }
}
