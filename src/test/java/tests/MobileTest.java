package tests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static utils.WaitUtils.hardSleep;
import static utils.WaitUtils.implicitWaitCustom;
import static utils.WaitUtils.disableImplicitWait;


public class MobileTest extends BaseTest {

    public void mobileTest() throws InterruptedException {
        implicitWaitCustom(driver, 10);

        //Locator strategy by accessibility ID

        driver.findElement(AppiumBy.accessibilityId("Preference")).click();

/*        String filterationText = "new UiSelector().clickable('true').className('PreferenceClass')";
        driver.findElement(AppiumBy.androidUIAutomator(filterationText));*/




        //Locator strategy by Xpath
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        hardSleep(2);

        //Click Operation
        driver.findElement(By.id("android:id/checkbox")).click();
        hardSleep(1);
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]")).click();
        disableImplicitWait(driver);

        //Extracted text from web element
        String actualAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        String expectedAlertTitle = "WiFi settings";


        //Send keys to enter text
        driver.findElement(By.id("android:id/edit")).sendKeys("21012024");
        driver.findElement(By.id("android:id/button1")).click();

        String expectedTitle = "Example preference dependency";
        String actualTitle = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Example preference dependency']")).getText();
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualAlertTitle, expectedAlertTitle);
        variableFromParent = "Test";
        System.out.println("actualTitle = " + actualTitle);
    }



    @Test
    public void mobileTestAuto() throws InterruptedException {

        implicitWaitCustom(driver, 5);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("android:id/checkbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]")).click();

        String actualAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        String expectedAlertTitle = "WiFi settings";

        driver.findElement(By.id("android:id/edit")).sendKeys("21012024");
        driver.findElement(By.id("android:id/button1")).click();

        String expectedTitle = "Example preference dependency";
        String actualTitle = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Example preference dependency']")).getText();
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualAlertTitle, expectedAlertTitle);

    }

}
