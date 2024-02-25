package ecomm;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppiumUtils;
import utils.ExtentReporterNG;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

import static utils.SSUtils.takeScreenShotAsBase64;

public class AddCartTest extends BaseEcomApp {


    private String testStatus = "Passed";
    private String testReason;

    @Test

    public void loginEcomAppTest() throws InterruptedException {
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

        //Scroll to country Australia in home screen drop down
        String countryName = "Australia";
        AppiumUtils.scrollToElementAndClickOnIt(countryName,
                "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + countryName + "\"]",
                driver);

        //Click on Submit Button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Scroll to product Jordan 6 Rings
        String inputProductName = "Jordan 6 Rings";
        AppiumUtils.scrollToElementByVisibleText(inputProductName, driver);

        //Add product to cart
        List<WebElement> addToCartButtons = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
        int countAddToCart = addToCartButtons.size();

        for (int i = 0; i < countAddToCart; i++) {
            String actualProductName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (actualProductName.equals(inputProductName)) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            } else {
                System.out.println("Product " + inputProductName + " not found on screen!");

            }
            if (i >= countAddToCart - 1)
                Assert.fail("Product " + inputProductName + " not found on screen!");
        }

        //Click on Cart Icon
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WaitUtils.hardSleep(3);
        //Explicit wait for Cart label to load attribute named 'text' with value 'Cart'
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        //Assert label Cart on Cart Screen
        String actualResultCartLabel = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getAttribute("text");
        String expectedResultCartLabel = "Cart";

/*        if(actualResultCartLabel.equals(expectedResultCartLabel+"s")){
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
            testStatus = "Failed";
            testReason = "Cart Label Not Found";
        }*/

        Assert.assertEquals(actualResultCartLabel, expectedResultCartLabel);

        //Validate cost of product against total cart purchase value
        String actualResulProductPriceValue = driver.findElement(By.id("com.androidsample.generalstore:id/productPrice")).getAttribute("text");
        String actualResultTotalPurchaseAmountValue = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getAttribute("text");
        Assert.assertEquals(actualResulProductPriceValue, actualResultTotalPurchaseAmountValue.replaceAll(" ", ""));

        //Long Click on Terms and Conditions popup
        WebElement termsAndConditionsLink = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) termsAndConditionsLink).getId(),
                "duration", "2000"
        ));


        //Click on close button of T&C
        driver.findElement(By.id("android:id/button1")).click();

        //Click on Checkbox
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();


        //Click on Proceed Button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        WaitUtils.hardSleep(5);

//        generateReport();


    }

    private void generateReport() {
        System.out.println("Entering to generate Extent Report");
        ExtentReports extent = ExtentReporterNG.getReportObject("TEST");
        ExtentTest test = extent.createTest("Native Test One");
        test.assignAuthor("Jon");
        test.assignCategory("Native");
        test.addScreenCaptureFromBase64String(takeScreenShotAsBase64(driver));
        if(testStatus.equalsIgnoreCase("failed")){
            test.fail(testReason);
        }
        extent.flush();
        System.out.println("Extent Report Generated");
    }
}
