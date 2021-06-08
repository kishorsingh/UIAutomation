package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class FINInvoiceDelegateTest extends FinBase{
    String invoiceNumber="";
	
	@Test(priority=1)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice Setup")
    public void testIsInvoiceSetupExists() {
		if(!isFinancialSetupExists) {
			setupInvoiceApproval("select");
		}
		System.out.println("Financial Setup Status : "+isFinancialSetupExists);
    }
	
	@Test(dependsOnMethods="testIsInvoiceSetupExists",priority=2)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - create Invoice")
    public void testCreateInvoice() {
		//setupInvoiceApproval();
		invoiceNumber=createInvoice(finUser1,finUser1Pwd,true,false);
    }

	@Test(dependsOnMethods="testCreateInvoice",priority=3)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - Delegate")
    public void testDelegateInvoice() {
		//Login as User2
		reAssignTask(finUser2,finUser2Pwd,invoiceNumber,finUser3, "Delegate");
    }

    @Test(dependsOnMethods="testDelegateInvoice",priority=4)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - Verify Bell Notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(finUser3,finUser3Pwd,invoiceNumber);
    }
	
	@Test(dependsOnMethods="testDelegateInvoice",priority=5)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Invoice - Approve Report")
    public void testDelegateApproveInvoice() {
		invoiceReportOperations(finUser3,finUser3Pwd,invoiceNumber,false,"approve",false,false);
    }
}
