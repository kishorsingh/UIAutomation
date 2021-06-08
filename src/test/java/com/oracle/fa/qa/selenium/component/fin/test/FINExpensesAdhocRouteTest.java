package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINExpensesAdhocRouteTest extends FinBase{
	String expenseRepName="";
	
	@Test(priority=1)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Expense Report")
    public void testCreateExpense() {
		//Login as User1
		expenseRepName=createExpenseReport(finUser1,finUser1Pwd,false);
    }

	@Test(dependsOnMethods="testCreateExpense",priority=2)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - ReAssign Expense Report")
    public void testAdhocUser() {
		//Login as User2
		expenseReportOperations(finUser2,finUser2Pwd,finUser3,expenseRepName,false,"adhoc_user",false,false);
    }

    @Test(dependsOnMethods="testAdhocUser",priority=3)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(finUser3,finUser3Pwd,expenseRepName);
    }
	
	@Test(dependsOnMethods="testAdhocUser",priority=4)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Approve Report")
    public void testDelegateApproveExpense() {
		//Login as User2
		//checkBellNotification=true;
		approveExpenseReport(finUser3,finUser3Pwd,expenseRepName);
    }
}
