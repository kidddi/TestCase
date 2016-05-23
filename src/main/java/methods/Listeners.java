package methods;

import selenium.TestngCase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import sun.rmi.runtime.Log;

import java.io.IOException;

/**
 * Created by energetic on 23.05.2016.
 */
public class Listeners implements ITestListener, ISuiteListener, IInvokedMethodListener{

    //Logger log = Logger.getLogger(String.valueOf(Listeners.class));

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        String textMsg = "beforeInvocation - about to begin executing the following method :" + returnMethodName(iInvokedMethod.getTestMethod());
        Reporter.log(textMsg, true);
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        String testMsg = "afterInvocation - Completeed execution following method :" + returnMethodName(iInvokedMethod.getTestMethod());
        Reporter.log(testMsg, true);
    }

    public void onStart(ISuite iSuite) {
    Reporter.log("onStart Suite - about to begin" + iSuite.getName(), true);
    }

    public void onFinish(ISuite iSuite) {
    Reporter.log("OnFinish Suite - about to finish" + iSuite.getName(), true);
    }

    public void onTestStart(ITestResult iTestResult) {
        Reporter.log("onTestStart - executes before @Test start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        Object currentClass = iTestResult.getInstance();
        WebDriver driver = ((selenium.TestngCase)currentClass).getDriver();

        try {
            GetScreenshot.getScreenshot(driver);
        }catch (IOException e){
            e.printStackTrace();
        }
        printTestResults(iTestResult);
    }

    public void onTestFailure(ITestResult iTestResult) {
        Object currentClass = iTestResult.getInstance();
        WebDriver driver = ((selenium.TestngCase)currentClass).getDriver();

        try {
            GetScreenshot.getScreenshot(driver);
        }catch (IOException e){
            e.printStackTrace();
        }
        printTestResults(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        printTestResults(iTestResult);
    }
    private void printTestResults (ITestResult result){
        Reporter.log("Test Method resides in " + result.getTestClass().getName());

        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failure";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
        }
        Reporter.log("Test status: " + status, true);
    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        Reporter.log("onStart Test - about to start executing test set" + iTestContext.getName(), true);
    }

    public void onFinish(ITestContext iTestContext) {
        Reporter.log("onFinish Test - Completed executing Test set" + iTestContext.getName(),true);
    }
    private String returnMethodName (ITestNGMethod method){
        return method.getRealClass().getSimpleName() + " " + method.getMethodName();
    }
}
