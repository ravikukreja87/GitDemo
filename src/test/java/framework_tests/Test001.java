package framework_tests;

import ecomm.BaseEcomApp;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Test001 extends BaseEcomApp {


    @Test(dataProvider = "getData")
    public void executeTest001(Map<String, String> input) throws InterruptedException {

        formPage.setName(input.get("name"));
        formPage.setCountryDropDown(input.get("country"));
        formPage.setGender(input.get("gender"));
        ProductsPage productsPage = formPage.submitForm();
        productsPage.addToCart();
        CartPage cartPage = productsPage.clickOnCartIcon();
        String actualCartTitle = cartPage.validateCartTitle();
        String expectedCartTitle = "Cart";
        Assert.assertEquals(actualCartTitle, expectedCartTitle);
    }

    @AfterMethod
    public void resetApp() throws InterruptedException {
        formPage.pressBackKey();
        formPage.pressBackKey();
    }


    @DataProvider
    public Object[][] getData() throws IOException {
        String fileNameWithLocation = System.getProperty("user.dir") + "/src/test/resources/data.json";
        List<Map<String, String>> data = getJsonData(fileNameWithLocation);
        return new Object[][]{
                {data.get(0)},
                {data.get(1)}
        };
    }


}
