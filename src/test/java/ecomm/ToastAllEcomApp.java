package ecomm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ToastAllEcomApp extends BaseEcomApp {


    @Test
    public void toastAllMessagesTest() throws InterruptedException {


        //Click on Submit Button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastXpath = "//android.widget.Toast";

        List<WebElement> toasts = driver.findElements(By.xpath(toastXpath));

        if(toasts.size() == 0){
            Assert.fail("No Toast Messages Returned");
        }

        for (int i = 0; i < toasts.size(); i++) {
            WebElement toast = driver.findElement(By.xpath(toastXpath + "[" + (i + 1) + "]"));
            String expectedResult = "Please enter your name";
            String toastMessage = toast.getAttribute("name");
            System.out.println("toastMessage: " + i + " = " + toastMessage);
            if(toastMessage.equals(expectedResult)){
                Assert.assertEquals(toastMessage, expectedResult);
                break;
            }
        }
    }
}
