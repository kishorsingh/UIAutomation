package com.oracle.fa.qa.selenium.component.prc.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class POConfirmation extends BasePageObject<POConfirmation>{
	
	@FindBy(xpath ="//*[text()='OK']")
	WebElement ok;
	
	@Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(ok) == null) {
            throw new TestErrorException("The OK is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

	public POOverView clickOk() {
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(ok);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(ok);
        DriverUtil.sleep(3000L);
        POOverView poOverView = PageFactory.getPage(POOverView.class);
        poOverView.isLoaded();
		return poOverView;
	}
}
