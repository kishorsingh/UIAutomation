package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.common.test.TestBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;
import com.oracle.fa.qa.selenium.component.fin.test.FinBase;

import java.util.List;

@Test(groups = { "bug27241158"})
public class Bug27241158BPMArchivalAssigneeData extends FinBase{
    String advanceRepName="";
    String userName="j,cooper";

	@Test
    @TestAuthor(createdBy = "amranaya", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Create an user with , in his name")
    public void testCreateUser() {
        createUser(userName);
    }

    @Test(dependsOnMethods="testCreateUser")
    @TestAuthor(createdBy = "amranaya", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Advance Request")
    public void testCreateCashAdvanceRequest() {
        advanceRepName=createCashAdvance(finUser4,finUser4Pwd,false);
    }

    @Test(dependsOnMethods="testCreateCashAdvanceRequest")
    @TestAuthor(createdBy = "amranaya", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - ReAssign Cash Advance Request")
    public void testReAssignCashAdvanceRequest() {
        reAssignTask(finUser5,finUser5Pwd,advanceRepName,userName, "Delegate");
    }

    @Test(dependsOnMethods="testReAssignCashAdvanceRequest")
    @TestAuthor(createdBy = "amranaya", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Test BUG 27241158 - BPM ARCHIVAL - ASSIGNEE DATA NOT POPULATED DUE TO LARGE SIZE ASSIGNEE_TYPE - Verify ASSIGNEE and TYPE")
    public void testVerifyAssigneeAndType() {

        List<String> result = getAssigneeAndType(userName);
        StepReport.assertEquals("Verify that assignee is populated with comma: BUG 27241158 - BPM ARCHIVAL - ASSIGNEE DATA NOT POPULATED DUE TO LARGE SIZE ASSIGNEE_TYPE",result.get(0),userName);
        StepReport.assertEquals("Verify that type is user: BUG 27241158 - BPM ARCHIVAL - ASSIGNEE DATA NOT POPULATED DUE TO LARGE SIZE ASSIGNEE_TYPE",result.get(1),"user");

    }

}
