package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test
    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Login Failed!");
    }

    @Test
    public void verifyUsernameAndPasswordAreRequired() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.verifyUsernameIsRequired());
        Assert.assertEquals(loginPage.getUsernameErrorText(), "Required");
        Assert.assertTrue(loginPage.verifyPasswordIsRequired());
        Assert.assertEquals(loginPage.getPasswordErrorText(), "Required");
    }

    @Test
    public void verifyInvalidCredentialsErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "wrongPassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }
}
