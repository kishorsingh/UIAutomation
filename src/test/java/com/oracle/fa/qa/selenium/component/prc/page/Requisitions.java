package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.oracle.fa.qa.selenium.component.fin.page.RequestMoreInformation;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

import java.sql.Driver;

public class Requisitions extends BasePageObject<Requisitions>{
	
	@FindBy(xpath ="//*[text()='Requisitions']/following::input[@placeholder='Search']")
	WebElement SearchInput;
	
	@FindBy(xpath ="//*[text()='Requisitions']/following::a[@title='Search']")
	WebElement SearchIcon;
	
	@FindBy(xpath ="//*[@title='Shopping Cart']")
	WebElement shoppingCart;

	@FindBy(xpath ="//a[text()='Manage Requisitions']")
	WebElement manageRequisitions;

    @FindBy(xpath = "//td[text()='Update Requisition Preferences']")
    WebElement updateRequisitionPreferences;

    @FindBy(xpath = "//td[text()='Enter Requisition Line']")
    WebElement enterRequisitionLine;

    @FindBy(xpath = "//td[text()='Request Noncatalog Item']")
    WebElement requestNoncatalogItem;

    @FindBy(xpath = "//button[@accesskey='S']")
    WebElement saveAndClose;

    @FindBy(xpath = "//select[contains(@id, 'my_information_purchase_requisitions')]")
    WebElement requisitioningBU;

    @FindBy(xpath = "//*[text()='Added to Cart']")
    WebElement addToCartConfirmation;

    @FindBy(xpath = "//button[text()='Add to Cart']")
    WebElement addToCart;

	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(SearchInput) == null) {
            throw new TestErrorException("The Search Input field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
            }
		StepReport.info("Requistion page is loaded");
    }
	
	public void searchItem(String itemName) {
		StepReport.info("Enter Item Name");
		DriverUtil.sleep(2000L);
		SearchInput.sendKeys(itemName);
		DriverUtil.sleep(3000L);
		StepReport.info("Click Search Icon");
		DriverUtil.clickByJS(SearchIcon);
		DriverUtil.sleep(7000L);
	}
	
	public void typeItemQtyAndAddToCart(String itemName,String qty) {
		StepReport.info("Enter Quantity");
		DriverUtil.sleep(2000L);
		String xpath="//*[text()='"+itemName+"']/following::input[1]";
		WebElement itemQtyElem=DriverUtil.getElement(By.xpath(xpath)); 
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(itemQtyElem);
        DriverUtil.sleep(2000L);
        itemQtyElem=DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(2000L);
        itemQtyElem.clear();
        DriverUtil.sleep(2000L);
        itemQtyElem.sendKeys(qty);
        DriverUtil.sleep(2000L);
        itemQtyElem.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
        StepReport.info("Click Add To Cart");
		xpath="//*[text()='"+itemName+"']/following::*[@title='Add to Cart']";
        WebElement AddToCartElem=DriverUtil.getElement(By.xpath(xpath)); 
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(AddToCartElem);
        DriverUtil.sleep(3000L);
        DriverUtil.clickByJS(AddToCartElem);
		StepReport.assertTrue("Item added to cart", SelUtil.isDisplayed(addToCartConfirmation,30));
	}
	
	public EditRequisition reviewItem() {
		StepReport.info("Open Shopping Cart");
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(shoppingCart);
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(shoppingCart);
        DriverUtil.sleep(4000L);
        String xpath="//*[text()='Review']";
        WebElement reviewElem=DriverUtil.getElement(By.xpath(xpath)); 
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(reviewElem);
        DriverUtil.clickByJS(reviewElem);
		DriverUtil.sleep(3000L);
		EditRequisition editRequisition = PageFactory.getPage(EditRequisition.class);
		editRequisition.isLoaded();
		return editRequisition;
	}

	public ManageRequisitions clickManageRequisitions(){
		StepReport.info("Click on Manage Requisitions");
		DriverUtil.sleep(2000L);
		manageRequisitions.click();
		ManageRequisitions manageRequisitions = PageFactory.getPage(ManageRequisitions.class);
		manageRequisitions.isLoaded();
		return manageRequisitions;

	}

    public EnterRequisitionLine gotoEnterRequisitionLine() {
        StepReport.info("Goto Enter Requisition Line");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(enterRequisitionLine);
        DriverUtil.sleep(3000L);
        return PageFactory.getPage(EnterRequisitionLine.class);
    }

    public CreateNoncatalogRequest gotoRequestNonCatalogItem() {
        StepReport.info("Goto Request non-catalog Item");
        DriverUtil.clickByJS(requestNoncatalogItem);
        CreateNoncatalogRequest createNoncatalogRequest = PageFactory.getPage(CreateNoncatalogRequest.class);
        createNoncatalogRequest.isLoaded();
        return createNoncatalogRequest;
    }

    public void updateRequisitionPreferences() {
        StepReport.info("Update Requisition Preferences");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(updateRequisitionPreferences);
        //updateRequisitionPreferences.click();

        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(saveAndClose);
        DriverUtil.sleep(2000L);

        //Select currencyElement = new Select(requisitioningBU);
        //currencyElement.selectByVisibleText("Vision Operations");

        WebElement deliverToLocation = DriverUtil.getElement(By.xpath("//input[contains(@id, 'deliverToLocationLOVId::content')]"));
        deliverToLocation.clear();
        deliverToLocation.sendKeys("V1- New York City");

        WebElement deliverLabel = DriverUtil.getElement(By.xpath("//label[text()='Deliver-to Location']"));
        deliverLabel.click();

        DriverUtil.sleep(2000L);

        saveAndClose.click();

        DriverUtil.sleep(10000L);
    }

    public void goListView() {
        StepReport.info("Goto List View");
        DriverUtil.getElement(By.xpath("//div[@title='List View']/a/img[@title='List View']")).click();
        DriverUtil.sleep(3000);
        StepReport.info("Done List View");
    }


    public void typeItemQtyAndAddToCartInListView(String itemName,String qty) {
        StepReport.info("Enter Quantity");
        DriverUtil.sleep(2000L);
        String xpath="//table[@summary='Search Results: "+itemName+"']//input[1]";
        WebElement itemQtyElem=DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(itemQtyElem);
        DriverUtil.sleep(2000L);
        itemQtyElem.clear();
        DriverUtil.sleep(2000L);
        itemQtyElem.sendKeys(qty);
        DriverUtil.sleep(2000L);
        itemQtyElem.click();
        DriverUtil.sleep(3000L);
        StepReport.info("Click Add To Cart");
        /*xpath="//button[text()='Add to Cart']";
        WebElement AddToCartElem=DriverUtil.getElement(By.xpath(xpath));
        DriverUtil.sleep(2000L);*/
        PageLoadHelper.waitForElementVisible(addToCart);
        DriverUtil.sleep(3000L);
        addToCart.click();
        StepReport.assertTrue("Item added to cart", SelUtil.isDisplayed(addToCartConfirmation,60));
    }

    public void updateRequisitionPreferences(String nickName, String chargeAccount) {
        StepReport.info("Update Requisition Preferences");
        DriverUtil.sleep(2000L);
        DriverUtil.clickByJS(updateRequisitionPreferences);
        //updateRequisitionPreferences.click();

        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(saveAndClose);
        DriverUtil.sleep(2000L);

        Select currencyElement = new Select(requisitioningBU);
        currencyElement.selectByVisibleText("Vision Operations");

        WebElement deliverToLocation = DriverUtil.getElement(By.xpath("//input[contains(@id, 'deliverToLocationLOVId::content')]"));
        deliverToLocation.clear();
        deliverToLocation.sendKeys("V1- New York City");

        WebElement deliverLabel = DriverUtil.getElement(By.xpath("//label[text()='Deliver-to Location']"));
        deliverLabel.click();

        DriverUtil.sleep(2000L);

        DriverUtil.scrollIntoView(saveAndClose);
        //WebElement createAccountEle = DriverUtil.getElement(By.xpath("//div[@title='Favorite Charge Accounts'/parent::td/following::a[@title='Create']"));
        WebElement createAccountEle = DriverUtil.getElement(By.xpath("//a[@title='Create']"));
        //DriverUtil.scrollIntoView(saveAndClose);

        WebElement nickNameEle = null;
        WebElement chargeAccountEle = null;
        //String nicknameXpath = "//td[@title='Nickname']/span/input";

        String nicknameXpath="//img[@title='Set Primary']/parent::a/parent::td/following::td/span/input";
        String chargeAccountXpath = "//img[@title='Set Primary']/parent::a/parent::td/following::td/following::td//span/input";

        String nnXpath = "//table[@summary='Favorite Charge Accounts']/tbody/tr/td[2]/span/input";
        String caXpath = "//table[@summary='Favorite Charge Accounts']/tbody/tr/td[3]//span/input";
        try {
            nickNameEle = DriverUtil.getElement(By.xpath(nnXpath));
            StepReport.info("Nickname is " + nickNameEle.getAttribute("value"));
            chargeAccountEle = DriverUtil.getElement(By.xpath(caXpath));
            StepReport.info("Charge Account is " + chargeAccountEle.getAttribute("value"));
        } catch (NoSuchElementException e) {
            StepReport.info("There is no default Charge Account!");
        }
        if (nickNameEle != null && chargeAccountEle != null && nickNameEle.getAttribute("value").equals(nickName) && chargeAccountEle.getAttribute("value").equals(chargeAccount))
        {

        } else {

            createAccountEle.click();

            DriverUtil.sleep(2000L);

            DriverUtil.scrollIntoView(saveAndClose);

            nickNameEle = DriverUtil.getElement(By.xpath(nicknameXpath));
            nickNameEle.sendKeys(nickName);
            chargeAccountEle = DriverUtil.getElement(By.xpath(chargeAccountXpath));
            chargeAccountEle.sendKeys(chargeAccount);
        }

        saveAndClose.click();

        DriverUtil.sleep(10000L);
    }
}
