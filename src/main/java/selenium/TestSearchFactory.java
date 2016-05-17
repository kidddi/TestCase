package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageclasses.SearchPageFactory;

import java.util.concurrent.TimeUnit;

public class TestSearchFactory {
    private WebDriver driver;
    private String baseUrl;
    SearchPageFactory searchPage;



    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.expedia.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "/");
        System.out.println("Site opened");
        searchPage = new SearchPageFactory(driver);

    }

    @Test
    public void test() throws Exception {

        searchPage.clickHotelTab();
        Thread.sleep(2000);
        searchPage.setHotelDestination("Empire");
        searchPage.setHotelCheckin("12.12.2016");
        searchPage.setHotelCheckout("24.12.2016");
        searchPage.clickSearchButton();
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        //driver.quit();

    }

}
