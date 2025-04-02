package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CharacterPage extends BasePage {

    @FindBy(xpath = "//section//h1")
    private WebElement name;
    @FindBy(xpath = "//p//b[text()='Gender:']")
    private WebElement gender;
    @FindBy(xpath = "//p//b[text()='Specie:']")
    private WebElement specie;
    @FindBy(xpath = "//p//b[text()='Status:']")
    private WebElement status;

    public CharacterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean characterNameIsDisplayed() {
        return name.isDisplayed();
    }

    public boolean characterGenderIsDisplayed() {
        return gender.isDisplayed();
    }

    public boolean characterSpecieIsDisplayed() {
        return specie.isDisplayed();
    }

    public boolean characterStatusIsDisplayed() {
        return status.isDisplayed();
    }

}
