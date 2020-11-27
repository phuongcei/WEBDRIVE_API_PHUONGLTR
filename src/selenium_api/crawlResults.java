package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class crawlResults {
	WebDriver driver;
//	private String xpath_linkTo655;

	By xpath_linkTo655 = By.xpath("//a[contains(@href, 'winning-number-655')]");
	By xpath_linkTo645 = By.xpath("//a[contains(@href, 'winning-number-645')]");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver_win_chrome86.exe");
//		driver = new ChromeDriver();
		
		
		driver.get("https://vietlott.vn/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void crawl_result_from_web() throws Exception {

		driver.findElement(xpath_linkTo645).click();

		// Get list of dates

		Select sel = new Select(driver.findElement(By.xpath("//select[@id='drpSelectGameDraw']")));

		List<WebElement> listDate = sel.getOptions();
		System.out.println("Number of rounds: " + listDate.size());

		// ======
//		int i = 1;

		for (WebElement resultdate : listDate) {

//			if (resultdate.getText().trim().equals("Toàn bộ") || resultdate.getText().trim().equals("01/11/2020 (00659)")) {
			if (!resultdate.getText().trim().equals("Toàn bộ")) {

//			} else {
				System.out.print(resultdate.getText() + " ");
				sel.selectByVisibleText(resultdate.getText().trim());
				sel.getFirstSelectedOption().click();
				Thread.sleep(6000);
				driver.findElement(By.xpath("//a[contains(@href, 'DoSearch')]")).click();
				Thread.sleep(3000);

				List<WebElement> bongtrons = driver.findElements(By.xpath("//div[@class='day_so_ket_qua_v2' and contains(@style, 'padding-top')]//span[contains(@class, 'bong_tron')]"));

				for (WebElement bongtron : bongtrons) {
					System.out.print(bongtron.getText() + " ");
				}
				System.out.println(System.lineSeparator());
//				i++;

//				if (i == 4) {
//					break;
//				}

			}
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
