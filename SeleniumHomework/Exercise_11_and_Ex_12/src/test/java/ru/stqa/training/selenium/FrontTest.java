package ru.stqa.training.selenium;

import com.google.common.base.CharMatcher;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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

        String productName = RandomString.make(4);
        File file = new File("myFile.jpg");

        WebElement generalTab = driver.findElement(By.cssSelector("div#tab-general"));
        generalTab.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        generalTab.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        generalTab.findElement(By.cssSelector("input[name='code']")).sendKeys("RD666");
        generalTab.findElement(By.cssSelector("input[type='checkbox'][value='1-2']")).click();
        generalTab.findElement(By.cssSelector("input[type='checkbox'][value='1-1']")).click();
        generalTab.findElement(By.cssSelector("input[name='quantity']")).sendKeys(Keys.DELETE +"5");
        generalTab.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(file.getAbsolutePath());
        generalTab.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT + "12112020");
        generalTab.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT + "12122022");

        //
        //

        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        WebElement informationTab = driver.findElement(By.cssSelector("div#tab-information"));
        Select manufacturer = new Select(informationTab.findElement(By.cssSelector("select[name='manufacturer_id']")));
        manufacturer.selectByValue("1");

        informationTab.findElement(By.cssSelector("input[name='keywords']")).sendKeys("my");
        informationTab.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("My product");
        informationTab.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("My Product create for selenium course");
        informationTab.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("My product");
        informationTab.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("My product for");

        //
        //

        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        WebElement pricesTab = driver.findElement(By.cssSelector("div#tab-prices"));
        pricesTab.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("1");
        Select purchasePrice = new Select(pricesTab.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        purchasePrice.selectByValue("USD");
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys( "10");
        driver.findElement(By.cssSelector("button[name='save']")).click();

        driver.findElements(By.xpath("//table[@class='dataTable']//td//a[text()='" + productName + "']"));

    }

    @Test
    public void newUserRegistration() {
        driver.get("http://localhost/litecart/en/");

        driver.findElement(By.cssSelector("form[name=login_form] a")).click();
        WebElement registrationForm = driver.findElement(By.cssSelector("form[name=customer_form]"));

        registrationForm.findElement(By.cssSelector("input[name='tax_id']")).sendKeys("111-11-1111");
        registrationForm.findElement(By.cssSelector("input[name='company']")).sendKeys("Job");
        registrationForm.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Ivan");
        registrationForm.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Petrov");
        registrationForm.findElement(By.cssSelector("input[name='address1']")).sendKeys("1 Beverly Hills");
        registrationForm.findElement(By.cssSelector("input[name='address2']")).sendKeys("2 Beverly Hills");
        registrationForm.findElement(By.cssSelector("input[name='postcode']")).sendKeys("69055");
        registrationForm.findElement(By.cssSelector("input[name='city']")).sendKeys("New York");

        WebElement countrySelect = registrationForm.findElement(By.cssSelector("span.select2-selection__arrow"));
        new Actions(driver).moveToElement(countrySelect).click().sendKeys("United States" + Keys.ENTER).perform();
        Select zoneSelect = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        zoneSelect.selectByVisibleText("Arizona");

        String email = RandomString.make(4).toLowerCase();

        registrationForm.findElement(By.cssSelector("input[name='email']")).sendKeys(email + "@gmail.com");
        registrationForm.findElement(By.cssSelector("input[name='phone']")).sendKeys("+18003211455");
        registrationForm.findElement(By.cssSelector("input[name='password']")).sendKeys("qwerty123");
        registrationForm.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys("qwerty123");
        registrationForm.findElement(By.cssSelector("button[name='create_account']")).click();

        driver.findElement(By.cssSelector("div#box-account li:last-child a")).click();

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(email + "@gmail.com");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("qwerty123");
        driver.findElement(By.cssSelector("button[name=login]")).click();

        driver.findElement(By.cssSelector("div#box-account li:last-child a")).click();
    }
}
