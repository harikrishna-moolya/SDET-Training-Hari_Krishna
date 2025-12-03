package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import pages.ContactPage;

public class ContactPage_Test {

    @Test
    public void contactTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        ContactPage cp = new ContactPage(driver);
        cp.openUrl();
        cp.submitForm("Hari", "hk01@gmail.com", "Need information regarding products");
    }
}
