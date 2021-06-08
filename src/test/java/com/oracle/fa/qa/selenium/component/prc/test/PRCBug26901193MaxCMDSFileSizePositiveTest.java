package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.fa.qa.selenium.component.bpm.common.SOACompositeAction;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import org.testng.annotations.Test;

@Test(groups = {"bug26901193"})
public class PRCBug26901193MaxCMDSFileSizePositiveTest extends PRCBase{
    String processID="";
    int isERImplemented;
    SOACompositeAction action = new SOACompositeAction();

    @Test(priority=11)
    @TestAuthor(createdBy = "amranaya", createdOn = "06/28/2018", version = "1.0")
    @TestDesc(desc = "Log into PRC UI and Switch from PRC UI to EDIT in DTRT")
    public void testswitchFromPRCUItoEditInDTRT() {
        switchFromPRCUItoEditInDTRT();
        System.out.println("Switched from PRC UI to EDIT in DTRT");
    }

    @Test(dependsOnMethods="testswitchFromPRCUItoEditInDTRT",priority=12)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Export the Original composite")
    public void testExportOriginalComposite() {
        action.makeDirOnRemoteHost();
        action.copyFilesToRemoteHost();
        action.faExportComposite("/u01/APPLTOP/fusionapps/soa/common/bin/wlst.sh","none","/u01/UI_SCRIPT_DIR/sca_PrcPoApprovalComposite_rev11.13.18.10.0.jar","PrcPoApprovalComposite", "11.13.18.10.0");
        System.out.println("Exported the Original Composite");
    }

    @Test(dependsOnMethods="testExportOriginalComposite",priority=13)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Undeploy the Original composite")
    public void testUndeployTheOriginalComposite() {
        action.faUnDeployComposite("/u01/APPLTOP/fusionapps/soa/common/bin/wlst.sh","PrcPoApprovalComposite","11.13.18.10.0");
        System.out.println("Undeployed the Original Composite");
    }

    @Test(dependsOnMethods="testUndeployTheOriginalComposite",priority=14)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Change MaxCMDSFileSize")
    public void testChangeMaxCMDFileSize() {
        isERImplemented = action.changeMaxCMDFileSize("/u01/APPLTOP/fusionapps/soa/common/bin/wlst.sh","50");
        if(isERImplemented!=0){
            throw new TestErrorException("MaxCMDSFileSize attribute is not found. The ER#26901193 has not been implemented. Skipping the remaining validations.");
        }
        System.out.println("Changed MaxCMDSFileSize");
    }

    @Test(dependsOnMethods="testChangeMaxCMDFileSize",priority=15)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Deploy the edited composite")
    public void testDeployTheEditedComposite() {
        action.faDeployComposite("/u01/APPLTOP/fusionapps/soa/common/bin/wlst.sh","/u01/UI_SCRIPT_DIR/sca_PrcPoApprovalComposite_rev2.0.jar");
        System.out.println("Deployed the edited Composite");
    }

    @Test(dependsOnMethods="testDeployTheEditedComposite",priority=16)
    @TestAuthor(createdBy = "amranaya", createdOn = "06/28/2018", version = "1.0")
    @TestDesc(desc = "Trigger ESS Job")
    public void testTriggerPRCRuleESSJob() {
        processID = triggerPRCRuleESSJob();
        System.out.println("New ESS Job triggered. Process ID is: "+processID);
    }

    @Test(dependsOnMethods="testTriggerPRCRuleESSJob",priority=17)
    @TestAuthor(createdBy = "amranaya", createdOn = "06/28/2018", version = "1.0")
    @TestDesc(desc = "Verify ESS job status")
    public void verifyESSJobStatus() {
        verifyESSJobStatus(processID, 6, "Succeeded");
    }

    @Test(dependsOnMethods="testUndeployTheOriginalComposite",priority=18)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Deploy the Original composite")
    public void testDeployTheOriginalComposite() {
        action.faDeployComposite("/u01/APPLTOP/fusionapps/soa/common/bin/wlst.sh","/u01/UI_SCRIPT_DIR/sca_PrcPoApprovalComposite_rev11.13.18.10.0.jar");
        System.out.println("Deployed the Original Composite");
    }

    @Test(dependsOnMethods="testDeployTheOriginalComposite",priority=19)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Delete Remote Directory")
    public void testDeleteRemoteDirectory() {
        action.deleteDirOnRemoteHost();
        System.out.println("Deleted Remote Directory");
    }

    /*@Test(dependsOnMethods="testDeleteRemoteDirectory",priority=20)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Close SSH Connection")
    public void testCloseSSHConnection() {
        action.closeAllSession();
        System.out.println("Closed SSH Connection");
    }*/
}
