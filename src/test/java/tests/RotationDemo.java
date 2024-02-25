package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.WaitUtils.*;

public class RotationDemo extends BaseTest {


    @Test
    public void rotateTest() throws InterruptedException {
        implicitWaitCustom(driver, 10);
        hardSleep(2);
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        hardSleep(2);

        //Locator strategy by Xpath
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        hardSleep(3);

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        hardSleep(3);
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        hardSleep(10);


        //Click Operation
        driver.findElement(By.id("android:id/checkbox")).click();
        hardSleep(1);
/*        DeviceRotation landScape = new DeviceRotation(0, 0, 90);
        driver.rotate(landScape);*/


        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]")).click();
        disableImplicitWait(driver);

        //Extracted text from web element
        String actualAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();



        String expectedAlertTitle = "WiFi settings";
        driver.setClipboardText("21012024");

        //Send keys to enter text
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElement(By.id("android:id/button1")).click();

        String expectedTitle = "Example preference dependency";
        hardSleep(10);
        String actualTitle = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Example preference dependency']")).getText();
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualAlertTitle, expectedAlertTitle);
        variableFromParent = "Test";
        System.out.println("actualTitle = " + actualTitle);
        hardSleep(10);
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        hardSleep(10);

    }

}
