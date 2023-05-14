package swiatRoweru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public double getPrice() {
        double price = Double.parseDouble(driver.findElement(By.cssSelector("span:not(.cena_stara)>.cena_value")).getText());
        return price;
    }

    public void clickAddToCart() {
        WebElement overlay = driver.findElement(By.cssSelector("#fancybox-overlay"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("#addtobasket_button")).click();
        wait.until(ExpectedConditions.visibilityOf(overlay));
        wait.until(ExpectedConditions.invisibilityOf(overlay));


    }
}
