package dynamicElements;


import java.time.Duration;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dynamic_Elements {

    WebDriver driver;
    Custom_Waits waits;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/dynamic-properties");

        waits = new Custom_Waits(driver);

        waits.waitForPageLoad(10);
    }
    


    // ===========================================================
    @Test
    public void UsingContains() {

        // contains() for dynamic IDs or dynamic buttons
        WebElement btn =
                waits.waitForContains("//button[contains(@id,'visibleAfter')]", 10);

        Assert.assertTrue(btn.isDisplayed());
        System.out.println("Visible After 5 Sec — OK");
    }

    // ===========================================================
   

    // ===========================================================
    @Test
    public void UsingCSSContains() {

        // CSS contains selector *= 
        WebElement colorBtn =
                waits.waitForCss("button[id*='colorChange']", 10);

        String color = colorBtn.getCssValue("color");

        Assert.assertTrue(color.contains("rgba"));
        System.out.println("Color Changed — OK");
    }
    @Test
    public void UsingStartsWithCSS() {

        // CSS starts-with selector ^= 
        WebElement enableBtn =
                waits.waitForCss("button[id^='enableAfter']", 10);
        waits.waitUntilClickable(enableBtn, 10);
        Assert.assertTrue(enableBtn.isEnabled());
        System.out.println("Enabled After 5 Sec — OK");
    }
    // ===========================================================
    @After
    public void tearDown() {
        driver.quit();
    }
}
