package com.oleynik.qa.workshop.junit.suites.beforeclass;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * JUnit 4 test suite demonstrating @BeforeClass / @AfterClass as an alternative
 * mechanism for suite-level setup and teardown.
 *
 * <p>Although JUnit 4 has no native @BeforeSuite / @AfterSuite annotations,
 * @BeforeClass and @AfterClass placed directly on a @RunWith(Suite.class) class
 * ARE executed by the Suite runner — they fire once before and after all member
 * classes, equivalent to @BeforeSuite / @AfterSuite behaviour.
 *
 * <p>When to prefer this over @ClassRule ExternalResource:
 * <ul>
 *   <li>When the setup/teardown logic is simple and self-contained.</li>
 *   <li>When you do not need the guaranteed after() of ExternalResource
 *       (which uses a try-finally internally).</li>
 * </ul>
 *
 * <p>Naming convention for suite members:
 * Classes selected by @Suite.SuiteClasses must NOT be named *Test or *Tests.
 * Using the *Case suffix prevents Surefire from discovering them as standalone
 * tests and running them a second time outside of the suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeforeClassFirstCase.class,
        BeforeClassSecondCase.class
})
public class BeforeClassSuite {

    @BeforeClass
    public static void beforeSuite() {
        System.out.println("[BeforeSuite] BeforeClassSuite setup — runs once before all suite tests");
    }

    @AfterClass
    public static void afterSuite() {
        System.out.println("[AfterSuite] BeforeClassSuite teardown — runs once after all suite tests");
    }
}

