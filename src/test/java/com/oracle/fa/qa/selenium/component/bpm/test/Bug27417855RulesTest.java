package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.bpm.common.SOACompositeAction;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class Bug27417855RulesTest extends BPMBase {
    String errorMessage = "";
    String compositeName = "";
    String inputPayload = "Test";
    String soaDomain = "WLS_SOAWC";
    String composite = "";
    String wlstLocation = "/scratch/ashwaraj/REL13_auto_work/mw9910/soa1506/common/bin/wlst.sh";

    SOACompositeAction action = new SOACompositeAction();


    @Test(priority = 1)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "04/10/2019", version = "1.0")
    @TestDesc(desc = "testDeployComposite")
    public void testDeployComposite() {
        //composite = "/net/slc15glz/scratch/ashwaraj/Composites/SimpleComposite/Project1/deploy/sca_Project1_rev1.0.jar";
        composite = "/net/slc10vle/scratch/qa_composites/Bug27417855RulesTest/sca_Project1_rev1.0.jar";
        action.makeDirOnRemoteHost();
        action.copyFilesToRemoteHost();
        action.soaDeployComposite(wlstLocation, composite);
    }

    @Test(dependsOnMethods = "testDeployComposite", priority = 2)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "04/10/2019", version = "1.0")
    @TestDesc(desc = "testCreateTaskForRuleTest")
    public void testCreateTaskForRuleTest() {
        errorMessage = "Error in routing slip. The task is assigned to an invalid user jcooper1 in realm jazn.com.";
        compositeName = "Project1 [1.0]";
        createTaskForRulesTest(compositeName, inputPayload, soaDomain, errorMessage);

    }

    @Test(dependsOnMethods = "testDeployComposite", priority = 3)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "04/10/2019", version = "1.0")
    @TestDesc(desc = "testUndeployComposite")
    public void testUndeployComposite() {
        action.soaUnDeployComposite(wlstLocation, "Project1", "1.0");

    }

    @Test(priority = 4)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "04/10/2019", version = "1.0")
    @TestDesc(desc = "testDeployComposite")
    public void testDeployRulesetComposite() {
        //composite = "/net/slc15glz/scratch/ashwaraj/Composites/RulesTestApp/RulesTest/deploy/sca_RulesTest_rev1.0.jar";
        composite = "/net/slc10vle/scratch/qa_composites/Bug27417855RulesTest/sca_RulesTest_rev1.0.jar";
        action.soaDeployComposite(wlstLocation, composite);
    }

    @Test(dependsOnMethods = "testDeployRulesetComposite", priority = 5)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "04/10/2019", version = "1.0")
    @TestDesc(desc = "testCreateTaskForRuleTest")
    public void testCreateTaskForRulesetTest() {
        errorMessage = "Error Message: {http://xmlns.oracle.com/bpel/workflow/taskService}operationErroredFault";
        compositeName = "RulesTest [1.0]";
        createTaskForRulesTest(compositeName, inputPayload, soaDomain, errorMessage);

    }

    @Test(dependsOnMethods = "testDeployRulesetComposite", priority = 6)
    @TestAuthor(createdBy = "ashwaraj", createdOn = "04/10/2019", version = "1.0")
    @TestDesc(desc = "testUndeployComposite")
    public void testUndeployRulesetComposite() {
        action.soaUnDeployComposite(wlstLocation,"Project1","1.0");

    }

}
