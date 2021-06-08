package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ObjectArrayGetAtMetaMethod;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SubmitContract extends BasePageObject<PartnersPage> {



    @FindBy(xpath ="//*[text()='Submit Contract: Review Approvers']")
    WebElement submitContractPage;

    @FindBy(xpath ="//*[text()='Note to Approver']/following::textarea")
    WebElement noteToApprover;

    @FindBy(xpath ="//a[@accesskey='m']")
    WebElement clickSubmit;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(submitContractPage) == null) {
            throw new TestErrorException("The Enrollment link is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Submit Contract Page is Loaded");
    }



       public void noteToApprover() {
        StepReport.info("Type Note to Approver");
        DriverUtil.sleep(2000L);
        noteToApprover.sendKeys("Approve this Contract");
        noteToApprover.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }
    public void ClickSubmit() {
        StepReport.info("Click Submit");
        DriverUtil.sleep(5000L);
        PageLoadHelper.waitForElementClickable(clickSubmit,20);
        DriverUtil.clickByJS(clickSubmit);
        DriverUtil.sleep(3000L);
    }


}
