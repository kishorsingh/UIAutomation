package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.fa.qa.selenium.component.common.page.EditApprovalRules;
import com.oracle.fa.qa.selenium.component.common.page.ManageRequisitionApprovals;
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
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class ManageUserDefAttr extends BasePageObject<ManageUserDefAttr> {

    @FindBy(xpath = "//img[@alt='Create']")
    WebElement createButton;

    @FindBy(xpath = "//img[@alt='Delete']")
    WebElement deleteButton;

    @FindBy(xpath = "//button[@accesskey='Y']")
    WebElement yesButton;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(createButton) == null) {
            throw new TestErrorException("The create button is not clickable after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Create Button Displayed");
    }

    public void clickCreateIcon() {
        StepReport.info("Click Create icon");
        PageLoadHelper.waitForElementClickable(createButton);
        createButton.click();
        DriverUtil.sleep(5000L);
    }

    public void enterAttributeName(String attrName) {
        StepReport.info("Enter attribute name");
        DriverUtil.sleep(2000L);
        String xpath = this.calculateInputXPathInDialog("User-Defined Attribute", "input");
        WebElement attrNameField = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(attrNameField);
        DriverUtil.sleep(2000L);
        attrNameField.clear();
        DriverUtil.sleep(2000L);
        attrNameField.sendKeys(attrName);
        DriverUtil.sleep(2000L);
    }

    public void selectAttributeType(String attrType) {
        StepReport.info("Select attribute type");
        DriverUtil.sleep(2000L);
        String xpath = this.calculateInputXPathInDialog("Type", "select");
        WebElement attrTypeField = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(attrTypeField);
        DriverUtil.sleep(2000L);
        Select statusSelect = new Select(attrTypeField);
        statusSelect.selectByVisibleText(attrType);
        DriverUtil.sleep(5000L);
    }

    public void selectAttributeAttr(String attrAttr) {
        StepReport.info("Select attribute attribute");
        DriverUtil.sleep(2000L);
        String xpath = this.calculateInputXPathInDialog("Attribute", "select");
        WebElement attrAttrField = DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(attrAttrField);
        DriverUtil.sleep(2000L);
        Select attrSelect = new Select(attrAttrField);
        attrSelect.selectByVisibleText(attrAttr);
        DriverUtil.sleep(5000L);
    }

    public void inputAttributeFilter(String matchType) {
        StepReport.info("Input attribute filter");
        DriverUtil.sleep(2000L);
        String xpath1 = this.calculateInputXPathInDialog("Match Using", "select");
        WebElement field1 = DriverUtil.getElement(By.xpath(xpath1));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(field1);
        DriverUtil.sleep(2000L);
        Select field1Select = new Select(field1);
        field1Select.selectByVisibleText("Hierarchy");
        DriverUtil.sleep(5000L);

        String xpath2 = xpath1 + "//following::select";
        WebElement field2 = DriverUtil.getElement(By.xpath(xpath2));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(field2);
        DriverUtil.sleep(2000L);
        Select field2Select = new Select(field2);
        field2Select.selectByVisibleText(matchType);
        DriverUtil.sleep(5000L);
    }

    public void inputRollsUpToForCategory(String rollsUpTo) {
        StepReport.info("Input Rolls Up To for Cost Center");
        String xpath1 = this.calculateInputXPathInDialog("Match Using", "select");
        String xpath2 = xpath1 + "//following::select";
        String xpath3 = xpath2 + "//following::input";
        WebElement field3 = DriverUtil.getElement(By.xpath(xpath3));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(field3);
        DriverUtil.sleep(2000L);
        field3.clear();
        DriverUtil.sleep(2000L);
        field3.sendKeys(rollsUpTo);
        DriverUtil.sleep(5000L);

        List<WebElement> elements = DriverUtil.getElements(By.xpath("//li[@class='AFAutoSuggestItem']"));
        if (elements.size() > 0) {
            elements.get(0).click();
            DriverUtil.sleep(5000L);
        }
    }

    public void inputRollsUpToForCategoryBySearch(String rollsUpTo) {
        StepReport.info("Input attribute filter");
        String xpathRollsUpToSearchButton = "//a[@title='Search: Rolls up To']";
        WebElement searchRollsUpToButton = DriverUtil.getElement(By.xpath(xpathRollsUpToSearchButton));
        PageLoadHelper.waitForElementVisible(searchRollsUpToButton);
        searchRollsUpToButton.click();
        DriverUtil.sleep(5000L);

        String xpathCategoryNameField = this.calculateInputXPathInDialog("Category Name", "input");
        WebElement categoryNameField = DriverUtil.getElement(By.xpath(xpathCategoryNameField));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(categoryNameField);
        DriverUtil.sleep(2000L);
        categoryNameField.clear();
        DriverUtil.sleep(2000L);
        categoryNameField.sendKeys(rollsUpTo);

        String xpathSearchButton = "//button[text()='Search']";
        WebElement searchButton = DriverUtil.getElement(By.xpath(xpathSearchButton));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(searchButton);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(searchButton);
        DriverUtil.sleep(5000L);

        categoryNameField.click();
        DriverUtil.sleep(2000L);

        String xpathSearchResult = "//*[text()='" + rollsUpTo + "']";
        DriverUtil.sleep(2000L);
        WebElement searchResult = DriverUtil.getElement(By.xpath(xpathSearchResult));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementClickable(searchResult);
        DriverUtil.sleep(2000L);
        searchResult.click();
        DriverUtil.sleep(5000L);
    }

    public void fillinRollsUpToForCostCenter(String rollsUpTo) {
        StepReport.info("Input Rolls Up To for Cost Center");
        String xpath1 = this.calculateInputXPathInDialog("Match Using", "select");
        String xpath2 = xpath1 + "//following::select";
        String xpath3 = xpath2 + "//following::input";
        WebElement field3 = DriverUtil.getElement(By.xpath(xpath3));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(field3);
        DriverUtil.sleep(2000L);
        field3.clear();
        DriverUtil.sleep(2000L);
        field3.sendKeys(rollsUpTo);
        DriverUtil.sleep(5000L);
    }

    private String calculateInputXPathInDialog(String label, String tag) {
        return "//label[text()='" + label + "']//following::" + tag + "[1]";
    }

    public void clickOKButton() {
        StepReport.info("Click OK button");
        String xpath = "//button[@accesskey='K']";
        WebElement okButton = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(okButton);
        DriverUtil.clickByJS(okButton);
        DriverUtil.sleep(5000L);
    }

    public ManageRequisitionApprovals clickDoneButton() {
        StepReport.info("Click Done button");
        String xpath = "//a[@accesskey='o']";
        WebElement doneButton = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(doneButton);
        DriverUtil.clickByJS(doneButton);
        DriverUtil.sleep(5000L);
        ManageRequisitionApprovals manageRequisitionApprovals = PageFactory.getPage(ManageRequisitionApprovals.class);
        manageRequisitionApprovals.isLoaded();
        return manageRequisitionApprovals;
    }

    public boolean isUserDefinedAttrCreated(String attrName) {
        StepReport.info("Testing whether user defined attribute " + attrName + " is created");
        DriverUtil.sleep(5000L);
        try {
            DriverUtil.getElement(By.xpath("//*[text()='" + attrName + "']"));
            StepReport.info("Attribute " + attrName + " is already created");
            return true;
        } catch (Exception e) {
            StepReport.info("Attribute " + attrName + " is not created yet");
            return false;
        }
    }

    public ManageRequisitionApprovals deleteUserDefinedAttribute(String attributeName) {
        StepReport.info("Delete User-Defined Attribute "+attributeName);
        String xpath = "//*[text()='"+attributeName+"']/ancestor::td[1]";
        WebElement attribute = DriverUtil.getElement(By.xpath(xpath));
        PageLoadHelper.waitForElementClickable(attribute);
        DriverUtil.clickByJS(attribute);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(deleteButton);
        StepReport.assertTrue("Warning message displayed while deleting User-Defined Attribute", verifyWarningPopUp());
        DriverUtil.clickByJS(yesButton);
        DriverUtil.sleep(2000L);
        ManageRequisitionApprovals manageRequisitionApprovals = clickDoneButton();
        manageRequisitionApprovals.isLoaded();
        return manageRequisitionApprovals;
    }

    public boolean verifyWarningPopUp() {
        StepReport.info("Click Warning Message");
        DriverUtil.sleep(2000L);
        try {
            String xpath = "//*[text()='The selected user-defined attribute will be deleted. Do you want to continue? (POR-2010637)']";
            WebElement warningMessage = DriverUtil.getElement(By.xpath(xpath));
            PageLoadHelper.waitForElementVisible(warningMessage);
            if(warningMessage.isDisplayed()) return true;
            else return false;
        }catch(Exception e) {
            return false;
        }
    }

}
