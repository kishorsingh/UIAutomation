package com.oracle.fa.qa.selenium.component.hcm.page;

import com.github.wiselenium.factory.annotation.Component;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@Component
public class ConfigureRulePage extends HCMBasePageObject {
    @FindBy(xpath = "//*[name()='svg']//*[name()='g' and @id='3' and @cursor='pointer']")
    WebElement rule1;
    @FindBy(xpath = "//*[name()='svg']//*[name()='g' and @id='6']")
    WebElement managementHierarchy;
    @FindBy(xpath = "//select")
    WebElement select;
    @FindBy(xpath = "//a/img[@alt='Add New Rule']")
    WebElement addNewRule;
    @FindBy(xpath = "//a/img[@alt='Delete Selected Items']")
    WebElement deleteRuleButton;
    @FindBy(xpath = "//*[name()='g' and @transform='matrix(1,0,0,1,285,0)']")
    WebElement rule1then;
    @FindBy(xpath = "//*[name()='g' and @transform='matrix(1,0,0,1,285,31.5)']")
    WebElement rule2then;

    @FindBy(xpath = "(//*[text()='Confirmation']/following::button[text()='OK'])[1]")
    WebElement confirmationOK;

    @FindBy(xpath = "//input[contains(@id,'Users::content')]")
    WebElement usersInput;

    @FindBy(xpath = "//a/span/span[text()='m']")
    WebElement submit;

    @FindBy(xpath = "//*[text()='Warning']/following::button[text()='Yes']")
    WebElement yesButton;


    @Override
    protected void isLoaded() {
       // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(addNewRule) == null) {

            throw new TestErrorException("The configure rule page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void configureRule(String ruleName, String condition, String user) {
        try{
            DriverUtil.waitForElementVisible("//a/img[@alt='Add New Rule']",30);
            //Delete the second rule if it exists
             if (findElements(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[2]")).size() > 0) {
                 StepReport.info("Rule2 Exists : Delete the rule2");
              deleteRule();
              DriverUtil.sleep(2000L);  }
        }catch (Error e) {
            if (findElements(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[1]")).size()> 0){
                StepReport.info("Rule1 Exists : Delete the rule1");
                deleteruleone();
                DriverUtil.sleep(2000);}
        }

           //Proceed to add a new rule.
            // Only for hire an employee case : after clicking "then" of rule2, it will delete all the rules and start creating rule afresh.
            StepReport.info("Click Add New Rule");
            DriverUtil.sleep(2000L);
            DriverUtil.clickByJS(addNewRule);
            //addNewRule.click();
            DriverUtil.sleep(6000);


        Actions ac = new Actions(driver);


        try {
            DriverUtil.waitForElementVisible("//*[name()='g' and @transform='matrix(1,0,0,1,285,0)']",20);
            DriverUtil.sleep(5000);
            DriverUtil.scrollIntoView(rule2then);
            DriverUtil.sleep(2000);
            rule2then.click();
            StepReport.info("Clicked on Then");
            DriverUtil.sleep(5000);
        }
        catch (Exception e){
            e.getMessage();
            DriverUtil.scrollIntoView(rule2then);
            DriverUtil.sleep(2000);
            rule2then.click();
            DriverUtil.sleep(5000);
        }


        if(ruleName.equals("Hire an Employee") && condition.equalsIgnoreCase("SelfApproval"))
           {
               StepReport.info("Delete all the existing rules and start afresh");
               //This part of code is added as FABATS left an incorrect hire an employee rule, which was causing issues while doing hire an employee action. Hence deleting complete rule and configuring from the scratch
               if (findElements(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[2]")).size()> 0){
                   StepReport.info("Hire an employee -> Rule2 Exists : Delete the rule2");
                   deleteruleone();
                   DriverUtil.sleep(2000);}
               if (findElements(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[1]")).size()> 0){
                   StepReport.info("Hire an employee -> Rule1 Exists : Delete the rule1");
                   deleteruleone();
                   DriverUtil.sleep(2000);}
               //After cleaning up all the rules proceed to add a new rule with management hierarchy
               StepReport.info("Click Add New Rule");
               DriverUtil.sleep(2000L);
               DriverUtil.clickByJS(addNewRule);
               DriverUtil.sleep(6000);
               DriverUtil.scrollIntoView(rule1then);
               DriverUtil.sleep(2000);
               rule1then.click();
               StepReport.info("Clicked on Then");
               DriverUtil.sleep(6000);
               //Find the add button for Self auto approve hierarchy
            //DriverUtil.waitForElementVisible(".//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:6:cil1::icon']",30);
            //DriverUtil.sleep(3000);
            findElement(
                    By.xpath((".//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:6:pgl6']/tbody/tr/td[1]"))).click();
            DriverUtil.sleep(5000);
            DriverUtil.waitForElementVisible(By.xpath("//*[name()='g' and @transform='matrix(1,0,0,1,387.5,-2.5)']"), 60);
            DriverUtil.sleep(5000);
            }


        if(ruleName.equals("Hire an Employee") && condition.equalsIgnoreCase("managementhierarchy")|| ruleName.equalsIgnoreCase("Add Contingent Worker")) {
             StepReport.info("Delete all the existing rules and start afresh");
             //This part of code is added as FABATS left an incorrect hire an employee rule, which was causing issues while doing hire an employee action. Hence deleting complete rule and configuring from the scratch


              if (findElements(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[2]")).size()> 0){
                 StepReport.info("Hire an employee -> Rule2 Exists : Delete the rule2");
                 deleteruleone();
                 DriverUtil.sleep(2000);}
             if (findElements(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[1]")).size()> 0){
                 StepReport.info("Hire an employee -> Rule1 Exists : Delete the rule1");
                 deleteruleone();
                 DriverUtil.sleep(2000);}
             //After cleaning up all the rules proceed to add a new rule with management hierarchy
             StepReport.info("Click Add New Rule");
             DriverUtil.sleep(2000L);
             DriverUtil.clickByJS(addNewRule);
             DriverUtil.sleep(6000);
             DriverUtil.scrollIntoView(rule1then);
             DriverUtil.sleep(2000);
             rule1then.click();
             StepReport.info("Clicked on Then");
             DriverUtil.sleep(5000);
             StepReport.info("Click then and Add Management hierarchy");
             DriverUtil.waitForElementVisible("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:3:cil1::icon']",30);
             DriverUtil.sleep(3000);
             //Find the add button for management hierarchy
            findElement(By.xpath(("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:3:cil1::icon']"))).click();
            DriverUtil.sleep(5000);
            //Click the added rectangle - Management hierarchy and change appropriate values
            StepReport.info("Change values in the management hierarchy");
           // findElement(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[1]")).click();
            findElement(By.xpath("//*[name()='g' and @transform='matrix(1,0,0,1,387.5,-2.5)']")).click();
            DriverUtil.sleep(3000);
            DriverUtil.waitForElementVisible("//tr//td/div/a[@title='increment']", 10);
            findElement(By.xpath("//tr//td/div/a[@title='increment']")).click();
            DriverUtil.sleep(3000);
            /*findElement(By.xpath("//td/label[text()='Top Approver']/parent::td/following-sibling::td/select")).click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("//td/label[text()='Top Approver']/parent::td/following-sibling::td/select/option[@value='Second Level Manager']")).click();*/
            DriverUtil.clickByJS(findElement(By.xpath("//*[text()='Top Approver']/following::*[text()='Second Level Manager']")));
            DriverUtil.sleep(4000);
        }

        if(ruleName.equalsIgnoreCase("Add NonWorker")){
            DriverUtil.waitForElementVisible("//*[contains(@id,'TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:7:cil1::icon')]",30);
            DriverUtil.sleep(3000);
            findElement(By.xpath("//*[contains(@id,'TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:7:cil1::icon')]")).click();
            DriverUtil.sleep(4000);
            //DriverUtil.waitForElementVisible(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[1]"), 40);
            WebElement users = findElement(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[2]"));
            DriverUtil.scrollIntoView(users);
            //ac.moveToElement(users).build().perform();
            users.click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:selectOneChoiceU2::content']")).click();
            DriverUtil.sleep(3000);
            findElement(By.xpath("//li[contains(text(),'user')]")).click();
            DriverUtil.sleep(3000);

            PageLoadHelper.waitForElementVisible(usersInput);
            usersInput.sendKeys(user);
            DriverUtil.sleep(5000);

        }

        if(ruleName.equalsIgnoreCase("Change Location")){
            DriverUtil.waitForElementVisible("//*[contains(@id,'TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:7:cil1::icon')]",30);
            DriverUtil.sleep(3000);
            findElement(By.xpath("//*[contains(@id,'TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:7:cil1::icon')]")).click();
            DriverUtil.waitForElementVisible(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[1]"), 10);
            WebElement users = findElement(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[2]"));
            DriverUtil.scrollIntoView(users);
            //ac.moveToElement(users).build().perform();
            users.click();
            DriverUtil.sleep(2000);
            WebElement sel=findElement(By.xpath("//*[contains(@id,'SP1:selectOneChoiceU2::content')]"));
            Select select = new Select(sel);
            DriverUtil.sleep(3000);
            select.selectByValue("4");
            DriverUtil.sleep(3000);
            findElement(By.xpath("//*[contains(@id,'Users::lovIconId')]")).click();
            DriverUtil.sleep(3000);
            findElement(By.xpath("//a[text()='Search...']")).click();
            DriverUtil.sleep(3000);
            findElement(By.xpath("//label[text()=' User Name']/preceding-sibling::input")).sendKeys("sballard");
            findElement(By.xpath("//button[text()='Search']")).click();
            DriverUtil.sleep(3000);
            DriverUtil.clickByJS(findElement(By.xpath("//span[text()='Scott Ballard']")));
            DriverUtil.sleep(2000);
            findElement(By.xpath("//button[text()='OK']")).click();
            DriverUtil.sleep(5000);
        }

        if(ruleName.equals("Change Manager")) {
            DriverUtil.waitForElementVisible("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:3:cil1::icon']",30);
            DriverUtil.sleep(3000);
            findElement(
                    By.xpath(("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:3:cil1::icon']"))).click();
            DriverUtil.sleep(5000);
            DriverUtil.waitForElementVisible(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[1]"), 30);
            WebElement manageHierarchy = findElement(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[2]"));
            DriverUtil.scrollIntoView(manageHierarchy);
            DriverUtil.sleep(5000);
            manageHierarchy.click();
            DriverUtil.sleep(3000);

            DriverUtil.sleep(4000);
            findElement(By.xpath("//*[contains(@title,'Worker')]")).click();
            DriverUtil.sleep(5000);
            DriverUtil.clickByJS(findElement(By.xpath("//*[@title='Requestor'] | //*[text()='Requestor']")));
            DriverUtil.sleep(4000);
        }

        //This rule is specific to HCMBug27969763FYIAutoDismiss test : adding 2 blocks ; FYI should go to byarmouth, approval goes to sballard
        if(ruleName.equalsIgnoreCase("Promote")&& condition.equalsIgnoreCase("managementhierarchy") && user.equalsIgnoreCase("byarmouth")) {
            DriverUtil.waitForElementVisible("//*[contains(@id,'TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:7:cil1::icon')]",30);
            DriverUtil.sleep(3000);
            findElement(By.xpath("//*[contains(@id,'TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:7:cil1::icon')]")).click();
            DriverUtil.sleep(4000);
            //DriverUtil.waitForElementVisible(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[1]"), 40);
            WebElement users = findElement(By.xpath("(//*[name()='g']/*[name()='rect' and @width='149'])[2]"));
            DriverUtil.scrollIntoView(users);
            //ac.moveToElement(users).build().perform();
            users.click();
            DriverUtil.sleep(3000);
            findElement(By.xpath(".//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:roc1::content']")).click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("//option[contains(text(),'Information only')]")).click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:selectOneChoiceU2::content']")).click();
            DriverUtil.sleep(2000);
            findElement(By.xpath("//option[contains(text(),'user')]")).click();
            DriverUtil.sleep(3000);
            findElement(By.xpath("//input[contains(@id,'Users::content')]")).sendKeys(user);
            DriverUtil.sleep(5000);
            findElement(
                    By.xpath(("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:3:cil1::icon']"))).click();
            DriverUtil.sleep(5000);

        }

        if(ruleName.equalsIgnoreCase("Promote")||ruleName.equalsIgnoreCase("Terminate")) {
            DriverUtil.waitForElementVisible("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:3:cil1::icon']", 30);
            DriverUtil.sleep(3000);
            findElement(
                    By.xpath(("//*[@id='_FOpt1:_FOr1:0:_FOSritemNode_Tools_TransactionConsole_TransactionAdministratorConsol:0:_FOTr2:2:SP1:appItr:3:cil1::icon']"))).click();
            DriverUtil.sleep(5000);
        }
            StepReport.info("Click Save");
            findElement(By.xpath("//a/span[text()='Save']")).click();
            DriverUtil.sleep(10000);

            StepReport.info("Click OK in confirmation");
            PageLoadHelper.waitForElementVisible(confirmationOK);
            confirmationOK.click();
            DriverUtil.sleep(10000);

            StepReport.info("Click Submit");
            PageLoadHelper.waitForElementVisible(submit);
            submit.click();
            DriverUtil.sleep(5000);

            StepReport.info("Click Yes Button");
            PageLoadHelper.waitForElementVisible(yesButton);
            yesButton.click();
            DriverUtil.sleep(5000);
            for(int i=0;i<3;i++) {
            try{
                    StepReport.info("Click OK Button in confirmation");
                    findElement(By.xpath("(//button[text()='OK'])[2]")).click();
                    break;

                }
            catch (Exception e){
                System.out.println(e.getMessage());

            }
            }

          //  findElement(By.xpath("//button[text()='OK']")).click();
            DriverUtil.sleep(10000);
            // findElement(By.xpath("//div[contains(text(),'approval rules')]/parent::div/parent::td/parent::tr/following-sibling::tr//button")).click();


    }







    public void deleteRule() {
        Actions ac = new Actions(FrameworkContext.getInstance().getWebDriver());
        //WebElement ruleSelect=findElement(By.xpath("//*[name()='svg']//*[name()='g' and @id='3' and @cursor='pointer']"));
        //ruleSelect.sendKeys(Keys.RETURN);
        DriverUtil.sleep(4000);

        WebElement ruleSelect=null;
        List<WebElement> elements = findElements(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[2]"));
        if (elements.size() > 0) {
            ruleSelect = elements.get(0);
        } else {
            System.out.println("Rule2 actually not exists");
            return;
        }
        DriverUtil.scrollIntoView(ruleSelect);
        DriverUtil.sleep(2000);
        //ac.moveToElement(ruleSelect).build().perform();
        ruleSelect.click();
        DriverUtil.sleep(4000);
        StepReport.info("Selected Rule");
        DriverUtil.waitForElementVisible("//a/img[@alt='Delete Selected Items']",20);
        StepReport.info("Deleting existing Rule");
        DriverUtil.sleep(5000);
        String xpath="//a/img[@alt='Delete Selected Items']";
        WebElement deleteElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", deleteElem);
        DriverUtil.sleep(5000);

        DriverUtil.waitForElementVisible("//td/button[@accesskey='Y']",20);
        findElement(By.xpath("//td/button[@accesskey='Y']")).click();
        DriverUtil.sleep(4000);
        StepReport.info("Clicked on Yes");

    }

    public void deleteruleone() {
        Actions ac = new Actions(FrameworkContext.getInstance().getWebDriver());
        DriverUtil.sleep(4000);

       // WebElement ruleSelect=findElement(By.xpath("//*[name()='svg']//*[name()='g' and @id='1']"));
       // WebElement ruleSelect=findElement(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[1]"));
       // WebElement ruleSelect=findElement(By.xpath("(//*[name()='svg']//*[name()='g']//*[name()='ellipse'])[1]"));
        WebElement ruleSelect=findElement(By.xpath("//*[name()='g' and @transform='matrix(1,0,0,1,25,6.5)']"));
        DriverUtil.scrollIntoView(ruleSelect);
        DriverUtil.sleep(2000);
        //ac.moveToElement(ruleSelect).build().perform();
        ruleSelect.click();
        DriverUtil.sleep(4000);
        StepReport.info("Selected Rule");
        DriverUtil.waitForElementVisible("//a/img[@alt='Delete Selected Items']",20);
        StepReport.info("Deleting existing Rule");
        DriverUtil.sleep(5000);
        String xpath="//a/img[@alt='Delete Selected Items']";
        WebElement deleteElem=DriverUtil.getElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].click();", deleteElem);
        DriverUtil.sleep(5000);

        DriverUtil.waitForElementVisible("//td/button[@accesskey='Y']",20);
        findElement(By.xpath("//td/button[@accesskey='Y']")).click();
        DriverUtil.sleep(4000);
        StepReport.info("Clicked on Yes");

    }


}
