package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class PRCRequisitionManageApprovalsTest extends PRCBase {

    String requisitionNumber ="";

    @Test
    @TestAuthor(createdBy = "leshen", createdOn = "05/28/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Create requisition with CM76840")
    public void testCreateRequisition() {
        //Login as User18
        requisitionNumber = createRequisitionCM76840(prcReqUser18,prcReqUser18Pwd);
    }

    @Test(dependsOnMethods="testCreateRequisition")
    @TestAuthor(createdBy = "leshen", createdOn = "05/18/2018", version = "1.0")
    @TestDesc(desc = "Test PRC - Verify requisition")
    public void testVerifyRequisition() {
        //Login as User18
        verifyRequisitionCM76840(prcReqUser18,prcReqUser18Pwd, requisitionNumber);
    }


}
