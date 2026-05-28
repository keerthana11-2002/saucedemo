package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

	private static final By INVENTORY_ITEMS = By.cssSelector(".inventory_item");
	private static final By PRODUCT_NAMES = By.cssSelector(".inventory_item_name");
	private static final By PRODUCT_PRICES = By.cssSelector(".inventory_item_price");
	private static final By SORT_DROPDOWN = By.cssSelector("[data-test='product-sort-container']");
	private static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");
	private static final By CART_ICON = By.cssSelector(".shopping_cart_link");
	private static final By PAGE_TITLE = By.cssSelector(".title");
	private static final By BURGER_MENU = By.id("react-burger-menu-btn");
	private static final By MENU_LOGOUT = By.id("logout_sidebar_link");

	public InventoryPage(WebDriver driver) {
		super(driver);
	}

	public boolean isInventoryPageLoaded() {
		return isDisplayed(SORT_DROPDOWN) && isDisplayed(CART_ICON);
	}

	public int getProductCount() {
		return findAll(INVENTORY_ITEMS).size();
	}

	public List<String> getProductNames() {
		return findAll(PRODUCT_NAMES).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public List<Double> getProductPrices() {
		return findAll(PRODUCT_PRICES).stream().map(el -> Double.parseDouble(el.getText().replace("$", "")))
				.collect(Collectors.toList());
	}

	public void sortBy(String optionText) {
		selectByVisibleText(SORT_DROPDOWN, optionText);
	}

	public boolean isCartBadgeVisible() {
		return isDisplayed(CART_BADGE);
	}

	public void clickCart() {
		click(CART_ICON);
	}

	public void clickProductName(String name) {
		By link = By.linkText(name);
		click(link);
	}

	public String getPageTitle() {
		return getText(PAGE_TITLE);
	}

	public void openMenu() {
		click(BURGER_MENU);
	}

	public void clickMenuLogout() {
		openMenu();
		click(MENU_LOGOUT);
	}

}
