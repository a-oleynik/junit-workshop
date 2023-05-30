package com.oleynik.qa.workshop.junit.retry;

import io.github.artsok.RepeatedIfExceptionsTest;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RetryRerunnerTest {

    //@Test
    @RepeatedIfExceptionsTest(repeats = 2)
    void retried_multiplication_test() {
        System.out.println("Multiplication test");
        assertEquals(10, 2 * 5, "Multiplication result is wrong");// 2*6
    }

    @RepeatedIfExceptionsTest(repeats = 3, exceptions = IndexOutOfBoundsException.class,
            name = "Retry failed test. Attempt {currentRepetition} of {totalRepetitions}")
    void retried_with_exception_test() {
        //throw new NullPointerException("My NullPointerException");
        try {
            new ArrayList<String>().get(0);
        } catch (IndexOutOfBoundsException exception) {
            assertTrue(exception.getMessage().equals("Index 0 out of bounds for length 0"));
            if (!(exception instanceof IndexOutOfBoundsException)) {
                fail(exception.getMessage());
            }
        }
        //new ArrayList<String>().get(0);
    }

    @DisplayName("Test Case Name")
    @RepeatedIfExceptionsTest(repeats = 6, minSuccess = 3)
    void retried_addition_test_with_display_name(TestInfo testInfo) {
        assertEquals(2, Math.addExact(1, 1), "1 + 1 should equal 2");//3
    }
}
