package ua.olx.login;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import datadriven.ExcelUtilities;
import extentreports.ExtentFactory;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageclasses.ProfilePage;
import utilites.Constans;
import utilites.GetScreenshot;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by energetic on 25.05.2016.
 */
public class TestNG_TestSuite {
    private WebDriver driver;
    private String baseUrl;
    ProfilePage profilePage;

    static Logger log = Logger.getLogger(String.valueOf(TestNG_TestSuite.class));

    ExtentReports report;
    ExtentTest test;
    ExcelUtilities exR;
    ExcelUtilities exW;

    StopWatch watch = new StopWatch();

    @BeforeClass
    public void setUp() throws Exception {
        PropertyConfigurator.configure("log4j.properties");
        report = ExtentFactory.getInstance();
        test = report.startTest("Verify");
        driver = new FirefoxDriver();
        test.log(LogStatus.INFO, "Browser started");

        baseUrl = Constans.URL;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Browser maximized");


        test.log(LogStatus.INFO, "Web Application added");

        profilePage = new ProfilePage(driver);
        exR = new ExcelUtilities();
        exW = new ExcelUtilities();
        exR.setExcelFile(Constans.ExcelReadPath, "Sheet1");
        exW.setExcelFile(Constans.ExcelWritePath, "Sheet1_write");
        driver.get(baseUrl + "/");

        //ExcelUtilities.setExcelFile(Constans.ExcelReadPath, "Sheet1");
        //ExcelUtilities.setExcelFile(Constans.ExcelWritePath, "Sheet1");

    }

    @DataProvider(name = "loginData")
    public Object[][] dataProvider() {
        Object[][] testData = exR.getTestData("Invalid_Login");
        return testData;
    }

//    @Test
//    public void test1() throws Exception {
//
//        profilePage.clickLoginLink();
//        test.log(LogStatus.INFO, "Logn link clicked");
//
//        profilePage.setUserEmail("kidyuk@mail.ru");
//        test.log(LogStatus.INFO, "UserEmail is set");
//
//        profilePage.setUserPass("12345");
//        test.log(LogStatus.INFO, "UserPass is set");
//
//        profilePage.clickLiginButton();
//        test.log(LogStatus.INFO, "LoginButton clicked");
//        Thread.sleep(2000);
//
//        WebElement welcomeText = null;
//        try {
//            welcomeText = driver.findElement(By.xpath("//span[@class='link inlblk']//strong[text()='kidyuk']"));
//        } catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//
//
//        Assert.assertTrue(welcomeText != null);
//        test.log(LogStatus.PASS, "Verified Welcome test");
//
//
//    }
//
//    @Test
//    public void test2() throws Exception {
//
//        profilePage.clearAll();
//        profilePage.setUserEmail("kidyuk@mail.ru");
//        test.log(LogStatus.INFO, "UserEmail is set");
//
//        profilePage.setUserPass("123456");
//        test.log(LogStatus.INFO, "UserPass is set");
//
//        profilePage.clickLiginButton();
//        test.log(LogStatus.INFO, "LoginButton clicked");
//        Thread.sleep(2000);
//
//        WebElement welcomeText = null;
//
//        try {
//            welcomeText = driver.findElement(By.xpath("//span[@class='link inlblk']//strong[text()='kidyuk']"));
//        } catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//        }
//        Assert.assertTrue(welcomeText != null);
//        test.log(LogStatus.PASS, "Verified Welcome test");
//
//
//    }

    @Test(dataProvider = "loginData")
    public void testExcelData(String username, String password) throws Exception {
        watch.start();
        profilePage.clickLoginLink();
        test.log(LogStatus.INFO, "Logn link clicked");

        profilePage.setUserEmail(username);
        test.log(LogStatus.INFO, "UserEmail is set");

        profilePage.setUserPass(password);
        test.log(LogStatus.INFO, "UserPass is set");

        profilePage.clickLiginButton();
        test.log(LogStatus.INFO, "LoginButton clicked");
        Thread.sleep(2000);


        WebElement welcomeText = null;
        try {
            welcomeText = driver.findElement(By.xpath("//span[@class='link inlblk']//strong[text()='kidyuk']"));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        double seconds = (double) watch.getTime() / 1000;
        watch.reset();
        //watch.start();
        log.info("time: " + seconds + " seconds");
        exW.setCellData(password, 1, 1);
        exW.setCellData(seconds, 1, 2);

        Assert.assertFalse(welcomeText != null);
        test.log(LogStatus.PASS, "Verified Welcome test");

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {


        if (result.getStatus() == ITestResult.FAILURE) {
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
