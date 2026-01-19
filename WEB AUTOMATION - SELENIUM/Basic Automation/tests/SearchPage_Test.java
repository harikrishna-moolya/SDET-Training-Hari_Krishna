package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.SearchPage;

public class SearchPage_Test {

    WebDriver driver;
    WebDriverWait wait;
    SearchPage searchPage;

    /* ---------- SCREENSHOT ON FAILURE ---------- */
    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot(description.getMethodName());
        }
    };

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        searchPage = new SearchPage(driver);
        searchPage.openUrl();
    }

    /* ---------- POSITIVE SEARCH ---------- */
    @Test
    public void searchItem() {

        searchPage.search("iphone");

        // Assert URL
        wait.until(ExpectedConditions.urlContains("route=product/search"));
        Assert.assertTrue(
                driver.getCurrentUrl().contains("route=product/search")
        );

        // Assert at least one product is shown
        List<WebElement> products =
                driver.findElements(By.cssSelector(".product-layout"));

        Assert.assertTrue(
                "Expected search results, but none were found",
                products.size() > 0
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* ---------- SCREENSHOT UTILITY ---------- */
    private void takeScreenshot(String testName) {

        try {
            File dir = new File("screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File src =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    src.toPath(),
                    new File(dir, testName + ".png").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException | WebDriverException ignored) {
        }
    }
}
