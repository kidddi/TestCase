package selenium;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import extentreports.ExtentFactory;
import utilites.GetScreenshot;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageclasses.SearchPageFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by energetic on 19.05.2016.
 */
public class TestngCase {
    private WebDriver driver;
    private String baseUrl;
    SearchPageFactory searchPage;
    static Logger log = Logger.getLogger(String.valueOf(TestSearchFactory.class));

    public WebDriver getDriver() {
        return driver;
    }

    ExtentReports report;
    ExtentTest test;


    @BeforeClass
    public void setUp() throws Exception {
        report = ExtentFactory.getInstance();
        test = report.startTest("Expedia");
        PropertyConfigurator.configure("log4j.properties");
        driver = new FirefoxDriver();
        test.log(LogStatus.INFO, "Browser started");

        baseUrl = "https://www.expedia.com";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Browser maximized");

        driver.get(baseUrl + "/");
        test.log(LogStatus.INFO, "Web Application added");

        searchPage = new SearchPageFactory(driver);

    }

    @Test(priority = 0)
    public void hotelBasicInfo() throws InterruptedException {
        searchPage.clickHotelTab();
        Thread.sleep(2000);
        log.info("This is test 1");
        searchPage.setHotelDestination("Empire");
        test.log(LogStatus.INFO, "Empire added");

        searchPage.setHotelCheckin("12/12/2016");
        searchPage.setHotelCheckout("");
        searchPage.setHotelCheckout("24/12/2016");
        test.log(LogStatus.INFO, "Dates added");

        searchPage.clickHotelTab();

    }

    @Test(priority = 1)
    public void hotelAdvansedInfo() throws InterruptedException {
        Thread.sleep(2000);
        log.info("This is test 2");
        searchPage.clickAddFlight();
        searchPage.setFlight("Dallas");
        test.log(LogStatus.INFO, "Dallas added");

        //searchPage.clickSearchButton();
    }

    @AfterMethod
    public void afterTest() throws IOException {
        GetScreenshot.getScreenshot(driver);
        log.info("Captured");
    }

    @AfterClass
    public void tearDown() throws Exception {
        test.log(LogStatus.PASS, "Test passed");

        Thread.sleep(8000);
        //log.info("Thats good enohg!");
        driver.quit();
        report.endTest(test);
        report.flush();
    }
}