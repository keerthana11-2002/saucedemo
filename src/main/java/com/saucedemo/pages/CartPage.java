package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    private static final By CART_ITEMS          = By.cssSelector(".cart_item");
    private static final By ITEM_NAMES          = By.cssSelector(".inventory_item_name");
    private static final By ITEM_PRICES         = By.cssSelector(".inventory_item_price");
    private static final By ITEM_QUANTITIES     = By.cssSelector(".cart_quantity");
    private static final By REMOVE_BTNS         = By.cssSelector("[data-test^='remove']");
    private static final By CHECKOUT_BTN        = By.id("checkout");
    private static final By CONTINUE_SHOP_BTN   = By.id("continue-shopping");
    private static final By PAGE_TITLE          = By.cssSelector(".title");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageLoaded() {
        return isDisplayed(CHECKOUT_BTN);
    }

    public int getCartItemCount() {
        return findAll(CART_ITEMS).size();
    }

    public List<String> getCartItemNames() {
        return findAll(ITEM_NAMES).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getCartItemPrices() {
        return findAll(ITEM_PRICES).stream()
                .map(el -> Double.parseDouble(el.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void removeItemByIndex(int index) {
        findAll(REMOVE_BTNS).get(index).click();
    }

    public void removeItemByName(String productName) {
        By removeBtn = By.cssSelector("[data-test='remove-" +
                productName.toLowerCase().replace(" ", "-") + "']");
        click(removeBtn);
    }

    public void clickCheckout() {
        click(CHECKOUT_BTN);
    }

    public void clickContinueShopping() {
        click(CONTINUE_SHOP_BTN);
    }

    public boolean isItemInCart(String productName) {
        return getCartItemNames().stream()
                .anyMatch(name -> name.equalsIgnoreCase(productName));
    }

    public String getPageTitle() {
        return getText(PAGE_TITLE);
    }
}
