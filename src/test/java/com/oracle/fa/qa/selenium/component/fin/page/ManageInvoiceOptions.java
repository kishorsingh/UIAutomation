package com.oracle.fa.qa.selenium.component.fin.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class ManageInvoiceOptions extends BasePageObject<ManageInvoiceOptions>{
	
	@FindBy(xpath = "//*[text()='Save']")
	WebElement save;

	@FindBy(xpath = "//*[@accesskey='C']")
	WebElement cancel;

    @FindBy(xpath = "//*[@accesskey='K']")
    WebElement ok;
	
	@FindBy(xpath = "//*[text()='Enable invoice approval']/preceding::input[1]")
	WebElement invoiceApprovalCheckBox;

	@FindBy(xpath = "//*[text()='Enable invoice approval']/div")
	WebElement invoiceApproval;

    @FindBy(xpath = "//button[contains(@id,'msgDlg::cancel')]")
    WebElement okButton;

	@FindBy(xpath = "//*[text()='Allow force approval']/preceding::input[1]")
	WebElement forceApprovalCheckBox;

	@FindBy(xpath = "//*[text()='Allow force approval']/div")
	WebElement forceApproval;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(cancel) == null) {
            throw new TestErrorException("The Cancel is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public String isInvoiceApprovalSelected() {
		PageLoadHelper.waitForElementVisible(invoiceApprovalCheckBox);
		String attrValue1=String.valueOf(invoiceApprovalCheckBox.getAttribute("checked"));
		String attrValue2=String.valueOf(invoiceApprovalCheckBox.getAttribute("value"));
		System.out.println("checked --> "+attrValue1);
		System.out.println("value --> "+attrValue2);
		StepReport.info("Check Approval Status");
		return attrValue1;
		
	}
	
	public boolean selectInvoiceApproval() {
		String result=isInvoiceApprovalSelected();
		if(result.equals("null")) {
			StepReport.info("Check Approval checkbox");
			StepReport.info("Select invoice Approval Check box");
			PageLoadHelper.waitForElementVisible(invoiceApproval);
			DriverUtil.sleep(2000L);
			DriverUtil.clickByJS(invoiceApproval);
			DriverUtil.sleep(10000L);
			StepReport.info("Click Save");
			DriverUtil.clickByJS(save);
			DriverUtil.sleep(10000L);
	        return true;
		}
		if(result.equals("true")) {
			return true;
		}
		return false;
	}

    public boolean deselectInvoiceApproval() {
        String result=isInvoiceApprovalSelected();
        if(result.equals("true")) {
            StepReport.info("Uncheck Approval checkbox");
            StepReport.info("Deselect invoice Approval Check box");
			PageLoadHelper.waitForElementVisible(invoiceApproval);
			DriverUtil.sleep(2000L);
			DriverUtil.clickByJS(invoiceApproval);
            DriverUtil.sleep(10000L);
            StepReport.info("Click Save");
            DriverUtil.clickByJS(save);
            DriverUtil.sleep(10000L);
            clickWarningOK();
            clickOK();
            return true;
        }
        if(result.equals("null")) {
            return true;
        }
        return false;
    }

    public void clickWarningOK() {
        StepReport.info("Click OK in Warning pop-up");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(ok);
        DriverUtil.sleep(1000L);
        ok.click();
        DriverUtil.sleep(2000L);
    }

    public void clickOK() {
        StepReport.info("Click OK");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(okButton);
        DriverUtil.sleep(1000L);
        okButton.click();
        DriverUtil.sleep(2000L);
    }

	public String isInvoiceForceApprovalSelected() {
		PageLoadHelper.waitForElementVisible(forceApprovalCheckBox);
		String attrValue1=String.valueOf(forceApprovalCheckBox.getAttribute("checked"));
		String attrValue2=String.valueOf(forceApprovalCheckBox.getAttribute("value"));
		System.out.println("checked --> "+attrValue1);
		System.out.println("value --> "+attrValue2);
		StepReport.info("Check Force Approval Status");
		return attrValue1;

	}

	public boolean selectInvoiceForceApproval() {
		String result=isInvoiceForceApprovalSelected();
		if(result.equals("null")) {
			StepReport.info("Check Force Approval checkbox");
			StepReport.info("Select Invoice Force Approval Check box");
			PageLoadHelper.waitForElementVisible(forceApproval);
			DriverUtil.sleep(2000L);
			DriverUtil.clickByJS(forceApproval);
			DriverUtil.sleep(10000L);
			StepReport.info("Click Save");
			DriverUtil.clickByJS(save);
			DriverUtil.sleep(10000L);
			return true;
		}
		if(result.equals("true")) {
			return true;
		}
		return false;
	}
	
	private void waitUntilCancelButtonIsVisible() {
		boolean isCancelButtonDisplayed=true;
		int counter=1;
		while((isCancelButtonDisplayed) && (counter<4)) {
		try {
			if(!cancel.isDisplayed()) {
				isCancelButtonDisplayed=false;
				System.out.println("Cancel Button Not Displayed");
			}else {
				System.out.println("Cancel Button Displayed");
			}
			}catch(Exception e) {
				System.out.println("Cancel Button Not Displayed");
				isCancelButtonDisplayed=false;
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
	   }

}
