import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import swiatRoweru.MainPage;
import swiatRoweru.ProductPage;
import swiatRoweru.ResultPage;

import static org.testng.Assert.assertEquals;

public class SRTest extends BaseTest{


    @BeforeClass
    public void prepareSR() {
        MainPage.navigateTo(driver);
    }

    @Test
    public void basketContentVerify() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.navigationBar.search("Revolt");

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickByName("Revolt Advanced 1");

        ProductPage productPage = new ProductPage(driver);


        double price = productPage.getPrice();

        productPage.clickAddToCart();
        double basketPrice =  productPage.navigationBar.cartPreviewGetPrice();
        assertEquals(basketPrice, price);


    }


}
