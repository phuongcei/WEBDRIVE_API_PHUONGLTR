package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import junit.framework.Assert;

public class Topic_01_CheckEnvironment {
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver_win.exe");
		driver = new ChromeDriver();
		
//		driver = new FirefoxDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		
	}

	@Test
	public void TC_01_CheckEnvironment() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Google");
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
