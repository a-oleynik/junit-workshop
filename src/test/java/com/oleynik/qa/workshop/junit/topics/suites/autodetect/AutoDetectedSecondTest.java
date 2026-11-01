package com.oleynik.qa.workshop.junit.topics.suites.autodetect;

import org.junit.Test;

/**
 * Plain JUnit 4 tests with no listener registration in the test class.
 * The Maven demo profile registers a JUnit {@code RunListener}; the Gradle demo task
 * observes execution with a Gradle {@code TestListener}. Normal test runs enable neither.
 */
public class AutoDetectedSecondTest {

    @Test
    public void second() {
        System.out.println("autodetect case two");
    }
}
