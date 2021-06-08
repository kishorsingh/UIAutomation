package com.oracle.fa.qa.selenium.component.common.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class AdhocRoute extends BasePageObject<AdhocRoute>{
	
	@FindBy(css = "input[id*='routingTypeChoice:_0']")
	WebElement singleAprover;
	
	@FindBy(css = "textarea[id*='routeTaskDetailsCommentsField']")
	WebElement comments;
	
	@FindBy(css = "input[id*='idSearchStringField']")
	WebElement userInput;
	
	@FindBy(css = "button[id*='idSearchButton']")
	WebElement searchButton;
	
	@FindBy(xpath = "//button[@accesskey='k' or @accesskey='K']")
	WebElement okButton;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(okButton) == null) {
            throw new TestErrorException("The Ok button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void adhocUser(String user,String comment) {
		StepReport.info("Click adhoc user");
		DriverUtil.sleep(2000L);
	    JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    executor.executeScript("arguments[0].click();", singleAprover);
	    DriverUtil.sleep(3000L);
	    comments.sendKeys(comment);
	    DriverUtil.sleep(2000L);
    	StepReport.info("Enter User to search");
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
		DriverUtil.sleep(5000L);
		SelUtil.waitUntilPageClosed();
	}


}
