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

import pages.Registration;

public class Registration__Test {

    WebDriver driver;
    WebDriverWait wait;
    Registration registration;

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
        registration = new Registration(driver);

        registration.openUrl(
                "https://tutorialsninja.com/demo/index.php?route=account/register");
    }

    /* ---------- POSITIVE REGISTRATION ---------- */
    @Test
    public void successfulRegistration() {

        registration.enterFirstname("HARI");
        registration.enterLastname("Krishna");
        registration.enterEmail("hk01+" + System.currentTimeMillis() + "@gmail.com");
        registration.enterTelephone("9876543210");
        registration.enterPassword("1432");
        registration.confirmPassword("1432");
        registration.toSubscribe();
        registration.toAgree();
        registration.toEnter();

        // Assert URL
        wait.until(ExpectedConditions.urlContains("route=account/success"));
        Assert.assertTrue(
                driver.getCurrentUrl().contains("route=account/success")
        );

        // Assert success message
        WebElement successHeading =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[text()='Your Account Has Been Created!']")));

        Assert.assertTrue(successHeading.isDisplayed());
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
