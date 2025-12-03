package advancedElementHandling;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Advanced_Element_Handling {

    static WebDriver driver;
    static Actions actions;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    //FOR DROPDOWNS
    public void dropdownsTest() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");

        // Normal dropdown
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByVisibleText("Green");

        // Custom dropdown
        driver.findElement(By.id("withOptGroup")).click();
        driver.findElement(By.xpath("//div[text()='Group 2, option 2']")).click();

        Thread.sleep(1500);
    }

    @Test
    //FOR FILE UPLOAD
    public void fileUploadTest() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");

        WebElement upload = driver.findElement(By.id("uploadFile"));
        upload.sendKeys("C:\\Users\\Hari Krishna\\Pictures\\sample.png");

        Thread.sleep(1500);
    }

    @Test
    //FOR DRAGDOPS
    public void dragDropTest() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");

        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));

        actions.dragAndDrop(drag, drop).perform();

        Thread.sleep(1500);
    }

    @Test
    //FOR HOVER
    public void hoverTest() throws InterruptedException {
        driver.get("https://demoqa.com/tool-tips");

        WebElement hoverBtn = driver.findElement(By.id("toolTipButton"));
        actions.moveToElement(hoverBtn).perform();

        Thread.sleep(1500);
    }

    @Test
    //FOR CLICKS
    public void clickActionsTest() throws InterruptedException {
        driver.get("https://demoqa.com/buttons");

        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));

        actions.contextClick(rightClickBtn).perform();
        actions.doubleClick(doubleClickBtn).perform();

        Thread.sleep(1500);
    }

    @Test
    //FOR CALENDER
    public void calendarTest() throws InterruptedException {
    	        driver.get("https://demoqa.com/date-picker");

    	        selectCurrentDate();

    	        Thread.sleep(1500);
    	    }

    public void selectCurrentDate() {
    	        // Open the calendar
    	        driver.findElement(By.id("datePickerMonthYearInput")).click();

    	        // Get today's date
    	        LocalDate today = LocalDate.now();

    	        String day = String.valueOf(today.getDayOfMonth());
    	        String month = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // e.g. January
    	        String year = String.valueOf(today.getYear());

    	        // Select Month
    	        WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
    	        monthDropdown.findElement(By.xpath("//option[text()='" + month + "']")).click();

    	        // Select Year
    	        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
    	        yearDropdown.findElement(By.xpath("//option[text()='" + year + "']")).click();

    	        // Select Day
    	        driver.findElement(By.xpath("//div[text()='" + day + "']")).click();
     } 
    
}
