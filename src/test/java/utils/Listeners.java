package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ecomm.BaseEcomApp;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static utils.SSUtils.takeScreenShotAsBase64;

public class Listeners extends BaseEcomApp implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject("Ecomm");

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("Starting TestNG Listener onTestStart");
         test = extent.createTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("Starting TestNG Listener onTestSuccess");
        test.log(Status.PASS, "Test Passed " + result.getMethod().getMethodName());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            service = (AppiumDriverLocalService) result.getTestClass().getRealClass().getField("service").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Starting TestNG Listener onTestFailure");
        ITestListener.super.onTestFailure(result);
        test.fail("Test Failed " + result.getMethod().getMethodName() );
        test.fail(result.getThrowable());
        test.addScreenCaptureFromBase64String(takeScreenShotAsBase64(driver));
        test.log(Status.FAIL, "FAILED");

    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("Starting TestNG Listener onFinish");
        extent.flush();
        driver.quit();

        service.stop();
    }
}
