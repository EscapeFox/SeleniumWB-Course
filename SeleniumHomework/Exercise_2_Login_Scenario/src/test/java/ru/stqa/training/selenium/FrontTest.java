package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class FrontTest extends TestBase {

    @Test
    public void loginLitecart(){
        driver.get(props.getProperty("baseUrl"));
        driver.findElement(By.name("username")).sendKeys(props.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.name("login")).click();
    }
}
