package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import resources.BaseClass;

public class StaffPage extends BaseClass {
	
	public By staffPage = By.xpath("//a[@id='sn_staff']");
	public By addEmployeeBtn = By.xpath("//button[@id='act_primary'][text()='Add Employees']");
	public By firstNameField = By.xpath("//input[@placeholder='first name'][@id='_asf1']");
	public By lastNameField = By.xpath("//input[@placeholder='last name'][@id='_asl1']");
	public By eMailField = By.xpath("//input[@placeholder='email@address.com'][@id='_ase1']");
	public By saveEmployeeBtn = By.xpath("//button[@id='_as_save_multiple']");
	public By employeeSavedLBL = By.xpath("//div[@id='_status']//div[text()='1 employee saved!']");
	public By closeEmployee = By.xpath("/html//div[@id='_cd_staff']/div[@class='right']/table[@class='p20']//table[@class='ResultsTable']//tr[@class='bulkfull full4932427 hide']/td[@title='']/a[@href='javascript://']");
	public By possitionAdded = By.xpath("//div[@id='_status']//div[text()='Position Added']");
	public By positionRemoved = By.xpath("//div[@id='_status']//div[text()='Position Removed']");
	public By addedEmployee = By.linkText("Gary Fisher");
	public By deleteEmployee = By.linkText("Click Here");
	public By invlidEmailLabel = By.xpath("//div[@id='_status'][text()='Invalid Email']");
	public By employeeDeletedLBL = By.xpath("//div[@id='_status'][text()='Employee Deleted']");
	
	public StaffPage(WebDriver driver) {
		
		StaffPage.driver = driver;
	}

	public void clickOnStaffPage() {
		this.waitForElement(staffPage).click();
	
	}

	public void employeePageTitle() {
	this.pageTitle();
	String currentUrl = driver.getCurrentUrl();
	this.print("The URL is: " +currentUrl);
	//Assert.assertEquals(currentUrl, prop.getProperty("staffPageUrl"));
	}
	
	public void clickAddEmployee() {
		this.waitForElement(addEmployeeBtn).click();
		//driver.findElement(addEmployeeBtn).click();
		this.print("Employee Button clicked");
	}
	public void addEmployeeData() {
		this.waitForElement(firstNameField).sendKeys(prop.getProperty("Name1"));
		driver.findElement(lastNameField).sendKeys(prop.getProperty("Surname1"));
		driver.findElement(eMailField).sendKeys(prop.getProperty("email1"));
		this.print("Employee added");
	}
	public void addInvalidEmployeeData() {
		this.waitForElement(firstNameField).sendKeys(prop.getProperty("Name2"));
		driver.findElement(lastNameField).sendKeys(prop.getProperty("Surname2"));
		driver.findElement(eMailField).sendKeys(prop.getProperty("invalidEmail2"));
	}

	public void clickOnSaveEmployeeBtn() {
		this.waitForElement(saveEmployeeBtn).click();
		this.print("Employee saved");
	}
	
	public void selectAddedEmployee() {
		this.waitForElement(addedEmployee).click();
	}
	
	public void deleteAddedEmployee() {
		this.waitForElement(deleteEmployee).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	
	

	

}
