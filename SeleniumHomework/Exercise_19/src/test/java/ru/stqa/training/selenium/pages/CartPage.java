package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ru.stqa.training.selenium.app.Application.isElementPresent;

public class CartPage extends Page{
    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage open(){
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    public int getTableRowsCount(){
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("tr.footer")));
        return driver.findElements(By.cssSelector("table.dataTable tr")).size();
    }

    public int getQuantity(){
        return Integer.parseInt(driver.findElement(By.cssSelector("div#cart span.quantity")).getText());
    }

    public List<WebElement> dataTable(){
        return driver.findElements(By.cssSelector("table.dataTable"));
    }

    public void removeItem(){
        int rowsCount = getTableRowsCount();
        removeCartItemButton().click();
        if(!isElementPresent(By.cssSelector("div#content em")))
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable tr"), rowsCount - (1)));
    }

    public WebElement removeCartItemButton(){
        return driver.findElement(By.cssSelector("button[name='remove_cart_item']"));
    }

    public List<WebElement> shortcut(){
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("tr.footer")));
        return driver.findElements(By.cssSelector("li.shortcut a.inact"));
    }
}
