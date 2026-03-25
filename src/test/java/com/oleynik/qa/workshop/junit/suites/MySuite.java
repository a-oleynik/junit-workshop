package com.oleynik.qa.workshop.junit.suites;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * JUnit 4 test suite demonstrating @BeforeSuite / @AfterSuite emulation.
 * <p>
 * JUnit 4 has no native @BeforeSuite / @AfterSuite annotations.
 * The standard workaround is a @ClassRule backed by ExternalResource:
 * - before()  → runs once before the first class in the suite starts
 * - after()   → runs once after the last class in the suite finishes
 * <p>
 * Naming convention for suite members:
 * Classes selected by @Suite.SuiteClasses must NOT be named *Test or *Tests.
 * Using the *Case suffix prevents Surefire from discovering them as standalone
 * tests and running them a second time outside of the suite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SuiteLifecycleFirstCase.class,
        SuiteLifecycleSecondCase.class
})
public class MySuite {

    @ClassRule
    public static ExternalResource suiteSetup = new ExternalResource() {
        @Override
        protected void before() {
            System.out.println("[BeforeSuite] Suite setup — runs once before all suite tests");
        }

        @Override
        protected void after() {
            System.out.println("[AfterSuite] Suite teardown — runs once after all suite tests");
        }
    };
}
