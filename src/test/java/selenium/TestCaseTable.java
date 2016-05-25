package selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCaseTable {
    private WebDriver driver;
    private String baseUrl;


    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://dhtmlx.com/docs/products/dhtmlxGrid";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl + "/");
        System.out.println("Site opened");

    }

    @Test
    public void testFirstTestCase() throws Exception {


        String book = "The Green Mile";
        String author = "Stephen King";
        String authorExpected = getAuthor(book);

        System.out.println("The author of book is: " + authorExpected);
        Assert.assertTrue(author.equals(authorExpected));
    }

    public String getAuthor(String book) {

        int index = 0;

        WebElement element = driver.findElement(By.xpath("//table[@class='obj row20px']"));
        List<WebElement> rows = element.findElements(By.xpath("//tr[contains(@class, 'skyblue')]"));

        int size = rows.size();
        System.out.println("The size is " + size);
        for (int i = 0; i < size; i++) {
            if (rows.get(i).getText().contains(book)){
                index = i;
                System.out.println("The index is " + index);
                System.out.println("The text is " + rows.get(i).getText());

            }
        }
        WebElement gauthor = rows.get(index).findElements(By.tagName("td")).get(2);

        return gauthor.getText();
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        //driver.quit();

    }

}
