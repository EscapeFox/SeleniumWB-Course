Feature: Work with the cart

    Scenario: Add 3 items to the cart and remove all items
        When we add '3' items to the cart
        And remove all items
        Then the cart is empty






