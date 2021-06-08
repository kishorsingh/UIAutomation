package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMEnrollmentWithdrawalTest extends CRMBase {

    public static String enrollmentName = "";
    public static String partnerName="";


    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Partner")
    public void testCreatePartner() {

        partnerName = createPartner(crmCOMUser1, crmCOMUser1Pwd);
        StepReport.info("Partner Created Successfully as: " + partnerName);
    }


    @Test(dependsOnMethods = "testCreatePartner")
    @TestAuthor(createdBy = "amranaya", createdOn = "02/15/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Enrollment")
    public void testSubmitEnrollmentForApproval() {
        enrollmentName = submitEnrollmentforApproval(crmCOMUser1, crmCOMUser1Pwd, partnerName);
        StepReport.info("Enrollment Submitted for Approval: " + enrollmentName);
    }

    @Test(dependsOnMethods = "testSubmitEnrollmentForApproval")
    @TestAuthor(createdBy = "amranaya", createdOn = "02/20/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Claim Enrollment")
    public void testClaimEnrollment() {
        claimRequest(crmCOMUser1, crmCOMUser1Pwd, partnerName);
        StepReport.info("Enrollment Claimed: " + enrollmentName);
    }

    @Test(dependsOnMethods="testClaimEnrollment")
    @TestAuthor(createdBy = "amranaya", createdOn = "02/27/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Withdraw Enrollment Request")
    public void testWithdraw() {
        enrollmentOperations(crmCOMUser1,crmCOMUser1Pwd,null,partnerName,false,"withdraw",false,false);
        StepReport.info("Enrollment Withdrawn: " + enrollmentName);
    }

}
