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

public class ManagePurchasingDocumentApprovals extends BasePageObject<ManagePurchasingDocumentApprovals> {

    @FindBy(xpath = "//button[text()='Edit Rules']")
    WebElement editRulesButton;

    @FindBy(xpath = "//button[text()='Enable']")
    WebElement enableButton;

    @FindBy(xpath = "//button[text()='Enable']")
    WebElement disableButton;

    @FindBy(xpath="//*[text()='Terms Approval Serial']")
    WebElement termsApprovalSerial;

    @FindBy(xpath="//*[@class='af_messages_detail']")
    WebElement deployConfirmation;

    @FindBy(xpath="//*[text()='Confirmation']/following::button[text()='OK'][1]")
    WebElement confirmationOKButton;

    @FindBy(xpath="//span[text()='Edit Rules in BPM']/parent::a")
    WebElement editRulesInBPM;

    @FindBy(xpath="//button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath="//button[@accesskey='K']")
    WebElement editInBPMConfirmationOK;

    //@FindBy(xpath = "//span[text()='Preapproval FYI']")
    //WebElement preapprovalFYI;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(editRulesButton) == null) {
            throw new TestErrorException("The Edit Rules button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Edit Rule Button Displayed");
    }

    public void clickTermsApprovalSerial() {
        StepReport.info("Select Header Hierarchy");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(termsApprovalSerial);
        DriverUtil.sleep(2000L);
        termsApprovalSerial.click();
        DriverUtil.sleep(2000L);

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
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(editRulesButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(editRulesButton);
        DriverUtil.sleep(2000L);
        EditApprovalRules editApprovalRules = PageFactory.getPage(EditApprovalRules.class);
        editApprovalRules.isLoaded();
        return editApprovalRules;

    }

    public void clickEnable() {
        if(enableButton.isDisplayed()) {
            DriverUtil.clickByJS(enableButton);
            DriverUtil.sleep(2000L);
            PageLoadHelper.waitForElementClickable(disableButton);
            DriverUtil.sleep(2000L);
            System.out.println("Rule is Enabled");
        }
        else {
            System.out.println("Rule is already Enabled");
        }
        DriverUtil.sleep(3000L);
    }

    public String getProcessID() {
        StepReport.info("Get Process ID");
        PageLoadHelper.waitForElementVisible(deployConfirmation);
        String repInfo=deployConfirmation.getText();
        String[] x = repInfo.split(" ");
        String processID=x[2];
        return processID;
    }

    public void clickConfirmationOK() {
        StepReport.info("Click OK Button in Confirmation Dialog");
        PageLoadHelper.waitForElementClickable(confirmationOKButton);
        DriverUtil.sleep(1000L);
        confirmationOKButton.click();
        DriverUtil.sleep(2000L);
    }

    public void clickEditRulesInBPM() {
        StepReport.info("Click Button: Edit Rules in BPM");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(editRulesInBPM);
        DriverUtil.sleep(2000L);
        editRulesInBPM.click();
        DriverUtil.sleep(2000L);
        clickYes();
        clickOK();

    }

    public void clickYes() {
        PageLoadHelper.waitForElementClickable(yesButton);
        StepReport.info("Click Yes button in the warning pop-up");
        DriverUtil.sleep(2000L);
        yesButton.click();
        DriverUtil.sleep(2000L);

    }

    public void clickOK() {
        PageLoadHelper.waitForElementClickable(editInBPMConfirmationOK);
        StepReport.info("Click OK button in the confirmation pop-up");
        DriverUtil.sleep(2000L);
        editInBPMConfirmationOK.click();
        DriverUtil.sleep(2000L);

    }
}
