package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.fin.test.FinBase;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups={"bug26976153","locale"})
public class Bug26976153DoubleByteCharacterComment extends FinBase {

    String expenseRepName = "";

    String username1="finuser1";
    String password1="Welcome1";
    String username2="finuser2";
    String password2="Welcome1";

    @Test(priority=1)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/11/2018", version = "1.0")
    @TestDesc(desc = "Create an expense report for future use")
    public void testCreateExpense() {
        expenseRepName = createExpenseReport(username1,password1,false);
    }

    @Test(dependsOnMethods="testCreateExpense", priority=2)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/11/2018", version = "1.0")
    @TestDesc(desc = "Verify bell notification")
    public void testVerifyBellNotification() {
        checkBellNotificationWithLogin(username2,password2,expenseRepName);
    }

    @Test(dependsOnMethods="testVerifyBellNotification", priority=3)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/11/2018", version = "1.0")
    @TestDesc(desc = "Test update report large comments")
    public void testUpdateExpenseReportComment() {
        String loopMessage = "私はウルトラマンです";
        System.out.println("Message to be looped for large comment is " + loopMessage);
        updateExpenseReportLargeComment(username2, password2, expenseRepName, loopMessage);
    }

}
