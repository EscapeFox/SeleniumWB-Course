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
        driver.get("http://localhost/litecart/en/");
        //Переход на страницу товара
        WebElement product = driver.findElement(By.cssSelector("div#box-campaigns a[title='Yellow Duck']"));
        product.click();
        //Действия(1) на странице товара
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#cart span.quantity"))));
        WebElement cartQuantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
        int quantityCount = Integer.parseInt(cartQuantity.getAttribute("textContent"));
        Select sizeSelect = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
        sizeSelect.selectByValue("Small");
        WebElement addToCartButton = driver.findElement(By.cssSelector("button[name='add_cart_product']"));
        addToCartButton.click();
        wait.until(ExpectedConditions.attributeToBe(cartQuantity, "textContent", Integer.toString(quantityCount + 1)));
        //Возврат на главную страницу
        WebElement homeButton = driver.findElement(By.cssSelector("li.general-0 a"));
        homeButton.click();


        //Переход на страницу товара
        product = driver.findElement(By.cssSelector("div#box-campaigns a[title='Yellow Duck']"));
        product.click();
        //Действия(2) на странице товара
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#cart span.quantity"))));
        cartQuantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
        quantityCount = Integer.parseInt(cartQuantity.getAttribute("textContent"));
        sizeSelect = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
        sizeSelect.selectByValue("Medium");
        addToCartButton = driver.findElement(By.cssSelector("button[name='add_cart_product']"));
        addToCartButton.click();
        wait.until(ExpectedConditions.attributeToBe(cartQuantity, "textContent", Integer.toString(quantityCount + 1)));
        //Возврат на главную страницу
        homeButton = driver.findElement(By.cssSelector("li.general-0 a"));
        homeButton.click();

        //Переход на страницу товара
        product = driver.findElement(By.cssSelector("div#box-campaigns a[title='Yellow Duck']"));
        product.click();
        //Действия(3) на странице товара
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#cart span.quantity"))));
        cartQuantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
        quantityCount = Integer.parseInt(cartQuantity.getAttribute("textContent"));
        sizeSelect = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
        sizeSelect.selectByValue("Large");
        addToCartButton = driver.findElement(By.cssSelector("button[name='add_cart_product']"));
        addToCartButton.click();
        wait.until(ExpectedConditions.attributeToBe(cartQuantity, "textContent", Integer.toString(quantityCount + 1)));
        //Возврат на главную страницу
        homeButton = driver.findElement(By.cssSelector("li.general-0 a"));
        homeButton.click();

        //Переход в корзину
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        //Действия на странице корзины
        driver.findElement(By.cssSelector("li.shortcut a.inact")).click();
        int rowsCount = driver.findElements(By.cssSelector("table.dataTable tr")).size();

        driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable tr"), rowsCount - 1));

        driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable tr"), rowsCount - 2));

        driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
    }
}
