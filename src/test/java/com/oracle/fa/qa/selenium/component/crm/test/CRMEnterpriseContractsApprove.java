package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMEnterpriseContractsApprove extends CRMBase {

    public static String operationName="";
    public static String contractNumber="";

    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Contracts")
    public void testCreateContracts() {
        operationName="SubmitForApproval";
        contractNumber=createContract(crmECUserName, crmECUserPassword);
        StepReport.info("Contract " +contractNumber+ " Created Successfully: ");
    }

    @Test(dependsOnMethods="testCreateContracts")
    @TestAuthor(createdBy = "amranaya", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - verify Bell notiifcation")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(crmECApproverName, crmECApproverPassword,contractNumber);
    }


    @Test(dependsOnMethods ="testVerifyBellNotification")
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/27/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Approve Dispute")
    public void testApproveContract() {
        operationName = "Approve";
        operatonsOnEnterpriseContracts(crmECApproverName, crmECApproverPassword,contractNumber,operationName);
        StepReport.info("Enterprise Contract Application Approved Successfully: ");
    }

}
