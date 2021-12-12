package ru.stqa.training.selenium.tests;

import org.junit.Test;
import ru.stqa.training.selenium.CucumberTestBase;

public class WorkWithCartTests extends CucumberTestBase {

    @Test
    public void workWithTheCart(){
        int countOfProduct = 3;

        for(int i = 0; i < countOfProduct; i++){
            app.chooseProduct();
            app.addToCart();
        }
        app.removeAllProductFromCart();
    }
}
