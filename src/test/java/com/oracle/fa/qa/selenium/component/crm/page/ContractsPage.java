package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.common.page.LoginPage;
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

public class ContractsPage extends BasePageObject<PartnersPage> {

    @FindBy(xpath ="//*[contains(@id,'srchit::content')]")
    WebElement searchProgram;

    @FindBy(xpath = "//*[@title='Find']")
    WebElement findButton;


    @FindBy(xpath = "//img[@title='Tasks']")
    WebElement clickTasks;

    @FindBy(xpath = "//img[@title='Recent Contracts']")
    WebElement recentContracts;

    @FindBy(xpath = "//a[text()='Create Contract']")
    WebElement createContract;

    @FindBy(xpath = "//h1[contains(text(),'Contracts')]")
    WebElement contractsPage;


    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(contractsPage) == null) {
            throw new TestErrorException("The createPartner field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Contracts Page is Loaded");
    }

   /* public EditProgram searchProgram(String crmProgramName) {
        StepReport.info("Enter program name");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(searchProgram,10);
        DriverUtil.sleep(2000L);
        searchProgram.sendKeys(crmProgramName);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(findButton);
        PageLoadHelper.waitForElementClickable(searchResult);
        DriverUtil.clickByJS(searchResult);
        DriverUtil.sleep(2000L);
        EditProgram editProgram = PageFactory.getPage(EditProgram.class);
        editProgram.isLoaded();
        return editProgram;
    }*/

    public void clickTasks() {
        StepReport.info("Click Tasks");
        DriverUtil.sleep(5000L);
        PageLoadHelper.waitForElementClickable(clickTasks,20);
        DriverUtil.clickByJS(clickTasks);
        DriverUtil.sleep(2000L);

    }

    public CreateContract clickCreateContract() {
        StepReport.info("Click Create Contracts");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(createContract,20);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(createContract);
        DriverUtil.sleep(2000L);
        CreateContract createContract = PageFactory.getPage(CreateContract.class);
        createContract.isLoaded();
        return createContract;
    }

    public  void recentContracts() {
        StepReport.info("Click Recent Contracts");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(recentContracts);
        DriverUtil.sleep(2000L);

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