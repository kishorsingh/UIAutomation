package com.oracle.fa.qa.selenium.component.common.page;

import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.driver.util.PageLoadHelper;
import com.oracle.mcs.qa.selenium.framework.exception.TestErrorException;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import com.oracle.mcs.qa.selenium.framework.object.PageFactory;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;

/**
 * Created by bpadhy on 1/17/18.
 */
public class ZimbraAttachments extends BasePageObject<ZimbraAttachments> {



    @FindBy(xpath = "//*[text()='Attach File(s)']")
    WebElement attachmentsTitle;

    @FindBy(xpath = "(//*[text()='Attach:']/following::input)[1]")
    WebElement browse;

    @FindBy(xpath = "//*[text()='Attach']")
    WebElement attachButton;




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
        if (PageLoadHelper.waitForElementVisible(attachmentsTitle) == null) {
            throw new TestErrorException("The attachmentsTitle field is not visible after " +
                    Timeout.pageLoadValue().seconds() + " seconds.");
        }
    }

    public void browseFile () {
        StepReport.info("Input Attachments File Path");
        DriverUtil.sleep(2000);
        PageLoadHelper.waitForElementVisible(browse);
        if (FrameworkContext.getInstance().getTestConfigParams().isOnHub()){
            browse.sendKeys("/net/slc05min/scratch/miecs/a.txt");
            StepReport.info("Attachment: /net/slc05min/scratch/miecs/a.txt");
        }else{
            browse.sendKeys("C:\\auto_phase3\\FA_UI_AUTOMATION\\test\\src\\test\\resources\\config\\testconfig.properties");
            StepReport.info("Attachment: C:\\auto_phase3\\FA_UI_AUTOMATION\\test\\src\\test\\resources\\config\\testconfig.properties");
        }

        DriverUtil.sleep(2000);
    }

    public void clickAttach () {
        StepReport.info("Click Attach button");
        DriverUtil.sleep(2000);
        attachButton.click();
        DriverUtil.sleep(2000);
    }


}