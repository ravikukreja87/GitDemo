package utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;


public class AppiumUtils {



    public static void scrollToElementAndClickOnIt(String countryName, String xpath, WebDriver driver) {
        scrollToElementByVisibleText(countryName, driver);
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void scrollToElementByVisibleText(String countryName, WebDriver driver) {
        //Scroll down to element name we want to choose //Scroll down to a value as visible text
        String scrollToElementScript = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollToElementScript));
    }

}
