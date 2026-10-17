package com.oleynik.qa.workshop.junit.topics.general;

import com.oleynik.qa.workshop.junit.scenarios.calculator.Calculator;
import org.junit.*;

import static java.lang.String.format;

// @Ignore("BUG-124: shared test fixture initialization fails")
public class IgnoringTest {

    @Ignore("BUG-123: integer overflow is not handled yet")
    @Test
    public void should_detect_integer_overflow() {
        Calculator calculator = new Calculator();

        // Intended behavior: reject overflow instead of silently wrapping to Integer.MIN_VALUE.
        Assert.assertThrows(ArithmeticException.class, () -> calculator.add(Integer.MAX_VALUE, 1));
    }

    @Test
    public void ignoring_division_test() {
        System.out.println("Division test.");
        Assert.assertEquals(2.0, 10 / 5.0, 0);
    }

    @Test
    public void ignoring_boolean_test() {
        System.out.println("Boolean test.");
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }

    @Test
    public void ignoring_fail_test() {
        System.out.println("Fail test.");
        boolean isFailed = false;
        if (isFailed) {
            Assert.fail(format("The test is because isFailed is %s", isFailed));
        }
    }
}
