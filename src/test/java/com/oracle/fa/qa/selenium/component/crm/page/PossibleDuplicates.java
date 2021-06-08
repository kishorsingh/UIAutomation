package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PossibleDuplicates extends BasePageObject<PossibleDuplicates> {

    @FindBy(xpath ="//*[text()='Continue with Create']")
    WebElement continueWithCreate;

    public boolean pageLoaded() {
        if (PageLoadHelper.waitForElementClickable(continueWithCreate,60L) == null) {
            return false;
        }
        else{
            return true;
        }
    }

    public EditPartnerProfile clickContinueWithCreate() {
        StepReport.info("Click Continue With Create");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(continueWithCreate);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(continueWithCreate);
        //comment
        DriverUtil.sleep(2000L);
        EditPartnerProfile editPartnerProfile = PageFactory.getPage(EditPartnerProfile.class);
        editPartnerProfile.isLoaded();
        return editPartnerProfile;
    }
    public EditContactPage clickContinueWithCreateContact() {
        StepReport.info("Click Continue With Create");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(continueWithCreate);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(continueWithCreate);
        //comment
        DriverUtil.sleep(2000L);
        EditContactPage editContactPage = PageFactory.getPage(EditContactPage.class);
        editContactPage.isLoaded();
        return editContactPage;
    }
}
