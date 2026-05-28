package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    // Step 1 — Customer Info
    private static final By FIRST_NAME_INPUT  = By.id("first-name");
    private static final By LAST_NAME_INPUT   = By.id("last-name");
    private static final By ZIP_CODE_INPUT    = By.id("postal-code");
    private static final By CONTINUE_BTN      = By.id("continue");
    private static final By CANCEL_BTN        = By.id("cancel");
    private static final By STEP1_ERROR       = By.cssSelector("[data-test='error']");

    // Step 2 — Overview
    private static final By ITEM_TOTAL_LABEL  = By.cssSelector(".summary_subtotal_label");
    private static final By TAX_LABEL         = By.cssSelector(".summary_tax_label");
    private static final By TOTAL_LABEL       = By.cssSelector(".summary_total_label");
    private static final By FINISH_BTN        = By.id("finish");
    private static final By OVERVIEW_ITEMS    = By.cssSelector(".cart_item");

    // Step 3 — Complete
    private static final By COMPLETE_HEADER   = By.cssSelector(".complete-header");
    private static final By COMPLETE_TEXT     = By.cssSelector(".complete-text");
    private static final By BACK_HOME_BTN     = By.id("back-to-products");
    private static final By PONY_IMAGE        = By.cssSelector(".pony_express");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // ── Step 1 ─────────────────────────────────────────────────────────
    public void enterFirstName(String name) {
        type(FIRST_NAME_INPUT, name);
    }

    public void enterLastName(String name) {
        type(LAST_NAME_INPUT, name);
    }

    public void enterZipCode(String zip) {
        type(ZIP_CODE_INPUT, zip);
    }

    public void fillCustomerInfo(String firstName, String lastName, String zip) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zip);
    }

    public void clickContinue() {
        click(CONTINUE_BTN);
    }

    public void clickCancel() {
        click(CANCEL_BTN);
    }

    public boolean isStep1ErrorDisplayed() {
        return isDisplayed(STEP1_ERROR);
    }

    public String getStep1ErrorMessage() {
        return getText(STEP1_ERROR);
    }

    // ── Step 2 ─────────────────────────────────────────────────────────
    public String getItemTotal() {
        return getText(ITEM_TOTAL_LABEL);
    }

    public String getTax() {
        return getText(TAX_LABEL);
    }

    public String getOrderTotal() {
        return getText(TOTAL_LABEL);
    }

    public double getItemTotalValue() {
        return Double.parseDouble(getItemTotal().replace("Item total: $", ""));
    }

    public double getTaxValue() {
        return Double.parseDouble(getTax().replace("Tax: $", ""));
    }

    public double getOrderTotalValue() {
        return Double.parseDouble(getOrderTotal().replace("Total: $", ""));
    }

    public boolean isTotalCorrect() {
        double expected = Math.round((getItemTotalValue() + getTaxValue()) * 100.0) / 100.0;
        double actual   = Math.round(getOrderTotalValue() * 100.0) / 100.0;
        return expected == actual;
    }

    public int getOverviewItemCount() {
        return findAll(OVERVIEW_ITEMS).size();
    }

    public void clickFinish() {
        click(FINISH_BTN);
    }

    // ── Step 3 ─────────────────────────────────────────────────────────
    public boolean isOrderComplete() {
        return isDisplayed(COMPLETE_HEADER);
    }

    public String getCompleteHeader() {
        return getText(COMPLETE_HEADER);
    }

    public String getCompleteText() {
        return getText(COMPLETE_TEXT);
    }

    public boolean isPonyImageDisplayed() {
        return isDisplayed(PONY_IMAGE);
    }

    public void clickBackToProducts() {
        click(BACK_HOME_BTN);
    }
}
