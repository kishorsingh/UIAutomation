package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.bpm.common.SOACompositeAction;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class Bug27655609TaskAggrTaskReassignStuck extends BPMBase {
    SOACompositeAction action = new SOACompositeAction();
    String composite = FrameworkContext.getInstance().getTestConfigParams().getString("test.composite");
    String compositeName = FrameworkContext.getInstance().getTestConfigParams().getString("test.composite.name");
    String ApplicationTF = FrameworkContext.getInstance().getTestConfigParams().getString("test.application.tf");
    String ApplicationTFName = FrameworkContext.getInstance().getTestConfigParams().getString("test.application.tf.name");
    String wlstLocation="/scratch/mdasari/REL13_auto_work/mw9408/soa7719/common/bin/wlst.sh";

    /**
     *  Deploy composite along with taskform in cli mode remotely.
     */
    @Test
    @TestAuthor(createdBy = "mdasari", createdOn = "07/25/2018", version = "1.0")
    public void testDeployCompositeAndTF(){
        action.makeDirOnRemoteHost();
        action.copyFilesToRemoteHost();
        action.soaDeployCompositeWithTaskForm(wlstLocation,composite,ApplicationTFName,ApplicationTF);
    }

    /**
     * Invoke a composite through EM.
     */
    @Test(dependsOnMethods="testDeployCompositeAndTF",priority=2)
    @TestAuthor(createdBy = "mdasari", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Invoking the composite")
    public void testInvokeComposite() {
        invokeComposite(compositeName,"test");
    }

    /**
     * Test reassign and request info with aggregated tasks.
     */
    @Test(dependsOnMethods="testInvokeComposite",priority=3)
    @TestAuthor(createdBy = "mdasari", createdOn = "08/10/2018", version = "1.0")
    @TestDesc(desc = "Select a task and in Reassign dialog pane, verify user details.")
    public void testAggregationReassignTask() {
        testAggregationReassign();
    }

    /**
     *  Undeploy the composite and taskform through CLI.
     */
    @Test(dependsOnMethods="testDeployCompositeAndTF",priority=4)
    @TestAuthor(createdBy = "mdasari", createdOn = "08/14/2018", version = "1.0")
    @TestDesc(desc = "Undeploy the composite")
    public void testUndeployComposite() {
        action.soaUnDeployCompositeWithTaskForm(wlstLocation,compositeName,"10.0",ApplicationTFName);
    }

}
