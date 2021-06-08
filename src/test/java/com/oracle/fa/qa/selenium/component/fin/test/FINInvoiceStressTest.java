package com.oracle.fa.qa.selenium.component.fin.test;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import org.testng.annotations.Test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;

public class FINInvoiceStressTest extends FinBase{
	String invoiceNumber="";

    //@Test
    @TestAuthor(createdBy = "pbharga", createdOn = "05/10/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice Setup")
    public void testIsInvoiceSetupExists() {
        if(!isFinancialSetupExists) {
            setupInvoiceApproval("select");
        }
        System.out.println("Financial Setup Status : "+isFinancialSetupExists);
    }


    //@Test(dependsOnMethods="testIsInvoiceSetupExists")
    @Test(groups = { "stress" })
    @TestAuthor(createdBy = "pabharga", createdOn = "05/16/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - create Invoice")
    public void testInitiateInvoiceUsingQuery() {
        Boolean result;
        String invName;
        result = isDatabaseConnectionValid();

        for (int i = 0; i<10; i++){
            if (result){
                invName = createInvoiceUsingSQLInjection("INITIATE", 10, 3, true, 1200);
                System.out.println("Inv Name: " +  invName);
                if (invName == "FAILURE"){
                    break;
                }
                DriverUtil.sleep(3000L);
            }
        }

    }


    @Test(groups = { "stress" })
    @TestAuthor(createdBy = "pabharga", createdOn = "05/23/2018", version = "1.0")
    @TestDesc(desc = "Test Invoice - create Invoice")
    public void testCreateInvoiceInRequiredState() {
        Boolean result;
        String invName;
        result = isDatabaseConnectionValid();

        for (int i = 0; i<100; i++){
            if (result){
                invName = createInvoiceUsingSQLInjection("REQUIRED", 100, 3, false, 3000);
                System.out.println("Inv Name: " +  invName);
                if (invName == "FAILURE"){
                    break;
                }
                DriverUtil.sleep(3000L);
            }
        }

    }

}