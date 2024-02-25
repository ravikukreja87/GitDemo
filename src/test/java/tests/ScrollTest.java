package tests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ScrollTest extends  BaseTest{

    @Test
    public void scrollTest(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        //Passed as an argument to another method
        String uiAutomaterText = "new UiScrollable(new UiSelector()).scrollIntoView(text('WebView'));";
        WebElement webViewElement = driver.findElement(AppiumBy.androidUIAutomator(uiAutomaterText));
        webViewElement.click();
    }
}
