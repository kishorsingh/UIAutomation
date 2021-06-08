package com.oracle.fa.qa.selenium.component.common.page;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class BellNotification extends BasePageObject<BellNotification>{
	
	@FindBy(css = "button[title='Search']")
	WebElement searchButton;
	
	@FindBy(xpath = "//input[@placeholder='Enter search terms']")
	WebElement searchPlaceHolder;

	@FindBy(xpath="//a[text()='More Details']")
	WebElement moreDetailsLink;

	@FindBy(xpath="//h1[contains(text(),'Notifications')]")
	WebElement NotificationsTitle;
	
	   @Override
	    protected void isLoaded() {
	        if (PageLoadHelper.waitForElementClickable(NotificationsTitle) == null) {
	            throw new TestErrorException("The Search button is not visible after " +
	                    Timeout.pageLoadValue().seconds() + " seconds.");
	        }
	    }

	public void clickMoreDetails() {
		StepReport.info("Click More Details");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(moreDetailsLink);
		DriverUtil.sleep(1000L);
		moreDetailsLink.click();
		DriverUtil.sleep(1000L);

	}

	public boolean isExpenseReportExists(String expReportName) {
		StepReport.info("Search Expense Report");
		String xpathString = "//*[contains(text(),'" + expReportName + "')]";
		if (DriverUtil.isElementPresent(By.xpath(xpathString))) {
			WebElement report = DriverUtil.getElement(By.xpath(xpathString));
			boolean result = report.isDisplayed();
			return result;
		}
		return false;
	}

	public boolean waitForReportAppear(String reportName) {
		StepReport.info("Wait for Report to Appear");
		String xpathString = "//*[contains(text(),'" + reportName + "')]";
		for(int i=1; i<11; i++) {
			StepReport.info("Retry : "+i);
			DriverUtil.sleep(2000L);
			searchPlaceHolder.clear();
			DriverUtil.sleep(2000L);
			searchPlaceHolder.sendKeys(reportName.trim());
			DriverUtil.sleep(3000L);
			DriverUtil.clickByJS(searchButton);
			DriverUtil.sleep(5000L);
			if(this.isExpenseReportExists(reportName)) {
				return true;
			}
		}
		return false;
	}

	public boolean waitForReportAndReTry(String reportName, HomePage homePage) {
		return this.waitForReportAndReTry(reportName, homePage, 7);
	}

	public boolean waitForReportAndReTry(String reportName, HomePage homePage, int retryTime) {
		StepReport.info("Wait for Report : "+reportName);
		int counter=1;
		boolean isReportNotFound=true;
		while((counter<=retryTime) && (isReportNotFound)) {
			try {
				DriverUtil.sleep(2000L);
				searchPlaceHolder.clear();
				DriverUtil.sleep(2000L);
				searchPlaceHolder.sendKeys(reportName.trim());
				DriverUtil.sleep(3000L);
				DriverUtil.clickByJS(searchButton);
				DriverUtil.sleep(2000L);
				String xpathString= "//*[contains(text(),'"+reportName+"')]";
				WebElement reportElem=DriverUtil.getElement(By.xpath(xpathString));
				if(reportElem.isDisplayed()) {
					isReportNotFound=false;
					return true;
				}
			}catch(Exception e) {
			}
			DriverUtil.sleep(5000L);
			counter++;
			searchPlaceHolder.click();
			searchPlaceHolder.sendKeys(Keys.RETURN);
			DriverUtil.sleep(2000L);
			searchPlaceHolder.sendKeys(Keys.ESCAPE);
			DriverUtil.sleep(2000L);
			homePage.clickHome();
			homePage.clickBellNotification();
		}
		return false;
	}

	public void clickMoreDetailsLink() {
		StepReport.info("Click more details");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(moreDetailsLink);
		DriverUtil.sleep(20000L);
	}

}
