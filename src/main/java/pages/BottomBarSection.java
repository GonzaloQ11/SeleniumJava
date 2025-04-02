package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BottomBarSection extends BasePage {

    @FindBy(xpath = "//a[text()=' Home ']")
    private WebElement homeButton;

    public BottomBarSection (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnHomeButton() {
        homeButton.click();
    }
}
