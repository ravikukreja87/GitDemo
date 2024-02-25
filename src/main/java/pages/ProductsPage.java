package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    AndroidDriver driver;
    public ProductsPage(AndroidDriver driver){
//        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    List<WebElement> products;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
    WebElement addToCart;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    WebElement cartIcon;

    public void addToCart(){
        addToCart.click();
    }

    public CartPage clickOnCartIcon() {
        cartIcon.click();
        return new CartPage(driver);
    }
}
