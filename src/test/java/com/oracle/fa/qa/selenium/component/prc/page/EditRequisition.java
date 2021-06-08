package com.oracle.fa.qa.selenium.component.prc.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.common.page.Attachment;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class EditRequisition extends BasePageObject<EditRequisition>{
	@FindBy(xpath ="//*[@accesskey='m' or @accesskey='M']")
	WebElement submit;
	
	@FindBy(xpath ="//*[contains(@id,'a1edreq:clLAdds::icon')]")
	WebElement attachment;

	@FindBy(xpath = "//span[text()='Manage Approvals']")
	WebElement manageApprovals;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(submit) == null) {
            throw new TestErrorException("The submit field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public ManageApprovals clickManageApprovals() {
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(manageApprovals);
		DriverUtil.sleep(2000L);
		ManageApprovals manageApprovals = PageFactory.getPage(ManageApprovals.class);
		manageApprovals.isLoaded();
		return manageApprovals;
	}
	
	public Confirmation clickSubmit() {
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(submit);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(submit);
        Confirmation confirmation = PageFactory.getPage(Confirmation.class);
        confirmation.isLoaded();
		return confirmation;
	}

	public String getRequisitionNumber() {
		DriverUtil.sleep(2000L);
        String xpath="//*[contains(text(),'Edit Requisition:')]";
        WebElement editRequisitionElem=DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(editRequisitionElem);
        DriverUtil.sleep(2000L);
        String reqNumber=editRequisitionElem.getText();
        reqNumber=reqNumber.replace("Edit Requisition:", "");
        reqNumber=reqNumber.trim();
        return reqNumber;
	}
	public Attachment clickAttachment() {
		StepReport.info("Click Attachment");
		DriverUtil.sleep(4000L);
		attachment.click();
		DriverUtil.sleep(2000L);
		Attachment attachment = PageFactory.getPage(Attachment.class);
		attachment.isLoaded();
        return attachment;
	}
	
	public void waitForAttachmentToDisplay(String fileName) {
		String xpath="//*[text()='"+fileName+"']";
		WebElement attachedFile=DriverUtil.getElement(By.xpath(xpath)); 
		PageLoadHelper.waitForElementVisible(attachedFile);
	}


}
