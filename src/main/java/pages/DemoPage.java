package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DemoPage extends BasePage {

    @FindBy(css = "[id='input-example'] input")
    private WebElement enableDisabledInput;
    @FindBy(css = "[id='input-example'] button")
    private WebElement enableDisabledButton;
    @FindBy(id = "message")
    private WebElement enableDisableMessage;

    public DemoPage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isEnableDisabledInputDisplayed() {
        return enableDisabledInput.isDisplayed();
    }

    public void clickOnEnableDisabledButton() {
        enableDisabledButton.click();
    }

    public boolean isEnabledMessageDisplayed(){
        return isMessageDisplayed(enableDisableMessage, "It's enabled!");
    }

    public boolean isDisabledMessageDisplayed(){
        return isMessageDisplayed(enableDisableMessage, "It's disabled!");
    }

    public boolean isInputEnabled () {
        return enableDisabledInput.isEnabled();
    }
}
