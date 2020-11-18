package selenium_api;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

}
