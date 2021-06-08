package com.oracle.fa.qa.selenium.component.fin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class CashAdvanceConfirmation extends BasePageObject<CashAdvanceConfirmation>{
	
	@FindBy(xpath = "//button[text()='OK']")
	WebElement okButton;

	@FindBy(xpath = "//*[contains(text(),'Cash advance')]")
	WebElement confirmScreen;

   	@FindBy(xpath = "//*[contains(@id,'submitCashAdvance')]")
	WebElement confirmText;
	@FindBy(css = "td[id*='contentContainer']")
	WebElement content;
	
	 @Override
	    protected void isLoaded() {
	        if (PageLoadHelper.waitForElementClickable(confirmScreen) == null) {
	            throw new TestErrorException("The OK field is not visible after " +
	                    Timeout.pageLoadValue().seconds() + " seconds.");
	        }
	    }
	 
	 public TravelandExpenses clickOk(){
		StepReport.info("Click Ok Button");
		 Boolean printReport_message;
		 printReport_message=DriverUtil.isElementPresent(By.xpath("//a[text()='Print Report']"));
		 StepReport.info("Print Report Message Displayed", printReport_message.toString());
		 if(!printReport_message){
			 PageLoadHelper.waitForElementClickable(okButton);
			 okButton.click();
		 }
		DriverUtil.sleep(4000L);
		TravelandExpenses travelandExpenses = PageFactory.getPage(TravelandExpenses.class);
		return travelandExpenses;
	 }
	 
	 public String getCashAdvanceReportName() {
			StepReport.info("Get Cash Advance Report Name");
			String repName;
		 	String repInfo="";
		 	repInfo=confirmScreen.getText();
		 	String[] x = repInfo.split(" ");
			repName=x[2];
		 	StepReport.info("Confirmation:" + confirmScreen.getText());
			return repName;
		}

}
