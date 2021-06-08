package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class PRCApprovalRulesUserDefinedCategoryAttribute extends PRCBase {

    @Test
    @TestAuthor(createdBy = "amranaya", createdOn = "05/28/2018", version = "1.0")
    @TestDesc(desc = "To verify that Approval Rules can be created based on user-defined Category attribute")
    public void testApprovalRulesUserDefinedCategoryAttribute() {
        if(!isUserDefinedRuleConfigured) {
            setupUserDefinedApprovalRule("Category Attribute Test4");
        }
        System.out.println("Approval Rules are created based on user-defined Category attribute Status");
    }
}
