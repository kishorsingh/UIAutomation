package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class DisputesPage extends BasePageObject<DisputesPage> {



	@FindBy(xpath = "//button[contains(@id,'dynam1:0:cb1')]")
	WebElement saveAndContinueButton;

	@FindBy(xpath = "//button[contains(text(),'Create Dispute')]")
	WebElement createDisputeButton;



	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(createDisputeButton) == null) {
			throw new TestErrorException("The Dispute page is not loaded after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Create Dispute Sub Page is Loaded");
	}






	public CreateDispute clickCreateDispute() {
		StepReport.info("Enter End Date  ");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(createDisputeButton);
		DriverUtil.sleep(2000L);
		CreateDispute createDispute = PageFactory.getPage(CreateDispute.class);
		createDispute.isLoaded();
		return createDispute;
	}




}
