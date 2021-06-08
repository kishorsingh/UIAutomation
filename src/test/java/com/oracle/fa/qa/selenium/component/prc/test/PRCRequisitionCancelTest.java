package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class PRCRequisitionCancelTest extends PRCBase{
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

		requisitionNumber= createRequisition(prcReqUser1,prcReqUser1Pwd,false);
    }

    @Test(dependsOnMethods="testCreateRequisition")
    @TestAuthor(createdBy = "yuezha", createdOn = "07/19/2018", version = "1.0")
    @TestDesc(desc = "test Bell Notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(prcReqUser2,prcReqUser2Pwd,requisitionNumber);
    }

	@Test(dependsOnMethods="testCreateRequisition")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Approve requisition")
    public void testCancelRequisition() {
		cancelRequisitionApproveTest(requisitionNumber);
    }

	
}
