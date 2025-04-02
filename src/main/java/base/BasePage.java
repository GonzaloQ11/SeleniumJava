package base;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final By requiredInputError = By.xpath("//parent::*//following-sibling::span");
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

    public void pageFinishedLoading() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}
