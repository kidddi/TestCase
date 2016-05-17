package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FirstTestCase {
    private WebDriver driver;
    private String baseUrl;


    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://letskodeit.teachable.com/pages/practice";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "/");
        System.out.println("Site opened");

    }

    @Test
    public void testFirstTestCase() throws Exception {

        WebElement openWindow = driver.findElement(By.id("openwindow"));
        openWindow.click();

        String parentHandle = driver.getWindowHandle();
        System.out.println("Parent handle: " + parentHandle);

        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles){
            System.out.println("Current handle: " + handle);

            if (!handle.equals(parentHandle)){
                driver.switchTo().window(handle);

            }

        }

        WebElement searchElement = driver.findElement(By.id("search-courses"));
        searchElement.sendKeys("python");
        System.out.println("Write Python");

        Thread.sleep(2000);
        driver.close();

        driver.switchTo().window(parentHandle);
        Thread.sleep(2000);

        driver.findElement(By.id("name")).sendKeys("BUY");



    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        //driver.quit();

    }

}
