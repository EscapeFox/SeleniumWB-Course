package ru.stqa.training.selenium;

import com.google.common.base.CharMatcher;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FrontTest extends TestBase {

    @Test
    public void newUserRegistration() {
        driver.get("http://localhost/litecart/en/");

        driver.findElement(By.cssSelector("form[name=login_form] a")).click();
        WebElement registrationForm = driver.findElement(By.cssSelector("form[name=customer_form]"));

        List<WebElement> formElements = registrationForm.findElements(By.cssSelector("input:not([type=hidden])"));
        //System.out.println(formElements.size());
        formElements.get(0).sendKeys("111-11-1111");
        formElements.get(1).sendKeys("Job");
        formElements.get(2).sendKeys("Ivan");
        formElements.get(3).sendKeys("Petrov");
        formElements.get(4).sendKeys("1 Beverly Hills");
        formElements.get(5).sendKeys("2 Beverly Hills");
        formElements.get(6).sendKeys("69055");
        formElements.get(7).sendKeys("New York");
        formElements.get(8).sendKeys("bethroom@gmail.com");
        formElements.get(9).sendKeys("+18003211455");
//        formElements.get(0).sendKeys("111-11-1111");
        formElements.get(11).sendKeys("qwerty123");
        formElements.get(12).sendKeys("qwerty123");

        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        countrySelect.selectByVisibleText("United States");
        Select zoneSelect = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        zoneSelect.selectByVisibleText("Arizona");

        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("button[type=submit]"))).click().perform();
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("div#box-account li:last-child a"))).click().perform();

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys("bethroom@gmail.com");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("qwerty123");
        driver.findElement(By.cssSelector("button[name=login]")).click();

        driver.findElement(By.cssSelector("div#box-account li:last-child a")).click();
    }
}
