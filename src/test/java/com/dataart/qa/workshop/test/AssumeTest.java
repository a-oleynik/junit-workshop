package com.dataart.qa.workshop.test;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static java.lang.String.format;

public class AssumeTest {
    private static final String os = System.getProperty("os.name");// Windows 10
    public String testDescription;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            testDescription = description.getMethodName();
        }
    };

    @Before
    public void checkOs() {
        System.out.print(format("Starting AssertTest#%s", testDescription));
        Assume.assumeThat("The test method name doesn't equal to assume_true_test", testDescription, equalTo("assume_true_test"));
    }

    @Test
    public void assume_that_test() {
        Assume.assumeThat("Detected os is not Windows 10", os, equalTo("Windows 10"));
        assertTrue("os was detected in wrong way", os.contains("Windows"));
    }

    @Test
    public void assume_true_test() {
        Assume.assumeTrue("Detected os is not Windows 10", os.contains("Windows"));
        assertTrue("os was detected in wrong way", os.contains("Windows"));
    }
}
