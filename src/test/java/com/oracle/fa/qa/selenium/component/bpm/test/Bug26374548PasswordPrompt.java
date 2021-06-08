package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.hcm.test.HCMBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups={"bug26374548"})
public class Bug26374548PasswordPrompt extends HCMBase {

    String resignUser1 = "finuser15";
    String resignUser1Pswd = "Welcome1";
    String resignUser1Name="Samuel Jackson";
    String resignUser2 = "finuser16";
    String resignUser2Pswd = "Welcome1";

    @Test(priority=1)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/20/2018", version = "1.0")
    @TestDesc(desc = "Config password prompt for resignation approval")
    public void configPasswordPromptForResignationApproval() {
        configTaskToShowPasswordPrompt(applConsultant, applConsultantPwd, "ResignationApproval");
    }

    @Test(dependsOnMethods="configPasswordPromptForResignationApproval", priority=2)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/20/2018", version = "1.0")
    @TestDesc(desc = "Create resignation request")
    public void createResignationRequestTest() {
        createResignationRequest(resignUser1, resignUser1Pswd);
    }

    @Test(dependsOnMethods="createResignationRequestTest", priority=3)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Verify bell notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(resignUser2, resignUser2Pswd, resignUser1Name);
    }

    @Test(dependsOnMethods="createResignationRequestTest", priority=4)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "Verify password prompt on action")
    public void testVerifyPasswordPromptOnAction() {
        verifyPasswordPromptOnReject(resignUser2, resignUser2Pswd, resignUser1Name);
    }

}
