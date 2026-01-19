package windows_Frames;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Windows_Frames {

    static WebDriver driver;

    /* ---------- FAILURE-ONLY SCREENSHOT RULE ---------- */

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            captureScreenshot(description.getMethodName());
        }
    };

    /* ---------- SETUP ---------- */

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /* ---------- TESTS ---------- */

    @Test
    public void test1_windowHandling() {

        driver.get("https://demoqa.com/browser-windows");

        String parentWindow = driver.getWindowHandle();

        WebElement btn = driver.findElement(By.id("windowButton"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
            }
        }

        String childText = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals("This is a sample page", childText);

        driver.close();
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void test2_frameByIndex() {

        driver.get("https://demoqa.com/frames");

        driver.switchTo().frame(3);

        WebElement heading = driver.findElement(By.id("sampleHeading"));
        Assert.assertTrue(heading.isDisplayed());

        driver.switchTo().defaultContent();
    }

    @Test
    public void test3_iframeByName() {

        driver.get("https://demoqa.com/frames");

        driver.switchTo().frame("frame1");

        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals("This is a sample page", text);

        driver.switchTo().defaultContent();
    }

    @Test
    public void test4_iframeByWebElement() {

        driver.get("https://demoqa.com/frames");

        WebElement frameElement = driver.findElement(By.id("frame2"));
        driver.switchTo().frame(frameElement);

        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertTrue(text.contains("sample"));

        driver.switchTo().defaultContent();
    }

    /* ---------- TEARDOWN ---------- */

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* ---------- SCREENSHOT UTILITY ---------- */

    private void captureScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            File dest = new File("screenshots/" + testName + ".png");
            Files.createDirectories(dest.getParentFile().toPath());

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
