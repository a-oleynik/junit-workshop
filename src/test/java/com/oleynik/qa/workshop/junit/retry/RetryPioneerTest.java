package com.oleynik.qa.workshop.junit.retry;

import org.junitpioneer.jupiter.RetryingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetryPioneerTest {

    @RetryingTest(3)
    void pioneer_retried_multiplication_test() {
        System.out.println("Multiplication test");
        assertEquals(10, 2 * 5, "Multiplication result is wrong");// 2*6
    }

    @RetryingTest(maxAttempts = 4, minSuccess = 2)
    void pioneer_retried_addition_test() {
        assertEquals(2.0, Math.addExact(1, 1), "1 + 1 should equal 2");//2.1
    }
}
