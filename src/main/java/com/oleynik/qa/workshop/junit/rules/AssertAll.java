package com.oleynik.qa.workshop.junit.rules;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import java.util.Arrays;

public class AssertAll {
    @Rule
    private ErrorCollector collector = new ErrorCollector();

    public void assertAll(Runnable... assertions) {
        Arrays.stream(assertions).forEach(this::runAndCollectThrowable);
    }

    public ErrorCollector getCollector() {
        return collector;
    }

    private void runAndCollectThrowable(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            collector.addError(throwable);
        }
    }
}
