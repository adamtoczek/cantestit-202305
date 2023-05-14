package swiatRoweru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultPage extends BasePage{
    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public void clickByName(String name) {
        List<WebElement> resultsList = driver.findElements(By.cssSelector("td.product_name"));
        for (WebElement row : resultsList) {
            if (row.getText().equals(name)) {
                row.findElement(By.cssSelector("a")).click();
                break;
            }
        }
    }
}
