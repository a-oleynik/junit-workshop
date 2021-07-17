package com.oleynik.qa.workshop.junit.listeners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class ExampleListener extends RunListener{
    public void testRunStarted(Description description) {
        System.out.println("Number of tests to execute: " + description.testCount());
    }

    public void testRunFinished(Result result) {
        System.out.println("Number of tests executed: " + result.getRunCount());
    }

    public void testStarted(Description description) {
        System.out.println("Starting: " + description.getMethodName());
    }

    public void testFinished(Description description) {
        System.out.println("Finished: " + description.getMethodName());
    }

    public void testFailure(Failure failure) {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
        //Screenshot!
    }

    public void testAssumptionFailure(Failure failure) {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
    }

    public void testIgnored(Description description) {
        System.out.println("Ignored: " + description.getMethodName());
    }
}
