package ru.stqa.training.selenium;

import com.google.common.base.CharMatcher;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FrontTest extends TestBase {

    @Test
    public void addNewProduct(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.name("username")).sendKeys(props.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("td#content a.button:last-child")).click();
        List<WebElement> tabElements = driver.findElements(By.cssSelector("div.tabs li"));
        List<WebElement> generalTabElements = driver.findElements(By.cssSelector("div#tab-general tr"));
        String productName = "MyProduct";
        File file = new File("myFile.jpg");


        generalTabElements.get(0).findElement(By.cssSelector("input[value='1']")).click();

        generalTabElements.get(1).findElement(By.cssSelector("input")).sendKeys(productName);
        generalTabElements.get(2).findElement(By.cssSelector("input")).sendKeys("RD666");
        generalTabElements.get(11).findElement(By.cssSelector("input[type='checkbox']")).click();
        generalTabElements.get(12).findElement(By.cssSelector("input[type='checkbox']")).click();
        generalTabElements.get(15).findElement(By.cssSelector("input[name='quantity']")).sendKeys(Keys.DELETE +"5");


        generalTabElements.get(17).findElement(By.cssSelector("input[type='file']"))
                .sendKeys(file.getAbsolutePath());

        generalTabElements.get(19).findElement(By.cssSelector("input[type='date']")).sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT + "12112020");
        generalTabElements.get(20).findElement(By.cssSelector("input[type='date']")).sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT + "12122022");

        //
        //

        tabElements.get(1).click();
        List<WebElement> informationTabElements = driver.findElements(By.cssSelector("div#tab-information tr"));

        Select manufacturer = new Select(informationTabElements.get(0).findElement(By.cssSelector("select")));
        manufacturer.selectByValue("1");

        informationTabElements.get(2).findElement(By.cssSelector("input")).sendKeys("my");
        informationTabElements.get(3).findElement(By.cssSelector("input")).sendKeys("My product");
        informationTabElements.get(4).findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("My Product create for selenium course");
        informationTabElements.get(5).findElement(By.cssSelector("input")).sendKeys("My product");
        informationTabElements.get(6).findElement(By.cssSelector("input")).sendKeys("My product for");

        //
        //

        tabElements.get(3).click();
        driver.findElement(By.cssSelector("div#tab-prices input[name='purchase_price']")).sendKeys("1");
        Select purchasePrice = new Select(driver.findElement(By.cssSelector("div#tab-prices select[name='purchase_price_currency_code']")));
        purchasePrice.selectByValue("USD");
        driver.findElement(By.cssSelector("div#tab-prices input[name='prices[USD]']")).sendKeys( "10");
        driver.findElement(By.cssSelector("button[name='save']")).click();
        driver.findElement(By.xpath("//table[@class='dataTable']//td//a[text()='" + productName + "']"));

    }

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

        String email = RandomString.make(4);

        formElements.get(8).sendKeys(email + "@gmail.com");
        formElements.get(9).sendKeys("+18003211455");
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
