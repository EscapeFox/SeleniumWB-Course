package ru.stqa.training.selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties props;

    @Before
    public void initializeWebDriver() throws IOException{
//        if(driver != null)
//            return;

        props = new Properties();
        props.load(TestBase.class.getResourceAsStream("/test.properties"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
//        Runtime.getRuntime().addShutdownHook(
//                new Thread(() -> { driver.quit(); driver = null; }));
    }

    @After
    public void closeWebDriverSession(){
        driver.quit();
        driver = null;
    }

    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        }
        catch(NoSuchElementException ex){
            return false;
        }
    }
}
