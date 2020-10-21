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

public class Topic_04_TextBox_TextArea_DropList {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Firefox
//		driver = new FirefoxDriver();

		// Chrome MAC
		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver_mac_chrome86");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC01_handleTextboxTextArea() {
		driver.get("http://demo.guru99.com/v4/");

		// mngr291495
		// EjYhYsE

		String userID = "mngr291495";
		String password = "EjYhYsE";

		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);

		driver.findElement(By.xpath("// input[@name='password']")).clear();
		driver.findElement(By.xpath("// input[@name='password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		String homePageMessage = driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).getText();

		Assert.assertEquals(homePageMessage, "Welcome To Manager's Page of Guru99 Bank");

		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();

		//input[@name='name']
		
		//input[@value='m']
		
		//input[@id='dob']
		
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String randomEmail() {
		String randomMail;

		String prefix = "testautomation_";
		int max = 1000;
		int min = 800;

		int subfix;

		Random rd = new Random();
		subfix = rd.nextInt(max - min) + min;

		randomMail = prefix + subfix + "@mailinator.com";

		return randomMail;
	}

}
