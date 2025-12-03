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
//	public static void main(String[] args) {
//		WebDriver driver =new ChromeDriver();
//		First_Program first = new First_Program(driver);
//		first.openUrl("https://tutorialsninja.com/demo/index.php?route=account/register");
//		first.enterFirstname("HARI");
//		first.enterLastname("Krishna");
//		first.enterEmail("hk01@gmail.com");
//	    first.enterTelephone("9876543210");
//	    first.enterPassword("1432");
//	    first.confirmPassword("1432");
//	    first.toSubscribe();
//	    first.toAgree();
//	    first.toEnter();
//	}



// public static void main(String[] args) {
//	WebDriver driver =new ChromeDriver();
//	driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
//	driver.manage().window().maximize();
//	driver.findElement(By.id("input-firstname")).sendKeys("HARI");
//	driver.findElement(By.id("input-lastname")).sendKeys("KRISHNA");
//	driver.findElement(By.id("input-email")).sendKeys("hk01@gmail.com");
//	driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
//	driver.findElement(By.id("input-password")).sendKeys("1432");
//	driver.findElement(By.id("input-confirm")).sendKeys("1432");
//	driver.findElement(By.xpath("//input[@value='0']")).click();
//	driver.findElement(By.name("agree")).click();
//	driver.findElement(By.xpath("//input[@value='Continue']")).click(); 
//}
//}
