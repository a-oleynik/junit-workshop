package com.oleynik.qa.workshop.junit.scenarios.calculator;

import com.oleynik.qa.workshop.junit.topics.grouping.categories.RegressionTests;
import com.oleynik.qa.workshop.junit.topics.grouping.categories.SmokeTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assume.assumeTrue;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void set_up() {
        calculator = new Calculator();
    }

    @Test
    @Category(SmokeTests.class)
    public void should_add_two_positive_numbers() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @Category(RegressionTests.class)
    public void should_add_zero() {
        assertEquals(5, calculator.add(0, 5));
    }

    @Test
    public void should_add_when_external_service_is_configured() {
        assumeTrue(System.getenv("EXTERNAL_SERVICE_URL") != null);

        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    public void should_throw_exception_when_dividing_by_zero() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0)
        );
    }
}
