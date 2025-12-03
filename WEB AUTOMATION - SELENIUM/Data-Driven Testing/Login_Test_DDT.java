package data_Driven_Testing;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Login_Test_DDT {

    WebDriver driver;

    private String username;
    private String password;

    // Constructor receives data for each test iteration
    public Login_Test_DDT(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Load data from Excel → convert list to array for JUnit
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        List<String[]> excelData =
                Excel_Reader.readExcel("C:\\Users\\Hari Krishna\\Downloads\\login_credentials.xlsx", "LoginData");

        Object[][] data = new Object[excelData.size()][2];

        for (int i = 0; i < excelData.size(); i++) {
            data[i] = excelData.get(i);
        }
        return Arrays.asList(data);
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test
    public void loginTest() {
        System.out.println("Testing → " + username + " | " + password);

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();

        // You can update expected condition based on your app
        boolean isLoggedIn =
                driver.getPageSource().contains("Logged In Successfully") ||
                driver.getCurrentUrl().contains("success");

        Assert.assertTrue("Login failed for → " + username, isLoggedIn);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
