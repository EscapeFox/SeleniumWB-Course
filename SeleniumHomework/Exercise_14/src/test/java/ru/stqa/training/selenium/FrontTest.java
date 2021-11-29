package ru.stqa.training.selenium;

import com.google.common.base.CharMatcher;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FrontTest extends TestBase {

    @Test
    public void openLinkInNewWindow(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys(props.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.name("login")).click();
        //Переход на страницу создания страны
        driver.findElement(By.cssSelector("a.button")).click();
        //Действия на странице
        List<WebElement> outerLink = driver.findElements(By.cssSelector("form a[target='_blank']"));

        for(int i = 0; i < outerLink.size(); i++){
            String originalWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            outerLink.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(existingWindows.size() + 1));
            Set<String> allWindows = driver.getWindowHandles();
            allWindows.removeAll(existingWindows);
            String newWindow = allWindows.iterator().next();
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }
}
