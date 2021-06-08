package com.oracle.fa.qa.selenium.base;

import java.lang.reflect.Method;

import com.oracle.fa.qa.selenium.component.util.SelUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import groovy.ui.SystemOutputInterceptor;
import org.testng.ITestResult;
import org.testng.TestNGException;
import org.testng.annotations.*;

import com.oracle.fa.qa.selenium.component.common.page.HomePage;
import com.oracle.fa.qa.selenium.component.bpm.page.EMHomePage;
import com.oracle.fa.qa.selenium.component.common.page.LoginPage;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.test.UITestBase;
import com.github.wiselenium.core.WiseContext;

public class SeleniumBase extends UITestBase{
	public static boolean isFinancialSetupExists=false;
    public static boolean isAutoFinancialSetupExists=false;
    public static boolean isForceFinancialSetupExists=false;
	public static boolean isHireEmployeeRuleConfigured=false;
	public static boolean isAutoHireEmployeeRuleConfigured=false;
	public static boolean isHireContingentEmployeeRuleConfigured=false;
	public static boolean isRequisitionRuleConfigured=false;
	public static boolean isRequisitionLenovoRuleConfigured=false;
	public static boolean isPORuleConfigured=false;
	public static boolean isUserDefinedRuleConfigured=false;
	public static String isCustomThemeEnabled="";
	public HomePage homePage=null;
	public EMHomePage emHomePage=null;
	public static String faAdminUser="";
	public static String faAdminUserPwd="";
	public static String hcmUser1="";
	public static String hcmUser1Pwd="";
	public static String hcmUser2="";
	public static String hcmUser2Pwd="";
	public static String hcmUser3="";
	public static String hcmUser3Pwd="";
	public static String hcmUser4="";
	public static String hcmUser4Pwd="";
	public static String hcmUser5="";
	public static String hcmUser5Pwd="";
	public static String finUser1="";
	public static String finUser1Pwd="";
	public static String finUser2="";
	public static String finUser2Pwd="";
	public static String finUser3="";
	public static String finUser3Pwd="";
	public static String finUser4="";
	public static String finUser4Pwd="";
	public static String finUser5="";
	public static String finUser5Pwd="";
	public static String finUser6="";
	public static String finUser6Pwd="";
	public static String finUser7="";
	public static String finUser7Pwd="";
	public static String finUser8="";
	public static String finUser8Pwd="";
	public static String finUser9="";
	public static String finUser9Pwd="";
	public static String applConsultant="";
	public static String applConsultantPwd="";
	public static String interCompanyAccountant="";
	public static String interCompanyAccountantPwd="";
	public static String internalAuditor="";
	public static String internalAuditorPwd="";
	public static String cashManager="";
	public static String cashManagerPwd="";
	public static String fusionUser="";
	public static String fusionUserPwd="";
	public static String prcReqUser1="";
	public static String prcReqUser1Pwd="";
	public static String prcReqUser2="";
	public static String prcReqUser2Pwd="";
	public static String prcReqUser3="";
	public static String prcReqUser3Pwd="";
	public static String prcReqUser10="";
	public static String prcReqUser10Pwd="";
	public static String prcBuyerUser1="";
	public static String prcBuyerUser1Pwd="";
	public static String prcBuyerUser2="";
	public static String prcBuyerUser2Pwd="";
	public static String prcBuyerUser3="";
	public static String prcBuyerUser3Pwd="";
    public static String prcReqUser4="";
    public static String prcReqUser4Pwd="";
	public static String prcReqUser5="";
	public static String prcReqUser5Pwd="";
	public static String prcReqUser18="";
	public static String prcReqUser18Pwd="";
	public static String prcReqUser32="";
	public static String prcReqUser32Pwd="";
	public static String prcReqUser33="";
	public static String prcReqUser33Pwd="";
	public static String crmCOMUser1="";
	public static String crmCOMUser1Pwd="";
	public static String crmCOMUser2="";
	public static String crmCOMUser2Pwd="";
	public static String crmSCMVUser1="";
	public static String crmSCMVUser1Pwd="";
	public static String crmECUserName ="";
	public static String crmECUserPassword ="";
	public static String crmECApproverName ="";
	public static String crmECApproverPassword ="";
	public static String crmRAUserName ="";
	public static String crmRAUserPassowrd ="";
	public static String crmTMUserName ="";
	public static String crmTMUserPassword ="";
	public static String crmAttFilePath ="";
	public static String databaseSOAUser="";
	public static String databaseSOAUserPwd="";
	public static String databaseUser1="";
	public static String databaseUser1Pwd="";
	public static String databaseHostName="";
	public static String databasePort="";
	public static String databaseSID="";

	public static String emURL="";
	public static String faEnvVersion="";
	public static String nisEncryptedPwd="";

	public static String bpmAdminUsername;
	public static String bpmAdminPassword;
	public static String bpmCompositeName;
	public static String bpmDomainName;
	public static String bpmSSOEmail;
	public static String bpmSSOPassword;
	public static String bpmHumanTask;
	public static String bpmTestEmailID;
	public static String bpmWorklistURL;
	public static String bpmEmailSender;



	@BeforeSuite(alwaysRun = true)
	public final void initData() throws Exception {
		getUserPwd();
		getDatabaseConnParam();
		//SelUtil.UpdatePropertiesFile();
	}

//	@BeforeSuite(alwaysRun = true)
	@Parameters("environment")
	public final void envTest(@Optional String envType){
		try {
			if (envType.equalsIgnoreCase("FA")) {
				login();
				logout();
			}
		}
		catch(NullPointerException e){
			System.out.println("Running individual test from IntelliJ");
			}
	}

	void getUserPwd() {
		faAdminUser=FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
		faAdminUserPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
		hcmUser1=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user1");
		hcmUser1Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user1.pwd");
		hcmUser2=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user2");
		hcmUser2Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user2.pwd");
		hcmUser3=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user3");
		hcmUser3Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user3.pwd");
		hcmUser4=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user4");
		hcmUser4Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user4.pwd");
		hcmUser5=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user5");
		hcmUser5Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.hcm.user5.pwd");
		finUser1=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user1");
		finUser1Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user1.pwd");
		finUser2=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user2");
		finUser2Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user2.pwd");
		finUser3=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user3");
		finUser3Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user3.pwd");
		finUser4=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user4");
		finUser4Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user4.pwd");
		finUser5=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user5");
		finUser5Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user5.pwd");
		finUser6=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user6");
		finUser6Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user6.pwd");
		finUser7=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user7");
		finUser7Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user7.pwd");
		finUser8=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user8");
		finUser8Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user8.pwd");
		finUser9=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user9");
		finUser9Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user9.pwd");
		applConsultant=FrameworkContext.getInstance().getTestConfigParams().getString("test.appl.counsultant");
		applConsultantPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.appl.counsultantPwd");
		interCompanyAccountant=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.ic.accountant");
		interCompanyAccountantPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.ic.accountant.pwd");
		internalAuditor=FrameworkContext.getInstance().getTestConfigParams().getString("test.internal.auditor.user");
		internalAuditorPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.internal.auditor.user.pwd");
		cashManager=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.cm.user");
		cashManagerPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.cm.user.pwd");
		fusionUser=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.fusion.user");
		fusionUserPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.fusion.user.pwd");
		prcReqUser1=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user1");
		prcReqUser1Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user1.pwd");
		prcReqUser2=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user2");
		prcReqUser2Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user2.pwd");
		prcReqUser3=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user3");
		prcReqUser3Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user3.pwd");
        prcReqUser4=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user4");
        prcReqUser4Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user4.pwd");
		prcReqUser5=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user5");
		prcReqUser5Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user5.pwd");
		prcReqUser10=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user5");
		prcReqUser10Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user5.pwd");
		prcReqUser18=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user18");
		prcReqUser18Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user18.pwd");
		prcReqUser32=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user32");
		prcReqUser32Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user32.pwd");
		prcReqUser33=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user33");
		prcReqUser33Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.req.user33.pwd");
		prcBuyerUser1=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.buyer.user1");
		prcBuyerUser1Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.buyer.user1.pwd");
		prcBuyerUser2=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.buyer.user2");
		prcBuyerUser2Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.buyer.user2.pwd");
		prcBuyerUser3=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.buyer.user3");
		prcBuyerUser3Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.prc.buyer.user3.pwd");


		crmCOMUser1=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.com.user1");
		crmCOMUser1Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.com.user1.pwd");
		crmCOMUser2=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.com.user2");
		crmCOMUser2Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.com.user2.pwd");
		crmSCMVUser1=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.scmv.user1");
		crmSCMVUser1Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.scmv.user1.pwd");
		crmECUserName =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.ec.ecUserName");
		crmECUserPassword =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.ec.ecUserPassword");
		crmECApproverName =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.ec.ecApproverName");
		crmECApproverPassword =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.ec.ecApproverPassword");
		crmRAUserName =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oic.raUserName");
		crmRAUserPassowrd =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.oic.raUserPassword");
		crmTMUserName =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.stm.UserName");
		crmTMUserPassword =FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.stm.UserPassword");
		crmAttFilePath=FrameworkContext.getInstance().getTestConfigParams().getString("test.crm.att.FilePath");

		isCustomThemeEnabled=FrameworkContext.getInstance().getTestConfigParams().getString("test.fa.isCustomThemeEnabled");

		databaseUser1=FrameworkContext.getInstance().getTestConfigParams().getString("test.database.user1");
		databaseUser1Pwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.database.user1.pwd");

		databaseSOAUser=FrameworkContext.getInstance().getTestConfigParams().getString("test.database.soainfra.user");
		databaseSOAUserPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.database.soainfra.user.pwd");

		emURL=FrameworkContext.getInstance().getTestConfigParams().getString("test.em.url");
		faEnvVersion=FrameworkContext.getInstance().getTestConfigParams().getString("test.fa.env.version");
		nisEncryptedPwd=FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nis.encrypted.pwd");

		bpmAdminUsername=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.AdminUsername");
		bpmAdminPassword=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.AdminPassword");
		bpmCompositeName=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.compositeName");
		bpmDomainName=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.domainName");
		bpmSSOEmail=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.SSOEmail");
		bpmSSOPassword=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.SSOPassowrd");
		bpmHumanTask=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.humanTask");
		bpmTestEmailID=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.TestNotificationEmailRecipient");
		bpmWorklistURL=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.worklistURL");
		bpmEmailSender=FrameworkContext.getInstance().getTestConfigParams().getString("test.bpm.EmailSender");


	}

	void getDatabaseConnParam() {
		databaseHostName=FrameworkContext.getInstance().getTestConfigParams().getString("test.database.hostname");
		databasePort=FrameworkContext.getInstance().getTestConfigParams().getString("test.database.port");
		databaseSID=FrameworkContext.getInstance().getTestConfigParams().getString("test.database.sid");
	}

	public void login() {
		StepReport.info("Start Login");
		LoginPage loginPage = PageFactory.getPage(LoginPage.class);
		loginPage.get(FrameworkContext.getInstance().getTestConfigParams().getBaseUrl());
		homePage = loginPage.login(FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user1"),
				FrameworkContext.getInstance().getTestConfigParams().getString("test.fin.user1.pwd"));
		StepReport.info("Login successful");
	}

	public void login(String userId,String password) {
		StepReport.info("Start Login");
		LoginPage loginPage = PageFactory.getPage(LoginPage.class);
		loginPage.get(FrameworkContext.getInstance().getTestConfigParams().getBaseUrl());
		homePage = loginPage.login(userId,password);
		StepReport.info("Login successful");
	}

	public void loginToEM(String userId,String password) {
		StepReport.info("Start Login");
		LoginPage loginPage = PageFactory.getPage(LoginPage.class);
		loginPage.get(emURL);
		emHomePage = loginPage.loginToEM(userId,password);
		StepReport.info("Login successful");
	}

	public void logout() {
		StepReport.info("start logout");
		homePage.signout();
		StepReport.info("Logout successful");
	}

	@BeforeMethod
	public final synchronized void beforeMethod(Method testMethod) throws Exception {
		WiseContext.setDriver(getWebDriver());
	}

	@AfterMethod
	public final synchronized void afterMethod(ITestResult result) throws Exception {
		FrameworkContext.getInstance().getWebDriver().quit();
		FrameworkContext.getInstance().removeWebDriver();
		DriverUtil.sleep(2000L);
	}
}
