package com.oracle.fa.qa.selenium.component.fin.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.common.page.Attachment;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.support.ui.Select;

public class CreateInvoice extends BasePageObject<CreateInvoice>{
	@FindBy(xpath = "//*[text()='Invoice Actions']")
	WebElement invoiceActions;
	
	@FindBy(xpath = "//*[text()='Identifying PO']/following::input[1]")
	WebElement poNumber;
	
	@FindBy(xpath = "//*[text()='Business Unit']/following::input[1]")
	WebElement businessUnit;
	
	@FindBy(xpath = "//*[text()='Invoice Group']/following::input[1]")
	WebElement invoiceGroup;
	
	@FindBy(xpath = "//*[text()='Number']/following::input[1]")
	WebElement invoiceNum;
	
	@FindBy(xpath = "//*[text()='Number']/following::input[2]")
	WebElement amount;
	
	@FindBy(xpath = "//*[text()='1']/following::input[1]")
	WebElement gridAmount;
	
	@FindBy(xpath = "//*[text()='Save']")
	WebElement save;
	
	@FindBy(xpath ="//a[@title='Manage Attachments']")
	WebElement attachmentLink;

    @FindBy(xpath ="//*[@title='Expand Lines']")
    WebElement expandLines;

	@FindBy(xpath = "//label[text()='Amount']/following::select[1][@title='']")
	WebElement amountType;


	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementClickable(invoiceActions) == null) {
            throw new TestErrorException("The Invoice Action is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void fillUpPO(String PONum) {
		StepReport.info("Fill Up Invoice Details");
        DriverUtil.sleep(5000L);
		PageLoadHelper.waitForElementVisible(poNumber);
		DriverUtil.sleep(2000L);
		//JavascriptExecutor executor = (JavascriptExecutor)FrameworkContext.getInstance().getWebDriver();
	    //executor.executeScript("arguments[0].setAttribute('value', '" + PONum +"')", poNumber);
		poNumber.sendKeys(PONum);
	    DriverUtil.sleep(2000L);
	    poNumber.sendKeys(Keys.RETURN);
	    DriverUtil.sleep(2000L);
	    //poNumber.sendKeys(Keys.TAB);
	    waitUntilBusinessUnitPopulated();
	    DriverUtil.sleep(2000L);
	}
	
	private void waitUntilBusinessUnitPopulated() {
		boolean isValueNotPopulated=true;
		int counter=1;
		while((isValueNotPopulated) && (counter<4)) {
		try {
			String val=businessUnit.getAttribute("value");
			System.out.println("Business Unit value="+val);
			if(val.equals("Vision Operations")) {
				isValueNotPopulated=false;
				System.out.println("Business Unit Populated");
			}else {
				System.out.println("Business Unit Not Populated");
			}
			}catch(Exception e) {
				System.out.println("Business Unit Not Populated");
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
	   }
	
	private void closeInformationPopup() {
		boolean isInformationPopupNotExists=true;
		int counter=1;
		while((isInformationPopupNotExists) && (counter<6)) {
		try {
			String xpath="//*[text()='OK']";
			WebElement infoOk=DriverUtil.getElement(By.xpath(xpath));
			if(infoOk.isDisplayed()) {
				infoOk.click();
				isInformationPopupNotExists=false;
				System.out.println("Information Popup Came");
			}else {
				System.out.println("Information Popup did not Come");
			}
			}catch(Exception e) {
				System.out.println("Information Popup did not Come");
			}
			DriverUtil.sleep(3000L);
			counter++;
		}
	}

	public void clickOKButton() {
		try {
			String xpath="//button[text()='OK']";
			WebElement infoOk=DriverUtil.getElement(By.xpath(xpath));
			if(infoOk.isDisplayed()) {
				infoOk.click();
				System.out.println("OK button is displayed and click it.");
			}else {
				System.out.println("OK button is not displayed");
			}
		}catch(Exception e) {
			System.out.println("OK button is not displayed");
		}
	}
	public void enterInvoiceGroup(String text) {
		StepReport.info("Enter Invoice Group");
		DriverUtil.sleep(3000L);
		invoiceGroup.sendKeys(text);
	    DriverUtil.sleep(2000L);
	    invoiceGroup.sendKeys(Keys.TAB);
	    DriverUtil.sleep(2000L);
	    closeInformationPopup();
	}
	
	public void enterInvoiceNumber(String text) {
		StepReport.info("Enter Invoice Number");
		DriverUtil.sleep(2000L);
		invoiceNum.sendKeys(text);
	    DriverUtil.sleep(2000L);
	}
	
	public void enterAmount(String text) {
		StepReport.info("Enter Invoice Amount");
		DriverUtil.sleep(2000L);
		amount.sendKeys(text);
	    DriverUtil.sleep(2000L);
	    DriverUtil.sleep(2000L);
	    amount.sendKeys(Keys.RETURN);
	    DriverUtil.sleep(2000L);
	    amount.sendKeys(Keys.TAB);
	}
	
	public void enterGridAmount(String text) {
		StepReport.info("Enter Grid Invoice Amount");
		DriverUtil.sleep(2000L);
		if(!SelUtil.isDisplayed(gridAmount,10)){
            DriverUtil.clickByJS(expandLines);
            PageLoadHelper.waitForElementVisible(gridAmount);
			DriverUtil.sleep(3000L);
        }
		gridAmount.sendKeys(text);
	    DriverUtil.sleep(2000L);
	}

	public boolean isGridAmountDisplayed() {
		try { if(gridAmount.isDisplayed()) {
			System.out.println("Grid Amount field is displayed");
			return true;
		}

			else{
				System.out.println("Grid Amount field is not displayed");
				return false;
		}
		}
		catch(Exception e) {
			System.out.println("Grid Amount field is not displayed");
			return false;
		}
	}
	
	public void clickSave() {
		StepReport.info("Click Save");
		DriverUtil.sleep(2000L);
		PageLoadHelper.waitForElementVisible(save);
		save.click();
		DriverUtil.sleep(2000L);
		String element="//*[text()='Not validated']";
		WebElement accountStatus=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementVisible(accountStatus);
        DriverUtil.sleep(2000L);
	}


	public void initiateApproval() {
		StepReport.info("Initiate Approval");
		DriverUtil.sleep(2000L);
		Actions builder = new Actions(FrameworkContext.getInstance().getWebDriver());
        Action mouseClick = builder.click(invoiceActions).build();
        mouseClick.perform();
	    DriverUtil.sleep(2000L);
	    String element="//*[text()='Invoice Actions']/following::*[text()='Approval']";
		WebElement approvalElem=DriverUtil.getElement(By.xpath(element));
		PageLoadHelper.waitForElementClickable(approvalElem);
		DriverUtil.sleep(2000L);
		mouseClick = builder.click(approvalElem).build();
        mouseClick.perform();
        DriverUtil.sleep(2000L);
        String element1="//*[text()='Invoice Actions']/following::*[text()='Approval']/following::*[text()='Initiate']";
		WebElement initiateElem=DriverUtil.getElement(By.xpath(element1));
        //initiateElem.click();
		DriverUtil.clickByJS(initiateElem);
	    DriverUtil.sleep(5000L);
	    //String element2="//*[text()='Approval']/following::*[text()='Initiated']";
		//WebElement initiateStatusElem=DriverUtil.getElement(By.xpath(element2));
	    //PageLoadHelper.waitForElementVisible(initiateStatusElem);
	}
	
	public Attachment clickAttachment() {
		StepReport.info("Click Attachment");
		attachmentLink.click();
		DriverUtil.sleep(2000L);
		Attachment attachment = PageFactory.getPage(Attachment.class);
		attachment.isLoaded();
        return attachment;
	}
	
	public void waitForAttachmentToDisplay(String fileName) {
		String xpath="//*[text()='"+fileName+"']";
		WebElement attachedFile=DriverUtil.getElement(By.xpath(xpath)); 
		PageLoadHelper.waitForElementVisible(attachedFile);
	}
/*
	public void selectCurrencyType(){
		StepReport.info("select currency type as","USD - US Dollar");
		Select statusSelect = new Select(amountType);
		statusSelect.selectByVisibleText("USD - US Dollar");
		DriverUtil.sleep(2000L);
	}*/
}
