package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.prc.page.ManageUserDefAttr;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SOAWorkListPage extends BasePageObject<SOAWorkListPage> {

    @FindBy(xpath = "//img[@alt='Add View'] | //img[@id='homePageTemplate:homePageRegion:0:vldc:dc1:addViewButton::icon']")
    WebElement addViewIcon;

    @FindBy(xpath = "//*[text()='Administration'] | //tr[@id='homePageTemplate:cni2']/td[@class='x148'] | //tr[@id='homePageTemplate:cni2']/td[@class='af_commandMenuItem_menu-item-text']")
    WebElement administrationLink;

    @FindBy(xpath = "//*[text()='Preferences']")
    WebElement preferencesLink;

    @FindBy(xpath = "//a[text()='Approval Groups']")
    WebElement approvalGroupButton;

    @FindBy(xpath = "//a[text()='Task Configuration'] | //a[@id='homePageTemplate:adminTabPanel:taskEdiorTab']")
    WebElement taskConfigurationButton;

    @FindBy(xpath = "//*[@title='Add Member']")
    WebElement addMember;

    @FindBy(xpath = "//*[contains(@id,'memberNameInputBox::content')]")
    WebElement member;

    @FindBy(xpath = "//*[@accesskey='K']")
    WebElement okButton;

    @FindBy(xpath = "//a[@title='Add View']")
    WebElement addView;

    @FindBy(xpath = "//input[@value='user view']")
    WebElement viewNameInput;

    @FindBy(xpath = "//label[text()='Users']/parent::td/following::input")
    WebElement sharedUsers;

    @FindBy(xpath = "//button[text()='OK']")
    WebElement createViewOKButton;

    @FindBy(xpath = "//button[text()='Yes']")
    WebElement yesButton;

    @FindBy(xpath = "//a[@title='Select a Task Type']")
    WebElement selectTaskType;

    @FindBy(xpath="//*[contains(text(), 'Vacation Period')]")
    WebElement vacationPeriod;

    @FindBy(xpath ="//*[contains(@id,'taskTypeSearchField::content')]")
    WebElement searchTextBox;

    @FindBy(xpath ="//img[@title='Search task types']")
    WebElement search;

    @FindBy(xpath ="//a/img[@title='Edit task']")
    WebElement editTask;

    @FindBy(xpath ="//a[text()='Advanced']")
    WebElement advanced;

    @FindBy(xpath ="//*[text()='Ignore Stage']/preceding-sibling::input")
    WebElement ignoreStage;

    @FindBy(xpath ="//img[@title='Save']")
    WebElement saveButton;

    @FindBy(xpath ="//*[text()='Enter Comments']/following::button[text()='OK']")
    WebElement commentOK;

    @FindBy(xpath ="//*[text()='Information']/following::button[text()='OK'][1]")
    WebElement informationOK;

    @FindBy(xpath ="//img[@title='Commit task']")
    WebElement commitTask;

    @FindBy(xpath ="//a/span[text()='Assignees']")
    WebElement assignees;

    @FindBy(xpath = "//*[@id='stgptidPromotion Approvers']")
    WebElement promotionApprovers;

    @FindBy(xpath = "//span[text()='Switch to Vertical Layout']")
    WebElement switchToVerticalLayout;

    @FindBy(xpath = "//span[text()='Switch to Horizontal Layout']")
    WebElement switchToHorizontalLayout;

    @FindBy(xpath ="//a/span[text()='Deadlines']")
    WebElement deadlines;

    @FindBy(xpath ="//a/span[text()='Notifications']")
    WebElement notifications;

    @FindBy(xpath ="//*[text()='Task Level']/preceding-sibling::input")
    WebElement taskLevelRadioButton;

    @FindBy(xpath ="//*[text()='Expiration Settings']/following::*[text()='Minutes']/following::input[1]")
    WebElement expirationSettingsMinutes;

    @FindBy(xpath ="//*[text()='Escalate']/preceding-sibling::input")
    WebElement escalateRadioButton;

    @FindBy(xpath ="(//*[text()='Maximum Escalation Levels']/following::input)[1]")
    WebElement maximumEscalationLevels;

    @FindBy(xpath = "//a[.='Actions']")
    WebElement actionLink;

    @FindBy(xpath = "//a[.='Reject']/following::a[.='Actions']")
    WebElement actionButton;


    @FindBy(xpath = "//td[.='Reassign...']")
    WebElement reassign;

    @FindBy(xpath = "//td[.='Request Information...']")
    WebElement requestInfoLink;

    @FindBy(xpath = ".//*[text()='Other users']/preceding::input[1]")
    WebElement selectOtherUser;

    @FindBy(xpath = ".//*[text()='Other users']/following::input[1]")
    WebElement otherUserInput;

    @FindBy(xpath = ".//*[text()='Comments:']/following::textarea")
    WebElement commentsTextArea;

    @FindBy(xpath = ".//*[@id='reqIfD::ok']")
    WebElement reqInfoOKButton;

    @FindBy(xpath = "//div[text()='Reassign Task']")
    WebElement reassignDialog;

    @FindBy(xpath = ".//*[text()='Users']/following::input[1]")
    WebElement reassignDialogSearchUserName;

    @FindBy(xpath = ".//button[text()='Search']")
    WebElement reassignDialogSearchButton;

    //@FindBy(xpath = ".//button[contains(@id,'reassignDialog')][.='OK']")
    @FindBy(xpath = ".//button[.='OK'][@accesskey='K']")
    WebElement OKbutton;

    @FindBy(xpath = "//td[.='Submit Information']")
    WebElement submitInfo;

    @FindBy(xpath = "//a[@title='Create']")
    WebElement createComment;

    @FindBy(xpath = "//textarea[@name='cmtBox']")
    WebElement commentTextAreaInInfoSubmit;

    @FindBy(xpath = "//table[contains(@summary,'COMMENTS_FOR_WORKLIST_TASK')]")
    WebElement commentsForTask;

    @FindBy(xpath ="//*[text()='Do Nothing']/preceding-sibling::input")
    WebElement doNothingRadioButton;

    @FindBy(xpath ="(//*[text()='Error assignees']/following::img)[2]")
    WebElement errorAssigneesEditor;

    @FindBy(xpath ="//*[text()='Enable Reminder']/parent::*/input")
    WebElement enableReminder;

    @FindBy(xpath ="(//*[text()='Repeat']/following::select)[1]")
    WebElement reminderRepeat;

    @FindBy(xpath ="//*[text()='Enable Reminder']/following::*[text()='Minutes']/following::input[1]")
    WebElement reminderMinutes;

    @FindBy(xpath ="(//*[text()='Number of levels']/following::input)[1]")
    WebElement numberOfLevels;

    @FindBy(xpath ="//*[contains(text(),'Administrative Tasks')]")
    WebElement administrativeTasks;

    @FindBy(xpath ="//*[contains(@id,'statusFilter::content')]")
    WebElement statusFilter;

    @FindBy(css ="input[id*='keywordFilter']")
    WebElement filter;

    @FindBy(xpath ="//*[contains(@id,'searchButton::icon')]")
    WebElement seachButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(addViewIcon) == null) {
            throw new TestErrorException("The add view icon is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Add view icon Displayed");
    }

    public void deleteView(String viewName) {
        StepReport.info("Delete View:" +viewName );

        By view = By.xpath("//a[contains(text(), '" + viewName + "')]");
        if(DriverUtil.isElementPresent(view)) {
            WebElement viewlink = DriverUtil.getElement(view);
            viewlink.click();
            DriverUtil.sleep(3000L);

            DriverUtil.getElement(By.xpath("//img[contains(@src,'/integration/worklistapp/images/func_remove_16')]")).click();

            DriverUtil.sleep(3000L);
            yesButton.click();
            DriverUtil.sleep(3000L);
            StepReport.info("Delete View:" +viewName + " successfully!");
        }

    }

    public boolean isViewAvailable(String viewName) {
        StepReport.info("verify View:" +viewName );

        By view = By.xpath("//a[contains(text(), '" + viewName + "')]");
        if(DriverUtil.isElementPresent(view)) {
            WebElement viewlink = DriverUtil.getElement(view);
            viewlink.click();
            DriverUtil.sleep(3000L);
            return true;
        }
        return false;
    }

    public void createSharedView(String viewName, String sharedUser) {
        StepReport.info("Create Shared View:" +viewName );
        deleteView(viewName);
        PageLoadHelper.waitForElementClickable(addView);
        addView.click();
        DriverUtil.sleep(5000L);

        viewNameInput.clear();
        viewNameInput.sendKeys(viewName);

        selectTaskType.click();
        DriverUtil.sleep(4000L);

        WebElement absAppElement = DriverUtil.getElement(By.xpath("//span[text()='AbsencesApproval']"));
        DriverUtil.clickByJS(absAppElement);
        //absAppElement.click();
        DriverUtil.sleep(2000L);
        DriverUtil.getElement(By.xpath("//div[text()='Task Type Browser']/following::button[text()='OK']")).click();
        DriverUtil.sleep(4000L);

        sharedUsers.clear();
        sharedUsers.sendKeys(sharedUser);
        DriverUtil.sleep(2000L);

        createViewOKButton.click();

        DriverUtil.sleep(4000L);

        WebElement viewLink = DriverUtil.getElement(By.xpath("//a[contains(text(), '"+viewName+"')]"));
        viewLink.click();

        DriverUtil.sleep(3000L);
    }

    public void clickAdminstration() {
        StepReport.info("Click Administration link");
        DriverUtil.clickByJS(administrationLink);
        DriverUtil.sleep(5000L);
    }

    public void clickApprovalGroupButton() {
        StepReport.info("Click Approve Group Button");
        PageLoadHelper.waitForElementClickable(approvalGroupButton);
        DriverUtil.clickByJS(approvalGroupButton);
        DriverUtil.sleep(5000L);
    }

    public void clickTaskConfigurationButton() {
        StepReport.info("Click Task Configuration Button");
        PageLoadHelper.waitForElementClickable(taskConfigurationButton);
        DriverUtil.clickByJS(taskConfigurationButton);
        DriverUtil.sleep(5000L);
    }

    public void clickCreateApprovalGroupIcon() {
        StepReport.info("Click Administration link");
        String xpath = "//img[@alt='Create Approval Group']";
        DriverUtil.sleep(2000L);
        WebElement createApproveGroupIcon = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(createApproveGroupIcon);
        DriverUtil.clickByJS(createApproveGroupIcon);
        DriverUtil.sleep(5000L);
    }

    public void inputApprovalGroupName(String groupName) {
        StepReport.info("Input Approval Group Name");
        String xpath = this.calculateInputXPathByLabel("Name", "input");
        DriverUtil.sleep(2000L);
        WebElement nameField = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(nameField);
        nameField.clear();
        nameField.sendKeys(groupName);
        DriverUtil.sleep(5000L);
    }

    public void clickSaveButton() {
        StepReport.info("Input Approval Group Name");
        String xpath = "//button[text()='Save']";
        DriverUtil.sleep(2000L);
        WebElement saveButton = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(saveButton);
        DriverUtil.clickByJS(saveButton);
        DriverUtil.sleep(5000L);
    }

    public void addMember(String name) {
        StepReport.info("Add member"+name);
        DriverUtil.clickByJS(addMember);
        PageLoadHelper.waitForElementVisible(member);
        member.sendKeys(name);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(okButton);
        DriverUtil.sleep(3000L);
    }

    public boolean isGroupCreated() {
        StepReport.info("Check if the group is created");
        try {
            String xpath = "//span[text()='US Office Technology Approval Group']";
            WebElement group = DriverUtil.getElement(By.xpath(xpath));
            if(group.isDisplayed()) return true;
            else return false;
        }catch(Exception e) {
            return false;
        }
    }

    private String calculateInputXPathByLabel(String label, String tag) {
        return "//label[text()='" + label + "']//following::" + tag + "[1]";
    }

    public void clickTask(String taskName) {
        StepReport.info("Click task " + taskName);
        String xpath = "//span[text()='" + taskName + "']";
        WebElement taskLink = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(taskLink);
        taskLink.click();
        DriverUtil.sleep(10000L);
    }

    public void clickTaskAssignee() {
        StepReport.info("Click task task assignee");
        String xpath = "//img[contains(@src, '/integration/worklistapp/images/assignees_enabled')]";
        WebElement assigneeImg = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(assigneeImg);
        assigneeImg.click();
        DriverUtil.sleep(5000L);
    }

    public void clickGoToRule(String stageName) {
        StepReport.info("Click Go to rule");
        String xpath = "(//*[text()='"+stageName+"']/following::*[contains(@id,'rule-icon')])[1]";
        WebElement ruleIcon = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(ruleIcon);
        ruleIcon.click();
        DriverUtil.sleep(5000L);

        String xpath1 = "(//*[text()='"+stageName+"']/following::li/span[text()='Go to rule'])[1]";
        WebElement goToRule = DriverUtil.getElement(By.xpath(xpath1));
        PageLoadHelper.waitForElementClickable(goToRule);
        goToRule.click();
        DriverUtil.sleep(5000L);
    }

    public void clickRuleSet(String ruleSetName) {
        StepReport.info("Click rule set",ruleSetName);
        String xpath = "//*[text()='"+ruleSetName+"']";
        WebElement ruleSet = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(ruleSet);
        DriverUtil.sleep(3000L);
        ruleSet.click();
        DriverUtil.sleep(5000L);
    }

    public void expandRule(String ruleName) {
        StepReport.info("Expand Rule:",ruleName);
        String xpath = "//*[@value='"+ruleName+"']/preceding::*[@title='Expand']";
        WebElement rule = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(rule);
        rule.click();
        DriverUtil.sleep(5000L);
    }

    public void enterLevels(String levels) {
        StepReport.info("Enter levels",levels);
        PageLoadHelper.waitForElementVisible(numberOfLevels);
        DriverUtil.sleep(2000L);
        numberOfLevels.clear();
        DriverUtil.sleep(2000L);
        numberOfLevels.sendKeys(levels);
        DriverUtil.sleep(2000L);
        numberOfLevels.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void clickPromotionApprovers() {
        StepReport.info("Click Promotion Approvers");

        DriverUtil.sleep(3000L);

        List<WebElement> paElements = DriverUtil.getElements(By.xpath("//*[text()='Promotion Appr...']"));
        paElements.get(1).click();
        //paElement.click();
        //PageLoadHelper.waitForElementVisible(promotionApprovers);
        //promotionApprovers.click();
        DriverUtil.sleep(5000L);
        String xpath = "//span[text()='SoaOLabel.PromotionApprovers']";
        WebElement promotion = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(promotion);
    }

    public void switchToVerticalLayout() {
        StepReport.info("switch To Vertical Layout");
        PageLoadHelper.waitForElementClickable(switchToVerticalLayout);
        switchToVerticalLayout.click();
        DriverUtil.sleep(5000L);
    }

    public void switchToHorizontalLayout() {
        StepReport.info("switch To Horizontal Layout");
        PageLoadHelper.waitForElementClickable(switchToHorizontalLayout);
        switchToHorizontalLayout.click();
        DriverUtil.sleep(5000L);
    }

    public void clickPreferences() {
        StepReport.info("Click PreferencesLink link");
        DriverUtil.clickByJS(preferencesLink);
        DriverUtil.sleep(5000L);
    }

    public void clickVacationPeriod() {
        StepReport.info("Click Vacation Period");
        PageLoadHelper.waitForElementClickable(vacationPeriod);
        vacationPeriod.click();
        DriverUtil.sleep(5000L);
    }

    public void configureVacationRuleToDelegate(String delegateUser) {
        StepReport.info("Configure vacation period and delegate to user " + delegateUser);
        clickEnableVacationPeriodCheckbox();
        inputDateField(calculateInputXPathByLabel("Start Date", "input"), "7/1/18 7:00 AM");
        inputDateField(calculateInputXPathByLabel("End Date", "input"), "7/1/22 7:00 AM");
        selectDelegateTo();
        inputDelegateUser(delegateUser);
        saveVacationRule();
    }

    private void clickEnableVacationPeriodCheckbox() {
        StepReport.info("Click enable vacation period checkbox");
        String enableRuleCheckboxXPath = "//input[@type='checkbox']";
        WebElement enableRuleCheckbox = DriverUtil.getElement(By.xpath(enableRuleCheckboxXPath));
        PageLoadHelper.waitForElementClickable(enableRuleCheckbox);
        if (!enableRuleCheckbox.isSelected()) {
            enableRuleCheckbox.click();
            DriverUtil.sleep(10000L);
        }
    }

    private void inputDateField(String elemXPath, String dateString) {
        StepReport.info("Input date string " + dateString);
        WebElement dateElem = DriverUtil.getElement(By.xpath(elemXPath));
        PageLoadHelper.waitForElementClickable(dateElem);
        dateElem.sendKeys(dateString);
        DriverUtil.sleep(3000L);
    }

    private void selectDelegateTo() {
        StepReport.info("Select Delegate To");
        String xpath = "//input[contains(@id, 'vacRuleActionItemRadioDelegate::content')]";
        WebElement radio = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(radio);
        radio.click();
        DriverUtil.sleep(5000L);
    }

    private void inputDelegateUser(String delegateUser) {
        StepReport.info("Input delegate user " + delegateUser);
        String xpath = "//input[contains(@id, 'vacRuledelegateNameInputBox::content')]";
        WebElement input = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(input);
        input.sendKeys(delegateUser);
        DriverUtil.sleep(3000L);
    }

    private void saveVacationRule() {
        StepReport.info("Click save button");
        String xpath = "//button[@title='Save']";
        WebElement button = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(button);
        button.click();
        DriverUtil.sleep(5000L);
    }

    public void typeSearchText(String text) {
        StepReport.info("Type Text");
        PageLoadHelper.waitForElementVisible(searchTextBox);
        DriverUtil.sleep(1000L);
        searchTextBox.clear();
        DriverUtil.sleep(1000L);
        searchTextBox.sendKeys(text);
        DriverUtil.sleep(2000L);
    }

    public void clickSearchButton() {
        StepReport.info("Click Search");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(search);
        DriverUtil.sleep(2000L);
        search.click();
        DriverUtil.sleep(2000L);
    }

    public void clickComposite(String compositeName) {
        StepReport.info("Click on the Composite");
        DriverUtil.sleep(3000L);
        String xPath="//*[text()='"+compositeName+"']";
        WebElement composite=DriverUtil.getElement(By.xpath(xPath));
        PageLoadHelper.waitForElementClickable(composite);
        DriverUtil.sleep(2000L);
        composite.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(assignees);
    }

    public void clickEditTask() {
        StepReport.info("Click Edit Task");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(editTask);
        DriverUtil.sleep(2000L);
        editTask.click();
        DriverUtil.sleep(10000L);
    }

    public void selectStage(String stageName) {
        StepReport.info("Select Stage", stageName);
        DriverUtil.sleep(2000L);
        String xpath = "(//*[text()='"+stageName+"'])[1]";
        WebElement stageElem = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(stageElem);
        DriverUtil.sleep(1000L);
        stageElem.click();
    }

    public void clickAdvanced() {
        StepReport.info("Click Advanced");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(advanced);
        DriverUtil.sleep(2000L);
        advanced.click();
        DriverUtil.sleep(2000L);
    }

    public void clickIgnoreStage() {
        StepReport.info("Click Ignore Stage");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(ignoreStage);
        DriverUtil.sleep(2000L);
        ignoreStage.click();
        DriverUtil.sleep(2000L);
    }

    public void clickSave() {
        StepReport.info("Click Save");
        DriverUtil.sleep(2000L);
        saveButton.click();
        DriverUtil.sleep(2000L);
    }

    public void clickCommentOKButton() {
        StepReport.info("Click OK Button in Enter Comments pop-up");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(commentOK);
        DriverUtil.sleep(2000L);
        commentOK.click();
        DriverUtil.sleep(2000L);
    }

    public void clickInformationOKButton() {
        StepReport.info("Click OK Button in Information pop-up");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(informationOK);
        DriverUtil.sleep(2000L);
        informationOK.click();
        DriverUtil.sleep(5000L);
    }

    public void clickCommitTask() {
        StepReport.info("Click Commit task");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(commitTask);
        DriverUtil.sleep(2000L);
        commitTask.click();
        DriverUtil.sleep(2000L);
    }

    public void clickView(String viewName) {
        StepReport.info("Click View:" +viewName);
        String viewNameXPath = "//a[contains(text(), '" + viewName + "')]";
        WebElement view = DriverUtil.getElement(By.xpath(viewNameXPath));
        PageLoadHelper.waitForElementClickable(view).click();
        DriverUtil.sleep(5000L);
        String addViewImgXPath = "//img[@src='/integration/worklistapp/images/func_add_16_ena.png']";
        PageLoadHelper.waitForElementClickable(DriverUtil.getElement(By.xpath(addViewImgXPath)));
    }

    public void clickUnpinIcon() {
        StepReport.info("Click Unpin Icon");
        String xpath = "//img[@alt='Unpin']";
        WebElement element = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(element).click();
        DriverUtil.sleep(5000L);
        String addViewImgXPath = "//img[@src='/integration/worklistapp/images/func_add_16_ena.png']";
        PageLoadHelper.waitForElementClickable(DriverUtil.getElement(By.xpath(addViewImgXPath)));
    }

    public void clickRefreshPage() {
        StepReport.info("Click Unpin Icon");
        String xpath = "//img[@alt='Refresh Page']";
        WebElement element = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(element).click();
        DriverUtil.sleep(10000L);
        String addViewImgXPath = "//img[@src='/integration/worklistapp/images/func_add_16_ena.png']";
        PageLoadHelper.waitForElementClickable(DriverUtil.getElement(By.xpath(addViewImgXPath)));
    }

    public void clickPinIcon() {
        StepReport.info("Click Pin Icon");
        String xpath = "//img[@alt='Pin']";
        WebElement element = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(element);
        DriverUtil.sleep(5000L);
        String unpinIconXPath = "//img[@alt='Unpin']";
        PageLoadHelper.waitForElementClickable(DriverUtil.getElement(By.xpath(unpinIconXPath)));
    }

    public boolean isUnpinIconPresent() {
        StepReport.info("Test to see whether unpin icon is presented");
        String refreshPageIcon = "//img[@alt='Refresh Page']";
        PageLoadHelper.waitForElementClickable(DriverUtil.getElement(By.xpath(refreshPageIcon)));
        try {
            FrameworkContext.getInstance().getWebDriver().findElement(By.xpath("//img[@alt='Unpin']"));
            StepReport.info("Unpin icon is presented");
            return true;
        } catch (Exception e) {
            StepReport.info("Unpin icon is not presented");
            return false;
        }
    }

    public void clickTaskAccess() {
        StepReport.info("Click task access");
        String xpath = "//span[text()='Access']";
        WebElement accessImg = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(accessImg);
        accessImg.click();
        DriverUtil.sleep(5000L);
    }

    public void selectSignaturePolicy(String policy) {
        StepReport.info("Select signature policy: " + policy);
        DriverUtil.sleep(5000L);
        String xpath = calculateInputXPathByLabel("Signature Policy", "select");
        WebElement signaturePolicy = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(signaturePolicy);
        Select select = new Select(signaturePolicy);
        select.selectByVisibleText(policy);
        DriverUtil.sleep(5000L);
    }

    public void clickDedadlines() {
        StepReport.info("Click Deadlines");
        PageLoadHelper.waitForElementClickable(deadlines);
        DriverUtil.sleep(1000L);
        deadlines.click();
        DriverUtil.sleep(5000L);
    }

    public void clickNotifications() {
        StepReport.info("Click Notifications");
        PageLoadHelper.waitForElementClickable(notifications);
        DriverUtil.sleep(1000L);
        notifications.click();
        DriverUtil.sleep(5000L);
    }

    public void expand(String text){
        StepReport.info("Expand",text);
        String xpath = "//*[text()='"+text+"']/parent::*/parent::*/preceding-sibling::td/a";
        WebElement link = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(link);
        String title = link.getAttribute("title");
        DriverUtil.sleep(2000);
        if(title.equalsIgnoreCase("Expand "+text)) {
            xpath = "//*[text()='"+text+"']/parent::*/parent::*/preceding-sibling::td/a[@title='Expand "+text+"']";
            link = DriverUtil.getElement(By.xpath(xpath));
            link.click();
            DriverUtil.sleep(2000L);
        }
    }

    public void selectTaskLevelRadioButton() {
        StepReport.info("Select Task Level Radio Button");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(taskLevelRadioButton);
        DriverUtil.sleep(2000L);
        taskLevelRadioButton.click();
        DriverUtil.sleep(2000L);
    }

    public void typeExpirationTime(String minutes) {
        StepReport.info("Type Expiration Time");
        PageLoadHelper.waitForElementVisible(expirationSettingsMinutes);
        DriverUtil.sleep(1000L);
        expirationSettingsMinutes.clear();
        DriverUtil.sleep(1000L);
        expirationSettingsMinutes.sendKeys(minutes);
        DriverUtil.sleep(2000L);
        expirationSettingsMinutes.sendKeys(Keys.TAB);
        DriverUtil.sleep(1000L);
    }

    public void selectEsclateRadioButton() {
        StepReport.info("Select Escalate Radio Button");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(escalateRadioButton);
        DriverUtil.sleep(2000L);
        escalateRadioButton.click();
        DriverUtil.sleep(2000L);
    }

    public void typeMaximumEscalationLevels(String levels) {
        StepReport.info("Type Maximum Escalation Levels");
        PageLoadHelper.waitForElementVisible(maximumEscalationLevels);
        DriverUtil.sleep(1000L);
        maximumEscalationLevels.clear();
        DriverUtil.sleep(1000L);
        maximumEscalationLevels.sendKeys(levels);
        DriverUtil.sleep(2000L);
        maximumEscalationLevels.sendKeys(Keys.TAB);
        DriverUtil.sleep(1000L);
    }

    public void selectHighestApprover(String highestApprover) {
        StepReport.info("Select Highest Approver");
        String xpath = "//*[text()='Highest Approver Title']/following::select";
        WebElement highestApproverDropdown = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(highestApproverDropdown);
        Select typeElement = new Select(highestApproverDropdown);
        typeElement.selectByVisibleText(highestApprover);
        DriverUtil.sleep(2000L);
    }

    public void clickOnActionLink() {
        StepReport.info("Clicking on the Actions link: ");
        actionLink.click();
        DriverUtil.sleep(2000L);
    }

    public void switchFrameToDetailsPane() {
        DriverUtil.sleep(2000L);
        StepReport.info("Switching to details pane:");
        WebElement frame=findElement(By.xpath("//iframe[contains(@id,'taskDetailInlineFrame')]"));
        getDriver().switchTo().frame(frame);
        DriverUtil.sleep(2000L);
    }

    public void clickOnActionButton() {
        StepReport.info("Clicking on the Actions button, next to Reject button: ");
        actionButton.click();
        DriverUtil.sleep(2000L);
    }
    public void clickOnReassignLink() {
        StepReport.info("Clicking on the Reassign link: ");
        reassign.click();
        DriverUtil.sleep(2000L);
    }

    public void clickOnRequestInfoLink() {
        StepReport.info("Clicking on the Request Info link: ");
        requestInfoLink.click();
        DriverUtil.sleep(2000L);
    }


    public void selectOtherUserLink() {
        StepReport.info("Clicking on the select Other User link: ");
        selectOtherUser.click();
        DriverUtil.sleep(2000L);
    }

    public void inputOtherUser(String user){
        StepReport.info("Input username in other user field: ");
        otherUserInput.sendKeys(user);
        DriverUtil.sleep(2000L);
    }

    public void enterComments(String comment){
        StepReport.info("Enter comments for the task action: ");
        commentsTextArea.sendKeys(comment);
        DriverUtil.sleep(2000L);
    }

    public void clickOKonReqInfoDlg(){
        StepReport.info("Click OK on request info dialog: ");
        reqInfoOKButton.click();
        DriverUtil.sleep(5000L);
    }



    public boolean isReassignDialogDisplayed(){
        return isElementDisplayed(reassignDialog,"Reassign Dialog");
    }

    public boolean isElementDisplayed(WebElement element, String elementName){
        StepReport.info("Verify " + elementName + " is displayed: ");
        if (PageLoadHelper.waitForElementVisible(element) == null) {
            throw new TestErrorException(element + " not found. " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        return true;
    }

    public void reassignDialogSearchUserName(String user) {
        StepReport.info("reassignDialogSearchUserName: ");
        reassignDialogSearchUserName.sendKeys(user);
        DriverUtil.sleep(2000);
        reassignDialogSearchButton.click();
        DriverUtil.sleep(5000);
    }

    public void clickReassignDialogSearchUserLink(String user) {
        StepReport.info("clickReassignDialogSearchUserLink: ");
        WebElement userLink = DriverUtil.getElement(By.xpath(".//a[text()='" + user + "']"));
        userLink.click();
        DriverUtil.sleep(2000);
    }

    public void clickReassignDialogSearchUserCheckbox(String user) {
        StepReport.info("clickReassignDialogSearchUserCheckbox: ");
        WebElement userCheckbox = DriverUtil.getElement(By.xpath("//*[.='" + user + "']/preceding::input[1]"));
        userCheckbox.click();
        DriverUtil.sleep(2000);
    }

    public void clickOKButton() {
        StepReport.info("clickOKButton: ");
        OKbutton.click();
        DriverUtil.sleep(2000);
    }

    public void clickSubmitInformationLink(){
        StepReport.info("clickSubmitInformationLink: ");
        submitInfo.click();
        DriverUtil.sleep(2000);
    }

    public void clickCreateComment(){
        StepReport.info("clickCreateComment: ");
        createComment.click();
        DriverUtil.sleep(2000);
    }


    public void enterCommentsForSubmitInfo(String comments){
        StepReport.info("enterCommentsForSubmitInfo: "+comments);
        commentTextAreaInInfoSubmit.sendKeys(comments);
        DriverUtil.sleep(2000);
    }

    public boolean verifyTextPresent(String[] comments){
        String textFromTaskComments=commentsForTask.getText();
        StepReport.info("Text from comments area: "+textFromTaskComments);
        int count=0;
        for (String s: comments)
        {
            StepReport.info("verifyTextPresent in the task comments area: "+s);
            if(textFromTaskComments.contains(s))
            {
                StepReport.info("Text found..");
                count++;
            }
        }
        if(comments.length==count)
            return true;
        else
            return false;
    }

    public void selectDoNothingRadioButton() {
        StepReport.info("Select Do Nothing Radio Button");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(doNothingRadioButton);
        DriverUtil.sleep(2000L);
        doNothingRadioButton.click();
        DriverUtil.sleep(2000L);
    }

    public void clickErrorAssigneesEditor() {
        StepReport.info("Click Error Assignees Editor");
        PageLoadHelper.waitForElementClickable(errorAssigneesEditor);
        DriverUtil.sleep(3000L);
        errorAssigneesEditor.click();
        DriverUtil.sleep(5000L);
    }

    public void clickEnableReminder() {
        StepReport.info("Select Enable Reminder Checkbox");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(enableReminder);
        DriverUtil.sleep(2000L);
        enableReminder.click();
        DriverUtil.sleep(2000L);
    }

    public void selectRepeat(String repeatNumber) {
        StepReport.info("Select Number of times for Reminder to repeat", repeatNumber);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(reminderRepeat);
        Select typeElement = new Select(reminderRepeat);
        typeElement.selectByVisibleText(repeatNumber);
        DriverUtil.sleep(2000L);
    }

    public void typeReminderFrequency(String minutes) {
        StepReport.info("Type Reminder Frequency", minutes);
        PageLoadHelper.waitForElementVisible(reminderMinutes);
        DriverUtil.sleep(1000L);
        reminderMinutes.clear();
        DriverUtil.sleep(1000L);
        reminderMinutes.sendKeys(minutes);
        DriverUtil.sleep(2000L);
        reminderMinutes.sendKeys(Keys.TAB);
        DriverUtil.sleep(1000L);
    }

    public void clickAdministrativeTasks() {
        StepReport.info("Click Administrative Tasks");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(administrativeTasks);
        DriverUtil.sleep(2000L);
        administrativeTasks.click();
        DriverUtil.sleep(2000L);
    }

    public void selectFilter(String filterName) {
        StepReport.info("Select Filter",filterName);
        PageLoadHelper.waitForElementVisible(statusFilter);
        Select filterElement = new Select(statusFilter);
        DriverUtil.sleep(2000L);
        filterElement.selectByVisibleText(filterName);
        DriverUtil.sleep(2000L);
    }

    public void typeFilter(String repName) {
        StepReport.info("Type Filter");
        filter.clear();
        DriverUtil.sleep(2000L);
        filter.sendKeys(repName);
        DriverUtil.sleep(2000L);
    }

    public void clickSearch() {
        StepReport.info("Click Search");
        //seachButton.click();
        DriverUtil.sleep(2000L);
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", seachButton);
        DriverUtil.sleep(3000L);
    }

    public boolean verifyIfReportPresent(String repName) {
        StepReport.info("Wait for report : "+repName);

        typeFilter(repName);
        DriverUtil.sleep(5000L);
        clickSearch();
        DriverUtil.sleep(5000L);

        try {
            String element="//a[contains(text(),'"+repName+"')]";
            WebElement report=DriverUtil.getElement(By.xpath(element));
            if(report.isDisplayed()) {
                StepReport.info("Report displayed.");
                return true;
            }else {
                StepReport.info("Report not displayed yet.");
                }
            }catch(Exception e) {
                StepReport.info("Report not displayed yet.");
            }
            DriverUtil.sleep(10000L);
        return false;
    }

}
