package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ManageOrder extends BasePageObject<ManageOrder>{


	@FindBy(xpath ="//h1[contains(text(),'Manage Orders')]")
	WebElement manageOrders;

	@FindBy(xpath ="(//*[text()='Order']/following::input)[1]")
	WebElement inputOrderNumber;

	@FindBy(xpath ="//*[contains(@id,'search')]")

	WebElement clickSearch;

	@FindBy(xpath ="//*[@id='pt1:_FOr1:1:_FOSritemNode_procurement_PurchaseOrders:0:MAt3:0:pt1:Purch1:0:AP1:r1:0:AT1:_ATp:table1:0:ot12']")
	WebElement clickPO;

	@FindBy(xpath ="//a[text()='Actions']")
	WebElement clickActions;

	@FindBy(xpath ="//*[text()='Cancel Document']")
	WebElement clickCancelDocument;

	@FindBy(xpath ="(//*[text()='Warning']/following::button[text()='Yes'])[3]")
	WebElement clickOKWarning;

	@FindBy(xpath ="//*[text()='Reason']/following::textarea")
	WebElement cancelReason;

	@FindBy(xpath ="//*[text()='Reason']/following::button[@accesskey='K']")
	WebElement clickOKCancelDocument;

	@FindBy(xpath ="//*[text()='Confirmation']")
	WebElement confirmation;

	@FindBy(xpath ="(//*[text()='Confirmation']/following::button[text()='OK'])[1]")
	WebElement confirmCancel;

	@FindBy(xpath ="//*[text()='Canceled']")
	WebElement cancelStatus;

	@FindBy(xpath ="//*[@id='pt1:_FOr1:1:_FOSritemNode_procurement_PurchaseOrders:0:MAt2:0:pt1:Purch1:0:AP1:SPb']/a']")
	WebElement clickOnDone;


	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(manageOrders) == null) {
            throw new TestErrorException("The Create Button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Manage Order Page is loaded");
    }
	
    public void searchPurchaseOrder(String OrderNum){
		DriverUtil.sleep(2000L);
		inputOrderNumber.sendKeys(OrderNum);
		StepReport.info("Enter Purchase Order Number to search",OrderNum);
		DriverUtil.sleep(3000L);
		clickSearch.click();
		String element="//*[text()='Order']/following::*[text()='"+OrderNum+"']";
		WebElement orderElem=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementVisible(orderElem);
	}

	public void cancelPurchaseOrder(){
		StepReport.info("Click Actions To Cancel Purchase Order");
		DriverUtil.sleep(2000L);
		clickActions.click();
		DriverUtil.sleep(2000L);
		StepReport.info("Click Cancel Document");
		clickCancelDocument.click();
		DriverUtil.sleep(2000L);
	}

	public void clickOKWarning(){
		StepReport.info("Click OK on Warning Page");
		DriverUtil.sleep(2000L);
		clickOKWarning.click();
	}

	public void typeCancelReason(){
		StepReport.info("Type Cancel Reason");
		cancelReason.sendKeys("PO Cancelled by cvbuyer01");
		DriverUtil.sleep(2000L);
		clickOKCancelDocument.click();
		DriverUtil.sleep(5000L);

	}

	public void clickOnConfirmation(){
		StepReport.info("Click OK on confirmation Page");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(confirmation);
		DriverUtil.sleep(2000L);
		confirmCancel.click();
	}

	public boolean verifyPOStatus(String POStatus) {
		StepReport.info("Verify Approval Status");
		DriverUtil.sleep(2000L);
		try {
			String element="//*[text()='Status']/following::*[text()='"+POStatus+"']";
			WebElement statusElem=DriverUtil.getElement(By.xpath(element));
			if(statusElem.isDisplayed()) return true;
			else return false;
		}catch(Exception e) {
			return false;
		}

	}

}
