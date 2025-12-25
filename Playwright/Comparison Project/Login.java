package comparison.comparison;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    public static void main(String[] args) {
    	
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");

            driver.findElement(By.id("input-email"))
                    .sendKeys("test@example.com");

            driver.findElement(By.id("input-password"))
                    .sendKeys("password123");

            driver.findElement(By.xpath("//input[@value='Login']")).click();

            if (driver.getCurrentUrl().contains("account")) {
                System.out.println("Login Successful");
            } else {
                System.out.println("Login Failed");
            }

        } finally {
            driver.quit();
        }
    }
}
