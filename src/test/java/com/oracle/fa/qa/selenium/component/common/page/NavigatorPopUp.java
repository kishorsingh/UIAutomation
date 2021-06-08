package com.oracle.fa.qa.selenium.component.common.page;

import com.github.wiselenium.testng.annotation.Page;
import com.oracle.fa.qa.selenium.component.crm.page.*;
import com.oracle.fa.qa.selenium.component.fin.page.CashBalances;
import com.oracle.fa.qa.selenium.component.fin.page.Invoices;
import com.oracle.fa.qa.selenium.component.fin.page.Transactions;
import com.oracle.fa.qa.selenium.component.fin.page.TravelandExpenses;
import com.oracle.fa.qa.selenium.component.hcm.page.NewPersonPage;
import com.oracle.fa.qa.selenium.component.hcm.page.PersonManagementPage;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigatorPopUp extends BasePageObject<NavigatorPopUp>{

	@FindBy(xpath="//*[text()='Configuration']")
	WebElement configuration;

	@FindBy(xpath="//a[text()='Appearance']")
	WebElement clickAppearance;

	@FindBy(xpath = "//a[@title='Partners']")
	WebElement partners;

	@Override
	protected void isLoaded() {
		if (PageLoadHelper.waitForElementVisible(configuration) == null) {
			throw new TestErrorException("The Navigator PopUp page link is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("NavigatorPopup Page is loaded after clicking More");
	}

	public ThemesPage clickAppearance() {
		StepReport.info("Click More>>Configuration>>Appearance");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(clickAppearance);
		DriverUtil.sleep(5000L);
		DriverUtil.clickByJS(clickAppearance);
		//DriverUtil.clickByAction(clickAppearance,true);
		DriverUtil.sleep(2000L);
		ThemesPage themesPage = PageFactory.getPage(ThemesPage.class);
		themesPage.isLoaded();
		return themesPage;
	}

	public PartnersPage clickPartners(){
		StepReport.info("click Partners");
		partners.click();
		DriverUtil.sleep(2000L);
		PartnersPage partnersPage = PageFactory.getPage(PartnersPage.class);
		partnersPage.isLoaded();
		return  partnersPage;
	}

}
