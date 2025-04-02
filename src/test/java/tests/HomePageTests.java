package tests;

import base.BaseTest;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
            Assert.isTrue(homePage.isCardImageDisplayed(card), "Card image is not displayed.");
            Assert.isTrue(homePage.isCardNameDisplayed(card), "Card name is not displayed.");
            Assert.isTrue(homePage.isCardLinkDisplayed(card), "Card link is not displayed.");
        }

    }
}
