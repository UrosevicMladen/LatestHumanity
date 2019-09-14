package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import resources.BaseClass;

public class DashboardPage extends BaseClass {


	public By welcomeMsg = By.xpath("//div[text()='Welcome to Humanity!']");
	public By scheduleNav = By.xpath("//a[@id=\"sn_schedule\"]/span/i");
	public By staffNav = By.cssSelector("[onclick='_load\\(\\'staff\\'\\, true\\)\\; return false\\;'] .primNavQtip__itemName");
	public By timeClockNav = By.cssSelector("[onclick='_load\\(\\'timeclock\\'\\, true\\)\\; return false\\;'] .primNavQtip__itemName");
	public By clockIn = By.xpath("//a[@id='tc_tl_ci']");
	public By clockOut = By.cssSelector(".clockOut_help_tag");
	public By removeClockTime = By.xpath("//div[@id='timeClockWidget']//a[text()='Remove this Clock In Time']");
	public By clockInTimeDeleted = By.xpath("//div[@id='_status'][text()='Clock in time deleted.']");
	public By toastClockedIn = By.xpath("//div[@id='_status'][text()='Clocked in.']");
	public By toastClockedOut = By.xpath("//div[@id='_status'][text()='Clocked out.']");
	public By clockedInLBL = By.xpath("//div[text()='Clocked in.']");
	public By clockedOutLBL = By.xpath("//div[text()='Clocked out.']");
	public By clockInTime = By.cssSelector(".clockedIn .time");
	public By expectedClockInTime = By.cssSelector("[class='time be-right-15']");
	public By clickOnNotes = By.xpath("//input[@value='Click to add notes']");//("//input[@id='tc_tl_no']");
	//public By clickOnNotes = By.xpath("/html//div[@id='right']");
	public By addNotes = By.xpath("//a[@id='tc_tl_no_a']");
	public By addBreak = By.xpath("//a[@id='tc_tl_br_s']");
	public By continueShift = By.xpath("clickToContinueShift");
	private By expandUserNav = By.xpath("//div[@id='wrap_us_menu']/i");
	private By logOut = By.linkText("Sign Out");
	
	public boolean clockedInHighlighted() {
		return driver.findElement(toastClockedIn).isEnabled();
	}
	public WebElement clockedInToast() {
		return driver.findElement(toastClockedIn);
	}
	
	
	public DashboardPage (WebDriver driver) {

		DashboardPage.driver = driver;
		Actions builder = new Actions(driver);
	}

	public void moveToElement() {
		this.actions.keyDown(Keys.DOWN).pause(2000).keyDown(Keys.NULL)
		.moveToElement((WebElement) clockIn).pause(2).sendKeys(Keys.ENTER).build()
		.perform();
	}

	public void clickOnClockIn() {
		this.waitForElement(clockIn).click();
	}
	
	public void getClockInTime() {
		this.waitForElement(expectedClockInTime);
	}
	/*
	public void clickToAddNotes() throws Throwable {
		this.waitForElement(clickOnNotes).sendKeys(prop.getProperty("AddNotes"));
		print("clicked on and added notes");
		//this.actions.moveToElement(driver.findElement(addNotes)).pause(2000).click(driver.findElement(clickOnNotes)).sendKeys(prop.getProperty("AddNotes")).keyDown(Keys.ENTER).build().perform();
		//assertTrue(prop.getProperty("AddNotes").equals(notesAdded));
	}
	*/
	public void clickToAddBreak() {
		this.waitForElement(addBreak).click();
	}

	public void clickToContinueShift() {
		this.waitForElement(continueShift).click();
	}

	public void clickOnClockOut() {
		this.waitForElement(clockOut).click();
		driver.findElement(clockOut).click();
	}
	public void removeClockTime() {
		driver.findElement(removeClockTime).click();
		Alert removeTime = driver.switchTo().alert();
		removeTime.getText();
		removeTime.accept();
		assert(true);
	}

}
