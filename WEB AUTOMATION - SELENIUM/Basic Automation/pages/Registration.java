package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {
	
	private By firstname = By.id("input-firstname");
	private By lastname  = By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirm =By.id("input-confirm");
	private By Subscribe=By.xpath("//input[@value='0']");
	private By agree = By.name("agree");
	private By btn=By.xpath("//input[@value='Continue']");
	private WebDriver driver;
	
	public Registration(WebDriver driver) {
		this.driver=driver;
	}
	public void openUrl(String Url) {
		driver.get(Url);
	}
	
	public void enterFirstname(String fname) {
		driver.findElement(firstname).sendKeys(fname);
    }
	public void enterLastname(String lname) {
		driver.findElement(lastname).sendKeys(lname);
    }
	public void enterEmail(String email1) {
		driver.findElement(email).sendKeys(email1);
    }
	public void enterTelephone(String num) {
		driver.findElement(telephone).sendKeys(num);
    }
	public void enterPassword(String pswrd) {
		driver.findElement(password).sendKeys(pswrd);
    }
	public void confirmPassword(String pswrd) {
		driver.findElement(confirm).sendKeys(pswrd);
    }
	public void toSubscribe() {
		driver.findElement(Subscribe).click();
    }
	public void toAgree() {
		driver.findElement(agree).click();
    }
	public void toEnter() {
		driver.findElement(btn).click();
    }
}
