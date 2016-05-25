package extentreports;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * Created by energetic on 25.05.2016.
 */
public class ExtentFactory {
    public static ExtentReports getInstance(){
        ExtentReports extent;
        String path = "D:\\1\\report.html";
        extent = new ExtentReports(path, false);
        return extent;
    }
}
