package com.oleynik.qa.workshop.junit.suites;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Suite member class — named {@code *Case} intentionally.
 *
 * <p>Using the {@code *Case} suffix (not {@code *Test} / {@code *Tests}) prevents Surefire from
 * discovering this class directly during {@code mvn clean test}, so its tests run only when
 * {@link BeforeAfterSuite} is executed. This avoids double execution.
 */
public class SuiteLifecycleFirstCase {

    @BeforeAll
    static void beforeAll() {
        System.out.println("[BeforeAll] SuiteLifecycleFirstCase — class setup");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("[AfterAll] SuiteLifecycleFirstCase — class teardown");
    }

    @Test
    void first_test_in_first_case() {
        System.out.println("Running first test in SuiteLifecycleFirstCase");
    }

    @Test
    void second_test_in_first_case() {
        System.out.println("Running second test in SuiteLifecycleFirstCase");
    }
}
