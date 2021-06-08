package com.oracle.fa.qa.selenium.component.crm.page;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
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

import java.util.Random;

public class CreateContract extends BasePageObject<CreateContract> {


	@FindBy(xpath = "//div[text()='Create Contract']")
	WebElement createContractText;

	@FindBy(xpath = "//*[@id='pt1:_FOr1:1:_FONSr2:0:_FOTRaT:0:dynam1:0:orgNameId::lovIconId']")
	WebElement typeBusinessUnit;

	@FindBy(xpath = "//*[contains(@name,'legalEntityNameId')]")
	WebElement typeLegalEntity;

	@FindBy(xpath = "//*[contains(@name,'contractTypeId')]")
	WebElement typeContracType;

	@FindBy(xpath = "//*[contains(@name,'inputText1')]")
	WebElement typeContractNumber;

	@FindBy(xpath = "//*[contains(@name,'partyNameId')]")
	WebElement typePrimaryPartyName;

	@FindBy(xpath = "//input[contains(@id,'inputDate1::content')]")
	WebElement typeStartDate;

	@FindBy(xpath = "//input[contains(@id,'inputDate2::content')]")
	WebElement typeEndDate;

	@FindBy(xpath = "//button[contains(@id,'dynam1:0:cb1')]")
	WebElement saveAndContinueButton;

	@FindBy(xpath = "//input[contains(@id,'invOrgNameId::content')]")
	WebElement typeItemMaster;

	@FindBy(xpath = "//*[@title='Search: Type']")
	WebElement typeDropDown;

	@FindBy(xpath = "//*[text()='Buy : Project lines']")
	WebElement typeValue;

	@Override
	public void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(createContractText) == null) {
			throw new TestErrorException("The partnerName  is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}

		StepReport.info("Create Contract Sub Page is Loaded");
	}




	public void typeBusinessUnit() {
		//StepReport.info("Type BusinessUnit  ");
		StepReport.info("Type BusinessUnit  ", "");
		DriverUtil.sleep(2000L);
		//typeBusinessUnit.sendKeys(ecBusinessUnit);
		typeBusinessUnit.sendKeys("");
		DriverUtil.sleep(2000L);
		typeBusinessUnit.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);

	}
	public void typeLegalEntity() {
		//StepReport.info("Type Legal Entity ");
		StepReport.info("Type Legal Entity ", "");
		DriverUtil.sleep(2000L);
		//typeLegalEntity.sendKeys(ecLegalEntity);
		typeLegalEntity.sendKeys("");
		DriverUtil.sleep(2000L);
		typeLegalEntity.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}
	public void selectContractType() {
		//StepReport.info("Type Contract Type :" + ecContractType);
		StepReport.info("Select Contract Type :Buy : Project lines", "Buy : Project lines");
		DriverUtil.sleep(2000L);
		typeDropDown.click();
		PageLoadHelper.waitForElementVisible(typeValue);
		typeValue.click();
		DriverUtil.sleep(2000L);
	}
	public String typeContractNumber() {
		StepReport.info("Type Contract Number : " );
		DriverUtil.sleep(2000L);
		int num = 100000;
		Random r = new Random();
		num = num + r.nextInt(100000);
		typeContractNumber.sendKeys(String.valueOf(num));
		DriverUtil.sleep(2000L);
		typeContractNumber.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
		return String.valueOf(num);
	}
	public void typePrimaryPartyName() {
		//StepReport.info("Type Contract Primary Party Name ");
		StepReport.info("Type Contract Primary Party Name", "GE Plastics");
		PageLoadHelper.waitForElementVisible(typeContractNumber);
		DriverUtil.sleep(2000L);
		//typePrimaryPartyName.sendKeys(ecPrimaryParty);
		typePrimaryPartyName.sendKeys("GE Plastics");
		DriverUtil.sleep(2000L);
		typePrimaryPartyName.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}
	public void typeStartDate(String ecStartDate) {
		StepReport.info("Type StartDate  ");
		DriverUtil.sleep(2000L);
		if(ecStartDate.equalsIgnoreCase("SysDate"))
		{
			ecStartDate= SelUtil.getSystemDateTime();
			StepReport.info("System Date Time " + ecStartDate);
		}
		typeStartDate.sendKeys(ecStartDate);
		DriverUtil.sleep(2000L);
		typeStartDate.sendKeys(Keys.RETURN);
		DriverUtil.sleep(2000L);
	}
	public void typeEndDate() {
		//StepReport.info("Enter End Date  ");
		StepReport.info("Enter End Date 02/19/21", "02/19/21");
		DriverUtil.sleep(3000L);
		typeEndDate.sendKeys("02/19/21");
		DriverUtil.sleep(3000L);
		typeEndDate.sendKeys(Keys.RETURN);
		DriverUtil.sleep(3000L);
	}

	public void typeItemMaster() {
		//StepReport.info("Enter Item Master	 : " + ecitemMaster);
		StepReport.info("Enter Item Master	 : Vision Operations", "Vision Operations");
		DriverUtil.sleep(2000L);
		//typeItemMaster.sendKeys(ecitemMaster);
		typeItemMaster.sendKeys("Vision Operations");
		DriverUtil.sleep(2000L);

	}

	public EditContract clickSaveAndContinue() {
		StepReport.info("Enter End Date  ");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(saveAndContinueButton);
		DriverUtil.sleep(2000L);
		EditContract editcontract = PageFactory.getPage(EditContract.class);
		editcontract.isLoaded();
		return editcontract;
	}




}
