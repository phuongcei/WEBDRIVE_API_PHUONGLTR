package selenium_api;

import java.util.Random;
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

	By email = By.xpath("//input[@id='mail']");

	By password = By.xpath("//input[@id='password']");

	By radioUnder18 = By.xpath("//input[@id='under_18']");

	By radioOver18 = By.xpath("//input[@id='over_18']");

	By textAreaEdu = By.xpath("//textarea[@id='edu']");

	By MyAccountLinkFooter = By.xpath("//div[@class='footer']//a[@title='My Account']");

	@BeforeClass
	public void beforeClass() {

//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver_mac_chrome86");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}


	public void TC01_verifyUrl() {
		driver.get("http://live.demoguru99.com");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	
	public void TC02_verifyTitle() {
		gotoMyAccountLink();

		Assert.assertEquals(driver.getTitle(), "Customer Login");

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}


	public void TC03_navigateFunction() {
		gotoMyAccountLink();

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.navigate().back();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.navigate().forward();

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	
	public void TC04_getPageSourceCode() {
		gotoMyAccountLink();

		String loginPageSource = driver.getPageSource();

		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		String registerPageSource = driver.getPageSource();

		Assert.assertTrue(registerPageSource.contains("Create an Account"));
	}


	public void TC05_chkDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Assert.assertTrue(isElementDisplayed(email));
		Assert.assertTrue(isElementDisplayed(radioUnder18));
		Assert.assertTrue(isElementDisplayed(textAreaEdu));

		if (isElementDisplayed(radioUnder18)) {

			driver.findElement(radioUnder18).click();
		}

	}

	
	public void TC06_chkEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Assert.assertTrue(isElementEnabled(email));

		Assert.assertFalse(isElementEnabled(password));
	}

	
	public void TC07_chkSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		By radioUnder18 = By.xpath("//input[@id='under_18']");
		By radioOver18 = By.xpath("//input[@id='over_18']");

		By javaLanguage = By.xpath("//input[@id='java']");

		driver.findElement(radioUnder18).click();
		driver.findElement(javaLanguage).click();

		Assert.assertTrue(isElementSelected(radioUnder18));
		Assert.assertTrue(isElementSelected(javaLanguage));

		driver.findElement(radioOver18).click();
		driver.findElement(javaLanguage).click();

		Assert.assertFalse(isElementSelected(radioUnder18));
		Assert.assertFalse(isElementSelected(javaLanguage));

	}

	@Test
	public void TC08_registerMailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		String randomEmail = randomEmail();
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys( randomEmail + "@mailinator.com");
		
		driver.findElement(By.xpath("//input[@id='new_username']")).clear();
		driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys(randomEmail);
		
		By password = By.xpath("//input[@id='new_password']");
		By lowerCase = By.xpath("//li[@class='lowercase-char']");
		By upperCase = By.xpath("//li[@class='uppercase-char']");
		By numberCase = By.xpath("//li[@class='number-char']");
		By specialCase = By.xpath("//li[@class='special-char']");
		By eightCase = By.xpath("//li[@class='8-char']");
		
		By signupButton = By.xpath("//button[@id='create-account']");
		
		By checkBox = By.xpath("//input[@id='marketing_newsletter']");
		
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys("p");
		
		Assert.assertFalse(isElementDisplayed(lowerCase));
		Assert.assertFalse(isElementEnabled(signupButton));
		
		driver.findElement(password).sendKeys("H");
		Assert.assertFalse(isElementEnabled(upperCase));
		Assert.assertFalse(isElementEnabled(signupButton));
		
		driver.findElement(password).sendKeys("3");
		Assert.assertFalse(isElementEnabled(numberCase));
		Assert.assertFalse(isElementEnabled(signupButton));
		
		driver.findElement(password).sendKeys("@");
		Assert.assertFalse(isElementEnabled(specialCase));
		Assert.assertFalse(isElementEnabled(signupButton));
		
		driver.findElement(password).sendKeys("uon6");
		Assert.assertFalse(isElementEnabled(eightCase));
		Assert.assertFalse(isElementEnabled(signupButton));
		
		driver.findElement(checkBox).click();
		Assert.assertTrue(isElementSelected(checkBox));
		
		
		
		
		
		
		
		
		
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementDisplayed(By by) {

		if (driver.findElement(by).isDisplayed()) {
			System.out.println("Element " + by + " is displayed.");
			return true;
		} else {
			System.out.println("Element " + by + " is not displayed.");
			return false;
		}

	}

	public boolean isElementEnabled(By by) {

		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element " + by + " is enabled.");
			return true;

		} else {
			System.out.println("Element " + by + " is disabled.");
			return false;

		}

	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println("Element [ " + by + " ] is selected.");
			return true;
		} else {
			System.out.println("Element [ " + by + " ] is not selected.");
			return false;
		}
	}

	public void gotoMyAccountLink() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

	}

	public String randomEmail() {
		String randomEmail;
		int max = 1000, min = 100;
		String prefix = "automation_";

		Random rd = new Random();

		int subfix = rd.nextInt(max - min) + min;

		randomEmail = prefix + subfix; // + "@mailinator.com";

		return randomEmail;
	}

}
