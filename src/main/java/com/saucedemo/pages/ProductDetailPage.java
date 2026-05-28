package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {

    private static final By PRODUCT_NAME    = By.cssSelector(".inventory_details_name");
    private static final By PRODUCT_DESC    = By.cssSelector(".inventory_details_desc");
    private static final By PRODUCT_PRICE   = By.cssSelector(".inventory_details_price");
    private static final By ADD_TO_CART_BTN = By.cssSelector("[data-test^='add-to-cart']");
    private static final By REMOVE_BTN      = By.cssSelector("[data-test^='remove']");
    private static final By BACK_BTN        = By.id("back-to-products");
    private static final By CART_BADGE      = By.cssSelector(".shopping_cart_badge");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDetailPageLoaded() {
        return isDisplayed(PRODUCT_NAME) && isDisplayed(PRODUCT_PRICE);
    }

    public String getProductName() {
        return getText(PRODUCT_NAME);
    }

    public String getProductPrice() {
        return getText(PRODUCT_PRICE);
    }

    public String getProductDescription() {
        return getText(PRODUCT_DESC);
    }

    public void clickAddToCart() {
        click(ADD_TO_CART_BTN);
    }

    public void clickRemove() {
        click(REMOVE_BTN);
    }

    public void clickBackToProducts() {
        click(BACK_BTN);
    }

    public int getCartBadgeCount() {
        if (!isDisplayed(CART_BADGE)) return 0;
        return Integer.parseInt(getText(CART_BADGE));
    }

    public boolean isAddToCartButtonVisible() {
        return isDisplayed(ADD_TO_CART_BTN);
    }

    public boolean isRemoveButtonVisible() {
        return isDisplayed(REMOVE_BTN);
    }
}
