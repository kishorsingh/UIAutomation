package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class SetupMaintenance extends BasePageObject<SetupMaintenance>{
	@FindBy(xpath = "//*[text()='Search Tasks']/following::*[@title='Search'][1]")
	WebElement seachIcon;
	
	@FindBy(xpath = "//*[text()='Search Tasks']/following::input[@type='text']")
	WebElement taskInput;
	
	@FindBy(css = "a[id*='soc2::drop']")
	WebElement setupDropdown;
	
	@FindBy(xpath = "//*[text()='Financials']")
	WebElement financials;

	@FindBy(xpath = "//a/img[@title='Tasks']")
	WebElement tasks;

    @FindBy(xpath="//a[text()='Manage Implementation Projects']")
    WebElement manageImplementationProjects;

	@FindBy(xpath="//*[text()='Approval Management']")
	WebElement approvalManagement;

	@FindBy(xpath="//a[text()='Manage Requisition Approvals']")
	WebElement manageRequisitionApprovals;

    @FindBy(xpath="//a[text()='Manage Purchasing Document Approvals']")
    WebElement managePurchasingDocumentApprovals;

    @FindBy(xpath="//button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath="//button[@accesskey='K']")
    WebElement okButton;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(seachIcon) == null) {
            throw new TestErrorException("The search button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void changeSetup(String setupName) {
		StepReport.info("Click setup dropdown");
		DriverUtil.sleep(2000L);
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", setupDropdown);
		DriverUtil.sleep(3000L);
	    StepReport.info("Click financials");
	    DriverUtil.sleep(3000L);
	    executor.executeScript("arguments[0].click();", financials);
	    DriverUtil.sleep(5000L);
	    String xpath="//*[@title='Setup: "+setupName+"']";
	    WebElement setupTitleElem=DriverUtil.getElement(By.xpath(xpath));
	    PageLoadHelper.waitForElementVisible(setupTitleElem);
	}

    public void selectSetup(String setupName) {
        StepReport.info("Click setup dropdown");
        DriverUtil.sleep(2000L);
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", setupDropdown);
        DriverUtil.sleep(3000L);
        StepReport.info("Click SetUp");
        DriverUtil.sleep(3000L);
        String xpath="//*[text()='"+setupName+"']";
        WebElement setupElem=DriverUtil.getElement(By.xpath(xpath));
        executor.executeScript("arguments[0].click();", setupElem);
        DriverUtil.sleep(5000L);
        String xpath1="//*[@title='Setup: "+setupName+"']";
        WebElement setupTitleElem=DriverUtil.getElement(By.xpath(xpath1));
        PageLoadHelper.waitForElementVisible(setupTitleElem);
    }
	
	public void searchTask(String taskName) {
		StepReport.info("Search Task");
		DriverUtil.sleep(2000L);
		taskInput.sendKeys(Keys.TAB);
		DriverUtil.sleep(2000L);
		taskInput.sendKeys(taskName);
		DriverUtil.sleep(2000L);
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", seachIcon);
	    DriverUtil.sleep(5000L);
	    String xpath="//*[text()='"+taskName+"']";
	    WebElement taskElem=DriverUtil.getElement(By.xpath(xpath));
	    PageLoadHelper.waitForElementVisible(taskElem);
	}
	
	public ManageAuditPolicies openTask(String taskName) {
		StepReport.info("Open Task");
		DriverUtil.sleep(2000L);
	    String xpath="//*[text()='"+taskName+"']";
	    WebElement taskElem=DriverUtil.getElement(By.xpath(xpath));
	    PageLoadHelper.waitForElementVisible(taskElem);
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", taskElem);
	    DriverUtil.sleep(3000L);
	    if(taskName.equalsIgnoreCase("Manage Audit Policies")) {
            ManageAuditPolicies manageAuditPolicies = PageFactory.getPage(ManageAuditPolicies.class);
            manageAuditPolicies.isLoaded();
            return manageAuditPolicies;
        }
        else return null;
	}

    public void clickTasks() {
        StepReport.info("Click Tasks");
        DriverUtil.sleep(2000L);
        tasks.click();
        DriverUtil.sleep(5000L);
        PageLoadHelper.waitForElementVisible(manageImplementationProjects);
    }

    public ImplementationProjects clickManageImplementationProjects() {
        StepReport.info("Click Manage Implementation Projects");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(manageImplementationProjects);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(manageImplementationProjects);
        DriverUtil.sleep(2000L);
        ImplementationProjects implementationProjects = PageFactory.getPage(ImplementationProjects.class);
        implementationProjects.isLoaded();
        return implementationProjects;
    }

	public void clickApprovalManagement() {
		StepReport.info("Click Approval Management");
		PageLoadHelper.waitForElementClickable(approvalManagement);
		approvalManagement.click();
		DriverUtil.sleep(5000L);
	}

	public ManageRequisitionApprovals clickManageRequisitionApprovals() {
		StepReport.info("Click Manage Requisition Approvals");
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(manageRequisitionApprovals);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(manageRequisitionApprovals);
        DriverUtil.sleep(10000L);
        acceptRuleUpgrade();
        ManageRequisitionApprovals manageRequisitionApprovals = PageFactory.getPage(ManageRequisitionApprovals.class);
        manageRequisitionApprovals.isLoaded();
        return manageRequisitionApprovals;
	}

    public ManagePurchasingDocumentApprovals clickManagePurchasingDocumentApprovals() {
        StepReport.info("Click Manage Purchasing Document Approvals");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(managePurchasingDocumentApprovals);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(managePurchasingDocumentApprovals);
        DriverUtil.sleep(10000L);
        acceptRuleUpgrade();
        ManagePurchasingDocumentApprovals managePurchasingDocumentApprovals = PageFactory.getPage(ManagePurchasingDocumentApprovals.class);
        managePurchasingDocumentApprovals.isLoaded();
        return managePurchasingDocumentApprovals;
    }

    private void acceptRuleUpgrade() {
        boolean isWarningPopupNotExists=true;
        int counter=1;
        while((isWarningPopupNotExists) && (counter<2)) {
            try {
                if(SelUtil.isDisplayed(yesButton,60)) {
                    StepReport.info("pop up displayed");
                    yesButton.click();
                    DriverUtil.sleep(1000L);
                    if(SelUtil.isDisplayed(okButton,90)) {
                        DriverUtil.clickByJS(okButton);
                    }
                    isWarningPopupNotExists=false;
                }else {
                    System.out.println("Warning Popup not Displayed");
                }
            }catch(Exception e) {
                //System.out.println("Warning Popup not Displayed");
            }
            DriverUtil.sleep(1000L);
            counter++;
        }
    }

}
