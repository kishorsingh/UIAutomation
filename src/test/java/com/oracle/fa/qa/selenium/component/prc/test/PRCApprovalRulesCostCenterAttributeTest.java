package com.oracle.fa.qa.selenium.component.prc.test;

import com.oracle.fa.qa.selenium.component.common.page.ManageRequisitionApprovals;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

public class PRCApprovalRulesCostCenterAttributeTest extends PRCBase {

    @Test
    @TestAuthor(createdBy = "leshen", createdOn = "06/01/2018", version = "1.0")
    @TestDesc(desc = "To verify that rules based on user-defined Cost Center attribute are created")
    public void testApprovalRulesUserDefinedCostCenterAttribute() {
        ManageRequisitionApprovals manageRequisitionApprovals = createUserDefinedAttribute("Cost Center Attribute");
        createRuleByCostCenterAttribute(manageRequisitionApprovals);
        System.out.println("2 Rules based on User-defined Cost Center attribute are created!");
    }
}