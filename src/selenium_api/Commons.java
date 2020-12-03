package selenium_api;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Commons {

	// Generate a random email
	public static String randomEmail() {
		String randomMail;

		String prefix = "testautomation_";
		int max = 10000;
		int min = 1000;

		int subfix;

		Random rd = new Random();
		subfix = rd.nextInt(max - min) + min;

		randomMail = prefix + subfix + "@gmail.com";

		System.out.println("Random email: " + randomMail);
		return randomMail;
	}

	// Remove an specific attribute of an element by Javascript executor
	public static void removeAttributeElement(WebDriver driver, By by, String attributeName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].removeAttribute('" + attributeName + "')", driver.findElement(by));

		// Reference:
		// https://stackoverflow.com/questions/53376933/how-to-remove-an-element-attribute-using-selenium-and-c

	}

	// Click an element by Javascript executor
	public static void clickElementByJavascript(WebDriver driver, WebElement element) {
		// Không cần quan tâm element có visible hay không?
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// Take a screen shot
	public static void takeSnapShot(WebDriver driver, String fileName) {
		// Reference: https://www.guru99.com/take-screenshot-selenium-webdriver.html

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file
		File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileName);

		try {
			// Copy file to Desired Location
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get system time in specific format
	public static String getTimeStampValue() throws IOException {
		// Output format example: Wed Dec 02 22-21-39 ICT 2020
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		String timeStamp = time.toString();
		System.out.println(timeStamp);
		String systime = timeStamp.replace(":", "-");
		System.out.println(systime);
		return systime;
	}
}
