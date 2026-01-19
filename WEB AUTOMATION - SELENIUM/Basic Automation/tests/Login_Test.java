package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Login;

public class Login_Test {

    WebDriver driver;
    WebDriverWait wait;
    Login loginPage;

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
        loginPage = new Login(driver);
        loginPage.openUrl();
    }

    /* ---------- POSITIVE LOGIN ---------- */
    @Test
    public void positiveLogin() {

        loginPage.login("hk01@gmail.com", "1432");

        wait.until(ExpectedConditions.urlContains("route=account/account"));

        Assert.assertTrue(
                "User should be redirected to My Account page",
                driver.getCurrentUrl().contains("route=account/account")
        );
    }

    /* ---------- NEGATIVE LOGIN ---------- */
    @Test
    public void negativeLogin() {

        loginPage.login("harik01.com", "1234");

        WebElement errorMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert-danger")));

        Assert.assertTrue(
                "Error message should be displayed for invalid login",
                errorMessage.isDisplayed()
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
