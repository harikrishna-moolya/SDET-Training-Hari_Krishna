package testNG_Basics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNG_Basics {

    WebDriver driver;
    String url = "https://www.selenium.dev/selenium/web/web-form.html";

    // ------------------- LIFECYCLE --------------------

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("=== BeforeSuite ===");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("=== BeforeTest ===");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("=== BeforeClass ===");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("---- BeforeMethod ----");
        driver.get(url);
    }

    // ------------------- TESTS ------------------------

    @Test(priority = 1)
    public void testTitle_HardAssert() {
        System.out.println("Running testTitle_HardAssert");

        String actual = driver.getTitle();
        Assert.assertEquals(actual, "Web form", "Title mismatch!");

        System.out.println("Hard assert successful");
    }

    @Test(priority = 2)
    public void testFormElements_SoftAssert() {
        System.out.println("Running testFormElements_SoftAssert");

        SoftAssert soft = new SoftAssert();

        WebElement heading = driver.findElement(By.tagName("h1"));
        soft.assertEquals(heading.getText(), "Web form", "Heading mismatch!");

        WebElement textBox = driver.findElement(By.name("my-text"));
        soft.assertTrue(textBox.isDisplayed(), "Textbox not visible!");

        WebElement submit = driver.findElement(By.cssSelector("button"));
        soft.assertEquals(submit.getText(), "Submit", "Button label wrong!");

        System.out.println("Collecting all soft assertion results...");
        soft.assertAll();
    }

    @Test(priority = 3)
    public void testEnterText() {
        System.out.println("Running testEnterText");

        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Hari Krishna");

        Assert.assertEquals(textBox.getAttribute("value"), "Hari Krishna");
    }

    @Test(priority = 4)
    public void testSubmitButton() {
        System.out.println("Running testSubmitButton");

        WebElement button = driver.findElement(By.cssSelector("button"));
        Assert.assertTrue(button.isEnabled(), "Submit button disabled!");

        button.click();

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Received!", "Submission failed!");
    }

    // ------------------- TEARDOWN ----------------------

    @AfterMethod
    public void afterMethod() {
        System.out.println("---- AfterMethod ----");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("=== AfterClass ===");
        driver.quit();
    }

    @AfterTest
    public void afterTest() {
        System.out.println("=== AfterTest ===");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("=== AfterSuite ===");
    }
}
