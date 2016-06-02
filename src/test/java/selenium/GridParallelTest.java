package selenium;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageclasses.TestSuiteBase;

/**
 * Created by energetic on 31.05.2016.
 */
public class GridParallelTest extends TestSuiteBase{
    @BeforeMethod
    public void setUp() throws Exception {


    }

    @Test
    public void test() throws Exception {
        search.clickHotelTab();
        Thread.sleep(2000);
        //log.info("This is test 1");
        search.setHotelDestination("Empire");
        //test.log(LogStatus.INFO, "Empire added");

        search.setHotelCheckin("12/12/2016");
        search.setHotelCheckout("");
        search.setHotelCheckout("24/12/2016");
        //test.log(LogStatus.INFO, "Dates added");

    }

    @AfterMethod
    public void tearDown() throws Exception {


    }
}
