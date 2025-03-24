package drivers;

import config.ConfigReader;
import enums.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;

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
                return FirefoxDriverHelper.getDriver();
            case CHROME:
            default:
                return ChromeDriverHelper.getDriver();
        }
    }

    private static WebDriver getRemoteDriver(String browser) {
        Capabilities browserOptions;
        switch (BrowserType.valueOf(browser)) {
            case FIREFOX:
                browserOptions = FirefoxDriverHelper.getOptions();
                break;
            case CHROME:
            default:
                browserOptions = ChromeDriverHelper.getOptions();
                break;
        }

        try {
            return new RemoteWebDriver(getRemoteDriverURL(), browserOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Remote WebDriver URL", e);
        }
    }

    private static URL getRemoteDriverURL() throws MalformedURLException {
        return new URL(ConfigReader.getProperty("seleniumRemoteURL"));
    }
}
