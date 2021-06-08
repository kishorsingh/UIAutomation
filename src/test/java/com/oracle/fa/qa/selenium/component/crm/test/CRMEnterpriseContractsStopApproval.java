package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMEnterpriseContractsStopApproval extends CRMBase {

    public static String operationName="";
    public static String contractNumber="";

    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Enrollment")
    public void testCreateContracts() {
        contractNumber=createContract(crmECUserName, crmECUserPassword);
        StepReport.info("Contract " +contractNumber+ " Created Successfully: ");
    }


    @Test(dependsOnMethods ="testCreateContracts")
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/27/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Approve Dispute")
    public void testStopApproval() {
        operationName = "StopApproval";
        createContractStopApproval(crmECUserName, crmECUserPassword,contractNumber,operationName);
        StepReport.info("Enterprise Contract Application Stopped Successfully: ");
    }


}
