package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class PRCPOCancelTest extends PRCBase{
	String poNumber="";

	@Test
    @TestAuthor(createdBy = "AMRANAYA", createdOn = "04/06/2018", version = "1.0")
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
		poNumber=createPO(prcBuyerUser1,prcBuyerUser1Pwd,false);
    }

    @Test(dependsOnMethods="testCreatePO")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Approve PO")
    public void testCancelPO() {

        testCancelPO(poNumber);
    }
}
