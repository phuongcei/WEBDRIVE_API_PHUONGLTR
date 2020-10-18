package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement {
	WebDriver driver;

	By MyAccountLinkFooter = By.xpath("//div[@class='footer']//a[@title='My Account']");

	@BeforeClass
	public void beforeClass() {

//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver_mac_chrome86");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC01_verifyUrl() {
		driver.get("http://live.demoguru99.com");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC02_verifyTitle() {
		gotoMyAccountLink();
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	
	@Test
	public void TC03_navigateFunction() {
		gotoMyAccountLink();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.navigate().back();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	
	@Test
	public void TC04_getPageSourceCode() {
		gotoMyAccountLink();
		
		String loginPageSource = driver.getPageSource();
		
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageSource = driver.getPageSource();
		
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	public void gotoMyAccountLink() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
	}
	
}
