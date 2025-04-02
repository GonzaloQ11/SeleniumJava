package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.List;

public class HomePageTests extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setupPages () {
        homePage = new HomePage(driver);
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
    public void verifyCardView() {
        List<WebElement> cards = homePage.getListOfCards();
        homePage.clickOnCardLink(cards, 1);
        Assert.assertTrue(homePage.getCurrentURL().contains("character/1"), "Character 1 view is not open");
    }
}
