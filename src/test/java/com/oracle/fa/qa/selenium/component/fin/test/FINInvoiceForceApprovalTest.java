package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class FINInvoiceForceApprovalTest extends FinBase{
	String invoiceNumber="";
	
	@Test(priority=11)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/09/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice Setup")
    public void testIsInvoiceSetupExists() {
		if(!isForceFinancialSetupExists) {
			setupInvoiceForceApproval("select");
		}
    }
	
	@Test(dependsOnMethods="testIsInvoiceSetupExists",priority=12)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/09/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - create Invoice")
    public void testCreateInvoice() {
		invoiceNumber=createInvoice(finUser1,finUser1Pwd,true,false);
    }

	@Test(dependsOnMethods="testCreateInvoice",priority=13)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/09/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - Force approve Invoice")
    public void testForceApproveInvoice() {
		invoiceForceApproval(finUser2,finUser2Pwd,invoiceNumber);
    }

    @Test(dependsOnMethods="testForceApproveInvoice",priority=14)
    @TestAuthor(createdBy = "amranaya", createdOn = "06/10/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - verify Invoice status")
    public void verifyInvoiceStatus() {
        verifyInvoiceStatus(finUser1,finUser1Pwd,invoiceNumber,"Manually approved");
    }

}
