package com.oleynik.qa.workshop.junit.topics.fixtures;

import com.oleynik.qa.workshop.junit.topics.model.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Implicit Setup: a lifecycle method prepares a fresh empty cart before each test.
 * The cart and its state are the fixture; the lifecycle method is not the fixture.
 */
public class ImplicitFixtureTest {
    private ShoppingCart cart;

    @Before
    public void create_empty_cart() {
        cart = new ShoppingCart();
    }

    @Test
    public void should_calculate_total_for_two_items() {
        assertEquals(0, cart.getTotal());

        // Scenario-specific prices and quantities stay visible in the test.
        cart.addItem(200, 2);

        assertEquals(400, cart.getTotal());
    }

    @Test
    public void should_calculate_total_for_different_items() {
        assertEquals(0, cart.getTotal());

        cart.addItem(200, 2);
        cart.addItem(50, 1);

        assertEquals(450, cart.getTotal());
    }
}
