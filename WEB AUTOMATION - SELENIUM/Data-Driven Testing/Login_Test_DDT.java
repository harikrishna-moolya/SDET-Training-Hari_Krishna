package data_Driven_Testing;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Login_Test_DDT {

    WebDriver driver;

    private String username;
    private String password;
    private String expectedResult;

    /* ---------- CONSTRUCTOR ---------- */

    public Login_Test_DDT(String username, String password, String expectedResult) {
        this.username = username;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    /* ---------- TEST DATA ---------- */

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {

        List<String[]> excelData =
                Excel_Reader.readExcel(
                        "login_credentials.xlsx",
                        "LoginData");

        Object[][] data = new Object[excelData.size()][3];

        for (int i = 0; i < excelData.size(); i++) {
            data[i] = excelData.get(i);
        }
        return Arrays.asList(data);
    }

    /* ---------- TEST WATCHER ---------- */

    @Rule
    public TestWatcher watcher = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            captureScreenshot(description.getMethodName() + "_" + username);
        }

        @Override
        protected void finished(Description description) {
            if (driver != null) {
                driver.quit();
            }
        }
    };

    /* ---------- SETUP ---------- */

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    /* ---------- TEST ---------- */

    @Test
    public void loginTest() {

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();

        boolean actualLoginSuccess =
                driver.getPageSource().contains("Logged In Successfully") ||
                        driver.getCurrentUrl().contains("success");

        if ("PASS".equalsIgnoreCase(expectedResult)) {

            Assert.assertTrue(
                    "Expected login to PASS but it failed for user: " + username,
                    actualLoginSuccess
            );

        } else {

            Assert.assertFalse(
                    "Expected login to FAIL but it passed for user: " + username,
                    actualLoginSuccess
            );
        }
    }

    /* ---------- SCREENSHOT UTILITY ---------- */

    private void captureScreenshot(String testName) {

        if (driver == null) {
            return;
        }

        try {
            File dir = new File("screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            File dest = new File(dir, testName + ".png");
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
    }
}
