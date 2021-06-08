package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups={"bug27561133"})
public class PRCBug27561133TriggerRuleAfterDelegate extends PRCBase {

    String requisitionNumber="";

    @Test(priority=1)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test Requisition Rule Setup")
    public void testConfigureRequisitionRule() {
        setupRequisitionApprovalForTriggerVacationRule();
    }

    @Test(priority=2)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test Requisition Rule Setup")
    public void testSetVacationRuleDelegate() {
        setupVacationDelegationRule();
    }

    @Test(dependsOnMethods="testConfigureRequisitionRule", priority=3)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test create new requisition")
    public void testCreateNewRequisition() {
        requisitionNumber = createRequisition(prcReqUser1,prcReqUser1Pwd,false);
    }

    @Test(dependsOnMethods="testCreateNewRequisition", priority=4)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test verify new requisition in bell notification")
    public void testVerifyBellNotificationLevel1() {
        checkBellNotificationWithLogin(prcReqUser2,prcReqUser2Pwd,requisitionNumber);
    }

    @Test(dependsOnMethods="testCreateNewRequisition", priority=5)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test approve requisition for level 1")
    public void testApproveRequisitionLevel1() {
        requisitionReportOperations(prcReqUser2,prcReqUser2Pwd,requisitionNumber,false,"approve",false,false);
    }

    @Test(dependsOnMethods="testApproveRequisitionLevel1", priority=6)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test requisition request is not in level 2")
    public void testRequisitionRequestNotInLevel2() {
        checkTaskNotInBellNotificationWithLogin(prcReqUser3,prcReqUser3Pwd,requisitionNumber, 5);
    }

    @Test(dependsOnMethods="testApproveRequisitionLevel1", priority=7)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test approve requisition by delegate user")
    public void testVerifyBellNotificationByDelegateUser() {
        checkBellNotificationWithLogin(prcReqUser10,prcReqUser10Pwd,requisitionNumber);
    }

    @Test(dependsOnMethods="testApproveRequisitionLevel1", priority=8)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test approve requisition by delegate user")
    public void testApproveRequisitionByDelegateUser() {
        requisitionReportOperations(prcReqUser10,prcReqUser10Pwd,requisitionNumber,false,"approve",false,false);
    }

    @Test(dependsOnMethods="testApproveRequisitionByDelegateUser", priority=9)
    @TestAuthor(createdBy = "yuezha", createdOn = "07/05/2018", version = "1.0")
    @TestDesc(desc = "Test requisition request is in level 3")
    public void testVerifyBellNotificationLevel3() {
        checkBellNotificationWithLogin(prcReqUser4,prcReqUser4Pwd,requisitionNumber);
    }

}
