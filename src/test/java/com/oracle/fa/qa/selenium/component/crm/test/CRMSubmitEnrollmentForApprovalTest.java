package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


public class CRMSubmitEnrollmentForApprovalTest extends CRMBase{

    public static String enrollmentName="";
    public static String partnerName="";


    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Partner")
    public void testCreatePartner() {

        partnerName = createPartner(crmCOMUser1,crmCOMUser1Pwd);
        StepReport.info("Partner Created Successfully as: " + partnerName );
    }


    @Test(dependsOnMethods="testCreatePartner")
    @TestAuthor(createdBy = "amranaya", createdOn = "02/15/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Enrollment")
    public void testSubmitEnrollmentForApproval() {
        enrollmentName=submitEnrollmentforApproval(crmCOMUser1,crmCOMUser1Pwd,partnerName);
        StepReport.info("Enrollment Submitted for Approval: " + enrollmentName );
    }

}
