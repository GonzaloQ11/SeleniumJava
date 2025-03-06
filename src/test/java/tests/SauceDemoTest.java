package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoPage;
import pages.SauceInventoryPage;
import pages.SauceShoppingCartPage;

public class SauceDemoTest extends BaseTest {

    @Test
    public void verifySuccessfulLoginWithStandardUser() {
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        SauceInventoryPage sauceInventoryPage = new SauceInventoryPage(driver);

        sauceDemoPage.enterUsername("standard_user");
        sauceDemoPage.enterPassword("secret_sauce");
        sauceDemoPage.clickOnLoginButton();
        boolean isInventoryPageDisplayed = sauceInventoryPage.isInventoryListDisplayed();
        Assert.assertTrue(isInventoryPageDisplayed);
    }

    @Test
    public void verifyUserCanAddMultipleItemsToCart() {
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        SauceInventoryPage sauceInventoryPage = new SauceInventoryPage(driver);
        SauceShoppingCartPage sauceShoppingCartPage = new SauceShoppingCartPage(driver);

        sauceDemoPage.login("standard_user", "secret_sauce");
        boolean isInventoryPageDisplayed = sauceInventoryPage.isInventoryListDisplayed();
        Assert.assertTrue(isInventoryPageDisplayed);

        sauceInventoryPage.addProduct("Sauce Labs Backpack");
        sauceInventoryPage.addProduct("Sauce Labs Bike Light");
        sauceInventoryPage.clickOnShoppingCartIcon();

        boolean isSauceShoppingCartPageDisplayed = sauceShoppingCartPage.isCartListDisplayed();
        Assert.assertTrue(isSauceShoppingCartPageDisplayed);
    }

    @Test
    public void verifyShoppingCartQuantity() {
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        SauceInventoryPage sauceInventoryPage = new SauceInventoryPage(driver);
        SauceShoppingCartPage sauceShoppingCartPage = new SauceShoppingCartPage(driver);

        sauceDemoPage.login("standard_user", "secret_sauce");
        boolean isInventoryPageDisplayed = sauceInventoryPage.isInventoryListDisplayed();
        Assert.assertTrue(isInventoryPageDisplayed);

        sauceInventoryPage.addProduct("Sauce Labs Backpack");
        sauceInventoryPage.addProduct("Sauce Labs Bike Light");
        String shoppingCartQuantity = sauceInventoryPage.getShoppingCartQuantity();
        Assert.assertEquals(shoppingCartQuantity, "2");
    }
}
