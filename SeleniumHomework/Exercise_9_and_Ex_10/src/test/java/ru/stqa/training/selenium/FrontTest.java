package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FrontTest extends TestBase {

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
