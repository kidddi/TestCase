package selenium;

import methods.GetScreenshot;
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

    @BeforeClass
    public void setUp() throws Exception {
        PropertyConfigurator.configure("src\\main\\java\\log4j.properties");
        driver = new FirefoxDriver();
        baseUrl = "https://www.expedia.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "/");
        System.out.println("Site opened");
        searchPage = new SearchPageFactory(driver);
        searchPage.clickHotelTab();
    }

   @Test
    public void hotelBasicInfo() throws InterruptedException {
        Thread.sleep(2000);
        log.info("This is test 1");
        searchPage.setHotelDestination("Empire");
        searchPage.setHotelCheckin("12.12.2016");
        searchPage.setHotelCheckout("");
        searchPage.setHotelCheckout("24.12.2016");
        Thread.sleep(2000);
    }
    @Test
    public void hotelAdvansedInfo(){
        log.info("This is test 2");
        searchPage.clickAddFlight();
        searchPage.setFlight("Dallas");
    }

    @AfterMethod
    public void afterTest() throws IOException {
        GetScreenshot.getScreenshot(driver);
        log.info("Captured");
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        log.info("Thats good enohg!");
    }
}