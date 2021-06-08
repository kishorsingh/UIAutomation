package com.oracle.fa.qa.selenium.component.common.test;

import org.testng.annotations.Test;

import com.oracle.fa.qa.selenium.base.SeleniumBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;

/**
 * Created by bpadhy on 01/16/18.
 */
public class TestLogin extends SeleniumBase {

    /**
     * Test FA Login
     */
	@Test
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/16/2018", version = "1.0")
    @TestDesc(desc = "Test FA Login")
    public void testFALogin1() {
		login();
    	StepReport.info("Login Test successful"); 
    }
	
	//@Test(dependsOnMethods="testFALogin")
   // @TestAuthor(createdBy = "bpadhy", createdOn = "01/16/2018", version = "1.0")
   // @TestDesc(desc = "Test FA Logout")
    public void testFALogOut() {
		logout();
    	StepReport.info("Login Test successful"); 
    }
    
    @Test
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/16/2018", version = "1.0")
    @TestDesc(desc = "Test FA Login")
    public void testFALogin2() {
		login();
    	StepReport.info("Login Test successful"); 
    }

}
