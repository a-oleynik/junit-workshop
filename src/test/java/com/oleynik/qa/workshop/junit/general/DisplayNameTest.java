package com.oleynik.qa.workshop.junit.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class DisplayNameTest {

    @Test
    @DisplayName("Simple multiplication test")
    public void assert_equals_multiplication_test() {
        System.out.println("Multiplication test");
        Assertions.assertEquals(10, 2 * 5, "Multiplication result is wrong");
    }

    @Test
    @DisplayName("Simple division test")
    public void assert_equals_division_test() {
        System.out.println("Division test");
        Assertions.assertEquals(1.9, 10 / 5.0, 0.2);
    }

    @Test
    @DisplayName("Boolean test")
    public void assert_boolean_test() {
        boolean variable = true;
        Assertions.assertTrue(variable, "variable is not true");
        Assertions.assertFalse(!variable, "!variable is not false");
    }

    @Test
    @DisplayName("Fail test")
    public void fail_test() {
        boolean isFailed = false;
        if (isFailed) {
            Assertions.fail(format("The test is failed because isFailed equals to %s", isFailed));
        }
    }
}
