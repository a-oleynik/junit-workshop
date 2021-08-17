package com.oleynik.qa.workshop.junit.general;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixturesTest {
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
        System.out.println("Test down method");
    }

    @Test
    public void fixtures_the_first_test() {
        System.out.println("The first test");
        assertEquals(EXPECTED, ACTUAL, "Wrong string found");
    }

    @Test
    public void fixtures_the_second_test() {
        System.out.println("The second test");
        assertEquals(ACTUAL, ACTUAL, "Wrong string found");
    }
}
