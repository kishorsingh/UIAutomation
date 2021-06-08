package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNoncatalogRequest extends BasePageObject<CreateNoncatalogRequest>{

	@FindBy(xpath ="//h1[text()='Create Noncatalog Request']")
	WebElement createNoncatalogRequestPageTitle;

	@FindBy(xpath ="//*[text()='Category Name']/following::input[1]")
	WebElement categoryName;

	@FindBy(xpath ="//*[text()='Item Description']/following::textarea[1]")
	WebElement itemDescriptionText;

	@FindBy(xpath ="//*[text()='Quantity']/following::input[1]")
	WebElement quantity;

	@FindBy(xpath ="//*[text()='UOM Name']/following::input[1]")
	WebElement UOMName;

	@FindBy(xpath ="//*[text()='Price']/following::input[1]")
	WebElement priceText;

	@FindBy(xpath ="//span[text()='Add to Cart']")
	WebElement addToCart;

    @FindBy(xpath ="//*[text()='Added to Cart']")
    WebElement addedToCart;

    @FindBy(xpath ="//span[text()='Done']")
    WebElement done;

	@FindBy(xpath ="//button[contains(@id,'cancelReasonDialog::ok')]")
	WebElement clickOK;

	@FindBy(xpath ="//*[@id='d1::msgDlg::_ttxt']")
	WebElement confirmation;

	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(createNoncatalogRequestPageTitle) == null) {
            throw new TestErrorException("The Create Noncatalog Request Page Title is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
            }
		StepReport.info("Create Noncatalog Request Page is loaded");
    }

    public Requisitions createNoncatalogRequestLine(String itemDescription,String category,String qty,String UOMName, String price) {
        enterItemDescription(itemDescription);
        enterCategoryName(category);
        enterQuantity(qty);
        enterUOMName(UOMName);
        enterPrice(price);
        clickAddToCart();
        return clickDone();
    }

	public void enterItemDescription(String itemDescription){
		StepReport.info("Enter Item Description",itemDescription);
		DriverUtil.sleep(2000L);
		itemDescriptionText.sendKeys(itemDescription);
		DriverUtil.sleep(2000L);
        itemDescriptionText.sendKeys(Keys.TAB);
	}

    public void enterCategoryName(String category){
        StepReport.info("Enter Category Name",category);
        DriverUtil.sleep(2000L);
        categoryName.sendKeys(category);
        DriverUtil.sleep(2000L);
        categoryName.sendKeys(Keys.TAB);
        categoryName.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterQuantity(String qty){
        StepReport.info("Enter Quantity",qty);
        PageLoadHelper.waitForElementVisible(quantity);
        DriverUtil.sleep(2000L);
        quantity.clear();
        quantity.sendKeys(qty);
        DriverUtil.sleep(2000L);
        quantity.sendKeys(Keys.TAB);
    }

    public void enterUOMName(String name){
        StepReport.info("Enter UOM Name",name);
        DriverUtil.sleep(2000L);
        UOMName.sendKeys(name);
        DriverUtil.sleep(2000L);
        UOMName.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterPrice(String price){
        StepReport.info("Enter Price",price);
        DriverUtil.sleep(2000L);
        priceText.sendKeys(price);
        DriverUtil.sleep(2000L);
        quantity.sendKeys(Keys.TAB);
    }

	public void clickAddToCart(){
		StepReport.info("Click Add To Cart");
		DriverUtil.sleep(2000L);
		addToCart.click();
		DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(addedToCart);
	}

    public Requisitions clickDone(){
        StepReport.info("Click Done");
        DriverUtil.sleep(2000L);
        done.click();
        DriverUtil.sleep(2000L);
        Requisitions requisitions = PageFactory.getPage(Requisitions.class);
        requisitions.isLoaded();
        return requisitions;
    }

}
