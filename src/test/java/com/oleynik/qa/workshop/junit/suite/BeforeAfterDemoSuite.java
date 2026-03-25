package com.oleynik.qa.workshop.junit.suite;

import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        SuiteLifecycleFirstCase.class,
        SuiteLifecycleSecondCase.class
})
public class BeforeAfterDemoSuite {
    @BeforeSuite
    static void beforeSuite() {
        System.out.println("Before entire suite");
    }

    @AfterSuite
    static void afterSuite() {
        System.out.println("After entire suite");
    }
}
