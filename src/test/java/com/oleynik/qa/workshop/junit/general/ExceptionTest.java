package com.oleynik.qa.workshop.junit.general;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTest {
    // New preferred way of exception assertion in JUnit 4.13 and JUnit 5
    @Test()
    void exception_test() {
        IndexOutOfBoundsException thrown = assertThrows(
                IndexOutOfBoundsException.class,
                () -> new ArrayList<String>().get(0)
        );
        assertTrue(thrown.getMessage().equals("Index 0 out of bounds for length 0"));
        assertTrue(thrown.getMessage().contains("Index 0"));
        assertTrue(thrown instanceof IndexOutOfBoundsException);
    }

    // A direct way of exception assertion (worse than the previous one)
    @Test
    void ugly_exception_test() {
        try {
            new ArrayList<String>().get(0);
        } catch (IndexOutOfBoundsException exception) {
            assertTrue(exception.getMessage().equals("Index 0 out of bounds for length 0"));
            if (!(exception instanceof IndexOutOfBoundsException)) {
                fail(exception.getMessage());
            }
        }
    }
}

