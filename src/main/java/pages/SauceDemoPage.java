package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.ref.WeakReference;

public class SauceDemoPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public SauceDemoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        this.usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        this.passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton() {
        this.loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
    }
}
