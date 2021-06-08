package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

/**
 * Created by bpadhy on 01/16/18.
 */
public class TestLogin extends ESSBase {

    /**
     * Test FA Login
     */
	@Test
    @TestAuthor(createdBy = "kishsing", createdOn = "01/31/2019", version = "1.0")
    @TestDesc(desc = "Test FA Login")
    public void testFALogin1() {
   executeProcess(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
//        srs.getJob();
    }
	
	//@Test(dependsOnMethods="testFALogin")
   // @TestAuthor(createdBy = "bpadhy", createdOn = "01/16/2018", version = "1.0")
   // @TestDesc(desc = "Test FA Logout")
    public void testFALogOut() {
		logout();
    	StepReport.info("Login Test successful"); 
    }
    
//    @Test
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/16/2018", version = "1.0")
    @TestDesc(desc = "Test FA Login")
    public void testFALogin2() {
		login();
    	StepReport.info("Login Test successful"); 
    }

}
