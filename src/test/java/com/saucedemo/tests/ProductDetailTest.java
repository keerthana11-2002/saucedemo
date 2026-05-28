package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginAndOpenProduct() {
        loginAsStandardUser();
        inventoryPage.clickProductName("Sauce Labs Backpack");
    }

     
    @Test(groups = {"smoke", "regression"})
     public void testProductDetailPageLoads() {
        Assert.assertTrue(productDetailPage.isDetailPageLoaded(),
                "Product detail page should load with name and price visible");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"),
                "URL should contain 'inventory-item' for detail page");
    }

     @Test(groups = {"regression"})
     public void testProductNameMatchesInventory() {
        String nameOnDetail = productDetailPage.getProductName();
        Assert.assertEquals(nameOnDetail, "Sauce Labs Backpack",
                "Product name on detail page should match the one clicked");
    }

    // TC_034 — Price on detail page is $29.99
    @Test(groups = {"regression"},
          description = "TC_034: Sauce Labs Backpack price on detail page should be $29.99")
    public void testProductPriceOnDetailPage() {
        Assert.assertEquals(productDetailPage.getProductPrice(), "$29.99",
                "Product price mismatch on detail page");
    }

     @Test(groups = {"smoke", "regression"})
     public void testAddToCartFromDetailPage() {
        Assert.assertTrue(productDetailPage.isAddToCartButtonVisible(),
                "Add to Cart button should be visible before adding");

        productDetailPage.clickAddToCart();

        Assert.assertEquals(productDetailPage.getCartBadgeCount(), 1,
                "Cart badge should show 1 after adding from detail page");
        Assert.assertTrue(productDetailPage.isRemoveButtonVisible(),
                "Remove button should replace Add to Cart after adding");
    }

     @Test(groups = {"regression"})
     public void testRemoveFromDetailPage() {
        productDetailPage.clickAddToCart();
        Assert.assertEquals(productDetailPage.getCartBadgeCount(), 1, "Badge should be 1 after add");

        productDetailPage.clickRemove();

        Assert.assertEquals(productDetailPage.getCartBadgeCount(), 0,
                "Cart badge should disappear after Remove on detail page");
        Assert.assertTrue(productDetailPage.isAddToCartButtonVisible(),
                "Add to Cart button should return after removing");
    }

     
     
}
