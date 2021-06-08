package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.fin.page.ManageInvoiceOptions;
import com.oracle.fa.qa.selenium.component.prc.page.ManageUserDefAttr;
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

public class ManageRequisitionApprovals extends BasePageObject<ManageRequisitionApprovals> {

    @FindBy(xpath = "//button[text()='Edit Rules']")
    WebElement editRulesButton;

    @FindBy(xpath = "//button[text()='Enable']")
    WebElement enableButton;

    @FindBy(xpath = "//button[text()='Disable']")
    WebElement disableButton;

    //@FindBy(xpath="//span[text()='Header Stage']/parent::td/following::td/span[text()='Header Hierarchy']")
    @FindBy(xpath="//span[text()='Header Hierarchy']")
    WebElement headerHierarchy;

    @FindBy(xpath="//span[text()='Manage User-Defined Attributes']")
    WebElement manageUserDefinedAttrButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(editRulesButton) == null) {
            throw new TestErrorException("The Edit Rules button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Edit Rule Button Displayed");
    }

    public void clickRule(String ruleName) {
        StepReport.info("Select"+ ruleName);
        DriverUtil.sleep(2000L);
        String xPath="//*[text()='"+ruleName+"']";
        WebElement rule=DriverUtil.getElement(By.xpath(xPath));
        PageLoadHelper.waitForElementClickable(rule);
        DriverUtil.sleep(2000L);
        rule.click();
        DriverUtil.sleep(2000L);

    }

    public EditApprovalRules clickEditRules() {
        StepReport.info("Click Edit Rules");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(editRulesButton);
        DriverUtil.sleep(1000L);
        DriverUtil.clickByJS(editRulesButton);
        DriverUtil.sleep(2000L);
        EditApprovalRules editApprovalRules = PageFactory.getPage(EditApprovalRules.class);
        editApprovalRules.isLoaded();
        return editApprovalRules;

    }

    public void clickEnable() {
        if(enableButton.isDisplayed()) {
            DriverUtil.clickByJS(enableButton);
            DriverUtil.sleep(1000L);
            PageLoadHelper.waitForElementClickable(disableButton);
            DriverUtil.sleep(1000L);
            System.out.println("Rule is Enabled");
            }
            else {
            System.out.println("Rule is already Enabled");
            }
            DriverUtil.sleep(1000L);
    }

    public void disableParticipants(String[] participants) {
        for(String participant:participants) {
            clickRule(participant);
            try{
                if (disableButton.isDisplayed()) {
                    DriverUtil.clickByJS(disableButton);
                    DriverUtil.sleep(1000L);
                    PageLoadHelper.waitForElementVisible(enableButton);
                    DriverUtil.sleep(1000L);
                    System.out.println("Rule is Disabled");
                }
                else {
                    System.out.println("Rule is already Disabled");
                }
            }catch(Exception e) {

                System.out.println("Rule is already Disabled");
            }
            }
            DriverUtil.sleep(1000L);
        }

    public ManageUserDefAttr clickManageUserDefinedAttrButton() {
        StepReport.info("Click Manage User-Defined Attribute");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(manageUserDefinedAttrButton);
        DriverUtil.sleep(1000L);
        DriverUtil.clickByJS(manageUserDefinedAttrButton);
        DriverUtil.sleep(2000L);
        ManageUserDefAttr manageUserDefAttr = PageFactory.getPage(ManageUserDefAttr.class);
        manageUserDefAttr.isLoaded();
        return manageUserDefAttr;

    }

}
