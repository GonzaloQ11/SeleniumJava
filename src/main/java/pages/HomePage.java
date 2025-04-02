package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "button[onclick]")
    private WebElement runButton;
    @FindBy(xpath = "//main//h1")
    private WebElement topTitle;
    private final By cards = By.xpath("//*[contains(@class, 'rounded-lg')]");
    private final By cardImage = By.xpath("//img");
    private final By cardName = By.xpath("//span[contains(@class, 'block')]");
    private final By cardVerDetalleLink = By.xpath("//a[text()=' Ver detalle ']");
    private BottomBarSection bottomBarSection = new BottomBarSection(driver);

    public HomePage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnRunButton() {
        runButton.click();
    }

    public boolean isCardImageDisplayed(WebElement card) {
        return card.findElement(cardImage).isDisplayed();
    }

    public boolean isCardNameDisplayed(WebElement card) {
        return card.findElement(cardName).isDisplayed();
    }

    public boolean isCardLinkDisplayed(WebElement card) {
        return card.findElement(cardVerDetalleLink).isDisplayed();
    }

    public void clickOnCardLink(List<WebElement> cards, int cardIndex) {
        cards.get(cardIndex).findElement(cardVerDetalleLink).click();
    }

    public List<WebElement> getListOfCards() {
        return driver.findElements(cards);
    }

    public BottomBarSection getBottomBarSection () {
        return bottomBarSection;
    }

    public boolean topTitleIsDisplayed() {
        return topTitle.isDisplayed();
    }

}
