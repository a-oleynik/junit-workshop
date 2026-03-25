package com.oleynik.qa.workshop.junit.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Suite member class — named *Case intentionally.
 *
 * Using the *Case suffix (not *Test / *Tests) prevents Surefire from discovering
 * this class directly during "mvn clean test", so its tests run only when
 * MySuite is executed. This avoids double execution.
 */
public class SuiteLifecycleFirstCase {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("[BeforeClass] SuiteLifecycleFirstCase — class setup");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("[AfterClass] SuiteLifecycleFirstCase — class teardown");
    }

    @Test
    public void first_test_in_first_case() {
        System.out.println("Running first test in SuiteLifecycleFirstCase");
    }

    @Test
    public void second_test_in_first_case() {
        System.out.println("Running second test in SuiteLifecycleFirstCase");
    }
}

