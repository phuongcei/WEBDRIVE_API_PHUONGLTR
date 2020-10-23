package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_TextBox_TextArea_DropList {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		driver = new FirefoxDriver();

		// Chrome MAC -- cannot use chrome for this case due to issue at Birthday field
//		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver_mac_chrome86");
//		driver = new ChromeDriver();
		
		// Chrome Windows:
//		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver_win_chrome86.exe");
//		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC01_handleTextboxTextArea() {
		driver.get("http://demo.guru99.com/v4/");

		String userID = "mngr291635";
		String password = "sEjyvEm";
		String emailId = randomEmail(); //"testautomation_819@gmail.com";

//		27982
		String customerID; // 35647

		String customerName = "phuongcei", customerNameNew = "Man Hoang";
		String gender = "female";
		String dob = "1989-05-03";
		String address = "59 Nguyen Chanh \n Hoa Khanh Bac";
		String city = "Da Nang", cityNew = "Ha Noi";
		String state = "Lien Chieu";
		String pinNum = "123456";
		String telNum = "0969835555";
		String passStr = "111111";

		By customerNameBy = By.xpath("//td[text()='Customer Name']/following-sibling::td/input");
		By genderBy = By.xpath("//td[text()='Gender']/following-sibling::td");
		By birthdayCreateBy = By.xpath("//td[text()='Date of Birth']/following-sibling::td/input");
		By birthdayBy = By.xpath("//td[text()='Birthdate']/following-sibling::td/input");
		By addressBy = By.xpath("//td[text()='Address']/following-sibling::td/textarea");
		By cityBy = By.xpath("//td[text()='City']/following-sibling::td/input");
		By stateBy = By.xpath("//td[text()='State']/following-sibling::td/input");
		By pinCreateBy = By.xpath("//td[text()='PIN']/following-sibling::td/input");
		By pinBy = By.xpath("//td[text()='Pin']/following-sibling::td/input");
		By telCreateBy = By.xpath("//td[text()='Mobile Number']/following-sibling::td/input");
		By telBy = By.xpath("//td[text()='Mobile No.']/following-sibling::td/input");
		By emailCreateBy = By.xpath("//td[text()='E-mail']/following-sibling::td/input");
		By emailBy = By.xpath("//td[text()='Email']/following-sibling::td/input");
		By passBy = By.xpath("//td[text()='Password']/following-sibling::td/input");
		By createNewCusBtn = By.xpath("//input[@type='submit']");

		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);

		driver.findElement(By.xpath("// input[@name='password']")).clear();
		driver.findElement(By.xpath("// input[@name='password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		String homePageMessage = driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).getText();

		Assert.assertEquals(homePageMessage, "Welcome To Manager's Page of Guru99 Bank");

		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();

		driver.findElement(customerNameBy).sendKeys(customerName);

		// Gender is a little bit of diff
		driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td/input[@value='f']")).click();

		driver.findElement(birthdayCreateBy).clear();
		driver.findElement(birthdayCreateBy).sendKeys(dob);

		driver.findElement(addressBy).sendKeys(address);

		driver.findElement(cityBy).sendKeys(city);

		driver.findElement(stateBy).sendKeys(state);

		driver.findElement(pinCreateBy).sendKeys(pinNum);

		driver.findElement(telCreateBy).sendKeys(telNum);

		driver.findElement(emailCreateBy).sendKeys(emailId);

		driver.findElement(passBy).sendKeys(passStr);

//		driver.findElement(createNewCusBtn).click();

		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(), 'Customer Registered Successfully')]")).getText(), "Customer Registered Successfully!!!");
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID: " + customerID);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
//		Assert.assertEquals(driver.findElement(genderBy).getText(), gender);
//		Assert.assertEquals(driver.findElement(birthdayBy).getText(), dob);
//		Assert.assertEquals(driver.findElement(addressBy).getText(), address);
//		Assert.assertEquals(driver.findElement(cityBy).getText(), city);
//		Assert.assertEquals(driver.findElement(stateBy).getText(), state);
//		Assert.assertEquals(driver.findElement(pinBy).getText(), pinNum);
//		Assert.assertEquals(driver.findElement(telBy).getText(), telNum);
//		Assert.assertEquals(driver.findElement(emailBy).getText(), emailId);

		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Assert.assertEquals(driver.findElement(customerNameBy).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(addressBy).getAttribute("value"), address);

//		Nhập giá trị mới cho các fields ngoại trừ field bị disable 
		if (isElementEnabled(customerNameBy)) {
			driver.findElement(customerNameBy).clear();
			driver.findElement(customerNameBy).sendKeys(customerNameNew);
		}
		if (isElementEnabled(cityBy)) {
			driver.findElement(cityBy).clear();
			driver.findElement(cityBy).sendKeys(cityNew);
		}

		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), cityNew);

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

		randomMail = prefix + subfix + "@gmail.com";

		System.out.println("Random email: " + randomMail);
		return randomMail;
	}

	public boolean isElementEnabled(By by) {

		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element [ " + by + " ] is enabled.");
			return true;
		} else {
			System.out.println("Element [ " + by + " ] is disabled.");
			return false;
		}

	}

}
