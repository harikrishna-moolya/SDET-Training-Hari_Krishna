package advancedElementHandling;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class First {

    WebDriver driver;
    WebDriverWait wait;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            captureScreenshot(description.getMethodName());
        }
    };

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* ---------- WAIT UTILITIES ---------- */

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /* ---------- TESTS ---------- */

    @Test
    public void testExplicitWaitVisible() {
        driver.get("https://demoqa.com/dynamic-properties");

        By visibleBtn = By.id("visibleAfter");
        WebElement button = waitForVisible(visibleBtn);

        Assert.assertTrue("Button should be visible", button.isDisplayed());
    }

    @Test
    public void testExplicitWaitClickable() {
        driver.get("https://demoqa.com/dynamic-properties");

        By enableBtn = By.id("enableAfter");
        WebElement button = waitForClickable(enableBtn);

        button.click();
        Assert.assertTrue("Button should be enabled", button.isEnabled());
    }

    @Test
    public void testExplicitWaitInvisible() {
        driver.get("https://demoqa.com/dynamic-properties");

        By colorChangeBtn = By.id("colorChange");
        WebElement button = waitForVisible(colorChangeBtn);

        Assert.assertTrue("Color change button should be visible", button.isDisplayed());
    }

    /* ---------- SCREENSHOT UTILITY ---------- */

    private void captureScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            File dest = new File("screenshots/" + testName + ".png");
            Files.createDirectories(dest.getParentFile().toPath());

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
