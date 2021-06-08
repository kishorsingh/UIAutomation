package com.oracle.fa.qa.selenium.component.prc.test;

import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

@Test(groups = { "srg" })
public class PRCPOApproveTest extends PRCBase{
	String poNumber="";

	@Test
    @TestAuthor(createdBy = "amranaya", createdOn = "04/06/2018", version = "1.0")
    @TestDesc(desc = "Test Purchase Order Rule Setup")
    public void testConfigurePORule() {
        if(!isPORuleConfigured) {
            setupPOApproval();
        }
        System.out.println("Purchase Order Rule Configuration Status : "+isPORuleConfigured);
    }

	@Test(dependsOnMethods="testConfigurePORule")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "testCreatePO")
    public void testCreatePO() {
		//Login as User1
		poNumber=createPO(prcBuyerUser1,prcBuyerUser1Pwd,false);
    }

	@Test(dependsOnMethods="testCreatePO")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "test Bell Notification")
    public void testVerifyBellNotification() {
		checkBellNotificationWithLogin(prcBuyerUser2,prcBuyerUser2Pwd,poNumber);
    }
    
    @Test(dependsOnMethods="testVerifyBellNotification")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Approve PO")
    public void testApprovePO() {
		//Login as User1
		poReportOperations(prcBuyerUser2,prcBuyerUser2Pwd,poNumber,false,"approve",false,false);
    }

	
}
