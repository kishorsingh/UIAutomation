package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.hcm.page.SubmitResignationPage;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;

import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.oracle.fa.qa.selenium.component.hcm.page.ContactInformationPage;

public class PersonalInfo extends BasePageObject {

    @FindBy(xpath = "//img[@id='_FOpt1:_FOr1:0:_FOSrPER_HCMPEOPLETOP_FUSE_PER_INFO:0:MAnt2:0:pDtlUpl:UPsp1:demoRgn:0:psRnPce:demoRgn0:0:demoPce:demoILv:0:legPse:PSEcil6::icon']")
    WebElement editpage;

    @FindBy(xpath = "//img[@title='Related Links']")
    WebElement relatedLinks;

    @FindBy(xpath = "//a[text()='Submit Resignation']")
    WebElement submitResignationLink;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(editpage) == null) {
            throw new TestErrorException("My Details Page is not loaded " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("My Details age is Loaded");
    }

    public ContactInformationPage editmydetails() {
        DriverUtil.sleep(2000L);
        StepReport.info("Editpage");
        editpage.click();
        ContactInformationPage editchange = PageFactory.getPage(ContactInformationPage.class);
        editchange.isLoaded();
        return editchange;

    }

    public String getMaritalStatus(String faEnvVersion) {

        String mstatus="";
        WebDriver driver= FrameworkContext.getInstance().getWebDriver();
        //#Pragathi : Changed xpath for getmaritalstatus (1901)
        mstatus=driver.findElement(By.xpath("//span[@id='_FOpt1:_FOr1:0:_FOSrPER_HCMPEOPLETOP_FUSE_PER_INFO:0:MAnt2:1:pDtlUpl:UPsp1:demoRgn:0:psRnPce:demoRgn0:0:demoPce:demoILv:0:legPse:rMarSel::content']")).getText();
        StepReport.info("Current Status:",mstatus);
        return mstatus;
    }

    public void clickRelatedLinks() {
        StepReport.info("Click Related links");
        relatedLinks.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(submitResignationLink);
    }

    public SubmitResignationPage clickSubmitResignation() {
        StepReport.info("Click Submit Resignation link");
        PageLoadHelper.waitForElementClickable(submitResignationLink);
        submitResignationLink.click();
        DriverUtil.sleep(5000L);
        SubmitResignationPage submitResignationPage = PageFactory.getPage(SubmitResignationPage.class);
        submitResignationPage.isLoaded();
        return submitResignationPage;
    }

}



