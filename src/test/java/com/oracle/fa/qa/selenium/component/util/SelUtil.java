package com.oracle.fa.qa.selenium.component.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.driver.util.DriverUtil;
import com.oracle.mcs.qa.selenium.framework.util.Timeout;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Enumeration;
import java.util.Properties;

public class SelUtil {
	private static final Logger logger = Logger.getLogger(DriverUtil.class);

	public static void waitUntilPageClosed() {
		int counter=1;
		boolean isBrowserAlive=true;
		while((counter<24) && (isBrowserAlive)) {
			 try {
				 String title=FrameworkContext.getInstance().getWebDriver().getTitle();
				 System.out.println("Page is not closed."+title);
				 isBrowserAlive=true;
			    } catch (Exception ex) {
			    	System.out.println("Page is closed.");
			    	isBrowserAlive=false;
			    }
		counter++;
		DriverUtil.sleep(5000L);
		}
	}
	
	public static String getCurrentLocalDateTimeStamp() {
	    return LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));
	}

	public static String getSystemDateTime(int months) {

		return LocalDateTime.now().plusMonths(months)
				.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
	}
	public static String getSystemDateTime() {

		return LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
	}

	public static boolean isDisplayed(WebElement element, long timeoutInSeconds) {
		WebElement returnedElement = DriverUtil.waitFor(ExpectedConditions.visibilityOf(element),
					FrameworkContext.getInstance().getWebDriver(),
					timeoutInSeconds);
		if(returnedElement == null) return false;
		else return true;

	}

	public static <T> T waitFor(ExpectedCondition<T> expectedCondition, WebDriver driver, long timeOutInSeconds) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			logger.info("Wait for " + expectedCondition.toString());
			T returnValue = new FluentWait<WebDriver>(driver)
					.withTimeout((long)timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS)
					.ignoring(WebDriverException.class)
					.until(expectedCondition);
			return returnValue;
		} catch (TimeoutException e) {
			throw new TimeoutException("Timed Out");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(Timeout.implicitValue().toLong(),
							TimeUnit.SECONDS);
			stopwatch.stop();
			logger.debug("Time Taken for waitFor method for Expected Condition is:"
					+ stopwatch.elapsed(TimeUnit.SECONDS));
		}
	}

	public static void UpdatePropertiesFile(){
			try {
				System.out.println("Current directory is:"+System.getProperty("user.dir"));
				File file = new File("src/../../../../testconfig.properties");
				FileInputStream fileInput = new FileInputStream(file);
				Properties properties = new Properties();
				properties.load(fileInput);
				fileInput.close();

				Enumeration enuKeys = properties.keys();
				while (enuKeys.hasMoreElements()) {
					String key = (String) enuKeys.nextElement();
					String value = properties.getProperty(key);
					System.out.println(key + ": " + value);
					WritePropertiesFile(key,value);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static void WritePropertiesFile(String key, String data)
	{
		try {
			File file = new File("src/test/resources/config/testconfig.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			if(properties.containsKey(key)) {
				properties.setProperty(key, data);
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "updated properties");
			fileOut.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isCustomThemeEnabled() {
		if (FrameworkContext.getInstance().getTestConfigParams().getString("test.fa.isCustomThemeEnabled").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

}
