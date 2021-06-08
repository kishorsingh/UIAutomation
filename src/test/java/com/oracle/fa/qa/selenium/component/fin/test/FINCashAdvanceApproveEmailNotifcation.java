package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class FINCashAdvanceApproveEmailNotifcation extends FinBase{
    public static String advanceRepName="";



    /*@Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testSendEmailNotifications")
    public void testSendEmailNotifications() {
        verifyTestSendEmailNotifications(soaDomain);
    }*/



	
	/*@Test
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Create Cash Advance Report")
    public void testCreateCashAdvance() {
		advanceRepName=createCashAdvance(finUser1,finUser1Pwd,false);
    }*/

    @Test//(dependsOnMethods="testCreateCashAdvance")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Action Email Notification")
    public void testActionEmailNotification() {
        actionEmailNotificationfromZimbra("Approve","EXM0152085545");
    }

	/*@Test(dependsOnMethods="testCreateCashAdvance")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Verify Bell Notification")
    public void testVerifyBellNotification() {
		checkBellNotificationWithLogin(finUser2,finUser2Pwd,advanceRepName);
    }

	@Test(dependsOnMethods="testCreateCashAdvance")
    @TestAuthor(createdBy = "bpadhy", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Test FIN Expense - Approve Cash Advance Report")
    public void testApproveCashAdvanceReport() {
		//Login as User2
		//checkBellNotification=true;
		cashAdvanceReportOperations(finUser2,finUser2Pwd,advanceRepName,false,"approve",false,false);
    }*/


}
