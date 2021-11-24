package ru.stqa.training.selenium;

import com.google.common.base.CharMatcher;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FrontTest extends TestBase {

    @Test
    public void openCorrectPage() {
        driver.get("http://localhost/litecart/en/");

        WebElement regularPriceElementOnMainPage = driver.findElement(By.cssSelector("div#box-campaigns .regular-price"));
        WebElement campaignPriceElementOnMainPage = driver.findElement(By.cssSelector("div#box-campaigns .campaign-price"));

        String productNameOnMainPage = driver.findElement(By.cssSelector("div#box-campaigns div.name")).getAttribute("textContent");
        String regularPriceOnMainPage = regularPriceElementOnMainPage.getAttribute("textContent");
        String campaignPriceOnMainPage = campaignPriceElementOnMainPage.getAttribute("textContent");

        String[] rgbaStringValueArray;
        int[] rgbaValueArray;

        //
        //Проверки на главной странице
        //

        //Проверка стиля текста акционной и обычной цен на главной странице
        // 1.1 Проверка цвета обычной цены
        rgbaStringValueArray = regularPriceElementOnMainPage.getCssValue("color").replaceAll("[^0-9 ]", "").split(" ");
        rgbaValueArray = new int[rgbaStringValueArray.length];
        for (int i = 0; i < rgbaStringValueArray.length; i++)
            rgbaValueArray[i] = Integer.parseInt(rgbaStringValueArray[i]);
        assertEquals(rgbaValueArray[0], rgbaValueArray[1], rgbaValueArray[2]);
        // 1.2 Зачеркнутость
        //Проверял тег элемента, т.к. text-decoration: line-through привязан к нему
        assertEquals(regularPriceElementOnMainPage.getAttribute("tagName"),"S");

        // 2.1 Проверка цвета акционной цены
        rgbaStringValueArray = campaignPriceElementOnMainPage.getCssValue("color").replaceAll("[^0-9 ]", "").split(" ");
        rgbaValueArray = new int[rgbaStringValueArray.length];
        for (int i = 0; i < rgbaStringValueArray.length; i++)
            rgbaValueArray[i] = Integer.parseInt(rgbaStringValueArray[i]);

        assertEquals(rgbaValueArray[1] + rgbaValueArray[2], 0);
        // 2.2 Жирность
        assertEquals(campaignPriceElementOnMainPage.getAttribute("tagName"),"STRONG");
        
        //Проверка, что на главной странице акционная цена крупнее чем обычная
        float regularPriceFontSize = Float.parseFloat(
                regularPriceElementOnMainPage.getCssValue("font-size")
                        .replaceAll("[^0-9.]", ""));
        float campaignPriceFontSize = Float.parseFloat(
                campaignPriceElementOnMainPage.getCssValue("font-size")
                        .replaceAll("[^0-9.]", ""));
        assertTrue(regularPriceFontSize < campaignPriceFontSize);

        //
        //Проверки на странице товара
        //

        //Переход на страницу товара
        driver.findElement(By.cssSelector("div#box-campaigns a.link")).click();

        WebElement regularPriceElementOnProductPage = driver.findElement(By.cssSelector("div#box-product .regular-price"));
        WebElement campaignPriceElementOnProductPage = driver.findElement(By.cssSelector("div#box-product .campaign-price"));

        //Проверка на совпадение имен на разных страницах
        assertTrue(productNameOnMainPage.equals(driver.findElement(By.cssSelector("div#box-product h1")).getAttribute("textContent")));

        //Проверка на совпадение акционной и обычной цен
        assertTrue(regularPriceOnMainPage.equals(regularPriceElementOnProductPage.getAttribute("textContent")));
        assertTrue(campaignPriceOnMainPage.equals(campaignPriceElementOnProductPage.getAttribute("textContent")));

        //Проверка стиля текста акционной и обычной цен на странице товара
        // 1.1 Проверка цвета обычной цены
        rgbaStringValueArray = regularPriceElementOnProductPage.getCssValue("color").replaceAll("[^0-9 ]", "").split(" ");
        rgbaValueArray = new int[rgbaStringValueArray.length];
        for (int i = 0; i < rgbaStringValueArray.length; i++)
            rgbaValueArray[i] = Integer.parseInt(rgbaStringValueArray[i]);
        assertEquals(rgbaValueArray[0], rgbaValueArray[1], rgbaValueArray[2]);
        // 1.2 Зачеркнутость
        //Проверял тег элемента, т.к. text-decoration: line-through привязан к нему
        assertEquals(regularPriceElementOnProductPage.getAttribute("tagName"),"S");

        // 2.1 Проверка цвета акционной цены
        rgbaStringValueArray = campaignPriceElementOnProductPage.getCssValue("color").replaceAll("[^0-9 ]", "").split(" ");
        rgbaValueArray = new int[rgbaStringValueArray.length];
        for (int i = 0; i < rgbaStringValueArray.length; i++)
            rgbaValueArray[i] = Integer.parseInt(rgbaStringValueArray[i]);

        assertEquals(rgbaValueArray[1] + rgbaValueArray[2], 0);
        // 2.2 Жирность
        assertEquals(campaignPriceElementOnProductPage.getAttribute("tagName"),"STRONG");

        //Проверка, что на странице товара акционная цена крупнее чем обычная
        regularPriceFontSize = Float.parseFloat(
                regularPriceElementOnProductPage.getCssValue("font-size")
                .replaceAll("[^0-9].", ""));
        campaignPriceFontSize = Float.parseFloat(
                campaignPriceElementOnProductPage.getCssValue("font-size")
                        .replaceAll("[^0-9].", ""));
        assertTrue(regularPriceFontSize < campaignPriceFontSize);
    }

    //
    //Задание 9
    //

    @Test
    public void isCountrySorted(){

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys(props.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.name("login")).click();

        //
        // a) Проверка, что страны расположены в алфавитном порядке
        //

        List<WebElement> countryElementList  = driver.findElements(By.cssSelector("tr.row a:not([title])"));
        ArrayList<String> obtainedList = new ArrayList<>();
        ArrayList<String> sortedList = new ArrayList<>();
        for(WebElement webEl: countryElementList){
            obtainedList.add(webEl.getAttribute("textContent"));
        }
        for(String textContent:obtainedList){
            sortedList.add(textContent);
        }
        Collections.sort(sortedList);
        assertTrue(sortedList.equals(obtainedList));

        //
        // б) Проверка, что часовые пояса стран расположены по алфавиту
        //

        WebElement headerRow = driver.findElement(By.cssSelector("tr.header"));
        List<WebElement> headerCells = headerRow.findElements(By.cssSelector("th"));
        int cellNumber = 0;
        for(int i = 0; i < headerCells.size(); i++){
            if(headerCells.get(i).getAttribute("textContent").equals("Zones"))
                cellNumber = i;
        }

        List<WebElement> rowCells;
        List<WebElement> tableRowsList = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        for(int i = 0; i < tableRowsList.size(); i++){
            tableRowsList = driver.findElements(By.cssSelector("table.dataTable tr.row"));
            rowCells = tableRowsList.get(i).findElements(By.cssSelector("td"));
            if(Integer.parseInt(rowCells.get(cellNumber).getAttribute("textContent")) > 0) {
                tableRowsList.get(i).findElement(By.cssSelector("td a:not([title])")).click();
                List<WebElement> zonesElementList = driver.findElements(By.cssSelector("input[type = hidden][name*=name]"));

                obtainedList.clear();
                sortedList.clear();

                for(WebElement webEl: zonesElementList){
                    obtainedList.add(webEl.getAttribute("value"));
                }
                for(String textContent:obtainedList){
                    sortedList.add(textContent);
                }
                Collections.sort(sortedList);
                assertTrue(sortedList.equals(obtainedList));
                driver.navigate().back();
            }
        }
    }

    @Test
    public void isGeofenceSorted(){
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys(props.getProperty("login"));
        driver.findElement(By.name("password")).sendKeys(props.getProperty("password"));
        driver.findElement(By.name("login")).click();

        List<WebElement> countryGeoElementList  = driver.findElements(By.cssSelector("tr.row a:not([title])"));
        ArrayList<String> obtainedList = new ArrayList<>();
        ArrayList<String> sortedList = new ArrayList<>();

        for(int i = 0; i < countryGeoElementList.size(); i++){
            countryGeoElementList = driver.findElements(By.cssSelector("tr.row a:not([title])"));
            countryGeoElementList.get(i).click();

            List<WebElement> zonesElementList = driver.findElements(By.cssSelector("select[name*=zone_code] option[selected]"));

            obtainedList.clear();
            sortedList.clear();

            for(WebElement webEl: zonesElementList){
                obtainedList.add(webEl.getAttribute("textContent"));
            }
            for(String textContent:obtainedList){
                sortedList.add(textContent);
            }
            Collections.sort(sortedList);
            assertTrue(sortedList.equals(obtainedList));

            driver.navigate().back();
        }
    }

}
