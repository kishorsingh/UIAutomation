package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;
@Test(groups = { "browser_certification","bug27123159" })
public class Bug27123159TaskConfigParticipantsTest extends BPMBase {

    @Test
    @TestAuthor(createdBy = "leshen", createdOn = "07/23/2018", version = "1.0")
    @TestDesc(desc = "BPMBug27123159TaskConfigParticipants")
    public void testVerifyTaskConfiguration() {
        verifyTaskConfiguration();
    }

}
