package drivers;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverHelper extends BaseBrowserDriver {

    public ChromeDriverHelper() {
        HEADLESS = Boolean.parseBoolean(ConfigReader.getProperty("HEADLESS"));
    }

    static ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        if (HEADLESS) {
            options.addArguments("--headless");
        }
        return options;
    }

    static ChromeDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new ChromeDriver(getOptions());
    }
}
