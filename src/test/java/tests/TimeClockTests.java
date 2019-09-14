package tests;


import org.testng.annotations.Test;

import org.testng.Assert;
import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.DashboardPage;
import pages.TimeClockPage;
import resources.BaseClass;

public class TimeClockTests extends BaseClass {

    @BeforeTest(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
        super.setUp();
        super.validLogin();
        super.logInit();
    }

    @Test(priority = 1)
    public void clockEmployeeIn() throws IOException, InterruptedException {
        TimeClockPage timeClock = new TimeClockPage(driver);
        timeClock.timeClockPageTitle();
        timeClock.clickOnTimeClockPage();
        timeClock.clickOnClockIn();
        Assert.assertTrue(true, prop.getProperty("ClockedIn"));
    }

    @Test(priority = 2)
    public void addBreak() throws IOException {
        TimeClockPage timeClock = new TimeClockPage(driver);
        timeClock.clickOnTimeClockPage();
        timeClock.clickToAddBreak();
        Assert.assertTrue(true, prop.getProperty("BreakStarted"));
    }
    @Test(priority = 3)
    public void continueShift() throws IOException {
        TimeClockPage timeClock = new TimeClockPage(driver);
        timeClock.clickOnTimeClockPage();
        timeClock.clickToContinueShift();
        Assert.assertTrue(true, prop.getProperty("ContinueShift"));
    }
    @Test(priority = 4)
    public void clockOut() throws IOException {
        TimeClockPage timeClock = new TimeClockPage(driver);
        timeClock.clickOnTimeClockPage();
        timeClock.clickOnClockOut();
        Assert.assertTrue(true, prop.getProperty("ClockedOut"));
    }

    @Test(priority = 5)
    public void removeTheShift() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickOnClockIn();
        dashboardPage.removeClockTime();
    }

    @AfterTest
    public void teardown () {
        super.teardown();
    }

}
