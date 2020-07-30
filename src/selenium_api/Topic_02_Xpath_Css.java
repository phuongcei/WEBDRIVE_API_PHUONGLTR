package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css {
	WebDriver driver;
	
	
  @BeforeClass
  public void beforeClass() {
	  	System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver_win.exe");
	  	driver = new ChromeDriver();
	  	
	  	driver.get("https://google.com");
	  	driver.manage().window().maximize();
	  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void f() {
	  driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Le Tran Phuong");
	  driver.findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']")).click();
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
