package selenium_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_TextBox_TextArea_DropList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascriptExecutor;

	WebDriverWait wExplicit;
	JavascriptExecutor jsExecutor;

	WebDriverWait wVuejsExplicit;
	JavascriptExecutor jsVuejs;

	WebDriverWait wEditable;
	
	WebDriverWait wReact;

	WebDriverWait wMulti;
	JavascriptExecutor jsMulti;

	private String userID, password, emailId, customerName, dob, address, city, state, pinNum, telNum, customerID;
	private String customerNameNew, cityNew, passStr;

	@BeforeClass
	public void beforeClass() {
		// Firefox
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		wExplicit = new WebDriverWait(driver, 30);
		wVuejsExplicit = new WebDriverWait(driver, 30);
		wEditable = new WebDriverWait(driver, 30);
		wReact = new WebDriverWait(driver, 30);
		wMulti = new WebDriverWait(driver, 30);

		// Chrome MAC -- cannot use chrome for this case due to issue at Birthday field
//		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver_mac_chrome86");
//		driver = new ChromeDriver();

		// Chrome Windows:
//		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver_win_chrome86.exe");
//		driver = new ChromeDriver();

		javascriptExecutor = (JavascriptExecutor) driver;
		jsExecutor = (JavascriptExecutor) driver;
		jsVuejs = (JavascriptExecutor) driver;
		jsMulti = (JavascriptExecutor) driver;

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC01_handleTextboxTextArea() {
		driver.get("http://demo.guru99.com/v4/");

		userID = "mngr291635";
		password = "sEjyvEm";
		emailId = Commons.randomEmail(); // "testautomation_819@gmail.com";

		customerName = "phuongcei";
		customerNameNew = "Man Hoang";
		dob = "1989-05-03";
		address = "59 Nguyen Chanh \n Hoa Khanh Bac";
		city = "Da Nang";
		cityNew = "Ha Noi";
		state = "Lien Chieu";
		pinNum = "123456";
		telNum = "0969835555";
		passStr = "111111";

		By customerNameBy = By.xpath("//td[text()='Customer Name']/following-sibling::td/input");
		By birthdayCreateBy = By.xpath("//td[text()='Date of Birth']/following-sibling::td/input");
		By addressBy = By.xpath("//td[text()='Address']/following-sibling::td/textarea");
		By cityBy = By.xpath("//td[text()='City']/following-sibling::td/input");
		By stateBy = By.xpath("//td[text()='State']/following-sibling::td/input");
		By pinCreateBy = By.xpath("//td[text()='PIN']/following-sibling::td/input");
		By telCreateBy = By.xpath("//td[text()='Mobile Number']/following-sibling::td/input");
		By emailCreateBy = By.xpath("//td[text()='E-mail']/following-sibling::td/input");
		By passBy = By.xpath("//td[text()='Password']/following-sibling::td/input");
		By createNewCusBtn = By.xpath("//input[@type='submit']");

		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);

		driver.findElement(By.xpath("// input[@name='password']")).clear();
		driver.findElement(By.xpath("// input[@name='password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		WebElement homePageMessage = driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));

		// Verify Home Page is displayed.
		Assert.assertTrue(homePageMessage.isDisplayed());

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

		driver.findElement(createNewCusBtn).click();

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

	public void TC02_handlePureDropdownList() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String testing[] = { "Automation", "Mobile", "Desktop" };

		WebElement JobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));

		// Pure Dropdown list use Select class to handle
		Select select = new Select(JobRole1);

		// Check this Job Role 1 is not multiple
		Assert.assertFalse(select.isMultiple());

		// Select by VisibleText
		select.selectByVisibleText("Mobile Testing");
//		Thread.sleep(3000);

		// Check value after selecting
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

//		System.out.println(select.getFirstSelectedOption().getText());

		select.selectByValue("manual");

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		select.selectByIndex(9);

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		Assert.assertEquals(select.getOptions().size(), 10);
		System.out.println("Number of options in dropdown: " + select.getOptions().size());

		// Verify multiple selected dropdown list - https://gist.github.com/daominhdam/9b58f3f533922b4148498fe10d6ecdd5
		WebElement JobRole2 = driver.findElement(By.xpath("//select[@id='job2']"));

		Select select2 = new Select(JobRole2);

		Assert.assertTrue(select2.isMultiple());

		// Select by visible text
		for (String visibleText : testing) {
			select2.selectByVisibleText(visibleText);
		}

		// Get list selected items
		List<WebElement> SelectedElements = select2.getAllSelectedOptions();
		Assert.assertEquals(SelectedElements.size(), 3);

		// Define for list string of selected items
		List<String> actualValues = new ArrayList<String>();

		for (WebElement selItem : SelectedElements) {
			actualValues.add(selItem.getText());
		}

		// Convert actual string array to a list for assert
		List<String> expectedValues = Arrays.asList(testing);

		// Assert to make sure selected items are correct
		Assert.assertEquals(actualValues, expectedValues);
	}

	public void TC03_handleHTMLDropdownList() {
		String email;

//		Truy cập trang https://demo.nopcommerce.com/register
//		Click Register button trên Header 
//		Input các thông tin hợp lệ vào form 
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		driver.findElement(By.xpath("//input[@id='gender-male']")).click();

		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Phuong");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Le");

//		Chọn các giá trị lần lượt 1, May, 1980 ở các dropdownlist 
//		Kiểm tra số lượng phần tử trong cár dropdownlist lần lượt là 32, 13, 112
		Select dateselect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		dateselect.selectByVisibleText("1");
		dateselect.getFirstSelectedOption();
		List<WebElement> datelist = dateselect.getOptions();
		Assert.assertEquals(datelist.size(), 32);

		Select monthselect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		monthselect.selectByVisibleText("May");
		monthselect.getFirstSelectedOption();
		List<WebElement> monthlist = monthselect.getOptions();
		Assert.assertEquals(monthlist.size(), 13);

		Select yearselect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		yearselect.selectByVisibleText("1980");
		yearselect.getFirstSelectedOption();
		List<WebElement> yearlist = yearselect.getOptions();
		Assert.assertEquals(yearlist.size(), 112);

		email = Commons.randomEmail();

		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();

//		Kiểm tra được chuyển đến trang chủ thành công sau khi đăng ký.
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

//		Click vào My Account và kiểm tra ngày tháng năm trong các dropdownlist là đúng.
		driver.findElement(By.xpath("//a[contains(@class,'ico-account')]")).click();

		/*
		 * ***** NOTE: Ở đây cần phải findElement lại 3 dropdownlist vì trang đã được load mới (khi click on My Account) mặc dù xpath không thay đổi. Trường hợp không find lại element sẽ dẫn đến lỗi bên dưới: org.openqa.selenium.StaleElementReferenceException: Element not found in the cache -
		 * perhaps the page has changed since it was looked up
		 */

		dateselect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(dateselect.getFirstSelectedOption().getText(), "1");

		monthselect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(monthselect.getFirstSelectedOption().getText(), "May");

		yearselect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(yearselect.getFirstSelectedOption().getText(), "1980");

	}

	public void TC04_handleJqueryDropdown() throws Exception {
// ** Solution:
//		- Click vào dropdown list
//		- Wait để tất cả các phần tử trong dropdown list hiển thị
//		- Get tất cả items trong dropdownlist vào trong 1 list Element (List<WebElement>)
//		- Dùng vòng lặp duyệt qua các phần tử vào getText()
//		- Nếu text đc get ra bằng textExpected thì:
//				+ Scroll den phan tu do
//				+ Click vao phan tu do
//				+ Break khoi vong lap

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemCustomDropdown("//label[text()='Select a number']", "//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "14");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='14']")).isDisplayed());
		Thread.sleep(3000);

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemCustomDropdown("//label[text()='Select a number']", "//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(3000);

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemCustomDropdown("//label[text()='Select a number']", "//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		Thread.sleep(3000);

	}

	public void TC05_handleAngularDropdown() throws Exception {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

//		selectItemCustomDropdown("//div[@id='sb-content-header']", "//*[@id='games']/span", "//ul[@id='games_options']/li", "Football");
		selectItemCustomDropdownNew("//*[@id='games']/span", "//ul[@id='games_options']/li", "Football");
//		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='games']//option[text()='Football']")).isDisplayed());

		// Ở đây phải assert attribute, vì sau khi chọn, item đó bị ẩn đi không check display như bình thường được
//		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='games']/span/select/option")).getAttribute("value"), "Game5");

		Assert.assertEquals(getHiddenText("#games_hidden"), "Football");

		// Remote Data dropdown
		selectItemCustomDropdownNew("//ejs-dropdownlist[@id='customers']", "//ul[@id='customers_options']/li", "Robert King");
		Assert.assertEquals(getHiddenText("#customers_hidden"), "Robert King");

	}

	public void TC06_handleReactDropdown() throws Exception {
		String parentLocator = "//div[@id='root']//div[@role='listbox']";
		String itemLocator = "//div[contains(@class,'menu')]/div";
		String expectedItem = "Matt";
		String cssLocator = "[role='listbox']>.menu>.selected";

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		// Wait cho các element có thể clickable
		wExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();

		// Wait cho cac itemLocator xuat hien trong HTML DOM:
		wExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));

		// Get all items into a list
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));

		// Duyet qua tung phan tu kiem tra xem text = expected or not
		for (WebElement item : allItems) {
			String itemText = item.getText().trim();
			System.out.println("Actual text of item: " + itemText);

			if (itemText.equals(expectedItem)) {
				// scroll tới expected item và click on it
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);

				// wait cho element clickable
				wExplicit.until(ExpectedConditions.elementToBeClickable(item));
				item.click();
				Thread.sleep(3000);
				break;
			}

		}

		// Kiem tra item selected
		// Assert bằng xpath thông thường không tìm thấy text. Cần dùng getHiddenText method
//		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'menu')]/div[contains(@class,'selected')]")).getText(), expectedItem);
		Assert.assertEquals(getHiddenText(cssLocator), expectedItem);
	}

	public void TC07_handleVuejsDropdown() throws Exception {
		String parentLocator = "//li[@class='dropdown-toggle']";
		String itemLocator = "//ul[@class='dropdown-menu']/li";
		String expectedItem = "Third Option";

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		// Wait all items are clickable and then click on parentLocator to show all list
		wVuejsExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();
		Thread.sleep(3000);

		// Wait for all items in dropdown presence in HTML DOM
		wVuejsExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));

		// Get all items into list
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));

		// Parse each item, get text and check if it equals to expectedItem?
		for (WebElement item : allItems) {
			String textItem = item.getText().trim();
			System.out.println("item text: " + textItem);

			// Check if the text equals to expected. If yes, click on item and break the loop
			if (textItem.equals(expectedItem)) {
				// Scroll to expected item
				jsVuejs.executeScript("arguments[0].scrollIntoView(true);", item);
				Thread.sleep(3000);
				// wait for item clickable
				wVuejsExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath(itemLocator)));
				item.click();
				Thread.sleep(3000);
				break;
			}
		}

		// Assert selected item
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().equals(expectedItem));

	}

	public void TC08_handleEditableDropdown() throws Exception {
		String parentLocator = "//div[@id='basic-place']/input";
//		String itemLocator = "//div[@id='basic-place']//li";
		String expectedValue = "Porsche";

		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		// Wait for all item clickable
		wEditable.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();

		driver.findElement(By.xpath(parentLocator)).clear();
		driver.findElement(By.xpath(parentLocator)).sendKeys(expectedValue);
		driver.findElement(By.xpath(parentLocator)).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);

		System.out.println("Text: " + driver.findElement(By.xpath("//div[@id='basic-place']//li[@class='es-visible selected']")).getText());

//		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='basic-place']//li[@class='es-visible selected']")).getText(), expectedValue);
		// Ở đây không thể dùng xpath ở trên và getText() để check vì xpath đó đang trỏ tới item bên trong dropdown (khi click lên dropdown và
		// find xpath sẽ thấy highlight)

		// => Dùng getAttribute để check
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='basic-place']//li[text()='Porsche']")).getAttribute("class"), "es-visible selected");

	}

	public void TC09_handleEditableDropdownReact() throws Exception {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		wReact.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='search']")));

		driver.findElement(By.xpath("//input[@class='search']")).click();
		driver.findElement(By.xpath("//input[@class='search']")).clear();
		driver.findElement(By.xpath("//input[@class='search']")).sendKeys("Bahamas");
		driver.findElement(By.xpath("//input[@class='search']")).sendKeys(Keys.ENTER);

		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'divider') and text()='Bahamas']")).isDisplayed());

	}

	public void TC10_handleMultiSelectDropdown() throws Exception {
		String parentLocator = "//div[@id='example']/div/div[2]//button";
		String itemLocator = "//div[@id='example']/div/div[2]//ul/li";
//		String expectedMonths[] = { "May", "September", "December" };

		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		// Vì behavior của dropdown là sau khi chọn 1 item, dropdown sẽ không close mà có thể chọn item tiếp theo luôn
		// Nên nếu chọn đưa click parentLocator vào trong method selectItemForMultiple ở lần chọn thứ 2 sẽ làm đóng dropdown

		// wait for parent locator clickable and click on it
		wMulti.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();

		// Select May, September, December
		selectItemForMultiple(itemLocator, "May");
		Thread.sleep(1000);

		selectItemForMultiple(itemLocator, "September");
		Thread.sleep(1000);

		selectItemForMultiple(itemLocator, "December");
		Thread.sleep(1000);

		// Verify if it present May, September, December
		String outExpected = "May, September, December";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='example']/div/div[2]//button")).getText().trim(), outExpected);

		selectItemForMultiple(itemLocator, "August");
		selectItemForMultiple(itemLocator, "February");

		// Verify if it 5 of 12 selected
		String outExpected2 = "5 of 12 selected";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='example']/div/div[2]//button")).getText().trim(), outExpected2);

	}

	@Test
	public void TC11_handleMultiSelectDropdownNew() throws Exception {

		String parentLocator = "//div[@id='example']/div/div[2]//button";
		String itemLocator = "//div[@id='example']/div/div[2]//ul/li";
		String selectedMonths[] = { "May", "September", "December", "January", "August" };

		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		wMulti.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();

		wMulti.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));

		int i = 0;
//		String outExpected = "";
		for (WebElement item : allItems) {
			for (String expectedvalue : selectedMonths) {
				if (expectedvalue.equals(item.getText())) {

//					outExpected = outExpected + ", " + item.getText();
					jsMulti.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
					i++;

				}
			}
			if (i == 3) {
				String outExpected = "January, May, August";
				System.out.println("Text when i=3: " + outExpected);
				Assert.assertEquals(driver.findElement(By.xpath("//div[@id='example']/div/div[2]//button")).getText().trim(), outExpected);
			} else if (i >= 4) {
				int selectedSize = driver.findElements(By.xpath("//div[@id='example']/div/div[2]//ul/li[@class='selected']")).size();
				String textInDropDown = driver.findElement(By.xpath("//div[@id='example']/div/div[2]//button")).getText().trim();
				Assert.assertEquals(textInDropDown, selectedSize + " of 12 selected");
//				Assert.assertEquals(textInDropDown, i + " of " + selectedSize + " selected");
				System.out.println("Text in dropdown: " + textInDropDown);
			}
		}

	}

	// Cần viết 1 method nhận selectedMonths làm input
	// Duyệt qua từng phần tử và select it in dropdown
	// Đếm số lần chọn, nếu <= 3 thì kiểm tra text trong dropdown là: '<month>, <month>, <month>
	// nếu số lần chọn >3 thì kiểm tra text trong dropdown là i of list.size

	@AfterClass
	public void afterClass() {
		driver.quit();
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

	public void selectItemCustomDropdown(String scrollToXpath, String parentXpath, String childXpath, String expectedValue) {

		// Scroll to a specific element to avoid cover before click on:
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollToXpath)));

		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();

		List<WebElement> childList = driver.findElements(By.xpath(childXpath));

		// Wait để tất cả elements trong dropdown hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));

		for (WebElement child : childList) {
			String textItem = child.getText().trim();
			System.out.println(textItem);
			if (textItem.equals(expectedValue)) {

				// scroll to element before click on:
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				child.click();
				break;
			}
		}

	}

	public void selectItemCustomDropdownNew(String parentLocator, String itemLocator, String expectedItem) throws Exception {

		// 1 - Click vao 1 parent de xo het ra tat ca item trong dropdown
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();
//		sleepInSecond(1);
		Thread.sleep(1000);

		// 2 - Chờ cho tất cả items xuất hiện trong HTML DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));

		// 3 - Lấy hết tất cả items này đưa vào 1 List elements
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));

		// Tổng số lượng items trong dropdown
		System.out.println("Total of items in dropdown: " + allItems.size());

		// 4 - Duyệt qua từng items
		for (WebElement item : allItems) {
			// 5 - Mỗi lần duyệt qua kiểm tra xem item đó có text giống expectedItem không
			String actualItem = item.getText();
			System.out.println("Actual item: " + actualItem);

			// 6 - Nếu như bằng thì click vào và thoát khỏi vòng lặp
			// - Nếu không bằng thì tiếp tục duyệt tiếp

			if (actualItem.equals(expectedItem)) {
				// Trước khi click thì cần scroll đến item đó để tránh bị lỗi đè cover
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				Thread.sleep(2000);

				// Wait cho element clickable
				waitExplicit.until(ExpectedConditions.elementToBeClickable(item));
				item.click();
				Thread.sleep(2000);
				break;

			}
		}

	}

	public String getHiddenText(String cssLocator) {
		return (String) javascriptExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
	}

	public void selectItemForMultiple(String itemLocator, String expectedValue) {

		// wait for all element in dropdown presence in HTML DOM
		wMulti.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));

		// Get all items into list
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));

		// Duyet qua allItems, kiem tra text() = expected, scroll toi item va click on it, break loop
		for (WebElement item : allItems) {
			String textItem = item.getText();
			System.out.println("Text item: " + textItem);
			if (expectedValue.equals(textItem)) {
				jsMulti.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}
		}
	}

}
