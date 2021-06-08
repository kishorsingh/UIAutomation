package com.oracle.fa.qa.selenium.component.hcm.page;

import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.object.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HCMBasePageObject extends BasePageObject<HCMBasePageObject> {
    public static WebDriver driver = FrameworkContext.getInstance().getWebDriver();
    public static final String employeeToBePromoted="Alok Shay GBI 3T";
    public static final String employeeToChangeLocation="Abhilash Chacko Pro1";
    public static final String employeeToBeTerminated="AUTO_GHR_EMP2";
    public static Actions ac = new Actions(driver);
}
