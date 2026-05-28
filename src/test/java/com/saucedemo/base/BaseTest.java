package com.saucedemo.base;

import com.saucedemo.pages.*;
import com.saucedemo.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver         driver;
    protected LoginPage         loginPage;
    protected InventoryPage     inventoryPage;
    protected CartPage          cartPage;
    protected CheckoutPage      checkoutPage;
    protected ProductDetailPage productDetailPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(ConfigReader.BASE_URL);

        loginPage         = new LoginPage(driver);
        inventoryPage     = new InventoryPage(driver);
        cartPage          = new CartPage(driver);
        checkoutPage      = new CheckoutPage(driver);
        productDetailPage = new ProductDetailPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void loginAsStandardUser() {
        loginPage.login(ConfigReader.STANDARD_USER, ConfigReader.PASSWORD);
    }
}
