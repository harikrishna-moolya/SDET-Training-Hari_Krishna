package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage {

    private WebDriver driver;

    private By name = By.id("input-name");
    private By email = By.id("input-email");
    private By enquiry = By.id("input-enquiry");
    private By submitBtn = By.xpath("//input[@value='Submit']");

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=information/contact");
    }

    public void submitForm(String uname, String mail, String msg) {
        driver.findElement(name).sendKeys(uname);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(enquiry).sendKeys(msg);
        driver.findElement(submitBtn).click();
    }
}
