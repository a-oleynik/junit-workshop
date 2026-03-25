package com.oleynik.qa.workshop.junit.suites.listener;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Demonstrates the third option for {@code @BeforeSuite} / {@code @AfterSuite} emulation
 * in JUnit 4: attaching a {@link SuiteRunListener} to a {@link JUnitCore} instance and
 * running test classes programmatically.
 *
 * <p>How it works:
 * <ol>
 *   <li>A {@code JUnitCore} instance is created.</li>
 *   <li>A {@link SuiteRunListener} is registered — it overrides
 *       {@code testRunStarted} and {@code testRunFinished}.</li>
 *   <li>{@code core.run(Class1, Class2, ...)} launches the classes as a single run:
 *       <ul>
 *         <li>{@code testRunStarted} fires <b>once</b> before any class starts
 *             → equivalent to {@code @BeforeSuite}.</li>
 *         <li>{@code testRunFinished} fires <b>once</b> after all classes finish
 *             → equivalent to {@code @AfterSuite}.</li>
 *       </ul>
 *   </li>
 *   <li>The {@link Result} is asserted to ensure all tests passed.</li>
 * </ol>
 *
 * <p>This class is named {@code *Test} so Surefire discovers it as a regular test.
 * The member classes ({@link ListenerFirstCase}, {@link ListenerSecondCase}) are named
 * {@code *Case} to prevent Surefire from running them a second time as standalone tests.
 *
 * <p><b>Comparison with Surefire {@code <listener>} property:</b><br>
 * The commented-out {@code <listener>} block in {@code pom.xml} can register a
 * {@code RunListener} for the <em>entire</em> Maven test run (all test classes in the project).
 * That is a global hook, not a suite-scoped one. The {@code JUnitCore} approach here is
 * scoped to exactly the classes you pass to {@code core.run()}.
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

