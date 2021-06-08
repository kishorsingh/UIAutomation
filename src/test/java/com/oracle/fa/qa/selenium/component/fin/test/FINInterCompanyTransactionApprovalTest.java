package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

public class FINInterCompanyTransactionApprovalTest extends FinBase{
	String transactionNumber="";
	
	@Test
    @TestAuthor(createdBy = "amranaya", createdOn = "05/11/2018", version = "1.0")
    @TestDesc(desc = "Test Intercompany Transaction - create Transaction")
    public void testCreateTransaction() {
        transactionNumber=createInterCompanyTransaction(interCompanyAccountant,interCompanyAccountantPwd);
        StepReport.info("Transaction Number is:"+transactionNumber);
    }
	
}
