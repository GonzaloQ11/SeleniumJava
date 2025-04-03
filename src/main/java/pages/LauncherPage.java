package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LauncherPage extends BasePage {

    @FindBy(css = "button[onclick]")
    private WebElement runButton;

    public LauncherPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnRunButton() {
        isElementDisplayed(runButton);
        runButton.click();
        pageFinishedLoading();
    }

}
