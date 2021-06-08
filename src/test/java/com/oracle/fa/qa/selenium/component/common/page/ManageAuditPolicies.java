package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ManageAuditPolicies extends BasePageObject<ManageAuditPolicies>{
	
	@FindBy(xpath = "//*[text()='Save']")
	WebElement save;

	@FindBy(xpath = "//*[@accesskey='C']")
	WebElement cancel;

    @FindBy(xpath = "//*[@accesskey='S']")
    WebElement saveAndClose;
	
	@FindBy(xpath = "//*[text()='Enable invoice approval']/preceding::input[1]")
	WebElement invoiceApprovalCheckBox;

    @FindBy(xpath = "//button[contains(@id,'msgDlg::cancel')]")
    WebElement okButton;

	@FindBy(xpath = "//*[text()='Allow force approval']/preceding::input[1]")
	WebElement forceApprovalCheckBox;
	
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(cancel) == null) {
            throw new TestErrorException("The Cancel is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void selectAuditLevel(String application, String auditLevel) {
        StepReport.info("Select Audit Level");
        String xpath = "//*[text()='"+application+"']/following::select[1]";
        WebElement applicationSelect = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(applicationSelect);
        Select typeElement = new Select(applicationSelect);
        typeElement.selectByVisibleText(auditLevel);
        DriverUtil.sleep(2000L);
    }

    public SetupMaintenance clickSaveAndClose() {
        StepReport.info("Click Save and Close");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(saveAndClose);
        DriverUtil.sleep(1000L);
        saveAndClose.click();
        DriverUtil.sleep(2000L);
        SetupMaintenance setupMaintenance= PageFactory.getPage(SetupMaintenance.class);
        setupMaintenance.isLoaded();
        return setupMaintenance;
    }

}
