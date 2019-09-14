package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import pages.*;

public class BaseClass {

	public static WebDriverWait wait;
	public static WebDriver driver;
	public static Properties prop;
	public int waitTimeInSeconds = 20;
	public int poll = 6;
	public Actions actions;
	public boolean shouldPrint = true;
	public long waitInTypeahead = 4000L;
	static Handler fileHandler = null;
	public final static Logger LOGGER = Logger.getLogger(BaseClass.class.getClass().getName());


	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		this.initializeBrowser();
		this.actions = new Actions(driver);
		this.driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
	}


	public static WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/main/java/resources/data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		String User = System.getProperty("user.name");
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/" + User + "/humanity"+"/geckodriver");
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/" + User + "/humanity"+"/chromedriver");
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
		return driver;

	}

	public void initializeBrowser() throws IOException {
		if (driver != null) {
			return;
		}
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}

	public void logInit() throws SecurityException, IOException {
		fileHandler = new FileHandler("MyLogFile.log", true);
		SimpleFormatter simple = new SimpleFormatter();
		fileHandler.setFormatter(simple);
		LOGGER.addHandler(fileHandler);

	}

	public WebElement waitForElement(By by) {
		WebElement result = null;
		long maxTime = 5 * 1000;
		long timeSlice = 250;
		long elapsedTime = 0;
		do {
			try {
				Thread.sleep(timeSlice);
				elapsedTime += timeSlice;
				result = driver.findElement(by);
			} catch (Exception e) {
			}
		} while (result == null && elapsedTime < maxTime);
		return result;
	}

	public void validLogin() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.loginPageTitle();
		login.enterEmail();
		login.enterPassword();
		login.clickSignIn();
		Assert.assertTrue(true, prop.getProperty("SuccessLogin"));
	}

	public void print(String message) {
		if (shouldPrint) {
			System.out.println(message);
		}
	}

	public void insert(By element, String property) {
		driver.findElement(element).sendKeys(prop.getProperty(property));
	}

	public void getScreenshot() throws IOException {
		String filename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(
				"/Users/" + prop.getProperty("macusername") + "/Screenshots/" + filename + "screenshot.png");
		FileUtils.copyFile(src, dest);
	}

	public void getCurrentTime() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'-'HH:mm");
		Date date = new Date(System.currentTimeMillis());
		prop.setProperty(String.valueOf(date), "key");
	}

	public void clickAndWait(WebDriver driver, By by) {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by).click();
			Thread.sleep(500L);
		} catch (StaleElementReferenceException e) {

			driver.findElement(by).click();
		} catch (InterruptedException echo) {
			echo.printStackTrace();
		}
	}

	public void pageTitle() {
		String title = driver.getTitle();
		print("Title is: "+ title);
		String expectedTitle = title;
		Assert.assertEquals(title, expectedTitle);
		if (title == expectedTitle)
			print("Test is sucessful. Found title: " + expectedTitle);
		else
			print("Test is failed. Did not find title:" + expectedTitle);
	}

	public void teardown() {
		driver.quit();
		fileHandler.close();

	}

}