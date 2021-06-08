package com.oracle.fa.qa.selenium.component.common.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class ReassignTask extends BasePageObject<ReassignTask>{
	
	@FindBy(css = "input[id*='reassignDialogComp:reassignOrDelegateSelection:_1']")
	WebElement delegateChoice;

	@FindBy(css = "input[id*='reassignDialogComp:reassignOrDelegateSelection:_0']")
	WebElement reassignChoice;

	@FindBy(css = "button[id*='reassignDialog::ok']")
	WebElement okButton;
	
	@FindBy(css = "button[id*='reassignDialogComp:idSearchButton']")
	WebElement searchButton;
	
	@FindBy(css = "input[id*='reassignDialogComp:idSearchStringField']")
	WebElement userInput;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(okButton) == null) {
            throw new TestErrorException("The Ok button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void delegateUser(String user) {
		StepReport.info("Click delegate");
		DriverUtil.sleep(2000L);
	    JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", delegateChoice);
    	StepReport.info("Enter User to search");
		DriverUtil.sleep(5000L);
		userInput.sendKeys(Keys.TAB);
		DriverUtil.sleep(2000L);
		userInput.sendKeys(user);
		StepReport.info("Click Search");
		DriverUtil.sleep(1000L);
        searchButton.click();
		DriverUtil.sleep(10000L);
		StepReport.info("Select User");
		int counter=1;
		boolean isUserNotFound=true;
		while((counter<10) && (isUserNotFound)) {
		try {
			String xpath="//*[text()='"+user+"']/preceding::input[1]";
			DriverUtil.waitForElementVisible(By.xpath(xpath),60);
			WebElement userChoice=DriverUtil.getElement(By.xpath(xpath));
			if(userChoice.isDisplayed()) {
				isUserNotFound=false;
			}
			userChoice.click();
			}catch(Exception e) {
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
		StepReport.info("Click Ok");
        okButton.click();
		DriverUtil.sleep(10000L);
	}

	public void reassignUser(String user) {
		StepReport.info("Click Reassign");
		DriverUtil.sleep(2000L);
		JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
		executor.executeScript("arguments[0].click();", reassignChoice);
		StepReport.info("Enter User to search");
		DriverUtil.sleep(5000L);
		userInput.sendKeys(Keys.TAB);
		DriverUtil.sleep(2000L);
		userInput.sendKeys(user);
		StepReport.info("Click Search");
		DriverUtil.sleep(1000L);
		searchButton.click();
		DriverUtil.sleep(10000L);
		StepReport.info("Select User");
		int counter=1;
		boolean isUserNotFound=true;
		while((counter<10) && (isUserNotFound)) {
			try {
				String xpath="//*[text()='"+user+"']/preceding::input[1]";
				DriverUtil.waitForElementVisible(By.xpath(xpath),60);
				WebElement userChoice=DriverUtil.getElement(By.xpath(xpath));
				if(userChoice.isDisplayed()) {
					isUserNotFound=false;
				}
				userChoice.click();
			}catch(Exception e) {
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
		StepReport.info("Click Ok");
		okButton.click();
		DriverUtil.sleep(10000L);
	}

}
