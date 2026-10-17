package com.oleynik.qa.workshop.junit.topics.general;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Demonstrates lifecycle method calls. */
@Execution(ExecutionMode.SAME_THREAD)
public class LifecycleTest {
    private static final String ACTUAL = "Actual string";
    private static final String EXPECTED = "Actual string";// "Expected string"

    @BeforeAll
    public static void globalSetUp() {
        System.out.println("Set up class");
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("tear down class");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Set up method");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Tear down method");
    }

    @Test
    void lifecycle_the_first_test() {
        System.out.println("The first test");
        assertEquals(EXPECTED, ACTUAL, "Wrong string found");
    }

    @Test
    void lifecycle_the_second_test() {
        System.out.println("The second test");
        assertEquals(ACTUAL, ACTUAL, "Wrong string found");
    }
}
