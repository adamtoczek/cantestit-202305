package swiatRoweru;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class NavigationBar {
    WebDriver driver;

    public NavigationBar(WebDriver driver) {
        this.driver = driver;
    }


    public void search(String text) {
        driver.findElement(By.cssSelector("#searchformicon")).click();
        driver.findElement(By.cssSelector("#form_search_inut")).sendKeys(text+ Keys.ENTER);
    }

    public double cartPreviewGetPrice() {
        Actions actions = new Actions(driver);
        WebElement cartLink = driver.findElement(By.cssSelector("#basket_link"));
        actions.moveToElement(cartLink).build().perform();

        WebElement cart = driver.findElement(By.cssSelector("#basket_top"));

        String[] cartRows = cart.getText().split("\n");
        String priceStr = cartRows[cartRows.length-1].replace("razem : ","").replace("PLN", "").replace(" ", "");
        return Double.parseDouble(priceStr);
    }
}
