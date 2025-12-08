import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Selenium_Integration {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void verifyHomePageTitle() {
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
    }

    @Test
    public void verifyMoreInfoLink() {
        boolean linkDisplayed = driver.findElement(By.cssSelector("a")).isDisplayed();
        Assert.assertTrue(linkDisplayed, "More Info link not displayed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
