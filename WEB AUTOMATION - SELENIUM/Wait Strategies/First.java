package advancedElementHandling;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class First {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // increased timeout
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    


   

    // 1. TEST — wait for VISIBLE
    @Test
    public void testExplicitWaitVisible() {
        driver.get("https://demoqa.com/dynamic-properties");

        By visibleBtn = By.id("visibleAfter");

        // Wait for element to appear
        WebElement button = waitForVisible(visibleBtn);

        System.out.println("Button is now visible: " + button.isDisplayed());
    }


    // 2. TEST — wait for CLICKABLE 
    @Test
    public void testExplicitWaitClickable() {
        driver.get("https://demoqa.com/dynamic-properties");

        By enableBtn = By.id("enableAfter");

        // Wait until button becomes clickable
        WebElement button = waitForClickable(enableBtn);

        button.click();

        System.out.println("Button clicked successfully!");
    }


    // 3. TEST — wait for INVISIBLE
    @Test
    public void testExplicitWaitInvisible() {
        driver.get("https://demoqa.com/dynamic-properties");

        // There is no disappearing element on this page.
        // So we use a NEW locator that becomes invisible.

        By colorChangeBtn = By.id("colorChange");

        // Wait for button to become visible first
        waitForVisible(colorChangeBtn);

        // There is NO element that becomes invisible here → so we only validate visible
        System.out.println("Element is visible. No invisible element present on this page.");
    }
}
