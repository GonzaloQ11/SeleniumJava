package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    @FindBy(className = "orangehrm-dashboard-grid")
    private WebElement dashboard;

    public DashboardPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        return dashboard.isDisplayed();
    }
}
