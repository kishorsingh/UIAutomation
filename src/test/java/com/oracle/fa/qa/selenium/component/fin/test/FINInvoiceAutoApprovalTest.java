package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class FINInvoiceAutoApprovalTest extends FinBase{
	String invoiceNumber="";
	
	@Test(priority = 21)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/08/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice Setup")
    public void testIsInvoiceSetupExists() {
		if(!isAutoFinancialSetupExists) {
			setupInvoiceApproval("deselect");
		}
		System.out.println("Financial Setup Auto Approval Status : "+isAutoFinancialSetupExists);
    }
	
	@Test(dependsOnMethods="testIsInvoiceSetupExists", priority = 22)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/08/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - create Invoice")
    public void testCreateInvoice() {
		invoiceNumber=createInvoice(finUser1,finUser1Pwd,false,false);
    }

	@Test(dependsOnMethods="testCreateInvoice", priority = 23)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/08/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - verify Invoice status")
    public void verifyInvoiceStatus() {
        verifyInvoiceStatus(finUser1,finUser1Pwd,invoiceNumber,"Not required");
    }
}
