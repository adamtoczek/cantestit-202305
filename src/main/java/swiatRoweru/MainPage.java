package swiatRoweru;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public static void navigateTo(WebDriver driver){
        driver.get("https://www.swiatroweru.com.pl/index.php");
    }
}
