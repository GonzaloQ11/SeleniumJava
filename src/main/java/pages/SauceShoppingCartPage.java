package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceShoppingCartPage extends BasePage {

    @FindBy(css = "[data-test='cart-list']")
    private WebElement cartList;

    public SauceShoppingCartPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCartListDisplayed() {
        return this.cartList.isDisplayed();
    }
}
