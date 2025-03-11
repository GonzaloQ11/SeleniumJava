package base;

import config.ConfigReader;
import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    private static final int DEFAULT_WAIT_DURATION = Integer.parseInt(ConfigReader.getProperty("defaultWait"));
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT_DURATION));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.getInstance().quitDriver();
    }
}
