package com.oracle.fa.qa.selenium.component.prc.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class PRCRequisitionRejectTest extends PRCBase{
	String requisitionNumber="";

    @Test
    @TestAuthor(createdBy = "amranaya", createdOn = "03/28/2018", version = "1.0")
    @TestDesc(desc = "Test Requisition Rule Setup")
    public void testConfigureRequisitionRule() {
        if(!isRequisitionRuleConfigured) {
            setupRequisitionApproval();
        }
        System.out.println("Requisition Rule Configuration Status : "+isRequisitionRuleConfigured);
    }

    @Test(dependsOnMethods="testConfigureRequisitionRule")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Create requisition")
    public void testCreateRequisition() {
		//Login as User1
		requisitionNumber= createRequisition(prcReqUser1,prcReqUser1Pwd,true);
    }

    @Test(dependsOnMethods="testCreateRequisition")
    @TestAuthor(createdBy = "yuezha", createdOn = "07/19/2018", version = "1.0")
    @TestDesc(desc = "test Bell Notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(prcReqUser2,prcReqUser2Pwd,requisitionNumber);
    }
	
	
	@Test(dependsOnMethods="testCreateRequisition")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Reject requisition")
    public void testRejectRequisition() {
		//Login as User1
		requisitionReportOperations(prcReqUser2,prcReqUser2Pwd,requisitionNumber,false,"reject",false,false);
    }

	
}
