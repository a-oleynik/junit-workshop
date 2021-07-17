package com.oleynik.qa.workshop.junit.rules;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class MonitorRule extends TestWatcher {

    @Override
    protected void succeeded(Description description) {
        System.out.printf("%s succeeded%n", description.getMethodName());
    }

    @Override
    protected void failed(Throwable e, Description description) {
        System.out.printf("%s failed with %s%n", description.getMethodName(), e);
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        System.out.printf("%s skipped%n", description.getMethodName());
    }

    @Override
    protected void starting(Description description) {
        System.out.printf("%s is starting%n", description.getMethodName());
    }

    @Override
    protected void finished(Description description) {
        System.out.printf("%s finished%n", description.getMethodName());
    }
}
