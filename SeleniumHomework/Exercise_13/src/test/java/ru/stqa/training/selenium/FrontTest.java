package ru.stqa.training.selenium;

import com.google.common.base.CharMatcher;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FrontTest extends TestBase {

    @Test
    public void workWithTheCart(){
        
        int countOfProduct = 3;
        WebElement product;
        WebElement cartQuantity;
        WebElement addToCartButton;
        WebElement homeButton;
        String code;
        ArrayList<String> productCodes = new ArrayList<>();
        int quantityCount;


        for(int i = 0; i < countOfProduct; i++){
            product = driver.findElement(By.cssSelector("div.content a[title]"));
            product.click();
            code = driver.findElement(By.cssSelector("span.sku")).getText();
            if(!productCodes.contains(code)) {
                productCodes.add(code);
            }
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#cart span.quantity"))));
            cartQuantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
            quantityCount = Integer.parseInt(cartQuantity.getAttribute("textContent"));
            if(isElementPresent(By.cssSelector("select[name='options[Size]']"))){
                Select sizeSelect = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                sizeSelect.selectByIndex(1);
            }
            addToCartButton = driver.findElement(By.cssSelector("button[name='add_cart_product']"));
            addToCartButton.click();
            wait.until(ExpectedConditions.attributeToBe(cartQuantity, "textContent", Integer.toString(quantityCount + 1)));
            //Возврат на главную страницу
            homeButton = driver.findElement(By.cssSelector("li.general-0 a"));
            homeButton.click();
        }

        //Переход в корзину
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("li.shortcut a.inact"))));
        //Действия на странице корзины
        driver.findElement(By.cssSelector("li.shortcut a.inact")).click();
        int rowsCount = driver.findElements(By.cssSelector("table.dataTable tr")).size();
        for (int i = 0; i < productCodes.size(); i++){
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            if(!isElementPresent(By.cssSelector("div#content em"))){
                wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable tr"), rowsCount - (i + 1)));
            }
        }
    }
}

