package drivers;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverHelper extends BaseBrowserDriver {

    public FirefoxDriverHelper() {
        HEADLESS = Boolean.parseBoolean(ConfigReader.getProperty("HEADLESS"));
    }

    static FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (HEADLESS) {
            options.addArguments("--headless");
        }
        return options;
    }

    static FirefoxDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(getOptions());
    }

}
