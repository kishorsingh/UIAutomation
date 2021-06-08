package com.oracle.fa.qa.selenium.component.prc.page;

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

import java.time.temporal.WeekFields;

public class EnterRequisitionLine extends BasePageObject<EnterRequisitionLine> {

    @FindBy(xpath = "//*[@title='Shopping Cart']")
    WebElement shoppingCart;

    @FindBy(xpath = "//*[text()='Add to Cart']/parent::a")
    WebElement addToCart;

    @FindBy(xpath = "(//label[text()='Item Description']/following::textarea)[1]")
    WebElement itemDescription;


    @FindBy(xpath = "//label[text()='Category Name']/parent::td/following::td/input")
    WebElement categoryName;

    @FindBy(xpath = "//label[text()='Quantity']/parent::td/following::td/input")
    WebElement quantity;

    @FindBy(xpath = "//label[text()='UOM Name']/parent::td/following::td/span/input")
    WebElement uomName;

    @FindBy(xpath = "//label[text()='Price']/parent::td/following::td/input")
    WebElement price;

    @FindBy(xpath = "//a[@title='Select: Charge Account']/parent::td/parent::tr/td/span/input")
    WebElement chargeAccount;

    @FindBy(xpath = "//img[@src='/fscmUI/images/qual_checkmark_16.png']")
    WebElement addedToCart;

    @FindBy(xpath = "//span[@title='Lenovo Laptop - Bundled with MS' and text()='Lenovo Laptop - Bundled with MS'")
    WebElement addedLenovo;

    @FindBy(xpath = ".//*[text()='There are no items in your shopping cart.']")
    WebElement noItemsInCart;

    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(addToCart) == null) {
            throw new TestErrorException("The submit field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }

        StepReport.info("Entered Requisition Line Page");
    }

    public void enterRequisitionWithLenovo1() {
        StepReport.info("Enter Requisition With Lenovo");
        DriverUtil.sleep(2000L);

        JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
        WebElement priceLabel = DriverUtil.getElement(By.xpath("//label[text()='Price']"));

        PageLoadHelper.waitForElementVisible(itemDescription);
        DriverUtil.sleep(2000L);

        itemDescription.sendKeys("Lenovo Laptop - Bundled with MS");
        DriverUtil.sleep(2000L);
        categoryName.sendKeys("Cameras");
        //priceLabel.click();
        DriverUtil.sleep(3000L);

        categoryName.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

        /*WebElement li = DriverUtil.getElement(By.xpath("//li[text()='Cameras Cameras']"));
        li.click();
        DriverUtil.sleep(3000L);*/

        //quantity.clear();
        //DriverUtil.sleep(1000L);
        //PageLoadHelper.waitForElementClickable(quantity);
        quantity.clear();
        //quantity.sendKeys("1");
        //DriverUtil.sleep(1000L);
        quantity.sendKeys("10");
        //PageLoadHelper.waitForElementClickable(quantity);
        //DriverUtil.clickByJS(quantity);
        //quantity.click();
        quantity.sendKeys(Keys.TAB);

        //DriverUtil.sleep(2000L);
        //uomName.click();
        DriverUtil.sleep(2000L);

        uomName.sendKeys("Each");
        DriverUtil.sleep(3000L);

        uomName.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);

        //DriverUtil.getElement(By.xpath("//li[text()='Each Quantity Each']")).click();
        //DriverUtil.sleep(3000L);

        //executor.executeScript("arguments[0].scrollIntoView(true);", price);
        priceLabel = DriverUtil.getElement(By.xpath("//label[text()='Price']"));
        priceLabel.click();
        DriverUtil.sleep(2000L);

        price.click();
        price.sendKeys("61000");
        DriverUtil.sleep(1000L);
        price.click();
        price.sendKeys(Keys.TAB);

        priceLabel = DriverUtil.getElement(By.xpath("//label[text()='Price']"));
        priceLabel.click();
        DriverUtil.sleep(2000L);

        String account = "01-510-7530-0000-000";

        this.inputChargeAccount(account);

        priceLabel = DriverUtil.getElement(By.xpath("//label[text()='Price']"));
        priceLabel.click();
        DriverUtil.sleep(2000L);

        this.addToCart();
        //StepReport.assertTrue("Item added to cart", SelUtil.isDisplayed(addedToCart,20));
        WebElement atcElem = PageLoadHelper.waitForElementVisible(addedToCart);

        if (atcElem == null || !atcElem.isDisplayed()) {
            this.inputChargeAccount(account);
            this.addToCart();

            PageLoadHelper.waitForElementVisible(addedToCart);
        }
        //addToCartElem.click();
        //PageLoadHelper.waitForElementVisible(addedLenovo);
        StepReport.info("Finished to add Lenovo to Cart");
    }

    public void enterRequisitionWithLenovo() {
        StepReport.info("Enter Requisition With Lenovo");
        enterItemDescription("Lenovo Laptop - Bundled with MS");
        enterCategoryName("Cameras");
        enterQuantity("10");
        enterUOMName("Each");
        enterPrice("61000");
        inputChargeAccount("01-510-7530-0000-000");
        addToCart();
        StepReport.info("Finished to add Lenovo to Cart");
    }

    public void enterItemDescription(String text) {
        StepReport.info("Enter Item Description", text);
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(itemDescription);
        DriverUtil.sleep(2000L);
        itemDescription.sendKeys(text);
        DriverUtil.sleep(2000L);
        DriverUtil.getElement(By.xpath("//label[text()='Item Description']")).click();
        //itemDescription.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterCategoryName(String category) {
        StepReport.info("Enter Category Name", category);
        categoryName.sendKeys(category);
        DriverUtil.sleep(3000L);
        DriverUtil.getElement(By.xpath("//label[text()='Category Name']")).click();
        //categoryName.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void enterQuantity(String qty) {
        StepReport.info("Enter Quantity", qty);
        quantity.clear();
        DriverUtil.sleep(2000L);
        quantity.sendKeys(qty);
        DriverUtil.sleep(2000L);
        DriverUtil.getElement(By.xpath("//label[text()='Quantity']")).click();
        //quantity.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }

    public void enterUOMName(String name) {
        StepReport.info("Enter UOM Name", name);
        uomName.sendKeys(name);
        DriverUtil.sleep(3000L);
        DriverUtil.getElement(By.xpath("//label[text()='UOM Name']")).click();
        //uomName.sendKeys(Keys.TAB);
        DriverUtil.sleep(3000L);
    }

    public void enterPrice(String number) {
        StepReport.info("Enter Price", number);
        price.sendKeys(number);
        DriverUtil.sleep(2000L);
        DriverUtil.getElement(By.xpath("//label[text()='Price']")).click();
        //price.sendKeys(Keys.TAB);
        DriverUtil.sleep(2000L);
    }


    public void inputChargeAccount(String account) {
        JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        DriverUtil.sleep(2000L);

        String currentAccount = chargeAccount.getAttribute("value");
        StepReport.info("Default Charge Account is ", currentAccount);
        if (currentAccount == null || currentAccount.trim().equals("")) {
            chargeAccount.sendKeys(account);
            chargeAccount.click();
            StepReport.info("Enter Charge Account", account);
        }
        DriverUtil.sleep(2000L);
    }

    public void addToCart() {
        JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
        executor.executeScript("arguments[0].scrollIntoView(true);", addToCart);

        WebElement priceLabel = DriverUtil.getElement(By.xpath("//label[text()='Price']"));
        priceLabel.click();
        DriverUtil.sleep(3000L);

        StepReport.info("Click Add To Cart");
        executor.executeScript("arguments[0].scrollIntoView(true);", addToCart);
        WebElement addToCartElem = DriverUtil.getElement(By.xpath("//span[text()='Add to Cart']/parent::a"));
        DriverUtil.sleep(2000L);
        PageLoadHelper.waitForElementVisible(addToCartElem);
        DriverUtil.sleep(3000L);
        addToCartElem.click();
        DriverUtil.sleep(3000L);
        /*StepReport.info("Click Add To Cart");
        DriverUtil.sleep(2000L);
        int counter=1;

        do { JavascriptExecutor executor = (JavascriptExecutor) FrameworkContext.getInstance().getWebDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", addToCart);

            WebElement priceLabel = DriverUtil.getElement(By.xpath("//label[text()='Price']"));
            priceLabel.click();
            DriverUtil.sleep(3000L);

            StepReport.info("Click Add To Cart");
            executor.executeScript("arguments[0].scrollIntoView(true);", addToCart);
            WebElement addToCartElem = DriverUtil.getElement(By.xpath("//span[text()='Add to Cart']/parent::a"));
            DriverUtil.sleep(2000L);
            PageLoadHelper.waitForElementVisible(addToCartElem);
            DriverUtil.sleep(3000L);
            counter++;
        }
        while (((counter<5))&& (!SelUtil.isDisplayed(addedToCart, 60)));

        DriverUtil.sleep(2000L);*/

        /*if(SelUtil.isDisplayed(addedToCart,30))return;
        else {
            addToCart.click();
            DriverUtil.sleep(2000L);
            SelUtil.isDisplayed(addedToCart,30);
        }*/
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
}
