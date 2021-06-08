package com.oracle.fa.qa.selenium.component.prc.page;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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
import org.openqa.selenium.support.ui.Select;

public class POOverView extends BasePageObject<POOverView>{
	@FindBy(xpath ="//*[contains(@id,'QuickSearch::icon')]")
	WebElement clickSearch;

	@FindBy(xpath ="//*[@alt='Tasks']")
	WebElement tasks;

	@FindBy(xpath ="//*[@id=//select[@id='pt1:_FOr1:0:_FOSritemNode_procurement_PurchaseOrders:0:_FOTRaTj_id_1:1:soc1::content']/option[text() = 'Orders']")
	WebElement selectOrders;

	@FindBy(xpath ="//*[text()='Create Order']")
	WebElement createOrder;

	@FindBy(xpath ="//a[text()='Manage Orders']")
	WebElement manageOrder;



	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(tasks) == null) {
            throw new TestErrorException("The tasks field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void clickTasks() {
		StepReport.info("Click Tasks");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(tasks);
		DriverUtil.sleep(2000L);
		
	}

	public void clickSearch() {
		StepReport.info("Click Search");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(clickSearch);
		DriverUtil.sleep(2000L);

	}

	public void selectOrders(){
		StepReport.info("Select Orders");
		DriverUtil.sleep(3000L);
		Select statusSelect = new Select(selectOrders);
		StepReport.info("Select Orders Xpath");
		statusSelect.selectByVisibleText("Orders");
		DriverUtil.sleep(2000L);
		selectOrders.sendKeys(Keys.RETURN);
	}
	public void selectPO(){
		StepReport.info("Select Orders");
		DriverUtil.sleep(2000L);
		clickSearch.click();
		DriverUtil.sleep(2000L);
	}
	
	public CreateOrder clickCreatePO() {
		StepReport.info("Click Create PO");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(createOrder);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(createOrder);
		DriverUtil.sleep(2000L);
		CreateOrder createOrder = PageFactory.getPage(CreateOrder.class);
		createOrder.isLoaded();
		return createOrder;
	}


	public ManageOrder clickManageOrder() {
		StepReport.info("Click Manage Order");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(manageOrder);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(manageOrder);
		DriverUtil.sleep(2000L);
		ManageOrder manageOrder = PageFactory.getPage(ManageOrder.class);
		manageOrder.isLoaded();
		return manageOrder;
	}





}
