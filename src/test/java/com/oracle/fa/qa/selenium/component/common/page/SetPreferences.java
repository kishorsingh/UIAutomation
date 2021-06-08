package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SetPreferences extends BasePageObject<SetPreferences> {

    @FindBy(xpath = "//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:_FOTsr1:0:P_sp1:cl6']")
    WebElement languageMenuItem;

    @FindBy(xpath = "//button[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:MAnt2:1:AP2:APsv']")
    WebElement saveButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(languageMenuItem,150000L) == null) {
            throw new TestErrorException("The search button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void clickLanguage() {
        StepReport.info("Click Language");
        DriverUtil.sleep(2000L);
        languageMenuItem.click();
        DriverUtil.sleep(5000L);
        PageLoadHelper.waitForElementClickable(saveButton);
    }

    public void selectAllLanguageToKorean() {
        StepReport.info("Select language to Korean");
        DriverUtil.sleep(2000L);

        selectLanguage("_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:MAnt2:1:AP2:soc2::content", "1");
        selectLanguage("_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:MAnt2:1:AP2:soc1::content", "1");
        selectLanguage("_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:MAnt2:1:AP2:soc3::content", "16");
    }

    public void selectAllLanguageToEnglish() {
        StepReport.info("Select language to Korean");
        DriverUtil.sleep(2000L);

        selectLanguage("_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:MAnt2:1:AP2:soc2::content", "0");
        selectLanguage("_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:MAnt2:1:AP2:soc1::content", "0");
        selectLanguage("_FOpt1:_FOr1:0:_FOSritemNode_Tools_Preferences:0:MAnt2:1:AP2:soc3::content", "32");
    }

    private void selectLanguage(String labelFor, String languageValue) {
        StepReport.info("Select language to " + languageValue + " for " + labelFor);
        String xpath = "//label[@for='" + labelFor + "']/ancestor::tr[1]//select";
        WebElement langField = DriverUtil.getElement(By.xpath(xpath));
        Select langField1Select = new Select(langField);
        langField1Select.selectByValue(languageValue);
        DriverUtil.sleep(5000L);
    }

    public void clickSaveButton() {
        StepReport.info("Click Save button");
        saveButton.click();
        DriverUtil.sleep(10000L);
    }
}
