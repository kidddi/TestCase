package ua.olx.login;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import extentreports.ExtentFactory;
import methods.GetScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageclasses.ProfilePage;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by energetic on 25.05.2016.
 */
public class TestNG_TestSuite {
    private WebDriver driver;
    private String baseUrl;
    ProfilePage profilePage;

    ExtentReports report;
    ExtentTest test;

    @BeforeClass
    public void setUp() throws Exception {
        report = ExtentFactory.getInstance();
        test = report.startTest("Verify");
        driver = new FirefoxDriver();
        test.log(LogStatus.INFO, "Browser started");

        baseUrl = "https://www.olx.ua";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Browser maximized");

        driver.get(baseUrl + "/");
        test.log(LogStatus.INFO, "Web Application added");

        profilePage = new ProfilePage(driver);
    }

    @Test
    public void test1() throws Exception {

        profilePage.clickLoginLink();
        test.log(LogStatus.INFO, "Logn link clicked");

        profilePage.setUserEmail("kidyuk@mail.ru");
        test.log(LogStatus.INFO, "UserEmail is set");

        profilePage.setUserPass("12345");
        test.log(LogStatus.INFO, "UserPass is set");

        profilePage.clickLiginButton();
        test.log(LogStatus.INFO, "LoginButton clicked");
        Thread.sleep(2000);

        WebElement welcomeText = null;

        try{
        welcomeText = driver.findElement(By.xpath("//span[@class='link inlblk']//strong[text()='kidyuk']"));
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(welcomeText != null);
        test.log(LogStatus.PASS, "Verified Welcome test");
    }

    @Test
    public void test2() throws Exception {

        profilePage.clearAll();
        profilePage.setUserEmail("kidyuk@mail.ru");
        test.log(LogStatus.INFO, "UserEmail is set");

        profilePage.setUserPass("123456");
        test.log(LogStatus.INFO, "UserPass is set");

        profilePage.clickLiginButton();
        test.log(LogStatus.INFO, "LoginButton clicked");
        Thread.sleep(2000);

        WebElement welcomeText = null;

        try{
            welcomeText = driver.findElement(By.xpath("//span[@class='link inlblk']//strong[text()='kidyuk']"));
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(welcomeText != null);
        test.log(LogStatus.PASS, "Verified Welcome test");


    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE){
            String path = GetScreenshot.getScreenshot(driver, result.getName());
            String imagePath = test.addScreenCapture(path);
            test.log(LogStatus.FAIL, "Verify Welcome Test Failed", imagePath);
        }


    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        report.endTest(test);
        report.flush();

        Thread.sleep(2000);
        driver.quit();
    }
}
