package com.oleynik.qa.workshop.junit.topics.extensions.basic;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/** First extension example: log the test name before each test. */
public class LoggingExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        System.out.println("Starting test: " + context.getDisplayName());
    }
}
