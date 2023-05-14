package swiatRoweru;

import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;
    public NavigationBar navigationBar;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        navigationBar = new NavigationBar(driver);
    }
}
