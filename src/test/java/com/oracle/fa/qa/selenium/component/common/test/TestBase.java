package com.oracle.fa.qa.selenium.component.common.test;

import com.oracle.fa.qa.selenium.base.SeleniumBase;
import com.oracle.fa.qa.selenium.component.bpm.page.EMHomePage;
import com.oracle.fa.qa.selenium.component.common.page.*;
import com.oracle.fa.qa.selenium.component.fin.page.CreateExpenseReport;
import com.oracle.fa.qa.selenium.component.fin.page.CreateInvoice;
import com.oracle.fa.qa.selenium.component.fin.page.ExpenseReport;
import com.oracle.fa.qa.selenium.component.fin.page.RequestCashAdvance;
import com.oracle.fa.qa.selenium.component.prc.page.EditPODocument;
import com.oracle.fa.qa.selenium.component.prc.page.EditRequisition;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestBase extends SeleniumBase{
	
     protected String attachmentSampleFileName="a.txt";
	
	 protected void checkBellNotificationWithLogin(String user, String password,String reportName) {
		 DriverUtil.sleep(30000L);
	     login(user,password);
	     StepReport.info("Login Test successful");
	     if(homePage.checkTheme()){
			 StepReport.info("Skipping Bell Notifications for CustomUI");
			 logout();
		 }else{
			 BellNotification bellNotification=homePage.clickBellNotification();
			 DriverUtil.sleep(20000L);
			 StepReport.assertTrue("Report displayed under Bell Notification ", bellNotification.waitForReportAndReTry(reportName,homePage));
			 logout();
		 }
	 }

	protected void checkTaskNotInBellNotificationWithLogin(String user, String password,String reportName) {
		DriverUtil.sleep(30000L);
		login(user,password);
		StepReport.info("Login Test successful");
		BellNotification bellNotification=homePage.clickBellNotification();
		DriverUtil.sleep(20000L);
		// bellNotification.waitForReportAndReTry(expenseReportName, homePage);
		int i =0;
		for(i=0; i<5; i++){
			if(bellNotification.waitForReportAndReTry(reportName,homePage)){
				StepReport.assertTrue("Report displayed under Bell Notification ", bellNotification.waitForReportAndReTry(reportName,homePage));
				break;
			}else{
				DriverUtil.sleep(500000L);
			}
		}
		logout();
	}

	protected void checkTaskNotInBellNotificationWithLogin(String user, String password,String reportName, int waitInSeconds) {
		login(user,password);
		StepReport.info("Login Test successful");
		BellNotification bellNotification=homePage.clickBellNotification();
		DriverUtil.sleep(waitInSeconds * 1000);
		StepReport.assertTrue("Report not displayed under Bell Notification ", !bellNotification.waitForReportAndReTry(reportName, homePage, 1));
		logout();
	}

	 protected void checkBellNotification(String reportName) {
		 BellNotification bellNotification=homePage.clickBellNotification();
		 DriverUtil.sleep(2000L);
		 StepReport.assertTrue("Report displayed under Bell Notification ",
				 bellNotification.isExpenseReportExists(reportName));
	 }
	 
	 protected void addAttachment(String title, String desc, String filePath,
			                      CreateExpenseReport createExpenseReport, 
			                      RequestCashAdvance requestCashAdvance, 
			                      CreateInvoice createInvoice,
			                      EditPODocument editPODocument,
			                      EditRequisition editRequisition,String option) {
		    Attachment attachment=null;	
		    if(option.equals("1")) {	
			     attachment=createExpenseReport.clickAttachment();
			 }
			 if(option.equals("2")) {	
			     attachment=requestCashAdvance.clickAttachment();
			 }
			 if(option.equals("3")) {	
			     attachment=createInvoice.clickAttachment();
			 }
			 if(option.equals("4")) {	
			     attachment=editPODocument.clickAttachment();
			 }
			 if(option.equals("5")) {	
			     attachment=editRequisition.clickAttachment();
			 }
			 //attachment.attachFile(filePath);
			 attachment.selectType("Text");
			 DriverUtil.sleep(2000L);
			 attachment.typeFileText(title);
			 attachment.typeTile(title);
			 attachment.typeDescription(desc);
			 DriverUtil.sleep(1000L);
			 attachment.clickOk();
			 if(option.equals("1")) {
			 createExpenseReport.waitForAttachmentToDisplay(title);
			 }
			 if(option.equals("2")) {
				 requestCashAdvance.waitForAttachmentToDisplay(title);
			 }
			 if(option.equals("3")) {
				 createInvoice.waitForAttachmentToDisplay(title);
			 }
			 if(option.equals("4")) {
				 editPODocument.waitForAttachmentToDisplay(title);
			 }
			 if(option.equals("5")) {
				 editRequisition.waitForAttachmentToDisplay(title);
			 }
	 }

	protected SOAWorkListPage clickToOpenBPMWorklistWindow() {
		WebDriver driver= FrameworkContext.getInstance().getWebDriver();
		String mainWindow= driver.getWindowHandle();

		BellNotification bellNotification = homePage.clickBellNotification();
		DriverUtil.sleep(5000L);
		bellNotification.clickMoreDetailsLink();
		DriverUtil.sleep(10000);

		Set<String> windowHandle = driver.getWindowHandles();
		for(String s: windowHandle) {
			if(!mainWindow.equalsIgnoreCase(s)) {
				driver.switchTo().window(s);
				SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
				soaWorkListPage.isLoaded();
				return soaWorkListPage;
			}
		}
		return null;
	}
	 
	 protected void reAssignReport(String user,String pwd, String reportName,String newUser) {
		    login(user,pwd);
	    	StepReport.info("Login Test successful"); 
	    	homePage.clickTools();
	    	WorklistNotificationsApprovals worklistNotificationsApprovals=homePage.clickWorklist();
	       	searchReport(reportName,worklistNotificationsApprovals);
	       	ReassignTask reassignTask=worklistNotificationsApprovals.clickReAssign(reportName);
	       	reassignTask.delegateUser(newUser);
	       	DriverUtil.sleep(5000L);
	       	worklistNotificationsApprovals.isLoaded();
	 }
	 
	 protected void searchReport(String repName,WorklistNotificationsApprovals worklistNotificationsApprovals) {
		    worklistNotificationsApprovals.typeFilter(repName);
	    	DriverUtil.sleep(5000L);
		    worklistNotificationsApprovals.clickSearch();
	    	worklistNotificationsApprovals.waitForReportOnGrid(repName);
	 }
	 
	 protected void searchCompletedReport(String repName,WorklistNotificationsApprovals worklistNotificationsApprovals) {
		    worklistNotificationsApprovals=worklistNotificationsApprovals.refreshPage();
		    DriverUtil.sleep(1000L);
		    worklistNotificationsApprovals.selectStatus("Completed");
			DriverUtil.sleep(2000L);
			//worklistNotificationsApprovals.typeFilter(repName);
	    	//worklistNotificationsApprovals.clickSearch();
	 }
	 
	 protected void reportSuspendResumeOperation(String user,String pwd, String repName) {
		    login(user,pwd);
			StepReport.info("Login Test successful");
			homePage.clickTools();
			WorklistNotificationsApprovals worklistNotificationsApprovals=homePage.clickWorklist();
			searchReport(repName,worklistNotificationsApprovals);
			worklistNotificationsApprovals.clickSuspend(repName);
			worklistNotificationsApprovals.refreshPage();
			worklistNotificationsApprovals.selectStatus("Suspended");
			DriverUtil.sleep(3000L);
			worklistNotificationsApprovals.waitForReportOnGrid(repName);
			StepReport.assertTrue("Report displayed under Suspended ",
	    			worklistNotificationsApprovals.verifyReportExists(repName));
			worklistNotificationsApprovals.clickResume(repName);
			worklistNotificationsApprovals.refreshPage();
			worklistNotificationsApprovals.selectStatus("Assigned");
			worklistNotificationsApprovals.waitForReportOnGrid(repName);
			StepReport.assertTrue("Report displayed under Assigned ",
	    			worklistNotificationsApprovals.verifyReportExists(repName));
			logout();
	 }

	protected void reAssignTask(String user,String pwd, String reportName,String newUser, String reassignType) {
		login(user,pwd);
		StepReport.info("Login Test successful");
		homePage.clickTools();
		WorklistNotificationsApprovals worklistNotificationsApprovals=homePage.clickWorklist();
		searchReport(reportName,worklistNotificationsApprovals);
		ReassignTask reassignTask=worklistNotificationsApprovals.clickReAssign(reportName);
		if(reassignType.equalsIgnoreCase("Reassign")){
			reassignTask.reassignUser(newUser);
		}
		if(reassignType.equalsIgnoreCase("Delegate")){
			reassignTask.delegateUser(newUser);
		}
		DriverUtil.sleep(5000L);
		worklistNotificationsApprovals.isLoaded();
		logout();
	}

	protected void verifyESSJobStatus(String processID, int waitTimeInMinutes, String expectedStatus) {
		DriverUtil.sleep(waitTimeInMinutes*500*60);
		login(faAdminUser,faAdminUserPwd);
		StepReport.info("Login Test successful");
		Navigator navigator = homePage.clickNavigator();
		ScheduledProcesses scheduledProcesses = navigator.clickScheduledProcesses();
		String processStatus = scheduledProcesses.getProcessStatusAfterCompletion(processID,waitTimeInMinutes,expectedStatus);
		StepReport.assertEquals("Verify the process Status ",processStatus,expectedStatus);
		logout();
	}

	protected void loginInAllLanguage(String userId,String password) {
		StepReport.info("Start Login");
		LoginPage loginPage = PageFactory.getPage(LoginPage.class);
		loginPage.get(FrameworkContext.getInstance().getTestConfigParams().getBaseUrl());
		homePage = loginPage.loginInAllLanguage(userId,password);
		StepReport.info("Login successful");
	}

	protected void changeLanguageToKorean(String user, String password) {
		loginInAllLanguage(user, password);
		StepReport.info("Login Test successful");
		SetPreferences setPreferences = homePage.clickSetPreferences();
		setPreferences.clickLanguage();
		setPreferences.selectAllLanguageToKorean();
		setPreferences.clickSaveButton();
		homePage.logoutInAllLanguage();
	}

	protected void changeLanguageToEnglish(String user, String password) {
		loginInAllLanguage(user, password);
		StepReport.info("Login Test successful");
		SetPreferences setPreferences = homePage.clickSetPreferences();
		setPreferences.clickLanguage();
		setPreferences.selectAllLanguageToEnglish();
		setPreferences.clickSaveButton();
		homePage.logoutInAllLanguage();
	}

	protected void updateExpenseReportLargeComment(String user, String password, String taskName, String loopMessage) {
		login(user,password);
		StepReport.info("Login Test successful");

		String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
		String soaWorklistWindow = openWorklistWindow(mainWindow);
		openTaskDetailWindow(mainWindow, soaWorklistWindow, taskName);

		addExpenseReportLargeComment(loopMessage);

		openTaskDetailWindow(mainWindow, soaWorklistWindow, taskName);
		verifyCommentUpdatedSuccess(loopMessage);

		FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
		logout();
	}

	protected String openWorklistWindow(String mainWindow) {
		StepReport.info("Opening SOA Worklist Window");

		BellNotification bellNotification = homePage.clickBellNotification();
		DriverUtil.sleep(5000L);
		bellNotification.clickMoreDetailsLink();
		DriverUtil.sleep(10000);

		String soaWorklistWindow = null;
		Set<String> windowHandle = FrameworkContext.getInstance().getWebDriver().getWindowHandles();
		for(String s: windowHandle) {
			if (!mainWindow.equalsIgnoreCase(s)) {
				soaWorklistWindow = s;
				break;
			}
		}

		FrameworkContext.getInstance().getWebDriver().switchTo().window(soaWorklistWindow);
		FrameworkContext.getInstance().getWebDriver().manage().window().maximize();
		return soaWorklistWindow;
	}

	protected String openTaskDetailWindow(String mainWindow, String soaWorklistWindow, String taskName) {
		StepReport.info("Opening task detail window, task name is " + taskName);

		FrameworkContext.getInstance().getWebDriver().switchTo().window(soaWorklistWindow);

		SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
		soaWorkListPage.isLoaded();

		String taskXPath = "//a[contains(text(), '" + taskName + "')]";
		WebElement taskDetailLink = DriverUtil.getElement(By.xpath(taskXPath));
		PageLoadHelper.waitForElementVisible(taskDetailLink);
		taskDetailLink.click();
		DriverUtil.sleep(10000);

		String taskDetailWindow = null;
		Set<String> windowHandle = FrameworkContext.getInstance().getWebDriver().getWindowHandles();
		for(String s: windowHandle) {
			if (!mainWindow.equalsIgnoreCase(s) && !soaWorklistWindow.equalsIgnoreCase(s)) {
				taskDetailWindow = s;
				break;
			}
		}

		FrameworkContext.getInstance().getWebDriver().switchTo().window(taskDetailWindow);
		return taskDetailWindow;
	}

	private void addExpenseReportLargeComment(String loopMessage) {
		StepReport.info("Add expense report comment");
		ExpenseReport expenseReport = PageFactory.getPage(ExpenseReport.class);
		expenseReport.isLoaded();
		AddComment addComment = expenseReport.clickAddComment();
		addComment.isLoaded();
		String largeComment = generateLargeStringInJapanese(loopMessage, 5200);
		//String largeComment = generateLargeStringInJapanese(loopMessage, 10);
		addComment.typeComment(largeComment);
		DriverUtil.sleep(5000);
		addComment.clickOK();
		StepReport.assertTrue("Comment updated successful", isMessageShownInPage(loopMessage));
		expenseReport.clickSave();
	}

	private String generateLargeStringInJapanese(String loopMessage, int byteMoreThan) {
	 	StringBuilder result = new StringBuilder(loopMessage);
	 	while (result.toString().getBytes().length <= byteMoreThan) {
			result.append(loopMessage);
		}
		return result.toString();
	}

	private boolean isMessageShownInPage(String message) {
		StepReport.info("Test whether message " + message + " is shown in page");
	 	try {
	 		DriverUtil.getElement(By.xpath("//*[contains(text(), '" + message + "')]"));
	 		return true;
		} catch (Exception e) {
	 		return false;
		}
	}

	private void verifyCommentUpdatedSuccess(String message) {
		StepReport.info("Verify comment updated successful");
		ExpenseReport expenseReport = PageFactory.getPage(ExpenseReport.class);
		expenseReport.isLoaded();
		StepReport.assertTrue("Comment updated successful", isMessageShownInPage(message));
	}

	protected void createUser(String username) {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		Navigator navigator=homePage.clickNavigator();
		SecurityConsole securityConsole;
		try {
			securityConsole = navigator.clickSecurityConsole();
		}catch(NoSuchElementException e){
			securityConsole = homePage.clickSecurityConsole();
		}
		securityConsole.acceptWarning();
		securityConsole.clickUsers();
		if(securityConsole.isUserCreated(username)){
			logout();
			return;
		}
		securityConsole.clickAddUserAccount();
		securityConsole.enterValue("First Name","James");
		securityConsole.enterValue("Last Name","Cooper");
		if(faEnvVersion.equals("11.13.18.05.0")) {
			securityConsole.enterValue("E-Mail", "jcooper@oracle.com");
		}
		if(faEnvVersion.equals("11.13.18.10.0")) {
			securityConsole.enterValue("Email", "jcooper@oracle.com");
		}
		securityConsole.enterValue("User Name",username);
		securityConsole.enterValue("Password","Welcome1");
		securityConsole.enterValue("Confirm Password","Welcome1");
		securityConsole.clickSaveAndClose();
		logout();
	}

	protected List<String> getAssigneeAndType(String userName){
		String URL = "jdbc:oracle:thin:@" + databaseHostName + ":" + databasePort + ":" + databaseSID;
		Connection conn = null;
		Statement stmt;
		List<String> result = new ArrayList<>();

		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			conn = DriverManager.getConnection(URL, databaseSOAUser, databaseSOAUserPwd);

			String sql1, sql2;
			sql1 ="CREATE OR REPLACE VIEW \"FA_FUSION_SOAINFRA\".\"WFASSIGNEE_VIEW\" (\"TASKID\", \"ASSIGNEE\", \"TYPE\", \"ASSIGNEE2\", \"VERSION\", \"ORIGINALASSIGNEEUSER\",  \"CREATEDDATE\")\n"+
					"AS SELECT taskid , SUBSTR(str, 1, INSTR(str, ',',-1)-1) \n"+
					"AS assignee, SUBSTR(str, INSTR(str, ',',-1)+1) \n"+
					"AS type, str2 AS assignee2, version, originalassigneeuser, createddate \n"+
					"from (SELECT taskid, trim(regexp_substr(str, '[^:]+', 1, 1)) str, substr(str, length(trim(regexp_substr(str, '[^:]+', 1, 1)))+2) str2, version, originalassigneeuser, createddate \n"+
					"FROM (SELECT wh.taskid taskid, wh.assignees str, wh.version version, wh.originalassigneeuser originalassigneeuser, wh.createddate createddate \n"+
					"FROM wftaskhistory wh, wftask wf \n"+
					"WHERE wh.taskid = wf.taskid)) \n"+
					"COMMIT \n";

			sql2 = "select * from WFASSIGNEE_VIEW where assignee = '"+userName+"' order by createddate desc";

			stmt = conn.createStatement();
			System.out.println("Execute Query:"+sql1);
			stmt.executeQuery(sql1);

			System.out.println("Execute Query:"+sql2);
			ResultSet rs = stmt.executeQuery(sql2);
			rs.next();
			//System.out.println(rs.getString("ASSIGNEE"));
			//System.out.println(rs.getString("TYPE"));
			result.add(rs.getString("ASSIGNEE"));
			result.add(rs.getString("TYPE"));

		} catch (SQLException e) {
			System.out.println("Caught Database Exception: " + e);
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
		return result;
	}

	protected void configTaskToShowPasswordPrompt(String user, String password, String taskName) {
		login(user,password);
		StepReport.info("Login Test successful");

		String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
		openWorklistWindow(mainWindow);

		SOAWorkListPage soaWorkListPage = loadSOAWorkListPage();
		gotoTaskConfiguration(soaWorkListPage, taskName);
		enableTaskPasswordPrompt(soaWorkListPage);

		FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
		logout();
	}

	protected SOAWorkListPage loadSOAWorkListPage() {
		StepReport.info("Load SOA Worklist Page");
		SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
		soaWorkListPage.isLoaded();
		return soaWorkListPage;
	}

	protected void gotoTaskConfiguration(SOAWorkListPage soaWorkListPage, String taskName) {
		StepReport.info("Go to task configuration page for task " + taskName);
		soaWorkListPage.clickAdminstration();
		soaWorkListPage.clickTaskConfigurationButton();
		soaWorkListPage.typeSearchText(taskName);
		soaWorkListPage.clickSearchButton();
		soaWorkListPage.clickTask(taskName);
	}

	private void enableTaskPasswordPrompt(SOAWorkListPage soaWorkListPage) {
		StepReport.info("Enable task password prompt");
		soaWorkListPage.clickEditTask();
		soaWorkListPage.clickTaskAccess();
		soaWorkListPage.selectSignaturePolicy("Password Required");
		soaWorkListPage.clickSave();
		soaWorkListPage.clickCommentOKButton();
		soaWorkListPage.clickInformationOKButton();
		soaWorkListPage.clickCommitTask();
		soaWorkListPage.clickCommentOKButton();
		soaWorkListPage.clickInformationOKButton();
	}

	protected void verifyPasswordPromptOnReject(String user, String password, String taskName) {
		login(user,password);
		StepReport.info("Login Test successful");

		String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
		String soaWorklistWindow = openWorklistWindow(mainWindow);
		openTaskDetailWindow(mainWindow, soaWorklistWindow, taskName);

		rejectRequestWithPassword(password);

		FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
		logout();
	}

	private void rejectRequestWithPassword(String password) {
		StepReport.info("Verify password prompt when click approve");
		String actionButtonXPath = "//span[text()='Reject']";
		WebElement actionButton = DriverUtil.getElement(By.xpath(actionButtonXPath));
		PageLoadHelper.waitForElementClickable(actionButton);
		actionButton.click();
		DriverUtil.sleep(10000);

		String passwordInputFieldXPath = "//input[@type='password']";
		WebElement passwordInputField = DriverUtil.getElement(By.xpath(passwordInputFieldXPath));
		PageLoadHelper.waitForElementClickable(passwordInputField);
		passwordInputField.sendKeys(password);
        DriverUtil.sleep(5000);

		String okButtonXPath = "//button[text()='OK']";
		WebElement okButton = DriverUtil.getElement(By.xpath(okButtonXPath));
		PageLoadHelper.waitForElementClickable(okButton);
		okButton.click();
		DriverUtil.sleep(15000);
	}

	protected void configureDeadlines(String user, String password, String compositeName, String operation) {
		login(user,password);
		StepReport.info("Login Test successful");

		String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
		openWorklistWindow(mainWindow);

		SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
		soaWorkListPage.isLoaded();
		soaWorkListPage.clickAdminstration();
		soaWorkListPage.clickTaskConfigurationButton();
		soaWorkListPage.typeSearchText(compositeName);
		soaWorkListPage.clickSearchButton();
		soaWorkListPage.clickComposite(compositeName);
		soaWorkListPage.clickEditTask();
		soaWorkListPage.clickDedadlines();
		soaWorkListPage.expand("Expiration Settings");
		if(operation.equalsIgnoreCase("enable")) {
			soaWorkListPage.selectTaskLevelRadioButton();
			soaWorkListPage.typeExpirationTime("5");
			soaWorkListPage.selectEsclateRadioButton();
			soaWorkListPage.typeMaximumEscalationLevels("3");
			soaWorkListPage.selectHighestApprover("CEO");
		}
		if(operation.equalsIgnoreCase("disable")) {
			soaWorkListPage.selectDoNothingRadioButton();
		}
		soaWorkListPage.clickSave();
		soaWorkListPage.clickCommentOKButton();
		soaWorkListPage.clickInformationOKButton();
		soaWorkListPage.clickCommitTask();
		soaWorkListPage.clickCommentOKButton();
		soaWorkListPage.clickInformationOKButton();

		FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
		logout();
	}

	protected void abortTaskFromEM(String user, String password, String compositeName, String taskCreator) {
		loginToEM(user,password);
		StepReport.info("Login Test successful");
		//emHomePage.restoreMenu();
		emHomePage.expand("Farm_FADomain");
		emHomePage.expand("SOA");
		emHomePage.expand("soa-infra (ESS_SOAServer_1)");
		emHomePage.clickDefault();
		emHomePage.searchComposite(compositeName);
		emHomePage.clickComposite(compositeName);
		emHomePage.clickLink("Instances");
		emHomePage.clickButton("Search");
		emHomePage.selectInstance(taskCreator);
		emHomePage.clickAbort();
		emHomePage.clickButton("Abort");
		PageLoadHelper.waitForElementVisible(DriverUtil.getElement(By.xpath("//*[text()='Instances aborted successfully.']")));
		emHomePage.signout();
	}

	protected boolean verifyIfReportPresent(String repName,WorklistNotificationsApprovals worklistNotificationsApprovals) {
		worklistNotificationsApprovals.typeFilter(repName);
		DriverUtil.sleep(5000L);
		worklistNotificationsApprovals.clickSearch();
		return worklistNotificationsApprovals.verifyIfReportPresentOnGrid(repName);
	}

	public void invokeComposite(String compositeName, String payload) {
		loginToEM(faAdminUser,faAdminUserPwd);
		StepReport.info("Invoking composite: "+compositeName);
		emHomePage.expand("Farm_FADomain");
		emHomePage.expand("SOA");
		emHomePage.expand("soa-infra (ESS_SOAServer_1)");
		emHomePage.clickDefault();
		emHomePage.searchComposite(compositeName);
		emHomePage.clickComposite(compositeName);
		emHomePage.clickTest();
		emHomePage.inputPayload(payload);
		emHomePage.clickTestWebService();
		if(!emHomePage.testInvokeCompositeStatus(compositeName))
			throw new TestErrorException("Failed to invoke the instance");
		emHomePage.signout();

	}

	protected boolean taskOperation(String user,String pwd, String reportName,String action) {
		login(user,pwd);
		StepReport.info("Login Test successful");
		homePage.clickTools();
		WorklistNotificationsApprovals worklistNotificationsApprovals=homePage.clickWorklist();
        boolean isTaskAssigned = verifyIfReportPresent(reportName,worklistNotificationsApprovals);
        if(isTaskAssigned==false) {
			//StepReport.assertTrue("Task " + reportName + " assigned to user " + user + "", isTaskAssigned);
			logout();
			return false;
		}
		if(action.equalsIgnoreCase("Approve")){
			worklistNotificationsApprovals.clickApprove(reportName);
		}
		DriverUtil.sleep(5000L);
		worklistNotificationsApprovals.isLoaded();
		logout();
		return true;
	}

	protected void verifyWorklistDisplay(String user, String password, String userFullName) {
		login(user, password);
		StepReport.info("Login Test successful");

		String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
		openWorklistWindow(mainWindow);
		this.loadSOAWorkListPage();

		verifyWorklistTopScreenElement(userFullName);
		verifyWorklistViewIcons();
		verifyWorklistTaskActions();

		FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
		logout();
	}

	private void verifyWorklistTopScreenElement(String userFullName) {
		StepReport.info("Verifying worklist top screen elements");
		// xpath written in this way to see whether elements in the same area
		verifyElementClickable("//table[@id='homePageTemplate:pgl1']//img[@title='Refresh Page']");
		verifyElementClickable("//table[@id='homePageTemplate:pgl1']//a[text()='" + userFullName + "']");
	}

	private void verifyWorklistViewIcons() {
		StepReport.info("Verifying worklist view icons");
		// xpath written in this way to see whether elements in the same area
		verifyElementClickable("//div[@id='homePageTemplate:homePageRegion:0:vldc:pgl2']//img[@title='Add View']");
		verifyElementClickable("//div[@id='homePageTemplate:homePageRegion:0:vldc:pgl2']//img[@title='Edit Inbox Settings']");
		verifyElementClickable("//div[@id='homePageTemplate:homePageRegion:0:vldc:pgl2']//img[@title='Unpin']");
	}

	private void verifyWorklistTaskActions() {
		StepReport.info("Verifying worklist task actions");
		if (!clickFirstTaskInWorklist()) {
			return;
		}
		expandTaskActionDropdownList();
		// xpath written in this way to see whether elements in the same area
		verifyElementClickable("//table[@id='homePageTemplate:homePageRegion:0:tldc:taskActionsSelect::ScrollContent']//td[text()='Approve']");
		verifyElementClickable("//table[@id='homePageTemplate:homePageRegion:0:tldc:taskActionsSelect::ScrollContent']//td[text()='Reject']");
		verifyElementClickable("//table[@id='homePageTemplate:homePageRegion:0:tldc:taskActionsSelect::ScrollContent']//td[contains(text(), 'Reassign')]");
	}

	private boolean clickFirstTaskInWorklist() {
		StepReport.info("Click first task in Worklist");
		String taskListImgXPath = "//img[contains(@src, '/integration/worklistapp/images/qual_clipboard')]";
		List<WebElement> taskListImages = DriverUtil.getElements(By.xpath(taskListImgXPath));
		if (taskListImages.size() > 0) {
			taskListImages.get(0).click();
			DriverUtil.sleep(5000);
			return true;
		} else {
			System.out.println("There is no task listed on worklist page");
			return false;
		}
	}

	private void expandTaskActionDropdownList() {
		StepReport.info("Expand task action dropdown list");
		String actionLinkXPath = "//a[text()='Actions']";
		WebElement actionLink = DriverUtil.getElement(By.xpath(actionLinkXPath));
		PageLoadHelper.waitForElementClickable(actionLink);
		actionLink.click();
		DriverUtil.sleep(5000L);
	}

	private void verifyElementClickable(String xpath) {
		System.out.println("Verifying element is clickable: " + xpath);
		PageLoadHelper.waitForElementClickable(DriverUtil.getElement(By.xpath(xpath)));
	}

	protected void verifyExceptioninEM() {
		loginToEM(faAdminUser,faAdminUserPwd);
		WebDriver driver = FrameworkContext.getInstance().getWebDriver();
		driver.get(""+emURL+"/faces/as/deployment/clusterAppHome?target=%2FFarm_FADomain%2FFADomain%2FESS_SOACluster%2Fworklistapp&type=j2ee_application_cluster");
		emHomePage.isLoaded();
		emHomePage.clickViewLogMessages();
		emHomePage.enterMessage("stringindexoutofboundsexception");
		emHomePage.clickSearch();
		DriverUtil.sleep(10000L);
		emHomePage.isErrorDisplayed();
		emHomePage.signout();

	}

	protected void configureComposite(String user, String password, String compositeName, String operation) {
		login(user,password);
		StepReport.info("Login Test successful");

		String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
		openWorklistWindow(mainWindow);

		SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
		soaWorkListPage.isLoaded();
		soaWorkListPage.clickAdminstration();
		soaWorkListPage.clickTaskConfigurationButton();
		soaWorkListPage.typeSearchText(compositeName);
		soaWorkListPage.clickSearchButton();
		soaWorkListPage.clickComposite(compositeName);
		soaWorkListPage.clickEditTask();

		configureDeadline(soaWorkListPage,"enable","3","4","Select Approver");

		configureNotifications(soaWorkListPage,"enable","2","1");

		configureApprovalLevels(soaWorkListPage,"Expense Report...","ExpenseReportRuleSet","ExpenseReportManagerApprovalRule","2");

		soaWorkListPage.clickSave();
		soaWorkListPage.clickCommentOKButton();
		soaWorkListPage.clickInformationOKButton();
		soaWorkListPage.clickCommitTask();
		soaWorkListPage.clickCommentOKButton();
		soaWorkListPage.clickInformationOKButton();

		FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
		logout();
	}

	protected void configureDeadline(SOAWorkListPage soaWorkListPage, String operation, String expirationTime, String escalationLevels, String highestApprover){
		soaWorkListPage.clickDedadlines();
		soaWorkListPage.expand("Expiration Settings");
		if(operation.equalsIgnoreCase("enable")) {
			soaWorkListPage.selectTaskLevelRadioButton();
			soaWorkListPage.typeExpirationTime(expirationTime);
			soaWorkListPage.selectEsclateRadioButton();
			soaWorkListPage.typeMaximumEscalationLevels(escalationLevels);
			soaWorkListPage.selectHighestApprover(highestApprover);
		}
		if(operation.equalsIgnoreCase("disable")) {
			soaWorkListPage.selectDoNothingRadioButton();
		}
	}

	protected void configureNotifications(SOAWorkListPage soaWorkListPage, String operation, String repeatNumber, String reminderFrequency){
		soaWorkListPage.clickNotifications();
		if(operation.equalsIgnoreCase("enable")) {
			soaWorkListPage.clickEnableReminder();
			soaWorkListPage.selectRepeat(repeatNumber);
			soaWorkListPage.typeReminderFrequency(reminderFrequency);
		}
		if(operation.equalsIgnoreCase("disable")) {
			soaWorkListPage.clickEnableReminder();
		}
	}

	protected void configureApprovalLevels(SOAWorkListPage soaWorkListPage,String stageName, String ruleSet, String ruleName, String levels){
		soaWorkListPage.clickTaskAssignee();
		soaWorkListPage.clickGoToRule(stageName);
		soaWorkListPage.clickRuleSet(ruleSet);
		soaWorkListPage.expandRule(ruleName);
		soaWorkListPage.enterLevels(levels);
	}

}
