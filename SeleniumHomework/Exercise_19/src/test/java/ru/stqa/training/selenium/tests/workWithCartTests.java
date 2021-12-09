package ru.stqa.training.selenium.tests;

import org.junit.Test;

public class workWithCartTests extends TestBase {

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
