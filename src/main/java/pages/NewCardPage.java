package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCardPage extends BasePage {

    /*
        There is an empty screen after clicking on "Add" button.
        The form element is not displayed.
    */
    @FindBy(xpath = "//form")
    private WebElement form;

    public NewCardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyFormIsDisplayed(){
        return isElementDisplayed(form);
    }
}
