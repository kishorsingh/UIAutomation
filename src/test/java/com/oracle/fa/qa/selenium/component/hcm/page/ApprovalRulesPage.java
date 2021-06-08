package com.oracle.fa.qa.selenium.component.hcm.page;

import com.github.wiselenium.factory.annotation.Component;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Component
public class ApprovalRulesPage extends HCMBasePageObject {

    @FindBy(xpath = "//tbody/tr/td[2]/input")
    WebElement searchRule;
    @FindBy(xpath = "//tbody/tr/td[2]/a[1]/img[@src='/hcmUI/images/func_search_16_ena.png']")
    WebElement searchFilter;
    @FindBy(xpath = "//img[@src='/hcmUI/images/func_pencil_16_ena.png']")
    WebElement configureRules;
    @FindBy(xpath = "//*[contains(@id,'i1::icon')]")
    WebElement bypassicon;

    @FindBy(xpath = "//div[text()='Warning']")
    WebElement warning;

    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(searchRule) == null) {
            throw new TestErrorException("The approvalRules field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void searchRule(String ruleName) {
        StepReport.info("Search Rule",ruleName);
        DriverUtil.sleep(6000);
        searchRule.sendKeys(ruleName);
        DriverUtil.sleep(3000);
        searchFilter.click();
        DriverUtil.sleep(5000);
        //#Pragathi : Changing xpath for rule pencil img
        //String xpath = "//td[text()='"+ruleName+"']";
        String xpath = "//*[contains(@src,'func_pencil_16_ena.png')]";
        WebElement rule = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(rule);
        DriverUtil.sleep(2000);
    }


    public ConfigureRulePage clickConfigureRule() {
        StepReport.info("Click Configure Rule");
        configureRules.click();
        DriverUtil.sleep(3000);
        if (SelUtil.isDisplayed(warning,30)) {
            findElement(By.xpath("//button[text()='No']")).click();
            DriverUtil.sleep(3000);
            findElement(By.xpath("//*[contains(@id,'i1::icon')]")).click();
            StepReport.info("Unselect Bypass Approvals");
            DriverUtil.sleep(2000);
            configureRules.click();
            DriverUtil.sleep(4000);

        }
        ConfigureRulePage conRulPg = PageFactory.getPage(com.oracle.fa.qa.selenium.component.hcm.page.ConfigureRulePage.class);
        conRulPg.isLoaded();
        return conRulPg;

    }

    public ConfigureRulePage enableBypassapproval() {
        bypassicon.click();
        if (SelUtil.isDisplayed(warning,30)) {
            findElement(By.xpath("//button[text()='Yes']")).click();
            DriverUtil.sleep(3000);
            StepReport.info("Select Bypass Approval");
        } else {
            bypassicon.click();
            findElement(By.xpath("//button[text()='Yes']")).click();
            DriverUtil.sleep(3000);
            StepReport.info("Select Bypass Approval");
        }

        ConfigureRulePage conbypass = PageFactory.getPage(com.oracle.fa.qa.selenium.component.hcm.page.ConfigureRulePage.class);
        return conbypass;

    }


    public ConfigureRulePage disableBypassapproval() {
        configureRules.click();
        DriverUtil.sleep(2000);
        if (findElements(By.xpath("//div[text()='Warning']")).size() > 0) {
            findElement(By.xpath("//button[text()='No']")).click();
            DriverUtil.sleep(3000);
            bypassicon.click();
            StepReport.info("Un-Selected Bypass Approval");
        }
        StepReport.info("Bypass approval disabled");
        ConfigureRulePage conbypass = PageFactory.getPage(com.oracle.fa.qa.selenium.component.hcm.page.ConfigureRulePage.class);
        return conbypass;
    }
}