package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.crm.page.ThemesPage;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationComposer extends BasePageObject<ApplicationComposer>{


	@FindBy(xpath="//*[text()='Appearance']")
	WebElement clickAppearance;

	@Override
	protected void isLoaded() {
		if (PageLoadHelper.waitForElementVisible(clickAppearance) == null) {
			throw new TestErrorException("The Application Composer page link is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("Application Composer Page is loaded");
	}

	public ThemesPage clickAppearance() {
		StepReport.info("Click On >>Appearance");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementClickable(clickAppearance);
		DriverUtil.clickByAction(clickAppearance,true);
		ThemesPage themesPage = PageFactory.getPage(ThemesPage.class);
		themesPage.isLoaded();
		return themesPage;
	}

}
