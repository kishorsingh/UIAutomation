package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups={"bug26186285"})
public class Bug26186285SwitchView extends BPMBase {

    @Test(priority=1)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Ensure view is in pin mode before testing")
    public void changeViewToPinMode() {
        changeViewToPinMode(finUser1, finUser1Pwd);
    }

    @Test(priority=2)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Test switch view in BPM worklist")
    public void testSwitchView() {
        switchView(finUser1, finUser1Pwd);
    }

    @Test(priority=3)
    @TestAuthor(createdBy = "YUEZHA", createdOn = "07/16/2018", version = "1.0")
    @TestDesc(desc = "Ensure view is in pin mode after testing")
    public void ensureViewInPinMode() {
        changeViewToPinMode(finUser1, finUser1Pwd);
    }
}
