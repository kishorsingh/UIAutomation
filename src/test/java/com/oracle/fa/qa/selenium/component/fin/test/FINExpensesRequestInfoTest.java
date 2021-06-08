package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINExpensesRequestInfoTest extends FinBase{
	String expenseRepName="";
	
	@Test(priority=1)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Expense Report")
    public void testCreateExpense() {
		expenseRepName=createExpenseReport(finUser1,finUser1Pwd,false);
    }
	
	@Test(dependsOnMethods="testCreateExpense",priority=2)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Request Information")
    public void testExpenseReportReportrequestInfo() {
		expenseReportOperations(finUser2,finUser2Pwd,null,expenseRepName,false,"reqInfo",false,false);
    }

    @Test(dependsOnMethods="testExpenseReportReportrequestInfo",priority=3)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotificationReqInfo() {
        checkBellNotificationWithLogin(finUser1,finUser1Pwd,expenseRepName);
    }

	@Test(dependsOnMethods="testExpenseReportReportrequestInfo",priority=4)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Provide Information")
    public void testExpenseReportProvideInfo() {
		expenseReportOperations(finUser1,finUser1Pwd,null,expenseRepName,false,"provideInfo",false,false);
    }

    @Test(dependsOnMethods="testExpenseReportProvideInfo",priority=5)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotificationProvideInfo() {
        checkBellNotificationWithLogin(finUser2,finUser2Pwd,expenseRepName);
    }
	
	@Test(dependsOnMethods="testExpenseReportProvideInfo",priority=6)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Approve Report")
    public void testApproveExpense() {
		expenseReportOperations(finUser2,finUser2Pwd,null,expenseRepName,false,"approve",true,false);
    }
}
