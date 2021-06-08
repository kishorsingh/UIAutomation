package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

public class FINAdhocPaymentAutoApprovalTest extends FinBase{
	String adhocPaymentNumber="";
	
	@Test
    @TestAuthor(createdBy = "amranaya", createdOn = "05/17/2018", version = "1.0")
    @TestDesc(desc = "Test Ad-hoc payment")
    public void testCreateTransaction() {
        adhocPaymentNumber = createAdHocPayment(cashManager,cashManagerPwd);
        StepReport.info("Ad-hoc Payment Number is:"+adhocPaymentNumber);
    }

    @Test(dependsOnMethods="testCreateTransaction")
    @TestAuthor(createdBy = "amranaya", createdOn = "05/18/2018", version = "1.0")
    @TestDesc(desc = "Test Ad-hoc payment - verify Ad-hoc Payment status")
    public void verifyAdhocPaymentStatus() {
        verifyAdhocPaymentStatus(cashManager,cashManagerPwd,"BofA-498");
    }
	
}
