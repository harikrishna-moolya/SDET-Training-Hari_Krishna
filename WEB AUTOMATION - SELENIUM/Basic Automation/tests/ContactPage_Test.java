package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.ContactPage;

public class ContactPage_Test {

    WebDriver driver;
    ContactPage contactPage;

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
        contactPage = new ContactPage(driver);
    }

    @Test
    public void contactTest() {

        contactPage.openUrl();

        contactPage.submitForm(
                "Hari",
                "hk01@gmail.com",
                "Need information regarding products"
        );

        boolean isSubmissionSuccessful =
                driver.getPageSource().toLowerCase().contains("thank") ||
                driver.getPageSource().toLowerCase().contains("success");

        Assert.assertTrue(
                "Contact form submission failed",
                isSubmissionSuccessful
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
