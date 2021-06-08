package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class CRMCreatePartner extends CRMBase{

    public static String partnerName="";
    public static Boolean customThemeEnabled=false;


    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "02/14/2018", version = "1.0")
    @TestDesc(desc = "Test CRM - Create Partner")
    public void testCreatePartner() {

            partnerName = createPartner(crmCOMUser1,crmCOMUser1Pwd);
            StepReport.info("Partner Created Successfully as: " + partnerName );

    }

}
