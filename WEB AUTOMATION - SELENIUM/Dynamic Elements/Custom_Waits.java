package dynamicElements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Custom_Waits {

    private WebDriver driver;

    public Custom_Waits(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement waitUntilClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    // Wait for element using XPath contains()
    public WebElement waitForContains(String xpathContains, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));

        return wait.until(driver -> driver.findElement(By.xpath(xpathContains)));
    }

    // Wait for element using CSS starts-with ^ or contains *
    public WebElement waitForCss(String pattern, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));

        return wait.until(driver -> driver.findElement(By.cssSelector(pattern)));
    }

    // Wait for AJAX completion
    public void waitForAjax(int sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));

        wait.until((ExpectedCondition<Boolean>) d ->
                (Boolean) ((JavascriptExecutor) d)
                        .executeScript("return window.jQuery != undefined && jQuery.active === 0;"));
    }

    // Wait for document.readyState='complete'
    public void waitForPageLoad(int sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));

        wait.until((ExpectedCondition<Boolean>) d ->
                ((JavascriptExecutor) d)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }
}

