package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddComment extends BasePageObject<AddComment>{
	@FindBy(css = "textarea[id='cmtBox::content']")
	WebElement comment;
	
	@FindBy(xpath = "//*[text()='Add Comment']/ancestor::tbody[2]/tr[3]/td[2]/table/tbody/tr/td/span/button[text()='OK']")
	WebElement okButton;

	@FindBy(xpath = "//*[text()='Comment']/following::textarea[1]")
	WebElement textArea;

	@FindBy(xpath = "//*[text()='Save']")
	WebElement save;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(textArea) == null) {
            throw new TestErrorException("The Ok Button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void typeComment(String text) {
		StepReport.info("Type Comment");
		PageLoadHelper.waitForElementVisible(textArea);
		DriverUtil.sleep(1000L);
		textArea.sendKeys(text);
		DriverUtil.sleep(2000L);
	}
	
	public void clickOK() {
		StepReport.info("Click OK");
		okButton.click();
		DriverUtil.sleep(7000L);
		waitUntilOKButtonIsVisible();
	}

	public void clickSave() {
		StepReport.info("Click Save");
		save.click();
		DriverUtil.sleep(7000L);
		waitUntilOKButtonIsVisible();
	}
	
	private void waitUntilOKButtonIsVisible() {
		boolean isOkButtonDisplayed=true;
		int counter=1;
		while((isOkButtonDisplayed) && (counter<20)) {
		try {
			if(!okButton.isDisplayed()) {
				isOkButtonDisplayed=false;
				System.out.println("OK Button Not Displayed");
			}else {
				System.out.println("OK Button Displayed");
			}
			}catch(Exception e) {
				System.out.println("OK Button Not Displayed");
				isOkButtonDisplayed=false;
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
	   }
	}
	


