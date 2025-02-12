package base;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int DEFAULT_WAIT_DURATION = Integer.parseInt(ConfigReader.getProperty("defaultWait"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_DURATION));
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean isMessageDisplayed(WebElement element, String text) {
       return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
