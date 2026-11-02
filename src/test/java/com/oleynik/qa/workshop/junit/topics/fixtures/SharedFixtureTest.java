package com.oleynik.qa.workshop.junit.topics.fixtures;

import com.oleynik.qa.workshop.junit.topics.model.ShoppingCart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Shared Fixture: both tests reuse the same immutable price catalog.
 * Each test also gets a Fresh Fixture: its own mutable shopping cart.
 * Lifecycle methods prepare these fixtures; the methods themselves are not fixtures.
 */
class SharedFixtureTest {
    private static Map<String, Integer> productPrices;
    private ShoppingCart cart;

    @BeforeAll
    static void load_shared_catalog() {
        // Shared reference data, created once for this class and never changed by tests.
        productPrices = Map.of("book", 200, "pen", 50);
    }

    @BeforeEach
    void create_empty_cart() {
        // Implicit Setup gives each test a fresh cart, while the catalog is reused.
        cart = new ShoppingCart();
    }

    @Test
    void should_calculate_total_for_two_items() {
        assertEquals(0, cart.getTotal());

        cart.addItem(productPrices.get("book"), 2);

        assertEquals(400, cart.getTotal());
    }

    @Test
    void should_calculate_total_for_different_items() {
        assertEquals(0, cart.getTotal());

        cart.addItem(productPrices.get("book"), 2);
        cart.addItem(productPrices.get("pen"), 1);

        assertEquals(450, cart.getTotal());
    }
}
