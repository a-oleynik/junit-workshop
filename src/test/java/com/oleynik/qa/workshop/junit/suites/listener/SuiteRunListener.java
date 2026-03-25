package com.oleynik.qa.workshop.junit.suites.listener;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 * Custom {@link RunListener} used to emulate {@code @BeforeSuite} / {@code @AfterSuite}
 * behaviour in JUnit 4 via a programmatic {@code JUnitCore} launch.
 *
 * <p>How it works:
 * <ul>
 *   <li>{@link #testRunStarted(Description)} — fires <b>once</b> before the first test class
 *       in the run starts. Equivalent to {@code @BeforeSuite}.</li>
 *   <li>{@link #testRunFinished(Result)} — fires <b>once</b> after the last test class in the
 *       run finishes. Equivalent to {@code @AfterSuite}.</li>
 * </ul>
 *
 * <p>This listener is attached to a {@code JUnitCore} instance inside
 * {@link JUnitCoreRunnerTest}, which passes the exact set of test classes it should wrap.
 * No suite class or {@code @ClassRule} is required.
 *
 * <p>Alternative (global scope): the Surefire {@code <listener>} property can register a
 * {@code RunListener} for the <em>entire Maven test run</em> (all test classes in the project),
 * not just a specific group. See the commented-out block in {@code pom.xml}.
 */
public class SuiteRunListener extends RunListener {

    @Override
    public void testRunStarted(Description description) {
        System.out.println("[BeforeSuite] SuiteRunListener.testRunStarted — runs once before all suite tests");
    }

    @Override
    public void testRunFinished(Result result) {
        System.out.println("[AfterSuite]  SuiteRunListener.testRunFinished — runs once after all suite tests"
                + " | total: " + result.getRunCount()
                + ", failed: " + result.getFailureCount());
    }
}

