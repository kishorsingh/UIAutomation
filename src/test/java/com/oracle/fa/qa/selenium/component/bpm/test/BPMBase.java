package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.fa.qa.selenium.component.bpm.page.*;
import com.oracle.fa.qa.selenium.component.bpm.page.LoginPage;
import com.oracle.fa.qa.selenium.component.common.page.*;
import com.oracle.fa.qa.selenium.component.common.test.TestBase;
import com.oracle.fa.qa.selenium.component.hcm.page.SubmitResignationPage;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class BPMBase extends TestBase {

    protected void verifyWorklistElementsInKorean(String user, String password) {
        loginInAllLanguage(user, password);
        StepReport.info("Login Test successful");

        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        String mainWindow = driver.getWindowHandle();

        clickBellNotifyButton();
        verifyTaskNamesInBellboxInKorean();
        clickMoreDetailsLinkInKorean();

        Set<String> windowHandle = driver.getWindowHandles();
        for (String s : windowHandle) {
            if (!mainWindow.equalsIgnoreCase(s)) {
                driver.switchTo().window(s);
                SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
                soaWorkListPage.isLoaded();
                DriverUtil.sleep(5000);

                verifyMenuItemsInKorean();
                verifyTaskNamesInKorean();
                verifyActionItemsInKorean();

                DriverUtil.sleep(5000);
            }
        }

        driver.switchTo().window(mainWindow);

        homePage.logoutInAllLanguage();
    }

    private void clickBellNotifyButton() {
        StepReport.info("Click Bell Notification Button");
        //String bellNotifyBnXPath = "//*[contains(@class, 'af_commandImageLink_image') and contains(@class, 'svg-bkgd11')]";
        String bellNotifyBnXPath = "//*[contains(@class, 'svg-bkgd11')]";
        WebElement bellNotifyButton = DriverUtil.getElement(By.xpath(bellNotifyBnXPath));
        PageLoadHelper.waitForElementVisible(bellNotifyButton);
        bellNotifyButton.click();
        DriverUtil.sleep(5000L);
    }

    private void clickMoreDetailsLinkInKorean() {
        StepReport.info("Click Bell More Details link in Korean");
        //String moreDetailLinkXPath = "//td[@class='af_menuBar_item-text-cell']/a[@class='af_commandMenuItem_bar-item-text' and @tabindex='-1']";
        String moreDetailLinkXPath = "//td[@class='af_menuBar_item-text-cell' or @class='x1lz']/a[@tabindex='-1']";
        WebElement moreDetailLink = DriverUtil.getElement(By.xpath(moreDetailLinkXPath));
        PageLoadHelper.waitForElementVisible(moreDetailLink);
        moreDetailLink.click();
        DriverUtil.sleep(10000);
    }

    protected boolean isAllTextInEnglish(String text) {
        return text.matches("[A-Za-z|0-9|\\s|(|)|,|\\.|:|;|&]+");
    }

    private void verifyTaskNamesInBellboxInKorean() {
        StepReport.info("Test if all tasks in bell box are in Korean");
        String xpath = "//div[@id='pt1:_UISatr:0:p1::content']//div[@class='af_panelHeader_content0']//a";
        List<WebElement> taskNameElems = DriverUtil.getElements(By.xpath(xpath));
        System.out.println(taskNameElems.size() + " links in bell box are found");
        for (int i = 0; i < taskNameElems.size(); i++) {
            if (taskNameElems.get(i).isDisplayed()) {
                String taskName = taskNameElems.get(i).getText();
                System.out.println(taskName);
                StepReport.assertTrue(taskName + " is in other language", !isAllTextInEnglish(taskName));
            }
        }
    }

    private void verifyMenuItemsInKorean() {
        StepReport.info("Test if all menu items are in Korean");
        String menuItemXPath = "//div[@id='homePageTemplate:homePageRegion:0:vldc:vsdfsdftpdc:pgl2']//td/a[contains(@id, 'homePageTemplate:homePageRegion')]";
        List<WebElement> linkElems = DriverUtil.getElements(By.xpath(menuItemXPath));
        StepReport.info("Link size: " + linkElems.size());
        for (int i = 0; i < linkElems.size(); i++) {
            WebElement linkElem = linkElems.get(i);
            if (linkElem.isDisplayed()) {
                String linktext = linkElem.getText();
                System.out.println(linktext);
                StepReport.assertTrue(linktext + " is in other language", !isAllTextInEnglish(linktext));
            }
        }
    }

    private void verifyTaskNamesInKorean() {
        StepReport.info("Test if all tasks are in Korean");
        String taskListImgXPath = "//img[contains(@src, '/integration/worklistapp/images/qual_clipboard')]";
        List<WebElement> taskNameElems = DriverUtil.getElements(By.xpath(taskListImgXPath));
        StepReport.info(taskNameElems.size() + " tasks are displayed");
        for (int i = 0; i < taskNameElems.size(); i++) {
            String taskName = taskNameElems.get(i).findElement(By.xpath("./../../td[3]//a")).getText();
            System.out.println(taskName);
            StepReport.assertTrue(taskName + " is in other language", !isAllTextInEnglish(taskName));
        }
    }

    private void verifyActionItemsInKorean() {
        StepReport.info("Test if all action items are in Korean");
        String taskListImgXPath = "//img[contains(@src, '/integration/worklistapp/images/qual_clipboard')]";
        List<WebElement> taskListImages = DriverUtil.getElements(By.xpath(taskListImgXPath));
        if (taskListImages.size() > 0) {
            taskListImages.get(0).click();
            DriverUtil.sleep(5000);
        } else {
            System.out.println("There is no task listed on worklist page");
            return;
        }

        String actionLinkXPath = "//*[@id='homePageTemplate:homePageRegion:0:tldc:taskActionsSelect']//a";
        WebElement actionLink = DriverUtil.getElement(By.xpath(actionLinkXPath));
        PageLoadHelper.waitForElementClickable(actionLink);
        actionLink.click();
        DriverUtil.sleep(5000L);

        String actionItemPath = "//table[@id='homePageTemplate:homePageRegion:0:tldc:taskActionsSelect::menu']//tr[contains(@id, 'homePageTemplate:homePageRegion')]/td[2]";
        List<WebElement> actionItems = DriverUtil.getElements(By.xpath(actionItemPath));
        System.out.println("Action item size: " + actionItems.size());
        for (int i = 0; i < actionItems.size(); i++) {
            WebElement actionItem = actionItems.get(i);
            if (actionItem.isDisplayed()) {
                String linktext = actionItem.getText();
                //StepReport.assertTrue(linktext + " is in other language", !isAllTextInEnglish(linktext));
                System.out.println(linktext);
            }
        }
    }

    protected void verifyTaskConfiguration() {
        login(hcmUser1,hcmUser1Pwd);
        StepReport.info("Login Test successful");
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        String mainWindow= driver.getWindowHandle();

        SOAWorkListPage workListPage = clickToOpenBPMWorklistWindow();
        workListPage.clickAdminstration();
        workListPage.clickTaskConfigurationButton();
        String taskName = "PromotionsApproval";
        workListPage.typeSearchText(taskName);
        workListPage.clickSearchButton();
        workListPage.clickTask(taskName);
        workListPage.clickTaskAssignee();

        workListPage.clickPromotionApprovers();
        workListPage.switchToVerticalLayout();
        workListPage.clickPromotionApprovers();

        driver.switchTo().window(mainWindow);

        logout();
    }

    protected void verifyAssigneeDiagramDisplayed(String user, String password) {
        loginInAllLanguage(user, password);
        StepReport.info("Login Test successful");

        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        String mainWindow = driver.getWindowHandle();

        clickBellNotifyButton();
        clickMoreDetailsLinkInKorean();

        Set<String> windowHandle = driver.getWindowHandles();
        for (String s : windowHandle) {
            if (!mainWindow.equalsIgnoreCase(s)) {
                driver.switchTo().window(s);
                SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
                soaWorkListPage.isLoaded();
                soaWorkListPage.clickAdminstration();
                soaWorkListPage.clickTaskConfigurationButton();

                soaWorkListPage.clickTask("AbsencesApproval");
                soaWorkListPage.clickTaskAssignee();
                verifyDiagramItemsInKorean();

                DriverUtil.sleep(5000);
            }
        }

        driver.switchTo().window(mainWindow);

        homePage.logoutInAllLanguage();
    }

    private void verifyDiagramItemsInKorean() {
        StepReport.info("Test if diagram items are in Korean");
        String xpath = "//span[@class='x15e'] | //span[@class='af_commandImageLink_text']";
        List<WebElement> elements = DriverUtil.getElements(By.xpath(xpath));
        System.out.println("Diagram item size: " + elements.size());
        for (int i=0; i<elements.size(); i++) {
            WebElement element = elements.get(i);
            if (element.isDisplayed()) {
                String value = element.getText();
                System.out.println(value);
                StepReport.assertTrue(value + " is in other language", !isAllTextInEnglish(value));
            }
        }
    }

    protected void changeAuditLevel() {
        login(fusionUser, fusionUserPwd);
        StepReport.info("Login Test successful");
        SetupMaintenance setupMaintenance = homePage.clickSetupMaintenance();
        setupMaintenance.searchTask("Manage Audit Policies");
        ManageAuditPolicies manageAuditPolicies = setupMaintenance.openTask("Manage Audit Policies");
        manageAuditPolicies.selectAuditLevel("Oracle SOA Suite", "High - All Events");
        manageAuditPolicies.clickSaveAndClose();
        logout();

    }

    protected void createDTRTEvent() {
        login(finUser1, finUser1Pwd);
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
        soaWorkListPage.isLoaded();
        soaWorkListPage.clickAdminstration();
        soaWorkListPage.clickTaskConfigurationButton();
        soaWorkListPage.typeSearchText("FinApHoldApproval");
        soaWorkListPage.clickSearchButton();
        soaWorkListPage.clickComposite("FinApHoldApproval");
        soaWorkListPage.clickEditTask();
        soaWorkListPage.clickTaskAssignee();
        soaWorkListPage.selectStage("Invoice Hold A...");
        soaWorkListPage.clickAdvanced();
        soaWorkListPage.clickIgnoreStage();
        soaWorkListPage.clickSave();
        soaWorkListPage.clickCommentOKButton();
        soaWorkListPage.clickInformationOKButton();
        soaWorkListPage.clickCommitTask();
        soaWorkListPage.clickCommentOKButton();
        soaWorkListPage.clickInformationOKButton();
        driver.switchTo().window(mainWindow);
        logout();
    }

    protected void verifyAuditTrace() {
        login(internalAuditor, internalAuditorPwd);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        AuditReports auditReports = navigator.clickAuditReports();
        auditReports.typeStartDate();
        auditReports.selectProduct("Oracle SOA Suite (SOA)");
        auditReports.selectEventType("SOA DT@RT Change");
        auditReports.clickSearchButton();
        throw new TestErrorException("Fix for BUG 27986668 is not merged");
        //logout();
    }

    protected void changeViewToPinMode(String user, String password) {
        login(user, password);
        StepReport.info("Login Test successful");

        String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
        openWorklistWindow(mainWindow);

        SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
        soaWorkListPage.isLoaded();

        if (!soaWorkListPage.isUnpinIconPresent()) {
            soaWorkListPage.clickPinIcon();
        }

        FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
        logout();
    }

    protected void switchView(String user, String password) {
        login(user, password);
        StepReport.info("Login Test successful");

        String mainWindow = FrameworkContext.getInstance().getWebDriver().getWindowHandle();
        openWorklistWindow(mainWindow);

        SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
        soaWorkListPage.isLoaded();

        soaWorkListPage.clickView("My Tasks");
        soaWorkListPage.clickView("Due Soon");
        soaWorkListPage.clickView("High Priority");
        soaWorkListPage.clickUnpinIcon();
        soaWorkListPage.clickRefreshPage();
        soaWorkListPage.clickPinIcon();
        soaWorkListPage.clickRefreshPage();

        FrameworkContext.getInstance().getWebDriver().switchTo().window(mainWindow);
        logout();
    }

    /**
     * This method logins to EM.
     *
     * @return Returns EMHomePage object after logging in to EM.
     */
    public EMHomePage loginToEMLocal()
    {
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        String username=FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        String password= FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        EMHomePage emHomePage = loginPage.loginToEMLocal(username,password);
        emHomePage.isLoaded();
        return emHomePage;
    }

    /**
     * Deploys a composite through EM
     * @param composite Enter composite location that needs to be deployed.
     */
    public void deployComposite(String composite) {

        EMHomePage emHomePage = loginToEMLocal();
        emHomePage.clickContinueIfAccessibilityPrefDialogExist();
        emHomePage.clickSOAMenu();
        StepReport.info("Performing composite deployment");
        emHomePage.clickSOAInfraMenu();
        emHomePage.clickSOADefaultPatititionMenu();
        emHomePage.clickDeployment();
        emHomePage.clickDeployToThisPartition();
        emHomePage.enterJARorWARlocation(composite);
        emHomePage.clickDeploymentNextButton();
        String isErrMsgPresent = emHomePage.getErrorMsgIfSeen();
        System.out.println("Error msg:"+isErrMsgPresent);
        if(isErrMsgPresent.equalsIgnoreCase("No Error")) {
            System.out.println("Error msg not present, hence proceeding..");
            emHomePage.clickDeployButton();
            emHomePage.confirmDeployment();
        }
        else if (isErrMsgPresent.contains("is already deployed")) {
            StepReport.info("Composite already deployed");
            emHomePage.clickOKButtonOnMsgDlg();
            emHomePage.clickCancelButton();
        }
        else {
            Assert.assertFalse(false, "Deployment failed.");
        }
        emHomePage.signout();
    }

    /**
     * This method is used to deploy TaskForm application, given the war location.
     *
     * @param warLocation Enter the warLocation of the Application to deploy the task form.
     */
    public void deployCompositeTF(String warLocation) {

        EMHomePage emHomePage = loginToEMLocal();
        StepReport.info("Performing EAR deployment");
        emHomePage.expand("WebLogic Domain");
        emHomePage.clickDeployInWeblogicHomeMenu();
        emHomePage.enterJARorWARlocation(warLocation);
        emHomePage.clickButton("Next");
        emHomePage.selectSoaServerDeployCheckbox();
        //emHomePage.findXpathsOfAllElements("buttons");
        emHomePage.clickButton("Next");
        emHomePage.clickButton("Deploy");
        emHomePage.waitFor(5000L);
        if(!emHomePage.verifyEarDeployProgress())
            Assert.assertFalse(false, "Deployment failed.");

        //emHomePage.signout();
    }

    /**
     * This method invokes a composite through EM.
     *
     * @param compositeName Provide compositName for which instance must be invoked.
     * @param payload Provide relevant payload to invoke the composite.
     */
    public void invokeComposite(String compositeName, String payload) {
        EMHomePage emHomePage = loginToEMLocal();
        StepReport.info("Invoking composite: "+compositeName);
        emHomePage.clickOnFarm_WLS_SOAWCLink();
        emHomePage.clickComposite(compositeName);
        emHomePage.clickTest();
        emHomePage.inputPayload(payload);
        emHomePage.clickTestWebService();
        if(!emHomePage.testInvokeCompositeStatus(compositeName))
            Assert.assertFalse(true,"Invoke Composite Failed.");
        //Need to enhance the code to capture the instance ID and pass it to user. New window is not recognised in seleninum. Need to explore.
        emHomePage.signout();
    }

    /**
     * This method will undeploy a given composite through EM.
     *
     * @param compositeName Provide composite to undeploy.
     */
    public void undeployComposite(String compositeName) {
        EMHomePage emHomePage = loginToEMLocal();
        StepReport.info("Undeploying composite: "+compositeName);
        emHomePage.clickOnFarm_WLS_SOAWCLink();
        emHomePage.clickComposite(compositeName);
        emHomePage.clickSOACompositeMenu();
        emHomePage.clickSOACompositeMenuSoaDeploymentLink();
        emHomePage.clickSOACompositeMenuSoaDeploymentUndeployLink();
        emHomePage.clickSOAUnDeployButton();
        if(!emHomePage.confirmUnDeployment(compositeName))
            Assert.assertFalse(true,"Undeploy Composite Failed.");
        emHomePage.signout();
    }

    /**
     * This methods test if the user details are present in the reassign dialog, when a user is selected.
     *
     * @param user Enter the username for which details should be seen on reassign dialog.
     */
    public void selectTaskAndCheckUserDetailsInReassignDialogSearchPane(String user) {

        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        loginPage.loginToBPMLocal("jcooper","welcome1");
        StepReport.info("Login successful");
        System.out.println("Inside selectTaskAndCheckUserDetailsInReassignDialogSearchPane");
        SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
        soaWorkListPage.clickTask("FineGrainedAccessTest");
        soaWorkListPage.clickOnActionLink();
        soaWorkListPage.clickOnReassignLink();

        if(soaWorkListPage.isReassignDialogDisplayed())
        {
            soaWorkListPage.reassignDialogSearchUserName(user);
            soaWorkListPage.clickReassignDialogSearchUserLink(user);
            soaWorkListPage.clickOKButton();
            //Yet to implement validation. Currently not done due to bug.
        }
        else
        {
            StepReport.info("Reassign dialog is not found: ");
            Assert.assertFalse(true,"Reassign Dialog not found");
        }
        throw new TestErrorException("Fix for BUG 26752292 is not merged");
    }


    /**
     * This method logins to BPM, clicks on the given task and changes focus to the details pane of a task.
     *
     * @param username Enter the login name.
     * @param password Enter the password.
     * @param taskName Enter the taskName.
     * @return returns soaWorklistPage object.
     */
    public SOAWorkListPage loginToBPMAndClickTask(String username, String password, String taskName){
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        SOAWorkListPage soaWorkListPage = PageFactory.getPage(SOAWorkListPage.class);
        loginPage.loginToBPMLocal(username,password);
        StepReport.info("Login successful");
        StepReport.info("Click on the task name: "+taskName);
        soaWorkListPage.clickTask(taskName);
        StepReport.info("Select Details Frame:");
        soaWorkListPage.switchFrameToDetailsPane();
        return soaWorkListPage;
    }

    /**
     * This method focuses on the default area to sign out from the BPM Page.
     *
     * @param soaWorkListPage Provide SoaWorkListPage object to focus on default pane and then signout.
     */
    public void signOutBPM(SOAWorkListPage soaWorkListPage){
        soaWorkListPage.switchToDefaultFrame();
        BPMHomePage bpmHomePage = PageFactory.getPage(BPMHomePage.class);
        StepReport.info("Signing out... from BPM");
        bpmHomePage.signout();
    }

    /**
     * This method requests information from a user.
     *
     * @param username Enter the user who is requesting the information.
     * @param password Enter the password of the user who is requesting the information.
     * @param taskName Enter the taskname.
     * @param requestUser Enter the user to which the task should be assigned to request information from.
     * @param comments  Provide comments accordingly.
     */
    public void requestInfoAction(String username, String password, String taskName, String requestUser, String comments) {
        SOAWorkListPage soaWorkListPage=loginToBPMAndClickTask(username,password,taskName);
        StepReport.info("Request information action start:");
        soaWorkListPage.clickOnActionButton();
        soaWorkListPage.clickOnRequestInfoLink();
        soaWorkListPage.selectOtherUserLink();
        soaWorkListPage.inputOtherUser(requestUser);
        soaWorkListPage.enterComments(comments);
        soaWorkListPage.clickOKonReqInfoDlg();

        StepReport.info("Request information action completed.");
        signOutBPM(soaWorkListPage);
    }

    /**
     * This method reassigns the task to the mentioned user.
     *
     * @param username Enter the user who is reassigning the task.
     * @param password Enter password of the user.
     * @param taskName Enter taskName.
     * @param reassignUser Enter the user to which task should be reassigned to.
     */
    public void reassignTaskAction(String username, String password, String taskName, String reassignUser) {
        SOAWorkListPage soaWorkListPage=loginToBPMAndClickTask(username,password,taskName);
        StepReport.info("Reassign task action started:");
        soaWorkListPage.clickOnActionLink();
        soaWorkListPage.clickOnReassignLink();
        if(soaWorkListPage.isReassignDialogDisplayed())
        {
            soaWorkListPage.reassignDialogSearchUserName(reassignUser);
            soaWorkListPage.clickReassignDialogSearchUserCheckbox(reassignUser);
            soaWorkListPage.clickOKButton();
        }
        else
        {
            StepReport.assertFalse("Reassign dialog is not found.",true);
        }
        StepReport.info("Reassign task completed.");
        signOutBPM(soaWorkListPage);
    }

    /**
     * This method submits the information that is requeted for.
     *
     * @param username Enter the user who should submit the information.
     * @param password Enter the password.
     * @param taskName Enter the taskName.
     * @param submitInfoComments Enter comments to submit the requested information accordingly.
     */
    public void submitInfoRequested(String username, String password, String taskName, String submitInfoComments){
        SOAWorkListPage soaWorkListPage=loginToBPMAndClickTask(username,password,taskName);
        StepReport.info("Submit information requested action start:");
        soaWorkListPage.clickCreateComment();
        soaWorkListPage.enterCommentsForSubmitInfo(submitInfoComments);
        soaWorkListPage.clickOKButton();
        soaWorkListPage.clickOnActionLink();
        soaWorkListPage.clickSubmitInformationLink();
        StepReport.info("Submit information requested action completed:");
        signOutBPM(soaWorkListPage);
    }

    /**
     *
     * This method returns true or false accordingly, based on the
     * comments if present.
     *
     * @param username Enter the username who needs to verify the information submitted.
     * @param password Enter the password.
     * @param taskName Enter the taskName.
     * @param infoComments Enter the comments as a String Array that needs to be verified.
     * @return Returns true/false based on whether the comments are present.
     */
    public boolean verifyInfoSubmitted(String username, String password, String taskName, String[] infoComments){
        SOAWorkListPage soaWorkListPage=loginToBPMAndClickTask(username,password,taskName);
        StepReport.info("Verify information submitted:");
        return soaWorkListPage.verifyTextPresent(infoComments);
    }

    /**
     * Test to Reassign/RequestInfo with aggregated tasks.
     */
    public void testAggregationReassign(){
        StepReport.info("Test task aggregation reassign:");

        String taskName="TaskAggHumanTask";
        String password="welcome1";

        //Initiate an Aggregated task that gets assigned to fkafka. fkafka Request Info from mmitch
        String fkafkaRequestInfoComments="Requesting information from mmitch";
        requestInfoAction("fkafka",password,taskName,"mmitch",fkafkaRequestInfoComments);

        //mmitch Reassigns the task to jstein
        reassignTaskAction("mmitch",password,taskName,"jstein");

        //jstein Submits the Info
        String informationByJstein="Information submitted by jstein.";
        submitInfoRequested("jstein",password,taskName,informationByJstein);

        String[] comments={fkafkaRequestInfoComments,informationByJstein};

        //Verify that INFO Submitted to fkafka
        Assert.assertTrue(verifyInfoSubmitted("fkafka",password,taskName,comments),"Information not submitted to fkafka");

    }

    protected void saveResignationRequest(String user, String password) {
        login(user, password);
        StepReport.info("Login Test successful");
        Navigator navigator = homePage.clickNavigator();
        PersonalInfo personalInfo = navigator.clickPersonalinfo();
        DriverUtil.sleep(3000);
        StepReport.info("Navigation to personal info page successful");
        personalInfo.clickRelatedLinks();
        SubmitResignationPage submitResignationPage = personalInfo.clickSubmitResignation();
        submitResignationPage.clickReviewButton();
        submitResignationPage.clickSaveButton();
        try {
            submitResignationPage.clickYesButton();
        } catch (Exception e) { }
        submitResignationPage.clickOkButton();
        logout();
    }

    protected void verifyErrorAssigneesExpressionEditor(String user, String password, String compositeName) {
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
        soaWorkListPage.clickTaskAssignee();
        soaWorkListPage.clickErrorAssigneesEditor();

        DriverUtil.sleep(10000L);
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        //Get all window handles
        Set<String> allHandles = driver.getWindowHandles();

        String worklist =null;
        check: for(String s: allHandles) {
            System.out.println("currentWindow Handle" + s);
            driver.switchTo().window(s);
            System.out.println(driver.getTitle());
            if(driver.getTitle().equalsIgnoreCase("BPM Worklist")) worklist = s;
            if(driver.getTitle().equalsIgnoreCase("Expression Builder")) break check;
        }

        ExpressionBuilder expressionBuilder = PageFactory.getPage(ExpressionBuilder.class);

        driver.switchTo().frame("_adfvdlg");

        expressionBuilder.isLoaded();
        expressionBuilder.typeExpression("test");
        expressionBuilder.clickOKButton(driver);
        DriverUtil.sleep(10000L);

        driver = FrameworkContext.getInstance().getWebDriver();
        //Get all window handles
        allHandles = driver.getWindowHandles();

        for(String s: allHandles) {
            System.out.println("currentWindow Handle" + s);
            driver.switchTo().window(s);
            System.out.println(driver.getTitle());
            if(driver.getTitle().equalsIgnoreCase("An unresolvable error has occured. Please contact your administrator for more information.")) {
                throw new TestErrorException("Bug#26679675:An unresolvable error has occured. Please contact your administrator for more information.");
            }
        }

        driver.switchTo().window(mainWindow);
        logout();

    }

    protected void verifyEmailNotification(String actionType, String TaskNumber){

        StepReport.info("Start Login");
        ZimbraLogin zimbraLogin= PageFactory.getPage(ZimbraLogin.class);
        zimbraLogin.get("https://stbeehive.oracle.com/zimbra/");
        zimbraLogin.enterUserName("soaqatestuser_in@oracle.com");
        zimbraLogin.enterPassword("Soaqa123");
        ZimbraHome zimbraHome=zimbraLogin.clickSignin();
        zimbraHome.checkHomePage();
        zimbraHome.clickGetMail();
        ZimbraMail zimbraMail=zimbraHome.openEmail(TaskNumber);
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

    protected String createTaskForEmailNotifications(){

        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        EMHomePage emHomePage = loginPage.loginToEMLocal(bpmAdminUsername,bpmAdminPassword);
        StepReport.info("Invoking composite: ","emailActionApp [1.0]");
        emHomePage.clickonSoaDomain("soainfra");
        emHomePage.clickComposite("emailActionApp [1.0]");
        emHomePage.clickTest();
        emHomePage.inputPayload("TestInput");
        emHomePage.clickTestWebService();
        if(!emHomePage.testInvokeCompositeStatus("emailActionApp [1.0]"))
            Assert.assertFalse(true,"Invoke Composite Failed.");
        emHomePage.clicklaunchFlowTrace();
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

        EMFlowTracePage emFlowTracePage = PageFactory.getPage(EMFlowTracePage.class);
        emFlowTracePage.isLoaded();
        emFlowTracePage.clickHumanTask("emailActionAppHT");
        DriverUtil.sleep(2000);
        String TaskNumber=emFlowTracePage.getTaskNumber();
        StepReport.info("Task Number: ",TaskNumber);
        DriverUtil.sleep(1000);
        driver.switchTo().window(currentWindowHandle);
        emHomePage.signout();
        StepReport.info("<END> Create Task for Email Notifications");
        return TaskNumber;
    }
    protected void verifySendEmailNotifications(){

        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        EMHomePage emHomePage = loginPage.loginToEMLocal(bpmAdminUsername,bpmAdminPassword);
        emHomePage.clickonSoaDomain(bpmDomainName);
        emHomePage.expand("SOA");
        emHomePage.clickSOAInfraAdminServer();
        emHomePage.clickHumanWorkflow();
        emHomePage.clickNotificationManagement();
        EMDailoguePage emDailoguePage=emHomePage.clickSendTestNotification();
        emDailoguePage.typeRecipientAddress(bpmTestEmailID);
        emDailoguePage.selectChannelType();
        emDailoguePage.typeSubject();
        emDailoguePage.typeEmailBody();
        emDailoguePage.clickSendButton();
        emDailoguePage.checkResponse();
        emDailoguePage.clickClose();
        emHomePage.signout();
        StepReport.info("<END> Test Send Email Notifications");
    }

    protected void updateAliasInWorklistApp(String alias){

        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        loginPage.get(bpmWorklistURL);
        BPMWorklistHomePage bpmWorklistHomePage=loginPage.loginToBpmWorkList("weblogic","weblogic1");
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.clickAdministration();
        bpmWorklistHomePage.inputSenderNameAlias(alias);
        boolean success=bpmWorklistHomePage.clickSaveButton(alias);
        if(success==false){
            Assert.assertEquals(success,false);
            StepReport.info("Cannot Support '<' , '>' and ','(comma) in Alias Name");
        }
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.logout();
        StepReport.info("<END> Test updateAliasInWorklistApp");
    }

    protected void verifyAliasNameInEM(String alias){
        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        EMHomePage emHomePage = loginPage.loginToEMLocal(bpmAdminUsername,bpmAdminPassword);
        emHomePage.clickonSoaDomain(bpmDomainName);
        emHomePage.expand("SOA");
        emHomePage.clickSOAInfraAdminServer();
        emHomePage.clickSOAInfractureMenu();
        emHomePage.clickAdministration();
        emHomePage.clickSystemMBeanBrowser();
        emHomePage.clickSearchMBeanBrowser();
        emHomePage.inputMBeanBrowserSearch("HWFMailerConfig");
        emHomePage.clickSearchMBeanBrowserButton();
        emHomePage.clickSenderEmailAddresslink();
        String  emailAlias=emHomePage.getEmailFromAddress();
        StepReport.info("Alias from WorklistApp:"+"'"+alias+"'"+"Alias from EM:"+"'"+emailAlias+"'");
        Assert.assertEquals(alias,emailAlias);
        emHomePage.signout();
    }

    protected BPMWorklistHomePage loginBpmWorklistApp(String username, String password){

        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        loginPage.get(bpmWorklistURL);
        BPMWorklistHomePage bpmWorklistHomePage=loginPage.loginToBpmWorkList(username,password);
        return bpmWorklistHomePage;
    }

    protected EMHomePage loginBpmEMconsole(String username, String passwrod){

        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        EMHomePage emHomePage = loginPage.loginToEMLocal(bpmAdminUsername,bpmAdminPassword);
        emHomePage.isLoaded();
        return emHomePage;
    }


    public void  verifyEmailAliasTaskConf(){

        BPMWorklistHomePage bpmWorklistHomePage=loginBpmWorklistApp(bpmAdminUsername,bpmAdminPassword);
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.clickAdministration();
        bpmWorklistHomePage.clickTaskConfiguration();
        bpmWorklistHomePage.clickHumanTask(bpmHumanTask);
        bpmWorklistHomePage.clickNotifications();
        bpmWorklistHomePage.clickMore();
        String lableText=bpmWorklistHomePage.getEmailDisplayNameText();
        StepReport.info("Actual Value:"+lableText+" Expected Value:"+"Email \"From:\" Display Name");
        Assert.assertEquals(lableText,"Email \"From:\" Display Name");
        boolean option1=bpmWorklistHomePage.checkEmailOptions1();
        StepReport.info("Email Options1: "+option1);
        Assert.assertEquals(option1,true);
        boolean option2=bpmWorklistHomePage.checkEmailOptions2();
        StepReport.info("Email Options2: "+option2);
        Assert.assertEquals(option2,true);
        boolean option3=bpmWorklistHomePage.checkEmailOptions3();
        StepReport.info("Email Options3: "+option3);
        Assert.assertEquals(option3,true);
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.logout();
    }


    public void  verifyAliasNameInWorkListApp(String EMAliasName){

        BPMWorklistHomePage bpmWorklistHomePage=loginBpmWorklistApp(bpmAdminUsername,bpmAdminPassword);
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.clickAdministration();
        String senderAlias=bpmWorklistHomePage.readSenderNameAlias();
        StepReport.info("SenderNameActual: "+senderAlias);
        StepReport.info("SenderNameExpected: "+EMAliasName);
        int stringLength=EMAliasName.length();
        StepReport.info("Size of Alias Name:"+stringLength);
        if(stringLength>90){
            Assert.assertNotEquals(senderAlias,EMAliasName);
        }else{
            Assert.assertEquals(senderAlias,EMAliasName);
        }
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.logout();
    }

    protected void updateAliasNameinEM(String alias){

        EMHomePage emHomePage= loginBpmEMconsole(bpmAdminUsername,bpmAdminUsername);
        emHomePage.expand("SOA");
        emHomePage.clickSOAInfraAdminServer();
        emHomePage.clickSOAInfractureMenu();
        emHomePage.clickAdministration();
        emHomePage.clickSystemMBeanBrowser();
        emHomePage.clickSearchMBeanBrowser();
        emHomePage.inputMBeanBrowserSearch("HWFMailerConfig");
        emHomePage.clickSearchMBeanBrowserButton();
        emHomePage.clickSenderEmailAddresslink();
        emHomePage.updateEmailFromAddress(alias,bpmEmailSender);
        emHomePage.clickApply();
        emHomePage.signout();
    }

    public void  updateAliasNameInHumanTask(String alias){

        BPMWorklistHomePage bpmWorklistHomePage=loginBpmWorklistApp(bpmAdminUsername,bpmAdminPassword);
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.clickAdministration();
        bpmWorklistHomePage.clickTaskConfiguration();
        bpmWorklistHomePage.clickHumanTask(bpmHumanTask);
        bpmWorklistHomePage.clickNotifications();
        bpmWorklistHomePage.clickMore();
        if(alias=="PreviousOwner"){
            bpmWorklistHomePage.selectInputOption3();
        }else{
            bpmWorklistHomePage.selectInputOption2();
            bpmWorklistHomePage.inputSenderNameAliasHT(alias);
        }
        boolean success=bpmWorklistHomePage.clickSaveIcon(alias);
        if(success==false){
            Assert.assertEquals(success,false);
            StepReport.info("Cannot Support '<' , '>' and ','(comma) in Alias Name");
        }else{
            bpmWorklistHomePage.addComments();
            bpmWorklistHomePage.clickOK();
        }
        bpmWorklistHomePage.clickAdminMenu();
        bpmWorklistHomePage.logout();
    }

    public String createTaskForTaskSeqCacheEmailNotifications(String compositeName,String inputPayload, String soaDomain,String humanTask){

        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        EMHomePage emHomePage = loginPage.loginToEMLocal(bpmAdminUsername,bpmAdminPassword);
        StepReport.info("Invoking composite: ",compositeName);
        emHomePage.clickonSoaDomain(soaDomain);
        emHomePage.clickComposite(compositeName);
        emHomePage.clickTest();
        emHomePage.inputPayload(inputPayload);
        emHomePage.clickTestWebService();
        if(!emHomePage.testInvokeCompositeStatus(compositeName))
            Assert.assertFalse(true,"Invoke Composite Failed.");
        emHomePage.clicklaunchFlowTrace();
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

        EMFlowTracePage emFlowTracePage = PageFactory.getPage(EMFlowTracePage.class);
        emFlowTracePage.isLoaded();
        emFlowTracePage.clickHumanTask(humanTask);
        DriverUtil.sleep(2000);
        String TaskNumber=emFlowTracePage.getTaskNumber();
        StepReport.info("Task Number: ",TaskNumber);
        DriverUtil.sleep(1000);
        driver.switchTo().window(currentWindowHandle);
        emHomePage.signout();
        StepReport.info("<END> Create Task for Email Notifications");
        return TaskNumber;
    }

    protected void verifyTestSendEmailNotifications(String domainName){

        StepReport.info("Start Login");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        EMHomePage emHomePage = loginPage.loginToEMLocal(bpmAdminUsername,bpmAdminPassword);
        emHomePage.clickonSoaDomain(domainName);
        emHomePage.expand("SOA");
        emHomePage.clickSOAInfraAdminServer();
        emHomePage.clickHumanWorkflow();
        emHomePage.clickNotificationManagement();
        EMDailoguePage emDailoguePage=emHomePage.clickSendTestNotification();
        emDailoguePage.typeRecipientAddress(bpmTestEmailID);
        emDailoguePage.selectChannelType();
        emDailoguePage.typeSubject();
        emDailoguePage.typeEmailBody();
        emDailoguePage.clickSendButton();
        emDailoguePage.checkResponse();
        emDailoguePage.clickClose();
        emHomePage.signout();
        StepReport.info("<END> Test Send Email Notifications");
    }

    public void createTaskForRulesTest(String compositeName, String inputPayload, String soaDomain, String errorMessageString) {

        StepReport.info("<Start> Rule test");
        LoginPage loginPage = PageFactory.getPage(LoginPage.class);
        EMHomePage emHomePage = loginPage.loginToEMLocal(bpmAdminUsername, bpmAdminPassword);
        StepReport.info("Invoking composite: ", compositeName);
        emHomePage.clickonSoaDomain(soaDomain);
        emHomePage.clickComposite(compositeName);
        emHomePage.clickTest();
        emHomePage.inputPayload(inputPayload);
        emHomePage.clickTestWebService();
        if (!emHomePage.testInvokeCompositeStatus(compositeName))
            Assert.assertFalse(true, "Invoke Composite Failed.");
        emHomePage.clicklaunchFlowTrace();
        WebDriver driver = FrameworkContext.getInstance().getWebDriver();
        Set<String> allHandles = driver.getWindowHandles();
        String currentWindowHandle = allHandles.iterator().next();
        System.out.println("currentWindow Handle" + currentWindowHandle);
        allHandles.remove(allHandles.iterator().next());
        String lastHandle = allHandles.iterator().next();
        System.out.println("last window handle" + lastHandle);
        System.out.println(driver.getTitle());
        driver.switchTo().window(lastHandle);
        System.out.println(driver.getTitle());
        EMFlowTracePage emFlowTracePage = PageFactory.getPage(EMFlowTracePage.class);
        emFlowTracePage.isLoaded();
        String errorMessage = "//*[@id='flowTraceTemplate:auditTreeContentTemplate:flowTraceTable:0:errorMsgLink']/span";
        WebElement errorMessageElem = DriverUtil.getElement(By.xpath(errorMessage));
        StepReport.assertTrue(errorMessageString, errorMessageElem.isDisplayed());
        DriverUtil.sleep(1000);
        driver.switchTo().window(currentWindowHandle);
        emHomePage.signout();
        StepReport.info("<END> Rule test");

    }

}
