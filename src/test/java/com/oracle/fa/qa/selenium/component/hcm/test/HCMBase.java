package com.oracle.fa.qa.selenium.component.hcm.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import com.oracle.fa.qa.selenium.component.bpm.common.CommandBuilder;
import com.oracle.fa.qa.selenium.component.bpm.common.CommandExecutor;
import com.oracle.fa.qa.selenium.component.common.page.*;
import com.oracle.fa.qa.selenium.component.common.test.TestBase;
import com.oracle.fa.qa.selenium.component.hcm.page.*;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.SkipException;


public class HCMBase extends TestBase {
    public static String locationCode;

    public void configureEmployeeHireRule(String ruleName, String condition, String user){
        configureRule(ruleName,condition,user);
        if(ruleName.equalsIgnoreCase("Hire an Employee")){
            if(condition.equalsIgnoreCase("managementhierarchy")) {
                isHireEmployeeRuleConfigured=true;
                isAutoHireEmployeeRuleConfigured=false;
            }
            if(condition.equalsIgnoreCase("SelfApproval")) {
                isAutoHireEmployeeRuleConfigured = true;
                isHireEmployeeRuleConfigured=false;
            }
        }
    }


    public void deleteHireEmpRule(String ruleName, String condition, String user){
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        TransactionConsole tc = nav.clickTransConsole();
        ApprovalRulesPage ar = tc.navigateToRules();
        ar.searchRule(ruleName);
        ConfigureRulePage crp = ar.clickConfigureRule();
        DriverUtil.sleep(3000);
        crp.configureRule(ruleName,condition,user);
        logout();
    }

    public void configureRule(String ruleName, String condition, String user) {

        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        TransactionConsole tc = nav.clickTransConsole();
        ApprovalRulesPage ar = tc.navigateToRules();
        ar.searchRule(ruleName);
        ConfigureRulePage crp = ar.clickConfigureRule();
        crp.configureRule(ruleName,condition,user);
        logout();

    }
    public void configureBypassApproval(String ruleName){
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        TransactionConsole tc = nav.clickTransConsole();
        ApprovalRulesPage ar = tc.navigateToRules();
        ar.searchRule(ruleName);
        if(ruleName.equalsIgnoreCase("Terminate")){
        ar.enableBypassapproval();}
        else {
            ar.disableBypassapproval();
        }
        logout();
    }

    public void verifyTransactionConsole(String transactionName,String status)
    {
        login(hcmUser1,hcmUser1Pwd);
        Navigator navigator =homePage.clickNavigator();
        TransactionConsole transactionConsole = navigator.clickTransConsole();
        transactionConsole.clickTransactions(status);
        DriverUtil.sleep(5000);
        transactionConsole.checkTransactionStatusforTasks(transactionName);
        logout();

    }

    public String createGoal() {
        String gname="";
        login(hcmUser4, hcmUser4Pwd);
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        DriverUtil.sleep(3000);
        CareerPerformance ch=nav.clickCareerPerf();
        DriverUtil.sleep(3000);
        AddGoal addGoal = ch.addGoal();
        DriverUtil.sleep(5000);
        gname=addGoal.typeGoalName();
        addGoal.typeTargetCompletionDate();
        addGoal.clickSubmit();
        addGoal.clickConfirm();
        logout();
        return gname;

    }


    public void changemaritalrule(String faEnvVersion ) {
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        PersonalInfo ch=nav.clickPersonalinfo();
        DriverUtil.sleep(3000);
        StepReport.info("Navigation to personal info page successful");
        PersonalInfo ch1=nav.clickPersonalinfo();
        DriverUtil.sleep(1000);
        String trimstr=ch1.getMaritalStatus(faEnvVersion);
        ContactInformationPage cip= ch.editmydetails();
        cip.changeMaritaldropdown(trimstr);
        logout();
    }

    public void changemaritalstatus(String maritalstatus ) {
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        PersonalInfo ch=nav.clickPersonalinfo();
        DriverUtil.sleep(3000);
        StepReport.info("Navigation to personal info page successful");
        PersonalInfo ch1=nav.clickPersonalinfo();
        DriverUtil.sleep(1000);
        String trimstr=ch1.getMaritalStatus(faEnvVersion);
        ContactInformationPage cip= ch.editmydetails();
        cip.changeMaritaldropdown(trimstr);
        logout();
    }



    public void firstLevelWorklist(String action,String user, String empName){
        login(hcmUser2,hcmUser2Pwd);
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        homePage.clickMoredetails();
        DriverUtil.sleep(10000);
        //Child Window handle 1
        Set<String> windowHandle = driver.getWindowHandles();
        for(String s: windowHandle) {
            if (!mainWindow.equalsIgnoreCase(s)) {
                driver.switchTo().window(s);
                DriverUtil.sleep(6000);
                StepReport.info("Searching employee");
                driver.findElement(By.xpath("//*[contains(@id,'keywordFilter::content')]")).sendKeys(empName);
                DriverUtil.sleep(4000);
                driver.findElement(By.xpath(".//img[@title='Search']")).click();
                StepReport.info("Select the task containing "+empName+" and reject it");
                DriverUtil.sleep(2000);
                driver.findElement(By.xpath("//*[contains(text(),'"+empName+"')]")).click();
                DriverUtil.sleep(2000);
                ActionsPage actionsPage = PageFactory.getPage(ActionsPage.class);
                driver.findElement(By.xpath("//a[text()='Actions']")).click();
                DriverUtil.sleep(2000);
                driver.findElement(By.xpath("//*[contains(text(),'Reject')]")).click();
                DriverUtil.sleep(2000);
                StepReport.info("Successfully rejected the task");

            }

        }
        driver.switchTo().window(mainWindow);
        logout();

    }

    public String promoteEmployee(String managerName) {
        String promotePersonNumber = "";
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Successful");
        Navigator nav = homePage.clickNavigator();
        DirectoryPage dp = nav.clickDirectory();
        DriverUtil.sleep(2000);
        dp.searchEmployee(managerName);
        StepReport.info("Search begins for employee : " + managerName);
        EmployeeInformationPage eip = dp.selectEmployee2(managerName);
        DriverUtil.sleep(10000);
        OrgChartPage ocp = eip.clickOrganizationChart();
        DriverUtil.sleep(6000);
        ocp.clickActionLinkForPromotion(managerName);
        ocp.clickEmploymentLink();
        DriverUtil.sleep(5000);
        PromotionPage pp = ocp.clickPromote();
        DriverUtil.sleep(5000);
        pp.clickContinueButton();
        DriverUtil.sleep(3000);
        pp.clickContinueButtonInPage();
        DriverUtil.sleep(3000);
        pp.clickSubmitButton();
        DriverUtil.sleep(10000);
        return promotePersonNumber;
    }

    public String enterContingentWorkerDetails(String empType){
        String empName="";
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Successful");
        Navigator nav = homePage.clickNavigator();
        NewPersonPage newPersonPage=nav.clickNewPerson();
        newPersonPage.clickTask();
        HireEmployeePage hireEmployeePage=newPersonPage.selectTask(empType);
        hireEmployeePage.waitUntilPageLoaded("Add a Contingent Worker: Identification");
        empName= hireEmployeePage.fillLastName();
        DriverUtil.sleep(2000);
        hireEmployeePage.selectLegalEmployer();
        System.out.println(empName);
        DriverUtil.sleep(2000);
        StepReport.info("Identification Page");
        hireEmployeePage.clickNext();
        hireEmployeePage.waitUntilPageLoaded("Add a Contingent Worker: Person Information");
        hireEmployeePage.typeZipCode();
        DriverUtil.sleep(8000);
        hireEmployeePage.fillAddress2();
        DriverUtil.sleep(3000);
        hireEmployeePage.fillAddress();
        DriverUtil.sleep(3000);
        StepReport.info("Personal Info Page");
        hireEmployeePage.clickNext();
        hireEmployeePage.waitUntilPageLoaded("Add a Contingent Worker: Employment Information");
        hireEmployeePage.enterBu();
        DriverUtil.sleep(3000);
        StepReport.info("Employment Info Page");
        hireEmployeePage.clickNext();
        hireEmployeePage.waitUntilPageLoaded("Add a Contingent Worker: Compensation and Other Information");
        StepReport.info("Compenstation and other info Page");
        hireEmployeePage.clickNext();
        hireEmployeePage.waitUntilPageLoaded("Add a Contingent Worker: Review");
        StepReport.info("Review Page");
        hireEmployeePage.verifyHistoryTable();
        hireEmployeePage.clickSave();
        DriverUtil.sleep(3000);
        hireEmployeePage.clickSubmit();
        DriverUtil.sleep(3000);
        StepReport.info("Submitted Add a Contingent Worker Task");
        logout();
        return empName;
    }

    public String enterNonWorkerDetails(String empType) {
        String empName="";
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Logged in..");
        Navigator nav = homePage.clickNavigator();
        NewPersonPage newPersonPage = nav.clickNewPerson();
        newPersonPage.clickTask();
        HireEmployeePage hireEmployeePage = newPersonPage.selectTask(empType);
        DriverUtil.sleep(3000);
        hireEmployeePage.LegalEmployerforNonworker();
        DriverUtil.sleep(2000);
        hireEmployeePage.selectNonWorkerType();
        DriverUtil.sleep(2000);
        empName= hireEmployeePage.fillLastName();
        DriverUtil.sleep(2000);
        StepReport.info("Nonworker name:",empName);
        hireEmployeePage.clickNext();
        DriverUtil.sleep(3000);
        hireEmployeePage.typeZipCode();
        DriverUtil.sleep(8000);
        hireEmployeePage.fillAddress2();
        DriverUtil.sleep(3000);
        hireEmployeePage.fillAddress();
        DriverUtil.sleep(3000);
        hireEmployeePage.clickNext();
        DriverUtil.sleep(3000);
        hireEmployeePage.enterBu();
        DriverUtil.sleep(3000);
        StepReport.info("Employment Info Page");
        hireEmployeePage.clickNext();
        hireEmployeePage.waitForCompensationAndOtherInformationPage();
        DriverUtil.sleep(3000);
        StepReport.info("Compensation and other info Page");
        hireEmployeePage.clickNext();
        hireEmployeePage.waitForReviewPage();
        DriverUtil.sleep(3000);
        StepReport.info("Review Page");
        hireEmployeePage.clickSave();
        DriverUtil.sleep(3000);
        hireEmployeePage.clickSubmit();
        DriverUtil.sleep(3000);
        StepReport.info("Submitted Add Nonworker Task");
        logout();
        return empName;
    }

    protected void checkBellNotificationWithLogin(String userLevel, String empName) {
        DriverUtil.sleep(30000L);
        if(userLevel.equalsIgnoreCase("Level1")){
            login(hcmUser2,hcmUser2Pwd);
        }
        if(userLevel.equalsIgnoreCase("Level2")){
            login(hcmUser3,hcmUser3Pwd);
        }

        StepReport.info("Login Test successful");
        BellNotification bellNotification=homePage.clickBellNotification();
        DriverUtil.sleep(20000L);
        // bellNotification.waitForReportAndReTry(expenseReportName, homePage);
        StepReport.assertTrue("Expense report displayed Bell Notification ",
                bellNotification.waitForReportAndReTry(empName,homePage));
        logout();
    }


    protected void checkNOBellNotificationWithLogin(String userLevel, String empName) {
        DriverUtil.sleep(30000L);
        if(userLevel.equalsIgnoreCase("Level0")){
            login(hcmUser1,hcmUser1Pwd);
        }
        if(userLevel.equalsIgnoreCase("Level1")){
            login(hcmUser2,hcmUser2Pwd);
        }
        if(userLevel.equalsIgnoreCase("Level2")){
            login(hcmUser3,hcmUser3Pwd);
        }

        StepReport.info("Login Test successful");
        BellNotification bellNotification=homePage.clickBellNotification();
        DriverUtil.sleep(20000L);
        // bellNotification.waitForReportAndReTry(expenseReportName, homePage);
        StepReport.assertFalse("Expense report displayed Bell Notification ",
                bellNotification.waitForReportAndReTry(empName,homePage));
        logout();
    }



    public void approvalforbypassAction(String actionName){
        login(hcmUser1,hcmUser1Pwd);
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        wrkListNotAppr.typeFilter(actionName);
        wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        wrkListNotAppr.clickOnExpenseReport(actionName);
        DriverUtil.sleep(10000);
        Set<String> windowHandle = driver.getWindowHandles();
        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s))
            {


                driver.switchTo().window(s);
                DriverUtil.waitForElementVisible("//div/a/span[text()='Approve']",10);

                driver.findElement(By.xpath("//div/a/span[text()='Approve']")).click();

            }
        }
        driver.switchTo().window(mainWindow);
        logout();

    }

    public void approveAction(String empName,String level){
        if(level.equalsIgnoreCase("First")){
            login(hcmUser2,hcmUser2Pwd);
        }
        else if(level.equalsIgnoreCase("FirstGoal")){
            login(hcmUser5,hcmUser5Pwd);
        }
        else {
            login(hcmUser3,hcmUser3Pwd);
        }

       // checkBellNotification();
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        wrkListNotAppr.searchRequestUntilAppear(empName);
//        wrkListNotAppr.typeFilter(empName);
//        wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        wrkListNotAppr.clickOnExpenseReport(empName);
        DriverUtil.sleep(10000);
        Set<String> windowHandle = driver.getWindowHandles();

        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s))
            {

                // Switching to Child window
                driver.switchTo().window(s);
                String xpath = "//button[text()='Approve'] | //span[text()='Approve']";
                WebElement approveButton = DriverUtil.getElement(By.xpath(xpath));
                PageLoadHelper.waitForElementVisible(approveButton);
                DriverUtil.clickByJS(approveButton);

                String submitXpath= "//span[text()='Submit']";
                WebElement submitButton = DriverUtil.waitForElementPresent(By.xpath(submitXpath), 20);
                StepReport.info("Click to Submit");
                DriverUtil.sleep(1000);
                DriverUtil.clickByJS(submitButton);
                DriverUtil.sleep(2000);

                       // .sendKeys(Keys.RETURN);
                //DriverUtil.sleep(10000);

                //driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        logout();
        // Switching to Parent window i.e Main Window.
    }

    public String enterNewHireDetails(String empType){
        String empName="";
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Successful");
        Navigator nav = homePage.clickNavigator();
        NewPersonPage newPersonPage=nav.clickNewPerson();
        newPersonPage.clickTask();
        HireEmployeePage hireEmployeePage=newPersonPage.selectTask(empType);
        DriverUtil.sleep(2000);
        hireEmployeePage.selectLegalEmployer();
        DriverUtil.sleep(2000);
        empName= hireEmployeePage.fillLastName();
        System.out.println(empName);
        DriverUtil.sleep(2000);
        StepReport.info("Identification Page");
        hireEmployeePage.clickNext();
        hireEmployeePage.typeZipCode();
        DriverUtil.sleep(8000);
        hireEmployeePage.fillAddress2();
        DriverUtil.sleep(3000);
        hireEmployeePage.fillAddress();
        DriverUtil.sleep(3000);
        StepReport.info("Personal Info Page");
        hireEmployeePage.clickNext();
        DriverUtil.sleep(3000);
        hireEmployeePage.enterBu();
        DriverUtil.sleep(3000);
        StepReport.info("Employment Info Page");
        hireEmployeePage.clickNext();
        DriverUtil.sleep(3000);
        hireEmployeePage.waitForOtherCompensationPage();
        StepReport.info("Compenstation and other info Page");
        hireEmployeePage.clickNext();
        DriverUtil.sleep(10000);
        hireEmployeePage.waitForReviewPage();
        StepReport.info("Review Page");
        hireEmployeePage.verifyHistoryTable();
        hireEmployeePage.clickSave();
        DriverUtil.sleep(3000);
        hireEmployeePage.clickSubmit();
        DriverUtil.sleep(3000);
        StepReport.info("Submited Hire Employee Task");
        logout();
        return empName;
    }
    public void rootUserAction(String action, String user, String empName){
        login(hcmUser1,hcmUser1Pwd);
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        if(action.equalsIgnoreCase("Resume")){
            wrkListNotAppr.selectSuspendedTaks("Suspended");

        }
        if(action.equalsIgnoreCase("Withdraw")){
            wrkListNotAppr.switchToInitiatedTasks();
        }
        wrkListNotAppr.typeFilter(empName);
        wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        wrkListNotAppr.clickOnExpenseReport(empName);
        DriverUtil.sleep(10000);
        Set<String> windowHandle = driver.getWindowHandles();

        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s))
            {

                // Switching to Child window
                driver.switchTo().window(s);
                DriverUtil.sleep(4000);
                ActionsPage actionsPage = PageFactory.getPage(ActionsPage.class);
                //DriverUtil.waitForElementVisible("//div/a/span[text()='"+action+"']",10);
                if(action.equalsIgnoreCase("Approve")||action.equalsIgnoreCase("Reject")
                        ||action.equalsIgnoreCase("Suspend")||action.equalsIgnoreCase("Resume")
                        ||action.equalsIgnoreCase("Withdraw")){
                    actionsPage.performAction(action);
                }
                else {
                    actionsPage.selectMoreOptions(action,user);
                }

                // driver.findElement(By.xpath("//div/a/span[text()='"+action+"']")).click();
                // .sendKeys(Keys.RETURN);
                //DriverUtil.sleep(10000);

                //driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        if(action.equalsIgnoreCase("Resume")){
            wrkListNotAppr.revertToAssignedTaks();
            DriverUtil.sleep(4000);
        }
        if(action.equalsIgnoreCase("Withdraw")){
            wrkListNotAppr.revertToAssignedTaks();
        }
        logout();
        // Switching to Parent window i.e Main Window.


    }
    public String changeManager(String employee) {
        String ChangeManagerPersonNum="";
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Successful");
        Navigator nav = homePage.clickNavigator();
        DirectoryPage dp = nav.clickDirectory();
        DriverUtil.sleep(2000);
        StepReport.info("Searching begins for employee "+employee);
        dp.searchEmployee(employee);
        EmployeeInformationPage eip = dp.selectEmployee2(employee);
        DriverUtil.sleep(10000);
        OrgChartPage ocp = eip.clickOrganizationChart();
        DriverUtil.sleep(4000);
        StepReport.info("Clicking action link for change manager");
        ocp.clickLinkforChangeManager();
        StepReport.info("Clicking Employement link for change manager");
        ocp.clickEmploymentLink();
        DriverUtil.sleep(5000);
        ChangeManagerPage pp = ocp.clickChangeManager();
        DriverUtil.sleep(3000);
        pp.clickReview();
        DriverUtil.sleep(6000);
        String personNumber=pp.getPersonNumber();
        System.out.println("Person Number is: "+personNumber);
        ChangeManagerPersonNum=personNumber.trim();
        DriverUtil.sleep(6000);
        pp.SubmitChangeManager();
        pp.clickSubmit();
        DriverUtil.sleep(5000);
        logout();
        return ChangeManagerPersonNum;
    }

    public void rootUserActionPromote(String action, String user , String taskRefNumber){
        login(hcmUser1,hcmUser1Pwd);
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        wrkListNotAppr.isLoaded();
        StepReport.info("Worklist loaded");
        if(action.equalsIgnoreCase("Resume")){
            DriverUtil.sleep(10000L);
            wrkListNotAppr.selectSuspendedTaks("Suspended");

        }
        if(action.equalsIgnoreCase("Withdraw")){
            DriverUtil.sleep(10000L);
            wrkListNotAppr.switchToInitiatedTasks();
        }
        searchReport(taskRefNumber,wrkListNotAppr);
        wrkListNotAppr.clickOnExpenseReport(taskRefNumber);
        DriverUtil.sleep(10000L);

        //wrkListNotAppr.typeFilter(taskRefNumber);
        //wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        //wrkListNotAppr.clickOnExpenseReport(taskRefNumber);
        DriverUtil.sleep(10000);
        Set<String> windowHandle = driver.getWindowHandles();

        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s))
            {

                // Switching to Child window
                driver.switchTo().window(s);
                DriverUtil.sleep(4000);
                ActionsPage actionsPage = PageFactory.getPage(ActionsPage.class);
                //DriverUtil.waitForElementVisible("//div/a/span[text()='"+action+"']",10);
                if(action.equalsIgnoreCase("Approve")||action.equalsIgnoreCase("Reject")
                        ||action.equalsIgnoreCase("Suspend")||action.equalsIgnoreCase("Resume")
                        ||action.equalsIgnoreCase("Withdraw")){
                    actionsPage.performAction(action);
                }
                else {
                    actionsPage.selectMoreOptions(action,user);
                }

                // driver.findElement(By.xpath("//div/a/span[text()='"+action+"']")).click();
                // .sendKeys(Keys.RETURN);
                //DriverUtil.sleep(10000);

                //driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        if(action.equalsIgnoreCase("Resume")){
            wrkListNotAppr.revertToAssignedTaks();
            DriverUtil.sleep(4000);
        }
        if(action.equalsIgnoreCase("Withdraw")){
            wrkListNotAppr.revertToAssignedTaks();
        }
        logout();
        // Switching to Parent window i.e Main Window.


    }

    public void firstLevelAction(String action,String user, String empName){
        login(hcmUser2,hcmUser2Pwd);
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        if(action.equalsIgnoreCase("Resume")){
            wrkListNotAppr.selectSuspendedTaks("Suspended");

        }
        wrkListNotAppr.typeFilter(empName);
        wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        wrkListNotAppr.clickOnExpenseReport(empName);
        DriverUtil.sleep(10000);
        Set<String> windowHandle = driver.getWindowHandles();

        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s))
            {

                // Switching to Child window
                driver.switchTo().window(s);
                DriverUtil.sleep(4000);
                ActionsPage actionsPage = PageFactory.getPage(ActionsPage.class);
                //DriverUtil.waitForElementVisible("//div/a/span[text()='"+action+"']",10);
                if(action.equalsIgnoreCase("Approve")||action.equalsIgnoreCase("Reject")
                        ||action.equalsIgnoreCase("Suspend")||action.equalsIgnoreCase("Resume")
                        ||action.equalsIgnoreCase("Withdraw")){
                    actionsPage.performAction(action);
                }
                else {
                    actionsPage.selectMoreOptions(action,user);
                }

               // driver.findElement(By.xpath("//div/a/span[text()='"+action+"']")).click();
                // .sendKeys(Keys.RETURN);
                //DriverUtil.sleep(10000);

                //driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        if(action.equalsIgnoreCase("Resume")){
            wrkListNotAppr.revertToAssignedTaks();
            DriverUtil.sleep(4000);
        }

        try {
            logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Switching to Parent window i.e Main Window.

    }


    public void verifyHistoryTreeforChangmgr(String empName) {
        login(hcmUser1,hcmUser1Pwd);
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        DriverUtil.sleep(2000);
        wrkListNotAppr.switchToInitiatedTasks();
        DriverUtil.sleep(2000);
        wrkListNotAppr.selectStatus("Withdrawn");
        DriverUtil.sleep(10000L);

        wrkListNotAppr.typeFilter(empName);
        DriverUtil.sleep(2000);
        wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        wrkListNotAppr.clickOnExpenseReport(empName);
        DriverUtil.sleep(10000);
        Set<String> windowHandle = driver.getWindowHandles();

        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s)) {
                // Switching to Child window
                driver.switchTo().window(s);
                DriverUtil.sleep(4000);
                ActionsPage actionsPage = PageFactory.getPage(ActionsPage.class);
                DriverUtil.sleep(4000);
                System.out.println("Expand history page");
                driver.findElement(By.xpath("//a[contains(@title,'Expand History')]")).click();
                DriverUtil.sleep(4000);
                DriverUtil.scrollIntoView(driver.findElement(By.xpath("//a[contains(@title,'Expand History')]")));
                //Verify if the manager name who has withdrawn the request is present in the tree node
                //It works for now. Check how to use this condition here "//div[contains(@class,'dTreeNode')] and //td[contains(text(),'Scott')]"
                DriverUtil.sleep(3000);
                if (driver.findElement(By.xpath("//td[contains(text(),'Veda')]")).isDisplayed()){
                    StepReport.info("Tree node is correctly displayed.");
                } else {
                    throw new TestErrorException("Manager details not included in the tree node, Bug27568308 still exists"); }

            }
        }
        driver.switchTo().window(mainWindow);

        logout();
        // Switching to Parent window i.e Main Window.


    }


    public void verifyHistoryTree(String taskname, String action) {
        if(action.equalsIgnoreCase("Goal"))
        {login(hcmUser4,hcmUser4Pwd);}
        else if(action.equalsIgnoreCase("Terminate"))
        {login(hcmUser1,hcmUser1Pwd);}
        else {
        login(hcmUser5,hcmUser5Pwd);}
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        DriverUtil.sleep(3000);
        if (action.equalsIgnoreCase("Terminate")) {
            wrkListNotAppr.switchToInitiatedTasks();
            DriverUtil.sleep(2000);
        }

        DriverUtil.sleep(2000);
        wrkListNotAppr.typeFilter(taskname);
        DriverUtil.sleep(2000);
        wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        wrkListNotAppr.clickOnExpenseReport(taskname);
        DriverUtil.sleep(10000);
        Set<String> windowHandle = driver.getWindowHandles();

        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s)) {
                // Switching to Child window
                driver.switchTo().window(s);
                DriverUtil.sleep(6000);
                ActionsPage actionsPage = PageFactory.getPage(ActionsPage.class);
                DriverUtil.sleep(4000);
                System.out.println("Expand history page");
                driver.findElement(By.xpath("//a[contains(@title,'Expand History')]")).click();
                DriverUtil.sleep(4000);

                if(action.equalsIgnoreCase("Goal")){
                //Verify if the history table has correct information
                    if (driver.findElement(By.xpath("//td[contains(text(),'Srinivas')]")).isDisplayed()){
                        String check=driver.findElement(By.xpath("//td[contains(text(),'Srinivas')]")).getText();
                        StepReport.info(" " +check);
                        String subStr="Srinivas";
                        //Checking here the number of times the approvers name being displayed in the history table.
                        int count = (check.length() - check.replace(subStr, "").length()) / subStr.length();
                       //int i=check.split("Srinivas").length();
                        System.out.println("********" +count);

                        if(count==1){
                            StepReport.info("Tree node is correctly displayed.");
                    } else {
                            throw new TestErrorException("Approval history shows incorrect information, Bug27469012 still exists");
                        }
                    } }

                else if(action.equalsIgnoreCase("Terminate")){
                    //Verify if the history table has correct information
                    if (driver.findElement(By.xpath("//td[contains(text(),'HCMTEST')]")).isDisplayed()){
                        String check=driver.findElement(By.xpath(".//*[@id='taskHst:taskHistoryTable::db']/table/tbody/tr[3]/td[2]/div/table/tbody/tr/td/div")).getText();
                        StepReport.info("Printing the assigned details from history table: " +check);
                        //Checking if the assignee name is HCMTEST*** (This is to confirm if the employee that we terminated has been assigned a task again)
                        Boolean verify=check.indexOf("HCMTEST")!=-1;
                        if(verify){
                            throw new SkipException("Approval history shows incorrect information, Bug21886115 still exists");
                    } else {
                            StepReport.info("Tree node is correctly displayed.");}
                    } }

            }
        }
        driver.switchTo().window(mainWindow);

        logout();
        // Switching to Parent window i.e Main Window.


    }






    public String hireEmployee(String empType){
        return enterNewHireDetails(empType);

    }
    public void secondLevelAction(String action,String user, String empName){
        login(hcmUser3,hcmUser3Pwd);
        homePage.clickTools();
        WorklistNotificationsApprovals wrkListNotAppr= homePage.clickWorklist();
        if(action.equalsIgnoreCase("Resume")){
            wrkListNotAppr.selectSuspendedTaks("Suspended");

        }
        wrkListNotAppr.typeFilter(empName);
        wrkListNotAppr.clickSearch();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();
        wrkListNotAppr.clickOnExpenseReport(empName);
        DriverUtil.sleep(8000);
        Set<String> windowHandle = driver.getWindowHandles();

        for(String s: windowHandle){
            if(!mainWindow.equalsIgnoreCase(s))
            {

                // Switching to Child window
                driver.switchTo().window(s);
                ActionsPage actionsPage = PageFactory.getPage(ActionsPage.class);
                //DriverUtil.waitForElementVisible("//div/a/span[text()='"+action+"']",10);
                if(action.equalsIgnoreCase("Approve")||action.equalsIgnoreCase("Reject")||
                        action.equalsIgnoreCase("Suspend")||action.equalsIgnoreCase("Resume")
                        ||action.equalsIgnoreCase("Withdraw")){
                        actionsPage.performAction(action);
                }
                else {
                    actionsPage.selectMoreOptions(action,user);
                }

                //DriverUtil.waitForElementVisible("//div/a/span[text()='"+action+"']",10);

               // driver.findElement(By.xpath("//div/a/span[text()='"+action+"']")).click();
                // .sendKeys(Keys.RETURN);
                //DriverUtil.sleep(10000);

                //driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        if(action.equalsIgnoreCase("Resume")){
            wrkListNotAppr.revertToAssignedTaks();
            DriverUtil.sleep(4000);
        }
        homePage.clickHome();
        DriverUtil.sleep(5000);
        logout();
        // Switching to Parent window i.e Main Window.

    }

    public void terminateEmployee(String empName){
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Successful");
        Navigator nav = homePage.clickNavigator();
        DirectoryPage dp = nav.clickDirectory();
        dp.searchEmployee(empName);
        EmployeeInformationPage eip = dp.selectEmployee2(empName);
        DriverUtil.sleep(10000);
        OrgChartPage ocp = eip.clickOrganizationChart();
        DriverUtil.sleep(4000);
        ocp.clickActionLinkforTermination();
        ocp.clickEmploymentLink();
        DriverUtil.sleep(5000);
        TerminateEmployeePage terminateEmployeePage  = ocp.clickTerminate();
        terminateEmployeePage.clickContinueButtonInPage();
        terminateEmployeePage.clickSubmitButton();
        dp.isLoaded();
        logout();

       }

    public Boolean verifyActionBellNotif (String str, String type)
    {
        //It takes some time to receive notifications
        DriverUtil.sleep(2000L);
        if(type.equalsIgnoreCase("FYI")){
            login(hcmUser3, hcmUser3Pwd);
        }
        else {
        login(hcmUser1, hcmUser1Pwd); }
        StepReport.info("Login Successful");
        BellNotification bellNotification=homePage.clickBellNotification();
        DriverUtil.sleep(2000L);
        Boolean checkAutodismiss=bellNotification.isExpenseReportExists(str);

        if(checkAutodismiss == false) {
            checkAutodismiss = bellNotification.waitForReportAppear(str);
        }

        StepReport.assertTrue("Report displayed under Bell Notification ",checkAutodismiss);
        logout();
        return checkAutodismiss;
    }


    public void addLocation(){
        login(hcmUser1,hcmUser1Pwd);
        Navigator navigator=homePage.clickNavigator();
        WorkForceStructurePage workForceStructurePage=navigator.clickWorForceStructures();
        workForceStructurePage.clickManageLocations();
        workForceStructurePage.setEnterLocationName();
        locationCode= workForceStructurePage.setEnterCode();
        workForceStructurePage.setCountryName();
        DriverUtil.sleep(3000);
        workForceStructurePage.setCity();
        DriverUtil.sleep(3000);
        workForceStructurePage.setLocationAddress2();
        DriverUtil.sleep(3000);
        workForceStructurePage.clickReview();
        DriverUtil.sleep(5000);
        workForceStructurePage.clickSubmit();
        logout();

    }

    public String changeLocation(String managerName){
        String ChangeLocationNum="";
        login(hcmUser1,hcmUser1Pwd);
        Navigator navigator = homePage.clickNavigator();
        DirectoryPage directoryPage= navigator.clickDirectory();
        directoryPage.searchEmployee(managerName);
        EmployeeInformationPage employeeInformationPage =directoryPage.selectEmployee2(managerName);
        DriverUtil.sleep(6000);
        OrgChartPage orgChartPage = employeeInformationPage.clickOrganizationChart();
        DriverUtil.sleep(5000);
        orgChartPage.clickActionLinkForChangeLocation(managerName);
        DriverUtil.sleep(4000);
        orgChartPage.clickEmploymentLink();
        DriverUtil.sleep(3000);
        StepReport.info("Input new location details");
        ChangeLocationPage changeLocationPage= orgChartPage.clickChangeLocation();
        changeLocationPage.selectLocation(locationCode);
        DriverUtil.sleep(4000);
        changeLocationPage.clickReview();
        DriverUtil.sleep(4000);
        String locNumber=changeLocationPage.getLocationNumber();
        System.out.println("Location Change Number is: "+locNumber);
        ChangeLocationNum=locNumber.trim();
        DriverUtil.sleep(5000);
        changeLocationPage.clickSubmit();
        DriverUtil.sleep(3000);
        logout();
        return ChangeLocationNum;
    }


    public void checkTransactionStatus(String transactionName,String status, String empName){
        login(hcmUser1,hcmUser1Pwd);
        Navigator navigator =homePage.clickNavigator();
        TransactionConsole transactionConsole = navigator.clickTransConsole();
        transactionConsole.clickTransactions(status);
        DriverUtil.sleep(5000);
        transactionConsole.searchTransaction(transactionName);
        transactionConsole.checkTransactionStatus(empName);
        logout();

    }

    public void checkTransactionStatusPromotion(String transactionName,String status){
        login(hcmUser1,hcmUser1Pwd);
        Navigator navigator =homePage.clickNavigator();
        TransactionConsole transactionConsole = navigator.clickTransConsole();
        transactionConsole.clickTransactions(status);
        DriverUtil.sleep(5000);
        transactionConsole.checkTransactionStatusPromote(transactionName);
        logout();

    }

    public void verifyTransactionStatusInWorkList(String transactionStatus, String empName){
        login(hcmUser1,hcmUser1Pwd);
        homePage.clickTools();

        WorklistNotificationsApprovals worklistNotificationsApprovals =homePage.clickWorklist();
        worklistNotificationsApprovals.verifyTransactionStatusInWorkList(empName,transactionStatus);
    }

    protected void createResignationRequest(String user, String password) {
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        PersonalInfo personalInfo = navigator.clickPersonalinfo();
        DriverUtil.sleep(3000);
        StepReport.info("Navigation to personal info page successful");
        personalInfo.clickRelatedLinks();
        SubmitResignationPage submitResignationPage = personalInfo.clickSubmitResignation();
        submitResignationPage.clickReviewButton();
        submitResignationPage.clickSubmitButton();
        try {
            submitResignationPage.clickYesButton();
        } catch (Exception e) { }
        submitResignationPage.clickOkButton();
        logout();
    }

    public void addProjectManager(String employeeName, String managerName, String managerType){
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Successful");
        Navigator navigator = homePage.clickNavigator();
        PersonManagementPage personManagement = navigator.clickPersonManagement();
        personManagement.enterName(employeeName);
        personManagement.clickSearch();
        ManageEmploymentPage manageEmployment = personManagement.clickEmployee(employeeName);
        if(manageEmployment.isManagerExists(managerName)){
            logout();
            return;
        }
        manageEmployment.clickCorrect();
        manageEmployment.clickOK();
        manageEmployment.clickAddManager();
        manageEmployment.enterName(managerName);
        manageEmployment.selectManagerType(managerType);
        manageEmployment.clickReview();
        manageEmployment.clickSubmit();
        manageEmployment.clickYesInWarningPopUp();
        manageEmployment.clickOKInConfirmation();
        manageEmployment.isLoaded();
        logout();
    }

    public void removeProjectManager(String employeeName, String managerID){
        login(hcmUser1, hcmUser1Pwd);
        StepReport.info("Login Successful");
        Navigator navigator = homePage.clickNavigator();
        PersonManagementPage personManagement = navigator.clickPersonManagement();
        personManagement.enterName(employeeName);
        personManagement.clickSearch();
        ManageEmploymentPage manageEmployment = personManagement.clickEmployee(employeeName);
        manageEmployment.clickCorrect();
        manageEmployment.clickOK();
        manageEmployment.clickRemoveManager(managerID);
        manageEmployment.clickReview();
        manageEmployment.clickSubmit();
        manageEmployment.clickYesInWarningPopUp();
        manageEmployment.clickOKInConfirmation();
        manageEmployment.isLoaded();
        logout();
    }

}
