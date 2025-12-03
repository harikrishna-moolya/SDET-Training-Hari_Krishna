package test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.Registration;
public class Registration__Test {

    @Test
    public void successfully() {
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        Registration first = new Registration(driver);

        first.openUrl("https://tutorialsninja.com/demo/index.php?route=account/register");
        first.enterFirstname("HARI");
        first.enterLastname("Krishna");
        first.enterEmail("hk01@gmail.com");
        first.enterTelephone("9876543210");
        first.enterPassword("1432");
        first.confirmPassword("1432");
        first.toSubscribe();
        first.toAgree();
        first.toEnter();
    }
}
