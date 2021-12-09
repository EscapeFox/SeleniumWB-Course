package ru.stqa.training.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page{
    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open(){
        driver.get("http://localhost/litecart/en/");
        return this;
    }

    @FindBy(css = "div.content a[title]")
    public WebElement product;
}
