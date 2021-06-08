package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "bug23280545"})
public class FINBug23280545VerifyDimensionID extends FinBase{
	
	@Test
    @TestAuthor(createdBy = "amranaya", createdOn = "05/24/2018", version = "1.0")
    @TestDesc(desc = "Check the value of DimensionId field for repeated stage when Collection type is InvoiceLine")
    public void verifyDimensionIDRepeatedStageInvoiceLine() {
	    String expectedDimensionID="InvoiceLine.InvoiceId.toString().concat(InvoiceLine.LineNumber.toString())";
        verifyDimensionID(faAdminUser,faAdminUserPwd, "Repeated", "InvoiceLine", expectedDimensionID);
    }

	@Test
    @TestAuthor(createdBy = "amranaya", createdOn = "05/24/2018", version = "1.0")
    @TestDesc(desc = "Check the value of DimensionId field for repeated stage when Collection type is InvoiceDistribution")
    public void verifyDimensionIDRepeatedStageInvoiceDistribution() {
        String expectedDimensionID="InvoiceDistribution.InvoiceDistributionId.toString()";
        verifyDimensionID(faAdminUser,faAdminUserPwd, "Repeated", "InvoiceDistribution", expectedDimensionID);
    }

    @Test
    @TestAuthor(createdBy = "amranaya", createdOn = "05/24/2018", version = "1.0")
    @TestDesc(desc = "Check the value of DimensionId field for Non-repeated stage")
    public void verifyDimensionIDNonRepeatedStage() {
        String expectedDimensionID="null";
        verifyDimensionID(faAdminUser,faAdminUserPwd, "NonRepeated", null, expectedDimensionID);
    }

}
