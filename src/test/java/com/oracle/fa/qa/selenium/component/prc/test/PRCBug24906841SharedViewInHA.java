package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = {"shared_views","ha_test","bug24906841", "bug27668714", "bug25744537"})
public class PRCBug24906841SharedViewInHA extends PRCBase {

    String viewName = "shared_view_to_CVRQST02";
    String sharedUser = "CVRQST02";

    @Test
    @TestAuthor(createdBy = "leshen", createdOn = "06/29/2018", version = "1.0")
    @TestDesc(desc = "Create a shared view")
    public void testCreateSharedView() {
        createSharedView(viewName, sharedUser);
    }

    @Test(dependsOnMethods="testCreateSharedView")
    @TestAuthor(createdBy = "leshen", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Verify shared view")
    public void testVerifySharedView() {
        verifySharedView(viewName, sharedUser, prcReqUser2Pwd);
    }
}
