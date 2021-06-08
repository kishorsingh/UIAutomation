package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMCreateNewOpportuinity extends CRMBase{
    public static String contactName="";
    public static String NewAccount="";

    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Partner")
    public void testCreateContact() {
        contactName=createContact();
        StepReport.info("Created New Contact Successfully as: ",contactName);
    }

    @Test(dependsOnMethods = "testCreateContact")
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Change Current Account")
    public void testChangeCurrent() {
        NewAccount=changeCurrentAccount(contactName);
        StepReport.info("Created New Opportunity Successfully as:"+NewAccount);
    }

    @Test(dependsOnMethods = "testChangeCurrent")
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Partner")
    public void testCheckNewAccount() {
        checkNewAccount(contactName,NewAccount);
    }

}
