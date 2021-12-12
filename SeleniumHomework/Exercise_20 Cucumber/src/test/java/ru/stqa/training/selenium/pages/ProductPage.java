package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static ru.stqa.training.selenium.app.Application.isElementPresent;

public class ProductPage extends Page{
    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

     public void selectSize(){
         if(isElementPresent(By.cssSelector("select[name='options[Size]']"))){
             Select sizeSelect = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
             sizeSelect.selectByIndex(1);
         }
     }

    public void submitAddToCart(){
        WebElement cartQuantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
        int quantity = Integer.parseInt(cartQuantity.getAttribute("textContent"));
        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.attributeToBe(cartQuantity, "textContent", Integer.toString(quantity + 1)));
    }

}
