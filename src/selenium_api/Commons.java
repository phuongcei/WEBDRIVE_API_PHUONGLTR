package selenium_api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

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

	// Take a screen shot in Selenium 2.x - getScreenshotAs method
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

	// Screenshot for the screen size page
	public static void captureScreenSizePageWithAShotAPI(WebDriver driver, String fileName) {
		// Create an Ashot object and call takeScreenshot() method
		// if you just want the screenshot for the screen size page.
		Screenshot screenshot = new AShot().takeScreenshot(driver);

		// Get image from screenshot and write it to a file
		File destFile = new File(fileName);
		try {
			ImageIO.write(screenshot.getImage(), "png", destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Screenshot for full page which have page size larger than screen size
	public static void captureFullpageWithAShotAPI(WebDriver driver, String fileName) {

		// Call shootingStrategy() method before calling takeScreenshot() method to set
		// up the policy
		// 1000 is scrolled out time in milliseconds, so for taking a screenshot, the
		// program will scroll for each 1000 msec.
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);

		File destFile = new File(fileName);

		try {
			ImageIO.write(screenshot.getImage(), "png", destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Screenshot of a particular element of the page
	// **NOTE: NOT WORK AS EXPECTATION
	public static void captureScreenOfSpecificElement(WebDriver driver, WebElement element, String elementName) {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver, element);

		File destFile = new File(elementName);

		try {
			ImageIO.write(screenshot.getImage(), "jpg", destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Compare image by AShot API
	// **NOTE: Not work as expected
	public static void compareImageByAShotAPI(WebDriver driver, WebElement element, String sourceFile) throws IOException {

		Screenshot screenshot = new AShot().takeScreenshot(driver, element);
		BufferedImage expectedImage = ImageIO.read(new File(sourceFile));

		BufferedImage actualImage = screenshot.getImage();

		// Create ImageDiffer object and call method makeDiff()

		ImageDiffer imageDiff = new ImageDiffer();
		ImageDiff diff = imageDiff.makeDiff(actualImage, expectedImage);

		if (diff.hasDiff() == false) {
			System.out.println("Images are same");

		} else {
			System.out.println("Images are different");
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
