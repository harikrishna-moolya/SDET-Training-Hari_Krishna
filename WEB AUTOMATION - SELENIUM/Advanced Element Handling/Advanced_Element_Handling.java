package advancedElementHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class Advanced_Element_Handling {

    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    /* -------- SCREENSHOT ON FAILURE -------- */
    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot(description.getMethodName());
        }
    };

    /* -------- SETUP -------- */
    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    /* -------- TEARDOWN -------- */
    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* -------- DROPDOWNS -------- */
    @Test
    public void dropdownsTest() {

        driver.get("https://demoqa.com/select-menu");

        /* ---- STANDARD HTML SELECT ---- */
        WebElement oldSelectMenu = waitFor(By.id("oldSelectMenu"));
        scrollIntoView(oldSelectMenu);

        Select select = new Select(oldSelectMenu);
        select.selectByVisibleText("Green");

        Assert.assertEquals(
                "Green",
                select.getFirstSelectedOption().getText()
        );
    }

    /* -------- FILE UPLOAD -------- */
    @Test
    public void fileUploadTest() {

        driver.get("https://demoqa.com/upload-download");

        WebElement upload = waitFor(By.id("uploadFile"));
        scrollIntoView(upload);
        upload.sendKeys("C:\\Users\\Hari Krishna\\Pictures\\sample.png");

        WebElement uploadedFile = waitFor(By.id("uploadedFilePath"));
        Assert.assertTrue(uploadedFile.getText().contains("sample.png"));
    }

    /* -------- DRAG AND DROP -------- */
    @Test
    public void dragDropTest() {

        driver.get("https://demoqa.com/droppable");

        WebElement drag = waitFor(By.id("draggable"));
        WebElement drop = waitFor(By.id("droppable"));

        scrollIntoView(drag);
        actions.dragAndDrop(drag, drop).perform();

        Assert.assertEquals("Dropped!", drop.getText());
    }

    /* -------- HOVER -------- */
    @Test
    public void hoverTest() {

        driver.get("https://demoqa.com/tool-tips");

        WebElement hoverBtn = waitFor(By.id("toolTipButton"));
        scrollIntoView(hoverBtn);
        actions.moveToElement(hoverBtn).perform();

        WebElement tooltip =
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.className("tooltip-inner")));

        Assert.assertTrue(tooltip.isDisplayed());
    }

    /* -------- CALENDAR -------- */
    @Test
    public void calendarTest() {

        driver.get("https://demoqa.com/date-picker");

        WebElement dateInput = waitFor(By.id("datePickerMonthYearInput"));
        scrollIntoView(dateInput);
        jsClick(dateInput);

        selectCurrentDate();

        Assert.assertFalse(dateInput.getAttribute("value").isEmpty());
    }

    private void selectCurrentDate() {

        LocalDate today = LocalDate.now();
        String month = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = String.valueOf(today.getYear());
        String day = String.valueOf(today.getDayOfMonth());

        new Select(waitFor(By.className("react-datepicker__month-select")))
                .selectByVisibleText(month);

        new Select(waitFor(By.className("react-datepicker__year-select")))
                .selectByVisibleText(year);

        WebElement dayElement = waitFor(By.xpath("//div[text()='" + day + "']"));
        jsClick(dayElement);
    }

    /* -------- UTILITIES -------- */
    private static WebElement waitFor(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    private static void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private static void takeScreenshot(String testName) {
        try {
            File dir = new File("screenshots");
            if (!dir.exists()) dir.mkdirs();

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(src.toPath(),
                    new File(dir, testName + ".png").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException | WebDriverException ignored) {
        }
    }
}
