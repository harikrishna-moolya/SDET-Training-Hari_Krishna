package test;
import pages.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;

public class Login_Test {

	//For Successful Login
    @Test
    public void positiveLogin() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        Login lp = new Login(driver);
        lp.openUrl();
        lp.login("hk01@gmail.com", "1432");
    }

    //For unsuccessful login
    @Test
    public void negativeLogin() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        Login lp = new Login(driver);
        lp.openUrl();
        lp.login("harik01.com", "1234");
    }
}
