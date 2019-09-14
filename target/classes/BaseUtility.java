package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;



import org.openqa.selenium.support.PageFactory;
import locators.LoginPageLocators;
import pages.LoginPage;

public class BaseUtility {
	
	LoginPageLocators locators;
	public static WebDriver driver;
	public static Properties prop;
	public int waitTimeInSeconds = 18;
	public Actions actions;
	public WebDriverWait wait;
	public boolean shouldPrint = true;
	public long waitInTypeahead = 4000L;
	static Handler fileHandler = null;
	public final static Logger LOGGER = Logger.getLogger(BaseUtility.class.getClass().getName());

	//public WebElement element;

	public void setUp() throws IOException {
		this.initializeBrowser();
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, waitTimeInSeconds);
	/*	element= wait.until(ExpectedConditions.visibilityOfElementLocated((ByAll) element));
		element.click();
		driver.quit();
	}

		WebElement aboutMe= wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("about_me"));
			}
		});*/
	}

	public void logInit() throws SecurityException, IOException {
		fileHandler = new FileHandler("MyLogFile.log", true);
		SimpleFormatter simple = new SimpleFormatter();
		fileHandler.setFormatter(simple);
		LOGGER.addHandler(fileHandler);

	}
	
	public static WebDriver initializeDriver() throws IOException {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("firefox")) {
	//		System.setProperty(“webdriver.gecko.driver”, “/path/to/binary/geckodriver”);
			WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver();
		}
		else if (browserName.equals("chrome")) {
	//	System.setProperty(“webdriver.chrome.driver”, “/path/to/binary/chromedriver”);
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//	driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		return driver;

	}

	public static WebDriver fileHandler() throws IOException{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/main/java/resources/data.properties");
		prop.load(fis);
		return driver;
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

	public void initializeBrowser() throws IOException {
		if (driver != null) {
			return;
		}
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		
	}
	
	public void validLogin() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.loginPageTitle();
		login.enterEmail();
		login.enterPassword();
		login.clickSignIn();
//		login.checkForLoginSuccess(prop.getProperty("LoginSuccess"));
	}

/*	public void login() {
		LoginPage login = new LoginPage(driver);
		login.setEmail().sendKeys(prop.getProperty("username"));
		login.setPassword().sendKeys(prop.getProperty("password"));
		login.setSignIn().click();
	}
*/
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
		 DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		 Date date = new Date();		 
		 String time= dateFormat.format(date);
		 prop.setProperty(time, time);
	}
	/*
	public WebDriver waitForElement() throws Exception {
		WebDriverWait wait = new WebDriverWait(element, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
	}


	public void waitForTextToAppear(WebDriver newDriver, String textToAppear, By element) {
	    WebDriverWait wait = new WebDriverWait(newDriver,30);
	    wait.until(ExpectedConditions.textToBePresentInElementValue(element, textToAppear));
	}

	public static void clickable(WebDriver driver, By by) {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by).click();
			Thread.sleep(500L);
		} catch (StaleElementReferenceException sere) {

			driver.findElement(by).click();
		} catch (InterruptedException echo) {
			echo.printStackTrace();
		}
	}
	*/
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
	
	public WebElement getElement(String locator, String type) {
		type = type.toLowerCase();
		if (type.equals("id")) {
			System.out.println("Element found with id: " + locator);
			return driver.findElement(By.id(locator));
		}
		else if (type.equals("name")) {
			System.out.println("Element found with name: " + locator);
			return driver.findElement(By.name(locator));
		}
		else if (type.equals("xpath")) {
			System.out.println("Element found with xpath: " + locator);
			return driver.findElement(By.xpath(locator));
		}
		else if (type.equals("css")) {
			System.out.println("Element found with css: " + locator);
			return driver.findElement(By.cssSelector(locator));
		}
		else if (type.equals("classname")) {
			System.out.println("Element found with classname: " + locator);
			return driver.findElement(By.className(locator));
		}
		else if (type.equals("tagname")) {
			System.out.println("Element found with tagname: " + locator);
			return driver.findElement(By.tagName(locator));
		}
		else if (type.equals("linktext")) {
			System.out.println("Element found with link text: " + locator);
			return driver.findElement(By.linkText(locator));
		}
		else if (type.equals("partiallinktext")) {
			System.out.println("Element found with partial link text: " + locator);
			return driver.findElement(By.partialLinkText(locator));
		}
		else {
			System.out.println("Locator type not supported");
			return null;
		}
	}
	
	public List<WebElement> getElementList(String locator, String type) {

		type = type.toLowerCase();
		List<WebElement> elementList = new ArrayList<WebElement>();
		if (type.equals("id")) {
			elementList = driver.findElements(By.id(locator));
		}
		else if (type.equals("name")) {
			elementList = driver.findElements(By.name(locator));
		}
		else if (type.equals("xpath")) {
			elementList = driver.findElements(By.xpath(locator));
		}
		else if (type.equals("css")) {
			elementList = driver.findElements(By.cssSelector(locator));
		}
		else if (type.equals("classname")) {
			elementList = driver.findElements(By.className(locator));
		}
		else if (type.equals("tagname")) {
			elementList = driver.findElements(By.tagName(locator));
		}
		else if (type.equals("linktext")) {
			elementList = driver.findElements(By.linkText(locator));
		}
		else if (type.equals("partiallinktext")) {
			elementList = driver.findElements(By.partialLinkText(locator));
		}
		else {
			System.out.println("Locator type not supported");
		}
		if (elementList.isEmpty()) {
			System.out.println("Element not found with " + type +": " + locator);
			
		} else {
			System.out.println("Element found with " + type +": " + locator);
		}
		return elementList;
	}
	
	public boolean isElementPresent(String locator, String type) {
		List<WebElement> elementList = getElementList(locator, type);
		
		int size = elementList.size();
		
		if (size > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void teardown() {
//		LoginPage login = new LoginPage(driver);
		driver.quit();
//		driver = null;
		fileHandler.close();

	}

}