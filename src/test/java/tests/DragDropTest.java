package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragDropTest extends BaseTest {



    @Test
    public void dragDropTest() {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement elementToDrag = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) elementToDrag).getId(),
                "endX", 613,
                "endY", 493
        ));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String expectedResult = "Dropped!";
        String actualResult = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

}
