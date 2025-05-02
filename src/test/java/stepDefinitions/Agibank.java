package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Agibank {
    @Given("Open agibank page")
    public void openAgibankPage() {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("https://blog.agibank.com.br/");
    }
}
