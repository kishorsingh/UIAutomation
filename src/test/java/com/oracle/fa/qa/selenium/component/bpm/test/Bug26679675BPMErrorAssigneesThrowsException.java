package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.fin.test.FinBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = { "bug26679675","worklist_app" })
public class Bug26679675BPMErrorAssigneesThrowsException extends BPMBase{

	@Test()
    @TestAuthor(createdBy = "amranaya", createdOn = "07/30/2018", version = "1.0")
    @TestDesc(desc = "Verify that Error is not displayed when entered values in Expression Editor")
    public void testVerifyErrorAssigneesExpressionEditor() {
        verifyErrorAssigneesExpressionEditor(fusionUser,fusionUserPwd,"ActivityManagement");
    }

    @Test(dependsOnMethods="testVerifyErrorAssigneesExpressionEditor")
    @TestAuthor(createdBy = "amranaya", createdOn = "08/01/2018", version = "1.0")
    @TestDesc(desc = "Verify the Error in EM")
    public void testVerifyExceptioninEM() {
        verifyExceptioninEM();
	}

}

