package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EditContract extends BasePageObject<PartnersPage> {


    @FindBy(xpath ="//div[contains(@title,'Edit Contract:')]")
    WebElement editContract;



    @FindBy(xpath ="//a[text()='Lines']")
    WebElement clickLines;

    @FindBy(xpath ="//a[text()='Overview']")
    WebElement clickOverview;

    @FindBy(xpath ="//img[contains(@title,'Create')]")
    WebElement clickCreate;

    @FindBy(xpath ="//*[contains(@id,'selectOneChoice4::content')]")
    WebElement selectFrieghtTerms;

    @FindBy(xpath ="//*[contains(@id,'selectOneChoice5::content')]")
    WebElement selectFOB;

    @FindBy(xpath ="//*[contains(@id,'carrierNameId::content')]")
    WebElement selectCarrier;

    @FindBy(xpath ="//span[text()='Save']")
    WebElement clickSave;

    @FindBy(xpath ="//*[contains(@id,'PriceUnitId::content')]")
    WebElement typeUnitPrice;

    @FindBy(xpath ="//*[contains(@id,'numOfItemId::content')]")
    WebElement typeLineQuantity;

    @FindBy(xpath ="//*[contains(@id,'id3::content')]")
    WebElement typeNeedbyDate;

    @FindBy(xpath ="//*[contains(@id,'soc1::content')]")
    WebElement typePaymentTerms;

    @FindBy(xpath ="//*[contains(@id,'it8::content')]")
    WebElement typeComments;

    @FindBy(xpath ="//*[contains(@id,'HomeApplicationPanel:SPsb2')]")
    WebElement clickSubmit;

    /*@FindBy(xpath ="//*[@id='pt1:_FOr1:1:_FONSr2:0:MAnt2:1:pt1:pt_r1:0:HomeApplicationPanel:ctb1']/table/tbody/tr/td[1]/a/span")
    WebElement clickActions;*/

    @FindBy(xpath ="//a[@title='Actions']")
    WebElement clickActions;

    @FindBy(xpath ="//*[text()='Submit for Approval']")
    WebElement clickSubmitForApproval;

    @FindBy(xpath ="//*[@id='pt1:_FOr1:1:_FONSr2:0:MAnt2:1:pt1:pt_r1:0:HomeApplicationPanel:commandMenuItem21']/td[2]")
    WebElement clickStopApproval;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(editContract) == null) {
            throw new TestErrorException("The Enrollment link is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Edit Contract Page is Loaded");
    }


        public CreateLine clickLines() {
        StepReport.info("Click Lines");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(clickLines);
        PageLoadHelper.waitForElementClickable(clickCreate);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(clickCreate);
        DriverUtil.sleep(2000L);
        CreateLine createLine = PageFactory.getPage(CreateLine.class);
        createLine.isLoaded();
        return createLine;
    }
    public void clickOverview() {
        StepReport.info("Click Overview");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(clickLines);
        PageLoadHelper.waitForElementClickable(clickOverview);
        DriverUtil.clickByJS(clickOverview);

    }

    public void selectFrieghtTerms() {
        StepReport.info("Select Frieght Terms: AIR");
        DriverUtil.sleep(2000L);
        Select statusSelect = new Select(selectFrieghtTerms);
        statusSelect.selectByVisibleText("AIR");
        selectFrieghtTerms.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }
    public void selectFOB() {
        StepReport.info("Select FOB: Destination");
        DriverUtil.sleep(2000L);
        Select statusSelect = new Select(selectFOB);
        statusSelect.selectByVisibleText("Destination");
        selectFOB.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }
    public void selectCarrier() {
        StepReport.info("Select Carrier: Federal Expres");
        DriverUtil.sleep(2000L);
        selectCarrier.sendKeys("Federal Express");
        DriverUtil.sleep(3000L);
        selectCarrier.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }
    public void clickSave() {
        StepReport.info("Click Save");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(clickSave);
        //clickSave.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }

    public void typeUnitPrice() {
        StepReport.info("Type UnitPrice");
        PageLoadHelper.waitForElementVisible(typeUnitPrice);
        DriverUtil.sleep(2000L);
        typeUnitPrice.sendKeys("100");
        DriverUtil.sleep(2000L);
        typeUnitPrice.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }
    public void typeLineQuantity() {
        StepReport.info("Type LineQuantity");
        DriverUtil.sleep(3000L);
        typeLineQuantity.sendKeys("100");
        DriverUtil.sleep(3000L);
        typeLineQuantity.sendKeys(Keys.RETURN);
        DriverUtil.sleep(3000L);
    }

    public void typeNeedbyDate() {
        StepReport.info("Type LineQuantity");
        DriverUtil.sleep(3000L);
        typeNeedbyDate.sendKeys(SelUtil.getSystemDateTime(5));
        DriverUtil.sleep(3000L);
        typeNeedbyDate.sendKeys(Keys.RETURN);
        DriverUtil.sleep(3000L);
    }

    public void typePaymentTerms(){
        StepReport.info("Type PaymentTerms");
        typePaymentTerms.sendKeys("Net 60");
        DriverUtil.sleep(2000L);
        typePaymentTerms.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }

    public void typeComments(){
        StepReport.info("Type Comments");
        typeComments.sendKeys("Item Line");
        DriverUtil.sleep(2000L);
        typeComments.sendKeys(Keys.RETURN);
        DriverUtil.sleep(2000L);
    }

    public void clickSubmit(){
        StepReport.info("Click Submit");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(clickSubmit);
        DriverUtil.sleep(2000L);
    }

    public SubmitContract clickSubmitForApproval(){
        StepReport.info("Click SubmitForApproval");
        DriverUtil.sleep(2000L);
        clickActions.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(clickSubmitForApproval);
        DriverUtil.clickByJS(clickSubmitForApproval);
        DriverUtil.sleep(2000L);
        SubmitContract submitContract = PageFactory.getPage(SubmitContract.class);
        submitContract.isLoaded();
        return submitContract;

    }

    public ConfirmApproval clickStopApproval(){
        StepReport.info("Click Stop Approval");
        DriverUtil.sleep(2000L);
        clickActions.click();
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(clickStopApproval);
        DriverUtil.clickByJS(clickStopApproval);
        DriverUtil.sleep(2000L);
        ConfirmApproval confirmApproval = PageFactory.getPage(ConfirmApproval.class);
        confirmApproval.isLoaded();
        return confirmApproval;

    }


}
