package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
//        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    WebElement toolBarTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    WebElement productPrice;


    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    WebElement proceedButton;


    public String validateCartTitle() {
        return toolBarTitle.getText();


    }
}
