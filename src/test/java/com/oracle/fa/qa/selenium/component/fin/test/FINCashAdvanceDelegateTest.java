package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class FINCashAdvanceDelegateTest extends FinBase{
	String advanceRepName="";
	
	@Test(priority = 1)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Advance Request")
    public void testCreateCashAdvanceRequest() {
		//Login as User1
		advanceRepName=createCashAdvance(finUser4,finUser4Pwd,false);
    }

	@Test(dependsOnMethods="testCreateCashAdvanceRequest",priority = 2)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Delegate Cash Advance Request")
    public void testDelegateCashAdvanceRequest() {
		//Login as User2
        reAssignTask(finUser5,finUser5Pwd,advanceRepName,finUser6, "Delegate");
    }

    @Test(dependsOnMethods="testDelegateCashAdvanceRequest",priority = 3)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(finUser6,finUser6Pwd,advanceRepName);
    }
	
	@Test(dependsOnMethods="testDelegateCashAdvanceRequest",priority = 4)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Approve Report")
    public void testDelegateApproveCashAdvance() {
		cashAdvanceReportOperations(finUser6,finUser6Pwd,advanceRepName,false,"approve",false,false);
    }
}
