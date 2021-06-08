package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMEnterpriseContractsReject extends CRMBase {

    public static String operationName="";
    public static String contractNumber="";

    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Enrollment")
    public void testCreateContracts() {
        operationName="SubmitForApproval";
        contractNumber=createContract(crmECUserName, crmECUserPassword);
        StepReport.info("Contract " +contractNumber+ " Created Successfully: ");
    }


    @Test(dependsOnMethods ="testCreateContracts")
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/27/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Approve Dispute")
    public void testRejectContract() {
        operationName = "Reject";
        operatonsOnEnterpriseContracts(crmECApproverName, crmECApproverPassword,contractNumber,operationName);
        StepReport.info("Create Dispute Application Rejected Successfully: ");
    }


}
