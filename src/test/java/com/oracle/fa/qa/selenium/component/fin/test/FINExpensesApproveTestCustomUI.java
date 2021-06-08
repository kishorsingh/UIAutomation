package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class FINExpensesApproveTestCustomUI extends FinBase{
	String expenseRepName="";


    @Test
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Expense Report")
    public void testCreateExpense() {
		//Login as User1
		expenseRepName=createExpenseReport(finUser1,finUser1Pwd,false);
    }

	@Test(dependsOnMethods="testCreateExpense")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - verify Bell notiifcation")
    public void testVerifyBellNotification() {
		checkBellNotificationWithLogin(finUser2,finUser2Pwd,expenseRepName);
    }

	@Test(dependsOnMethods="testVerifyBellNotification")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Expense Report")
    public void testApproveExpense() {
		//Login as User2
		//checkBellNotification=true;
		approveExpenseReport(finUser2,finUser2Pwd,expenseRepName);
    }
}
