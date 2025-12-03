package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login{

    private WebDriver driver;

    private By email = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
    }

    public void login(String userEmail, String userPassword) {
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(userPassword);
        driver.findElement(loginBtn).click();
    }
}
