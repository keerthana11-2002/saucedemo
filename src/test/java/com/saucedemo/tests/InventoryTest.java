package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class InventoryTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToInventory() {
        loginAsStandardUser();
    }

     
    @Test(groups = {"smoke", "regression"})
     public void testInventoryDisplaysSixProducts() {
        int count = inventoryPage.getProductCount();
        Assert.assertEquals(count, 6,
                "Expected 6 products on inventory page, but found: " + count);
    }

     

     
    @Test(groups = {"functional"})
     public void testDefaultSortIsNameAtoZ() {
        List<String> names = inventoryPage.getProductNames();
        for (int i = 0; i < names.size() - 1; i++) {
            Assert.assertTrue(
                names.get(i).compareToIgnoreCase(names.get(i + 1)) <= 0,
                "Products not sorted A→Z at index " + i
                + ": '" + names.get(i) + "' came before '" + names.get(i + 1) + "'"
            );
        }
    }

     
}
