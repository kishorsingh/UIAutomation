package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "browser_certification","bug27123159" })
public class EmailNotificationsTest extends BPMBase {
    public static String TaskNumber="";

    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testSendEmailNotifications")
    public void testSendEmailNotifications() {
        verifySendEmailNotifications();
    }


    @Test(dependsOnMethods = "testSendEmailNotifications")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testcreateTaskForEmailNotifications")
    public void testcreateTaskForEmailNotifications() {
         TaskNumber=createTaskForEmailNotifications();
    }

    @Test(dependsOnMethods = "testcreateTaskForEmailNotifications")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testEmailNotifications_Approve")
    public void testEmailNotifications_Approve() {
        verifyEmailNotification("Approve",TaskNumber);
    }


    @Test(dependsOnMethods = "testEmailNotifications_Approve")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "createTaskForEmailNotifications")
    public void testcreateTaskForEmailNotifications2() {
        TaskNumber=createTaskForEmailNotifications();
    }

    @Test(dependsOnMethods="testcreateTaskForEmailNotifications2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testEmailNotifications_Reject")
    public void testEmailNotifications_Reject() {
          verifyEmailNotification("Reject",TaskNumber);
    }

    @Test(dependsOnMethods = "testEmailNotifications_Reject")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "createTaskForEmailNotifications")
    public void testcreateTaskForEmailNotifications3() {
        TaskNumber=createTaskForEmailNotifications();
    }

    @Test(dependsOnMethods="testcreateTaskForEmailNotifications3")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testEmailNotifications_RequestMoreInfo")
    public void testEmailNotifications_RequestMoreInfo() {
          verifyEmailNotification("Request More Info",TaskNumber);
    }


}
