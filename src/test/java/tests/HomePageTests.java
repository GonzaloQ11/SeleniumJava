package tests;

import base.BaseTest;
import config.ConfigReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.CharacterPage;
import pages.HomePage;
import pages.LauncherPage;
import pages.NewCardPage;

import java.util.List;

public class HomePageTests extends BaseTest {

    private LauncherPage launcherPage;
    private HomePage homePage;
    private CharacterPage characterPage;
    private NewCardPage newCardPage;

    @Before
    public void setupPagesAndLaunchProject() {
        launcherPage = new LauncherPage(driver);
        homePage = new HomePage(driver);
        characterPage = new CharacterPage(driver);
        newCardPage = new NewCardPage(driver);
        launcherPage.pageFinishedLoading();
        launcherPage.clickOnRunButton();
    }

    @Test
    public void verifyCardsInformation() {
        List<WebElement> cards = homePage.getListOfCards();
        for (WebElement card : cards) {
            Assert.assertTrue("Card image is not displayed.", homePage.isCardImageDisplayed(card));
            Assert.assertTrue("Card name is not displayed.", homePage.isCardNameDisplayed(card));
            Assert.assertTrue("Card link is not displayed.", homePage.isCardLinkDisplayed(card));
        }
    }

    @Test
    public void verifyVerDetalleLinkOpensCharacterView() {
        List<WebElement> cards = homePage.getListOfCards();
        homePage.clickOnCardLink(cards, 1);
        Assert.assertTrue("Character 1 view is not open", homePage.getCurrentURL().contains("character/1"));
    }

    @Test
    public void verifyCharacterViewInformation() {
        List<WebElement> cards = homePage.getListOfCards();
        homePage.clickOnCardLink(cards, 1);
        Assert.assertTrue("Character name is not displayed.", characterPage.characterNameIsDisplayed());
        Assert.assertTrue("Character gender is not displayed.", characterPage.characterGenderIsDisplayed());
        Assert.assertTrue("Character specie is not displayed.", characterPage.characterSpecieIsDisplayed());
        Assert.assertTrue("Character status is not displayed.", characterPage.characterStatusIsDisplayed());
    }

    @Test
    public void verifyHomeButton() {
        List<WebElement> cards = homePage.getListOfCards();
        homePage.clickOnCardLink(cards, 1);
        characterPage.getBottomBarSection().clickOnHomeButton();
        Assert.assertEquals("User is not redirected to home page.", ConfigReader.getProperty("baseUrl"), homePage.getCurrentURL());
    }

    @Test
    public void verifyAddButton() {
        homePage.getBottomBarSection().clickOnAddButton();
        /*
            There is an empty screen after clicking on "Add" button.
            The form element is not displayed, making this test fail.
        */
        Assert.assertTrue("Form is not displayed.", newCardPage.verifyFormIsDisplayed());
    }

    @Test
    public void verifyTopButton() {
        homePage.scrollToTheBottom();
        homePage.getBottomBarSection().clickOnTopButton();
        Assert.assertTrue("Top Title is not displayed.", homePage.topTitleIsDisplayed());
    }
}
