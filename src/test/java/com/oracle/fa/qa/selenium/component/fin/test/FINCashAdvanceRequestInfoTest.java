package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINCashAdvanceRequestInfoTest extends FinBase{
	String advanceRepName="";
	
	@Test(priority = 1)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Cash Advance Report")
    public void testCreateCashAdvance() {
		//Login as User1
		advanceRepName=createCashAdvance(finUser4,finUser4Pwd,false);
    }

	@Test(dependsOnMethods="testCreateCashAdvance",priority = 2)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Request Information")
    public void testCashAdvanceReportrequestInfo() {
		//Login as User2
		//checkBellNotification=true;
		cashAdvanceReportOperations(finUser5,finUser5Pwd,advanceRepName,false,"reqInfo",false,false);
    }

    @Test(dependsOnMethods="testCashAdvanceReportrequestInfo",priority = 3)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotificationReqInfo() {
        checkBellNotificationWithLogin(finUser4,finUser4Pwd,advanceRepName);
    }
	
	@Test(dependsOnMethods="testCashAdvanceReportrequestInfo",priority = 4)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Provide Information")
    public void testCashAdvanceReportProvideInfo() {
		//Login as User2
		//checkBellNotification=true;
		cashAdvanceReportOperations(finUser4,finUser4Pwd,advanceRepName,false,"provideInfo",false,false);
    }

    @Test(dependsOnMethods="testCashAdvanceReportProvideInfo",priority = 5, groups = { "srg" })
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotificationProvideInfo() {
        checkBellNotificationWithLogin(finUser5,finUser5Pwd,advanceRepName);
    }
	
	@Test(dependsOnMethods="testCashAdvanceReportProvideInfo",priority = 6, groups = { "srg" })
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Approve Cash Advance Report")
	 public void testApproveCashAdvanceReportAfterReqInfo() {
		//Login as User2
		//checkBellNotification=true;
		cashAdvanceReportOperations(finUser5,finUser5Pwd,advanceRepName,false,"approve",true,false);
    }
}
