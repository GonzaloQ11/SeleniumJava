package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }
    @Test
    public void verifySuccessfulLogin() {
        loginPage.login("Admin", "admin123");
        Assert.assertTrue( dashboardPage.isDashboardDisplayed(), "Login Failed!");
    }

    @Test
    public void verifyUsernameAndPasswordAreRequired() {
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.verifyUsernameIsRequired());
        Assert.assertEquals(loginPage.getUsernameErrorText(), "Required");
        Assert.assertTrue(loginPage.verifyPasswordIsRequired());
        Assert.assertEquals(loginPage.getPasswordErrorText(), "Required");
    }

    @Test
    public void verifyInvalidCredentialsErrorMessage() {
        loginPage.login("Admin", "wrongPassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }

    @Test
    public void verifyUserCanLoginAfterFailedAttempt() {
        loginPage.login("Admin", "wrongPassword");
        loginPage.login("Admin", "admin123");
        Assert.assertTrue( dashboardPage.isDashboardDisplayed(), "Login Failed!");
    }
}
