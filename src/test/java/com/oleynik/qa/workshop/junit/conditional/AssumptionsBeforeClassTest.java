package com.oleynik.qa.workshop.junit.conditional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionsBeforeClassTest {
    private static String os = System.getProperty("os.name");// Windows 10

    @BeforeAll
    public static void checkOs() {
        System.out.println("Starting AssertTest");
        assumeTrue(os.equals("Linux"),
                () -> "Detected os is not Linux");// "Windows 10"
    }

    @Test
    public void assume_that_test() {
        assumeTrue(os.equals("Windows 10"),
                () -> "Detected os is not Windows 10");
        assertTrue(os.contains("Windows"), "os was detected in wrong way");
    }

    @Test
    public void assume_true_test() {
        assumeTrue(os.equals("Windows"), () -> "Detected os is not Windows");
        assertTrue(os.contains("Windows"), "os was detected in wrong way");
    }

    @AfterAll
    public static void afterExecution() {
        System.out.println("Finishing AssumptionsTest");
    }
}
