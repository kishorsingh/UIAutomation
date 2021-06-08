package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RequisitionPage extends BasePageObject<RequisitionPage>{

	@FindBy(xpath ="//h1[contains(text(),'Requisition: ')]")
	WebElement requisitionPageTitle;

	@FindBy(xpath ="//*[contains(@id,'CancelMenuItem')]/td[2]")
	WebElement cancelRequisition;

	@FindBy(xpath ="//a[@title='Actions']")
	WebElement clickActions;

	@FindBy(xpath ="//textarea[contains(@id,'AP1:it1::content')]")
	WebElement addComments;

	@FindBy(xpath ="//button[contains(@id,'cancelReasonDialog::ok')]")
	WebElement clickOK;
	@FindBy(xpath ="//*[@id='d1::msgDlg::_ttxt']")
	WebElement confirmation;

	@FindBy(xpath ="//*[@id='d1::msgDlg::cancel']")
	WebElement confirmCancel;

	@FindBy(xpath ="//*[contains(@id,'my_information_purchase_requisitions:0:MAnt2:1:pt1:r1:1:AP1:SPb')]/a/span")
	WebElement clickDone;

	@FindBy(xpath ="//*[contains(@id,'my_information_purchase_requisitions:0:MAnt2:1:pt1:r1:0:ap1:SPb')]/a")
	WebElement clickDone2;

	@FindBy(xpath = "//label[text()='Status']/parent::td/following::td//a")
	WebElement statusLink;

	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(requisitionPageTitle) == null) {
            throw new TestErrorException("The Search Input field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
            }
		StepReport.info("RequisitionPage is loaded");
    }


    public void gotoActionDetail() {

	}

	public void clickCancelRequistion(){
		StepReport.info("Click Actions");
		DriverUtil.sleep(2000L);
		clickActions.click();
		DriverUtil.sleep(2000L);
		cancelRequisition.click();
		DriverUtil.sleep(2000L);
		addComments.sendKeys("Requistion Cancelled by cvrqst01");
		DriverUtil.sleep(2000L);
		StepReport.info("Add comments and clik OK to cancel");
		clickOK.click();
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(confirmation);
		DriverUtil.sleep(2000L);
		StepReport.info("Confrim OK to cancel requistion");
		confirmCancel.click();
		DriverUtil.sleep(2000L);
		StepReport.info("Confrim Done after requistion is cancelled");
		clickDone.click();
		DriverUtil.sleep(2000L);
		StepReport.info("Confrim Done On Manage Requistion page");
		clickDone2.click();
		DriverUtil.sleep(2000L);
	}

}
