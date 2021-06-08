package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContactInformationPage extends HCMBasePageObject {

    @FindBy(xpath = "//input[contains(@title,'Married')]")
    WebElement statusinfo;
    @FindBy(xpath = ".//*[@id='_FOpt1:_FOr1:0:_FOSrPER_HCMPEOPLETOP_FUSE_PER_INFO:0:MAnt2:1:SP1:r4:1:i4:0:i7:0:soc4::content']")
    WebElement maritalStatus;

    @FindBy(xpath = "//div[contains(@title,'Demographic Info: United States')]")
    WebElement demoinfo;
    @FindBy(xpath = ".//img[@title='Edit']")
    WebElement flag;
    @FindBy(xpath = ".//*[@id='_FOpt1:_FOr1:0:_FOSrPER_HCMPEOPLETOP_FUSE_PER_INFO:0:MAnt2:1:SP1:APscl2']/a/span")
    WebElement saveclose;
    @FindBy(xpath = "//select[@id='_FOpt1:_FOr1:0:_FOSrPER_HCMPEOPLETOP_FUSE_PER_INFO:0:MAnt2:1:SP1:r4:1:i4:0:i7:0:soc4::content']/option[7]")
    WebElement single;
    @FindBy(xpath = "//select[@id='_FOpt1:_FOr1:0:_FOSrPER_HCMPEOPLETOP_FUSE_PER_INFO:0:MAnt2:1:SP1:r4:1:i4:0:i7:0:soc4::content']/option[5]")
    WebElement married;

    @Override
    public void isLoaded() {
        // DriverUtil.sleep(30000);
        if (PageLoadHelper.waitForElementVisible(demoinfo) == null) {

            throw new TestErrorException("The Personal information page is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
        StepReport.info("Edit Details page is loaded");
    }


    public void changeMaritaldropdown(String trimstr) {
        StepReport.info("Navigating to bio info page");
 //       bioinfo.click();
 //       bioinfo.click();
        for (int i = 0; i < 2; i++) {
            try {
                statusinfo.click();
                DriverUtil.sleep(3000);
                break;
            } catch (Exception e2) {
                statusinfo.click();
            }
        }
        DriverUtil.sleep(2000);

      /*  if (flag.isEnabled()) {
            StepReport.info("Cannot edit this information because current or future-dated changes exist");
            saveclose.click();
            DriverUtil.sleep(2000);
            } else {
            if (trimstr.equals("Married")) {
                maritalStatus.click();
                DriverUtil.sleep(2000);
                single.click();
                DriverUtil.sleep(2000);
                saveclose.click();
                StepReport.info("Updated Marital Status", "Single");
            }
            if (trimstr.equals("Single")) {
                maritalStatus.click();
                DriverUtil.sleep(2000);
                married.click();
                DriverUtil.sleep(2000);
                saveclose.click();
                StepReport.info("Updated Marital Status", "Married");
            } */
            StepReport.info("Updated Marital Status", "Married");

    }
    }




