package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PurchaseOrder extends BasePageObject<PurchaseOrder>{


	@FindBy(xpath ="//h1[contains(text(),'Purchase Order: ')]")
	WebElement purchaseOrder;

	@FindBy(xpath ="//*[contains(@id,'PurchaseOrders:0:MAt3:0:pt1:Purch1:0:AP1:r1:0:q1:value40::content')]")
	WebElement inputOrderNumber;

	@FindBy(xpath ="//*[contains(@id,'PurchaseOrders:0:MAt3:0:pt1:Purch1:0:AP1:r1:0:q1::search')]")
	WebElement clickSearch;


	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(purchaseOrder) == null) {
            throw new TestErrorException("The Create Button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Purchase Order Page is loaded");
    }
	
    public void searchPurchaseOrder(String OrderNum){
		StepReport.info("Enter Purchase Order Number to search");
		DriverUtil.sleep(2000L);
		inputOrderNumber.sendKeys(OrderNum);
		DriverUtil.sleep(3000L);
		inputOrderNumber.click();
	}
}
