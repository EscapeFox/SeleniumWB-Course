package ru.stqa.training.selenium.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.training.selenium.pages.CartPage;
import ru.stqa.training.selenium.pages.MainPage;
import ru.stqa.training.selenium.pages.ProductPage;

import java.util.concurrent.TimeUnit;

public class Application {
    public static WebDriver driver;
    private MainPage mainPage;
    private CartPage cartPage;
    private ProductPage productPage;

    public Application(){
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }
    public void chooseProduct(){
        mainPage.open().product.click();
    }

    public void addToCart(){
        productPage.selectSize();
        productPage.submitAddToCart();
    }

    public void removeAllProductFromCart() {
        cartPage.open();
        int count;
        if(cartPage.shortcut().size() > 0) {
            cartPage.shortcut().get(0).click();
            count = cartPage.shortcut().size();
        }else
            count = 1;

        for(int i = 1; i <= count; i++ ){
            cartPage.removeItem(i);
        }

    }

    public void quit(){
        driver.quit();
    }

    // Проверка наличия(с неявными ожданиями)
    public static boolean isElementPresent(By locator){
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
}
