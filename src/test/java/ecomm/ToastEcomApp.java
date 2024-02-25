package ecomm;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

public class ToastEcomApp extends BaseEcomApp {


    @Test
    public void toastMessageTest() throws InterruptedException {


        //Click on Submit Button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastXpath = "(//android.widget.Toast)[1]";
        String toastMessage= driver.findElement(By.xpath(toastXpath)).getAttribute("name");
        System.out.println("toastMessage = " + toastMessage);
        String expectedResult = "Please enter your name";

        Assert.assertEquals(toastMessage, expectedResult);

    }
}
