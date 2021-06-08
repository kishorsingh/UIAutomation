package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.fa.qa.selenium.component.bpm.common.SOACompositeAction;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.SkipException;

@Test(groups = { "bug28390711","task_operations" })
public class HCMBug28390711AbsenceApproval extends HCMBase {
    public static boolean skiptests=true;
    SOACompositeAction action = new SOACompositeAction();
    String compositeName = "SimpleAggregationProject [2.0]";

    @Test
    public  void getHostname() throws Exception {
        if(checkHostname()){
            throw new SkipException("Skipping this exception");
        }
    }
    @Test(dependsOnMethods="getHostname",priority = 1)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Add Bruce Yarmouth as the Project Manager for Veda Moss")
    public void testAddProjectManager() {

        addProjectManager("Moss, Veda","Yarmouth, Bruce","Project manager" );
    }

    @Test(dependsOnMethods="testAddProjectManager",priority = 2)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Deploy the composite")
    public void testDeployComposite() throws Exception {
        action.copyCompositeFromRepository("sca_SimpleAggregationProject_rev2.0.jar");
        action.makeDirOnRemoteHost();
        action.copyFilesToRemoteHost();
        action.faDeployComposite("/u01/APPLTOP/fusionapps/soa/common/bin/wlst.sh","/u01/UI_SCRIPT_DIR/sca_SimpleAggregationProject_rev2.0.jar");
        System.out.println("Deployed the Composite");
        action.deleteDirOnRemoteHost();
    }

    @Test(dependsOnMethods="testDeployComposite",priority = 3)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Create instance")
    public void testCreateInstance() {
        invokeComposite(compositeName,"Test");
    }

    @Test(dependsOnMethods="testCreateInstance",priority = 4)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Approve the task by Project Manager")
    public void testApproveByProjectManager() {
        taskOperation(hcmUser3,finUser3Pwd,"Simple Aggregation HT","Approve");
    }

    @Test(dependsOnMethods = "testApproveByProjectManager",priority = 5)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Approve the task by Project Manager")
    public void testApproveByLineManager() {
        boolean isTaskAssigned = taskOperation(hcmUser2,finUser2Pwd,"Simple Aggregation HT","Approve");
        StepReport.assertTrue("Bug28390711: Task Simple Aggregation HT assigned to user " + hcmUser2 + "", isTaskAssigned);
    }

    @Test(dependsOnMethods = "testAddProjectManager",priority = 6)
    @TestAuthor(createdBy = "amranaya", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Add Bruce Yarmouth as the Project Manager for Veda Moss")
    public void testRemoveProjectManager() {
        removeProjectManager("Moss, Veda","100010034145833");
    }



    public boolean checkHostname(){
        String myhostName="";
        boolean runRemainingTests=false;
        myhostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        if (myhostName.length()<2){
            skiptests=true;
        }else {
            skiptests=false;
        }
        StepReport.info("Hostname details are Incorrect, Hence skipping the tests. Download testconfig.properties file from wiki below, make necessary changes and upload. Help wiki http://aseng-wiki.us.oracle.com/asengwiki/display/ASQA/Test+Config+Properties+for+FA+UI");
        return skiptests;
    }
}



