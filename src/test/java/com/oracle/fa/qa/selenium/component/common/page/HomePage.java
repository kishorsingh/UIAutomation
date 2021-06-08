package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.fa.qa.selenium.component.fin.page.Invoices;
import com.oracle.fa.qa.selenium.component.hcm.page.HCMBasePageObject;
import com.oracle.fa.qa.selenium.component.util.SelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.oracle.fa.qa.selenium.component.prc.page.POOverView;
import com.oracle.fa.qa.selenium.component.prc.page.Requisitions;
import com.oracle.fa.qa.selenium.component.crm.page.*;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;

/**
 * Created by bpadhy on 1/17/18.
 */
public class HomePage extends HCMBasePageObject {
	@FindBy(id = "ATK_HOMEPAGE_FUSE_REPORTS")
	WebElement atkHomePageAnalytics;

	//@FindBy(css = ".AFBrandingLinkColor")
	@FindBy(xpath = "//*[contains(@id,'UIScmil1u::icon')]")
	WebElement signOutdropdown;

	@FindBy(xpath = "//*[text()='Sign Out']")
	WebElement signOut;

	@FindBy(css = "a[title='Navigator']")
	WebElement navigator;

	@FindBy(css = "div[id='groupNode_tools']")
	WebElement tools;

	@FindBy(xpath = "//a[contains(@id,'ATK_FUSE_GETTING_STARTED_')]")
	WebElement gettingStarted;

	@FindBy(xpath = "//*[text()='Me']")
	WebElement meIcon;

	@FindBy(css = "div[id*='tools_setup_and_maintenance']")
	WebElement setupMaintenance;

	@FindBy(xpath = "//span[text()='Setup and Maintenance']")
	WebElement setupMaintenanceInCustomTheme;

	@FindBy(id = "ATK_HOMEPAGE_FUSE_WORKLIST")
	WebElement worklist;

	@FindBy(css = "a[title*='Notifications']")
	WebElement bellNotification;

	@FindBy(xpath = "//*[@title='Home']")
	WebElement homeElem;

	@FindBy(xpath = "//*[@id='groupNode_procurement']")
	WebElement procurmentGroup;

	@FindBy(xpath = "//*[@id='itemNode_my_information_purchase_requisitions']")
	WebElement requisition;

	@FindBy(xpath = "//*[@id='itemNode_procurement_PurchaseOrders']")
	WebElement purchaseOrder;

	@FindBy(xpath = "//*[@id='groupNode_partner_management']")
	WebElement partnerManagementGroup;

	@FindBy(xpath = "//a[contains(text(),'Partners')]")
	WebElement partners;

	@FindBy(xpath = "//*[contains(@id,'ZPM_PROGRAMS_CARD')]")
	WebElement programs;

	@FindBy(xpath = "//div[contains(@id,'contract_management_contracts')]")
	WebElement contracts;


	@FindBy(xpath = ".//*[@id='groupNode_incentive_compensation']")
	WebElement clickIncentiveCompensation;

    //@FindBy(xpath = "//img[contains(@src,'personDisplayName')]")
    @FindBy(xpath = "//*[@id='pt1:_UIScmil1u::icon']")
    WebElement settingsAndActions;

	@FindBy(xpath = "//*[contains(@id,'IC_SALES_COMPENSATION_FUSE_CARD')]")
	WebElement salesCompensation;

	@FindBy(xpath = "//*[@id='itemNode_sales_territories_and_quotas_16']")
	WebElement territories;

	@FindBy(xpath = "//a[contains(@id,'groupNode_sales')]")
	WebElement clickSales;

	@FindBy(xpath = "//*[@id='HZ_FOUNDATIONPARTIES_CONTACTS_CRM_CARD']")
	WebElement clickContacts;

	@FindBy(xpath = "//*[@id='groupNode_manager_resources']")
	WebElement clickMyTeam;

	@FindBy(xpath = "//a[text()='Team Compensation']")
	WebElement teamCompensation;

	@FindBy(xpath = "//a[contains(@id,'itemNode_sales_quotas')]")
	WebElement clickQuotas;

	@FindBy(xpath =".//a[text()='More Details']")
	WebElement moredetails;

	@FindBy(xpath = "//*[@id='pt1:_UIScmi1']")
	WebElement setPreferences;

	@FindBy(xpath = "(//a[text()='Security Console'])[1]")
	WebElement securityConsole;

	@FindBy(xpath = "(//a[text()='Invoices'])[1]")
	WebElement invoices;

    @FindBy(xpath="//*[text()='Application Composer']")
    WebElement clickApplicationCompposer;

	@FindBy(xpath="//*[text()='Appearance']")
	WebElement clickAppearance;

	@FindBy(xpath="//*[contains(text(),'Partner')]")
	WebElement clickPartnerMgmt;


	/**
	 * Determine whether or not the component is loaded. When the component is loaded, this method
	 * will return, but when it is not loaded, an Error should be thrown. This also allows for complex
	 * checking and error reporting when loading a page, which in turn supports better error reporting
	 * when a page fails to load.
	 * <p>
	 * <p>
	 * This behaviour makes it readily visible when a page has not been loaded successfully, and
	 * because an error and not an exception is thrown tests should fail as expected. By using Error,
	 * we also allow the use of junit's "Assert.assert*" methods
	 *
	 * @throws Error when the page is not loaded.
	 */
	@Override
	protected void isLoaded() {
		if (PageLoadHelper.waitForElementClickable(meIcon) == null) {
			throw new TestErrorException("The Getting Started link is not clickable after " +
					Timeout.pageLoadValue().seconds() + " seconds.");
		}
	}

	public void signout() {
//    	PageLoadHelper.waitForJetPageReady(driver);
		StepReport.info("Click Signout Dropdown");
		//DriverUtil.scrollIntoView(signOutdropdown);
//    	PageLoadHelper.waitForJetPageReady(driver);
		//signOutdropdown.click();
		PageLoadHelper.waitForElementClickable(signOutdropdown);
		DriverUtil.clickByAction(signOutdropdown,true);
		DriverUtil.sleep(3000);
		StepReport.info("Click Signout");
		PageLoadHelper.waitForElementClickable(signOut);
		DriverUtil.clickByAction(signOut,true);
		//signOut.click();
		LogoutConfirm logoutConfirm = PageFactory.getPage(LogoutConfirm.class);
		LoginPage login = PageFactory.getPage(LoginPage.class);
		if(logoutConfirm.isLogoutConfirmPageLoaded()){
			logoutConfirm.isLoaded();
			logoutConfirm.clickLogoutConfirm();
		}
		login.isLoaded();
	}

	public Navigator clickNavigator() {
		DriverUtil.sleep(3000);
		StepReport.info("Click Navigator");
		PageLoadHelper.waitForElementClickable(navigator);
		DriverUtil.sleep(3000L);
		/*boolean customTheme = this.checkTheme();
		navigator.click();*/
		navigator.click();
		Navigator navigator = PageFactory.getPage(Navigator.class);
		//navigator.isLoaded(customTheme);
		navigator.isLoaded();
		return navigator;
	}




	public void clickTools() {
		StepReport.info("Click Tools");
		PageLoadHelper.waitForElementClickable(tools);
		DriverUtil.sleep(2000L);
		tools.click();
	}
	public void clickProcurment() {
		StepReport.info("Click Procurment");
		PageLoadHelper.waitForElementClickable(procurmentGroup);
		DriverUtil.sleep(2000L);
		procurmentGroup.click();
	}
	public Requisitions clickPurchaseRequisition() {
		StepReport.info("Click Purchase Requisition");
		PageLoadHelper.waitForElementClickable(requisition);
		DriverUtil.sleep(2000L);
		requisition.click();
		Requisitions requisitions = PageFactory.getPage(Requisitions.class);
		requisitions.isLoaded();
		return requisitions;
	}

	public POOverView ClickPurchaseOrder() {
		StepReport.info("Click Purchase Orders");
		PageLoadHelper.waitForElementClickable(purchaseOrder);
		DriverUtil.sleep(2000L);
		purchaseOrder.click();
		POOverView poOverView = PageFactory.getPage(POOverView.class);
		poOverView.isLoaded();
		return poOverView;
	}

	public SetupMaintenance clickSetupMaintenance() {
		StepReport.info("Click Setup and Maintenance");
		if(this.checkTheme()) {
			Navigator navigator = this.clickNavigator();
			DriverUtil.sleep(5000L);
			DriverUtil.clickByJS(setupMaintenanceInCustomTheme);
		} else {
			PageLoadHelper.waitForElementClickable(setupMaintenance);
			DriverUtil.sleep(2000L);
			setupMaintenance.click();
		}
		SetupMaintenance setupMaintenance=PageFactory.getPage(SetupMaintenance.class);
		setupMaintenance.isLoaded();
		return setupMaintenance;
	}

	public void clickHome() {
		StepReport.info("Click Home");
		PageLoadHelper.waitForElementVisible(homeElem);
		DriverUtil.sleep(2000L);
		homeElem.click();
		DriverUtil.sleep(2000L);
	}

	public void clickPartnerMgmt() {
		StepReport.info("Click Home");
		PageLoadHelper.waitForElementVisible(clickPartnerMgmt);
		DriverUtil.sleep(2000L);
		clickPartnerMgmt.click();
		DriverUtil.sleep(2000L);
	}

	public WorklistNotificationsApprovals clickWorklist() {
		StepReport.info("Click Worklist");
		PageLoadHelper.waitForElementClickable(worklist);
		DriverUtil.sleep(2000L);
		worklist.click();
		WorklistNotificationsApprovals worklistNotificationsApprovals = PageFactory.getPage(WorklistNotificationsApprovals.class);
		worklistNotificationsApprovals.isLoaded();
		return worklistNotificationsApprovals;
	}

	public BellNotification clickBellNotification() {
		StepReport.info("Click Bell Notification");
		PageLoadHelper.waitForElementClickable(bellNotification);
		DriverUtil.sleep(4000L);
		bellNotification.click();
		BellNotification bellNotification = PageFactory.getPage(BellNotification.class);
		bellNotification.isLoaded();
		return bellNotification;
	}

	public void clickPartnerManagement() {
		StepReport.info("Click PartnerManagement");
		PageLoadHelper.waitForElementClickable(partnerManagementGroup);
		DriverUtil.sleep(2000L);
		partnerManagementGroup.click();
	}

	public PartnersPage clickPartner() {
		StepReport.info("Click Partner");
		PageLoadHelper.waitForElementClickable(partners);
		DriverUtil.sleep(2000L);
		partners.click();
		PartnersPage partner = PageFactory.getPage(PartnersPage.class);
		partner.isLoaded();
		return partner;

	}

	public ProgramsPage clickPrograms() {
		StepReport.info("Click Programs");
		PageLoadHelper.waitForElementClickable(programs);
		DriverUtil.sleep(2000L);
		programs.click();
		ProgramsPage programs = PageFactory.getPage(ProgramsPage.class);
		programs.isLoaded();
		return programs;

	}

	public ContractsPage clickContracts() {
		StepReport.info("Click Contracts");
		PageLoadHelper.waitForElementClickable(contracts);
		DriverUtil.sleep(2000L);
		contracts.click();
		ContractsPage contracts = PageFactory.getPage(ContractsPage.class);
		contracts.isLoaded();
		return contracts;

	}

	public SettingsAndActions clickSettingsAndActions() {
		StepReport.info("Click Settings and Actions");
		PageLoadHelper.waitForElementClickable(settingsAndActions);
		DriverUtil.sleep(2000L);
		//settingsAndActions.click();
		DriverUtil.doubleClick(settingsAndActions);
		SettingsAndActions settings = PageFactory.getPage(SettingsAndActions.class);
		settings.isLoaded();
		return settings;

	}

	public SalesCompensation clickSalesCompensation() {
		StepReport.info("Click Sales Compensation");
		DriverUtil.sleep(2000L);
		DriverUtil.clickByJS(salesCompensation);
		DriverUtil.sleep(5000L);
		SalesCompensation salesCompensation = PageFactory.getPage(SalesCompensation.class);
		salesCompensation.isLoaded();
		return salesCompensation;

	}

	public void clickIncentiveCompensation() {
		StepReport.info("Click IncentiveCompensation");
		PageLoadHelper.waitForElementClickable(clickIncentiveCompensation);
		DriverUtil.sleep(2000L);
		clickIncentiveCompensation.click();
	}
	public void clickSales() {
		StepReport.info("Click Sales");
		PageLoadHelper.waitForElementClickable(clickSales);
		DriverUtil.sleep(2000L);
		clickSales.click();
	}

	public boolean checkTheme(){
		Boolean isCustomTheme=false;
		try{

			if(gettingStarted.isDisplayed()) {
				isCustomTheme=false;
				StepReport.info("Default Theme");
			}else{
				isCustomTheme=true;
				StepReport.info("Custom Theme");
			}
			return isCustomTheme;
		}catch (Exception e){
			StepReport.info("Exception",e.getMessage());
			StepReport.info("Custom Theme");
			return true;
		}
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

	public ManageSalesQuota clickQuotas() {
		StepReport.info("Click Quotas");
		PageLoadHelper.waitForElementClickable(clickQuotas);
		DriverUtil.sleep(2000L);
		clickQuotas.click();
		ManageSalesQuota manageSalesQuota = PageFactory.getPage(ManageSalesQuota.class);
		manageSalesQuota.isLoaded();
		return manageSalesQuota;
	}

	public ContactsPage clickContacts() {
		StepReport.info("Click ContactsPage");
		PageLoadHelper.waitForElementClickable(clickContacts);
		DriverUtil.sleep(2000L);
		clickContacts.click();
		ContactsPage contactsPage = PageFactory.getPage(ContactsPage.class);
		contactsPage.isLoaded();
		return contactsPage;

	}

	public void clickMyteam(){
		StepReport.info("Click MyTeam");
		PageLoadHelper.waitForElementClickable(clickMyTeam);
		DriverUtil.sleep(2000L);
		clickMyTeam.click();
	}

	public SecurityConsole clickSecurityConsole() {
		StepReport.info("Click Security Console");
		DriverUtil.clickByJS(securityConsole);
		DriverUtil.sleep(2000L);
		SecurityConsole securityConsole = PageFactory.getPage(SecurityConsole.class);
		securityConsole.isLoaded();
		return securityConsole;
	}

	public Invoices clickInvoices() {
		StepReport.info("Click Invoices");
		DriverUtil.clickByJS(invoices);
		DriverUtil.sleep(3000L);
		Invoices invoices = PageFactory.getPage(Invoices.class);
		invoices.isLoaded();
		return invoices;
	}

	public TeamCompensationPage clickTeamCompensation(){
		StepReport.info("Click TeamCompensation");
		PageLoadHelper.waitForElementClickable(teamCompensation);
		DriverUtil.sleep(2000L);
		teamCompensation.click();
		TeamCompensationPage teamCompensationPage = PageFactory.getPage(TeamCompensationPage.class);
		teamCompensationPage.isLoaded();
		return teamCompensationPage;
	}

	public BellNotification clickMoredetails() {
		StepReport.info("Click More details");
		PageLoadHelper.waitForElementClickable(bellNotification);
		DriverUtil.sleep(4000L);
		bellNotification.click();
		DriverUtil.sleep(1000L);
		PageLoadHelper.waitForElementClickable(moredetails);
		moredetails.click();
		DriverUtil.sleep(1000L);
		BellNotification moredetails = PageFactory.getPage(BellNotification.class);
		//moredetails.isLoaded();
		return moredetails;
	}

	public SetPreferences clickSetPreferences() {
		StepReport.info("Click Signout Dropdown");
		DriverUtil.scrollIntoView(signOutdropdown);
		signOutdropdown.click();
		DriverUtil.sleep(5000);
		StepReport.info("Click Set Preferences");
		DriverUtil.clickByJS(setPreferences);
		DriverUtil.sleep(5000);
		SetPreferences setPreferences = PageFactory.getPage(SetPreferences.class);
		setPreferences.isLoaded();
		return  setPreferences;
	}

	public void logoutInAllLanguage() {
		StepReport.info("Click Signout Dropdown");
		int maxRetry = 1;
		for (int i=0; i<maxRetry; i++) {
			try {
				DriverUtil.scrollIntoView(signOutdropdown);
				signOutdropdown.click();
				DriverUtil.sleep(2000);
				StepReport.info("Click Signout");
				String signOutLinkXPath = "//a[contains(@class, 'fndSignOutLink')] | //a[@id='_FOpt1:_UISlg1']";
				WebElement signOutLink = DriverUtil.getElement(By.xpath(signOutLinkXPath));
				PageLoadHelper.waitForElementVisible(signOutLink);
				signOutLink.click();
				LogoutConfirm logoutConfirm = PageFactory.getPage(LogoutConfirm.class);
				logoutConfirm.isLoaded();
				logoutConfirm.clickLogoutConfirm();
				DriverUtil.sleep(5000);
			} catch (Exception e) {
				StepReport.info("Logout failed at retry time " + (i+1) + ", will retry logout");
				String homePageIconXPath = "//a[@id='pt1:_UIShome']";
				WebElement homePageIcon = DriverUtil.getElement(By.xpath(homePageIconXPath));
				PageLoadHelper.waitForElementVisible(homePageIcon);
				homePageIcon.click();
				DriverUtil.sleep(10000);
				if (i == maxRetry) {
					throw new RuntimeException(e);
				}
			}
		}
	}

    public ApplicationComposer clickApplicationComposer(){
			DriverUtil.sleep(2000L);
			clickApplicationCompposer.click();
			ApplicationComposer applicationComposer = PageFactory.getPage(ApplicationComposer.class);
			applicationComposer.isLoaded();
			return applicationComposer;


	}

	public ThemesPage clickAppearance() {
		StepReport.info("Click On >>Appearance");
		DriverUtil.sleep(2000L);

		if(clickApplicationCompposer.isDisplayed()){
			StepReport.info("Click On >>Application Composer");
			clickApplicationCompposer.click();
			ApplicationComposer applicationComposer = PageFactory.getPage(ApplicationComposer.class);
			applicationComposer.isLoaded();
			StepReport.info("Click >>Appearance");
			ThemesPage themesPage=applicationComposer.clickAppearance();
			return themesPage;
		}else
		{   StepReport.info("Click >>Navigator");
			Navigator navigator=clickNavigator();
			DriverUtil.sleep(2000L);
			StepReport.info("Click >>More");
			navigator.clickMore();
			DriverUtil.sleep(2000L);
			StepReport.info("Click >>Appearance");
			PageLoadHelper.waitForElementClickable(clickAppearance);
			DriverUtil.clickByAction(clickAppearance,true);
			ThemesPage themesPage = PageFactory.getPage(ThemesPage.class);
			themesPage.isLoaded();
			return themesPage;
		}


	}



}
