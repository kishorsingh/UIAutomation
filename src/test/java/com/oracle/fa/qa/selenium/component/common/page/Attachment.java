package com.oracle.fa.qa.selenium.component.common.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.oracle.fa.qa.selenium.component.fin.page.CreateItemDetails;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

public class Attachment extends BasePageObject<Attachment>{
	
	@FindBy(css ="select[id*='DatatypeCodeChoice']")
	WebElement type;
	
	@FindBy(css ="select[id*='Category']")
	WebElement category;
	
	@FindBy(css ="input[id*='DescriptionInputText']")
	WebElement description;
	
	@FindBy(css ="input[id*='TitleInputText']")
	WebElement titleField;
	
	@FindBy(css ="input[type='file']")
	WebElement fileAttachment;
	
	@FindBy(css ="button[accesskey='K']")
	WebElement okButton;
	
	@FindBy(css ="textarea[id*='FileText']")
	WebElement fileText;
	
		
	@Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(type) == null) {
            throw new TestErrorException("The type is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }
	
	public void selectType(String typeName) {
		StepReport.info("Select Type");
		Select typeElement = new Select(type);
		typeElement.selectByVisibleText(typeName);
	}
	
	public void attachFile(String filePath) {
		StepReport.info("Attach File");
		fileAttachment.sendKeys(filePath);
		DriverUtil.sleep(2000L);
	}
	
	public void typeFileText(String text) {
		StepReport.info("Type File Text");
		PageLoadHelper.waitForElementVisible(fileText);
		fileText.sendKeys(text);
	}
	
	public void typeTile(String title) {
		StepReport.info("Type Title");
		titleField.sendKeys(title);
	}
	public void typeDescription(String desc) {
		StepReport.info("Type Description");
		description.sendKeys(desc);
	}
	
	public void clickOk() {
		StepReport.info("Click OK");
		okButton.click();
		DriverUtil.sleep(2000L);
	}
	
}
