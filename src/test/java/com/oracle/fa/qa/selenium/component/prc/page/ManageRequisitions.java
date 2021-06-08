package com.oracle.fa.qa.selenium.component.prc.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageRequisitions extends BasePageObject<ManageRequisitions>{

	@FindBy(xpath ="//h1[text()= 'Manage Requisitions']")
	WebElement manageRequisitionsTitle;

	@FindBy(xpath = "//label[text()='Requisition']/parent::td/following::td//input")
	WebElement searchRequisition;

	@FindBy(xpath = "//button[text()='Search']")
	WebElement searchButton;

	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(manageRequisitionsTitle) == null) {
            throw new TestErrorException("The Search Input field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
            }
		StepReport.info("ManageRequisitions page is loaded");
    }

    public void searchRequisition(String reqNum) {
		StepReport.info("Search Requisition "+reqNum);
		DriverUtil.sleep(2000L);
		searchRequisition.clear();
		searchRequisition.sendKeys(reqNum);

		DriverUtil.clickByJS(searchButton);
		DriverUtil.sleep(3000L);
	}

	/*public void searchItem(String itemName) {
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
		DriverUtil.sleep(20000L);
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

	}*/

	public RequisitionPage clickRequisition(String reqNum){
		StepReport.info("Click Requisition Number");
		DriverUtil.sleep(2000L);
		WebElement reqNumber =findElement(By.xpath("//*[text()='"+reqNum+"']"));
		DriverUtil.clickByJS(reqNumber);
		DriverUtil.sleep(2000L);
		RequisitionPage requisitionPage = PageFactory.getPage(RequisitionPage.class);
		requisitionPage.isLoaded();
		return requisitionPage;
	}

	public boolean verifyApprovalStatus(String requisitionNum, String approvalStatus) {
		StepReport.info("Verify Approval Status");
		DriverUtil.sleep(2000L);
		try {
			String element="//*[text()='"+requisitionNum+"']/following::*[text()='"+approvalStatus+"']";
			WebElement approvalStatusElem=DriverUtil.getElement(By.xpath(element));
			if(approvalStatusElem.isDisplayed()) return true;
			else return false;
		}catch(Exception e) {
			return false;
		}

	}

}
