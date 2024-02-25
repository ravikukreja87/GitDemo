package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


/**
 * This class contains Wait utilities for this project
 *
 */
public class WaitUtils {


    /**
     * This method is used to wait for defined duration (number of seconds)
     * @param duration
     * @throws InterruptedException
     */
    public  static void hardSleep(long durationInSeconds) throws InterruptedException {
        Thread.sleep(durationInSeconds * 1000);
    }

    public static void implicitWaitCustom(WebDriver driver, long unitToWaitInSeconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(unitToWaitInSeconds));
    }


    /**
     * Disables implicit wait by setting duration of wait to 0 value
     * @param driver
     */
    public static void disableImplicitWait(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    public static void explicitWaitOnAttributeValue(String toolbarTitleLocator, String attributeId, String attributeValue, AndroidDriver driver, long timeOutDuration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutDuration));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(toolbarTitleLocator)), attributeId, attributeValue));
    }
}
