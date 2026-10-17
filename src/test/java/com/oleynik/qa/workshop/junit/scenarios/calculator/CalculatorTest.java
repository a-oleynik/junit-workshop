package com.oleynik.qa.workshop.junit.scenarios.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @Tag("smoke")
    void shouldAddTwoPositiveNumbers() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @Tag("regression")
    void shouldAddZero() {
        assertEquals(5, calculator.add(0, 5));
    }

    @Test
    void shouldAddWhenExternalServiceIsConfigured() {
        assumeTrue(System.getenv("EXTERNAL_SERVICE_URL") != null);

        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void shouldThrowExceptionWhenDividingByZero() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0)
        );
    }
}
