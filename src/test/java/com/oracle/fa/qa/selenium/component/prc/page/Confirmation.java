package com.oracle.fa.qa.selenium.component.prc.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class Confirmation extends BasePageObject<Confirmation>{
	
	@FindBy(xpath ="//*[contains(text(),'View PDF')]")
	WebElement viewPDF;
	
	@FindBy(css ="button[accesskey='K']")
	WebElement ok;
	
	@Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(ok) == null) {
            throw new TestErrorException("The OK is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public Requisitions clickOk() {
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(ok);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(ok);
        DriverUtil.sleep(3000L);
        Requisitions requisitions = PageFactory.getPage(Requisitions.class);
        requisitions.isLoaded();
		return requisitions;
	}

}
