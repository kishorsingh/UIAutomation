package com.oracle.fa.qa.selenium.component.prc.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.prc.page.AddComment;
import com.oracle.fa.qa.selenium.component.fin.page.RequestMoreInformation;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class POReport extends BasePageObject<POReport>{

	@FindBy(xpath = "//*[text()='Actions']")
	WebElement actions;
	
	
	@FindBy(xpath = "//*[text()='Approve']")
	WebElement approve;
	
	@FindBy(xpath = "//*[text()='Reject']")
	WebElement reject;

	@FindBy(xpath = "//*[text()='Dismiss']")
	WebElement dismiss;
	
	@FindBy(xpath = "//*[@id='adCmtBt::icon']")
	WebElement addComment;
	
	@FindBy(xpath = "//*[contains(text(),'requestInfo1')]")
	WebElement reqInfoComment;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(actions) == null) {
            throw new TestErrorException("The select actions is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void clickApprove() {
		StepReport.info("Click Approve");
		actions.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        Action mouseClick = builder.click(approve).build();
        mouseClick.perform();
        SelUtil.waitUntilPageClosed();        
	}

	public ApprovePO clickApprovePO() {
		StepReport.info("Click Approve");
		DriverUtil.sleep(2000L);
		approve.click();
		ApprovePO approvePO=PageFactory.getPage(ApprovePO.class);
		approvePO.isLoaded();
		return approvePO;
	}

	public ApprovePO clickRejectPO() {
		StepReport.info("Click Reject");
		DriverUtil.sleep(2000L);
		reject.click();
		ApprovePO approvePO=PageFactory.getPage(ApprovePO.class);
		approvePO.isLoaded();
		return approvePO;
	}

	public SubmitPO clickSubmitInfo() {
		clickActions();
		DriverUtil.sleep(3000L);
		StepReport.info("Click Submit Information");
		String xpath="//*[contains(text(),'Submit Information')]";
		WebElement submitInfoElem=DriverUtil.getElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", submitInfoElem);
		DriverUtil.sleep(3000L);
		SubmitPO submitPO=PageFactory.getPage(SubmitPO.class);
		submitPO.isLoaded();
		return submitPO;
	}

	
	public void clickReject() {
		StepReport.info("Click Reject");
		actions.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        Action mouseClick = builder.click(reject).build();
        mouseClick.perform();
        SelUtil.waitUntilPageClosed();          
	}

	public void doAction(String action) {
		StepReport.info("Click Action: " + action);
		WebElement actionEle = DriverUtil.getElement(By.xpath("//*[text()='"+action+"']"));

		actions.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
		Action mouseClick = builder.click(actionEle).build();
		mouseClick.perform();
		SelUtil.waitUntilPageClosed();
	}
	
	public boolean IsAttachmentExists(String fileName){
		String xpath="//*[contains(text(),'"+fileName+"')]";
		WebElement attachedFile=DriverUtil.getElement(By.xpath(xpath)); 
		return attachedFile.isDisplayed();
	}
	
	public void clickActions() {
		StepReport.info("Click Actions");
		actions.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", actions);
        DriverUtil.sleep(3000L);
	}
	
	public RequestMoreInformation clickRequestInfo() {
		StepReport.info("Click Request More indo");
		clickActions();
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        String xpath="//*[contains(text(),'Request Information')]";
		WebElement reqInfoElem=DriverUtil.getElement(By.xpath(xpath)); 
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", reqInfoElem);
		DriverUtil.sleep(3000L);
		RequestMoreInformation requestMoreInformation = PageFactory.getPage(RequestMoreInformation.class);
		requestMoreInformation.isLoaded();
		return requestMoreInformation;
	}
	
	public AddComment clickAddComment() {
		StepReport.info("Add Comment");
		PageLoadHelper.waitForElementClickable(addComment);
		addComment.click();
		DriverUtil.sleep(3000L);
		AddComment AddComment=PageFactory.getPage(AddComment.class);
		AddComment.isLoaded();
		return AddComment;
	}


	public AddComment provideInfoComments() {
		StepReport.info("Click Add Comments ");
		clickActions();
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
		String xpath="//*[contains(text(),'Add Comments')]";
		WebElement reqInfoElem=DriverUtil.getElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", reqInfoElem);
		DriverUtil.sleep(3000L);
		AddComment AddComment=PageFactory.getPage(AddComment.class);
		AddComment.isLoaded();
		return AddComment;
	}


	public void clickSubmitInformation() {
		clickActions();
        DriverUtil.sleep(3000L);
        StepReport.info("Click Submit Information");
        String xpath="//*[contains(text(),'Submit Information')]";
		WebElement submitInfoElem=DriverUtil.getElement(By.xpath(xpath)); 
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", submitInfoElem);
		DriverUtil.sleep(3000L);
		System.out.println("Wait for Page to Close");
		SelUtil.waitUntilPageClosed();
	}

	public ReassignTask clickReassign() {
		StepReport.info("Click Reassign...");
		clickActions();
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
		String xpath="//*[contains(text(),'Reassign')]";
		WebElement reassignElem=DriverUtil.getElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", reassignElem);
		DriverUtil.sleep(3000L);
		ReassignTask reassignTask = PageFactory.getPage(ReassignTask.class);
		reassignTask.isLoaded();
		return reassignTask;
	}


    public ReassignTask clickDelegate() {
        StepReport.info("Click Delegate");
        clickActions();
        Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        String xpath="//*[contains(text(),'Delegate')]";
        WebElement reassignElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", reassignElem);
        DriverUtil.sleep(3000L);
        ReassignTask reassignTask = PageFactory.getPage(ReassignTask.class);
        reassignTask.isLoaded();
        return reassignTask;
    }

}
