package windows_Frames;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Windows_Frames {

    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    
    @Test
    public void test1_windowHandling() {

        driver.get("https://demoqa.com/browser-windows");

        String parentWindow = driver.getWindowHandle();

        // Click using JavaScript to avoid ad iframe
        WebElement btn = driver.findElement(By.id("windowButton"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        // Switch to child window
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
            }
        }

        // Validate the content inside child window
        String childText = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals("This is a sample page", childText);

        // Close child window
        driver.close();

        // Switch back
        driver.switchTo().window(parentWindow);
    }


    
    @Test
    public void test_frame_by_index() {
        driver.get("https://demoqa.com/frames");

        // index 3 for our required page
        driver.switchTo().frame(3);

        WebElement heading = driver.findElement(By.id("sampleHeading"));
        Assert.assertTrue(heading.isDisplayed());

        driver.switchTo().defaultContent();
    }



    

    @Test
    public void test3_iframeByName() {
        driver.get("https://demoqa.com/frames");

        // 'frame1' is the id of the iframe
        driver.switchTo().frame("frame1");

        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals("This is a sample page", text);

        // Go back to main page
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

    

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
