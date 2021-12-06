package ru.stqa.training.selenium;

import com.google.common.base.CharMatcher;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
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
    public void test1() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys(props.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.name("login")).click();

        List<WebElement> list = driver.findElements(By.cssSelector("table.dataTable a:not([title='Edit'])[href*=product]"));

        for(int i = 0; i < list.size();i++){
            list = driver.findElements(By.cssSelector("table.dataTable a:not([title='Edit'])[href*=product]"));
            list.get(i).click();
            assertTrue(driver.manage().logs().get("browser").getAll().size() == 0);
            driver.navigate().back();
        }
    }
}
