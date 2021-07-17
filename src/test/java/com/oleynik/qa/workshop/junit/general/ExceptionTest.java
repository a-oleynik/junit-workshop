package com.oleynik.qa.workshop.junit.general;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class ExceptionTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void expected_exception_test(){
        new ArrayList<String>().get(0);
    }

    // New preferred way of exception assertion in JUnit 4.13 and JUnit 5
    @Test()
    public void preferred_exception_test() {
        IndexOutOfBoundsException thrown = assertThrows(
                IndexOutOfBoundsException.class,
                () -> new ArrayList<String>().get(0)
        );
        assertTrue(thrown.getMessage().contains("Index 0"));
        assertTrue(thrown.getMessage().equals("Index 0 out of bounds for length 0"));
        assertTrue(thrown instanceof IndexOutOfBoundsException);
    }

    // A direct way of exception assertion (worse than the previous one)
    @Test
    public void ugly_exception_test() {
        try {
            new ArrayList<String>().get(0);
        } catch (IndexOutOfBoundsException exception) {
            assertTrue(exception.getMessage().equals("Index 0 out of bounds for length 0"));
            if (!(exception instanceof IndexOutOfBoundsException)) {
                Assert.fail(exception.getMessage());
            }
        }
    }
}

