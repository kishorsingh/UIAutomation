package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.fa.qa.selenium.component.bpm.common.SOACompositeAction;
import com.oracle.fa.qa.selenium.component.hcm.test.HCMBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.SkipException;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

@Test(groups = { "aggregation","bug27969763" })
public class HCMBug27969763FYIAutoDismiss extends HCMBase {
    String processID = "";
    public static String personNumber="";
    String empName="";
    int IsMbeansChanged;
    SOACompositeAction action = new SOACompositeAction();

    public static boolean skiptests=true;

    @Test
    public  void getHostname() throws Exception {
        if(checkHostname()){
            throw new SkipException("Skipping this exception");
        }
    }

    @Test (dependsOnMethods="getHostname",priority=1)
    @TestAuthor(createdBy = "pkanlet", createdOn = "08/02/2018", version = "1.0")
    @TestDesc(desc = "Change FyiTaskLifeTime and FyiDismissalJobFrequency")
    public void testChangeFyiMbeanValues() {
        //Note that values for the mbeans have to be directly added in the fyijob.py script
        action.makeDirOnRemoteHost();
        action.copyFilesToRemoteHost();
        IsMbeansChanged = action.changeFyiValues("/u01/APPLTOP/fusionapps/soa/common/bin/wlst.sh");
        System.out.println("Changed FyiTaskLifeTime and FyiDismissalJobFrequency");
        action.deleteDirOnRemoteHost();
    }

    //Configure hire an employee rule so that the task gets autoapproved
    @Test(dependsOnMethods = "testChangeFyiMbeanValues",priority=2)
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Verify if hireEmployee rule is pre-configured")
    public void testConfigureAutoHireRule(){
           deleteHireEmpRule("Hire an Employee","SelfApproval",""); }


    //Hire an employee, use the same employee for promotion
    @Test(dependsOnMethods = "testConfigureAutoHireRule",priority=3)
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = hireEmployee("Employee"); }

   // Configure promote rule : FYI should go to byarmouth, approval goes to sballard
    @Test(dependsOnMethods = "testHireAnEmployee",priority=4)
    @TestAuthor(createdBy = "pkanlet", createdOn = "8/2/2018", version = "1.0")
    @TestDesc(desc = "Test create promote employee rule")
    public void testCreatePromotionRule(){
       //Adding 2 blocks in the promote rule
       configureRule("Promote", "managementhierarchy","byarmouth");
    }

    //Promote the employee who was hired in the previous step
    @Test(dependsOnMethods = "testCreatePromotionRule",priority=5)
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test promote Employee at Level1")
    public void testPromoteAnEmployee() {
        promoteEmployee(empName);
        StepReport.info("Promotion request is created for Person Number: " + empName );

    }

    //Check if byarmouth has got FYI message immediately after promoting an employee
    @Test(dependsOnMethods = "testPromoteAnEmployee",priority=6)
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Verify FYI as byarmouth")
    public void verifyActionFYInotification(){
        //Login changes should be made in the method.
        String str="FYI: Promotion for "+empName;
        verifyActionBellNotif(str,"FYI");
    }

    //Method to wait for 5 mins ; verify if there is any better way.
      @Test(dependsOnMethods = "verifyActionFYInotification",priority=7)
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Verify FYI as byarmouth")
    public void WaitforFewminutes(){
          DriverUtil.sleep(300000);
    }



  //  Same action : verify after 5 mins if FYI is dismissed or still stays..
    @Test(dependsOnMethods = "WaitforFewminutes",priority=8)
    @TestAuthor(createdBy = "pkanlet", createdOn = "07/25/2018", version = "1.0")
    @TestDesc(desc = "Verify FYI as byarmouth")
    public void verifyActionFYInotificationAfter3min(){
        //Login changes should be made in the method.
        String str=" *** FYI: Promotion for "+empName;
        Boolean checkAutoDismiss=verifyActionBellNotif(str,"FYI");
        if(checkAutoDismiss){
            throw new TestErrorException("FYI Task not AutoDismissed after 3 minutes, Bug27969763 still exists");
        } }


    //An approval request for promotion would go to sballard, approve the same
    @Test(dependsOnMethods = "testPromoteAnEmployee",priority=9)
    @TestAuthor(createdBy = "pkanlet", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve promotion request sballard")
    public void testApprovePromotion(){
        approveAction(empName,"First");    }



    @Test(dependsOnMethods = "testApprovePromotion",priority=10)
    @TestAuthor(createdBy = "pkanlet", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Test Approve Termination")
    public void testVerifyTermination(){
        verifyTransactionConsole(empName,"Completed");

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
