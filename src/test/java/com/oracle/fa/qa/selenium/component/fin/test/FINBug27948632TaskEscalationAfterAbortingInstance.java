package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups = { "bug27948632","task_operations" })
public class FINBug27948632TaskEscalationAfterAbortingInstance extends FinBase {
	String advanceRepName="ADVEXM0151813832";

    @Test(priority = 1)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Enable Deadline->Expiration ")
    public void testEnableDeadline() {
        configureDeadlines(fusionUser,fusionUserPwd,"FinExmWorkflowCashAdvanceApproval", "enable");
    }

	@Test(dependsOnMethods="testEnableDeadline",priority = 2)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Cash Advance Report")
    public void testCreateCashAdvance() {
		advanceRepName=createCashAdvance("finuser6","Welcome1",false);
    }

	@Test(dependsOnMethods="testCreateCashAdvance",priority = 3)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Go to the instance in EM and abort the task")
    public void testAbortTaskFromEM() {
        String compositeName="FinExmWorkflowCashAdvanceApprovalComposite ["+faEnvVersion+"]";
        abortTaskFromEM(faAdminUser,faAdminUserPwd,compositeName,"finuser6");
    }

	@Test(dependsOnMethods="testAbortTaskFromEM",priority = 4)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Verify if any escalation task created after terminating the instance")
    public void testVerifyEscalationTaskLevel1() {
        boolean isTaskExists = verifyEscalationTask("finuser7","Welcome1",advanceRepName);
        StepReport.assertFalse("Bug#27948632:Verify if any escalation task created after terminating the instance for user finuser7",isTaskExists);
    }

    @Test(dependsOnMethods="testVerifyEscalationTaskLevel1",priority = 5)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Verify if any escalation task created after terminating the instance")
    public void testVerifyEscalationTaskLevel2() {
        boolean isTaskExists = verifyEscalationTask("finuser8","Welcome1",advanceRepName);
        StepReport.assertFalse("Bug#27948632:Verify if any escalation task created after terminating the instance for user finuser8",isTaskExists);
    }

    @Test(dependsOnMethods="testVerifyEscalationTaskLevel2",priority = 6)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Verify if any escalation task created after terminating the instance")
    public void testVerifyEscalationTaskLevel3() {
        boolean isTaskExists = verifyEscalationTask("finuser9","Welcome1",advanceRepName);
        StepReport.assertFalse("Bug#27948632:Verify if any escalation task created after terminating the instance for user finuser9",isTaskExists);
    }

    @Test(dependsOnMethods="testEnableDeadline",priority = 7)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Disable Deadline->Expiration ")
    public void testDisableDeadline() {
        configureDeadlines(fusionUser,fusionUserPwd,"FinExmWorkflowCashAdvanceApproval", "disable");
    }
}
