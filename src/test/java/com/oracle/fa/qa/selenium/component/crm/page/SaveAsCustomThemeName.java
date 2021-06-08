package com.oracle.fa.qa.selenium.component.crm.page;

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

/**
 * Created by bpadhy on 1/17/18.
 */
public class SaveAsCustomThemeName extends BasePageObject<SaveAsCustomThemeName> {
    @FindBy(xpath="//*[contains(text(),'Theme Name')]")
    WebElement saveAsThemename;

    @FindBy(xpath="//*[contains(text(),'Theme Name')]//following::input[1]")
    WebElement typeCustomeThemeName;

    @FindBy(xpath="//*[contains(text(),'Theme Name')]//following::button[1]")
    WebElement clickOK;


    @Override
    protected void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(saveAsThemename) == null) {
            throw new TestErrorException("The UserName field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Click OK Confirmation page is loaded");
    }

   public ThemesPage clickOK() {
        StepReport.info("Click OK");
        DriverUtil.clickByJS(clickOK);
        DriverUtil.sleep(3000L);
        ThemesPage themesPage = PageFactory.getPage(ThemesPage.class);
        themesPage.isLoaded();
        return themesPage;
   }

   public void typeCustomThemeName(String customThemeName) {
        StepReport.info("Type Custom Theme Name");
        typeCustomeThemeName.sendKeys(customThemeName);
        DriverUtil.sleep(3000L);
   }

    public boolean isSaveAsCustomThemeNamePageLoaded(){
        if(DriverUtil.isElementPresent(By.xpath("//*[contains(text(),'Theme Name')]"))){
            return true;
        }else{
            return false;
        }
    }
}
