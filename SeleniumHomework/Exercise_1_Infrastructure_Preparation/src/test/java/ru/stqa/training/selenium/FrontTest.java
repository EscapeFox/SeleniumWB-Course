package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Unit test for simple App.
 */
public class FrontTest extends TestBase {

    @Test
    public void openHomePage(){
        driver.get(props.getProperty("baseUrl"));
    }
}

//+ "/" + props.getProperty("lang")