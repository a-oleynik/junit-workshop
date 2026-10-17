package com.oleynik.qa.workshop.junit.topics.asserts;

import com.oleynik.qa.workshop.junit.topics.rules.AssertAll;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SoftAssertErrorCollector2Test {
    public final AssertAll assertAll = new AssertAll();

    @Rule
    public ErrorCollector collector = assertAll.getCollector();

    @Test
    public void assert_all_test() {
        System.out.println("Grouped assertions with a custom AssertAll helper and JUnit 4 ErrorCollector");
        boolean variable = true;
        assertAll.assertAll(
                () -> assertEquals("Multiplication result is wrong", 10, 2 * 5),
                () -> assertEquals(1.9, 10 / 5.0, 0.2),
                () -> assertTrue(variable),
                () -> assertFalse(!variable)
        );
    }
}
