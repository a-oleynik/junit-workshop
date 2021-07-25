package com.oleynik.qa.workshop.junit.conditional;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class AssumeBeforeClassTest {
    private static final String OS = System.getProperty("os.name");// Windows 10

    @BeforeClass
    public static void checkOs() {
        System.out.println("Starting AssertTest");
        Assume.assumeThat("Detected os is not Linux", OS, equalTo("Linux"));
    }

    @Test
    public void assume_that_test() {
        Assume.assumeThat("Detected os is not Windows 10", OS, equalTo("Windows 10"));
        assertTrue("os was detected in wrong way", OS.contains("Windows"));
    }

    @Test
    public void assume_true_test() {
        Assume.assumeTrue("Detected os is not Windows 10", OS.contains("Windows"));
        assertTrue("os was detected in wrong way", OS.contains("Windows"));
    }

    @AfterClass
    public static void afterExecution() {
        System.out.println("Finishing AssertTest");
    }
}
