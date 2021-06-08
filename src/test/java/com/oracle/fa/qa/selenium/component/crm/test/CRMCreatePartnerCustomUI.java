package com.oracle.fa.qa.selenium.component.crm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

import java.util.Random;

@Test(groups = { "srg" })
public class CRMCreatePartnerCustomUI extends CRMBase{

    public static String sandBoxName="venki";


    @Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "01/24/2019", version = "1.0")
    @TestDesc(desc = "Test testCreateSandBox")
    public void testCreateSandBox() {
        int num = 1000;
        Random r = new Random();
        num = num + r.nextInt(1000);
        sandBoxName = sandBoxName+String.valueOf(num);
        if(isCustomThemeEnabled.equalsIgnoreCase("true")){
            StepReport.info("CustomTheme Enabled: " , isCustomThemeEnabled );
            if(checkifSandBoxExists(fusionUser,fusionUserPwd,sandBoxName)){
                publishSandBox(fusionUser,fusionUserPwd,sandBoxName);
            }
            createSandBox(fusionUser,fusionUserPwd,sandBoxName);
            StepReport.info("Sandbox Created successfully: "+ sandBoxName);
        }else{
            StepReport.info("Enable CustomTheme flag in testconfig.parameters");
        }
    }

    @Test(dependsOnMethods = "testCreateSandBox")
    @TestAuthor(createdBy = "vevinnak", createdOn = "01/24/2019", version = "1.0")
    @TestDesc(desc = "Test testModifyCustomUITheme")
    public void testModifyCustomUITheme()
    {
            modifytheme(fusionUser,fusionUserPwd);
    }

    @Test(dependsOnMethods = "testModifyCustomUITheme")
    @TestAuthor(createdBy = "vevinnak", createdOn = "01/24/2019", version = "1.0")
    @TestDesc(desc = "Test testModifyUIThemeOrangeToBlue")
    public void testModifyUIThemeOrangeToBlue()
    {
        modifythemeOrangeToBlue(fusionUser,fusionUserPwd);
    }



}
