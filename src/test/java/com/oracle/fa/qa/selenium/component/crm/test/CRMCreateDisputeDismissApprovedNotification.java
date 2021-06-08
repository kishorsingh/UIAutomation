package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMCreateDisputeDismissApprovedNotification extends CRMBase{
    public static String cdUserName="";
    public static String cdUserPassword="";
    public static String adUserName="";
    public static String adUserPassword="";
    public static String DisputeName="";
    public static String operationName="";


    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/27/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Dispute")
    public void testCreateDispute() {
        DisputeName = createDispute(cdUserName,cdUserPassword);
        StepReport.info("Create Dispute Application Submitted Successfully: "+DisputeName);
    }

    @Test(dependsOnMethods ="testCreateDispute")
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/27/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Approve Dispute")
    public void testApproveDispute() {
        operationName = "Approve";
        operationsOnDisputeNotification(adUserName,adUserPassword,DisputeName,operationName);
        StepReport.info("Create Dispute Application Approved Successfully: ");
    }

    @Test(dependsOnMethods ="testApproveDispute")
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/27/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Withdraw Dispute Notification")
    public void testDissmissDispute() {
        operationName = "Dissmiss";
        operationsOnDisputeNotification(cdUserName,cdUserPassword,DisputeName,operationName);
        StepReport.info("Create Dispute Application dismissed Successfully: ");
    }



    @BeforeClass
    public final void initializeCRMTestData() throws Exception {
        getCRMTestData();
    }
    void getCRMTestData() {


        cdUserName=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oic.cdUserName");
        cdUserPassword=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oic.cdUserPassword");
        adUserName=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oic.adUserName");
        adUserPassword=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oic.adUserPassword");
    }

}
