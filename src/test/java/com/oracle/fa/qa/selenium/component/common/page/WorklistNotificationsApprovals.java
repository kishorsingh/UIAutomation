package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.hcm.page.HCMBasePageObject;
import com.oracle.fa.qa.selenium.component.crm.page.ApproveContractsPage;
import com.oracle.fa.qa.selenium.component.crm.page.DisputesNotificationPage;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import java.util.Set;
import org.testng.Assert;

public class WorklistNotificationsApprovals extends BasePageObject<WorklistNotificationsApprovals> {

	@FindBy(css ="input[id*='keywordFilter']")
	WebElement filter;
	
	@FindBy(css ="a[id*='searchButton1']")
	WebElement seachButton;
	
	@FindBy(css ="select[id*='statusFilter']")
	WebElement statusFilter;
	
	@FindBy(xpath ="//a[text()='Actions']")
	WebElement actions;

    @FindBy(xpath ="//a/img[@title='Refresh']")
    WebElement refresh;

	@FindBy(xpath ="//a[text()='faadmin']")
	WebElement menu;

	@FindBy(xpath ="//*[text()='Administration']")
	WebElement administration;

	@FindBy(xpath ="//a[text()='Task Configuration']")
	WebElement taskConfiguration;

	@FindBy(xpath ="//*[contains(@id,'taskTypeSearchField::content')]")
	WebElement searchTextBox;

	@FindBy(xpath ="//img[@title='Search task types']")
	WebElement search;

	@FindBy(xpath ="//a/img[@title='Edit task']")
	WebElement editTask;

	@FindBy(xpath ="//a/span[text()='Assignees']")
	WebElement assignees;

	@FindBy(xpath ="//*[@id='stageidInvoice Line A...']")
	WebElement repeatedStage;

    @FindBy(xpath ="//*[text()='Invoice Docume...']")
    WebElement nonRepeatedStage;

	@FindBy(xpath ="//img[@title='Add Rule']")
	WebElement addRule;

    @FindBy(xpath ="//*[@value='Rule 1']/preceding::*[@title='Expand'][1]")
    WebElement expandRule;

    @FindBy(xpath ="//*[contains(@id,'insertActMenuddc:actionButton::popEl')]")
    WebElement addAction;

    @FindBy(xpath ="//*[text()='Add Approver']")
    WebElement addApprover;

    @FindBy(xpath ="//*[text()='Approval Group']")
    WebElement approvalGroup;

    @FindBy(xpath ="//*[text()='Dimension Id']/following::input[1]")
    WebElement dimensionID;

    @FindBy(xpath ="//img[@title='Delete Rule']")
    WebElement deleteRule;

    @FindBy(xpath ="//img[@title='Reset']")
    WebElement revert;


	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(filter) == null) {
            throw new TestErrorException("The filter is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void typeFilter(String repName) {
		StepReport.info("Type Filter");
		filter.clear();
		DriverUtil.sleep(2000L);
		filter.sendKeys(repName);
		DriverUtil.sleep(2000L);
	}



	public void clickMenu() {
		StepReport.info("Click Menu");
		DriverUtil.sleep(3000L);
		PageLoadHelper.waitForElementClickable(menu);
		DriverUtil.sleep(2000L);
		menu.click();
		DriverUtil.sleep(2000L);
	}

	public void clickAdministration() {
		StepReport.info("Click Administration");
		DriverUtil.sleep(3000L);
		PageLoadHelper.waitForElementClickable(administration);
		DriverUtil.sleep(2000L);
		administration.click();
		DriverUtil.sleep(2000L);
	}

	public void clickTaskConfiguration() {
		StepReport.info("Click TaskConfiguration");
		DriverUtil.sleep(3000L);
		PageLoadHelper.waitForElementClickable(taskConfiguration);
		DriverUtil.sleep(2000L);
		taskConfiguration.click();
		DriverUtil.sleep(2000L);
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
	}

	public void clickEditTask() {
		StepReport.info("Click Edit Task");
		DriverUtil.sleep(3000L);
		PageLoadHelper.waitForElementClickable(editTask);
		DriverUtil.sleep(2000L);
		editTask.click();
		DriverUtil.sleep(5000L);
	}

	public void clickAssignees() {
		StepReport.info("Click Assignees");
		DriverUtil.sleep(3000L);
		PageLoadHelper.waitForElementClickable(assignees);
		DriverUtil.sleep(2000L);
		assignees.click();
		DriverUtil.sleep(2000L);
	}

	public void clickRepeatingStage() {
		StepReport.info("Click Repeating Stage");
		DriverUtil.sleep(3000L);
		PageLoadHelper.waitForElementClickable(repeatedStage);
		DriverUtil.sleep(2000L);
        repeatedStage.click();
		DriverUtil.sleep(2000L);
	}

    public void selectCollections(String collectionType) {
        StepReport.info("Select Collection type");
        DriverUtil.sleep(2000L);
        String xpath = "//*[contains(@id,'collections::content')]";
        WebElement collections = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(collections);
        DriverUtil.sleep(1000L);
        Select typeSelect = new Select(collections);
        typeSelect.selectByVisibleText(collectionType);
        DriverUtil.sleep(2000L);
    }

    public void clickNonRepeatingStage() {
        StepReport.info("Click  Non Repeating Stage");
        DriverUtil.sleep(3000L);
        Actions ac = new Actions(FrameworkContext.getInstance().getWebDriver());
        PageLoadHelper.waitForElementClickable(nonRepeatedStage);
        DriverUtil.scrollIntoView(nonRepeatedStage);
        ac.moveToElement(nonRepeatedStage).build().perform();
        DriverUtil.sleep(2000L);
        nonRepeatedStage.click();
        DriverUtil.sleep(2000L);
    }

	public void goToRuleRepeated() {
        StepReport.info("Click Rule Icon");
        DriverUtil.sleep(3000L);
        String xpathRuleIcon = "//*[@id='rule-icon-5']";
        WebElement ruleIcon = DriverUtil.getElement(By.xpath(xpathRuleIcon));
        PageLoadHelper.waitForElementClickable(ruleIcon);
        DriverUtil.sleep(2000L);
        ruleIcon.click();
        DriverUtil.sleep(2000L);
		StepReport.info("Click Go To Rule");
        String xpathGoToRule = "//*[@id='rule-icon-5']/following::*[text()='Go to rule'][1]";
        WebElement goToRule = DriverUtil.getElement(By.xpath(xpathGoToRule));
		PageLoadHelper.waitForElementClickable(goToRule);
		DriverUtil.sleep(1000L);
		goToRule.click();
		DriverUtil.sleep(2000L);
	}

    public void goToRuleNonRepeated() {
        StepReport.info("Click Rule Icon");
        DriverUtil.sleep(3000L);
        String xpathRuleIcon = "//*[@id='rule-icon-19']";
        WebElement ruleIcon = DriverUtil.getElement(By.xpath(xpathRuleIcon));
        PageLoadHelper.waitForElementClickable(ruleIcon);
        DriverUtil.sleep(2000L);
        ruleIcon.click();
        DriverUtil.sleep(2000L);
        StepReport.info("Click Go To Rule");
        String xpathGoToRule = "//*[@id='rule-icon-19']/following::*[text()='Go to rule'][11]";
        WebElement goToRule = DriverUtil.getElement(By.xpath(xpathGoToRule));
        PageLoadHelper.waitForElementClickable(goToRule);
        DriverUtil.sleep(1000L);
        goToRule.click();
        DriverUtil.sleep(2000L);
    }

	public void clickAddRule() {
		StepReport.info("Click Add Rule");
		DriverUtil.sleep(3000L);
		PageLoadHelper.waitForElementClickable(addRule);
		DriverUtil.sleep(2000L);
		addRule.click();
		DriverUtil.sleep(2000L);
	}

    public void clickExpandRule() {
        StepReport.info("Click Expand Rule");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(expandRule);
        DriverUtil.sleep(2000L);
        expandRule.click();
        DriverUtil.sleep(2000L);
    }

    public void clickAddAction() {
        StepReport.info("Click Add Action");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(addAction);
        DriverUtil.sleep(2000L);
        addAction.click();
        DriverUtil.sleep(2000L);
    }

    public void clickAddApprover() {
        StepReport.info("Click Add Approver");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(addApprover);
        DriverUtil.sleep(2000L);
        addApprover.click();
        DriverUtil.sleep(2000L);
    }

    public void clickApprovalGroup() {
        StepReport.info("Click Approval Group");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(approvalGroup);
        DriverUtil.sleep(2000L);
        approvalGroup.click();
        DriverUtil.sleep(2000L);
    }

    public String getDimensionID() {
        StepReport.info("Get Dimension ID");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(dimensionID);
        DriverUtil.sleep(2000L);
        return dimensionID.getAttribute("value").trim();
    }

    public void deleteRule(String ruleName) {
        StepReport.info("Delete Rule");
        DriverUtil.sleep(3000L);
        String xpath = "//*[@value='"+ruleName+"']/preceding::input[1]";
        WebElement ruleCheckBox = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(ruleCheckBox);
        DriverUtil.sleep(2000L);
        ruleCheckBox.click();
        DriverUtil.sleep(2000L);
        deleteRule.click();
        DriverUtil.sleep(5000L);
    }

    public void clickRevert() {
        StepReport.info("Click Revert");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(revert);
        DriverUtil.sleep(2000L);
        revert.click();
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

	public void searchRequestUntilAppear(String repName) {
		for(int i=0; i<10; i++) {
			this.typeFilter(repName);
			this.clickSearch();
			String element = "//a[contains(text(),'"+repName+"')]";
			if(DriverUtil.isElementPresent(By.xpath(element))) {
				break;
			}
			StepReport.info("Retry to search : "+repName);
			DriverUtil.sleep(10000L);
		}
	}
	
	public void waitForReportOnGrid(String repName) {
		StepReport.info("Wait for report : "+repName);
		boolean isReportNotDisplayed=true;
		int counter=1;
		while((isReportNotDisplayed) && (counter<6)) {
			try {
			String element="//a[contains(text(),'"+repName+"')]";
			WebElement report=DriverUtil.getElement(By.xpath(element));
			if(report.isDisplayed()) {
				isReportNotDisplayed=false;
				StepReport.info("Report displayed.");
			}else {
				StepReport.info("Report not displayed yet.");
                DriverUtil.clickByJS(refresh);
                refreshPage();
				StepReport.info("Page refreshed.");
			}
			}catch(Exception e) {
				StepReport.info("Report not displayed yet.");
				DriverUtil.clickByJS(refresh);
				StepReport.info("Page refreshed.");
			}
			DriverUtil.sleep(10000L);
			counter++;
		}
	}

	public void clickOnExpenseReport(String repName) {
		StepReport.info("Click on Expense Report "+repName);
		DriverUtil.sleep(8000L);
		String element = "//a[contains(text(),'"+repName+"')]";
		WebElement report;

		try {
			report = DriverUtil.getElement(By.xpath(element));
		}
		catch (Exception e){
			report = findElement(By.xpath(element));
		}
		//PageLoadHelper.waitForElementClickable(report);
		//DriverUtil.sleep(4000L);
		//report.click();
	    JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", report);
	    StepReport.info("Clicked Report");
	//	Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
    //    Action mouseClick = builder.doubleClick(report).build();
    //    mouseClick.perform();
		DriverUtil.sleep(3000L);
	}
	
	public void selectStatus(String statusText) {
		StepReport.info("Select Status "+statusText);
		DriverUtil.sleep(2000L);
		Select statusSelect = new Select(statusFilter);
		statusSelect.selectByVisibleText(statusText);
	}
	
	public boolean verifyReportExists(String repName) {
		StepReport.info("Verify Report Exists: "+repName);
		DriverUtil.sleep(4000L);
		String element="//a[contains(text(),'"+repName+"')]";
		WebElement report=DriverUtil.getElement(By.xpath(element)); 
		if(report.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public WorklistNotificationsApprovals refreshPage() {
		StepReport.info("Refresh Page");
		FrameworkContext.getInstance().getWebDriver().navigate().refresh();
		WorklistNotificationsApprovals worklistNotificationsApprovals = PageFactory.getPage(WorklistNotificationsApprovals.class);
		worklistNotificationsApprovals.isLoaded();
		return worklistNotificationsApprovals;
	}
	
	public ReassignTask clickReAssign(String reportName) {
		StepReport.info("Select Report");
		String xPath="//*[contains(text(),'"+reportName+"')]/following::*[text()='Assigned'][1]";
		WebElement reportLine=DriverUtil.getElement(By.xpath(xPath));
		reportLine.click();
		DriverUtil.sleep(10000L);
		StepReport.info("Click Actions -> Reassign");
		int counter=1;
		boolean isReAssignNotFound=true;
		WebElement reAssignElem=null;
		while((counter<10) && (isReAssignNotFound)) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		    executor.executeScript("arguments[0].click();", actions);
			DriverUtil.sleep(3000L);
			String reAssignXPath="//*[contains(text(),'Reassign')]";
			reAssignElem=DriverUtil.getElement(By.xpath(reAssignXPath));
			if(reAssignElem.getText()!=null) {
				isReAssignNotFound=false;
			}
			}catch(Exception e) {
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
		StepReport.info("Click Reassign");
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", reAssignElem);
		ReassignTask reassignTask=PageFactory.getPage(ReassignTask.class);
		reassignTask.isLoaded();
		return reassignTask;
	}
	
	public void clickSuspend(String reportName) {
		StepReport.info("Select Report");
		String xPath="//*[contains(text(),'"+reportName+"')]/following::*[text()='Assigned'][1]";
		WebElement reportLine=DriverUtil.getElement(By.xpath(xPath));
		reportLine.click();
		DriverUtil.sleep(10000L);
		StepReport.info("Click Actions -> suspend");
		int counter=1;
		boolean issuspendNotFound=true;
		WebElement suspendElem=null;
		while((counter<10) && (issuspendNotFound)) {
		try {
			actions.click();
			DriverUtil.sleep(3000L);
			String suspend="//*[text()='Suspend']";
			suspendElem=DriverUtil.getElement(By.xpath(suspend));
			if(suspendElem.isDisplayed()) {
				issuspendNotFound=false;
			}
			}catch(Exception e) {
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        Action mouseClick = builder.click(suspendElem).build();
        mouseClick.perform();
        DriverUtil.sleep(3000L);
	}
	
	public void clickResume(String reportName) {
		StepReport.info("Select Report");
		String xPath="//*[contains(text(),'"+reportName+"')]/following::*[text()='Suspended'][1]";
		WebElement reportLine=DriverUtil.getElement(By.xpath(xPath));
		reportLine.click();
		DriverUtil.sleep(10000L);
		StepReport.info("Click Actions -> Resume");
		int counter=1;
		boolean isResumeNotFound=true;
		WebElement resumeElem=null;
		while((counter<10) && (isResumeNotFound)) {
		try {
			actions.click();
			DriverUtil.sleep(3000L);
			String resume="//*[text()='Resume']";
			resumeElem=DriverUtil.getElement(By.xpath(resume));
			if(resumeElem.isDisplayed()) {
				isResumeNotFound=false;
			}
			}catch(Exception e) {
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        Action mouseClick = builder.click(resumeElem).build();
        mouseClick.perform();
        DriverUtil.sleep(3000L);
	}

	public void clickClaim(String partnerName) {
		StepReport.info("Select Report");
		String xPath="//*[contains(text(),'"+partnerName+"')]/following::*[text()='Assigned'][1]";
		WebElement reportLine=DriverUtil.getElement(By.xpath(xPath));
		reportLine.click();
		DriverUtil.sleep(10000L);
		StepReport.info("Click Actions -> Claim");
		int counter=1;
		boolean isClaimNotFound=true;
		WebElement claimElem=null;
		while((counter<10) && (isClaimNotFound)) {
			try {
				actions.click();
				DriverUtil.sleep(3000L);
				String claim="//*[text()='Claim']";
				claimElem=DriverUtil.getElement(By.xpath(claim));
				if(claimElem.isDisplayed()) {
					isClaimNotFound=false;
				}
			}catch(Exception e) {
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
		Action mouseClick = builder.click(claimElem).build();
		mouseClick.perform();
		DriverUtil.sleep(6000L);
	}

	public void clickApprove(String taskName) {
		StepReport.info("Select Report");
		String xPath="//*[contains(text(),'"+taskName+"')]/following::*[text()='Assigned'][1]";
		WebElement reportLine=DriverUtil.getElement(By.xpath(xPath));
		reportLine.click();
		DriverUtil.sleep(10000L);
		StepReport.info("Click Actions -> Approve");
		int counter=1;
		boolean isApproveNotFound=true;
		WebElement approveElem=null;
		while((counter<10) && (isApproveNotFound)) {
			try {
				actions.click();
				DriverUtil.sleep(3000L);
				String approve="//*[text()='Approve']";
				approveElem=DriverUtil.getElement(By.xpath(approve));
				if(approveElem.isDisplayed()) {
					isApproveNotFound=false;
				}
			}catch(Exception e) {
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
		Action mouseClick = builder.click(approveElem).build();
		mouseClick.perform();
		DriverUtil.sleep(10000L);
	}

    public void verifyTransactionStatusInWorkList(String transactionName,String transactionStatus){
        Select filterSelect =new Select(statusFilter);
        filterSelect.selectByVisibleText(transactionStatus);
        filter.sendKeys(transactionName);
        seachButton.click();
        DriverUtil.sleep(3000);
        Assert.assertTrue(findElements(By.xpath("//a[contains(text(),'Employee87')]")).size()>0,"Transaction is in "+transactionStatus+"");
        revertToAssignedTaks();
    }
    public void selectSuspendedTaks(String transactionStatus){
        Select filterSelect =new Select(statusFilter);
        filterSelect.selectByVisibleText(transactionStatus);
        DriverUtil.sleep(4000);
    }
    public void revertToAssignedTaks() {
        for (int i = 0; i < 3; i++) {
            try{
                Select filterSelect = new Select(statusFilter);
                filterSelect.selectByVisibleText("Assigned");
                break;
            }
            catch (Exception e){

		}
	}

		DriverUtil.sleep(4000);
	}
	public void switchToInitiatedTasks(){
		findElement(By.xpath("//a[text()='My Tasks']")).click();
		DriverUtil.sleep(2000);
		findElement(By.xpath("//a[text()='Initiated Tasks ']")).click();
		DriverUtil.sleep(4000);
	}
	public void switchToMyTasks(){
		for(int i =0;i<3;i++){
			try{
				findElement(By.xpath("//a[text()='Initiated Tasks']")).click();
				DriverUtil.sleep(2000);
				findElement(By.xpath("//a[contains(text()='My Tasks')]")).click();
				DriverUtil.sleep(4000);
				break;
			}
			catch (Exception e){

			}

		}


	}

	public DisputesNotificationPage clickOnDisputeNotification(String repName) {
		StepReport.info("Click On Dispute Notification Report "+repName);
		DriverUtil.sleep(10000L);
		String element = "//a[contains(text(),'" + repName + "')]";
		WebElement report;

		try {
			report = DriverUtil.getElement(By.xpath(element));
		}
		catch (Exception e){
			report = findElement(By.xpath(element));
		}

		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", report);
		DriverUtil.sleep(3000L);
		DisputesNotificationPage disputesNotificationPage = PageFactory.getPage(DisputesNotificationPage.class);
		disputesNotificationPage.isLoaded();
		return disputesNotificationPage;

	}

	public ApproveContractsPage clickOnContractReport(String repName) {
		StepReport.info("Click On Dispute Notification Report "+repName);
		DriverUtil.sleep(10000L);
		String element = "//a[contains(text(),'" + repName + "')]";
		WebElement report;

		try {
			report = DriverUtil.getElement(By.xpath(element));
		}
		catch (Exception e){
			report = findElement(By.xpath(element));
		}

		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", report);
		DriverUtil.sleep(3000L);
		ApproveContractsPage approveContractsPage = PageFactory.getPage(ApproveContractsPage.class);
		approveContractsPage.isLoaded();
		return approveContractsPage;

	}

	public boolean verifyIfReportPresentOnGrid(String repName) {
		StepReport.info("Wait for report : "+repName);
		boolean isReportNotDisplayed=true;
		int counter=1;
		while((isReportNotDisplayed) && (counter<2)) {
			try {
				String element="//a[contains(text(),'"+repName+"')]";
				WebElement report=DriverUtil.getElement(By.xpath(element));
				if(report.isDisplayed()) {
					isReportNotDisplayed=false;
					StepReport.info("Report displayed.");
					return true;
				}else {
					StepReport.info("Report not displayed yet.");
					DriverUtil.clickByJS(refresh);
					refreshPage();
					StepReport.info("Page refreshed.");
				}
			}catch(Exception e) {
				StepReport.info("Report not displayed yet.");
				DriverUtil.clickByJS(refresh);
				StepReport.info("Page refreshed.");
			}
			DriverUtil.sleep(10000L);
			counter++;
		}
		return false;
	}


}
