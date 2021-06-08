package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = { "browser_certification","bug28406206" })
public class Bug28406206EmailNotificationsTests extends BPMBase {
    public static String TaskNumber = "";
    public static String AliasName = "";

    //BUG 28406206 - RCA for bug 28338763 - Needs QA Test Case Automation
    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "24.Send Test Notification using EM")
    public void testSendEmailNotifications() {
        verifySendEmailNotifications();
    }

    @Test(dependsOnMethods = "testSendEmailNotifications")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "2.*Email From: Display Name* for Admin user in Human Task level")
    public void testEmailAliasTaskConf() {
        verifyEmailAliasTaskConf();
    }


    @Test(dependsOnMethods = "testEmailAliasTaskConf")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "3.EM validation (Global Level only)")
    public void testupdateAliasInWorklistApp() {
        AliasName = "NewAliasEMverify";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testupdateAliasInWorklistApp")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "3.EM validation (Global Level only)")
    public void testverifyAliasinEM() {
        verifyAliasNameInEM(AliasName);

    }

    @Test(dependsOnMethods = "testverifyAliasinEM")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "5.Notification Mail (Global level)")
    public void testcreateTaskForEmailNotifications1() {
        TaskNumber = createTaskForEmailNotifications();
    }

    @Test(dependsOnMethods = "testcreateTaskForEmailNotifications1")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "5.Notification Mail (Global level)")
    public void testEmailNotifications_Approve1() {
        verifyEmailNotification("Approve", TaskNumber);
    }

    @Test(dependsOnMethods = "testEmailNotifications_Approve1")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "4.Worklistapp validation (Global level only)")
    public void testEmailWLValidation() {
        AliasName = "NewAliasWLAppverify";
        updateAliasNameinEM(AliasName);
    }

    @Test(dependsOnMethods = "testEmailWLValidation")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "4.Worklistapp validation (Global level only)")
    public void testEmailWLValidation2() {
        verifyAliasNameInWorkListApp(AliasName);
    }

    @Test(dependsOnMethods = "testEmailWLValidation2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "5.Notification Mail (Global level)")
    public void testcreateTaskForEmailNotifications2() {
        TaskNumber = createTaskForEmailNotifications();
    }

    @Test(dependsOnMethods = "testcreateTaskForEmailNotifications2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "5.Notification Mail (Global level)")
    public void testEmailNotifications_Approve2() {
        verifyEmailNotification("Approve", TaskNumber);
    }

    @Test(dependsOnMethods = "testEmailNotifications_Approve2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "6.Special characters(Global level & Human task level)")
    public void testSpecialCharactersInAlias1() {
        AliasName = "SplAlias&_$-#";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testSpecialCharactersInAlias1")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "6.Special characters(Global level & Human task level)")
    public void testSpecialCharactersInAlias2() {
        TaskNumber = createTaskForEmailNotifications();
    }

    @Test(dependsOnMethods = "testSpecialCharactersInAlias2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "6.Special characters(Global level & Human task level)")
    public void testSpecialCharactersInAlias3() {
        verifyEmailNotification("Approve", TaskNumber);
        //verifyAliasInEmail();
    }

    @Test(dependsOnMethods = "testSpecialCharactersInAlias3")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "7.Nonsupport of special characters <> (Global level & Human task level)")
    public void testNonSupportSpecialCharsInAliasWL() {
        AliasName = "SplAlias&_$-#<_.>";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testNonSupportSpecialCharsInAliasWL")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "9.Localisation (Global level & Human task level)")
    public void testFrenchGermanCharsInAliasWL1() {
        AliasName = "测试";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testFrenchGermanCharsInAliasWL1")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "9.Localisation (Global level & Human task level)")
    public void testFrenchGermanCharsInAliasWL2() {
        AliasName = "Prüfung";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testFrenchGermanCharsInAliasWL2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "9.Localisation (Global level & Human task level)")
    public void testHebrewCharsInAliasWL() {
        AliasName = "אני אוהב";
        updateAliasInWorklistApp(AliasName);
    }


    @Test(dependsOnMethods = "testHebrewCharsInAliasWL")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "9.Localisation (Global level & Human task level) and 12.Uppercase and lowercase")
    public void testSymbolsCharsInAlias() {
        AliasName = "I❤Testing♠♣";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testHebrewCharsInAliasWL")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "11.Boundary value testing (Global level & Human task level)")
    public void testBoundaryValuesAlias1_1() {
        AliasName = "123Testing123Testing123Testing123Testing123Testing123Testing123Testing123Testing123Testing123Testing";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testBoundaryValuesAlias1_1")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "11.Boundary value testing (Global level & Human task level)")
    public void testBoundaryValuesAlias1_2() {
        verifyAliasNameInWorkListApp(AliasName);
    }

    @Test(dependsOnMethods = "testBoundaryValuesAlias1_2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "11.Boundary value testing (Global level & Human task level)")
    public void testBoundaryValuesAlias2_1() {
        AliasName = "123Testing123Testing123Testing123Testing123Testing123Testing123Testing123Testing123Testing";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testBoundaryValuesAlias2_1")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "11.Boundary value testing (Global level & Human task level)")
    public void testBoundaryValuesAlias2_2() {
        verifyAliasNameInWorkListApp(AliasName);
    }

    @Test(dependsOnMethods = "testBoundaryValuesAlias2_2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "11.Boundary value testing (Global level & Human task level)")
    public void testBoundaryValuesAlias3_1() {
        AliasName = "1";
        updateAliasInWorklistApp(AliasName);
    }

    @Test(dependsOnMethods = "testBoundaryValuesAlias3_1")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "13.Sender Name Text Field with empty value with double quotes at Human Task level")
    public void testBoundaryValuesAlias3_2() {
        verifyAliasNameInWorkListApp(AliasName);
    }

    @Test(dependsOnMethods = "testBoundaryValuesAlias3_2")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "13.Sender Name Text Field with empty value with double quotes at Human Task level")
    public void testEmptyAliasinHT() {
        AliasName = "";
        updateAliasNameInHumanTask(AliasName);
    }

    @Test(dependsOnMethods = "testEmptyAliasinHT")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "17.Sender Name text field with incorrect value at Human task level")
    public void testIncorrectAliasinHT() {
        AliasName = "Test\"IncorrectInput";
        updateAliasNameInHumanTask(AliasName);
    }

    @Test(dependsOnMethods = "testIncorrectAliasinHT")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "16.Sender Name text field with Previous Approver at Human task level")
    public void testPreviousOwnerinHT() {
        AliasName = "PreviousOwner";
        updateAliasNameInHumanTask(AliasName);
    }

    @Test(dependsOnMethods = "testIncorrectAliasinHT")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "19.Sender Name text field updated with comma")
    public void testCommaAliasinHT() {
        AliasName = "Test,CommaInAlias";
        updateAliasNameInHumanTask(AliasName);
    }



}
