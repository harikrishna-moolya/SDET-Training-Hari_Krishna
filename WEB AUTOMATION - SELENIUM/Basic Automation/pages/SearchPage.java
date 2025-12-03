package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    private WebDriver driver;

    private By searchBox = By.name("search");
    private By searchBtn = By.xpath("//button[@class='btn btn-default btn-lg']");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl() {
        driver.get("https://tutorialsninja.com/demo/");
    }

    public void search(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBtn).click();
    }
}
