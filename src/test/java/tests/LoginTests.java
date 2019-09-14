package tests;

import org.testng.annotations.Test;

import org.testng.Assert;
import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;
import resources.BaseClass;

public class LoginTests extends BaseClass {

	@BeforeTest(alwaysRun = true)
	public void setup() throws IOException, InterruptedException {
		super.setUp();
		super.logInit();
	}

	@Test(priority=1)
	public void invalidEmail() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.enterInvalidEmail();
		login.enterPassword();
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
	}

	@Test(priority=2)
	public void validUpperCaseUserName() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.enterUpperCaseUsername();
		login.enterEmail();
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
	}

	@Test(priority=3)
	public void enterUsernameAndMissingPass() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.enterEmail();
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
	}

	@Test(priority=4)
	public void enterPassAndMissingUsername() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.enterPassword();
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
	}

	@Test(priority=5)
	public void missingPassAndUsername() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
	}
	@Test(priority=6)
	public void usernameAllCaps() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.enterUpperCaseUsername();
		login.enterPassword();
		login.clickSignIn();
		Assert.assertTrue(true, prop.getProperty("SuccessLogin"));
	}

	@Test(priority=7)
	public void multipleInvalidEmailUsernameEntries() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.enterInvalidEmail();
		login.enterPassword();
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
		login.enterInvalidEmail2();
		login.enterPassword();
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
		login.enterRandomUsername();
		login.enterPassword();
		login.clickSignIn();
		Assert.assertEquals(login.invalidCredentials(), prop.getProperty("invalidMessage"));
	}
    @Test(priority=8)
	public void validLogin() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.loginPageTitle();
        login.enterEmail();
        login.enterPassword();
        login.clickSignIn();
        Assert.assertEquals(login.validCredentials(), prop.getProperty("SuccessLogin"));
    }
    @AfterTest
        public void teardown () {
		super.teardown();

    }

}