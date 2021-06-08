package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMObjectAppointmentTest extends CRMBase{

    public static String crmCreateAppointmnetUser="";
    public static String crmCreateAppointmnetPassword="";
    public static String crmVerifyAppointmnetUser="";
    public static String crmVerifyAppointmnetPassword="";

    @Test
    @TestAuthor(createdBy = "amranaya", createdOn = "03/02/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Sandbox")
    public void testCreateSandBox() {
        createSandBox(crmVerifyAppointmnetUser,crmVerifyAppointmnetPassword,"TestCBox5");
        StepReport.info("Sanbox Created Successfully");
    }

    @Test(dependsOnMethods = "testCreateSandBox")
    @TestAuthor(createdBy = "amranaya", createdOn = "03/02/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - BPMNotification_DeepLink_ActivityObject_Appointment")
    public void testCreateAppointment() {
        createAppointment(crmVerifyAppointmnetUser,crmVerifyAppointmnetPassword);
        StepReport.info("Appointment  Successfully Created as: " );
    }

    @BeforeClass
    public final void initializeCRMTestData() throws Exception {
        getCRMTestData();
    }
    void getCRMTestData() {
        crmCreateAppointmnetUser= FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oa.caUserName");
        crmCreateAppointmnetPassword= FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oa.caUserPassword");
        crmVerifyAppointmnetUser= FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oa.vaUserName");
        crmVerifyAppointmnetPassword= FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oa.vaUserPassword");
    }
}
