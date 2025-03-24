package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ForgottenPasswordPage;
import pages.LoginPage;


public class ForgottenPasswordsTests extends BaseTest {
    LoginPage loginPage;
    ForgottenPasswordPage forgottenPasswordPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        forgottenPasswordPage = new ForgottenPasswordPage(driver);
    }

    @Test
    public void userCanCancelForgottenPasswordAction() {
        loginPage.clickOnForgottenPasswordLink();
        forgottenPasswordPage.clickCancelButton();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login screen is not displayed.");
    }

    @Test
    public void usernameRequired() {
        loginPage.clickOnForgottenPasswordLink();
        Assert.assertTrue(forgottenPasswordPage.isForgottenPasswordPageDisplayed(), "Forgotten Password page is not displayed");
        forgottenPasswordPage.clickOnResetPasswordButton();
        Assert.assertTrue(forgottenPasswordPage.verifyUsernameIsRequired(), "Error text 'Required' is not displayed");
    }
}
