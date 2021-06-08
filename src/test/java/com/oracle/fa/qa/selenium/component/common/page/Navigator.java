package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.crm.page.*;
import com.oracle.fa.qa.selenium.component.ess.page.ESSUIMonitorPage;
import com.oracle.fa.qa.selenium.component.fin.page.CashBalances;
import com.oracle.fa.qa.selenium.component.fin.page.Transactions;
import com.oracle.fa.qa.selenium.component.hcm.page.NewPersonPage;
import com.oracle.fa.qa.selenium.component.hcm.page.PersonManagementPage;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.fin.page.Invoices;
import com.oracle.fa.qa.selenium.component.fin.page.TravelandExpenses;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

import javax.xml.ws.WebEndpoint;

public class Navigator extends BasePageObject<Navigator>{
	@FindBy(css = "a[title='Expenses']")
	WebElement expenses;

	@FindBy(xpath = "//span[text()='Expenses']")
	WebElement expensesInCustomTheme;

	@FindBy(css = "img[title='Transaction Console']")
	WebElement transactionConsole;

	@FindBy(xpath = "//span[text()='Transaction Console']")
	WebElement transactionConsoleInCustomTheme;

	@FindBy(css="a[title='New Person']")
	WebElement newPersonInCustomTheme;

	@FindBy(xpath="//*[contains(@id,'manager_resources_new_person::icon')]")
	WebElement newPerson;

	@FindBy(css="a[title='Directory']")
	WebElement directory;

	@FindBy(css = "a[title='Invoices']")
	WebElement invoicesElem;

	@FindBy(xpath = "//span[text()='Invoices']")
	WebElement invoicesInCustomTheme;

	@FindBy(css = "a[title='Transactions']")
	WebElement transactionsElem;

	@FindBy(xpath="//a[contains(@id,'pt1:nv_itemNode_manager_resources_CareerandPerformance')]")
	WebElement CareerPerf;

	@FindBy(xpath="//a[contains(text(),'Career Development')] ")
	WebElement Careerdev;


	@FindBy(css = "a[title='Cash Balances']")
	WebElement cashBalancesElem;

	@FindBy(css="a[title='Workforce Structures']")
	WebElement workforceStructure;

	@FindBy(xpath="//*[@id='pt1:nv_PER_HCMPEOPLETOP_FUSE_PER_INFO']")
	WebElement personal;

	@FindBy(xpath="//span[contains(text(),'Personal Details')]")
	WebElement personaldet;

	@FindBy(xpath = "//a[@title='Quotas']")
	WebElement clickQuotas;

	@FindBy(xpath = "//a[@title='Territories']")
	WebElement territories;

	@FindBy(xpath = "//a[@title='Partners']")
	WebElement partners;

	@FindBy(xpath = "//a[@title='Programs']")
	WebElement programs;

	@FindBy(xpath = "//a[@title='Scheduled Processes']")
	WebElement scheduledProcesses;

	@FindBy(css="a[title='Audit Reports']")
	WebElement auditReports;

	@FindBy(css="a[title='Security Console']")
	WebElement securityConsole;

	@FindBy(css="a[title='Getting Started']")
	WebElement gettingStarted;

	@FindBy(css="a[title='Me']")
	WebElement Me;

	@FindBy(css="a[title='Person Management']")
	WebElement personManagement;

	@FindBy(xpath="(//*[text()='Sales']/following::a[@title='Contacts'])[1]")
	WebElement salesContatcts;

	//@FindBy(xpath="//a[contains(@id,'showmore_groupNode_my_information')]")
	@FindBy(xpath="//a[contains(text(),'artner')]")
	WebElement clickMore;

	@FindBy(xpath="//span[text()='Me']")
	WebElement meInNavigator;


	@FindBy(xpath="//h1[text()='Navigator']")
	WebElement NavigatorHeader;
	
	@FindBy(css = "a[title='Scheduled Processes']")
    WebElement essMonitorLink;
    @FindBy(css = "a[id='pt1:nv_moreLk']")  
    WebElement essMore;

	@FindBy(xpath = "a[text()='Tools']")  //contains(@id,'groupNode_tools')]")
	WebElement tools;

	@FindBy(xpath = "a[text()='Scheduled Processes']")
	WebElement scheduleProcesses;

/*
	protected void isLoaded(boolean customTheme) {
		*/
/*WebElement elementToBeChecked = gettingStarted;
		if (customTheme) {
			elementToBeChecked = meInNavigator;
		}
		*//*

		if (PageLoadHelper.waitForElementVisible(meInNavigator) == null) {
			throw new TestErrorException("The Me link is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("NavigatorPage is loaded");
	}
*/


	@Override
	protected void isLoaded() {
		/*WebElement elementToBeChecked = gettingStarted;
		if (SelUtil.isCustomThemeEnabled()) {
			elementToBeChecked = meInNavigator;
		}
		if (PageLoadHelper.waitForElementVisible(elementToBeChecked) == null) {
		*/
		if (PageLoadHelper.waitForElementVisible(NavigatorHeader) == null) {
			throw new TestErrorException("The NavigatorHeader Started link is not visible after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
		StepReport.info("NavigatorPage is loaded");
	}

	public TravelandExpenses clickExpenses() {
		StepReport.info("Click Expenses");
		/*if (SelUtil.isCustomThemeEnabled()) {
			DriverUtil.sleep(5000L);
			DriverUtil.clickByJS(expensesInCustomTheme);
		} else {
			PageLoadHelper.waitForElementClickable(expenses);
			expenses.click();
		}*/
		PageLoadHelper.waitForElementClickable(expenses);
		expenses.click();
		DriverUtil.sleep(2000L);
		TravelandExpenses travelandExpenses = PageFactory.getPage(TravelandExpenses.class);
		travelandExpenses.isLoaded();
		return travelandExpenses;
	}

	public ManageSalesQuota clickQuotas() {
		StepReport.info("Click Quotas");
		PageLoadHelper.waitForElementClickable(clickQuotas);
		DriverUtil.sleep(2000L);
		clickQuotas.click();
		ManageSalesQuota manageSalesQuota = PageFactory.getPage(ManageSalesQuota.class);
		manageSalesQuota.isLoaded();
		return manageSalesQuota;
	}

	public TerritoriesPage clickTerritories() {
		StepReport.info("Click Territories");
		PageLoadHelper.waitForElementClickable(territories);
		DriverUtil.sleep(2000L);
		territories.click();
		TerritoriesPage territoriesPage = PageFactory.getPage(TerritoriesPage.class);
		territoriesPage.isLoaded();
		return territoriesPage;
	}

	public CareerPerformance clickCareerPerf() {
		DriverUtil.sleep(3000);
		StepReport.info("Click Career and Performance");
		DriverUtil.sleep(3000L);
		CareerPerf.click();
		CareerPerformance nav = PageFactory.getPage(CareerPerformance.class);
		nav.isLoaded();
		return nav;
	}


	public Invoices clickInvoices() {
		StepReport.info("Click Invoices");
		/*if (SelUtil.isCustomThemeEnabled()) {
			DriverUtil.sleep(5000L);
			DriverUtil.clickByJS(invoicesInCustomTheme);
		} else {
			PageLoadHelper.waitForElementClickable(invoicesElem);
			invoicesElem.click();
		}*/
		PageLoadHelper.waitForElementClickable(invoicesElem);
		invoicesElem.click();
		DriverUtil.sleep(2000L);
		Invoices invoices = PageFactory.getPage(Invoices.class);
		invoices.isLoaded();
		return invoices;
	}

	public Transactions clickTransactions() {
		StepReport.info("Click Transactions");
		PageLoadHelper.waitForElementClickable(transactionsElem);
		transactionsElem.click();
		DriverUtil.sleep(2000L);
		Transactions transactions = PageFactory.getPage(Transactions.class);
		transactions.isLoaded();
		return transactions;
	}

	public CashBalances clickCashBalances() {
		StepReport.info("Click Cash Balances");
		PageLoadHelper.waitForElementClickable(cashBalancesElem);
		cashBalancesElem.click();
		DriverUtil.sleep(2000L);
		CashBalances cashBalances = PageFactory.getPage(CashBalances.class);
		cashBalances.isLoaded();
		return cashBalances;
	}

	public TransactionConsole clickTransConsole(){
		StepReport.info("Click Transaction Console");
		/*if (SelUtil.isCustomThemeEnabled()) {
			DriverUtil.sleep(5000L);
			DriverUtil.clickByJS(transactionConsoleInCustomTheme);
		} else {
			PageLoadHelper.waitForElementClickable(transactionConsole);
			DriverUtil.sleep(2000L);
			transactionConsole.click();
		}*/
		PageLoadHelper.waitForElementClickable(transactionConsole);
		DriverUtil.sleep(2000L);
		transactionConsole.click();
		DriverUtil.sleep(2000L);
		TransactionConsole transactionConsole = PageFactory.getPage(TransactionConsole.class);
		return  transactionConsole;
	}
	public NewPersonPage clickNewPerson(){
		StepReport.info("click New Person");
		/*if (SelUtil.isCustomThemeEnabled()) {
			DriverUtil.sleep(5000L);
			DriverUtil.clickByJS(newPersonInCustomTheme);
		} else {
			newPerson.click();
		}
*/
		DriverUtil.sleep(2000L);
		newPerson.click();
		NewPersonPage newPerson= PageFactory.getPage(NewPersonPage.class);
		newPerson.isLoaded();
		return  newPerson;

	}
	public DirectoryPage clickDirectory(){
		StepReport.info("click directory");
		directory.click();
		DirectoryPage directoryPage= PageFactory.getPage(DirectoryPage.class);
		directoryPage.isLoaded();
		return  directoryPage;
	}
	public WorkForceStructurePage clickWorForceStructures(){
		StepReport.info("click directory");
		workforceStructure.click();
		WorkForceStructurePage workForceStructurePage= PageFactory.getPage(WorkForceStructurePage.class);
		return  workForceStructurePage;

	}
	public PersonalInfo clickPersonalinfo() {
		StepReport.info("Click Personal info");
		personal.click();
		DriverUtil.sleep(2000L);
		PersonalInfo changepersonalInfo = PageFactory.getPage(PersonalInfo.class);
		return changepersonalInfo;
	}

	public PersonalInfo clickPersonalDetails() {
		StepReport.info("Click PersonalDetails page");
		personaldet.click();
		DriverUtil.sleep(2000L);
		PersonalInfo changepersonalInfo = PageFactory.getPage(PersonalInfo.class);
		return changepersonalInfo;
	}
	public PartnersPage clickPartners(){
		StepReport.info("click Partners");
		partners.click();
		DriverUtil.sleep(2000L);
		PartnersPage partnersPage = PageFactory.getPage(PartnersPage.class);
		partnersPage.isLoaded();
		return  partnersPage;
	}

	public ProgramsPage clickPrograms(){
		StepReport.info("click Programs");
		programs.click();
		DriverUtil.sleep(2000L);
		ProgramsPage programsPage = PageFactory.getPage(ProgramsPage.class);
		programsPage.isLoaded();
		return  programsPage;
	}

	public ScheduledProcesses clickScheduledProcesses() {
		StepReport.info("Click Scheduled Processes");
		PageLoadHelper.waitForElementClickable(scheduledProcesses);
		scheduledProcesses.click();
		DriverUtil.sleep(2000L);
		ScheduledProcesses scheduledProcesses = PageFactory.getPage(ScheduledProcesses.class);
		scheduledProcesses.isLoaded();
		return scheduledProcesses;
	}

	public AuditReports clickAuditReports() {
		StepReport.info("Click Audit Reports");
		PageLoadHelper.waitForElementClickable(auditReports);
		auditReports.click();
		DriverUtil.sleep(2000L);
		AuditReports auditReports = PageFactory.getPage(AuditReports.class);
		auditReports.isLoaded();
		return auditReports;
	}

	public SecurityConsole clickSecurityConsole() {
		StepReport.info("Click Security Console");
		PageLoadHelper.waitForElementClickable(securityConsole);
		securityConsole.click();
		DriverUtil.sleep(2000L);
		SecurityConsole securityConsole = PageFactory.getPage(SecurityConsole.class);
		securityConsole.isLoaded();
		return securityConsole;
	}

	public PersonManagementPage clickPersonManagement() {
		StepReport.info("Click Person Management");
		PageLoadHelper.waitForElementClickable(personManagement);
		personManagement.click();
		DriverUtil.sleep(2000L);
		PersonManagementPage personManagement = PageFactory.getPage(PersonManagementPage.class);
		personManagement.isLoaded();
		return personManagement;
	}

	public ContactsPage clickSalesContatcs() {
		StepReport.info("Click Sales->Contacts");
		PageLoadHelper.waitForElementClickable(salesContatcts);
		salesContatcts.click();
		DriverUtil.sleep(2000L);
		ContactsPage contatcts = PageFactory.getPage(ContactsPage.class);
		contatcts.isLoaded();
		return contatcts;
	}

	public NavigatorPopUp clickMore() {
		StepReport.info("Click More->");
		PageLoadHelper.waitForElementClickable(clickMore);
		clickMore.click();
		DriverUtil.sleep(2000L);
		NavigatorPopUp navigatorPopUp = PageFactory.getPage(NavigatorPopUp.class);
		navigatorPopUp.isLoaded();
		return navigatorPopUp;
	}

	public void showMore() {
		StepReport.info("Click More->");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByAction(clickMore,true);
		DriverUtil.sleep(2000L);
	}
	
	//	For ESS
    public ESSUIMonitorPage clickESSConsole(){
        StepReport.info("Clicking on More Link");
		PageLoadHelper.waitForElementclickable(essMore);
		essMore.click();
        StepReport.info("Clicking on Scheduled Processes");
		DriverUtil.sleep(10000);
        essMonitorLink.click();
        DriverUtil.sleep(5000);
        StepReport.info("Clicked on Scheduled Processes");
        ESSUIMonitorPage essMonitor= PageFactory.getPage(ESSUIMonitorPage.class);
        essMonitor.isLoaded();
        return essMonitor;
    }

//	//	For ESS
//	public ESSUIMonitorPage clickESSConsole(){
//		StepReport.info("Clicking on More Link");
//		PageLoadHelper.waitForElementclickable(essMore);
//		essMore.click();
//		StepReport.info("Clicking on Scheduled Processes");
//		DriverUtil.sleep(10000);
//		essMonitorLink.click();
//		DriverUtil.sleep(3000);
//		StepReport.info("Clicked on Scheduled Processes");
//		ESSUIMonitorPage essMonitor= PageFactory.getPage(ESSUIMonitorPage.class);
//		essMonitor.isLoaded();
//		return essMonitor;
//	}
}
