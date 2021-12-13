package ru.stqa.training.selenium.cucumber;

import io.cucumber.java8.En;
import org.junit.Assert;
import ru.stqa.training.selenium.CucumberTestBase;

public class WorkWithCartSteps extends CucumberTestBase implements En {

    public WorkWithCartSteps() {
        When("we add {string} items to the cart", (String count) -> {
            int countOfProduct = Integer.parseInt(count);
            for(int i = 0; i < countOfProduct; i++){
                app.chooseProduct();
                app.addToCart();
            }
        });
        And("remove all items", () -> {
            app.removeAllProductFromCart();
        });
        Then("the cart is empty", () -> {
            Assert.assertTrue(app.cartIsEmpty());
        });
    }
}
