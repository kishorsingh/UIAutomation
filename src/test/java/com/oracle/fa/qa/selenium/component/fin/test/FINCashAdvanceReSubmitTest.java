package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FINCashAdvanceReSubmitTest extends FinBase{
	String advanceRepName="";
    String newAdvanceRepName="";
	
	@Test(priority = 1)
    @TestAuthor(createdBy = "amranaya", createdOn = "01/23/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Cash Advance Report")
    public void testCreateCashAdvance() {
		advanceRepName=createCashAdvance(finUser1,finUser1Pwd,false);
    }

	@Test(dependsOnMethods="testCreateCashAdvance",priority = 2)
    @TestAuthor(createdBy = "amranaya", createdOn = "01/23/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Reject Cash Advance Report")
    public void testRejectCashAdvanceReport() {
		cashAdvanceReportOperations(finUser2,finUser2Pwd,advanceRepName,false,"reject",false,false);
    }

    @Test(dependsOnMethods="testRejectCashAdvanceReport",priority = 3)
    @TestAuthor(createdBy = "amranaya", createdOn = "01/23/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Resubmit Cash Advance Report")
    public void testReSubmitCashAdvance() {
        newAdvanceRepName=reSubmitCashAdvance(finUser1,finUser1Pwd,advanceRepName);
        Assert.assertEquals(newAdvanceRepName,advanceRepName, "Resubmitted Cash Advance Report name doesn't match with the Original Cash Advance Report name");
    }

    @Test(dependsOnMethods="testReSubmitCashAdvance",priority = 4)
    @TestAuthor(createdBy = "amranaya", createdOn = "01/23/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotificationReqInfo() {
        checkBellNotificationWithLogin(finUser2,finUser2Pwd,advanceRepName);
    }

    @Test(dependsOnMethods="testReSubmitCashAdvance",priority = 5)
    @TestAuthor(createdBy = "amranaya", createdOn = "01/23/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Approve Resubmitted Cash Advance Report")
    public void testApproveCashAdvanceReport() {
        cashAdvanceReportOperations(finUser2,finUser2Pwd,advanceRepName,false,"approve",false,false);
    }
}