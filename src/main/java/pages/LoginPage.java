package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.BaseClass;

public class LoginPage extends BaseClass {

	BaseClass baseClass;

	public By email = By.cssSelector("[name='email']");
	public By password = By.cssSelector("[name='password']");
	public By button = By.cssSelector("#LoginForm button");
	public By success = By.cssSelector("#response-message");
	public By validCreds = By.xpath("//div[@id='response-message'][text()='Login success. One second please..']");
	public By invalidCreds = By.xpath("//div[@id='response-message'][text()='Your username/email or password is incorrect. Please try again or click ']");
	

	public LoginPage(WebDriver driver) {

		LoginPage.driver = driver;
	}
	
	public void loginPageTitle() {
		this.pageTitle();
	}

	public void enterEmail() {
		this.waitForElement(email).sendKeys(prop.getProperty("email"));
	}

	public void enterUsername() {
		this.waitForElement(email).sendKeys(prop.getProperty("username"));
	}
	
	public void enterInvalidEmail() {
		this.waitForElement(email).sendKeys(prop.getProperty("invalidEmail"));
	}

	public void enterInvalidEmail2() {
		this.waitForElement(email).sendKeys(prop.getProperty("invalidEmail2"));
	}

	public void enterRandomUsername() {
		this.waitForElement(email).sendKeys(prop.getProperty("randomUsername"));
	}

	public void enterPassword() {
		this.waitForElement(password).sendKeys(prop.getProperty("password").toUpperCase());
	}

	public void enterUpperCaseUsername() {
		this.waitForElement(email).sendKeys(prop.getProperty("username").toUpperCase());
	}
	
	public void enterLowerCasePassword() {
		driver.findElement(password).sendKeys(prop.getProperty("password").toLowerCase());
	}

	public void clickSignIn() throws InterruptedException {
		this.waitForElement(button).click();
	}

	public String validCredentials() {
		return this.waitForElement(success).getText();
	}


	public String invalidCredentials() {
		return this.waitForElement(invalidCreds).getText();
	}

}
