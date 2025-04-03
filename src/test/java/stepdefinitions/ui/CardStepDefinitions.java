package stepdefinitions.ui;

import config.ConfigReader;
import drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CharacterPage;
import pages.HomePage;
import pages.LauncherPage;
import pages.NewCardPage;

import java.time.Duration;
import java.util.List;

public class CardStepDefinitions {

    private static final int DEFAULT_WAIT_DURATION = Integer.parseInt(ConfigReader.getProperty("defaultWait"));
    protected WebDriver driver;
    private LauncherPage launcherPage;
    private HomePage homePage;
    private CharacterPage characterPage;
    private NewCardPage newCardPage;
    List<WebElement> cards;

    @Before
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT_DURATION));
    }

    @After
    public void tearDown() {
        DriverManager.getInstance().quitDriver();
    }

    @Given("the card project is launched")
    public void launchCardProject() {
        launcherPage = new LauncherPage(driver);
        homePage = new HomePage(driver);
        characterPage = new CharacterPage(driver);
        newCardPage = new NewCardPage(driver);
        launcherPage.pageFinishedLoading();
        launcherPage.clickOnRunButton();
    }

    @When("I view the list of cards")
    public void viewListOfCards() {
        cards = homePage.getListOfCards();
    }

    @Then("each card displays a name, image, and link")
    public void verifyEachCardDisplaysCompleteInfo() {
        for (WebElement card : cards) {
            Assert.assertTrue("Card image is not displayed.", homePage.isCardImageDisplayed(card));
            Assert.assertTrue("Card name is not displayed.", homePage.isCardNameDisplayed(card));
            Assert.assertTrue("Card link is not displayed.", homePage.isCardLinkDisplayed(card));
        }
    }

    @When("I click on a card")
    public void clickOnCard() {
        cards = homePage.getListOfCards();
        homePage.clickOnCardLink(cards, 1);
    }

    @Then("I should be navigated to the character detail page")
    public void verifyNavigationToCharacterPage() {
        Assert.assertTrue("Character detail page is not opened.", homePage.getCurrentURL().contains("character/1"));
    }

    @When("I open a character card")
    public void openCharacterCard() {
        clickOnCard();
    }

    @Then("the name, gender, species, and status are displayed")
    public void verifyCharacterDetailsAreDisplayed() {
        Assert.assertTrue("Character name is not displayed.", characterPage.characterNameIsDisplayed());
        Assert.assertTrue("Character gender is not displayed.", characterPage.characterGenderIsDisplayed());
        Assert.assertTrue("Character species is not displayed.", characterPage.characterSpecieIsDisplayed());
        Assert.assertTrue("Character status is not displayed.", characterPage.characterStatusIsDisplayed());
    }

    @When("I click the Home button")
    public void clickHomeButton() {
        characterPage.getBottomBarSection().clickOnHomeButton();
    }

    @Then("I should be redirected to the homepage")
    public void redirectedToHomepage() {
        Assert.assertEquals("User is not redirected to home page.",
                ConfigReader.getProperty("baseUrl"), homePage.getCurrentURL());
    }

    @When("I click the Add button")
    public void clickAddButton() {
        homePage.getBottomBarSection().clickOnAddButton();
    }

    @Then("the new card form should be visible")
    public void verifyNewCardFormIsDisplayed() {
        Assert.assertTrue("Form is not displayed.", newCardPage.verifyFormIsDisplayed());
    }

    @When("I scroll to the bottom")
    public void scrollToBottom() {
        homePage.scrollToTheBottom();
    }

    @When("I click the Top button")
    public void clickTopButton() {
        homePage.getBottomBarSection().clickOnTopButton();
    }

    @Then("I should be redirected to the top of the page")
    public void verifyRedirectedToTop() {
        Assert.assertTrue("Top Title is not displayed.", homePage.topTitleIsDisplayed());
    }
}
