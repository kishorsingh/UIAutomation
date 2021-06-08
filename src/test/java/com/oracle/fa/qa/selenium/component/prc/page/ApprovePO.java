package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApprovePO extends BasePageObject<ApprovePO>{
	
	@FindBy(xpath =".//*[text()='Comment']/following::textarea[1]")
	WebElement addcomments;
	
	@FindBy(xpath ="//*[@id='r1:0:bip_up:UPsp1:bip_rpp:0:ctb_apprej_submit']")
	WebElement clickSubmit;
	
	@Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(addcomments) == null) {
            throw new TestErrorException("The Approve Page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void typeComments(String text) {
        StepReport.info("Type Comment");
        PageLoadHelper.waitForElementVisible(addcomments);
        DriverUtil.sleep(1000L);
        addcomments.sendKeys(text);
        DriverUtil.sleep(2000L);
    }


    public void clickSubmit() {
        StepReport.info("Click Submit");
        DriverUtil.sleep(1000L);
        clickSubmit.click();
        DriverUtil.sleep(2000L);
        SelUtil.waitUntilPageClosed();
    }


}
