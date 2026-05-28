package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

 
    @Test(groups = {"smoke", "regression"})
           
    public void testValidLoginStandardUser() {
        loginPage.login(ConfigReader.STANDARD_USER, ConfigReader.PASSWORD);

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "URL should contain 'inventory' after successful login");
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded(),
                "Inventory page should be fully loaded after login");
    }

     
    @Test(groups = {"smoke", "regression"})
     public void testLockedOutUserSeesError() {
        loginPage.login(ConfigReader.LOCKED_USER, ConfigReader.PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be visible for locked_out_user");
        Assert.assertTrue(loginPage.getErrorMessage()
                        .contains("locked out"),
                "Error should mention 'locked out'. Actual: " + loginPage.getErrorMessage());
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com")
                       && !driver.getCurrentUrl().contains("inventory"),
                "Locked user must NOT reach inventory page");
    }

     
}
