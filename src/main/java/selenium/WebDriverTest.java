package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by energetic on 12.05.2016.
 */
public class WebDriverTest {


    public static void main(String... args) throws InterruptedException {

        String baseURL = "http://demostore.x-cart.com/avengers-age-of-ultron-fabrikations-plush.html";

        WebDriver driver;
        driver = new FirefoxDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@id='amount16']")).clear();
        driver.findElement(By.xpath("//input[@id='amount16']")).sendKeys("5");

        driver.findElement(By.xpath("//div[@class='product-details-info']//button[contains(@class,'add2cart')]")).click();

    }


}
