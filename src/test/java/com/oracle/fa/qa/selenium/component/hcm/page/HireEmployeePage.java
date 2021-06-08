package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.fa.qa.selenium.component.crm.page.EditContract;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class HireEmployeePage extends HCMBasePageObject {
    @FindBy(xpath = "//a[@title='Legal Employer']")
    WebElement legalEmployer;

    @FindBy(xpath = "//input[contains(@id,'it20::content')]")
    WebElement lastName;

    @FindBy(xpath="//*[contains(@id,'selectOneChoice3::content')]")
    WebElement legalEmpInput;

    @FindBy(xpath = "//span[contains(text(),'Ne')]/parent::a")
    WebElement nextHref;

    @FindBy(xpath = "//div[@title='Submit']/a")
    WebElement submit;

    @FindBy(xpath="//div[@title='Save']/table/tbody")
    WebElement save;

    //@FindBy(xpath="(//*[text()='Postal Code']/following::input)[1]")
    //WebElement typeZipCode;

    @FindBy(xpath="//*[contains(@id,'inputComboboxListOfValues28::content')]")
    WebElement typeZipCode;

    @FindBy(xpath="//*[contains(@title, 'Task start')]")
    WebElement taskStarticon;

    @FindBy(xpath="//*[text()='Bypass approval is enabled for the transaction']")
    WebElement bypassenabled;

    @FindBy(xpath="//*[contains(@id, 'submit')]")
    WebElement clickSubmit;

    @FindBy(xpath = "//div[@title='Submit']/a/span/span")
    WebElement submitButton;

    @FindBy(xpath="//button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath="//button[@accesskey='K']")
    WebElement confirmButton;

    @FindBy(xpath="//*[contains(@id,'okWarningDialog')]")
    WebElement clickYes;

    @FindBy(xpath="//*[contains(@id,'okConfirmationDialog')]")
    WebElement clickOK;

    @FindBy(xpath="//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_workforce_management_new_person:0:MAnt2:1:pt1:pt_r1:2:pt1:sP2:NewPe2:0:NewPe1:0:businessUnitId']/td[1]/label")
    WebElement label;

    @FindBy(xpath="//*[text()='New Person Approval Stage']")
    WebElement historyTable;

    @FindBy(xpath="//*[text()='Other Compensation']")
    WebElement otherCompensation;

    @FindBy(xpath="//h1[contains(text(),'Compensation and Other Information')]")
    WebElement compensation;

    @FindBy(xpath="//h1[contains(text(),'Review')]")
    WebElement reviewPage;



    public static String empName;
    //public static WebDriver driver = FrameworkContext.getInstance().getWebDriver();


  //  public static WebDriver driver= FrameworkContext.getInstance().getWebDriver();

    /* public  LegalEmployerSearchPopUp selectLegalEmployer(){
            legalEmployer.click();
            DriverUtil.waitForElementVisible("//a[text()='Search...']",10);
            findElement(By.xpath("//a[text()='Search...']")).click();

            LegalEmployerSearchPopUp legalEmployerSearchPopUp= PageFactory.getPage(LegalEmployerSearchPopUp.class);
            return legalEmployerSearchPopUp;


        }*/
    public String fillLastName(){
        DriverUtil.sleep(3000);
        Random r = new Random();
        int n = r.nextInt(10000)+1;
        StepReport.info("Fill in Employee LastName");

       // DriverUtil.waitForElementVisible("//label[text()='Last Name']/preceding::span[1]/parent::span/parent::td/following-sibling::td/input",10);
        empName ="HCMTEST"+n;
        StepReport.info("Emp Name:"+empName);
        PageLoadHelper.waitForElementVisible(lastName);
        DriverUtil.sleep(3000);
        lastName.sendKeys(empName);
        DriverUtil.sleep(3000);
        lastName.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000);
        return empName;


    }
    public String getLastName(){
        return lastName.getText();

    }
    public void fillAddress(){
        try {
            PageLoadHelper.waitForJetPageReady(driver,5000);
            StepReport.info("Fill address line1");
            DriverUtil.waitForElementVisible("//input[contains(@id,'nputText17::content')]", 10);
            findElement(By.xpath("//input[contains(@id,'nputText17::content')]")).sendKeys("Charles House");
            DriverUtil.sleep(4000);
            findElement(By.xpath("//input[contains(@id,'nputText17::content')]")).sendKeys(Keys.RETURN);

        }
        catch (Error e){
            findElement(By.xpath("//input[contains(@id,'nputText17::content')]")).sendKeys("Charles House");
            StepReport.info("Fill address line1 attempt2");
            DriverUtil.sleep(4000);
            findElement(By.xpath("//input[contains(@id,'nputText17::content')]")).sendKeys(Keys.RETURN);
        }

    }

    public void fillAddress2(){
        try {
            PageLoadHelper.waitForJetPageReady(driver,5000);
            StepReport.info("Fill address line1");
            DriverUtil.waitForElementVisible("//input[contains(@id,'nputText18::content')]", 10);
            findElement(By.xpath("//input[contains(@id,'nputText18::content')]")).sendKeys("Street 56");
            DriverUtil.sleep(4000);
            findElement(By.xpath("//input[contains(@id,'nputText18::content')]")).sendKeys(Keys.RETURN);

        }
        catch (Error e){
            findElement(By.xpath("//input[contains(@id,'nputText18::content')]")).sendKeys("Street 56");
            StepReport.info("Fill address line1 attempt2");
            DriverUtil.sleep(4000);
            findElement(By.xpath("//input[contains(@id,'nputText18::content')]")).sendKeys(Keys.RETURN);
        }

    }


    public void selectLegalEmployer(){
        DriverUtil.sleep(6000);
        try {
            PageLoadHelper.waitForElementVisible(legalEmpInput);
            DriverUtil.sleep(4000);
            legalEmpInput.sendKeys("GBI HCM TECHNOLOGY USA");

        }
        catch (Exception e){
            legalEmpInput.sendKeys("GBI HCM TECHNOLOGY USA");
        }

        DriverUtil.sleep(4000);
        //DriverUtil.waitForElementVisible("//ul/li[text()='GBI HCM Technology USA US']",20);
        //findElement(By.xpath("//ul/li[text()='GBI HCM Technology USA US']")).sendKeys(Keys.RETURN);
        legalEmpInput.sendKeys(Keys.TAB);
        DriverUtil.sleep(5000);

    }

    public void enterZipcode(String empType){
       // PageLoadHelper.waitForJetPageReady(driver,10000L);
        findElement(By.xpath("//*[contains(@id,'inputComboboxListOfValues28::lovIconId')]")).click();
        DriverUtil.waitForElementVisible("//a[text()='Search...']",10);
        findElement(By.xpath("//a[text()='Search...']")).click();
        if(empType.equalsIgnoreCase("Employee")) {
            DriverUtil.waitForElementVisible("//label[text()=' Geography']/preceding-sibling::input", 10);
            findElement(By.xpath("//label[text()=' Geography']/preceding-sibling::input")).sendKeys("94065");
            DriverUtil.waitForElementVisible("//button[text()='Search']",10);
            findElement(By.xpath("//button[text()='Search']")).click();
            DriverUtil.sleep(2000);
            DriverUtil.waitForElementVisible("//span[contains(text(),'REDWOOD SHORES')]",10);
            findElement(By.xpath("//span[contains(text(),'REDWOOD SHORES')]")).click();
        }
        else{

            DriverUtil.waitForElementVisible("//button[text()='Search']",10);
            findElement(By.xpath("//button[text()='Search']")).click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("(//colgroup[@span='2']/following-sibling::tbody/tr/td)[1]")).click();


        }

        DriverUtil.sleep(4000);
        findElement(By.xpath("(//button[text()='OK'])[1]")).click();
        DriverUtil.sleep(3000);


    }

    public void enterBu(){
       // DriverUtil.waitForElementVisible("//*[contains(@id,'businessUnitId::lovIconId')]",10);

        try {
            StepReport.info("Inside Enter BU");
            PageLoadHelper.waitForJetPageReady(driver,10000);
            WebElement businessUnit = findElement(By.xpath("//*[contains(@id,'businessUnitId::content')]"));
            DriverUtil.waitForElementVisible("//*[contains(@id,'businessUnitId::content')]",90);
            DriverUtil.scrollIntoView(businessUnit);
            businessUnit.sendKeys("BI Payroll US Business Unit");
            businessUnit.sendKeys(Keys.TAB);

        }
        catch (Error e){
            WebElement businessUnit = findElement(By.xpath("//*[contains(@id,'businessUnitId::content')]"));
            businessUnit.sendKeys("BI Payroll US Business Unit");
            businessUnit.sendKeys(Keys.TAB);

        }


        /*findElement(By.xpath("//*[contains(@id,'businessUnitId::lovIconId')]")).click();
        //DriverUtil.sleep(4000);
        DriverUtil.waitForElementVisible("//a[text()='Search...']",10);
        findElement(By.xpath("//a[text()='Search...']")).click();
        DriverUtil.sleep(3000);
        findElement(By.xpath("//label[text()=' Business Unit']/preceding-sibling::input")).sendKeys("BI Payroll US Business Unit");
        findElement(By.xpath("//button[text()='Search']")).click();
        DriverUtil.sleep(4000);

       // DriverUtil.waitForElementVisible("//span[text()='BI Payroll US Business Unit']",10);
       // findElement(By.xpath("//span[text()='BI Payroll US Business Unit']")).click();
       WebElement buUnit= findElement(By.xpath("//span[text()='BI Payroll US Business Unit']"));
       DriverUtil.clickByJS(buUnit);
       StepReport.info("Clicked on BU search results");
       DriverUtil.sleep(5000);

           for(int i=0;i<3;i++) {

               try {
                   WebElement okButton=findElement(By.xpath("//button[contains(@id,'businessUnitId::lovDialogId::ok')]"));
                   DriverUtil.clickByJS(okButton);
                   StepReport.info("Trying for "+i+" Time");
                   StepReport.info("Clicked ok to select Bu");
                   break;
               } catch (Exception e) {
                   System.out.println(e.getMessage());
               }
           }

       DriverUtil.sleep(3000);
       executeScript("scroll(0, -450);");
*/


    }
    public  void clickNext(){
        StepReport.info("Click Next");
        try {
            PageLoadHelper.waitForJetPageReady(driver,10000);
            nextHref.click();
            DriverUtil.sleep(6000);


        }
        catch (Exception e){
            findElement(By.xpath("//span[contains(text(),'Ne')]/parent::a")).click();
            DriverUtil.sleep(6000);
        }
        catch (Error e){
            nextHref.click();
            DriverUtil.sleep(6000);


        }

    }

    public void clickSubmit() {
        try {
            PageLoadHelper.waitForJetPageReady(driver,10000);
            //DriverUtil.sleep(3000);
            submit.click();
            StepReport.info("Submitting Employee Hire Request in attempt 1");
        }
        catch (Error e){
            submit.click();
            StepReport.info("Submitting Employee Hire Request in attempt 2");
        }
        DriverUtil.sleep(4000);

        //DriverUtil.sleep(2000);
        // findElement(By.xpath("//span[contains(text(),'Su')]/parent::a")).sendKeys(Keys.RETURN);
        //DriverUtil.sleep(3000);
        //PageLoadHelper.waitForJetPageReady(driver);



        //DriverUtil.sleep(3000);

        for (int i = 0; i < 5; i++) {

            try {
                WebElement ok=findElement(By.xpath("//button[contains(@id,'okWarningDialog')]"));
                DriverUtil.clickByJS(ok);
                DriverUtil.sleep(3000);
                StepReport.info("Clicked Submit Request Approval Pop Up");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        for (int i = 0; i < 5; i++) {

            try {
               DriverUtil.clickByJS(findElement(By.xpath("//button[contains(@id,'okConfirmationDialog')]")));
               DriverUtil.sleep(3000);
               StepReport.info("Clicked OK Dialog post Submitting Request");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
    public void clickSave(){
        try {
            PageLoadHelper.waitForJetPageReady(driver,10000);
            StepReport.info("Clicked Save Employee Request in 1st Attempt");
            for(int i=0;i<2;i++){
                try {
                    save.click();
                    DriverUtil.sleep(3000);
                    break;
                }
                catch (Exception e2){
                    findElement(By.xpath("//div[@title='Save']/table/tbody")).click();
                }
            }


            }
        catch (Error e){
            DriverUtil.clickByJS(save);
            StepReport.info("Clicked Save Employee Hire Request in 2nd Attempt");
            DriverUtil.sleep(3000);
           }

        //DriverUtil.sleep(3000);
        try{
            findElement(By.xpath("//button[contains(@id,'okConfirmationDialog')]")).click();
            DriverUtil.sleep(3000);
        }
        catch (Exception e){
            save.click();
            StepReport.info("Clicked Save Employee Hire Request in 3rd Attempt");
            DriverUtil.sleep(3000);
            try {
                findElement(By.xpath("//button[contains(@id,'okConfirmationDialog')]")).click();
                DriverUtil.sleep(3000);
            }
            catch (Exception e1){
                save.sendKeys(Keys.RETURN);
                StepReport.info("Clicked Save Employee Hire Request in 4th Attempt");
                DriverUtil.sleep(3000);
                findElement(By.xpath("//button[contains(@id,'okConfirmationDialog')]")).click();
                DriverUtil.sleep(3000);
            }

        }

        StepReport.info("Clicked OK Dialog After Saving Employee Hire Request");
        DriverUtil.sleep(3000);
    }


    public void typeZipCode(){
        StepReport.info("Enter ZipCode ");
        PageLoadHelper.waitForElementClickable(typeZipCode);
        DriverUtil.sleep(2000L);
        typeZipCode.sendKeys("94065");
        DriverUtil.sleep(2000L);

    }

    public void LegalEmployerforNonworker(){
        StepReport.info("Enter Legal Employer","ZHRX-US-JP-LE and PSU-3MTMA");
        DriverUtil.sleep(4000);
        PageLoadHelper.waitForElementVisible(legalEmpInput);
        DriverUtil.sleep(2000);
        legalEmpInput.sendKeys("ZHRX-US-JP-LE and PSU-3MTMA");
        DriverUtil.sleep(2000);
        legalEmpInput.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000);

    }

    public void selectNonWorkerType() {
        DriverUtil.sleep(2000);
        StepReport.info("Selecting nonworker type");
        /*String xpath = "//*[contains(@id,'AP1:selectOneChoice4::content')]";
        WebElement typeDropdown = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(typeDropdown);
        Select typeElement = new Select(typeDropdown);
        typeElement.selectByVisibleText("Nonworker");*/
        String xpath = "//*[text()='Nonworker']";
        WebElement nonworker = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.clickByJS(nonworker);
        DriverUtil.sleep(2000);
    }

    public  void clickSubmitEmployeeHire() {
        StepReport.info("Click Submit");

        try {
            PageLoadHelper.waitForElementClickable(submitButton);
            StepReport.info("Submit button is enabled");
            DriverUtil.doubleClick(submitButton);
            submitButton.submit();
        } catch (Exception e) {
            StepReport.info("Submit Button is not yet enabled");

        }

        DriverUtil.sleep(5000);
        StepReport.info("Click Yes");


        try {
            PageLoadHelper.waitForElementClickable(clickYes);
            StepReport.info("clickYes");
            clickYes.click();
        } catch (Exception e) {
            StepReport.info("clickYes not yet enabled");

        }

        DriverUtil.sleep(5000);
        StepReport.info("Click OK");
        PageLoadHelper.waitForElementClickable(clickOK, 20);
        DriverUtil.clickByJS(clickOK);
        DriverUtil.sleep(5000);

    }

    public void verifyHistoryTable() {
        StepReport.info("Verify History Table");
        if (PageLoadHelper.waitForElementVisible(historyTable) == null && !(bypassenabled.isDisplayed())) {
            throw new TestErrorException("The History Table is not visible");
        }
        DriverUtil.sleep(1000L);
    }

    public void waitUntilPageLoaded(String pageTitle) {
        StepReport.info("Verify if the page "+pageTitle+" is loaded");
        String xpath="//*[text()='"+pageTitle+"']";
        WebElement pageHeader=DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(pageHeader);
    }

    public void waitForOtherCompensationPage() {
        PageLoadHelper.waitForElementVisible(otherCompensation);
    }

    public void waitForCompensationAndOtherInformationPage() {
        PageLoadHelper.waitForElementVisible(compensation);
    }

    public void waitForReviewPage() {
        PageLoadHelper.waitForElementVisible(reviewPage);
    }


}
