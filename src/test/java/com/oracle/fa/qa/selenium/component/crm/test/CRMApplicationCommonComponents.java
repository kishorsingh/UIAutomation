package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

public class CRMApplicationCommonComponents extends CRMBase{

    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Enrollment")
    public void testCreateContracts() {
        String contractNumber="";
        createContract(crmECUserName, crmECUserPassword);
        StepReport.info("Contract " +contractNumber+ " Created Successfully: ");
    }

}
