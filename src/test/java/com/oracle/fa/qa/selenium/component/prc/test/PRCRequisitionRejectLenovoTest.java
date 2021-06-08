package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class PRCRequisitionRejectLenovoTest extends PRCBase {

    String requisitionNumber="";

    @Test
    @TestAuthor(createdBy = "leshen", createdOn = "05/22/2018", version = "1.0")
    @TestDesc(desc = "Test Requisition Rule Setup")
    public void testConfigureRequisitionRule() {
        if(!isRequisitionLenovoRuleConfigured) {
            setupRequisitionLenovoApproval();
        }
        System.out.println("Requisition Rule Configuration Status : "+isRequisitionLenovoRuleConfigured);
    }

    @Test(dependsOnMethods="testConfigureRequisitionRule")
    @TestAuthor(createdBy = "leshen", createdOn = "05/18/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Create requisition with Lenovo Laptop")
    public void testCreateRequisition() {
        //Login as User3
        requisitionNumber=createRequisitionLenovo(prcReqUser3,prcReqUser3Pwd);
    }

    @Test(dependsOnMethods="testCreateRequisition")
    @TestAuthor(createdBy = "yuezha", createdOn = "07/19/2018", version = "1.0")
    @TestDesc(desc = "test Bell Notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(prcReqUser4,prcReqUser4Pwd,requisitionNumber);
    }

    @Test(dependsOnMethods="testCreateRequisition")
    @TestAuthor(createdBy = "leshen", createdOn = "05/18/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Reject requisition")
    public void testRejectRequisition() {
        //Login as User4
        requisitionReportOperations(prcReqUser4,prcReqUser4Pwd,requisitionNumber,false,"reject",false,false);
    }
}
