package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class ConfigurePORule extends PRCBase {

    @Test
    @TestAuthor(createdBy = "amranaya", createdOn = "03/28/2018", version = "1.0")
    @TestDesc(desc = "Test Purchase Order Rule Setup")
    public void testConfigurePORule() {
        if(!isPORuleConfigured) {
            setupPOApproval();
        }
        System.out.println("Purchase Order Rule Configuration Status : "+isPORuleConfigured);
    }
}
