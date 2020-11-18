package selenium_api;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Button_RadioButton_Checkbox_Alert {
	WebDriver driver;
	WebDriverWait wait;
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

		wait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
	}

	public void TC01_handleButtonJs() throws Exception {

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
		By buttonXpathBy = By.xpath("//button[@class='fhs-btn-login']");

		Commons.removeAttributeElement(driver, buttonXpathBy, "disabled");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();

		List<WebElement> listMessages = driver.findElements(By.xpath("//div[@class='popup-login-content']//div[@class='fhs-input-alert']"));
		for (WebElement massage : listMessages) {
			Assert.assertEquals(massage.getText().trim(), "Thông tin này không thể để trống");
		}

	}

	@Test
	public void TC02_handleDefaultCheckboxRadioButton() throws Exception {
//			Step 01 - Truy cập trang: http://demos.telerik.com/kendo-ui/styling/checkboxes
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");

//			Step 02 - Click vào checkbox: Dual-zone air conditioning
		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='example']//li")));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dualZoneCheckbox);
		dualZoneCheckbox.click();
		Thread.sleep(3000);

//			Step 03 - Kiểm tra checkbox đó đã chọn
		Assert.assertTrue(dualZoneCheckbox.isSelected());

//			Step 04 - Sau khi checkbox đã được chọn - bỏ chọn nó và kiểm tra nó chưa được chọn
		dualZoneCheckbox.click();
		Thread.sleep(3000);
		Assert.assertFalse(dualZoneCheckbox.isSelected());

//			Step 05 - Truy cập vào trang http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

//			Step 06 - Click vào radio button: 2.0 Petrol, 147kW
		WebElement petrolRadio = driver.findElement(By.xpath("//div[@id='example']//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", petrolRadio);
		petrolRadio.click();
		Thread.sleep(3000);

//			Step 07 - Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		if (petrolRadio.isSelected()) {
			System.out.println("Radio button is selected.");
		} else {
			System.out.println("Radio button is NOT selected. Re-select.");
			petrolRadio.click();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
