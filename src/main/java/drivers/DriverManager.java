package drivers;

import config.ConfigReader;
import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String executionMode = ConfigReader.getProperty("seleniumExecutionMode").toUpperCase();
            String browser = ConfigReader.getProperty("browser").toUpperCase();
            driver = executionMode.equals("LOCAL")? getLocalDriver(browser) : getRemoteDriver(browser);
        }
        return driver;
    }

    public static WebDriver getLocalDriver(String browser) {
        switch (BrowserType.valueOf(browser)) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case CHROME:
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public static WebDriver getRemoteDriver(String browser) {
        Capabilities browserOptions;
        switch (BrowserType.valueOf(browser)) {
            case FIREFOX:
                browserOptions = new FirefoxOptions();
                break;
            case CHROME:
            default:
                browserOptions = new ChromeOptions();
                break;
        }
        try {
            driver = new RemoteWebDriver(getRemoteDriverURL(), browserOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    public static URL getRemoteDriverURL() throws MalformedURLException {
        return new URL(ConfigReader.getProperty("seleniumRemoteURL"));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
