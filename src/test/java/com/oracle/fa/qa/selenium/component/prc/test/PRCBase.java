package com.oracle.fa.qa.selenium.component.prc.test;

import java.util.Set;

import com.oracle.fa.qa.selenium.component.common.page.*;
import com.oracle.fa.qa.selenium.component.fin.page.CashAdvanceReport;
import com.oracle.fa.qa.selenium.component.fin.page.ManageInvoiceOptions;
import com.oracle.fa.qa.selenium.component.prc.page.*;
import com.oracle.fa.qa.selenium.component.prc.page.AddComment;
import com.oracle.fa.qa.selenium.component.prc.page.ReassignTask;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.oracle.fa.qa.selenium.component.common.test.TestBase;
import com.oracle.fa.qa.selenium.component.fin.page.RequestMoreInformation;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.testng.Assert;

public class PRCBase extends TestBase{

	protected void setupPOApproval() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		//Navigator navigator = homePage.clickNavigator();
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManagePurchasingDocumentApprovals managePurchasingDocumentApprovals=setupMaintenance.clickManagePurchasingDocumentApprovals();
		managePurchasingDocumentApprovals.isLoaded();
		managePurchasingDocumentApprovals.clickTermsApprovalSerial();
		managePurchasingDocumentApprovals.clickEnable();
		EditApprovalRules editApprovalRules=managePurchasingDocumentApprovals.clickEditRules();
		editApprovalRules.isLoaded();
		if(editApprovalRules.isPORuleConfigured()){
			isPORuleConfigured = true;
			return;
		}
		editApprovalRules.clickCreate();
		editApprovalRules.typeRuleName("Purchase Order Approval Rule");
		editApprovalRules.clickRuleOK();
        editApprovalRules.addPOCondition("600000","700000");
		editApprovalRules.addAction("Supervisory hierarchy", "Buyer","1","Givan,Clinton");
		editApprovalRules.clickSave();
		editApprovalRules.clickDeploy();
		editApprovalRules.closeWarningPopup();
		editApprovalRules.checkForError();
        isPORuleConfigured = true;
        logout();

	}

	protected void setupConfigurePreapprovalFYIRule() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		//Navigator navigator = homePage.clickNavigator();
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManagePurchasingDocumentApprovals managePurchasingDocumentApprovals = setupMaintenance.clickManagePurchasingDocumentApprovals();
		managePurchasingDocumentApprovals.clickRule("Preapproval FYI");
		managePurchasingDocumentApprovals.clickEnable();
		EditApprovalRules editApprovalRules=managePurchasingDocumentApprovals.clickEditRules();
		editApprovalRules.isLoaded();

		String ruleName = "FYIBuyer";
		if(!editApprovalRules.isRuleExist(ruleName)) {

			editApprovalRules.clickCreate();

			editApprovalRules.typeRuleName(ruleName);
			editApprovalRules.clickRuleAlwaysApplies();
            editApprovalRules.clickRuleOK();

			editApprovalRules.clickAddAction();
			editApprovalRules.selectActionType("Information only");
			editApprovalRules.selectRouteType("Single approver");
			editApprovalRules.selectUserType("Buyer");
			editApprovalRules.clickActionOK();

			editApprovalRules.clickSave();
			editApprovalRules.clickDeploy();
			editApprovalRules.closeWarningPopup();
			editApprovalRules.checkForError();
		}
		logout();

	}

	protected void setupRequisitionApproval() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		//Navigator navigator = homePage.clickNavigator();
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
        ManageRequisitionApprovals manageRequisitionApprovals=setupMaintenance.clickManageRequisitionApprovals();
        manageRequisitionApprovals.isLoaded();
        String[] disableParticipants={"Header Hierarchy","Header First Responder Wins","Header Hierarchy 2","Header Hierarchy 3"};
		manageRequisitionApprovals.disableParticipants(disableParticipants);
        manageRequisitionApprovals.clickRule("Header Consensus");
        manageRequisitionApprovals.clickEnable();
        EditApprovalRules editApprovalRules=manageRequisitionApprovals.clickEditRules();
        editApprovalRules.isLoaded();
        if(editApprovalRules.isRuleExist("Requisition Approval Rule")){
            isRequisitionRuleConfigured = true;
            return;
		}
        editApprovalRules.clickCreate();
        editApprovalRules.typeRuleName("Requisition Approval Rule");
        editApprovalRules.clickRuleOK();
        editApprovalRules.addCondition("Approval task attribute","Requisition Amount","Between","44000","46000", false);
        editApprovalRules.addAction("Supervisory hierarchy", "Preparer","1","Mervis,Tania");
        editApprovalRules.clickSave();
        editApprovalRules.clickDeploy();
        editApprovalRules.closeWarningPopup();
		editApprovalRules.checkForError();
        isRequisitionRuleConfigured = true;
        logout();

	}


	protected void setupRequisitionLenovoApproval() {
		this.setupRequisitionApproval("Requisition Approval Lenovo Rule", "600000", "620000");
		isRequisitionLenovoRuleConfigured = true;
	}

	protected void setupRequisitionApproval(String ruleName, String minValue, String maxValue) {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		//Navigator navigator = homePage.clickNavigator();
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManageRequisitionApprovals manageRequisitionApprovals=setupMaintenance.clickManageRequisitionApprovals();
		manageRequisitionApprovals.isLoaded();
		String[] disableParticipants={"Header Hierarchy","Header First Responder Wins","Header Hierarchy 2","Header Hierarchy 3"};
		manageRequisitionApprovals.disableParticipants(disableParticipants);
        manageRequisitionApprovals.clickRule("Header Consensus");
		manageRequisitionApprovals.clickEnable();
		EditApprovalRules editApprovalRules=manageRequisitionApprovals.clickEditRules();
		editApprovalRules.isLoaded();
		if(editApprovalRules.isRuleExist(ruleName)){
			//isRequisitionRuleConfigured = true;
			logout();
			return;
		}
		editApprovalRules.clickCreate();
		editApprovalRules.typeRuleName(ruleName);
		editApprovalRules.clickRuleOK();
        editApprovalRules.addCondition("Approval task attribute","Requisition Amount","Between",minValue,maxValue, false);
		editApprovalRules.addAction("Supervisory hierarchy", "Preparer","1","Mervis,Tania");
		editApprovalRules.clickSave();
		editApprovalRules.clickDeploy();
		editApprovalRules.closeWarningPopup();
		editApprovalRules.checkForError();
		//isRequisitionRuleConfigured = true;
		logout();

	}

	protected String createRequisitionLenovo(String user, String password) {
		//Login as User
		String requisitionNumber="";
		login(user,password);
		StepReport.info("Login Test successful");
		homePage.clickProcurment();
		Requisitions requisitions=homePage.clickPurchaseRequisition();
		requisitions.updateRequisitionPreferences();
		EnterRequisitionLine enterRL=requisitions.gotoEnterRequisitionLine();
		enterRL.isLoaded();
		enterRL.enterRequisitionWithLenovo();
		EditRequisition editRequisition=enterRL.reviewItem();
		editRequisition.isLoaded();
		requisitionNumber=editRequisition.getRequisitionNumber();
		System.out.println("Requisition Number : "+requisitionNumber);
		Confirmation confirmation=editRequisition.clickSubmit();
		confirmation.clickOk();
		logout();
		return requisitionNumber;
	}

	protected String createRequisitionCM76840(String user, String password) {
		//Login as User
		String requisitionNumber="";
		login(user,password);
		StepReport.info("Login Test successful");
		homePage.clickProcurment();
		Requisitions requisitions=homePage.clickPurchaseRequisition();
		requisitions.isLoaded();
		requisitions.updateRequisitionPreferences("PORAPV02706","01-510-7530-0000-000");
		requisitions.searchItem("CM76840");
		requisitions.goListView();
		requisitions.typeItemQtyAndAddToCartInListView("CM76840","41");
		EditRequisition editRequisition=requisitions.reviewItem();
		editRequisition.isLoaded();
		requisitionNumber=editRequisition.getRequisitionNumber();
		System.out.println("Requisition Number : "+requisitionNumber);
		ManageApprovals manageApprovals = editRequisition.clickManageApprovals();
		manageApprovals.isLoaded();
		Confirmation confirmation=manageApprovals.clickSubmit();
		confirmation.clickOk();
		logout();
		return requisitionNumber;
	}

	protected void verifyRequisitionCM76840(String user, String password, String requisitionNumber) {
		//Login as User
		login(user,password);
		StepReport.info("Login Test successful");
		homePage.clickProcurment();
		Requisitions requisitions=homePage.clickPurchaseRequisition();
		ManageRequisitions manageRequisitions = requisitions.clickManageRequisitions();
		manageRequisitions.searchRequisition(requisitionNumber);
		RequisitionPage requisitionPage = manageRequisitions.clickRequisition(requisitionNumber);
		logout();
	}
	
	protected String createRequisition(String user, String password, boolean addReportAttachment) {
		//Login as User1
	    String requisitionNumber="";
    	login(user,password);
    	StepReport.info("Login Test successful"); 
    	homePage.clickProcurment();
    	Requisitions requisitions=homePage.clickPurchaseRequisition();
    	requisitions.searchItem("Sheet Metal");
    	requisitions.typeItemQtyAndAddToCart("Sheet Metal","44400");
    	EditRequisition editRequisition=requisitions.reviewItem();
    	requisitionNumber=editRequisition.getRequisitionNumber();
    	StepReport.info("Requisition Number : ",requisitionNumber);
    	if(addReportAttachment) {
            addAttachment(attachmentSampleFileName,attachmentSampleFileName,attachmentSampleFileName,null,null,null,null,editRequisition,"5");	
        }
    	Confirmation confirmation=editRequisition.clickSubmit();
    	confirmation.clickOk();
		logout();
    	return requisitionNumber;
    }
	
	protected void requisitionReportOperations(String user,String pwd, String requisitionReportNumber,
            boolean checkBellNotification, String operation, boolean checkComment,boolean checkAttachment) {
		login(user,pwd);
		StepReport.info("Login Test successful"); 
		if(checkBellNotification) {
		checkBellNotification(requisitionReportNumber);
		}
		homePage.clickTools();
		WorklistNotificationsApprovals worklistNotificationsApprovals=homePage.clickWorklist();
        DriverUtil.sleep(2000L);
        if(operation.equalsIgnoreCase("provideInfo")){
            worklistNotificationsApprovals.selectStatus("Information Requested");
            DriverUtil.sleep(3000L);
        }
		searchReport(requisitionReportNumber,worklistNotificationsApprovals);
		worklistNotificationsApprovals.clickOnExpenseReport(requisitionReportNumber);
		DriverUtil.sleep(10000L);
		WebDriver driver=FrameworkContext.getInstance().getWebDriver();
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
		System.out.println(driver.getTitle());
		
		RequisitionReport requisitionReport = PageFactory.getPage(RequisitionReport.class);
		requisitionReport.isLoaded();
		if(checkComment) {
			String commentTxtXpath="//*[text()='requestInfoProvided']";
			WebElement commentElem=DriverUtil.getElement(By.xpath(commentTxtXpath));
			StepReport.assertTrue("Report Comment Displayed ",commentElem.isDisplayed());
		}
		if(checkAttachment) {
			DriverUtil.sleep(3000L);
			String attachmentTxtXpath="//*[contains(text(),'"+attachmentSampleFileName+"')]";
			WebElement attachmentTxtElem=DriverUtil.getElement(By.xpath(attachmentTxtXpath));
			StepReport.assertTrue("Report Attachment Displayed ",attachmentTxtElem.isDisplayed());
		}
		if(operation.equals("approve")) {
			//requisitionReport.clickApprove();
			ApprovePO approvePO=requisitionReport.clickApproveReq();
			approvePO.typeComments("Approved by :"+user);
			approvePO.clickSubmit();
		}
		if(operation.equals("reject")) {
			ApprovePO approvePO=requisitionReport.clickRejectReq();
			approvePO.typeComments("Rejected by :"+user);
			approvePO.clickSubmit();
		}
		
		if(operation.equals("reqInfo")) {
			RequestMoreInformation requestMoreInformation=requisitionReport.clickRequestInfo();
			requestMoreInformation.typeComments("requestInfo1 provided by: "+user);
			requestMoreInformation.clickSubmit();
		}
		
		if(operation.equals("provideInfo")) {
			AddComment addComment=requisitionReport.provideInfoComments();
			addComment.typeComment("requestInfoProvided");
			addComment.clickSave();
            SubmitPO submitPO=requisitionReport.clickSubmitInfo();
            submitPO.typeComments(user +" is submitting after providing info");
            submitPO.clickSubmit();
		}
		driver.switchTo().window(currentWindowHandle);
		logout();
	}
	
	protected String createPO(String user, String password, boolean addReportAttachment) {
		//Login as User1
	    String poNumber="";
    	login(user,password);
    	StepReport.info("Login Test successful"); 
    	homePage.clickProcurment();
    	POOverView poOverView=homePage.ClickPurchaseOrder();
    	poOverView.clickTasks();
    	CreateOrder createOrder=poOverView.clickCreatePO();
    	createOrder.typeSupplier("CV_SuppA01");
    	createOrder.typeSupplierSite("CVSuppA01Site01");
    	EditPODocument editPODocument=createOrder.clickCreate();
    	editPODocument.clickAddItem();
    	editPODocument.addItemLine("Laptop Battery", "1", "650000");
    	editPODocument.typeReqDate("11/11/2025");
 	    if(addReportAttachment) {
 	    editPODocument.clickNotesAttachment();
        addAttachment(attachmentSampleFileName,attachmentSampleFileName,attachmentSampleFileName,null,null,null,editPODocument,null,"4");	
        }
    	poNumber=editPODocument.getPONumber();
    	System.out.println("PO Number : "+poNumber);
    	POConfirmation poConfirmation=editPODocument.clickSubmit();
    	poConfirmation.clickOk();
    	logout();
    	return poNumber;
    }
	
	protected void poReportOperations(String user,String pwd, String requisitionReportNumber,
            boolean checkBellNotification, String operation, boolean checkComment,boolean checkAttachment) {
		login(user,pwd);
		StepReport.info("Login Test successful"); 
		if(checkBellNotification) {
		checkBellNotification(requisitionReportNumber);
		}
		homePage.clickTools();
		WorklistNotificationsApprovals worklistNotificationsApprovals=homePage.clickWorklist();
        DriverUtil.sleep(2000L);
		if(operation.equalsIgnoreCase("provideInfo")){
			worklistNotificationsApprovals.selectStatus("Information Requested");
			DriverUtil.sleep(3000L);
		}
		searchReport(requisitionReportNumber,worklistNotificationsApprovals);
		worklistNotificationsApprovals.clickOnExpenseReport(requisitionReportNumber);
		DriverUtil.sleep(10000L);
		WebDriver driver=FrameworkContext.getInstance().getWebDriver();
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
		System.out.println(driver.getTitle());
		
		POReport poReport = PageFactory.getPage(POReport.class);
		poReport.isLoaded();
		if(checkComment) {
			String commentTxtXpath="//*[text()='requestInfoProvided']";
			WebElement commentElem=DriverUtil.getElement(By.xpath(commentTxtXpath));
			StepReport.assertTrue("Report Comment Displayed ",commentElem.isDisplayed());
		}
		if(checkAttachment) {
			DriverUtil.sleep(3000L);
			String attachmentTxtXpath="//*[contains(text(),'"+attachmentSampleFileName+"')]";
			WebElement attachmentTxtElem=DriverUtil.getElement(By.xpath(attachmentTxtXpath));
			StepReport.assertTrue("Report Attachment Displayed ",attachmentTxtElem.isDisplayed());
		}
		if(operation.equals("approve")) {
			ApprovePO approvePO=poReport.clickApprovePO();
			approvePO.typeComments("Approved by :"+user);
			approvePO.clickSubmit();
		}
		if(operation.equals("reject")) {
			ApprovePO approvePO=poReport.clickRejectPO();
			approvePO.typeComments("Reject by :"+user);
			approvePO.clickSubmit();

		}
		if(operation.equals("dismiss")) {
			poReport.doAction("Dismiss");
		}
		
		if(operation.equals("reqInfo")) {
			RequestMoreInformation requestMoreInformation=poReport.clickRequestInfo();
			requestMoreInformation.typeComments("requestInfo1 provided by: "+user);
			requestMoreInformation.clickSubmit();
		}
		
		if(operation.equals("provideInfo")) {
			AddComment addComment=poReport.provideInfoComments();
			addComment.typeComment("requestInfoProvided");
			addComment.clickSave();
			SubmitPO submitPO=poReport.clickSubmitInfo();
			submitPO.typeComments(user +" is submitting after providing info");
			submitPO.clickSubmit();
		}
		driver.switchTo().window(currentWindowHandle);
		logout();
	}

	protected void createUserDefinedRequisitionApprovalAttributes() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManageRequisitionApprovals manageRequisitionApprovals = setupMaintenance.clickManageRequisitionApprovals();
		manageRequisitionApprovals.isLoaded();
		ManageUserDefAttr manageUserDefAttr = manageRequisitionApprovals.clickManageUserDefinedAttrButton();
		manageUserDefAttr.isLoaded();

		String categoryAttrName = "Category Attribute";
		if (!manageUserDefAttr.isUserDefinedAttrCreated(categoryAttrName)) {
			manageUserDefAttr.clickCreateIcon();
			manageUserDefAttr.enterAttributeName(categoryAttrName);
			manageUserDefAttr.selectAttributeType("Summation");
			manageUserDefAttr.selectAttributeAttr("Approval Amount");
			manageUserDefAttr.inputAttributeFilter("Category Name");
			manageUserDefAttr.inputRollsUpToForCategory("Office Technology");
			manageUserDefAttr.clickOKButton();

			if (!manageUserDefAttr.isUserDefinedAttrCreated(categoryAttrName)) {
				throw new RuntimeException("Failed to create attribute " + categoryAttrName);
			}
		}

		String costCenterAttrName = "Cost Center Attribute";
		if (!manageUserDefAttr.isUserDefinedAttrCreated(costCenterAttrName)) {
			manageUserDefAttr.clickCreateIcon();
			manageUserDefAttr.enterAttributeName(costCenterAttrName);
			manageUserDefAttr.selectAttributeType("Summation");
			manageUserDefAttr.selectAttributeAttr("Approval Amount");
			manageUserDefAttr.inputAttributeFilter("Cost Center");
			manageUserDefAttr.fillinRollsUpToForCostCenter("100");
			manageUserDefAttr.clickOKButton();

			if (!manageUserDefAttr.isUserDefinedAttrCreated(costCenterAttrName)) {
				throw new RuntimeException("Failed to create attribute " + costCenterAttrName);
			}
		}

		logout();
	}

	protected void createApprovalGroup() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");

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
				soaWorkListPage.clickAdminstration();
				soaWorkListPage.clickApprovalGroupButton();
				soaWorkListPage.clickCreateApprovalGroupIcon();
                if(!soaWorkListPage.isGroupCreated()) {
                    soaWorkListPage.inputApprovalGroupName("US Office Technology Approval Group");
                    soaWorkListPage.addMember("cvrqst04");
                    soaWorkListPage.addMember("cvrqst05");
                    soaWorkListPage.clickSaveButton();

                    DriverUtil.sleep(10000);
                }
			}
		}

		driver.switchTo().window(mainWindow);

		logout();
	}

	protected void createSharedView(String viewName, String sharedUser) {
		login(prcReqUser1,prcBuyerUser1Pwd);
		StepReport.info("Login Test successful");
		WebDriver driver= FrameworkContext.getInstance().getWebDriver();
		String mainWindow= driver.getWindowHandle();

		SOAWorkListPage workListPage = clickToOpenBPMWorklistWindow();

		workListPage.createSharedView(viewName, sharedUser);
		driver.switchTo().window(mainWindow);

		logout();
	}

	protected void verifySharedView(String viewName, String sharedUser, String sharedPassword) {
		login(sharedUser,sharedPassword);
		StepReport.info("Login Test successful");
		WebDriver driver= FrameworkContext.getInstance().getWebDriver();
		String mainWindow= driver.getWindowHandle();

		SOAWorkListPage workListPage = clickToOpenBPMWorklistWindow();

		Assert.assertTrue(workListPage.isViewAvailable(viewName));
		driver.switchTo().window(mainWindow);

		logout();
	}

	/*protected SOAWorkListPage clickToOpenBPMWorklistWindow() {
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
	}*/

	protected void cancelRequisitionApproveTest(String reqNumber){
		login(prcReqUser1,prcReqUser1Pwd);
		homePage.clickProcurment();
		Requisitions req = homePage.clickPurchaseRequisition();
		ManageRequisitions manageRequisitions= req.clickManageRequisitions();
		manageRequisitions.isLoaded();
		RequisitionPage requisitionPage=manageRequisitions.clickRequisition(reqNumber);
		requisitionPage.clickCancelRequistion();
		logout();

	}

	protected void testCancelPO(String poNumber){
		login(prcBuyerUser1,prcBuyerUser1Pwd);
		homePage.clickProcurment();
		POOverView purchaseOrder = homePage.ClickPurchaseOrder();
		purchaseOrder.clickTasks();
		ManageOrder manageOrder = purchaseOrder.clickManageOrder();
		manageOrder.searchPurchaseOrder(poNumber);
		StepReport.info("PO Number",poNumber);
		manageOrder.cancelPurchaseOrder();
		manageOrder.clickOKWarning();
		manageOrder.typeCancelReason();
		manageOrder.clickOnConfirmation();
		Assert.assertTrue(manageOrder.verifyPOStatus("Canceled"),"Verify if the approval status is Canceled");
		logout();
	}

    protected void setupUserDefinedApprovalRule(String attributeName) {
        login(fusionUser,fusionUserPwd);
        StepReport.info("Login Test successful");
        SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
        setupMaintenance.selectSetup("Procurement");
        setupMaintenance.clickApprovalManagement();
        ManageRequisitionApprovals manageRequisitionApprovals=setupMaintenance.clickManageRequisitionApprovals();
        manageRequisitionApprovals.isLoaded();
		String[] disableParticipants={"Header Hierarchy","Header First Responder Wins","Header Hierarchy 2","Header Hierarchy 3"};
		manageRequisitionApprovals.disableParticipants(disableParticipants);
        manageRequisitionApprovals.clickRule("Header Consensus");
        manageRequisitionApprovals.clickEnable();
		ManageUserDefAttr manageUserDefinedAttributes=manageRequisitionApprovals.clickManageUserDefinedAttrButton();
        if (!manageUserDefinedAttributes.isUserDefinedAttrCreated(attributeName)) {
            manageUserDefinedAttributes.clickCreateIcon();
            manageUserDefinedAttributes.enterAttributeName(attributeName);
            manageUserDefinedAttributes.selectAttributeType("Summation");
            manageUserDefinedAttributes.inputAttributeFilter("Category Name");
            manageUserDefinedAttributes.fillinRollsUpToForCostCenter("Office Technology ");
            manageUserDefinedAttributes.clickOKButton();
        }
		if (!manageUserDefinedAttributes.isUserDefinedAttrCreated(attributeName)) {
			throw new RuntimeException("Failed to create attribute:"+attributeName);
		}
        manageRequisitionApprovals = manageUserDefinedAttributes.clickDoneButton();
        manageRequisitionApprovals.isLoaded();
        EditApprovalRules editApprovalRules=manageRequisitionApprovals.clickEditRules();
        editApprovalRules.isLoaded();
        /*if(editApprovalRules.isUserDefinedRuleConfigured(attributeName)){
            isUserDefinedRuleConfigured = true;
            return;
        }*/

        //boolean[] ruleConfig = editApprovalRules.isUserDefinedRuleConfigured(attributeName);
		/*if (ruleConfig[0] & ruleConfig[1]) {
			System.out.println("Approval Rules based on user-defined Category attribute are already created");
			return;
		}*/
		if(!editApprovalRules.isRuleExist("AUI15_Rule1")) {
			editApprovalRules.clickCreate();
			editApprovalRules.typeRuleName("AUI15_Rule1");
			editApprovalRules.clickRuleAlwaysApplies();
			editApprovalRules.clickRuleOK();
			editApprovalRules.addAutoApproval("Approved");
		}

		if(!editApprovalRules.isRuleExist("AUI15_Rule2")) {
			editApprovalRules.clickCreate();
			editApprovalRules.typeRuleName("AUI15_Rule2");
			editApprovalRules.clickRuleOK();
			editApprovalRules.addConditionUserDefinedAttribute(attributeName, "Greater than", "1000000", true);
			editApprovalRules.addConditionUserDefinedAttribute(attributeName, "Less than or equal to", "5000000", false);
			editApprovalRules.addAutoApproval("Approved");
		}
		if(!editApprovalRules.isRuleExist("AUI15_Rule3")) {
			editApprovalRules.clickCreate();
			editApprovalRules.typeRuleName("AUI15_Rule3");
			editApprovalRules.clickRuleOK();
			editApprovalRules.addConditionUserDefinedAttribute(attributeName, "Greater than", "5000000", false);
			editApprovalRules.addAction("Approval group", "US Office Technology Approval Group", true);
		}
        editApprovalRules.clickSave();
        editApprovalRules.clickDeploy();
        editApprovalRules.closeWarningPopup();
		editApprovalRules.checkForError();
        isUserDefinedRuleConfigured = true;
        logout();

    }

	protected ManageRequisitionApprovals createUserDefinedAttribute(String attributeName) {
		login(fusionUser, fusionUserPwd);
		StepReport.info("Login Test successful");
		SetupMaintenance setupMaintenance = homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManageRequisitionApprovals manageRequisitionApprovals = setupMaintenance.clickManageRequisitionApprovals();
		manageRequisitionApprovals.isLoaded();
		String[] disableParticipants={"Header Hierarchy","Header First Responder Wins","Header Hierarchy 2","Header Hierarchy 3"};
		manageRequisitionApprovals.disableParticipants(disableParticipants);
		manageRequisitionApprovals.clickRule("Header Consensus");
		manageRequisitionApprovals.clickEnable();
		ManageUserDefAttr manageUserDefinedAttributes = manageRequisitionApprovals.clickManageUserDefinedAttrButton();
		if (!manageUserDefinedAttributes.isUserDefinedAttrCreated(attributeName)) {
			manageUserDefinedAttributes.clickCreateIcon();
			manageUserDefinedAttributes.enterAttributeName(attributeName);
			manageUserDefinedAttributes.selectAttributeType("Summation");
			manageUserDefinedAttributes.inputAttributeFilter("Cost Center");
			manageUserDefinedAttributes.fillinRollsUpToForCostCenter("100");
			manageUserDefinedAttributes.clickOKButton();
		}
		if (!manageUserDefinedAttributes.isUserDefinedAttrCreated(attributeName)) {
			throw new RuntimeException("Failed to create attribute:" + attributeName);
		}
		manageRequisitionApprovals = manageUserDefinedAttributes.clickDoneButton();
		manageRequisitionApprovals.isLoaded();

		return manageRequisitionApprovals;
	}

	protected void createRuleByCostCenterAttribute(ManageRequisitionApprovals manageRequisitionApprovals) {

		String attributeName = "Cost Center Attribute";
		String rule1 = "CostCenter_Rule3";
		String rule2 = "CostCenter_Rule4";

		EditApprovalRules editApprovalRules=manageRequisitionApprovals.clickEditRules();
		editApprovalRules.isLoaded();
		if(editApprovalRules.isRuleExist(rule1)){
			editApprovalRules.deleteRule(rule1);
		}
		if(editApprovalRules.isRuleExist(rule2)){
			editApprovalRules.deleteRule(rule2);
		}

		editApprovalRules.clickCreate();
		editApprovalRules.typeRuleName(rule1);
		//editApprovalRules.clickRuleAlwaysApplies();
		editApprovalRules.clickRuleOK();
		editApprovalRules.addConditionUserDefinedAttribute(attributeName,"Greater than","1000000", true);
		editApprovalRules.addConditionUserDefinedAttribute(attributeName,"Less than or equal to","5000000", false);
		editApprovalRules.addAutoApproval("Approved");

		editApprovalRules.clickCreate();
		editApprovalRules.typeRuleName(rule2);
		editApprovalRules.clickRuleOK();
		editApprovalRules.addConditionUserDefinedAttribute(attributeName,"Greater than","5000000", false);
		editApprovalRules.addAction("Single approver", "Worker","Lawalin,Natalie");

		editApprovalRules.clickSave();
		editApprovalRules.clickDeploy();
		editApprovalRules.closeWarningPopup();
		editApprovalRules.checkForError();
		logout();

	}

    protected void updateRequisitionPreferences(String user, String password) {
        //Login as User
        String requisitionNumber="";
        login(user,password);
        StepReport.info("Login Test successful");
        homePage.clickProcurment();
        Requisitions requisitions=homePage.clickPurchaseRequisition();
        requisitions.updateRequisitionPreferences("nickName_Test","01-510-7530-0000-000");
        logout();
    }

    protected String createRequisitionThreeLines(String user, String password) {
        //Login as User1
        String requisitionNumber="";
        login(user,password);
        StepReport.info("Login Test successful");
        homePage.clickProcurment();
        Requisitions requisitions=homePage.clickPurchaseRequisition();
        CreateNoncatalogRequest createNoncatalogRequest=requisitions.gotoRequestNonCatalogItem();
		requisitions = createNoncatalogRequest.createNoncatalogRequestLine("Laptop Accessory","Laptop Accessory","3,000","Each","1,000");
		createNoncatalogRequest=requisitions.gotoRequestNonCatalogItem();
		requisitions = createNoncatalogRequest.createNoncatalogRequestLine("Laptop Accessory","Laptop Accessory","2,500","Each","1,000");
		createNoncatalogRequest=requisitions.gotoRequestNonCatalogItem();
		requisitions = createNoncatalogRequest.createNoncatalogRequestLine("Laser Printer","Laser Printer","1,000","Each","1,000");
        requisitions.isLoaded();
        EditRequisition editRequisition = requisitions.reviewItem();
        requisitionNumber=editRequisition.getRequisitionNumber();
        System.out.println("Requisition Number : "+requisitionNumber);
        Confirmation confirmation=editRequisition.clickSubmit();
        confirmation.clickOk();
        logout();
        return requisitionNumber;
    }

    protected String createRequisitionTwoLines(String user, String password) {
        //Login as User1
        String requisitionNumber="";
        login(user,password);
        StepReport.info("Login Test successful");
        homePage.clickProcurment();
        Requisitions requisitions=homePage.clickPurchaseRequisition();
        CreateNoncatalogRequest createNoncatalogRequest=requisitions.gotoRequestNonCatalogItem();
        requisitions = createNoncatalogRequest.createNoncatalogRequestLine("Laptop Accessory","Laptop Accessory","3,000","Each","1,000");
        createNoncatalogRequest=requisitions.gotoRequestNonCatalogItem();
        requisitions = createNoncatalogRequest.createNoncatalogRequestLine("Miscellaneous_1","Miscellaneous_1","2,500","Each","1,000");
        requisitions.isLoaded();
        EditRequisition editRequisition = requisitions.reviewItem();
        requisitionNumber=editRequisition.getRequisitionNumber();
        System.out.println("Requisition Number : "+requisitionNumber);
        Confirmation confirmation=editRequisition.clickSubmit();
        confirmation.clickOk();
        logout();
        return requisitionNumber;
    }

    protected void verifyRequisitionStatus(String user, String password, String requisitionNumber, String expectedStatus) {
        //Login as User
        login(user,password);
        StepReport.info("Login Test successful");
        homePage.clickProcurment();
        Requisitions requisitions=homePage.clickPurchaseRequisition();
        ManageRequisitions manageRequisitions = requisitions.clickManageRequisitions();
        manageRequisitions.searchRequisition(requisitionNumber);
        Assert.assertTrue(manageRequisitions.verifyApprovalStatus(requisitionNumber,expectedStatus),"Approval Status is not"+expectedStatus);
        logout();
    }

    protected void unSetupUserDefinedApprovalRule(String attributeName) {
        login(fusionUser,fusionUserPwd);
        StepReport.info("Login Test successful");
        SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
        setupMaintenance.selectSetup("Procurement");
        setupMaintenance.clickApprovalManagement();
        ManageRequisitionApprovals manageRequisitionApprovals=setupMaintenance.clickManageRequisitionApprovals();
        manageRequisitionApprovals.isLoaded();
        manageRequisitionApprovals.clickRule("Header Consensus");
        EditApprovalRules editApprovalRules=manageRequisitionApprovals.clickEditRules();
        editApprovalRules.isLoaded();
        editApprovalRules.deleteRule("AUI15_Rule1");
        editApprovalRules.deleteRule("AUI15_Rule2");
        editApprovalRules.deleteRule("AUI15_Rule3");

        editApprovalRules.clickSave();
        editApprovalRules.clickDeploy();
        editApprovalRules.closeWarningPopup();
		editApprovalRules.checkForError();
		isUserDefinedRuleConfigured = false;

        ManageUserDefAttr manageUserDefinedAttributes=manageRequisitionApprovals.clickManageUserDefinedAttrButton();
        if (manageUserDefinedAttributes.isUserDefinedAttrCreated(attributeName)) {
            manageUserDefinedAttributes.deleteUserDefinedAttribute(attributeName);
        }
        logout();

    }

	protected void switchFromPRCUItoEditInDTRT() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManagePurchasingDocumentApprovals managePurchasingDocumentApprovals=setupMaintenance.clickManagePurchasingDocumentApprovals();
		managePurchasingDocumentApprovals.isLoaded();
		managePurchasingDocumentApprovals.clickEditRulesInBPM();
		logout();
	}

	protected String triggerPRCRuleESSJob() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManagePurchasingDocumentApprovals managePurchasingDocumentApprovals=setupMaintenance.clickManagePurchasingDocumentApprovals();
		String processID = managePurchasingDocumentApprovals.getProcessID();
		managePurchasingDocumentApprovals.clickConfirmationOK();
		logout();
		return processID;
	}

	protected void setupRequisitionApprovalForTriggerVacationRule() {
		login(fusionUser,fusionUserPwd);
		StepReport.info("Login Test successful");
		SetupMaintenance setupMaintenance=homePage.clickSetupMaintenance();
		setupMaintenance.selectSetup("Procurement");
		setupMaintenance.clickApprovalManagement();
		ManageRequisitionApprovals manageRequisitionApprovals=setupMaintenance.clickManageRequisitionApprovals();
		manageRequisitionApprovals.isLoaded();
		String[] disableParticipants={"Header Hierarchy","Header First Responder Wins","Header Hierarchy 2","Header Hierarchy 3"};
		manageRequisitionApprovals.disableParticipants(disableParticipants);
		manageRequisitionApprovals.clickRule("Header Consensus");
		manageRequisitionApprovals.clickEnable();
		EditApprovalRules editApprovalRules=manageRequisitionApprovals.clickEditRules();
		editApprovalRules.isLoaded();

		String ruleName = "Rule_to_Test_Vacation";
		if(editApprovalRules.isRequisitionRuleConfigured(ruleName)){
			return;
		}
		editApprovalRules.clickCreate();
		editApprovalRules.typeRuleName(ruleName);
		editApprovalRules.clickRuleOK();
		editApprovalRules.addCondition("Approval task attribute","Requisition Amount","Between","44000","46000", false);
		editApprovalRules.addAction("Supervisory hierarchy", "Preparer","4","Mervis,Tania");
		editApprovalRules.clickSave();
		editApprovalRules.clickDeploy();
		editApprovalRules.closeWarningPopup();
		logout();
	}

	protected void setupVacationDelegationRule() {
		login(prcReqUser3,prcReqUser3Pwd);
		StepReport.info("Login Test successful");

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
				soaWorkListPage.clickPreferences();

				soaWorkListPage.clickVacationPeriod();
				soaWorkListPage.configureVacationRuleToDelegate("cvrqst10");

				DriverUtil.sleep(5000);
			}
		}

		driver.switchTo().window(mainWindow);

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

		POReport poReport = PageFactory.getPage(POReport.class);
		poReport.isLoaded();

		if (reassignType.equals("ReAssign")) {
			ReassignTask reassignTask = poReport.clickReassign();
			reassignTask.inputUserName(newUser);
			reassignTask.addComments("Task Reassigning to User:"+newUser+" by "+user);
			reassignTask.clickSubmit();
		}

		if (reassignType.equals("Delegate")) {
			ReassignTask reassignTask = poReport.clickDelegate();
			reassignTask.inputUserName(newUser);
			reassignTask.addComments("Task delegated to User:"+newUser+" by "+user);
			reassignTask.clickSubmit();
		}

		SelUtil.waitUntilPageClosed();
		driver.switchTo().window(currentWindowHandle);
		logout();

	}

}
