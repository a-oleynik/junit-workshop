package com.oleynik.qa.workshop.junit.topics.general;

import com.oleynik.qa.workshop.junit.scenarios.calculator.Calculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

// @Disabled("BUG-124: shared test fixture initialization fails")
public class DisabledTest {

    @Disabled("BUG-123: integer overflow is not handled yet")
    @Test
    void should_detect_integer_overflow() {
        Calculator calculator = new Calculator();

        // Intended behavior: reject overflow instead of silently wrapping to Integer.MIN_VALUE.
        assertThrows(ArithmeticException.class, () -> calculator.add(Integer.MAX_VALUE, 1));
    }

    @Test
    public void disabled_division_test() {
        System.out.println("Division test");
        assertEquals(2.0, 10 / 5.0, 0);
    }

    @Test
    public void disabled_boolean_test() {
        System.out.println("Boolean test");
        boolean variable = true;
        assertTrue(variable, "variable is not true");
        assertFalse(!variable, "!variable is not false");
    }

    @Test
    public void disabled_fail_test() {
        System.out.println("Fail test");
        boolean isFailed = false;
        if (isFailed) {
            fail(format("The test is because isFailed is %s", isFailed));
        }
    }
}
