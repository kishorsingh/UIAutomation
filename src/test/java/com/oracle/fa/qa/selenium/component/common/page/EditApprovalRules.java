package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;

public class EditApprovalRules extends BasePageObject<EditApprovalRules> {

    @FindBy(xpath = "//*[contains(text(),'Edit Approval Rules')]")
    WebElement editRulesButton;

    @FindBy(xpath = "//a/img[@title='Create']")
    WebElement createButton;

    @FindBy(xpath = "//*[contains(@id,'it1::content')]")
    WebElement ruleTextBox;

    @FindBy(xpath = "//*[contains(@id,'sbc1::content')]")
    WebElement ruleAlwaysApplies;

    @FindBy(xpath = "//*[contains(@id,'d1::ok')]")
    WebElement ruleOkButton;

    @FindBy(xpath = "//*[contains(@id,'d3::ok')]")
    WebElement conditionOkButton;

    @FindBy(xpath = "//*[contains(@id,'d2::ok')]")
    WebElement actionOkButton;

    @FindBy(xpath = "//a/span[text()='Add Condition']")
    WebElement addCondition;

    @FindBy(xpath = "//*[contains(@id,'aNDisp::content')]")
    WebElement attributeTextField;

    @FindBy(xpath = "//*[contains(@id,'csoc2::content')]")
    WebElement operatorDropDown;

    @FindBy(xpath = "//*[contains(@id,'it4::content')]")
    WebElement valueTextField;

    @FindBy(xpath = "//*[contains(@id,'it4::content')]")
    WebElement minValueTextField;

    @FindBy(xpath = "//*[contains(@id,'it5::content')]")
    WebElement maxValueTextField;

    @FindBy(xpath = "//button[text()='Add Action']")
    WebElement addAction;

    @FindBy(xpath = "//*[text()='Requisition Amount Between 44000 and 46000']")
    WebElement requisitionRule;

    @FindBy(xpath = "//*[text()='Document Type Equals Purchase Order AND Change Order Initiating Party Does not equal Supplier AND Document Total Amount Between 600000 and 700000']")
    WebElement purchaseOrderRule;

    @FindBy(xpath = "//*[contains(@id,'dynamicRegion1:1:AP1:soc2::content')]")
    WebElement routeUsingDropDown;

    @FindBy(xpath = "//*[contains(@id,'AP1:soc6::content')]")
    WebElement approvalchainDropDown;

    @FindBy(xpath = "//*[contains(@id,'it3::content')]")
    WebElement approvalLevels;

    @FindBy(xpath = "//*[contains(@id,'DoNotRouteBeyondWorker::content')]")
    WebElement topWorker;

    @FindBy(xpath = "//a/span[text()='Save']")
    WebElement save;

    @FindBy(xpath = "//a/span[text()='Deploy']")
    WebElement deploy;

    @FindBy(xpath="//button[@accesskey='Y']")
    WebElement yesButton;

    @FindBy(xpath="(//*[text()='Confirmation']/following::button[text()='OK'])[1]")
    WebElement confirmOKButton;

    @FindBy(xpath = "//tr[contains(@id,'ATTp:cmi3')]/td[text()='Above']")
    WebElement above;

    @FindBy(xpath = "//*[text()='Document Type Equals Purchase Order']")
    WebElement documentType;

    @FindBy(xpath = "//button[text()='Add Another']")
    WebElement addAnother;

    @FindBy(xpath = "//*[text()='Rules']/following::img[@title='Delete'][1]")
    WebElement deleteRules;

    @FindBy(xpath = "//label[@class='af_selectOneChoice_label-text' and text()='Route Using']/following::select")
    WebElement routeUsingSelect;

    @FindBy(xpath = "//label[@class='af_selectOneChoice_label-text' and text()='User Type']/following::select")
    WebElement userTypeSelector;

    @FindBy(xpath = "//label[@class='af_inputListOfValues_label-text' and text()='Worker']/following::input")
    WebElement workerInput;

    @FindBy(xpath = "//*[text()='Error']")
    WebElement error;

    @FindBy(xpath = "//img[@title='Query By Example']")
    WebElement queryByExample;

    @FindBy(xpath = "(//*[@title='Clear All']/following::input)[1]")
    WebElement ruleSearch;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(editRulesButton) == null) {
            throw new TestErrorException("The Edit Rules button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void clickCreate() {
        StepReport.info("Click Create Rule");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(createButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(createButton);
        DriverUtil.sleep(2000L);
    }

    public void clickDocumentType() {
        StepReport.info("Click Document Type");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(documentType);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(documentType);
        DriverUtil.sleep(2000L);
    }

    public void typeRuleName(String ruleName) {
        StepReport.info("Type Rule Name");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(ruleTextBox);
        DriverUtil.sleep(2000L);
        ruleTextBox.sendKeys(ruleName);
        DriverUtil.sleep(3000L);
        ruleTextBox.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void clickRuleAlwaysApplies() {
        StepReport.info("Click Rule Always Applies");
        PageLoadHelper.waitForElementClickable(ruleAlwaysApplies);
        DriverUtil.sleep(1000L);
        ruleAlwaysApplies.click();
        DriverUtil.sleep(1000L);
    }

    public void clickRuleOK() {
        StepReport.info("Click OK");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(ruleOkButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(ruleOkButton);
        DriverUtil.sleep(2000L);
    }

    public void clickConditionOK() {
        StepReport.info("Click OK");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(conditionOkButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(conditionOkButton);
        DriverUtil.sleep(2000L);
    }

    public void clickAddAnother() {
        StepReport.info("Click Add Another");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(addAnother);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(addAnother);
        DriverUtil.sleep(2000L);
    }

    public void clickActionOK() {
        StepReport.info("Click OK");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(actionOkButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(actionOkButton);
        DriverUtil.sleep(2000L);
    }

    public void addCondition(String conditionType, String attributeName, String operator, String minValue, String maxValue, boolean addAnother) {
        clickAddCondition();
        selectConditionType(conditionType);
        typeAttribute(attributeName);
        selectOperator(operator);
        typeMinValue(minValue);
        typeMaxValue(maxValue);
        if(addAnother == true){
            clickAddAnother();
        }
        else {
            clickConditionOK();
        }
    }

    public void addCondition(String conditionType, String attributeName, String operator, String value, boolean addAnother) {
        clickAddCondition();
        selectConditionType(conditionType);
        typeAttribute(attributeName);
        selectOperator(operator);
        typeValue(value);
        if(addAnother == true){
            clickAddAnother();
        }
        else {
            clickConditionOK();
        }
    }

    public void addConditionUserDefinedAttribute(String attributeName, String operator, String value, boolean addAnother) {
        clickAddCondition();
        selectConditionType("User-defined attribute");
        selectAttribute(attributeName);
        selectOperator(operator);
        typeValue(value);
        if(addAnother == true){
            clickAddAnother();
        }
        else {
            clickConditionOK();
        }
    }

    public void addPOCondition(String minValue, String maxValue) {
        clickAddCondition();
        typeAttribute("Document Type");
        selectOperator("Equals");
        selectValue("Purchase Order");
        clickAddAnother();

        typeAttribute("Change Order Initiating Party");
        selectOperator("Does not equal");
        selectValue("Supplier");
        clickAddAnother();

        typeAttribute("Document Total Amount");
        selectOperator("Between");
        typeMinValue(minValue);
        typeMaxValue(maxValue);
        clickConditionOK();

    }

    public void clickAddCondition() {
        StepReport.info("Click Add Condition");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(addCondition);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(addCondition);
        DriverUtil.sleep(2000L);

    }

    public void typeAttribute(String attributeName) {
        StepReport.info("Type Attribute");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(attributeTextField);
        DriverUtil.sleep(2000L);
        attributeTextField.sendKeys(attributeName);
        DriverUtil.sleep(3000L);
        attributeTextField.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
        attributeTextField.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void selectOperator(String operator) {
        StepReport.info("Select Operator");
        Select operatorElement = new Select(operatorDropDown);
        operatorElement.selectByVisibleText(operator);
        DriverUtil.sleep(2000L);
    }

    public void selectConditionType(String conditionType) {
        StepReport.info("Select Condition Type");
        String xpath = "//*[contains(@id,'soc18::content')]";
        WebElement typeDropdown = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(typeDropdown);
        Select typeElement = new Select(typeDropdown);
        typeElement.selectByVisibleText(conditionType);
        DriverUtil.sleep(2000L);
    }

    public void selectAttribute(String attributeName) {
        StepReport.info("Select Attribute Name");
        String xpath = "//*[contains(@id,'soc20::content')]";
        WebElement typeDropdown = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(typeDropdown);
        Select typeElement = new Select(typeDropdown);
        typeElement.selectByVisibleText(attributeName);
        DriverUtil.sleep(2000L);
    }

    public void typeValue(String value) {
        StepReport.info("Type Attribute");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(valueTextField);
        DriverUtil.sleep(2000L);
        valueTextField.sendKeys(value);
        DriverUtil.sleep(3000L);
        valueTextField.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void typeMinValue(String value) {
        StepReport.info("Type Attribute");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(minValueTextField);
        DriverUtil.sleep(2000L);
        minValueTextField.sendKeys(value);
        DriverUtil.sleep(3000L);
        minValueTextField.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void typeMaxValue(String value) {
        StepReport.info("Type Attribute");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(maxValueTextField);
        DriverUtil.sleep(2000L);
        maxValueTextField.sendKeys(value);
        DriverUtil.sleep(3000L);
        maxValueTextField.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void selectValue(String type) {
        StepReport.info("Select Value:"+type);
        WebElement webElement = DriverUtil.getElement(By.xpath("//*[contains(@id,'soc7::content')]"));
        Select select = new Select(webElement);
        select.selectByVisibleText(type);
        DriverUtil.sleep(2000L);

    }

    public void selectApprovalChain(String type) {
        StepReport.info("Select Value:"+type);
        PageLoadHelper.waitForElementVisible(approvalchainDropDown);
        DriverUtil.sleep(2000L);
        Select routeElement = new Select(approvalchainDropDown);
        routeElement.selectByVisibleText(type);
        DriverUtil.sleep(3000L);

    }



    public void addAction(String routeUsing, String userType, String worker) {
        clickAddAction();
        selectRouteUsing(routeUsing);
        selectUserType(userType);
        typeWorker(worker);
        clickActionOK();
    }


    public void selectRouteUsing(String routeUsing) {
        StepReport.info("Select Route Using");
        PageLoadHelper.waitForElementVisible(routeUsingSelect);
        DriverUtil.sleep(2000L);
        Select routeElement = new Select(routeUsingSelect);
        routeElement.selectByVisibleText(routeUsing);
        DriverUtil.sleep(3000L);
    }

    public void selectUserType(String userType) {
        StepReport.info("Select User Type");
        PageLoadHelper.waitForElementVisible(userTypeSelector);
        DriverUtil.sleep(2000L);
        Select routeElement = new Select(userTypeSelector);
        routeElement.selectByVisibleText(userType);
        DriverUtil.sleep(3000L);
    }

    /*public boolean isRuleExist(String ruleName) {
        try {
            WebElement rule = DriverUtil.getElement(By.xpath("//*[text()='"+ruleName+"']"));
            if(SelUtil.isDisplayed(rule,20)) {
                System.out.println("Approval Rules "+ruleName+" are already created");
                return true;
            }
        }
        catch(Exception e) {
            System.out.println("Approval Rules "+ruleName+" are not Configured. Continue with rule creation");
            return false;
        }
        return false;

    }*/

    public boolean isRuleExist(String ruleName) {
        StepReport.info("Click Query By Example");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementClickable(queryByExample);
        DriverUtil.sleep(2000L);

        String xpath = "//img[@title='Query By Example']/parent::a/parent::div";
        WebElement qbe = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(qbe);
        String classValue = qbe.getAttribute("class");
        System.out.println("Class Value:"+classValue);
        DriverUtil.sleep(2000);
        if(!classValue.contains("Selected")) {
            DriverUtil.clickByJS(queryByExample);
            DriverUtil.sleep(5000L);
        }

        StepReport.info("Search Rule", ruleName);
        DriverUtil.sleep(5000L);
        PageLoadHelper.waitForElementClickable(ruleSearch);
        DriverUtil.sleep(2000L);
        ruleSearch.clear();
        DriverUtil.sleep(2000L);
        ruleSearch.sendKeys(ruleName);
        DriverUtil.sleep(5000L);
        ruleSearch.sendKeys(Keys.ENTER);

        try {
            WebElement rule = DriverUtil.getElement(By.xpath("//*[text()='"+ruleName+"']"));
            if(SelUtil.isDisplayed(rule,30)) {
                System.out.println("Approval Rules "+ruleName+" are already created");
                return true;
            }
        }
        catch(Exception e) {
            System.out.println("Approval Rules "+ruleName+" are not Configured. Continue with rule creation");
            return false;
        }
        return false;

    }

    public void typeWorker(String worker) {
        StepReport.info("Type Worker");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(workerInput);
        DriverUtil.sleep(2000L);
        workerInput.sendKeys(worker);
        DriverUtil.sleep(3000L);
        workerInput.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }


    public void addAction(String routeUsing, String approvalChainType, String levels, String worker) {
        clickAddAction();
        selectRouteType(routeUsing);
        selectApprovalChain(approvalChainType);
        typeApprovalLevels(levels);
        typeTopWorker(worker);
        clickActionOK();
    }

    public void addAction(String routeUsing, String approvalGroup, boolean autoApprove) {
        clickAddAction();
        //selectRouteType(routeUsing);
        selectApprovalGroup(approvalGroup);
        if(autoApprove == true){
            selectautoApproveCheckBox();
        }
        clickActionOK();
    }

    public void addAutoApproval(String defaultOutcome) {
        clickAddAction();
        selectActionType("Automatic");
        selectDefaultOutcome(defaultOutcome);
        clickActionOK();
    }

    public void clickAddAction() {
        StepReport.info("Click Add Action");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(addAction);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(addAction);
        DriverUtil.sleep(2000L);
    }

    public void selectActionType(String actionType) {
        StepReport.info("Select Action Type");
        String xpath = "//*[contains(@id,'AP1:soc1::content')]";
        WebElement typeDropdown = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(typeDropdown);
        Select typeElement = new Select(typeDropdown);
        typeElement.selectByVisibleText(actionType);
        DriverUtil.sleep(2000L);
    }

    public void selectDefaultOutcome(String outcome) {
        StepReport.info("Select Default Outcome");
        String xpath = "//*[contains(@id,'soc9::content')]";
        WebElement typeDropdown = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(typeDropdown);
        Select typeElement = new Select(typeDropdown);
        typeElement.selectByVisibleText(outcome);
        DriverUtil.sleep(2000L);
    }

    public void selectApprovalGroup(String approvalGroup) {
        StepReport.info("Select Approval Group");
        String xpath = "//select[contains(@id,'soc5::content')]";
        WebElement typeDropdown = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(typeDropdown);
        Select typeElement = new Select(typeDropdown);
        typeElement.selectByVisibleText(approvalGroup);
        DriverUtil.sleep(2000L);
    }

    public void selectautoApproveCheckBox() {
        StepReport.info("Check box: Automatically approve if group returns no approvers");
        String xpath = "//*[contains(@id,'allowEmptyGroup::content')]";
        WebElement checkbox = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementVisible(checkbox);
        checkbox.click();
        DriverUtil.sleep(2000L);
    }

    public void selectRouteType(String routeType) {
        StepReport.info("Select Route Type");
        PageLoadHelper.waitForElementVisible(routeUsingDropDown);
        DriverUtil.sleep(2000L);
        Select routeElement = new Select(routeUsingDropDown);
        routeElement.selectByVisibleText(routeType);
        DriverUtil.sleep(3000L);
    }

    public void typeApprovalLevels(String levels) {
        StepReport.info("Type Levels");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(approvalLevels);
        DriverUtil.sleep(2000L);
        approvalLevels.sendKeys(levels);
        DriverUtil.sleep(3000L);
        approvalLevels.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public boolean isRequisitionRuleConfigured() {
        try { if(requisitionRule.isDisplayed()) {
            System.out.println("Requisition Rule is already Configured");
        }}
        catch(Exception e) {
            System.out.println("Requisition Rule is not Configured. Continue with rule creation");
            return false;
        }
        return true;
    }

    public boolean[] isUserDefinedRuleConfigured(String attributeName) {

        boolean[] ruleConfig = new boolean[2];

        try {
            WebElement rule1 = DriverUtil.getElement(By.xpath("//*[text()='"+attributeName+" Greater than 1000000 AND "+attributeName+" Less than or equal to 5000000']"));
            System.out.println("Rule1:"+rule1);

            if(SelUtil.isDisplayed(rule1,20)) {
            System.out.println("Approval Rule 1 based on user-defined Category attribute are already created");
            ruleConfig[0]=true;
        }}
        catch(Exception e) {
            System.out.println("Approval Rule 1 based on user-defined Category attribute are not Configured. Continue with rule creation");
            ruleConfig[0]= false;
        }

        try {
            WebElement rule2 = DriverUtil.getElement(By.xpath("//*[text()='"+attributeName+" Greater than 5000000']"));
            System.out.println("Rule2:"+rule2);

            if(SelUtil.isDisplayed(rule2,20)) {
                System.out.println("Approval Rule 2 based on user-defined Category attribute are already created");
                ruleConfig[1]=true;
            }}
        catch(Exception e) {
            System.out.println("Approval Rule 2 based on user-defined Category attribute are not Configured. Continue with rule creation");
            ruleConfig[1]=false;
        }

        return ruleConfig;
    }

    public boolean isRequisitionRuleConfigured(String ruleName) {
        try {
            WebElement rule = DriverUtil.getElement(By.xpath("//*[text()='"+ruleName+"']"));
            if(rule!=null && rule.isDisplayed()) {
            System.out.println("Requisition Rule "+ruleName+"is already Configured");
        }}
        catch(Exception e) {
            System.out.println("Requisition Rule "+ruleName+" is not Configured. Continue with rule creation");
            return false;
        }
        return true;
    }

    public boolean isPORuleConfigured() {
        try {if(purchaseOrderRule.isDisplayed()) {
            System.out.println("Purchase Order is already Configured");
        }}
        catch(Exception e) {
            System.out.println("Purchase Order is not Configured. Continue with rule creation");
            return false;
        }
        return true;
    }

    public void typeTopWorker(String worker) {
        StepReport.info("Type Top Worker in Hierarchy");
        DriverUtil.sleep(3000L);
        PageLoadHelper.waitForElementVisible(topWorker);
        DriverUtil.sleep(2000L);
        topWorker.sendKeys(worker);
        DriverUtil.sleep(3000L);
        topWorker.sendKeys(Keys.TAB);
        DriverUtil.sleep(1000L);
        topWorker.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void clickSave() {
        StepReport.info("Click Save");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(save);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(save);
        DriverUtil.sleep(2000L);
    }

    public void clickDeploy() {
        StepReport.info("Click Deploy");
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(deploy);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(deploy);
        DriverUtil.sleep(2000L);
    }

    public void closeWarningPopup() {
        boolean isWarningPopupNotExists=true;
        int counter=1;
        while((isWarningPopupNotExists) && (counter<6)) {
            try {
                if(yesButton.isDisplayed()) {
                    DriverUtil.clickByJS(yesButton);
                    DriverUtil.sleep(2000L);
                    PageLoadHelper.waitForElementClickable(confirmOKButton);
                    DriverUtil.sleep(2000L);
                    DriverUtil.clickByJS(confirmOKButton);
                    isWarningPopupNotExists=false;
                    System.out.println("Warning Popup Displayed");
                }else {
                    System.out.println("Warning Popup not Displayed");
                }
            }catch(Exception e) {
                System.out.println("Warning Popup not Displayed");
            }
            DriverUtil.sleep(3000L);
            counter++;
        }
    }

    public void checkForError() {
        DriverUtil.sleep(2000L);
        if (PageLoadHelper.waitForElementVisible(error,20) != null) {
            throw new TestErrorException("Error thrown while deploying the rules");
        }
        StepReport.info("Error not displayed while deploying the rules");
    }

    public void deleteRule(String ruleName) {
        StepReport.info("Delete Rule "+ruleName);
        DriverUtil.sleep(5000);
        String xpath = "//*[text()='"+ruleName+"']";
        WebElement rule = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(rule);
        DriverUtil.clickByJS(rule);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(deleteRules);
        DriverUtil.sleep(5000L);
    }


}
