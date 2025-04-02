package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CharacterPage;
import pages.HomePage;

import java.util.List;

public class HomePageTests extends BaseTest {

    private HomePage homePage;
    private CharacterPage characterPage;

    @BeforeMethod
    public void setupPages () {
        homePage = new HomePage(driver);
        characterPage = new CharacterPage(driver);
        homePage.pageFinishedLoading();
        homePage.clickOnRunButton();
    }

    @Test
    public void verifyCardsInformation() {
        List<WebElement> cards = homePage.getListOfCards();
        for (WebElement card : cards) {
            Assert.assertTrue(homePage.isCardImageDisplayed(card), "Card image is not displayed.");
            Assert.assertTrue(homePage.isCardNameDisplayed(card), "Card name is not displayed.");
            Assert.assertTrue(homePage.isCardLinkDisplayed(card), "Card link is not displayed.");
        }
    }

    @Test
    public void verifyVerDetalleLinkOpensCharacterView() {
        List<WebElement> cards = homePage.getListOfCards();
        homePage.clickOnCardLink(cards, 1);
        Assert.assertTrue(homePage.getCurrentURL().contains("character/1"), "Character 1 view is not open");
    }

    @Test
    public void verifyCharacterViewInformation() {
        List<WebElement> cards = homePage.getListOfCards();
        homePage.clickOnCardLink(cards, 1);
        Assert.assertTrue(characterPage.characterNameIsDisplayed(), "Character name is not displayed.");
        Assert.assertTrue(characterPage.characterGenderIsDisplayed(), "Character gender is not displayed.");
        Assert.assertTrue(characterPage.characterSpecieIsDisplayed(), "Character specie is not displayed.");
        Assert.assertTrue(characterPage.characterStatusIsDisplayed(), "Character status is not displayed.");
    }
}
