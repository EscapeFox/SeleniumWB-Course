package ru.stqa.training.selenium.cucumber;

import io.cucumber.java8.En;
import ru.stqa.training.selenium.CucumberTestBase;

public class WorkWithCartSteps extends CucumberTestBase implements En {

    public WorkWithCartSteps() {
        When("we add {string} items to the cart and remove all items", (String count) -> {
            int countOfProduct = Integer.parseInt(count);
            for(int i = 0; i < countOfProduct; i++){
                app.chooseProduct();
                app.addToCart();
            }
            app.removeAllProductFromCart();
        });
    }
}
