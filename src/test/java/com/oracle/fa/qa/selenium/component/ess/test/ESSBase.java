package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.fa.qa.selenium.component.common.page.Navigator;
import com.oracle.fa.qa.selenium.component.common.test.TestBase;
import com.oracle.fa.qa.selenium.component.ess.page.ESSUIEssSRSPage;
import com.oracle.fa.qa.selenium.component.ess.page.ESSUIMonitorPage;
import com.oracle.fa.qa.selenium.component.ess.page.ESSUIProcessSearchPage;
import com.oracle.fa.qa.selenium.component.ess.page.Schedules;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;

public class ESSBase extends TestBase {
	
     protected String attachmentSampleFileName="a.txt";
//	public static String pName= FrameworkContext.getInstance().getTestConfigParams().getString("jobNameJava");
	public static String subNotes= FrameworkContext.getInstance().getTestConfigParams().getString("submissionNotes");

     protected String executeProcess(String processType){
		 login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
		 StepReport.info("Login Test successful");
		 Navigator nav =  homePage.clickNavigator();
		 ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		 DriverUtil.sleep(5000);
//		 System.out.println("This is record status " + essMonitor.getRecord());
		 ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
		 DriverUtil.sleep(7000);
		 ESSUIEssSRSPage srs = eps.searchProcess(processType);
		 if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
		 	srs.setJavaJobParam();
		 }
		 if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            srs.setPLSQLJobParam();
		 }
		 if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
		 	srs.setBIPJobParam();
		 }
         if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
             srs.setCJobParam();
         }
		 ESSUIMonitorPage essMonitor1=srs.submitJob();
		 essMonitor1.isLoaded();
		 DriverUtil.sleep(20000);
		 essMonitor.refreshProcessTable();
		 DriverUtil.sleep(5000);
		 String actualRecord = null;
		 if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
			  actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob") + ", " + essMonitor.getJavaJobStatus();
		 }
		 if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
			  actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob") + ", " + essMonitor.getPLSQLJobStatus();
		 }
		 if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
			  actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob") +", " + essMonitor.getBIPJobStatus();
		 }
         if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
             actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("CJob") +", " + essMonitor.getCJobStatus();
         }
		 System.out.println("This is actual value : " + actualRecord);
		 return actualRecord;
	 }

    protected String executeJobSet(String jobSetName){
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
        Navigator nav =  homePage.clickNavigator();
        ESSUIMonitorPage essMonitor = nav.clickESSConsole();
        DriverUtil.sleep(5000);
        ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
        DriverUtil.sleep(7000);
        ESSUIEssSRSPage srs = eps.searchJobSet(jobSetName);

        ESSUIMonitorPage essMonitor1=srs.submitJobSet();
        essMonitor1.isLoaded();
        DriverUtil.sleep(20000);
        essMonitor.refreshProcessTable();
        DriverUtil.sleep(5000);
        String actualRecord = null;
        if(jobSetName.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JobSet1"))){
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("JobSet1") + ", " + essMonitor.getJobSetStatus();
        }
        System.out.println("This is actual value : " + actualRecord);
        return actualRecord;
    }

	protected void executeJavaJobs(String javaJob){
		String expectedRecord=javaJob+", Succeeded";
		String actualRecord = executeProcess(javaJob);
		StepReport.assertEquals("Executing Java Jobs", actualRecord, expectedRecord );
     }

	protected void executePLSSQLJobs(String sqlJob){
		String expectedRecord=sqlJob+", "+"Succeeded";;
		String actualRecord = executeProcess(sqlJob);
		StepReport.assertEquals(" Executing SQL Jobs ", actualRecord, expectedRecord );
	}

	protected void executeBIPJobs(String bipJob){
		String expectedRecord=bipJob+", "+"Succeeded";
		String actualRecord = executeProcess(bipJob);
		StepReport.assertEquals("Executing BIP Jobs ", actualRecord, expectedRecord );
	}

    protected void executeCJobs(String cJob){
        String expectedRecord=cJob+", "+"Succeeded";
        String actualRecord = executeProcess(cJob);
        StepReport.assertEquals("Executing C Jobs ", actualRecord, expectedRecord );
    }

	protected void searchByProcessId(String processType) {
//		String actualRecord = executeProcess(processJob);
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
		Navigator nav =  homePage.clickNavigator();
		ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		DriverUtil.sleep(5000);
		ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
		DriverUtil.sleep(5000);
		ESSUIEssSRSPage srs = eps.searchProcess(processType);
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
			srs.setJavaJobParam();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
			srs.setPLSQLJobParam();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
			srs.setBIPJobParam();
		}
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            srs.setCJobParam();
        }
		ESSUIMonitorPage essMonitor1=essMonitor=srs.submitJob();
		essMonitor1.isLoaded();
		essMonitor1.searchByPID(ESSUIEssSRSPage.processIdFromConfirmationPopUp);
//		String actualRecord= "Import Blanket Agreements, "+ essMonitor1.getRecord();
        String expectedRecord=null;
        String actualRecord = null;
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            expectedRecord="Import Blanket Agreements, " + ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord="Import Blanket Agreements, " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            expectedRecord="Create Mass Additions, "+ ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord="Create Mass Additions, " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            expectedRecord="Create Mass Additions Report, " + ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord="Create Mass Additions Report, " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            expectedRecord="Create Mass Additions Report, " + ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord="Create Mass Additions Report, " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
		StepReport.assertEquals("searchByProcessId verification", expectedRecord, actualRecord );
	}

    protected void searchByProcessName(String processType) {
//		String actualRecord = executeProcess(processJob);
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
        Navigator nav =  homePage.clickNavigator();
        ESSUIMonitorPage essMonitor = nav.clickESSConsole();
        DriverUtil.sleep(5000);
        ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
        DriverUtil.sleep(5000);
        ESSUIEssSRSPage srs = eps.searchProcess(processType);
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            srs.setJavaJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            srs.setPLSQLJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            srs.setBIPJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            srs.setCJobParam();
        }
        ESSUIMonitorPage essMonitor1=essMonitor=srs.submitJob();
        essMonitor1.isLoaded();
        essMonitor1.searchByName(processType);
//		String actualRecord= "Import Blanket Agreements, "+ essMonitor1.getRecord();
        String expectedRecord=null;
        String actualRecord = null;
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            expectedRecord=FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob")+ ", " + ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob")+ ", " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            expectedRecord=FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob")+ ", "+ ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob")+", " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            expectedRecord=FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob")+", " + ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob")+", " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            expectedRecord=FrameworkContext.getInstance().getTestConfigParams().getString("CJob")+", " + ESSUIEssSRSPage.processIdFromConfirmationPopUp;
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("CJob")+", " + essMonitor.getPID(processType);
            StepReport.info("expected : " + expectedRecord + " and " + "actual : " + actualRecord);
        }
        StepReport.assertEquals("searchByProcessId verification", expectedRecord, actualRecord );
    }


    protected void searchByProcessName1(String processName){
		String expectedRecord=processName+", "+"Succeeded";;
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
		Navigator nav =  homePage.clickNavigator();
		ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		DriverUtil.sleep(5000);
		ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
		DriverUtil.sleep(5000);
		ESSUIEssSRSPage srs = eps.searchProcess(processName);
		srs.submitJob();
		essMonitor.isLoaded();
		essMonitor.searchByName(processName);
//		String actualRecord= essMonitor.getRecordName()+", "+ essMonitor.getRecordStatus();
//		StepReport.assertEquals("searchByProcessName verification",actualRecord, expectedRecord );
	}

	protected void searchBySubmissionTime(){
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
		Navigator nav =  homePage.clickNavigator();
		ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		DriverUtil.sleep(5000);
//		ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
//		DriverUtil.sleep(5000);
//		ESSUIEssSRSPage srs = eps.searchProcess();
//		srs.submitJob();
//		essMonitor.isLoaded();
		essMonitor.searchBySubmissionTime();
		System.out.print("searchBySubmissionTime : " + ESSUIMonitorPage.actualSubNotes);
	}

	protected void searchBySubmissionNotes(String processName){
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
		Navigator nav =  homePage.clickNavigator();
		ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		DriverUtil.sleep(5000);
		ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
		DriverUtil.sleep(5000);
		ESSUIEssSRSPage srs = eps.searchProcess(processName);
		srs.submitJob();
		essMonitor.isLoaded();
		essMonitor.searchBySubmissionNotes(subNotes);
		StepReport.assertEquals("Submission Notes verification", subNotes, ESSUIMonitorPage.actualSubNotes );
		System.out.print("searchBySubmissionNotes : " + ESSUIMonitorPage.actualSubNotes);
	}

	protected void searchByStatus(String processName){
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
		Navigator nav =  homePage.clickNavigator();
		ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		DriverUtil.sleep(5000);
		ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
		DriverUtil.sleep(5000);
		ESSUIEssSRSPage srs = eps.searchProcess(processName);
		srs.submitJob();
		essMonitor.isLoaded();
		essMonitor.searchBySubmissionNotes(subNotes);
		StepReport.assertEquals("searchByStatus verification", subNotes, ESSUIMonitorPage.actualSubNotes );
		System.out.print("searchByStatus : " + ESSUIMonitorPage.actualSubNotes);
	}

	protected void cancelProcess(String processType){
		login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
		StepReport.info("Login Test successful");
		Navigator nav =  homePage.clickNavigator();
		ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		DriverUtil.sleep(10000);
		ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
		DriverUtil.sleep(5000);
		ESSUIEssSRSPage srs = eps.searchProcess(processType);
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
			srs.setJavaJobParam();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
			srs.setPLSQLJobParam();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
			srs.setBIPJobParam();
		}
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            srs.setCJobParam();
        }
		essMonitor=srs.submitJobwithOnceSchedule();
        DriverUtil.sleep(4000);
		essMonitor.refreshProcessTable();
        DriverUtil.sleep(5000);

		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
		    StepReport.info("Inside Java Job1 for cancellation");
//            DriverUtil.sleep(7000);
			essMonitor.cancelJavaProcess();
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(5000);
            essMonitor.reSelectJavaProcess();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            StepReport.info("Inside PLSQL Job for cancellation");
//            DriverUtil.sleep(7000);
			essMonitor.cancelPLSQLProcess();
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(5000);
            essMonitor.reSelectPLSQLProcess();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            StepReport.info("Inside BIP Job for cancellation");
//            DriverUtil.sleep(7000);
			essMonitor.cancelBIPProcess();
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(5000);
            essMonitor.reSelectBIPProcess();
		}
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            StepReport.info("Inside C Job for cancellation");
//            DriverUtil.sleep(7000);
            essMonitor.cancelCProcess();
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(5000);
            essMonitor.reSelectCProcess();
        }
		String status= essMonitor.getCancelJobStatus(processType);
		StepReport.assertEquals("cancelProcess verification", status, "Canceled" );
	}

	protected void putOnHoldProcess(String processType){
		login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
		StepReport.info("Login Test successful");
		Navigator nav =  homePage.clickNavigator();
		ESSUIMonitorPage essMonitor = nav.clickESSConsole();
		DriverUtil.sleep(5000);
//		 System.out.println("This is record status " + essMonitor.getRecord());
		ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
		DriverUtil.sleep(5000);
		ESSUIEssSRSPage srs = eps.searchProcess(processType);
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
			srs.setJavaJobParam();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
			srs.setPLSQLJobParam();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
			srs.setBIPJobParam();
		}
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            srs.setCJobParam();
        }
		essMonitor=srs.submitJobwithOnceSchedule();
		DriverUtil.sleep(4000);
		essMonitor.refreshProcessTable();

		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
			StepReport.info("Inside Java Job1 for cancellation");
			DriverUtil.sleep(4000);
			essMonitor.putOnHoldProcess(processType);
			DriverUtil.sleep(15000);
			essMonitor.refreshProcessTable();
			DriverUtil.sleep(3000);
			essMonitor.reSelectJavaProcess();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
			StepReport.info("Inside PLSQL Job for cancellation");
			DriverUtil.sleep(5000);
			essMonitor.putOnHoldProcess(processType);
			DriverUtil.sleep(15000);
			essMonitor.refreshProcessTable();
			DriverUtil.sleep(3000);
			essMonitor.reSelectPLSQLProcess();
		}
		if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
			StepReport.info("Inside BIP Job for cancellation");
			DriverUtil.sleep(5000);
			essMonitor.putOnHoldProcess(processType);
			DriverUtil.sleep(15000);
			essMonitor.refreshProcessTable();
			DriverUtil.sleep(3000);
			essMonitor.reSelectBIPProcess();
		}
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            StepReport.info("Inside C Job for cancellation");
            DriverUtil.sleep(5000);
            essMonitor.putOnHoldProcess(processType);
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(3000);
            essMonitor.reSelectCProcess();
        }
		String status= essMonitor.getPutOnHoldJobStatus(processType);
		StepReport.assertEquals("Put On Hold verification", status.trim(), "Hold" );
	}

    protected void releaseProcess(String processType){
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
        Navigator nav =  homePage.clickNavigator();
        ESSUIMonitorPage essMonitor = nav.clickESSConsole();
        DriverUtil.sleep(5000);
//		 System.out.println("This is record status " + essMonitor.getRecord());
        ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
        DriverUtil.sleep(5000);
        ESSUIEssSRSPage srs = eps.searchProcess(processType);
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            srs.setJavaJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            srs.setPLSQLJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            srs.setBIPJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            srs.setCJobParam();
        }
        essMonitor=srs.submitJobwithOnceSchedule();
        DriverUtil.sleep(4000);
        essMonitor.refreshProcessTable();

        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            StepReport.info("Inside Java Job for cancellation");
            DriverUtil.sleep(4000);
            essMonitor.putOnHoldProcess(processType);
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(3000);
            essMonitor.reSelectJavaProcess();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            StepReport.info("Inside PLSQL Job for cancellation");
            DriverUtil.sleep(5000);
            essMonitor.putOnHoldProcess(processType);
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(3000);
            essMonitor.reSelectPLSQLProcess();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            StepReport.info("Inside BIP Job for cancellation");
            DriverUtil.sleep(5000);
            essMonitor.putOnHoldProcess(processType);
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(3000);
            essMonitor.reSelectBIPProcess();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            StepReport.info("Inside C Job for cancellation");
            DriverUtil.sleep(5000);
            essMonitor.putOnHoldProcess(processType);
            DriverUtil.sleep(15000);
            essMonitor.refreshProcessTable();
            DriverUtil.sleep(3000);
            essMonitor.reSelectCProcess();
        }
        String status= essMonitor.getPutOnHoldJobStatus(processType);
        if (status.equalsIgnoreCase("Hold")){
            StepReport.info("Process released ");
            essMonitor.releaseProcesses(processType);
        }
        DriverUtil.sleep(3000);
        String newStatus= essMonitor.getPutOnHoldJobStatus(processType);
        StepReport.assertEquals("Release process verification", newStatus.trim(), "Wait" );
    }

    protected String reSubmitProcess(String processType){
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
        Navigator nav =  homePage.clickNavigator();
        ESSUIMonitorPage essMonitor = nav.clickESSConsole();
        DriverUtil.sleep(5000);
//		 System.out.println("This is record status " + essMonitor.getRecord());
        ESSUIProcessSearchPage eps= essMonitor.submitNewProcess();
        DriverUtil.sleep(7000);
        ESSUIEssSRSPage srs = eps.searchProcess(processType);
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
            srs.setJavaJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            srs.setPLSQLJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            srs.setBIPJobParam();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            srs.setCJobParam();
        }
        ESSUIMonitorPage essMonitor1=srs.submitJob();
        essMonitor1.isLoaded();
        DriverUtil.sleep(20000);
        essMonitor.refreshProcessTable();
        DriverUtil.sleep(5000);
//        essMonitor.reSelectJavaProcess();
        DriverUtil.sleep(5000);

        String actualRecord = null;
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"))){
                essMonitor.reSubmitJavaProcess();
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob") + ", " + essMonitor.getJavaJobStatus();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"))){
            essMonitor.reSubmitPLSQLProcess();
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob") + ", " + essMonitor.getPLSQLJobStatus();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"))){
            essMonitor.reSubmitBIPProcess();
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob") +", " + essMonitor.getBIPJobStatus();
        }
        if(processType.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"))){
            essMonitor.reSubmitCProcess();
            actualRecord=FrameworkContext.getInstance().getTestConfigParams().getString("CJob") +", " + essMonitor.getCJobStatus();
        }

        System.out.println("This is actual value : " + actualRecord);
        return actualRecord;
    }

    protected void executeJobsWithSchedule(String frequency) {
        login(FrameworkContext.getInstance().getTestConfigParams().getString("essUser"), FrameworkContext.getInstance().getTestConfigParams().getString("essUserPwd"));
        StepReport.info("Login Test successful");
        Navigator nav = homePage.clickNavigator();
        ESSUIMonitorPage essMonitor = nav.clickESSConsole();
        DriverUtil.sleep(10000);
        ESSUIProcessSearchPage eps = essMonitor.submitNewProcess();
        DriverUtil.sleep(5000);
        ESSUIEssSRSPage srs = eps.searchProcess(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
        Schedules sch = new Schedules();
//        srs.addSchedule();
        if (frequency.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency1"))) {
            StepReport.info("Inside Java Job1 for Adding Once Schedule");
            srs.addOnceSchedule1();
        }
        if (frequency.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency2"))) {
            StepReport.info("Inside Java Job1 for Adding HM Schedule");
            srs.addHMSchedule();
        }
        if (frequency.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency3"))) {
            StepReport.info("Inside Java Job1 for Adding Daily Schedule");
            srs.addDailySchedule();
        }
        if (frequency.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency4"))) {
            StepReport.info("Inside Java Job1 for Adding Weekly Schedule");
            srs.addWeeklySchedule();
        }
        if (frequency.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency5"))) {
            StepReport.info("Inside Java Job1 for Adding Monthly Schedule");
            srs.addMonthlySchedule();
        }
        if (frequency.equalsIgnoreCase(FrameworkContext.getInstance().getTestConfigParams().getString("Frequency6"))) {
            StepReport.info("Inside Java Job1 for Adding Yearly Schedule");
            srs.addYearlySchedule();
        }
        srs.submitJob1();
        String actual = essMonitor.getfirstScheduledTime();
        StepReport.info("First scheduled time from table : " + actual);
        String expected = FrameworkContext.getInstance().getTestConfigParams().getString("startDate") + " UTC";
        StepReport.assertEquals("cancelProcess verification", actual, expected);
    }
}
