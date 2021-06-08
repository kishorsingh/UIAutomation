package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThemesPage extends BasePageObject<PartnersPage> {


    @FindBy(xpath = "//h1[contains(text(),'Themes')]")
    WebElement themesPage;

    @FindBy(xpath = "//*[contains(@id,'HOMEPAGE_FUSE_SETTINGS:0:_FOTr1:0:pt1:ic4::content')]")
    WebElement changeBackgroundColor;

    @FindBy(xpath = "//button[contains(text(),'App')]")
    WebElement clickApply;

    @FindBy(xpath = "//*[@title='Select']")
    WebElement selectTheme;


    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(themesPage) == null) {
            throw new TestErrorException("The themesPage field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Themes Page is Loaded");
    }



    public  void changeBackgroundcolor(String Color) {
        StepReport.info("Change Backgroundcolor to Orange");
        DriverUtil.sleep(2000L);
        changeBackgroundColor.clear();
        DriverUtil.sleep(1000L);
        changeBackgroundColor.sendKeys(Color);
        DriverUtil.sleep(2000L);
    }

    public  void selectTheme(String themeName) {
        StepReport.info("Change theme to:",themeName);
        DriverUtil.sleep(2000L);
        selectTheme.click();
        String themeNameXpath="//td[contains(text(),'"+themeName+"')]";
        WebElement selectThemeName=DriverUtil.getElement(By.xpath(themeNameXpath));
        DriverUtil.sleep(2000L);
        selectThemeName.click();
        DriverUtil.sleep(2000L);
    }


    public void clickApply(String customThemeName) {
        StepReport.info("Click Apply");
        DriverUtil.sleep(2000L);
        clickApply.click();
        DriverUtil.sleep(2000L);
        SaveAsCustomThemeName saveAsCustomThemeName = PageFactory.getPage(SaveAsCustomThemeName.class);
        if(saveAsCustomThemeName.isSaveAsCustomThemeNamePageLoaded()){
            saveAsCustomThemeName.isLoaded();
            saveAsCustomThemeName.typeCustomThemeName(customThemeName);
            saveAsCustomThemeName.clickOK();
        }
    }

    public EditContract clickOnContractNumber(String contractNumber) {
        StepReport.info("Click on Contract Number");
        //*[contains(text(),'186626')]
        DriverUtil.sleep(2000L);
        String xpath="//*[contains(text(),'"+contractNumber+"')]";
        WebElement clickOnContractNumber=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", clickOnContractNumber);
        DriverUtil.sleep(2000L);
        EditContract editContract = PageFactory.getPage(EditContract.class);
        editContract.isLoaded();
        return editContract;
    }



}