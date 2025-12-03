package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import pages.SearchPage;

public class SearchPage_Test {

    @Test
    public void searchItem() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        SearchPage sp = new SearchPage(driver);
        sp.openUrl();
        sp.search("iphone");
    }
}
