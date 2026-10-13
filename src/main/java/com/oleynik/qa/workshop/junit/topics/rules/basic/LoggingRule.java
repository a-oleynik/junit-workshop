package com.oleynik.qa.workshop.junit.topics.rules.basic;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/** First rule example: log the test name before each test. */
public class LoggingRule extends TestWatcher {
    @Override
    protected void starting(Description description) {
        System.out.println("Starting test: " + description.getMethodName());
    }
}
