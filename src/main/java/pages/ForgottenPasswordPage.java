package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPasswordPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;
    @FindBy(xpath = "//button[contains(@class,'cancel')]")
    private WebElement cancelButton;
    @FindBy(xpath = "//button[contains(@class,'reset')]")
    private WebElement resetPasswordButton;

    public ForgottenPasswordPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isForgottenPasswordPageDisplayed(){
        return usernameInput.isDisplayed() && cancelButton.isDisplayed() && resetPasswordButton.isDisplayed();
    }
}
