package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageApprovals extends BasePageObject<ManageApprovals> {


    @FindBy(xpath ="//h1[contains(text(),'Manage Approvals (Requisition')]")
    WebElement manageApprovalsTitle;

    @FindBy(xpath = "//*[@accesskey='m']")
    WebElement submit;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(manageApprovalsTitle) == null) {
            throw new TestErrorException("The Search Input field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Manage Approvals page is loaded");
    }

    public Requisitions submit() {
        StepReport.info("Click submit button");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(submit);
        DriverUtil.sleep(3000L);

        Requisitions requisition = PageFactory.getPage(Requisitions.class);
        requisition.isLoaded();
        return requisition;
    }

    public Confirmation clickSubmit() {
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(submit);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(submit);
        Confirmation confirmation = PageFactory.getPage(Confirmation.class);
        confirmation.isLoaded();
        return confirmation;
    }
}
