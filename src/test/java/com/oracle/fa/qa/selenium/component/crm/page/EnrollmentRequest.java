package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.common.page.AddComment;
import com.oracle.fa.qa.selenium.component.common.page.AdhocRoute;
import com.oracle.fa.qa.selenium.component.fin.page.InvoiceReport;
import com.oracle.fa.qa.selenium.component.fin.page.RequestMoreInformation;
import com.oracle.fa.qa.selenium.component.crm.page.ReassignTask;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class EnrollmentRequest extends BasePageObject<EnrollmentRequest> {

    @FindBy(xpath = "//*[text()='Actions']")
    WebElement actions;


    @FindBy(xpath = "//*[text()='Approve']")
    WebElement approve;

    @FindBy(xpath = "//*[text()='Reject']")
    WebElement reject;

    @FindBy(xpath = "//a[@title='Create']")
    WebElement addComment;

    @FindBy(xpath = "//*[contains(text(),'requestInfo1')]")
    WebElement reqInfoComment;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(actions) == null) {
            throw new TestErrorException("The select require approval is not clickable after " +
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

    public void clickWithdraw() {
        clickActions();
        StepReport.info("Click Withdraw");
        String xpath="//*[contains(text(),'Withdraw')]";
        WebElement withdrawElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", withdrawElem);
        SelUtil.waitUntilPageClosed();
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

    public RequestMoreInfo clickRequestInfo() {
        StepReport.info("Click Request More indo");
        clickActions();
        DriverUtil.sleep(3000L);
        String xpath="//*[contains(text(),'Request Information')]";
        WebElement reqInfoElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", reqInfoElem);
        DriverUtil.sleep(3000L);
        RequestMoreInfo requestMoreInformation = PageFactory.getPage(RequestMoreInfo.class);
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

    public void clickSave() {
        clickActions();
        DriverUtil.sleep(3000L);
        StepReport.info("Click Save");
        String xpath="//*[contains(text(),'Save')]";
        WebElement saveElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", saveElem);
        DriverUtil.sleep(3000L);
        System.out.println("Wait for Page to Close");
        SelUtil.waitUntilPageClosed();
    }

    public AdhocRoute clickAdhocRoute() {
        clickActions();
        StepReport.info("Click Adhoc Route");
        String xpath="//*[contains(text(),'Adhoc Route')]";
        WebElement adhocRouteElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", adhocRouteElem);
        DriverUtil.sleep(3000L);
        AdhocRoute adhocRoute = PageFactory.getPage(AdhocRoute.class);
        adhocRoute.isLoaded();
        return adhocRoute;
    }

    public ReassignTask clickReassign() {
        StepReport.info("Click Reassign...");
        clickActions();
        Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        String xpath="//*[contains(text(),'Reassign...')]";
        WebElement reassignElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", reassignElem);
        DriverUtil.sleep(3000L);
        ReassignTask reassignTask = PageFactory.getPage(ReassignTask.class);
        reassignTask.isLoaded();
        return reassignTask;
    }


}
