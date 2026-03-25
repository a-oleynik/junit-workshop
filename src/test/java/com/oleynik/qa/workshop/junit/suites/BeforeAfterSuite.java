package com.oleynik.qa.workshop.junit.suites;

import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * JUnit 6 test suite demonstrating native {@code @BeforeSuite} / {@code @AfterSuite} support.
 *
 * <p>Unlike JUnit 4, JUnit 6 provides first-class {@code @BeforeSuite} / {@code @AfterSuite}
 * annotations via the {@code junit-platform-suite} dependency — no {@code @ClassRule} workaround
 * needed.
 *
 * <p>Naming convention for suite members:
 * <ul>
 *   <li>Classes selected by {@code @SelectClasses} must <b>NOT</b> be named {@code *Test} or
 *       {@code *Tests}.</li>
 *   <li>Using the {@code *Case} suffix prevents Surefire from discovering them as standalone
 *       tests and running them a second time outside of the suite.</li>
 * </ul>
 *
 * <p>Maven config: {@code pom.xml} requires two things for the suite feature to work:
 * <ol>
 *   <li>The {@code junit-platform-suite} dependency (enables {@code @Suite}, {@code @BeforeSuite},
 *       {@code @AfterSuite}).</li>
 *   <li>{@code **&#47;*Suite.java} added to Surefire {@code <includes>} so this class is
 *       automatically discovered by {@code mvn clean test}.</li>
 * </ol>
 */
@Suite
@SelectClasses({
        SuiteLifecycleFirstCase.class,
        SuiteLifecycleSecondCase.class
})
public class BeforeAfterSuite {

    @BeforeSuite
    static void beforeSuite() {
        System.out.println("[BeforeSuite] Suite setup — runs once before all suite tests");
    }

    @AfterSuite
    static void afterSuite() {
        System.out.println("[AfterSuite] Suite teardown — runs once after all suite tests");
    }
}
