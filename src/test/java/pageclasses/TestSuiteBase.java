package pageclasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by energetic on 31.05.2016.
 */
public class TestSuiteBase {
    protected WebDriver driver;
    protected SearchPageFactory search;
    static Logger log = Logger.getLogger(String.valueOf(TestSuiteBase.class));

    @Parameters({ "platform", "browser", "version", "url" })
    @BeforeClass(alwaysRun = true)
    public void setup(String platform, String browser, String version, String url) throws MalformedURLException {

        PropertyConfigurator.configure("log4j.properties");
        driver = getDriverInstance(platform, browser, version, url);
        search = PageFactory.initElements(driver, SearchPageFactory.class);
    }

    public static WebDriver getDriverInstance(String platform, String browser, String version, String url) throws MalformedURLException {
        String nodeUrl = "http://10.0.2.15:5555/wd/hub";
        WebDriver driver = null;
        DesiredCapabilities cap = new DesiredCapabilities();

        if (platform.equalsIgnoreCase("Window")){
            cap.setPlatform(Platform.WINDOWS);
        }
        if (platform.equalsIgnoreCase("Mac")){
            cap.setPlatform(Platform.MAC);
        }
        if (browser.equalsIgnoreCase("Chrome")){
            cap = DesiredCapabilities.chrome();
            log.info("CHROME");
        }
        if (browser.equalsIgnoreCase("firefox")){
            cap = DesiredCapabilities.firefox();
            log.info("FIREFOX");

        }
        cap.setVersion(version);
        System.out.println(cap.getVersion());
        driver = new RemoteWebDriver(new URL(nodeUrl), cap);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(url);
        return driver;
    }
}
