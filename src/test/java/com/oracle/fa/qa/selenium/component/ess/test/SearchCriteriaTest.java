package com.oracle.fa.qa.selenium.component.ess.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import org.testng.annotations.Test;

public class SearchCriteriaTest extends ESSBase {
     String processName="";
     String processId="";


//    @FindBy(xpath="//span[@id='_FOpt1:_FOr1:0:_FOSritemNode_tools_scheduled_processes_fuse_plus:0:_FOTsr1:0:pt1:panel:result:0:ot1']")
//    WebElement row;
//    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Execute process")
    public void testExecuteProcess() {
        //Login as User1
       executeJavaJobs(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

    @Test //(dependsOnMethods="testExecuteProcess")
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Search process by ID")
    public void testSearchById_Java(){
        searchByProcessId(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

    @Test //(dependsOnMethods="testExecuteProcess")
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Search process by ID")
    public void testSearchById_PLSQL(){
        searchByProcessId(FrameworkContext.getInstance().getTestConfigParams().getString("PLSQLJob"));
    }

    @Test //(dependsOnMethods="testExecuteProcess")
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Search process by ID")
    public void testSearchById_BIP(){
        searchByProcessId(FrameworkContext.getInstance().getTestConfigParams().getString("BIPJob"));
    }

    @Test //(dependsOnMethods="testExecuteProcess")
    @TestAuthor(createdBy = "kishsing", createdOn = "06/19/2019", version = "1.0")
    @TestDesc(desc = "Search process by ID")
    public void testSearchById_C(){
        searchByProcessId(FrameworkContext.getInstance().getTestConfigParams().getString("CJob"));
    }
//    @Test  //(dependsOnMethods="testExecuteProcess")
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Search process by Name")
    public void testSearchByName(){
        searchByProcessName(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

//    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Search process by Submission notes")
    public void testSearchBySubmissionTime(){

        searchBySubmissionTime();
    }

//    @Test
    @TestAuthor(createdBy = "kishsing", createdOn = "03/19/2019", version = "1.0")
    @TestDesc(desc = "Search process by Submission notes")
    public void testSearchBySubmissionNotes(){

        searchBySubmissionNotes(FrameworkContext.getInstance().getTestConfigParams().getString("JavaJob"));
    }

}
