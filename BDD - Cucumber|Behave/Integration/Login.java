package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {

    WebDriver driver;
    WebDriverWait wait;

    public Login(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By emailField = By.id("input-email");
    By passwordField = By.id("input-password");
    By loginButton = By.xpath("//input[@value='Login']");
    By logoutLink = By.linkText("Logout");

    public void enterEmail(String email) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement p = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        p.clear();
        p.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToLoginPage() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
    }
}
