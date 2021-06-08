package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINCashAdvanceSuspendResumeTest extends FinBase{
	String advanceRepName="";
	
	@Test
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Cash Advance Report")
    public void testCreateCashAdvance() {
		advanceRepName=createCashAdvance(finUser4,finUser4Pwd,false);
    }

	@Test(dependsOnMethods="testCreateCashAdvance")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - test Suspend Resume")
    public void testSuspendResumeCashAdvanceReport() {
		reportSuspendResumeOperation(finUser5,finUser5Pwd,advanceRepName);
    }
}
