package pages;

import org.openqa.selenium.*;
import resources.BaseClass;
import java.io.IOException;

public class TimeClockPage extends BaseClass {


    public By timeClock = By.xpath("//a[@id='sn_timeclock']//i");
    public By clockInButton = By.xpath("//a[@id='tc_tl_ci']");
    public By clockOutButton = By.xpath("//a[@id='tc_tl_co']/span[@class='clockOut_help_tag']");
    public By displayedDate = By.xpath("//table[@id='tc_tl_overview']//tr/td[1]/div[1]");
    public String title = "Timeclock - Overview - Humanity";
    public By clockInLabel = By.xpath("//div[text()='Clocked in.']");
    public By clockedOutLabel = By.xpath("//div[text()='Clocked out.']");
    public By clockInTime = By.cssSelector(".clockedIn .time");
    public By expectedClockInTime = By.cssSelector("[class='time be-right-15']");
    public By breakStarted = By.xpath("//ul[@id='tc_timeline']/li[@class='center']/ul/li[11]/div");
    public By addBreak = By.xpath("//a[@id='tc_tl_br_s']/span[@class='icon-break']"); //("//a[@id='tc_tl_br_s']");
    public By continueShift = By.xpath("//a[@id='tc_tl_br_e']");
    public By removeClockTime = By.xpath("//a[text()='Remove this Clock In Time']");
    public By addNotes = By.xpath("//a[@id='tc_tl_no_a']");
    public By clickOnNotes = By.xpath("//div[@id='right']");
    public By typheadNotes = By.xpath("//input[@id='tc_tl_no']");


    public TimeClockPage (WebDriver driver){
        TimeClockPage.driver = driver;
    }

    public void timeClockPageTitle() {
        this.pageTitle();
        String currentUrl = driver.getCurrentUrl();
        this.print("The URL is: " + currentUrl);
    }

    public void clickOnTimeClockPage() throws IOException {
        this.waitForElement(timeClock).click();
    }
    public void clickOnClockIn () throws  IOException {
        this.waitForElement(clockInButton).click();
    }

    public String clockedIn() {
        return this.waitForElement(clockInLabel).getText();
    }

    public void clickToAddBreak() {
        this.waitForElement(addBreak).click();
    }

    public void clickToContinueShift() {
        this.waitForElement(continueShift).click();
    }

    public void clickOnClockOut() {
		this.waitForElement(clockOutButton).click();
    }

    public void removeClockTime() {
        this.waitForElement(removeClockTime).click();
        Alert removeTime = driver.switchTo().alert();
        removeTime.getText();
        removeTime.accept();
        assert(true);
    }
}
