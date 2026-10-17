package com.oleynik.qa.workshop.junit.topics.fixtures;

import com.oleynik.qa.workshop.junit.topics.model.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In-line Setup: each test prepares its own fresh fixture inside the test method.
 * Compare with ImplicitFixtureTest: the scenarios and assertions are the same.
 */
class InlineFixtureTest {

    @Test
    void should_calculate_total_for_two_items() {
        // The empty cart is the fixture; constructing it is fixture setup.
        ShoppingCart cart = new ShoppingCart();
        assertEquals(0, cart.getTotal());

        cart.addItem(200, 2);

        assertEquals(400, cart.getTotal());
    }

    @Test
    void should_calculate_total_for_different_items() {
        ShoppingCart cart = new ShoppingCart();
        assertEquals(0, cart.getTotal());

        cart.addItem(200, 2);
        cart.addItem(50, 1);

        assertEquals(450, cart.getTotal());
    }
}
