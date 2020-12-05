package selenium_api;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

	public void TC03_handleCustomCheckboxRadioButton() throws Exception {
//		Step 01 - Truy cập vào trang https://material.angular.io/components/radio/examples
		driver.get("https://material.angular.io/components/radio/examples");

//		Step 02 - Click vào Radio button: Summer

		WebElement summerRadio = driver.findElement(By.xpath("//span[@class='mat-radio-label-content' and contains(text(), 'Summer')]/preceding-sibling::span/input"));
		wait.until(ExpectedConditions.elementToBeClickable(summerRadio));

//		Ở đây vì thẻ input trong element summerRadio không visible trên DOM (không highlight khi hold on xpath)
//		nên việc dùng click action của selenium sẽ báo lỗi:
//		org.openqa.selenium.WebDriverException: unknown error: Element <input type="radio"...> is not clickable
//		=> Sử dụng js để click lên input element.
//		
		jsExecutor.executeScript("arguments[0].click();", summerRadio);
		Thread.sleep(3000);

//		Select another radio button to check code re-check "summer
		WebElement springRadioElement = driver.findElement(By.xpath("//span[@class='mat-radio-label-content' and contains(text(), 'Spring')]/preceding-sibling::span/input"));
		jsExecutor.executeScript("arguments[0].click();", springRadioElement);

//		Step 03 - Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		if (!summerRadio.isSelected()) {
			System.out.println("Summer radiobutton is not selected. Re-select...");
			jsExecutor.executeScript("arguments[0].click();", summerRadio);
		}

//		Step 04 - Truy cập vào trang https://material.angular.io/components/checkbox/examples
		driver.get("https://material.angular.io/components/checkbox/examples");

//		Step 05 - Click vào checkbox:
//				+ checked
//				+ Indeterminate
		WebElement CheckBox1 = driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']//input"));

		WebElement CheckBox2 = driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-2']//input"));

		// scroll to Checkbox
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", CheckBox1);

		Commons.clickElementByJavascript(driver, CheckBox1);
		Commons.clickElementByJavascript(driver, CheckBox2);
		Thread.sleep(3000);

//		Step 06 - Kiểm tra checkbox đó đã chọn
		Assert.assertTrue(CheckBox1.isSelected());
		Assert.assertTrue(CheckBox2.isSelected());

//		Step 07 - Sau khi checkbox đã được chọn - bỏ chọn nó và kiểm tra nó chưa được chọn
		Commons.clickElementByJavascript(driver, CheckBox1);
		Commons.clickElementByJavascript(driver, CheckBox2);
		Thread.sleep(3000);

		Assert.assertFalse(CheckBox1.isSelected());
		Assert.assertFalse(CheckBox2.isSelected());
	}

	public void TC04_handleAcceptAlert() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;

//		Step 01 - Truy cập vào trang https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");

//		Step 02 - Click vào button: Click for JS Alert
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Click for JS Alert']")));
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

//		Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		Thread.sleep(4000);

//		Step 04 - Accept alert và verify message hiển thị tại Result là: You clicked an alert successfully
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");

	}

	public void TC05_handleConfirmAlert() throws Exception {
//		Step 01 - Truy cập vào trang https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");

//		Step 02 - Click vào button: Click for JS Confirm
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

//		Step 03 - Verify message hiển thị trong alert là: I am a JS Confirm
		Alert alert = driver.switchTo().alert();
		String alertTextString = alert.getText();
		Assert.assertEquals(alertTextString, "I am a JS Confirm");

//		Step 04 - Cancel alert và verify message hiển thị tại Result là: You clicked: Cancel
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");

	}

	public void TC06_handlePromptAlert() throws Exception {
//		Step 01 - Truy cập vào trang https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");

//		Step 02 - Click vào button: Click for JS Prompt
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

//		Step 03 - Verify message hiển thị trong alert là: I am a JS prompt
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "I am a JS prompt");

//		Step 04 - Nhập vào text bất kì và verify message hiển thị tại Result là You entered: <your_text>
		alert.sendKeys("Le Tran Phuong");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Le Tran Phuong");

	}

	public void TC07_handleAuthenAlertBypassLink() throws Exception {
		String sourceFolder = System.getProperty("user.dir");
		String username = "admin";
		String password = "admin";

//		Step 01 - Truy cập vào trang http://the-internet.herokuapp.com/basic_auth
//					bằng cách pass username/password vào link 
		driver.get("http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");

//		Step 02 - Verify message hiển thị sau khi login thành công:
//			Congratulations! You must have the proper credentials.
		System.out.println("sourceFolder: " + sourceFolder);
//		Commons.takeSnapShot(driver, sourceFolder + "/screenshot/" + Commons.getTimeStampValue() + ".png");
//		Commons.captureScreenSizePageWithAShotAPI(driver, sourceFolder + "/screenshot/" + Commons.getTimeStampValue() + ".png");
		Commons.captureFullpageWithAShotAPI(driver, sourceFolder + "/screenshot/" + Commons.getTimeStampValue() + ".png");
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']//p")).getText().trim().equals("Congratulations! You must have the proper credentials."));
	}

	@Test
	public void TC08_testScreenshotElement() throws Exception {
		String sourceFolder = System.getProperty("user.dir");

		driver.get("http://demo.guru99.com/v4/");

		WebElement logo = driver.findElement(By.xpath("//img[@role='presentation']"));
	
		Commons.compareImageByAShotAPI(driver, logo, sourceFolder+ "/screenshot/logo.png");

	}

	// Guide: https://www.youtube.com/watch?v=nKIIgQgAScQ
	public void TC08_handleAuthenAlertUsingAutoIT() throws Exception {
		String username = "admin";
		String password = "admin";

		driver.get("http://the-internet.herokuapp.com/basic_auth");

		Runtime.getRuntime().exec("<Path to autoIT script.exe>");
		// Run CompileScript to compile autoIT script -> exe file

		// AutoIT script as below:
//			WinWaitActive("Sign in")
//			Sleep(5000)
//			Send("admin")
//			Send("{TAB}")
//			Send("admin")
//			Send("{ENTER}")

//		Cuối cùng thì verify message.
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
