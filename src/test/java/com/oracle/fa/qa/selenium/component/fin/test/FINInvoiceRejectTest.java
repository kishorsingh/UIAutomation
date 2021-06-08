package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINInvoiceRejectTest extends FinBase{
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
		invoiceNumber=createInvoice(finUser1,finUser1Pwd,true,true);
    }
	
	@Test(dependsOnMethods="testCreateInvoice",priority=3)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - Reject Invoice")
    public void testRejectInvoice() {
		//setupInvoiceApproval();
		invoiceReportOperations(finUser2,finUser2Pwd,invoiceNumber,false,"reject",false,true);
    }

	
}
