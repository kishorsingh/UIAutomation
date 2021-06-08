package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMPublishProgramTest extends CRMBase{

    public static String crmPublishProgramName="";

    @Test
    @TestAuthor(createdBy = "amranaya", createdOn = "03/01/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Publish Program")
    public void testPublishProgram() {
        crmPublishProgramName=publishProgram(crmCOMUser1,crmCOMUser1Pwd);
        StepReport.info("Program Published Successfully as: " + crmPublishProgramName );
    }

}
