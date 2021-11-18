package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FrontTest extends TestBase {

    @Test
    public void passingThroughAdminSection(){
        //Вход в аккаунт
        driver.get(props.getProperty("baseUrl"));
        driver.findElement(By.name("username")).sendKeys(props.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.name("login")).click();
        //Создание списков элементов меню и подменю
        List<WebElement> sidebarMenuElementsList  = driver.findElements(By.cssSelector("li#app-"));
        List<WebElement> subMenuElementsList;
        //Прокликивание каждого пункта меню(внешний цикл для Основного и внутренний для Подменю)
        for (int i = 0; i < sidebarMenuElementsList.size() ; i++){
            sidebarMenuElementsList = driver.findElements(By.cssSelector("li#app-"));
            sidebarMenuElementsList.get(i).click();
            subMenuElementsList = driver.findElements(By.cssSelector("ul.docs"));
            for (int j = 0; j < subMenuElementsList.size(); j++) {
                subMenuElementsList = driver.findElements(By.cssSelector("ul.docs"));
                subMenuElementsList.get(j).click();
                assertTrue(isElementPresent(By.cssSelector("h1")));
            }
        }
    }

    @Test
    public void elementsHaveStickers(){
        driver.get("http://localhost/litecart/en/");
        List<WebElement> productElementsList = driver.findElements(By.cssSelector("li.product"));
        for(int i = 0;i < productElementsList.size() ; i++ ){
            assertEquals(productElementsList.get(i).findElements(By.cssSelector("div.sticker")).size(), 1);
        }
    }
}
