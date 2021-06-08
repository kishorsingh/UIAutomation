package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.fin.test.FinBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups={"bug26438182"})
public class Bug26438182MissingIcons extends FinBase {

    String expenseRepName = "";

    String username1="finuser1";
    String password1="Welcome1";
    String username2="finuser2";
    String password2="Welcome1";
    String user2FullName = "Ted Brown";

    @Test(priority=1)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/30/2018", version = "1.0")
    @TestDesc(desc = "Create an expense report for future use")
    public void testCreateExpense() {
        expenseRepName = createExpenseReport(username1,password1,false);
    }

    @Test(dependsOnMethods="testCreateExpense", priority=2)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/30/2018", version = "1.0")
    @TestDesc(desc = "Verify bell notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(username2,password2,expenseRepName);
    }

    @Test(priority=3)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/30/2018", version = "1.0")
    @TestDesc(desc = "Verify BPM worklist display")
    public void testUpdateExpenseReportComment() {
        verifyWorklistDisplay(username2,password2, user2FullName);
    }

}
