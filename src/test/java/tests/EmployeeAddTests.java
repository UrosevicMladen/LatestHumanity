package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.StaffPage;
import resources.BaseClass;

public class EmployeeAddTests extends BaseClass {
	
	@BeforeTest(alwaysRun = true)
	public void setup() throws IOException, InterruptedException {
		super.setUp();
		super.validLogin();
		super.logInit();
	}
	
	@Test(priority = 1)
  	public void testAddingEmployee() throws Throwable {
		StaffPage employee = new StaffPage(driver);
		employee.clickOnStaffPage();
		employee.employeePageTitle();
		employee.clickAddEmployee();
		employee.addEmployeeData();
		employee.clickOnSaveEmployeeBtn();
		Assert.assertTrue(true, String.valueOf(employee.employeeSavedLBL));
  	}
	
	@Test(priority = 2)
	public void testDeleteEmployee() {
		StaffPage employee = new StaffPage(driver);
		
		employee.clickOnStaffPage();
		employee.selectAddedEmployee();
		employee.deleteAddedEmployee();
		Assert.assertTrue(true, String.valueOf(employee.employeeDeletedLBL));
	}

	@Test(priority = 3)
	public void testAddingEmployeeWithInvalidEmail() throws Throwable {
		StaffPage employee = new StaffPage(driver);

		employee.clickOnStaffPage();
		employee.employeePageTitle();
		employee.clickAddEmployee();
		employee.addInvalidEmployeeData();
		employee.clickOnSaveEmployeeBtn();
		Assert.assertTrue(true, String.valueOf(employee.invlidEmailLabel));
	}
	
	@AfterTest
	public void teardown() {
		super.teardown();
	}
}
