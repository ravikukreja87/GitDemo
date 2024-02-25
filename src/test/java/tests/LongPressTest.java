package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.SSUtils;
import utils.WaitUtils;

import java.io.IOException;

public class LongPressTest extends BaseTest {

    @Test
    public void longPressTest() throws InterruptedException, IOException {

        WaitUtils.implicitWaitCustom(driver, 3);

        //accessibility id
        //Views
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        WaitUtils.hardSleep(2);
        //android.widget.TextView[@content-desc="Expandable Lists"]
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();

        WaitUtils.hardSleep(2);
        //accessibility id
        //1. Custom Adapter
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        SSUtils.takeScreenShot(driver, "./screenshots/"+SSUtils.getDateTimeStamp()+"_customAdapter.png");

        WaitUtils.hardSleep(2);
        //android.widget.TextView[@text="People Names"]
        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
/*        By locator = By.xpath("//android.widget.TextView[@text=\"People Names\"]");

        //Long Press Code
        AndroidTouchAction touchAction = new AndroidTouchAction(driver);
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element))).perform();


        //Another way to long press
        TouchAction action = new TouchAction;
        action.longPress(element).release().perform();*/

        WaitUtils.hardSleep(2);
        //Long Press Using JavaScript
        String javaScriptCode = "mobile: longClickGesture";

        //Second parameter will need id of element
        ((JavascriptExecutor) driver).executeScript(javaScriptCode, ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));

        WaitUtils.hardSleep(2);
        //Add an assertion to check if Long Press was success
        String actual = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample menu\"]")).getText();
        String expected = "Sample me";


        Assert.assertEquals(actual, expected);


        /*((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                                                             "locator", locator));

        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                                                                                    "x", 1024,
                                                                                    "y",200));
*/

    }



}
