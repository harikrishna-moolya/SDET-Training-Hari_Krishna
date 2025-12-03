package advancedElementHandling;
import org.junit.*;
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

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/dynamic-properties");
        customWait = new Custom_Waits(driver);
        System.out.println("== Tests Started ==");
    }

    // -----------------------------------------
    @Test
    public void test1_visibility() {
        System.out.println("Running Test 1...");
        WebElement visibleBtn = customWait.waitForVisibility(By.id("visibleAfter"), 15);
        Assert.assertTrue(visibleBtn.isDisplayed());
        System.out.println("Test 1: Visible OK");
    }

    // -----------------------------------------
    @Test
    public void test2_enable() {
        System.out.println("Running Test 2...");
        WebElement enableBtn = customWait.waitForClickability(By.id("enableAfter"), 5);
        Assert.assertTrue(enableBtn.isEnabled());
        System.out.println("Test 2: Enabled OK");
    }

    // -----------------------------------------
    @Test
    public void test3_colorChange() {
        System.out.println("Running Test 3...");
        customWait.waitForAttribute(By.id("colorChange"), "color", "rgba", 0);

        String color = driver.findElement(By.id("colorChange")).getCssValue("color");
        Assert.assertTrue(color.contains("rgba"));
        System.out.println("Test 3: Color Changed OK");
    }

    // -----------------------------------------
    @AfterClass
    public static void tearDown() {
    	System.out.println("== Tests Ended ==");
        driver.quit();
    }
}
