package mobile_tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import utils.ExtentReporterNG;

import static utils.SSUtils.takeScreenShotAsBase64;

public class ChromeTest extends BrowserBaseTest {


    @Test
    public void executeChromeTest(){

        driver.get("https://www.google.co.in/");
        String title = driver.getTitle();
        System.out.println("title = " + title);

        driver.findElement(By.name("q")).sendKeys("Selenium Appium Browser Tests");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        String titleAfterSearch = driver.getTitle();
        System.out.println("titleAfterSearch = " + titleAfterSearch);

        generateReport();


    }

    private void generateReport() {

        ExtentReports extent = ExtentReporterNG.getReportObject("TEST");
        ExtentTest test = extent.createTest("Mobile Test One");
        test.assignAuthor("Jon");
        test.assignCategory("Mobile");
        test.addScreenCaptureFromBase64String(takeScreenShotAsBase64(driver));

    }
}
