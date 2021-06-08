package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

import java.util.Random;

public class PRCRequisitionUserDefinedCategoryAttributeApprovalTest extends PRCBase{
	String requisitionNumber="";
    String attributeName="Category Attribute Test";

    @Test(priority=1)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Test create approval group")
    public void testCreateApprovalGroup() {
        createApprovalGroup();
    }

    @Test(dependsOnMethods="testCreateApprovalGroup",priority=2)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/28/2018", version = "1.0")
    @TestDesc(desc = "To verify that Approval Rules can be created based on user-defined Category attribute")
    public void testApprovalRulesUserDefinedCategoryAttribute() {
        if(!isUserDefinedRuleConfigured) {
            setupUserDefinedApprovalRule(attributeName);
        }
        System.out.println("Approval Rules are created based on user-defined Category attribute Status");
    }

    @Test(dependsOnMethods="testApprovalRulesUserDefinedCategoryAttribute",priority = 3)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/28/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Update Requistion Preferences")
    public void testUpdateRequisitionPreferences() {
        //Login as User3
        updateRequisitionPreferences(prcReqUser3,prcReqUser3Pwd);
    }

	@Test(dependsOnMethods="testUpdateRequisitionPreferences",priority=4)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/28/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Create requisition with 3 lines")
    public void testCreateRequisitionThreeLines() {
		//Login as User3
        requisitionNumber=createRequisitionThreeLines(prcReqUser3,prcReqUser3Pwd);
    }

	@Test(dependsOnMethods="testCreateRequisitionThreeLines",priority=5)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Approve requisition by CVRQST04")
    public void testApproveRequisitionFirstApprover() {
		//Login as User4
		requisitionReportOperations(prcReqUser4,prcReqUser4Pwd,requisitionNumber,false,"approve",false,false);
    }

    @Test(dependsOnMethods="testApproveRequisitionFirstApprover",priority=6)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Reject requisition by CVRQST05")
    public void testRejectRequisitionSecondApprover() {
        //Login as User5
        requisitionReportOperations(prcReqUser5,prcReqUser5Pwd,requisitionNumber,false,"reject",false,false);
    }

    @Test(dependsOnMethods="testRejectRequisitionSecondApprover",priority=7)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - verify PRC status")
    public void verifyApprovalStatus() {
        verifyRequisitionStatus(prcReqUser3,prcReqUser3Pwd,requisitionNumber, "Rejected");
    }

    @Test(dependsOnMethods="testApprovalRulesUserDefinedCategoryAttribute",priority=8)
    @TestAuthor(createdBy = "amranaya", createdOn = "05/31/2018", version = "1.0")
    @TestDesc(desc = "Delete the Approval Rules created based on user-defined Category attribute")
    public void testDeleteApprovalRulesUserDefinedCategoryAttribute() {
        if(isUserDefinedRuleConfigured) {
            unSetupUserDefinedApprovalRule(attributeName);
        }
        System.out.println("User-defined Category attribute and Approval rules  are deleted");
    }

	
}
