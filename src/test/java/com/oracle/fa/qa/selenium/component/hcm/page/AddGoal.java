package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AddGoal extends BasePageObject {

    @FindBy(xpath = "//span[contains(text(),'Career Development')]")
    WebElement careerdev;

    @FindBy(xpath = "//span[contains(text(),'Development Plan:')]")
    WebElement devplan;

    @FindBy(xpath = "//h1[contains(text(),'Add Goal')]")
    WebElement addGoal;

    @FindBy(xpath = "//span[contains(text(),'Add Goal')]")
    WebElement addgoal;

    @FindBy(xpath = "(//*[text()='Goal Name']/following::input)[1]")
    WebElement goalname;

    @FindBy(xpath = "(//*[text()='Confirmation']/following::button[text()='OK'])[1]")
    WebElement confirm;

    @FindBy(xpath = "(//*[@accesskey='m'])[1]")
    WebElement submit;

    @FindBy(xpath = "(//*[text()='Target Completion Date']/following::input)[1]")
    WebElement completiondate;



    public static String gname;
    @Override
    public void isLoaded() {
        if (PageLoadHelper.waitForElementVisible(addGoal) == null) {
            throw new TestErrorException("The Add Goal page is not loaded after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public String typeGoalName() {
        Random r = new Random();
        int n = r.nextInt(10000) + 1;
        StepReport.info("Fill in Goal Name");
        gname = "HCMGOAL" + n;
        StepReport.info("Goal Name :" + gname);
        goalname.sendKeys(gname);
        DriverUtil.sleep(3000);
        return gname;
    }

    public void typeTargetCompletionDate() {
        //#Pragathi : This part is to add tomorrows date in the goal submit request.
        Date dt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.MONTH, 1);
        dt = calendar.getTime();
        String todaysDate = new SimpleDateFormat("MM/dd/yy").format(dt);
        todaysDate.equals(dt);
        StepReport.info("Enter this date as target completion date :" + todaysDate);

        DriverUtil.sleep(2000);
        completiondate.sendKeys(todaysDate);
        DriverUtil.sleep(6000);
    }

    public void clickSubmit() {
        StepReport.info("Click Submit");
        PageLoadHelper.waitForElementVisible(submit);
        submit.click();
        DriverUtil.sleep(3000);

    }

    public void clickConfirm() {
        PageLoadHelper.waitForElementVisible(submit);
        StepReport.info("Click Confirm");
        confirm.click();
        DriverUtil.sleep(3000);

    }
}
