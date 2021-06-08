package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class PRCRequisitionConfigTest extends PRCBase {

    @Test
    @TestAuthor(createdBy = "YUEZHA", createdOn = "05/11/2018", version = "1.0")
    @TestDesc(desc = "Test create user defined attributes")
    public void testCreateUserDefinedAttributes() {
        createUserDefinedRequisitionApprovalAttributes();
    }

    @Test
    @TestAuthor(createdBy = "YUEZHA", createdOn = "05/17/2018", version = "1.0")
    @TestDesc(desc = "Test create approval group")
    public void testCreateApprovalGroup() {
        createApprovalGroup();
    }

}
