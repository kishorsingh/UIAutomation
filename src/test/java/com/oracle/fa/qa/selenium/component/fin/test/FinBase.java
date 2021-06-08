package com.oracle.fa.qa.selenium.component.fin.test;

import java.util.Set;

import com.oracle.fa.qa.selenium.component.common.page.*;
import com.oracle.fa.qa.selenium.component.fin.page.*;
import com.oracle.fa.qa.selenium.component.fin.page.ReassignTask;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.oracle.fa.qa.selenium.component.common.test.TestBase;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.sql.*;

public class FinBase extends TestBase {

    protected String createExpenseReport(String user, String password) {
        return createExpenseReport(user, password, false);
    }

    protected String createInvoice(String user, String password, boolean initiateApproval, boolean addReportAttachment) {
        //Login as User1
        String InvNum = "INV" + SelUtil.getCurrentLocalDateTimeStamp();
        String InvGroup = "INVG" + SelUtil.getCurrentLocalDateTimeStamp();
        System.out.println("Invoice Number : " + InvNum);
        login(user, password);
        StepReport.info("Login Test successful");
        DriverUtil.sleep(2000L);
        Navigator navigator = homePage.clickNavigator();
        Invoices invoices = navigator.clickInvoices();
        invoices.isLoaded();
        DriverUtil.sleep(3000L);
        invoices.clickTasks();
        DriverUtil.sleep(2000L);
        CreateInvoice createInvoice = invoices.createInvoice();
        createInvoice.fillUpPO("1000183");
        createInvoice.enterInvoiceGroup(InvGroup);
        createInvoice.enterInvoiceNumber(InvNum);
        createInvoice.enterAmount("8000.00");
        createInvoice.enterGridAmount("8000.00");
        if (addReportAttachment) {
            addAttachment(attachmentSampleFileName, attachmentSampleFileName, attachmentSampleFileName, null, null, createInvoice, null, null, "3");
        }
        createInvoice.clickSave();
        createInvoice.clickOKButton();
        if(initiateApproval) {
            createInvoice.initiateApproval();
        }
        logout();
        return InvNum;
    }
    protected boolean isDatabaseConnectionValid(){
        boolean value=false;
        String URL = "jdbc:oracle:thin:@" + databaseHostName + ":" + databasePort +":"+ databaseSID;
        Connection conn;
        try{
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(URL, databaseUser1, databaseUser1Pwd);
            value=true;
        }catch (SQLException e)
        {
            System.out.println("Caught Database Exception: " + e);
            System.out.println("Please enter correct database connection parameters:" +
                    "Make changes in test.java.resources.config.testconfig.properties file\n" +
                    "test.database.port=1616\n" +
                    "test.database.hostname=slc14phy.us.oracle.com\n" +
                    "test.database.sid=ORCL2\n" +
                    "test.fa.hostname=slc14qqa.us.oracle.com");

            value = false;
        }
        return value;
    }


    /*
       createInvoiceUsingSQLInjection does 2 things
       1) Creates Invoices with x number of lines, y(1-3) number of Distributions, and line amount z. Also you can provide prefix
       2) If initiate is true, invoice will be initiated else it will remain in REQUIRED state.

       prefix - Any ASCII character
       numberOfLines - number of lines in an invoice
       numberOfDistributions - number of Distributions per invoice
       initiate - boolean value whether to iniatiate or default state REQUIRED
       lineAmount - line Amount should be divisible by numberOfDistributions
       Prerequisites:
       Following properties in testconfig.properties file should be filled as per FA Env database.
       test.database.hostname=slcr01dev.us.oracle.com
       test.database.port=1616
       test.database.sid=ORCL2
    */
    protected String createInvoiceUsingSQLInjection(String prefix, int numberOfLines, int numberOfDistributions, boolean initiate, int lineAmount){
        String invName= prefix + "-" + numberOfLines + "-" + numberOfDistributions + "-" + SelUtil.getCurrentLocalDateTimeStamp();
        String URL = "jdbc:oracle:thin:@" + databaseHostName + ":" + databasePort + ":" + databaseSID;
        Connection conn = null;
        Statement stmt = null;
        String userId = "FINUSER1";

        if (numberOfDistributions < 1 || numberOfDistributions >3){
            numberOfDistributions = 3;
        }

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(URL, databaseUser1, databaseUser1Pwd);
            stmt = conn.createStatement();
            CallableStatement cstmt = null;

            String sql1;
            sql1 = "CREATE OR REPLACE PROCEDURE CreateInvoiceUsingSQL(invoicePrefix IN varchar2, user in varchar2, numberOflines in NUMBER, numberOfdist in NUMBER, lineAmt in NUMBER)\n" +
                    "IS\n" +
                    "  i number;\n" +
                    "  j number;\n" +
                    "  l_inv_num_prefix varchar2(100) := invoicePrefix;\n" +
                    "  l_created_by varchar2(15) := user;\n" +
                    "  l_no_of_invoices number := 1;\n" +
                    "  l_no_of_lines number := numberOflines;\n" +
                    "  l_no_of_dist number := numberOfdist;\n" +
                    "  l_inv_id number;\n" +
                    "  l_event_ns VARCHAR2(1000) :=  'http://xmlns.oracle.com/apps/financials/components/payablesSoa/ap/invoices/transactions/invoiceApprovalComposite/FinApInvTransactionsInvoiceApprovalComposite/InvoiceApproval';\n" +
                    "  l_schema_ns VARCHAR2(1000):=  'http://xmlns.oracle.com/apps/financials/components/payablesSoa/ap/invoices/transactions/invoiceApprovalComposite/FinApInvTransactionsInvoiceApprovalComposite/InvoiceApprovalProcessMessage';\n" +
                    "  l_event_name VARCHAR2(80) := 'InvoiceApproval';\n" +
                    "  l_payload VARCHAR2(5000);\n" +
                    "BEGIN\n" +
                    "     for i in 1..l_no_of_invoices loop\n" +
                    "         select ap_invoices_s.nextval into l_inv_id from sys.dual;\n" +
                    "         insert into ap_invoices_all\n" +
                    "           (invoice_id, invoice_num, set_of_books_id, ACCTS_PAY_CODE_COMBINATION_ID,\n" +
                    "            invoice_currency_code,payment_currency_code, payment_cross_rate, terms_id ,terms_date,\n" +
                    "            approval_ready_flag, wfapproval_status, gl_date, org_id, legal_entity_id,\n" +
                    "            creation_date, created_by, last_update_date, last_updated_by,object_version_number,vendor_id,vendor_site_id,invoice_date,invoice_amount,invoice_type_lookup_code,\n" +
                    "            party_id,party_site_id,source,pay_group_lookup_code,requester_id) \n" +
                    "          values (l_inv_id, l_inv_num_prefix, 1, 12854,\n" +
                    "                  'USD','USD',1,10001,trunc(sysdate),\n" +
                    "                  'Y','REQUIRED',trunc(sysdate), 204, 204,\n" +
                    "                  trunc(sysdate),l_created_by, sysdate, l_created_by, 1,300100031591572,300100031591589,sysdate,lineAmt*numberOflines,'STANDARD',300100031591570,300100031591583,'INVOICE GATEWAY',null,null) ;\n" +
                    "  \n" +
                    "          for j in 1..l_no_of_lines loop\n" +
                    "              insert into ap_invoice_lines_all\n" +
                    "                (invoice_id, line_number, line_type_lookup_code, line_source,org_id,accounting_date,set_of_books_id,amount,wfapproval_status,\n" +
                    "                 creation_date,created_by,last_updated_by,last_update_date,object_version_number)\n" +
                    "               values\n" +
                    "                (l_inv_id, j, 'ITEM', 'MANUAL LINE ENTRY', 204,sysdate,1,lineAmt,'REQUIRED',\n" +
                    "                 trunc(sysdate),l_created_by,l_created_by,sysdate,1);\n" +
                    "  \n" +
                    "              for k in 1..l_no_of_dist loop \n" +
                    "                  insert into ap_invoice_distributions_all\n" +
                    "                    (invoice_id, invoice_line_number, distribution_line_number, dist_code_combination_id, invoice_distribution_id, line_type_lookup_code,\n" +
                    "                     period_name, set_of_books_id, org_id, accounting_date, assets_addition_flag, assets_tracking_flag,\n" +
                    "                     creation_date,created_by,last_updated_by,last_update_date,object_version_number,amount)\n" +
                    "                   values\n" +
                    "                   (l_inv_id, j, k, 98859, AP_INVOICE_DISTRIBUTIONS_S.NEXTVAL,'ITEM',\n" +
                    "                     'Apr-15',1,204,sysdate,'U','N',\n" +
                    "                     trunc(sysdate),l_created_by,l_created_by,sysdate,1,lineAmt/3);\n" +
                    "               end loop;\n" +
                    "           end loop;   \n" +
                    "       end loop;  \n" +
                    "\n" ;

            if (initiate){
                sql1 = sql1 +  "       l_payload := '<eb:business-event xmlns:eb=\"http://oracle.com/fabric/businessEvent\" xmlns:ob=\"' || l_event_ns ||\n" +
                        "                    '\"><eb:name>ob:' || l_event_name  || '</eb:name><eb:content><CU:InvoiceApprovalProcessRequestMessage xmlns:CU=\"'  || l_schema_ns || '\">';\n" +
                        "             l_payload := l_payload ||'<CU:InvoiceApprovalBatchSubmissionJobId/>\n" +
                        "                                       <CU:Requester>' || user ||'</CU:Requester>\n" +
                        "                                                 <CU:Invoices>\n" +
                        "                                                     <CU:InvoiceId>' || l_inv_id ||'</CU:InvoiceId>\n" +
                        "                                                     <CU:InvoiceNumber>' || l_inv_num_prefix ||'</CU:InvoiceNumber> \n" +
                        "                                                 </CU:Invoices>';\n" +
                        "\n" +
                        "      l_payload := l_payload || '</CU:InvoiceApprovalProcessRequestMessage></eb:content></eb:business-event>';\n" +
                        "      fnd_global.initialize_session('D07276AD44A627628E80A4B424BD3C3D', user,null,null,null,'JAVA');\n" +
                        "      edn_publish_event (l_event_ns, l_event_name, to_clob(l_payload));\n" ;

            }

            sql1 = sql1 +   "      COMMIT;\n" +
                    "     \n" +
                    "END;";

            stmt.executeUpdate(sql1);
            String sql92Style = "{ call CreateInvoiceUsingSQL (?,?,?,?,?) }";
            cstmt = conn.prepareCall( sql92Style );
            cstmt.setString(1,invName);
            cstmt.setString(2,userId);
            cstmt.setInt(3,numberOfLines);
            cstmt.setInt(4,numberOfDistributions);
            cstmt.setInt(5,lineAmount);
            cstmt.execute();

        } catch (SQLException e) {
            System.out.println("Caught Database Exception: " + e);
            invName = "FAILURE";
        }
        finally
        {
            try
            {
                if( conn != null )
                    conn.close();
            }
            catch (SQLException ignore ) {}
        }

        return invName;

    }


    protected String createExpenseReport(String user, String password, boolean addReportAttachment) {
        //Login as User1
        String expenseReportName = "";
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        TravelandExpenses travelandExpenses = navigator.clickExpenses();
        CreateExpenseReport createExpenseReport = travelandExpenses.clickCreateExpenseReport();
        createExpenseReport.typePurpose("ExpenseReportAuto");
        createExpenseReport.selectPaymentCurrency("USD - US Dollar");
        createExpenseReport.clickReadAcceptCheckBox();
        if (addReportAttachment) {
            addAttachment(attachmentSampleFileName, attachmentSampleFileName, attachmentSampleFileName, createExpenseReport, null, null, null, null, "1");
        }
        CreateExpenseItem createExpenseItem = createExpenseReport.addExpenseItem();
        createExpenseItem.selectTemplate("Travel");
        CreateItemDetails createItemDetails = createExpenseItem.selectType("Airfare");
        createItemDetails.enterExpenseDescription("HQ Trip");
        createExpenseItem.selectReceiptCurrency("USD - US Dollar");
        createExpenseItem.enterReceiptAmount("3000.00");
        createExpenseReport = createExpenseItem.clickSaveAndClose();
        String expRepName = createExpenseReport.getExpenseReportName();
        StepReport.info("Expense Report Name: ",expRepName);
        expenseReportName = expRepName.trim();
        Confirmation confirmation = createExpenseReport.submitExpenseReport();
        if (confirmation != null) {
            confirmation.clickOk();
        }
        DriverUtil.sleep(3000L);
        travelandExpenses.isLoaded();
        
        logout();
        return expenseReportName;
    }


    protected void approveExpenseReport(String user, String pwd, String expenseReportName) {
        approveExpenseReport(user, pwd, expenseReportName, false);
    }

    protected void rejectExpenseReport(String user, String pwd, String expenseReportName) {
        rejectExpenseReport(user, pwd, expenseReportName, false);
    }

    protected void approveExpenseReport(String user, String pwd, String expenseReportName, boolean checkBellNotification) {
        expenseReportOperations(user, pwd, null, expenseReportName, checkBellNotification, "approve", false, false);
    }

    protected void rejectExpenseReport(String user, String pwd, String expenseReportName, boolean checkBellNotification) {
        expenseReportOperations(user, pwd, null, expenseReportName, checkBellNotification, "reject", false, false);
    }


    protected void expenseReportOperations(String user, String pwd, String adHocUser, String expenseReportName,
                                           boolean checkBellNotification, String operation,
                                           boolean checkComment, boolean checkAttachment) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        if (checkBellNotification) {
            checkBellNotification(expenseReportName);
        }
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        searchReport(expenseReportName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickOnExpenseReport(expenseReportName);
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

        ExpenseReport expenseReport = PageFactory.getPage(ExpenseReport.class);
        expenseReport.isLoaded();
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
            expenseReport.clickApprove();
        }
        if (operation.equals("reject")) {
            expenseReport.clickReject();
        }
        if (operation.equals("reqInfo")) {
            RequestMoreInformationOldScreen requestMoreInformationOldScreen = expenseReport.clickRequestMoreInfo();
            requestMoreInformationOldScreen.typeComment("requestInfo1");
            requestMoreInformationOldScreen.clickOK();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("provideInfo")) {
            AddComment addComment = expenseReport.clickAddComment();
            addComment.typeComment("requestInfoProvided");
            addComment.clickOK();
            expenseReport.clickSubmitInformation();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("adhoc_user")) {
            AdhocRoute adhocRoute = expenseReport.clickAdhocRoute();
            adhocRoute.adhocUser(adHocUser, "Please Approve");
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("approve") || operation.equals("reject")) {
            driver.switchTo().window(currentWindowHandle);
            System.out.println(driver.getTitle());
            DriverUtil.sleep(2000L);
            searchCompletedReport(expenseReportName, worklistNotificationsApprovals);
            StepReport.assertTrue("Expense report displayed under completed ",
                    worklistNotificationsApprovals.verifyReportExists(expenseReportName));
            DriverUtil.sleep(2000L);
        }
        logout();
    }


    protected void cashAdvanceReportOperations(String user, String pwd, String cashAdvReportName,
                                               boolean checkBellNotification, String operation, boolean checkComment, boolean checkAttachment) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        if (checkBellNotification) {
            checkBellNotification(cashAdvReportName);
        }
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        searchReport(cashAdvReportName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickOnExpenseReport(cashAdvReportName);
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

        CashAdvanceReport cashAdvanceReport = PageFactory.getPage(CashAdvanceReport.class);
        cashAdvanceReport.isLoaded();
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
            cashAdvanceReport.clickApprove();
        }
        if (operation.equals("reject")) {
            cashAdvanceReport.clickReject();
        }

        if (operation.equals("reqInfo")) {
            RequestMoreInformationOldScreen requestMoreInformationOldScreen = cashAdvanceReport.clickRequestMoreInfo();
            requestMoreInformationOldScreen.typeComment("requestInfo1");
            requestMoreInformationOldScreen.clickOK();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("provideInfo")) {
            AddComment addComment = cashAdvanceReport.clickAddComment();
            addComment.typeComment("requestInfoProvided");
            addComment.clickOK();
            cashAdvanceReport.clickSubmitInformation();
            driver.switchTo().window(currentWindowHandle);
        }


        if (operation.equals("approve") || operation.equals("reject")) {
            driver.switchTo().window(currentWindowHandle);
            System.out.println(driver.getTitle());
            DriverUtil.sleep(2000L);
            searchCompletedReport(cashAdvReportName, worklistNotificationsApprovals);
            StepReport.assertTrue("Expense report displayed under completed ",
                    worklistNotificationsApprovals.verifyReportExists(cashAdvReportName));
            DriverUtil.sleep(2000L);
        }

        logout();
    }

    protected void reAssignTask(String user,String pwd, String reportName,String newUser, String reassignType) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
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

        CashAdvanceReport cashAdvanceReport = PageFactory.getPage(CashAdvanceReport.class);
        cashAdvanceReport.isLoaded();

        if (reassignType.equals("ReAssign")) {
            ReassignTask reassignTask = cashAdvanceReport.clickReassign();
            reassignTask.reassignUser(newUser);

        }

        if (reassignType.equals("Delegate")) {
            ReassignTask reassignTask = cashAdvanceReport.clickReassign();
            reassignTask.delegateUser(newUser);
        }

        SelUtil.waitUntilPageClosed();
        driver.switchTo().window(mainWindow);
        logout();

    }


    protected void invoiceReportOperations(String user, String pwd, String invoiceName,
                                           boolean checkBellNotification, String operation, boolean checkComment, boolean checkAttachment) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        if (checkBellNotification) {
            checkBellNotification(invoiceName);
        }
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        searchReport(invoiceName, worklistNotificationsApprovals);
        worklistNotificationsApprovals.clickOnExpenseReport(invoiceName);
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

        InvoiceReport invoiceReport = PageFactory.getPage(InvoiceReport.class);
        invoiceReport.isLoaded();
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
            invoiceReport.clickApprove();
        }
        if (operation.equals("reject")) {
            invoiceReport.clickReject();
        }

        if (operation.equals("reqInfo")) {
            RequestMoreInformation requestMoreInformation = invoiceReport.clickRequestInfo();
            requestMoreInformation.typeComments("requestInfo1");
            requestMoreInformation.clickOK();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("provideInfo")) {
            AddComment addComment = invoiceReport.clickAddComment();
            addComment.typeComment("requestInfoProvided");
            addComment.clickOK();
            invoiceReport.clickSubmitInformation();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equalsIgnoreCase("Withdraw")) {
            invoiceReport.clickWithdraw();
            driver.switchTo().window(currentWindowHandle);
        }

        if (operation.equals("approve") || operation.equals("reject")) {
            driver.switchTo().window(currentWindowHandle);
            System.out.println(driver.getTitle());
            DriverUtil.sleep(2000L);
            searchCompletedReport(invoiceName, worklistNotificationsApprovals);
            StepReport.assertTrue("Invoice displayed under completed ",
                    worklistNotificationsApprovals.verifyReportExists(invoiceName));
            DriverUtil.sleep(2000L);
        }
        logout();
    }

    protected void verifyInvoiceStatus(String user, String password, String invoiceNumber, String approvalStatus) {
        login(user, password);
        StepReport.info("Login Test successful");
        DriverUtil.sleep(2000L);
        Invoices invoices = homePage.clickInvoices();
        ManageInvoices manageInvoices = invoices.manageInvoices();
        manageInvoices.searchInvoice(invoiceNumber);
        Assert.assertTrue(manageInvoices.verifyApprovalStatus(approvalStatus),"Verify if the approval status is: "+approvalStatus);
        logout();
    }


    protected String createCashAdvance(String user, String password, boolean addReportAttachment) {
        //Login as User1
        String cashAdvReportName = "";
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        TravelandExpenses travelandExpenses = navigator.clickExpenses();
        RequestCashAdvance requestCashAdvance = travelandExpenses.clickRequestCashAdvance();
        requestCashAdvance.typeAmount("3000.00");
        requestCashAdvance.typePurpose("Automation Test");
        if (addReportAttachment) {
            addAttachment(attachmentSampleFileName, attachmentSampleFileName, attachmentSampleFileName, null, requestCashAdvance, null, null, null, "2");
        }
        CashAdvanceConfirmation cashAdvanceConfirmation = requestCashAdvance.submitCashAdvanceReport();
        String repName=cashAdvanceConfirmation.getCashAdvanceReportName();
        cashAdvReportName = repName.trim();
        travelandExpenses.isLoaded();
        DriverUtil.sleep(3000L);
        logout();

        return cashAdvReportName;
    }

    protected String reSubmitCashAdvance(String user, String password, String cashAdvReportName) {
        //Login as User1
        String newCashAdvReportName = "";
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        TravelandExpenses travelandExpenses = navigator.clickExpenses();
        travelandExpenses.clickSelectCashAdvances();
        RequestCashAdvance requestCashAdvance = travelandExpenses.clickcashAdvReport(cashAdvReportName);
        requestCashAdvance.typeAmount("2800.00");
        CashAdvanceConfirmation cashAdvanceConfirmation = requestCashAdvance.submitCashAdvanceReport();
        String repName = cashAdvanceConfirmation.getCashAdvanceReportName();
        System.out.println(" New Cash Advance Report Name: " + repName);
        newCashAdvReportName = repName.trim();
        //travelandExpenses = cashAdvanceConfirmation.clickOk();
        travelandExpenses.isLoaded();
        DriverUtil.sleep(3000L);
        logout();
        return newCashAdvReportName;
    }

    protected void setupInvoiceApproval(String operation) {
        login(applConsultant, applConsultantPwd);
        StepReport.info("Login Test successful");
        SetupMaintenance setupMaintenance = homePage.clickSetupMaintenance();
        //setupMaintenance.changeSetup("Financials");
        setupMaintenance.clickTasks();
        ImplementationProjects implementationProjects = setupMaintenance.clickManageImplementationProjects();
        implementationProjects.typeTask("Financials IP");
        implementationProjects.clickSearchButton();
        implementationProjects.clickSearchResults("Financials IP");
        implementationProjects.searchAndClickTask("Manage Invoice Options");
        ManageInvoiceOptions manageInvoiceOptions = implementationProjects.clickGoToTask();
        if (operation.equalsIgnoreCase("select")) {
            isFinancialSetupExists = manageInvoiceOptions.selectInvoiceApproval();
            isAutoFinancialSetupExists = false;
        } else if (operation.equalsIgnoreCase("deselect")) {
            isAutoFinancialSetupExists = manageInvoiceOptions.deselectInvoiceApproval();
            isFinancialSetupExists = false;
        }
        logout();
    }

    protected void setupInvoiceForceApproval(String operation) {
        login(applConsultant, applConsultantPwd);
        StepReport.info("Login Test successful");
        SetupMaintenance setupMaintenance = homePage.clickSetupMaintenance();
        //setupMaintenance.changeSetup("Financials");
        setupMaintenance.clickTasks();
        ImplementationProjects implementationProjects = setupMaintenance.clickManageImplementationProjects();
        implementationProjects.typeTask("Financials IP");
        implementationProjects.clickSearchButton();
        implementationProjects.clickSearchResults("Financials IP");
        implementationProjects.searchAndClickTask("Manage Invoice Options");
        ManageInvoiceOptions manageInvoiceOptions = implementationProjects.clickGoToTask();
        if (operation.equalsIgnoreCase("select")) {
            isFinancialSetupExists = manageInvoiceOptions.selectInvoiceApproval();
        } else if (operation.equalsIgnoreCase("deselect")) {
            isFinancialSetupExists = manageInvoiceOptions.deselectInvoiceApproval();
        }
        isForceFinancialSetupExists = manageInvoiceOptions.selectInvoiceForceApproval();
        logout();
    }

    protected void invoiceForceApproval(String user, String password, String invoiceNumber) {
        login(user, password);
        StepReport.info("Login Test successful");
        DriverUtil.sleep(2000L);
        Invoices invoices = homePage.clickInvoices();
        ManageInvoices manageInvoices = invoices.manageInvoices();
        manageInvoices.searchInvoice(invoiceNumber);
        manageInvoices.clickInvoice(invoiceNumber);
        manageInvoices.forceApproval();
        logout();
    }

    protected String createInterCompanyTransaction(String user, String password) {
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        Transactions transactions = navigator.clickTransactions();
        CreateIntercompanyBatch createIntercompanyBatch = transactions.createTransaction();
        createIntercompanyBatch.fillProvider("SSC Operations 01");
        createIntercompanyBatch.enterTransactionTypeName("Intercompany Sales");
        createIntercompanyBatch.clickAddTransactions();
        createIntercompanyBatch.enterReceiverName("SSC Operations 02");
        createIntercompanyBatch.enterDebitTransactionsValue("1.00");
        createIntercompanyBatch.clickAddRow();
        createIntercompanyBatch.enterAccountNumber("00-000-1110-0000-000");
        createIntercompanyBatch.enterCreditValue("1.00");
        createIntercompanyBatch.clickReceiver();
        createIntercompanyBatch.clickAddRow();
        createIntercompanyBatch.enterAccountNumber("00-000-1120-0000-000");
        createIntercompanyBatch.enterDebitValue("1.00");
        createIntercompanyBatch.clickSubmit();
        String batchNumber = createIntercompanyBatch.getBatchNumber();
        logout();
        return batchNumber;
    }

    protected String createAdHocPayment(String user, String password) {
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        CashBalances cashBalances = navigator.clickCashBalances();
        CreateAdhocPayment createAdhocPayment = cashBalances.clickCreateAdHocPayment();
        createAdhocPayment.enterFromAccountName("BofA-498");
        createAdhocPayment.enterPayeeName("AutoPayee290917-130844");
        createAdhocPayment.enterPayeeAccount("AutoPayee290917-130844");
        createAdhocPayment.enterPaymentAmount("8000");
        createAdhocPayment.enterPaymentMethod("Electronic");
        createAdhocPayment.enterPaymentProfile("AP Basic Electronic");
        createAdhocPayment.enterBusinessUnit("Vision ADB");
        createAdhocPayment.clickSubmit();
        String adhocPaymentNumber = createAdhocPayment.getPaymentNumber();
        cashBalances = createAdhocPayment.clickYes();
        logout();
        return adhocPaymentNumber;
    }

    protected void verifyAdhocPaymentStatus(String user, String pwd, String account) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        worklistNotificationsApprovals.switchToInitiatedTasks();
        DriverUtil.sleep(2000L);
        worklistNotificationsApprovals.selectStatus("Completed");
        DriverUtil.sleep(3000L);
        worklistNotificationsApprovals.typeFilter(account);
        worklistNotificationsApprovals.clickSearch();
        StepReport.assertTrue("Task displayed under completed ",
                worklistNotificationsApprovals.verifyReportExists(account));
        logout();
    }

    protected void verifyDimensionID(String user, String pwd, String stageType, String collectiosType, String expectedDimensionID) {
        String actualDimensionID = "";
        login(user, pwd);
        StepReport.info("Login Test successful");
        BellNotification bellNotification = homePage.clickBellNotification();
        bellNotification.clickMoreDetails();

        DriverUtil.sleep(10000L);
        String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        //Get all window handles
        Set<String> allHandles = driver.getWindowHandles();

        //count the handles Here count is=2
        System.out.println("Count of windows:"+allHandles.size());

        //Get current handle or default handle
        String currentWindowHandle = allHandles.iterator().next();
        System.out.println("currentWindow Handle"+currentWindowHandle);

        //Remove first/default Handle
        allHandles.remove(allHandles.iterator().next());

        //get the last Window Handle
        String lastHandle = allHandles.iterator().next();
        System.out.println("last window handle"+lastHandle);
        System.out.println(driver.getTitle());
        driver.switchTo().window(lastHandle);
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());

        WorklistNotificationsApprovals worklistNotificationsApprovals = PageFactory.getPage(WorklistNotificationsApprovals.class);
        worklistNotificationsApprovals.isLoaded();
        worklistNotificationsApprovals.clickMenu();
        worklistNotificationsApprovals.clickAdministration();
        worklistNotificationsApprovals.clickTaskConfiguration();
        worklistNotificationsApprovals.typeSearchText("FinApInvTransactionsInvoiceApprovalComposite");
        worklistNotificationsApprovals.clickSearchButton();
        worklistNotificationsApprovals.clickComposite("FinApInvoiceApproval");
        worklistNotificationsApprovals.clickEditTask();
        worklistNotificationsApprovals.clickAssignees();
        if(stageType.equalsIgnoreCase("repeated")) {
            worklistNotificationsApprovals.clickRepeatingStage();
            worklistNotificationsApprovals.selectCollections(collectiosType);
            worklistNotificationsApprovals.goToRuleRepeated();
        }
        if(stageType.equalsIgnoreCase("nonRepeated")){
            worklistNotificationsApprovals.clickNonRepeatingStage();
            worklistNotificationsApprovals.goToRuleNonRepeated();
        }
        worklistNotificationsApprovals.clickAddRule();
        worklistNotificationsApprovals.clickExpandRule();
        worklistNotificationsApprovals.clickAddAction();
        worklistNotificationsApprovals.clickAddApprover();
        worklistNotificationsApprovals.clickApprovalGroup();
        actualDimensionID = worklistNotificationsApprovals.getDimensionID();
        worklistNotificationsApprovals.deleteRule("Rule 1");
        worklistNotificationsApprovals.clickRevert();
        StepReport.assertEquals("Verify if the Actual Dimension Id matches with the expected Dimension Id", actualDimensionID, expectedDimensionID);
        driver.switchTo().window(mainWindow);
        logout();

    }

    protected boolean verifyEscalationTask(String user, String pwd, String cashAdvReportName) {
        login(user, pwd);
        StepReport.info("Login Test successful");
        homePage.clickTools();
        WorklistNotificationsApprovals worklistNotificationsApprovals = homePage.clickWorklist();
        boolean isReportPresent = verifyIfReportPresent(cashAdvReportName, worklistNotificationsApprovals);
        logout();
        return isReportPresent;
    }

    protected String createExpenseReportWithTwoItems(String user, String password, boolean addReportAttachment) {
        //Login as User1
        String expenseReportName = "";
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        TravelandExpenses travelandExpenses = navigator.clickExpenses();
        CreateExpenseReport createExpenseReport = travelandExpenses.clickCreateExpenseReport();
        createExpenseReport.typePurpose("ExpenseReportAuto");
        createExpenseReport.selectPaymentCurrency("USD - US Dollar");
        createExpenseReport.clickReadAcceptCheckBox();
        if (addReportAttachment) {
            addAttachment(attachmentSampleFileName, attachmentSampleFileName, attachmentSampleFileName, createExpenseReport, null, null, null, null, "1");
        }
        CreateExpenseItem createExpenseItem = createExpenseReport.addExpenseItem();
        createExpenseItem.selectTemplate("Travel");
        CreateItemDetails createItemDetails = createExpenseItem.selectType("Airfare");
        createItemDetails.enterExpenseDescription("HQ Trip");
        createExpenseItem.selectReceiptCurrency("USD - US Dollar");
        createExpenseItem.enterReceiptAmount("2000.00");
        createExpenseReport = createExpenseItem.clickSaveAndClose();

        createExpenseItem = createExpenseReport.addExpenseItem();
        createExpenseItem.selectTemplate("Travel");
        createItemDetails = createExpenseItem.selectType("Airfare");
        createItemDetails.enterExpenseDescription("Open World");
        createExpenseItem.selectReceiptCurrency("USD - US Dollar");
        createExpenseItem.enterReceiptAmount("1000.00");
        createExpenseReport = createExpenseItem.clickSaveAndClose();

        String expRepName = createExpenseReport.getExpenseReportName();
        System.out.println("Expense Report Name: " + expRepName);
        expenseReportName = expRepName.trim();
        Confirmation confirmation = createExpenseReport.submitExpenseReport();
        if (confirmation != null) {
            confirmation.clickOk();
        }
        DriverUtil.sleep(3000L);
        travelandExpenses.isLoaded();
        logout();
        return expenseReportName;
    }

    protected void verifyEscalateAndExpire(String user, String pwd,String expenseReportName) {
        DriverUtil.sleep(15*60000L);
        login(user, pwd);
        StepReport.info("Login Test successful");
        BellNotification bellNotification = homePage.clickBellNotification();
        bellNotification.clickMoreDetails();

        DriverUtil.sleep(10000L);
        String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
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
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());

        SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);

        soaWorkListPage.clickAdministrativeTasks();

        StepReport.assertFalse("No tasks should be available in Assigned state",soaWorkListPage.verifyIfReportPresent(expenseReportName));

        soaWorkListPage.selectFilter("Expired");
        StepReport.assertTrue("Select Expired filter. Tasks should be available for that expense report number",soaWorkListPage.verifyIfReportPresent(expenseReportName));

        soaWorkListPage.selectFilter("Errored");
        StepReport.assertFalse("Select Errored filter. No tasks should be available.",soaWorkListPage.verifyIfReportPresent(expenseReportName));
    }



    protected void actionEmailNotificationfromZimbra(String actionType, String TaskNumber){

        StepReport.info("Start Login");
        ZimbraLogin zimbraLogin= PageFactory.getPage(ZimbraLogin.class);
        zimbraLogin.get("https://stbeehive.oracle.com/zimbra/");
        zimbraLogin.enterUserName(bpmSSOEmail);
        zimbraLogin.enterPassword(bpmSSOPassword);
        ZimbraHome zimbraHome=zimbraLogin.clickSignin();
        zimbraHome.checkHomePage();
        zimbraHome.clickGetMail();
        ZimbraMail zimbraMail=zimbraHome.openEmail(TaskNumber);
        zimbraMail.clickOnHighlightObjects();
        zimbraMail.clickOnApprove(actionType);

        zimbraMail.addComments(actionType);
        DriverUtil.sleep(5000);
        ZimbraAttachments zimbraAttachments=zimbraMail.clickAttachments();
        zimbraAttachments.browseFile();
        zimbraAttachments.clickAttach();
        DriverUtil.sleep(5000);
        zimbraMail.addComments("Attachment(s) Added");
        zimbraMail.sendEmail();
        zimbraMail.logout();
        StepReport.info("<END> Test Email Notifications");
    }
}
