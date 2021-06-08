package com.oracle.fa.qa.selenium.component.crm.test;
import com.oracle.fa.qa.selenium.component.common.page.*;
import com.oracle.fa.qa.selenium.component.common.test.TestBase;
import com.oracle.fa.qa.selenium.component.crm.page.*;
import com.oracle.fa.qa.selenium.component.crm.page.ReassignTask;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Set;

public class CRMBase extends TestBase {


    protected String createContract(String ecuserName, String ecuserPassword) {
        String contractNumber = "";
        login(ecuserName, ecuserPassword);
        StepReport.info("Login EC Test successful");
        if(homePage.checkTheme()){
            ContractsPage contracts = homePage.clickContracts();
            DriverUtil.sleep(5000L);
            contracts.clickTasks();
            DriverUtil.sleep(2000L);
            CreateContract createContract = contracts.clickCreateContract();
            //createContract.typeBusinessUnit();
            //createContract.typeLegalEntity();
            createContract.selectContractType();
            createContract.typePrimaryPartyName();
            createContract.typeEndDate();
            createContract.typeItemMaster();
            contractNumber = createContract.typeContractNumber();
            DriverUtil.sleep(5000L);
            StepReport.info("Contract Number: " + contractNumber);
            EditContract editContract = createContract.clickSaveAndContinue();
            editContract.selectFOB();
            editContract.selectCarrier();
            editContract.selectFrieghtTerms();
            editContract.clickSave();
            CreateLine createLine = editContract.clickLines();
            createLine.typeLineNumber();
            createLine.selectLineType();
            createLine.typeLineName();
            editContract = createLine.clickOK();
            editContract.typeUnitPrice();
            editContract.typeNeedbyDate();
            editContract.typeLineQuantity();
            editContract.typePaymentTerms();
            editContract.typeComments();
            editContract.clickSave();
            SubmitContract submitContract = editContract.clickSubmitForApproval();
            submitContract.noteToApprover();
            submitContract.ClickSubmit();
            StepReport.info("Contract Number: " + contractNumber + " Submitted for approval");
            logout();
            return contractNumber;
        }else {
            ContractsPage contracts = homePage.clickContracts();
            DriverUtil.sleep(5000L);
            contracts.clickTasks();
            DriverUtil.sleep(2000L);
            CreateContract createContract = contracts.clickCreateContract();
            //createContract.typeBusinessUnit();
            //createContract.typeLegalEntity();
            createContract.selectContractType();
            createContract.typePrimaryPartyName();
            createContract.typeEndDate();
            createContract.typeItemMaster();
            contractNumber = createContract.typeContractNumber();
            DriverUtil.sleep(5000L);
            StepReport.info("Contract Number: " + contractNumber);
            EditContract editContract = createContract.clickSaveAndContinue();
            editContract.selectFOB();
            editContract.selectCarrier();
            editContract.selectFrieghtTerms();
            editContract.clickSave();
            CreateLine createLine = editContract.clickLines();
            createLine.typeLineNumber();
            createLine.selectLineType();
            createLine.typeLineName();
            editContract = createLine.clickOK();
            editContract.typeUnitPrice();
            editContract.typeNeedbyDate();
            editContract.typeLineQuantity();
            editContract.typePaymentTerms();
            editContract.typeComments();
            editContract.clickSave();
            SubmitContract submitContract = editContract.clickSubmitForApproval();
            submitContract.noteToApprover();
            submitContract.ClickSubmit();
            StepReport.info("Contract Number: " + contractNumber + " Submitted for approval");
            logout();
            return contractNumber;
        }

    }


    protected String createContractStopApproval(String ecuserName, String ecuserPassword, String contractNumber, String operation) {

        login(ecuserName, ecuserPassword);
        StepReport.info("Login EC Test successful");
        ContractsPage contracts = homePage.clickContracts();
        DriverUtil.sleep(3000L);
        contracts.recentContracts();
        EditContract editContract = contracts.clickOnContractNumber(contractNumber);
        ConfirmApproval confirmApproval = editContract.clickStopApproval();
        confirmApproval.clickOKToConfirm();
        editContract.clickSave();
        StepReport.info("Contract Number: " + contractNumber + " Stopped from approval");
        logout();
        return contractNumber;
    }

    protected String createDispute(String cduserName, String cduserPassword) {
        login(cduserName, cduserPassword);
        StepReport.info("Login Create Dispute Page Test successful");
        if(homePage.checkTheme()){
            homePage.clickIncentiveCompensation();
            SalesCompensation salesCompensation = homePage.clickSalesCompensation();
            DisputesPage disputesPage = salesCompensation.clickDispute();
            CreateDispute createDispute = disputesPage.clickCreateDispute();
            String disputeSummary = "";
            disputeSummary = createDispute.typeSummary();
            createDispute.typeJustification();
            createDispute.typeDescription();
            createDispute.selectTxnType();
            createDispute.typeActualCreditAmount();
            createDispute.typeExpectedCreditAmount();
            createDispute.typeTxnNumber();
            createDispute.selectCurrency();
            createDispute.clickSaveAndClose();
            logout();
            return disputeSummary;
        }else {
            SalesCompensation salesCompensation = homePage.clickSalesCompensation();
            StepReport.info("Find Flag for Creating Dispute");
            DisputesPage disputesPage = salesCompensation.clickDispute();
            CreateDispute createDispute = disputesPage.clickCreateDispute();
            String disputeSummary = "";
            disputeSummary = createDispute.typeSummary();
            createDispute.typeJustification();
            createDispute.typeDescription();
            createDispute.selectTxnType();
            createDispute.typeActualCreditAmount();
            createDispute.typeExpectedCreditAmount();
            createDispute.typeTxnNumber();
            createDispute.selectCurrency();
            createDispute.clickSaveAndClose();
            logout();
            return disputeSummary;
        }
    }

    protected String createDispute2(String cduserName, String cduserPassword) {
        login(cduserName, cduserPassword);
        StepReport.info("Login Create Dispute Page Test successful");
        SalesCompensation salesCompensation = homePage.clickSalesCompensation();
        StepReport.info("Find Flag for Creating Dispute");
        DisputesPage disputesPage = salesCompensation.clickDispute();
        CreateDispute createDispute = disputesPage.clickCreateDispute();
        String disputeSummary = "";
        disputeSummary = createDispute.typeSummary();
        createDispute.typeJustification();
        createDispute.typeDescription();
        createDispute.selectTxnType();
        createDispute.typeActualCreditAmount();
        createDispute.typeExpectedCreditAmount();
        createDispute.typeTxnNumber();
        createDispute.selectCurrency();
        createDispute.clickSaveAndClose();
        logout();
        return disputeSummary;
    }

    protected void operationsOnDisputeNotification(String aduserName, String aduserPassword, String DisputeName, String operationName) {
        login(aduserName, aduserPassword);
        StepReport.info("Login Approve Dispute Page Test successful");
        // BellNotification bellNotification=homePage.clickBellNotification();
        DriverUtil.sleep(2000L);
        // StepReport.assertTrue("Dispute Approve / Reject Bell Notification ",bellNotification.isDisputeApprovalNotificationExists(DisputeName));
        // StepReport.info("Bell Notification Check Completed");

        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        if (operationName.equalsIgnoreCase("Resume")) {
            worklistNotificationsApprovals.selectStatus("Any");
            searchReport(DisputeName, worklistNotificationsApprovals);

        } else {
            searchReport(DisputeName, worklistNotificationsApprovals);
        }
        DisputesNotificationPage disputesNotificationPage = worklistNotificationsApprovals.clickOnDisputeNotification(DisputeName);

        //Switch Handle to control new page
        DriverUtil.sleep(10000L);
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        //Get all window handles
        Set<String> allHandles = driver.getWindowHandles();
        //count the handles Here count is=2
        StepReport.info("Count of windows:" + allHandles.size());
        //Get current handle or default handle
        String currentWindowHandle = allHandles.iterator().next();
        //Remove first/default Handle
        allHandles.remove(allHandles.iterator().next());
        //get the last Window Handle
        String lastHandle = allHandles.iterator().next();
        driver.switchTo().window(lastHandle);
        StepReport.info("Page Title " + driver.getTitle());

        if (operationName.equalsIgnoreCase("Approve")) {
            disputesNotificationPage.selectResolution();
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Approving the Dispute Notification :" + DisputeName);
            addComment.clickOK();
            disputesNotificationPage.clickApprove();
        }

        if (operationName.equalsIgnoreCase("Reject")) {
            disputesNotificationPage.selectResolution();
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Reject the Dispute Notification :" + DisputeName);
            addComment.clickOK();
            disputesNotificationPage.clickReject();
        }

        if (operationName.equalsIgnoreCase("RequestMoreInfo")) {
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("RequestMoreInfo to DisputeNotification :" + DisputeName);
            addComment.clickOK();
            RequestMoreInfo requestMoreInformation = disputesNotificationPage.clickRequestInfo();
            requestMoreInformation.typeComment("RequestMoreInfo from oic_party User");
            requestMoreInformation.clickOK();
        }
        if (operationName.equalsIgnoreCase("ProvideInfo")) {
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Provide Info to DisputeNotification :" + DisputeName);
            addComment.clickOK();
            disputesNotificationPage.clickSubmitInformation();
        }
        if (operationName.equalsIgnoreCase("Withdraw")) {
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Withdraw DisputeNotification :" + DisputeName);
            addComment.clickOK();
            disputesNotificationPage.clickWithdraw();
        }
        if (operationName.equalsIgnoreCase("Dismiss")) {
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Dismiss DisputeNotification :" + DisputeName);
            addComment.clickOK();
            disputesNotificationPage.clickDismiss();
        }

        if (operationName.equalsIgnoreCase("Suspend")) {
            disputesNotificationPage.selectResolution();
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Suspend DisputeNotification :" + DisputeName);
            addComment.clickOK();
            disputesNotificationPage.clickSuspend();
        }
        if (operationName.equalsIgnoreCase("Save")) {
            disputesNotificationPage.selectResolution();
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Save Dispute Notification :" + DisputeName);
            addComment.clickOK();
            disputesNotificationPage.clickSave();
        }
        if (operationName.equalsIgnoreCase("Resume")) {

            disputesNotificationPage.clickResume();
            DriverUtil.sleep(3000L);
            AddComment addComment = disputesNotificationPage.addComment();
            addComment.typeComment("Resume DisputeNotification :" + DisputeName);
            addComment.clickOK();
            DriverUtil.sleep(2000L);
            disputesNotificationPage.selectResolution();
            DriverUtil.sleep(2000L);
            disputesNotificationPage.clickApprove();
        }
        driver.switchTo().window(currentWindowHandle);
        logout();

    }

    protected void operatonsOnEnterpriseContracts(String aduserName, String aduserPassword, String reportName, String operationName) {
        login(aduserName, aduserPassword);
        StepReport.info("Login Approve Dispute Page Test successful");
        // BellNotification bellNotification=homePage.clickBellNotification();
        DriverUtil.sleep(2000L);
        // StepReport.assertTrue("Dispute Approve / Reject Bell Notification ",bellNotification.isDisputeApprovalNotificationExists(DisputeName));
        // StepReport.info("Bell Notification Check Completed");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        if (operationName.equalsIgnoreCase("Resume")) {
            worklistNotificationsApprovals.selectStatus("Any");
            searchReport(reportName, worklistNotificationsApprovals);
            worklistNotificationsApprovals.waitForReportOnGrid(reportName);

        } else {
            searchReport(reportName, worklistNotificationsApprovals);
            worklistNotificationsApprovals.waitForReportOnGrid(reportName);
        }
        ApproveContractsPage approveContractsPage = worklistNotificationsApprovals.clickOnContractReport(reportName);

        //Switch Handle to control new page
        DriverUtil.sleep(10000L);
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        //Get all window handles
        Set<String> allHandles = driver.getWindowHandles();
        //count the handles Here count is=2
        StepReport.info("Count of windows:" + allHandles.size());
        //Get current handle or default handle
        String currentWindowHandle = allHandles.iterator().next();
        //Remove first/default Handle
        allHandles.remove(allHandles.iterator().next());
        //get the last Window Handle
        String lastHandle = allHandles.iterator().next();
        driver.switchTo().window(lastHandle);
        StepReport.info("Page Title " + driver.getTitle());

        if (operationName.equalsIgnoreCase("Approve")) {

            AddComment addComment = approveContractsPage.addComment();
            addComment.typeComment("Approving the Contract Request :" + reportName);
            addComment.clickOK();
            approveContractsPage.clickApprove();
        }

        if (operationName.equalsIgnoreCase("AddAttachments")) {

            AddAttachments addAttachments = approveContractsPage.clickAddAttachment();
            addAttachments.typeFileName();
            addAttachments.typeURL(crmAttFilePath);
            addAttachments.clickOKButton();
            approveContractsPage.clickSave();
        }

        if (operationName.equalsIgnoreCase("Reject")) {

            AddComment addComment = approveContractsPage.addComment();
            addComment.typeComment("Reject the Contract Request  :" + reportName);
            addComment.clickOK();
            approveContractsPage.clickReject();
        }
        driver.switchTo().window(currentWindowHandle);
        logout();


    }

    protected void approveDisputeNotificationFromWorklist(String user, String pwd, String taskName) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        searchReport(taskName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickApprove(taskName);
        logout();
    }


    protected String createPartner2(String user, String password) {
        //Login as User1
        String partnerName = "";
        login(user, password);
        StepReport.info("Login CRM Test successful");
        homePage.clickPartnerManagement();
        PartnersPage partnersPage=homePage.clickPartner();
        CreatePartner createPartner=partnersPage.clickCreateParter();
        partnerName = createPartner.typePartnerName();
        createPartner.typePartnerAddressLine1();
        createPartner.selectStatus("Registered");
        createPartner.selectCreatePrimaryContactCheckbox();
        createPartner.typePartnerFirstName();
        createPartner.typePartnerLastName();
        createPartner.typePartnerEmailAddress();
        EditPartnerProfile editPartnerProfile = createPartner.clickSaveAndContinue();
        editPartnerProfile.saveAndCloseEditPartnerProfile();
        DriverUtil.sleep(5000L);
        logout();
        return partnerName;
    }

    protected String createPartner(String user, String password) {
        //Login as User1
        String partnerName = "";
        login(user, password);
        if(homePage.checkTheme()){
            homePage.clickPartnerManagement();
            PartnersPage partnersPage=homePage.clickPartner();
            CreatePartner createPartner = partnersPage.clickCreateParter();
            DriverUtil.sleep(3000L);
            createPartner.selectCreatePrimaryContactCheckbox();
            partnerName = createPartner.typePartnerName();
            createPartner.typePartnerAddressLine1();
            createPartner.selectStatus("Registered");
            createPartner.selectCreatePrimaryContactCheckbox();
            createPartner.typePartnerFirstName();
            createPartner.typePartnerLastName();
            createPartner.typePartnerEmailAddress();
            EditPartnerProfile editPartnerProfile = createPartner.clickSaveAndContinue();
            editPartnerProfile.saveAndCloseEditPartnerProfile();
            DriverUtil.sleep(5000L);
            logout();
        }else{
            Navigator nav = homePage.clickNavigator();
            PartnersPage partnersPage = nav.clickPartners();
            CreatePartner createPartner = partnersPage.clickCreateParter();
            DriverUtil.sleep(3000L);
            partnerName = createPartner.typePartnerName();
            createPartner.typePartnerAddressLine1();
            createPartner.selectStatus("Registered");
            createPartner.selectCreatePrimaryContactCheckbox();
            createPartner.typePartnerFirstName();
            createPartner.typePartnerLastName();
            createPartner.typePartnerEmailAddress();
            EditPartnerProfile editPartnerProfile = createPartner.clickSaveAndContinue();
            editPartnerProfile.saveAndCloseEditPartnerProfile();
            DriverUtil.sleep(5000L);
            logout();
        }
        return partnerName;
    }

    protected String createEnrollment(String user, String password, String partnerName) {
        String enrollmentName;
        login(user, password);
        StepReport.info("Login CRM Test successful");

        Navigator navigator = homePage.clickNavigator();
        ProgramsPage programs = navigator.clickPrograms();

        String crmProgramName = "PRM Test Program 9";
        EditProgram editProgram = programs.searchProgram(crmProgramName);
        DriverUtil.sleep(3000L);

        editProgram.clickEnrollments();


        CreateEnrollment createEnrollment = editProgram.clickCreateEnrollments();
        createEnrollment.typePartnerName(partnerName);
        createEnrollment.typeStartDate();
        createEnrollment.typeEndDate();
        EditEnrollmentSummary editEnrollmentSummary = createEnrollment.clickSaveAndContinue();
        DriverUtil.sleep(5000L);
        enrollmentName = editEnrollmentSummary.getEnrollmentNumber();
        logout();
        return enrollmentName;
    }

    protected String submitEnrollmentforApproval(String user, String password, String partnerName) {
        String enrollmentName;
        login(user, password);
        StepReport.info("Login CRM Test successful");
        Navigator nav = homePage.clickNavigator();
        DriverUtil.sleep(3000L);
        ProgramsPage programsPage = nav.clickPrograms();
        DriverUtil.sleep(3000L);
        String crmProgramName = "PRM Test Program 9";
        EditProgram editProgram = programsPage.searchProgram(crmProgramName);
        DriverUtil.sleep(3000L);

        editProgram.clickEnrollments();


        CreateEnrollment createEnrollment = editProgram.clickCreateEnrollments();
        createEnrollment.typePartnerName(partnerName);
        createEnrollment.typeStartDate();
        createEnrollment.typeEndDate();
        EditEnrollmentSummary editEnrollmentSummary = createEnrollment.clickSaveAndContinue();
        DriverUtil.sleep(3000L);
        StepReport.info("Get Enrollment Number");
        enrollmentName = editEnrollmentSummary.getEnrollmentNumber();
        editEnrollmentSummary.clickActions();
        DriverUtil.sleep(3000L);
        editEnrollmentSummary.clicksubmitForApproval();
        DriverUtil.sleep(3000L);
        StepReport.info("Enrollment is in Status:" + editEnrollmentSummary.getEnrollmentStatus());
        editEnrollmentSummary.clickSaveAndClose();
        logout();
        return enrollmentName;
    }


    protected void approveEnrollment(String user, String pwd, String partnerName) {
        approveEnrollment(user, pwd, partnerName, false);
    }

    protected void rejectEnrollment(String user, String pwd, String expenseReportName) {
        rejectEnrollment(user, pwd, expenseReportName, false);
    }


    protected void approveEnrollment(String user, String pwd, String partnerName, boolean checkBellNotification) {
        enrollmentOperations(user, pwd, null, partnerName, checkBellNotification, "approve", false, false);
    }

    protected void rejectEnrollment(String user, String pwd, String partnerName, boolean checkBellNotification) {
        enrollmentOperations(user, pwd, null, partnerName, checkBellNotification, "reject", false, false);
    }


    protected void enrollmentOperations(String user, String pwd, String adHocUser, String partnerName, boolean checkBellNotification, String operation, boolean checkComment, boolean checkAttachment) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();

        if (operation.equalsIgnoreCase("withdraw")) {
            worklistNotificationsApprovals.switchToInitiatedTasks();
        }
        searchReport(partnerName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickOnExpenseReport(partnerName);
        DriverUtil.sleep(10000L);
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        //Get all window handles
        Set<String> allHandles = driver.getWindowHandles();

        //count the handles Here count is=2
        System.out.println("Count of windows:" + allHandles.size());

        //Get current handle or default handle
        String currentWindowHandle = allHandles.iterator().next();
        System.out.println("currentWindow Handle" + currentWindowHandle);

        //Remove first/default Handle
        allHandles.remove(allHandles.iterator().next());

        //get the last Window Handle
        String lastHandle = allHandles.iterator().next();
        System.out.println("last window handle" + lastHandle);
        System.out.println(driver.getTitle());
        driver.switchTo().window(lastHandle);
        System.out.println(driver.getTitle());

        EnrollmentRequest enrollmentRequest = PageFactory.getPage(EnrollmentRequest.class);
        enrollmentRequest.isLoaded();
        if (checkComment) {
            String commentTxtXpath = "//*[text()='requestInfoProvided']";
            WebElement commentElem = DriverUtil.getElement(By.xpath(commentTxtXpath));
            StepReport.assertTrue("Report Comment Displayed ", commentElem.isDisplayed());
        }
        if (checkAttachment) {
            DriverUtil.sleep(3000L);
            String attachmentTxtXpath = "//*[contains(text(),'" + attachmentSampleFileName + "')]";
            WebElement attachmentTxtElem = DriverUtil.getElement(By.xpath(attachmentTxtXpath));
            StepReport.assertTrue("Report Attachment Displayed ", attachmentTxtElem.isDisplayed());
        }

        if (operation.equals("approve")) {
            enrollmentRequest.clickApprove();
        }
        if (operation.equals("reject")) {
            enrollmentRequest.clickReject();
        }

        if (operation.equals("reqInfo")) {
            RequestMoreInfo requestMoreInformation = enrollmentRequest.clickRequestInfo();
            requestMoreInformation.typeComment("requestInfo1");
            requestMoreInformation.clickOK();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("provideInfo")) {
            AddComment addComment = enrollmentRequest.clickAddComment();
            addComment.typeComment("requestInfoProvided");
            addComment.clickOK();
            enrollmentRequest.clickSubmitInformation();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("adhoc_user")) {
            AdhocRoute adhocRoute = enrollmentRequest.clickAdhocRoute();
            adhocRoute.adhocUser(adHocUser, "Please Approve");
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("withdraw")) {
            enrollmentRequest.clickWithdraw();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("save")) {
            AddComment addComment = enrollmentRequest.clickAddComment();
            addComment.typeComment("Testing save option");
            addComment.clickOK();
            enrollmentRequest.clickSave();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("approve") || operation.equals("reject")) {
            driver.switchTo().window(currentWindowHandle);
            System.out.println(driver.getTitle());
            DriverUtil.sleep(2000L);
            searchCompletedReport(partnerName, worklistNotificationsApprovals);
            StepReport.assertTrue("Invoice displayed under completed ",
                    worklistNotificationsApprovals.verifyReportExists(partnerName));
        }
        logout();
    }

    protected void claimRequest(String user, String pwd, String partnerName) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        searchReport(partnerName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickClaim(partnerName);
        logout();
    }

    protected void approveEnrollmentWorklist(String user, String pwd, String partnerName) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        searchReport(partnerName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickApprove(partnerName);
        logout();
    }

    protected String publishProgram(String user, String pwd) {
        String myprogramName = "";
        login(user, pwd);
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        ProgramsPage programs = nav.clickPrograms();
        CreateProgram createProgram = programs.clickCreateProgram();
        myprogramName = createProgram.typeProgramName();
        createProgram.selectType("Level");
        createProgram.typeDescription("Test Program");
        createProgram.typeStartDate("SysDate");
        createProgram.typeEndDate("12/31/20");
        ProgramsPage programs1 = createProgram.clickSaveAndClose();
        EditProgram editProgram = programs1.searchProgram(myprogramName);
        editProgram.clickPublish();
        String enrollmentStatus = editProgram.getEnrollmentStatus();
        System.out.println(enrollmentStatus);
        Assert.assertTrue(enrollmentStatus.equalsIgnoreCase("Pending"));
        editProgram.clickSaveAndClose();
        DriverUtil.sleep(10000L);
        EditProgram editProgram1 = programs1.searchProgram(myprogramName);
        enrollmentStatus = editProgram.getEnrollmentStatus();
        Assert.assertTrue(enrollmentStatus.equalsIgnoreCase("Published"));
        editProgram1.clickSaveAndClose();
        logout();
        return myprogramName;
    }

    protected void createSandBox(String user, String pwd, String SandBoxName) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        SettingsAndActions settingsAndActions = homePage.clickSettingsAndActions();
        ManageSandBoxes manageSandBoxes = settingsAndActions.clickManageSandBoxes();
        CreateSandBox createSandBox = manageSandBoxes.clickNewButton();

        createSandBox.typeSandBoxName(SandBoxName);
        SandBoxConfirmation confirmation = createSandBox.clickSaveAndClose();
        ManageSandBoxes manageSandBoxes1 = confirmation.clickOkButton();
        manageSandBoxes1.setAsActive(user, SandBoxName);
        logout();
    }

    protected void publishSandBox(String user, String pwd, String SandBoxName) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        SettingsAndActions settingsAndActions = homePage.clickSettingsAndActions();
        ManageSandBoxes manageSandBoxes = settingsAndActions.clickManageSandBoxes();
        PublishSandBoxConfirmation publishSandBoxConfirmation = manageSandBoxes.publishSandbox(SandBoxName);
        publishSandBoxConfirmation.clickYesButton();
        homePage.clickHome();
        logout();
    }


    protected void createAppointment(String user, String pwd) {
        login(user, pwd);
        StepReport.info("Login Test successful");


        /*homePage.clickPartnerManagement();
        ProgramsPage programs = homePage.clickPrograms();
        CreateProgram createProgram = programs.clickCreateProgram();
        createProgram.typeProgramName(programName);
        createProgram.selectType("Level");
        createProgram.typeDescription("Test Program");
        createProgram.typeStartDate("SysDate");
        createProgram.typeEndDate("12/31/20");
        ProgramsPage programs1 = createProgram.clickSaveAndClose();
        EditProgram editProgram = programs1.searchProgram(programName);
        editProgram.clickPublish();
        String enrollmentStatus = editProgram.getEnrollmentStatus();
        System.out.println(enrollmentStatus);
        Assert.assertTrue(enrollmentStatus.equalsIgnoreCase("Pending"));
        editProgram.clickSaveAndClose();
        DriverUtil.sleep(10000L);
        EditProgram editProgram1 = programs1.searchProgram(programName);
        enrollmentStatus = editProgram.getEnrollmentStatus();
        Assert.assertTrue(enrollmentStatus.equalsIgnoreCase("Published"));*/

    }

    protected void salesTerritoryMgmt() {
        login(crmTMUserName, crmTMUserPassword);
        StepReport.info("Login CMR Sales Territory Mgmt Test successful");
        Navigator navigator = homePage.clickNavigator();
        TerritoriesPage territoriesPage = navigator.clickTerritories();
        DriverUtil.sleep(3000L);
        territoriesPage.clickShowDimensions();
        territoriesPage.clickShowMetrics();
        logout();
    }

    protected String changeCurrentAccount(String contactName) {
        String newAcc="";
        login(crmCOMUser1, crmCOMUser1Pwd);
        StepReport.info("Login and change the account associated already");
        Navigator navigator = homePage.clickNavigator();
        ContactsPage contactsPage = navigator.clickSalesContatcs();
        EditContactPage editContactPage=contactsPage.searchName(contactName);
        editContactPage.clickProfile();
        editContactPage.removeCurrentAccount();
        newAcc=editContactPage.typeAccount();
        editContactPage.clickSaveAndClose();
        contactsPage.isLoaded();
        logout();
        return newAcc;
    }

    protected String createContact() {
        String contactName = "";
        login(crmCOMUser1, crmCOMUser1Pwd);
        Navigator navigator = homePage.clickNavigator();
        ContactsPage contactsPage = navigator.clickSalesContatcs();
        CreateContactPage createContactPage= contactsPage.clickCreateContact();
        contactName = createContactPage.typeContactFirstName() +" "+ createContactPage.typeContactLastName();
        createContactPage.typeContactAddressLine1();
        createContactPage.typeContactState();
        createContactPage.typeContactPostCode();
        createContactPage.typeContactAccount();
        EditContactPage editContactPage = createContactPage.clickSaveAndContinue();
        editContactPage.clickSaveAndClose();
        contactsPage.isLoaded();
        logout();
        return contactName;
    }

    protected void checkNewAccount(String contactName, String AccName) {
        String accountnameExpected="";
        String accountnameActual="";
        accountnameExpected=AccName;
        login(crmCOMUser1, crmCOMUser1Pwd);
        Navigator navigator = homePage.clickNavigator();
        ContactsPage contactsPage = navigator.clickSalesContatcs();
        EditContactPage editContactPage=contactsPage.searchName(contactName);
        accountnameActual=editContactPage.getText();
        StepReport.info("AccountName Expected and Actual values",accountnameExpected,accountnameActual);
        editContactPage.clickSaveAndClose();
        logout();
    }

    protected void awardCompensation() {
        login(hcmUser1, hcmUser1Pwd);
        homePage.clickMyteam();
        TeamCompensationPage teamCompensationPage= homePage.clickTeamCompensation();
        CompensationPage compensationPage=teamCompensationPage.clickEmployee();
        ManageCompensationPage manageCompensationPage=compensationPage.clickManageCompensation();
        AwardCompensation awardCompensation=manageCompensationPage.clickAwardCompensation();
        awardCompensation.selectPlan();
        awardCompensation.selectOption();
        awardCompensation.selectPeriodicity();
        awardCompensation.typeAmount();
        awardCompensation.clickOK();
        manageCompensationPage.isLoaded();
        ReviewCompensationPage reviewCompensationPage=manageCompensationPage.clickContinue();
        reviewCompensationPage.clickSubmit();
        reviewCompensationPage.clickWarningOK();
        reviewCompensationPage.clickConfirmOK();
        teamCompensationPage.isLoaded();
        logout();
    }

    protected void salesQuotaManagement() {
        login(crmTMUserName, crmTMUserPassword);
        StepReport.info("Login CMR Sales Territory Mgmt Test successful");
        Navigator navigator = homePage.clickNavigator();
        ManageSalesQuota manageSalesQuota = navigator.clickQuotas();
        ManageResourceQuotasAPAC manageResourceQuotasAPAC=manageSalesQuota.clickEditAPAC();
        manageResourceQuotasAPAC.inputAPACQuota();
        manageResourceQuotasAPAC.clickOK();
        manageSalesQuota.isLoaded();
        manageSalesQuota.clickPublish();
        DriverUtil.sleep(3000L);
        logout();
    }

    protected void reAssignTask(String user,String pwd, String reportName,String newUser, String reassignType) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        searchReport(reportName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickOnExpenseReport(reportName);
        DriverUtil.sleep(10000L);
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        //Get all window handles
        Set<String> allHandles = driver.getWindowHandles();

        //count the handles Here count is=2
        System.out.println("Count of windows:" + allHandles.size());

        //Get current handle or default handle
        String currentWindowHandle = allHandles.iterator().next();
        System.out.println("currentWindow Handle" + currentWindowHandle);

        //Remove first/default Handle
        allHandles.remove(allHandles.iterator().next());

        //get the last Window Handle
        String lastHandle = allHandles.iterator().next();
        System.out.println("last window handle" + lastHandle);
        System.out.println(driver.getTitle());
        driver.switchTo().window(lastHandle);
        System.out.println(driver.getTitle());

        EnrollmentRequest enrollment = PageFactory.getPage(EnrollmentRequest.class);
        enrollment.isLoaded();

        if (reassignType.equals("ReAssign")) {
            ReassignTask reassignTask = enrollment.clickReassign();
            reassignTask.reassignUser(newUser);

        }

        if (reassignType.equals("Delegate")) {
            ReassignTask reassignTask = enrollment.clickReassign();
            reassignTask.delegateUser(newUser);
        }

        SelUtil.waitUntilPageClosed();
        driver.switchTo().window(currentWindowHandle);
        logout();

    }

        protected boolean checkifSandBoxExists(String User1, String Password1,String sbName){
        login(User1,Password1);
        StepReport.info("Logged in as:", User1);
        SettingsAndActions settingsAndActions = homePage.clickSettingsAndActions();
        ManageSandBoxes manageSandBoxes = settingsAndActions.clickManageSandBoxes();
        boolean ifSandBoxExists=false;
        ifSandBoxExists=manageSandBoxes.checkIfSandBoxExists(User1,sbName);
        manageSandBoxes.clickCloseButton();
        logout();
        return ifSandBoxExists;
    }

    protected void modifytheme(String User1, String Password1){
        login(User1,Password1);
        StepReport.info("Logged in to Blue UI:", User1);
        /*Navigator navigator1 = homePage.clickNavigator();
        NavigatorPopUp navigatorPopUp1=navigator1.clickMore();
        ThemesPage themesPage=navigatorPopUp1.clickAppearance();*/

        ApplicationComposer applicationComposer=homePage.clickApplicationComposer();
        ThemesPage themesPage=applicationComposer.clickAppearance();
        themesPage.changeBackgroundcolor("FFA500"); //Orange=FFA500
        themesPage.clickApply("ven_Orange");//Custom Theme Name
        homePage.clickHome();
        logout();
    }


    protected void modifythemeOrangeToBlue(String User1, String Password1){
        login(User1,Password1);
        StepReport.info("Logged in to Orange UI:", User1);
        ApplicationComposer applicationComposer=homePage.clickApplicationComposer();
        ThemesPage themesPage=applicationComposer.clickAppearance();
        themesPage.changeBackgroundcolor("72BBF2"); //Blue=FFA500
        themesPage.clickApply("ven_Orange");//Custom Theme Name
        homePage.clickHome();
        logout();
    }

    protected void useDefaultTheme(String User1, String Password1){
        login(User1,Password1);
        StepReport.info("Logged in as:", User1);

        //ApplicationComposer applicationComposer=homePage.clickApplicationComposer();
        ThemesPage themesPage=homePage.clickAppearance();
        themesPage.selectTheme("Autumn Red");
        //themesPage.changeBackgroundcolor("72BBF2"); //Blue=FFA500
        //themesPage.clickApply("ven_Orange");//Custom Theme Name
       // homePage.clickHome();
       // logout();
    }

}


