package com.oleynik.qa.workshop.junit.conditional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTest {
    private static final String os = System.getProperty("os.name");// Windows 10

    @BeforeEach
    public void checkOs(TestInfo testInfo) {
        String name = testInfo.getTestMethod().get().getName();
        System.out.printf("Starting AssertTest#%s", testInfo.getTestMethod().get().getName());
        assumeTrue(name.equals("assume_true_test"), () -> "The test method name doesn't equal to assume_true_test");
    }

    @Test
    public void assuming_that_test() {
        assumingThat(
                os.equals("Windows 10"),
                () -> {
                    System.out.println("Assuming that executable executed");
                });
        assertTrue(os.contains("Windows"), "os was detected in wrong way");
    }

    @Test
    public void assume_true_test() {
        assumeTrue(os.contains("Windows"), () -> "Detected os is not Windows 10");
        assertTrue(os.contains("Windows"), "os was detected in wrong way");
    }
}
