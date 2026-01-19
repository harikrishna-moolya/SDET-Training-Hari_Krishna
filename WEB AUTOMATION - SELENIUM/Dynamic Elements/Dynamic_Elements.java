package dynamicElements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dynamic_Elements {

    WebDriver driver;
    Custom_Waits waits;

    /* ---------- FAILURE-ONLY SCREENSHOT RULE ---------- */

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot(description.getMethodName());
        }
    };

    /* ---------- SETUP ---------- */

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/dynamic-properties");

        waits = new Custom_Waits(driver);
        waits.waitForPageLoad(10);
    }

    /* ---------- TESTS ---------- */

    @Test
    public void UsingContains() {
        WebElement btn =
                waits.waitForContains("//button[contains(@id,'visibleAfter')]", 10);

        Assert.assertTrue("Button should be visible", btn.isDisplayed());
    }

    @Test
    public void UsingCSSContains() {
        WebElement colorBtn =
                waits.waitForCss("button[id*='colorChange']", 10);

        String color = colorBtn.getCssValue("color");

        Assert.assertNotNull("Color should not be null", color);
        Assert.assertTrue("Color should be rgba", color.contains("rgba"));
    }

    @Test
    public void UsingStartsWithCSS() {
        WebElement enableBtn =
                waits.waitForCss("button[id^='enableAfter']", 10);

        waits.waitUntilClickable(enableBtn, 10);

        Assert.assertTrue("Button should be enabled", enableBtn.isEnabled());
    }

    /* ---------- TEARDOWN ---------- */

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* ---------- SCREENSHOT UTILITY ---------- */

    private void takeScreenshot(String testName) {

        if (driver == null) {
            return;
        }

        try {
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            File dest = new File(screenshotsDir, testName + ".png");

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (WebDriverException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
