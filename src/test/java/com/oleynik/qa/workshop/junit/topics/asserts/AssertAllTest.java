package com.oleynik.qa.workshop.junit.topics.asserts;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertAllTest {

    @Test
    @Tag("regression")
    void assert_all_test() {
        System.out.println("Grouped assertions with JUnit assertAll()");
        boolean variable = true;
        assertAll(
                () -> assertEquals(10, 2 * 5, "Multiplication result is wrong"),
                () -> assertEquals(1.9, 10 / 5.0, 0.2),
                () -> assertTrue(variable),
                () -> assertFalse(!variable)
        );
    }
}
