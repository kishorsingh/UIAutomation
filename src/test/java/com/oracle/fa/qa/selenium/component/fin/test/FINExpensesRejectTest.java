package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINExpensesRejectTest extends FinBase{
	String expenseRepName="";
	
	@Test
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Expense Report")
    public void testCreateExpense() {
		//Login as User1
		expenseRepName=createExpenseReport(finUser1,finUser1Pwd,true);
    }

	@Test(dependsOnMethods="testCreateExpense")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Expense Report")
    public void testRejectExpense() {
		//Login as User2
		expenseReportOperations(finUser2,finUser2Pwd,null,expenseRepName,false,"approve",false,true);
    }
}
