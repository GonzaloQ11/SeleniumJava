package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private WebDriver driver;
    @FindBy(name = "username")
    private WebElement usernameInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(css = "[type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    @Step("Login")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();

    }
}
