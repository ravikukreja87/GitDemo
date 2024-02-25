package ecomm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FormPage;
import utils.WaitUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static utils.SSUtils.getDateTimeStamp;
import static utils.SSUtils.takeScreenShot;

public class BaseEcomApp {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    public FormPage formPage;

    @BeforeClass
    public void setup() throws MalformedURLException, InterruptedException {

        service = new AppiumServiceBuilder().withAppiumJS
                        (new File("C:\\Users\\91721\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(60)).build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 4");
        options.setApp("C:\\Training2023\\Tools\\Appium\\APKFiles\\resources\\General-Store.apk");
        options.setChromedriverExecutable("C:\\Training2023\\Workspaces\\Appium\\Appium\\src\\test\\resources\\chromedriver\\chromedriver.exe");
        String appiumServerURL = "http://127.0.0.1:4723";
        driver = new AndroidDriver(new URL(appiumServerURL), options);
        WaitUtils.implicitWaitCustom(driver, 10);
        WaitUtils.hardSleep(5);
        formPage = new FormPage(driver);
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {
        WaitUtils.hardSleep(5);
        String dateTimeStamp = getDateTimeStamp() + "_endTest.png";

        takeScreenShot(driver, "./screenshots/" + dateTimeStamp);
        driver.quit();
        service.stop();
    }


    public List<Map<String,String>> getJsonData(String fileNameWithLocation) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(fileNameWithLocation), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<Map<String, String>>>() {

        });

        return data;

    }
}