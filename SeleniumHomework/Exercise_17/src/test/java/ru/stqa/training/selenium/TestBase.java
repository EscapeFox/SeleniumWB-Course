package ru.stqa.training.selenium;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static EventFiringWebDriver driver;
    public static WebDriverWait wait;
    public static Properties props;

    @Before
    public void initializeWebDriver() throws IOException{
//        if(driver != null)
//            return;

        props = new Properties();
        props.load(TestBase.class.getResourceAsStream("/test.properties"));
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
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

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition<String>(){
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0? handles.iterator().next() : null;
            }
        };
    }

    public static class MyListener extends AbstractWebDriverEventListener {
//        @Override
//        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//            System.out.println(by);
//        }
//
//        @Override
//        public void afterFindBy(By by, WebElement element, WebDriver driver) {
//            System.out.println(by +  " found");
//        }
//
//        @Override
//        public void onException(Throwable throwable, WebDriver driver) {
//            System.out.println(throwable);
//            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            try {
//                Files.copy(tempFile, new File("screen.png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
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
