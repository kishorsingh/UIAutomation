package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class FINInvoiceWithdrawByAnyUser extends FinBase{
	String invoiceNumber="";
	
	@Test(priority=1)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/08/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice Setup")
    public void testIsInvoiceSetupExists() {
		if(!isFinancialSetupExists) {
			setupInvoiceApproval("select");
		}
		System.out.println("Financial Setup Status : "+isFinancialSetupExists);
    }
	
	@Test(dependsOnMethods="testIsInvoiceSetupExists",priority=2)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/08/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - create Invoice")
    public void testCreateInvoice() {
		//setupInvoiceApproval();
		invoiceNumber=createInvoice(finUser1,finUser1Pwd,true,true);
    }
	
	@Test(dependsOnMethods="testCreateInvoice",priority=3)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/08/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - Withdraw Invoice By Any User")
    public void testWithdrawInvoice() {
		invoiceReportOperations(finUser2,finUser2Pwd,invoiceNumber,false,"Withdraw",false,true);
    }

	
}
