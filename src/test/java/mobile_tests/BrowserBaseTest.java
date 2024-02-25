package mobile_tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.WaitUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static utils.SSUtils.getDateTimeStamp;
import static utils.SSUtils.takeScreenShot;

public class BrowserBaseTest {


    AndroidDriver driver;
    AppiumDriverLocalService service;

    @BeforeClass
    public void setup() throws MalformedURLException, InterruptedException {

        service = new AppiumServiceBuilder().withAppiumJS
                        (new File("C:\\Users\\91721\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(60)).build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 4");

        options.setChromedriverExecutable("C:\\Training2023\\Workspaces\\Appium\\Appium\\src\\test\\resources\\chromedriver\\chromedriver.exe");

        //Additional setup of capabilities
        options.setCapability("browserName", "Chrome");


        String appiumServerURL = "http://127.0.0.1:4723";
        driver = new AndroidDriver(new URL(appiumServerURL), options);
        WaitUtils.implicitWaitCustom(driver, 10);
        WaitUtils.hardSleep(5);
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {
        WaitUtils.hardSleep(5);
        String dateTimeStamp = getDateTimeStamp() + "_endTest.png";

        takeScreenShot(driver, "./screenshots/" + dateTimeStamp);

    }


}
