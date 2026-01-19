package advancedElementHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Custom_Waits_Test {

    static WebDriver driver;
    static Custom_Waits customWait;

    By visibleAfter5SecBtn = By.id("visibleAfter");
    By enableAfter5SecBtn = By.id("enableAfter");
    By colorChangeBtn = By.id("colorChange");

    /* ---------- SCREENSHOT ON FAILURE ---------- */
    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot(description.getMethodName());
        }
    };

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/dynamic-properties");
        customWait = new Custom_Waits(driver);
    }

    /* ---------- TEST 1: VISIBILITY ---------- */
    @Test
    public void test1_visibility() {

        WebElement visibleBtn =
                customWait.waitForVisibility(visibleAfter5SecBtn, 15);

        Assert.assertNotNull("Element should be present", visibleBtn);
        Assert.assertTrue("Element should be visible", visibleBtn.isDisplayed());
    }

    /* ---------- TEST 2: ENABLED ---------- */
    @Test
    public void test2_enable() {

        WebElement enableBtn =
                customWait.waitForClickability(enableAfter5SecBtn, 10);

        Assert.assertNotNull("Element should be present", enableBtn);
        Assert.assertTrue("Element should be enabled", enableBtn.isEnabled());
    }

    /* ---------- TEST 3: COLOR CHANGE ---------- */
    @Test
    public void test3_colorChange() {

        customWait.waitForAttribute(colorChangeBtn, "color", "rgba", 10);

        String color =
                driver.findElement(colorChangeBtn).getCssValue("color");

        Assert.assertNotNull("Color value should not be null", color);
        Assert.assertTrue("Color should be in rgba format", color.contains("rgba"));
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* ---------- SCREENSHOT UTILITY ---------- */
    private static void takeScreenshot(String testName) {

        try {
            File dir = new File("screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File src =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Files.copy(
                    src.toPath(),
                    new File(dir, testName + ".png").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException | WebDriverException ignored) {
        }
    }
}
