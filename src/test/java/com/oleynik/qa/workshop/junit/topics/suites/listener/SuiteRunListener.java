package com.oleynik.qa.workshop.junit.topics.suites.listener;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 * Observes the classes passed to one {@code JUnitCore.run(...)} call in {@link JUnitCoreRunnerTest}.
 * The start callback precedes those classes and the finish callback receives their combined result.
 * Running other tests outside that call does not notify this listener.
 *
 * <p>Registering a listener through Surefire is a separate example: the provider may report
 * several JUnit runs in one Maven execution. It does not guarantee one setup/teardown pair
 * for the whole build. Use a suite-scoped {@code ClassRule} when demonstrating a shared resource.
 *
 * @see <a href="https://junit.org/junit4/javadoc/4.13.2/org/junit/runner/JUnitCore.html">JUnitCore</a>
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

