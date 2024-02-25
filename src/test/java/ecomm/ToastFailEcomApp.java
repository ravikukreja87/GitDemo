package ecomm;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.util.List;

public class ToastFailEcomApp extends BaseEcomApp {


    @Test
    public void toastFailTest() throws InterruptedException {
        //Enter Name
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Jon");
        WaitUtils.hardSleep(2);

        //Choose gender as Female from Radio Button Group
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        WaitUtils.hardSleep(2);

        //Choose gender as Male from Radio Button Group
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        WaitUtils.hardSleep(2);

        //Click operation on Drop Down
        driver.findElement(By.id("android:id/text1")).click();
        WaitUtils.hardSleep(2);

        //Scroll down to country we want to choose //Scroll down to a value
        String countryName = "Australia";
        String scrollToElementScript = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));";
        driver.findElement(AppiumBy.androidUIAutomator(scrollToElementScript));
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + countryName + "\"]")).click();

        //Click on Submit Button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastXpath = "//android.widget.Toast";

        List<WebElement> toasts = driver.findElements(By.xpath(toastXpath));
        System.out.println("toasts count = " + toasts.size());

        if (toasts.size() == 0) {
            Assert.fail("No Toast Messages Returned");
        } else {
            for (int i = 0; i < toasts.size(); i++) {
                WebElement toast = driver.findElement(By.xpath(toastXpath + "[" + (i + 1) + "]"));
                String expectedResult = "Please enter your name";
                String toastMessage = toast.getAttribute("name");
                System.out.println("toastMessage: " + i + " = " + toastMessage);
                if (toastMessage.equals(expectedResult)) {
                    Assert.assertEquals(toastMessage, expectedResult);
                    break;
                }
            }
        }

    }
}
