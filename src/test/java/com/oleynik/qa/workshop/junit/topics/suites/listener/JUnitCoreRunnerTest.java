package com.oleynik.qa.workshop.junit.topics.suites.listener;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Demonstrates an explicitly controlled JUnit 4 run with a {@link SuiteRunListener}.
 * One {@code core.run(...)} call executes the two selected classes and returns a combined {@link Result}.
 * The assertions below verify all four nested tests passed.
 *
 * <p>This wrapper is named {@code *Test} so build tools discover it. Its member classes are named
 * {@code *Case} to avoid also matching the workshop's default discovery patterns.
 *
 * <p>The listener observes only this {@code JUnitCore} run. A Surefire-configured listener instead
 * observes events delivered by its provider, which can include several runs per build execution.
 * Neither example is a JVM-wide resource lifecycle.
 */
public class JUnitCoreRunnerTest {

    @Test
    public void run_suite_via_junit_core_with_listener() {
        JUnitCore core = new JUnitCore();
        core.addListener(new SuiteRunListener());

        Result result = core.run(ListenerFirstCase.class, ListenerSecondCase.class);

        Assert.assertEquals("Expected 4 tests to run", 4, result.getRunCount());
        Assert.assertEquals("Expected 0 failures",     0, result.getFailureCount());
        Assert.assertEquals("Expected 0 ignored",      0, result.getIgnoreCount());
    }
}

