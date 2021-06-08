package com.oracle.fa.qa.selenium.component.fin.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class CreateItemDetails extends BasePageObject<CreateItemDetails>{
	@FindBy(css ="textarea[id*='Description']")
	WebElement description;
	
	@Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(description) == null) {
            throw new TestErrorException("The description field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

	public void enterExpenseDescription(String desc) {
		StepReport.info("Enter Receipt Description");
		DriverUtil.sleep(2000L);
		description.sendKeys(desc);
	}
}
