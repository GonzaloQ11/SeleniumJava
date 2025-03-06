package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceInventoryPage extends BasePage {

    @FindBy(css = "[data-test='inventory-list']")
    private WebElement inventoryList;
    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement shoppingCartIcon;
    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement shoppingCartIconQuantity;

    private String addProductByNameSelector = "//*[text()='%s']//following::button[contains(@data-test, 'add-to-cart')]";

    public SauceInventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isInventoryListDisplayed() {
        return this.inventoryList.isDisplayed();
    }

    public void clickOnShoppingCartIcon() {
        this.shoppingCartIcon.click();
    }

    public void addProduct (String productName) {
        String productSelector = String.format(addProductByNameSelector, productName);
        driver.findElement(By.xpath(productSelector)).click();
    }

    public String getShoppingCartQuantity() {
        return this.shoppingCartIconQuantity.getText();
    }
}
