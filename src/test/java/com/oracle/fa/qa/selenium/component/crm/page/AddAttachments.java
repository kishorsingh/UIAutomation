package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddAttachments extends BasePageObject<AddAttachments>{
	
	@FindBy(xpath = "//div[text()='Add Attachment']")
	WebElement title;

	@FindBy(xpath = "//*[@id='edAttTy:_0']")
	WebElement attachmentType;

	@FindBy(xpath = "//*[@id='attFile::content']")
	WebElement clickBrowse;

	@FindBy(xpath = "//*[@id='attName::content']")
	WebElement typeFileName;

	@FindBy(xpath = "//*[@id='attHref::content']")
	WebElement typeURL;

	@FindBy(xpath = "//*[@id='adAtBt2']")
	WebElement OkButton;


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
        if (PageLoadHelper.waitForElementVisible(title) == null) {
            throw new TestErrorException("The type is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
		StepReport.info("Add Attachments Page is successfully loaded");
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

	public void selectAttachmentType(){
		StepReport.info("Select Attachment type as Desktop");
		attachmentType.click();
		DriverUtil.sleep(3000L);
	}

	public void clickBrowse(){
		StepReport.info("Click Browse");
		clickBrowse.click();
		DriverUtil.sleep(3000L);
	}

	public void typeFilepath(){
		StepReport.info("Click Browse");
		clickBrowse.sendKeys("history.txt");
		DriverUtil.sleep(3000L);
	}

	public void typeFileName(){
		StepReport.info("Click Browse");
		typeFileName.sendKeys("TestAttachement");
		DriverUtil.sleep(3000L);
	}

	public void typeURL(String attFilePath){
		StepReport.info("Type URL");
		typeURL.sendKeys(attFilePath);
		DriverUtil.sleep(3000L);
	}
	public void clickOKButton(){
		StepReport.info("Click Browse");
		OkButton.click();
		DriverUtil.sleep(3000L);
	}
}
