package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINCashAdvanceApproveTest extends FinBase{
	String advanceRepName="";
	
	@Test(priority = 1)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Cash Advance Report")
    public void testCreateCashAdvance() {
		//Login as User1
		advanceRepName=createCashAdvance(finUser1,finUser1Pwd,false);
    }

	@Test(dependsOnMethods="testCreateCashAdvance",priority = 2)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotification() {
		checkBellNotificationWithLogin(finUser2,finUser2Pwd,advanceRepName);
    }

	@Test(dependsOnMethods="testCreateCashAdvance",priority = 3)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Approve Cash Advance Report")
    public void testApproveCashAdvanceReport() {
		//Login as User2
		//checkBellNotification=true;
		cashAdvanceReportOperations(finUser2,finUser2Pwd,advanceRepName,false,"approve",false,false);
    }
}
