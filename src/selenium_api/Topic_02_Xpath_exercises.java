package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_exercises {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
		
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_verifyUrlAndTitle() {

//		Test Script 01: Verify URL and title
//		* Step 01 - Truy cập vào trang: http://live.demoguru99.com
//		* Step 02 - Kiểm tra title của page là: "Home page"
//		* Step 03 - Click vào link "My Account" để tới trang đăng nhập
//		* Step 04 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
//		* Step 05 - Back lại trang đăng nhập
//		* Step 06 - Kiểm tra url của page đăng nhập là: http://live.demoguru99.com/index.php/customer/account/login/
//		* Step 07 - Forward tới trang tạo tài khoản
//		* Step 08 - Kiểm tra url của page tạo tài khoản là: http://live.demoguru99.com/index.php/customer/account/create/

		driver.get("http://live.demoguru99.com");

		String homePageTitle;
		homePageTitle = driver.getTitle();

		Assert.assertEquals(homePageTitle, "Home page");

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();

		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.navigate().back();

		String currentURL = driver.getCurrentUrl();

		Assert.assertEquals(currentURL, "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.navigate().forward();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_loginEmpty() {
//		Test Script 02: Login empty
//		* Step 01 - Truy cập vào trang: http://live.demoguru99.com/
//		* Step 02 - Click vào link "My Account" để tới trang đăng nhập
//		* Step 03 - Để trống Username/ Password
//		* Step 04 - Click Login button
//		* Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.

		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.cssSelector("input[id='email']")).clear();
		driver.findElement(By.id("pass")).clear();

		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String errorMsg = "This is a required field.";

		String msgUsr = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String msgPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();

		Assert.assertEquals(msgUsr, errorMsg);
		Assert.assertEquals(msgPass, errorMsg);

	}

	@Test
	public void TC_03_loginWithInvalidEmail() {
//		Test Script 03: Login with Email invalid
//		* Step 01 - Truy cập vào trang: http://live.demoguru99.com/
//		* Step 02 - Click vào link "My Account" để tới trang đăng nhập
//		* Step 03 - Nhập email invalid: 123434234@12312.12312
//		* Step 04 - Click Login button
//		* Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
		
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
