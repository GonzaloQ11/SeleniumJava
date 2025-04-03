package base;

import config.ConfigReader;
import drivers.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseTest {
    private static final int DEFAULT_WAIT_DURATION = Integer.parseInt(ConfigReader.getProperty("defaultWait"));
    protected WebDriver driver;

    @Before
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT_DURATION));
    }

    @After
    public void tearDown() {
        DriverManager.getInstance().quitDriver();
    }
}
