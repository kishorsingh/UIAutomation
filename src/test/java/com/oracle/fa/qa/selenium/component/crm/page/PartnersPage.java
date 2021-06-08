package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.prc.page.CreateOrder;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartnersPage extends BasePageObject<PartnersPage>{
	@FindBy(xpath ="//*[@alt='Tasks']")
	WebElement tasks;

	@FindBy(xpath ="//*[text()='Create Order']")
	WebElement createOrder;

	@FindBy(xpath ="//*[text()='Create Partner']")
	WebElement createPartner;

	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(createPartner) == null) {
            throw new TestErrorException("The createPartner field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

		StepReport.info("Partners Page is Loaded");
    }

	public void clickTasks() {
		StepReport.info("Click Tasks");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(tasks);
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


	public CreatePartner clickCreateParter() {
		StepReport.info("Click Create Parter Button");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(createPartner);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(createPartner);
		DriverUtil.sleep(2000L);
		CreatePartner createPartner = PageFactory.getPage(CreatePartner.class);
		createPartner.isLoaded();
		return createPartner;
	}

}
