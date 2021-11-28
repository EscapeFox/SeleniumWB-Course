package ru.stqa.training.selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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
        driver = new FirefoxDriver();
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

    //Проверка наличия(без ожидания)
//    public boolean isElementPresent(By locator){
//        try {
//            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);;
//            return driver.findElements(locator).size() > 0;
//        }
//        catch(NoSuchElementException ex){
//            return false;
//        }
//        finally{
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        }
//    }

    // Проверка наличия(с неявными ожданиями)
    public boolean isElementPresent(By locator){
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }

    //Проверка отсутствия(без неявных ожиданий)
    public boolean isElementNotPresent(By locator){
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);;
            return driver.findElements(locator).size() == 0;
        }
        finally{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
}
