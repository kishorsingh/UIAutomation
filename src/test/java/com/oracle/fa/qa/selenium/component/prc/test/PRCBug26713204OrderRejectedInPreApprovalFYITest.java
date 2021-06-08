package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = {"bug26713204"})
public class PRCBug26713204OrderRejectedInPreApprovalFYITest extends PRCBase {

    String poNumber="";

    @Test
    @TestAuthor(createdBy = "leshen", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Preapproval FYI Rule Creation")
    public void testConfigurePreapprovalFYIRule() {
        setupConfigurePreapprovalFYIRule();
    }

    @Test(dependsOnMethods="testConfigurePreapprovalFYIRule")
    @TestAuthor(createdBy = "leshen", createdOn = "07/03/2018", version = "1.0")
    @TestDesc(desc = "Create an FYI order")
    public void testCreatePurchaseOrder() {
        poNumber = createPO(prcBuyerUser1, prcBuyerUser1Pwd,false);
    }

    @Test(dependsOnMethods="testCreatePurchaseOrder")
    @TestAuthor(createdBy = "leshen", createdOn = "07/04/2018", version = "1.0")
    @TestDesc(desc = "Verify and dismiss FYI task")
    public void testVerifyAndDismissFYITaskInWorklist() {
        poReportOperations(prcBuyerUser1,prcBuyerUser1Pwd,poNumber,false,"dismiss",false,false);
    }

    @Test(dependsOnMethods="testVerifyAndDismissFYITaskInWorklist")
    @TestAuthor(createdBy = "leshen", createdOn = "07/10/2018", version = "1.0")
    @TestDesc(desc = "Approve PO task")
    public void testApprovePOTask() {
        poReportOperations(prcBuyerUser2,prcBuyerUser2Pwd,poNumber,false,"approve",false,false);
    }

}
