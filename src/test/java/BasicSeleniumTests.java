import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasicSeleniumTests {
    WebDriver driver;

    @BeforeClass
    public void setupWebdriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    public void alertTest() {
        driver.get("http://qac.com.pl/alerts.html");

        driver.findElement(By.cssSelector("#sAlert button")).click();
        driver.switchTo().alert().accept();

        assertEquals(driver.findElement(By.cssSelector("#sAlert .message")).getText(), "Simple alert OK button pressed");


    }

    @Test
    public void iframeTest() throws InterruptedException {
        driver.get("http://qac.com.pl/iframes.html");

        driver.switchTo().frame("alerty");

        driver.findElement(By.cssSelector("#sAlert button")).click();
        driver.switchTo().alert().accept();
        assertEquals(driver.findElement(By.cssSelector("#sAlert .message")).getText(), "Simple alert OK button pressed");

        driver.switchTo().defaultContent();

        driver.switchTo().frame("formularz");
        driver.findElement(By.cssSelector("form")).submit();
        assertEquals(driver.findElement(By.id("formMessage")).getText(), "Nie wszystkie pola zostały wypełnione");

        driver.switchTo().defaultContent();

        Thread.sleep(5000);
    }

    @Test
    public void formTest() throws InterruptedException {
        driver.get("http://qac.com.pl/basicForm.html");

        WebElement fName = driver.findElement(By.name("firstName"));
        fName.sendKeys("Adam");
//        driver.findElement(By.name("firstName")).sendKeys("Adam");

        driver.findElement(By.name("lastName")).sendKeys("Kowalski");
        driver.findElement(By.name("email")).sendKeys("test@test.pl");

        WebElement partNo = driver.findElement(By.name("partNo"));
        partNo.clear();
        partNo.sendKeys("5");

        List<WebElement> genders = driver.findElements(By.name("gender"));
        for(WebElement item : genders) {
            if (item.getAttribute("value").equals("m")) {
                item.click();
                break;
            }
        }

        List<String> additionalOptions = new ArrayList<>();
        additionalOptions.add("materials");
        additionalOptions.add("gadgets");
        List<WebElement> additional = driver.findElements(By.name("additional"));
        for (WebElement item : additional) {
            if (additionalOptions.contains(item.getAttribute("value")))
                item.click();
        }

        Select selectLang = new Select(driver.findElement(By.name("lang")));
        selectLang.selectByVisibleText("Niemiecki");

        Select subjectsSelect = new Select(driver.findElement(By.name("subjects")));

        List<String> subjectsList = Arrays.asList("Performance", "CICD");
        for (String item : subjectsList) {
            subjectsSelect.selectByVisibleText(item);
        }

        File file = new File("src\\main\\resources\\text.txt");
        driver.findElement(By.name("file")).sendKeys(file.getAbsolutePath());

        fName.submit();

        WebElement formMessage = driver.findElement(By.id("formMessage"));

        assertEquals(formMessage.getText(), "Formularz został wysłany poprawnie");
        assertTrue(formMessage.getAttribute("class").contains("green"));



        Thread.sleep(5000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}