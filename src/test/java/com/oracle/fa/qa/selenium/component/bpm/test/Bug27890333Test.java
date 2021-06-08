package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import org.testng.annotations.Test;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;


/**
 * Created by ashwaraj on 5/17/18.
 */
public class Bug27890333Test extends BPMBase{

    @Test
    @TestAuthor(createdBy = "ashwaraj", createdOn = "05/17/2018", version = "1.0")
    @TestDesc(desc = "Test BPM Login")
    public void testBPMLoginLogout() {

        BPMBase base = PageFactory.getPage(BPMBase.class);
        //bpmLogin(base);
       // bpmGoToDTRT(base);
        //bpmGoTogoToAssignee(base);
    }
}
