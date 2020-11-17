package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Button_RadioButton_Checkbox_Alert {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chromedriver.driver", "./lib/chromedriver_mac_chrome86");

		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("disable-infobars");
		opt.addArguments("--start-maximized");
		opt.addArguments("--disable-extensions");

		driver = new ChromeDriver(opt);

//		driver.manage().window().maximize();  //-> From Chrome 70, this is an issue.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC01_handleButtonJs() {

		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//ul[@id='popup-login-tab_list']//a")).click();

		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).clear();
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0969835355");

		driver.findElement(By.xpath("//input[@id='login_password']")).clear();
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");

		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//ul[@id='popup-login-tab_list']//a")).click();

		// Remove thuoc tinh disable cua button

		jsExecutor.executeScript("document.querySelector('.fhs-btn-login').removeAttribute('disabled')");
		
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();
		
		List<WebElement> listMessages = driver.findElements(By.xpath("//div[@class='popup-login-content']//div[@class='fhs-input-alert']"));
		for(WebElement massage : listMessages) {
			Assert.assertEquals(massage.getText().trim(), "Thông tin này không thể để trống");
		}
		
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
