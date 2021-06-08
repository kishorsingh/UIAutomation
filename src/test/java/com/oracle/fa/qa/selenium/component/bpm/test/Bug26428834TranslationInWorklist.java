package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.common.page.SetPreferences;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.annotations.Test;

@Test(groups={"bug26428834","locale"})
public class Bug26428834TranslationInWorklist extends BPMBase {
    String userName="finuser21";
    String password="Welcome1";

    @Test(priority=1)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/20/2018", version = "1.0")
    @TestDesc(desc = "Save resignation request to add a task to oneself")
    public void testSaveResignationRequest() {
        saveResignationRequest(userName, password);
    }

    @Test(priority=2)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "06/27/2018", version = "1.0")
    @TestDesc(desc = "Change language to Korean")
    public void testChangeLanguageToKorean() {
        changeLanguageToKorean(userName, password);
    }

    @Test(priority=3)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "06/29/2018", version = "1.0")
    @TestDesc(desc = "Verify all items in BPM worklist are translated into Korean")
    public void testWorklistElementsInKorean() {
        verifyWorklistElementsInKorean(userName, password);
    }

    @Test(priority=4)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "06/29/2018", version = "1.0")
    @TestDesc(desc = "Change language to English")
    public void testChangeLanguageToEnglish() {
        changeLanguageToEnglish(userName, password);
    }

}
