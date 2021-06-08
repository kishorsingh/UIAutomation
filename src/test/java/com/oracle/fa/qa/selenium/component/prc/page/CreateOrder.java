package com.oracle.fa.qa.selenium.component.prc.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class CreateOrder extends BasePageObject<CreateOrder>{
	@FindBy(xpath = "//*[contains(@name,'StyleName')]")
	WebElement styleName;
	
	@FindBy(xpath = "//button[text()='Create']")
	WebElement createButton;
	
	@FindBy(xpath = "//*[contains(@id,'Supplier::content')]")
	WebElement supplier;
	
	@FindBy(xpath = "//*[contains(@id,'SupplierSite::content')]")
	WebElement supplierSite;

	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(createButton) == null) {
            throw new TestErrorException("The Create Button is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public EditPODocument clickCreate() {
		StepReport.info("Click Create");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(createButton);
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(createButton);
		DriverUtil.sleep(2000L);
		EditPODocument editPODocument = PageFactory.getPage(EditPODocument.class);
		editPODocument.isLoaded();
		return editPODocument;
		
	}
	
	public void typeSupplier(String suppl) {
		StepReport.info("Type Supplier");
		DriverUtil.sleep(3000L);
		supplier.sendKeys(suppl);
		DriverUtil.sleep(3000L);
		supplier.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);
		supplier.sendKeys(Keys.TAB);
		DriverUtil.sleep(3000L);
		
	}
	
	public void typeSupplierSite(String supplSite) {
		StepReport.info("Type Supplier Site");
		PageLoadHelper.waitForElementVisible(supplierSite);
		DriverUtil.sleep(2000L);
		supplierSite.sendKeys(supplSite);
		DriverUtil.sleep(2000L);
		supplierSite.sendKeys(Keys.TAB);
		DriverUtil.sleep(2000L);
		
	}
}
