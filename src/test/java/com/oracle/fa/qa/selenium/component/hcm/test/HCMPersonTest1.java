package com.oracle.fa.qa.selenium.component.hcm.test;

import org.testng.annotations.Test;

import com.oracle.fa.qa.selenium.base.SeleniumBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;

public class HCMPersonTest1 extends HCMBase{
	    @Test
	    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
	    @TestDesc(desc = "Test FA Login")
	    public void testHireAnEmployee() {
			configureRule("Hire an Employee","managementhierarchy","");

	    }
}
