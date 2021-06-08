package com.oracle.fa.qa.selenium.component.fin.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class FINInvoiceRequestInfoTest extends FinBase{
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
    @TestDesc(desc = "Test Invoice - Request Information")
    public void testInvoiceRequestInfo() {
		invoiceReportOperations(finUser2,finUser2Pwd,invoiceNumber,false,"reqInfo",false,false);
    }

    @Test(dependsOnMethods="testInvoiceRequestInfo",priority=4)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - Verify Bell Notification")
    public void testVerifyBellNotificationReqInfo() {
        checkBellNotificationWithLogin(finUser1,finUser1Pwd,invoiceNumber);
    }
	
	@Test(dependsOnMethods="testInvoiceRequestInfo",priority=5)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Invoice - Provide Information")
    public void testInvoiceProvideInfo() {
		invoiceReportOperations(finUser1,finUser1Pwd,invoiceNumber,false,"provideInfo",false,false);
    }

    @Test(dependsOnMethods="testInvoiceRequestInfo",priority=6)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - Verify Bell Notification")
    public void testVerifyBellNotificationProvideInfo() {
        checkBellNotificationWithLogin(finUser1,finUser1Pwd,invoiceNumber);
    }
	
	@Test(dependsOnMethods="testInvoiceProvideInfo",priority=7)
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Invoice - Approve")
	 public void testApproveCashAdvanceReportAfterReqInfo() {
		invoiceReportOperations(finUser2,finUser2Pwd,invoiceNumber,false,"approve",true,false);
    }
}
