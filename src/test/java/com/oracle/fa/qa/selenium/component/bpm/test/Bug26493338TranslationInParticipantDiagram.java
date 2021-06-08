package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups={"bug26493338","locale"})
public class Bug26493338TranslationInParticipantDiagram extends BPMBase {
    String userName="finuser22";
    String password="Welcome1";

    @Test(priority=1)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Change language to Korean")
    public void testChangeLanguageToKorean() {
        changeLanguageToKorean(userName, password);
    }

    @Test(priority=2)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Verify Assignee dialog is displayed without issue")
    public void testAssigneeDiagramDisplayed() {
        verifyAssigneeDiagramDisplayed(userName, password);
    }

    @Test(priority=3)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/02/2018", version = "1.0")
    @TestDesc(desc = "Change language to English")
    public void testChangeLanguageToEnglish() {
        changeLanguageToEnglish(userName, password);
    }
}
