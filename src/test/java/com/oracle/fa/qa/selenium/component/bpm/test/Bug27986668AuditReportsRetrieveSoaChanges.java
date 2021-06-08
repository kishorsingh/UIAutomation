package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.prc.test.PRCBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "bug27986668"})
public class Bug27986668AuditReportsRetrieveSoaChanges extends BPMBase{

	@Test()
    @TestAuthor(createdBy = "amranaya", createdOn = "07/10/2018", version = "1.0")
    @TestDesc(desc = "Change the Audit Level for Oracle SOA Suite to High – All Events")
    public void testChangeAuditLevel() {
        changeAuditLevel();
    }

	@Test(dependsOnMethods="testChangeAuditLevel")
    @TestAuthor(createdBy = "amranaya", createdOn = "07/11/2018", version = "1.0")
    @TestDesc(desc = "Create DT@RT Event by modifying a composite")
    public void testCreateDTRTEvent() {
        createDTRTEvent();
    }

	@Test(dependsOnMethods="testCreateDTRTEvent")
    @TestAuthor(createdBy = "amranaya", createdOn = "07/11/2018", version = "1.0")
    @TestDesc(desc = "Verify Audit Trace")
    public void testVerifyAuditTrace() {
        verifyAuditTrace();
    }
	
}
