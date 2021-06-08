package com.oracle.fa.qa.selenium.component.hcm.test;

import com.oracle.fa.qa.selenium.component.hcm.page.HireEmployeePage;
import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "srg" })
public class HCMHireEmployeeTest extends HCMBase {
    String empName="";

    @Test
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Create Employee Hire Rule")
    public void testIsHireAnEmployeeRuleConfigured() {
        if (!isHireEmployeeRuleConfigured) {
            configureEmployeeHireRule("Hire an Employee", "managementhierarchy", "");
        }
        System.out.println("Hire an Employee rule configured: "+isHireEmployeeRuleConfigured);
    }


    @Test(dependsOnMethods = "testIsHireAnEmployeeRuleConfigured")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Submit Employee Hire Request")
    public void testHireAnEmployee() {
        empName = hireEmployee("Employee");

    }

    @Test(dependsOnMethods = "testHireAnEmployee")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "verify Bell Notification for first level approver")
    public void verifyBellNotificationLevel1() {
        checkBellNotificationWithLogin("Level1", empName);

    }

    @Test(dependsOnMethods = "verifyBellNotificationLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve Employee Hire at Level1")
    public void approveHireLevel1(){
     firstLevelAction("Approve","sballard", empName);

    }

    @Test(dependsOnMethods = "approveHireLevel1")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "verify Bell Notification for second level approver")
    public void verifyBellNotificationLevel2() {
        checkBellNotificationWithLogin("Level2", empName);

    }

    @Test(dependsOnMethods = "verifyBellNotificationLevel2")
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "Approve Employee Hire at Level2")
    public void approveHireLevel2(){
        secondLevelAction("Approve","byarmouth", empName);

    }

/* VENKI: Transaction says in progreess, but acutally it is completed.
    @Test(dependsOnMethods = "approveHireLevel2", groups = { "srg" })
    @TestAuthor(createdBy = "mputtach", createdOn = "01/17/2018", version = "1.0")
    @TestDesc(desc = "check if Employee Hire transaction is in completed status")
    public void checkTransactionStatus(){
        checkTransactionStatus("Hire an Employee","Completed");

   }

*/

}
