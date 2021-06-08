package com.oracle.fa.qa.selenium.component.bpm.test;

import com.oracle.mcs.qa.selenium.framework.annotation.TestAuthor;
import com.oracle.mcs.qa.selenium.framework.annotation.TestDesc;
import org.testng.annotations.Test;

@Test(groups = { "browser_certification","bug27123159" })
public class TaskSequenceCacheEmailNotifications extends BPMBase {
    public static String TaskNumber="";
    public static String compositeName="AMXTestWith3LevelSDO [1.0]";
    public static String inputPayload="51";
    public static String soaDomain="Farm_WLS_SOAWC";
    public static String humanTask="Humantask1";


    /*@Test
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testSendEmailNotifications")
    public void testSendEmailNotifications() {
        verifyTestSendEmailNotifications(soaDomain);
    }*/


    @Test//(dependsOnMethods = "testSendEmailNotifications")
    @TestAuthor(createdBy = "vevinnak", createdOn = "08/21/2018", version = "1.0")
    @TestDesc(desc = "testcreateTaskForEmailNotifications")
    public void testcreateTaskForTaskSeqCacheEmailNotifications() {
         compositeName="AMXTestWith3LevelSDO [1.0]";
         TaskNumber=createTaskForTaskSeqCacheEmailNotifications(compositeName,inputPayload,soaDomain,humanTask);

    }

}
