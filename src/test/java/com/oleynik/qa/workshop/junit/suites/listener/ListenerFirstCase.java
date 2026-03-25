package com.oleynik.qa.workshop.junit.suites.listener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Suite member class — named {@code *Case} intentionally.
 *
 * <p>Using the {@code *Case} suffix (not {@code *Test} / {@code *Tests}) prevents Surefire
 * from discovering this class directly during {@code mvn clean test}, so its tests run only
 * when launched via {@link JUnitCoreRunnerTest}. This avoids double execution.
 */
public class ListenerFirstCase {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("[BeforeClass] ListenerFirstCase — class setup");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("[AfterClass]  ListenerFirstCase — class teardown");
    }

    @Test
    public void first_test_in_first_case() {
        System.out.println("Running first test in ListenerFirstCase");
    }

    @Test
    public void second_test_in_first_case() {
        System.out.println("Running second test in ListenerFirstCase");
    }
}

