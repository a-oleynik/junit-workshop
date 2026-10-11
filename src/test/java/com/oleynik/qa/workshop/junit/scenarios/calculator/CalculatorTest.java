package com.oleynik.qa.workshop.junit.scenarios.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void set_up() {
        calculator = new Calculator();
    }

    @Test
    public void should_add_two_positive_numbers() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    public void should_add_zero() {
        assertEquals(5, calculator.add(0, 5));
    }

    @Test
    public void should_throw_exception_when_dividing_by_zero() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0)
        );
    }
}
