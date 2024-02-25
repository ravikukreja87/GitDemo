package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AppiumUtils;
import utils.WaitUtils;

public class FormPage {

    AndroidDriver driver;
    public FormPage(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void setName(String name) throws InterruptedException {
        nameText.clear();
        nameText.sendKeys(name);
        WaitUtils.hardSleep(2);
    }

    public void setGender(String gender) throws InterruptedException {
        if(gender.equals("female")){
            femaleRadioButton.click();
        } else {
            maleRadioButton.click();
        }
        WaitUtils.hardSleep(2);
    }

    public void setCountryDropDown(String countryName) throws InterruptedException {
        countryDropDown.click();
        AppiumUtils.scrollToElementAndClickOnIt(countryName,
                "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + countryName + "\"]",
                driver);
        WaitUtils.hardSleep(2);
    }

    public ProductsPage submitForm() throws InterruptedException {
        submitButton.click();
        WaitUtils.hardSleep(2);
        return new ProductsPage(driver);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameText;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleRadioButton;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
    private WebElement maleRadioButton;

    @AndroidFindBy(id="android:id/text1")
    private WebElement countryDropDown;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement submitButton;

    public void pressBackKey() throws InterruptedException {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        WaitUtils.hardSleep(1);
    }
}
