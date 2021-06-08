package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

/**
 * Created by mdasari on 6/11/18.
 */
public class Bug26752292Test extends BPMBase{

    String composite = FrameworkContext.getInstance().getTestConfigParams().getString("test.composite");
    String compositeName = FrameworkContext.getInstance().getTestConfigParams().getString("test.composite.name");

    @Test(priority=1)
    @TestAuthor(createdBy = "mdasari", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Deploy the composite")
    public void testDeployComposite() {
        deployComposite(composite);
    }

    @Test(dependsOnMethods="testDeployComposite",priority=2)
    @TestAuthor(createdBy = "mdasari", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Invoking the composite")
    public void testInvokeComposite() {
        invokeComposite(compositeName,"Test");
    }


    @Test(dependsOnMethods="testInvokeComposite",priority=3)
    @TestAuthor(createdBy = "mdasari", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Select a task and in Reassign dialog pane, verify user details.")
    public void testUserDetailsOnReassignDialog() {
        selectTaskAndCheckUserDetailsInReassignDialogSearchPane("jstein");
    }


    @Test(dependsOnMethods="testDeployComposite",priority=4)
    @TestAuthor(createdBy = "mdasari", createdOn = "07/18/2018", version = "1.0")
    @TestDesc(desc = "Undeploy the composite")
    public void testUndeployComposite() {
        undeployComposite(compositeName);
    }

}