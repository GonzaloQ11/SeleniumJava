package drivers;

import config.ConfigReader;
import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;
    private static final boolean HEADLESS = Boolean.parseBoolean(ConfigReader.getProperty("HEADLESS"));

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            String executionMode = ConfigReader.getProperty("seleniumExecutionMode").toUpperCase();
            String browser = ConfigReader.getProperty("browser").toUpperCase();
            driver = executionMode.equals("LOCAL")? getLocalDriver(browser) : getRemoteDriver(browser);
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver getLocalDriver(String browser) {
        switch (BrowserType.valueOf(browser)) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(getFirefoxOptions());
            case CHROME:
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(getChromeOptions());
        }
    }

    private static WebDriver getRemoteDriver(String browser) {
        Capabilities browserOptions;
        switch (BrowserType.valueOf(browser)) {
            case FIREFOX:
                browserOptions = getFirefoxOptions();
                break;
            case CHROME:
            default:
                browserOptions = getChromeOptions();
                break;
        }

        try {
            return new RemoteWebDriver(getRemoteDriverURL(), browserOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Remote WebDriver URL", e);
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        if (HEADLESS) {
            options.addArguments("--headless");
        }
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (HEADLESS) {
            options.addArguments("--headless");
        }
        return options;
    }

    private static URL getRemoteDriverURL() throws MalformedURLException {
        return new URL(ConfigReader.getProperty("seleniumRemoteURL"));
    }
}
