package com.oleynik.qa.workshop.junit.topics.listeners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 * Registered by the Surefire listener property in the {@code autodetect-listener-demo} Maven profile.
 * The demo tests need no listener registration annotations or shared base class.
 *
 * <p>Reports JUnit run events delivered by the configured provider. There may be several
 * start/finish pairs in one Maven execution, especially when tests use separate forks.
 * These callbacks do not define a single shared-resource lifecycle for the whole build.
 *
 * <p>The Gradle demo uses a Gradle {@code TestListener} configured in {@code build.gradle};
 * it does not register this JUnit {@code RunListener}.
 *
 * @see <a href="https://junit.org/junit4/javadoc/4.13.2/org/junit/runner/notification/RunListener.html">JUnit 4 RunListener</a>
 */
public class AutoDetectedRunListener extends RunListener {

    @Override
    public void testRunStarted(Description description) {
        // Surefire's JUnit 4 adapter can deliver a run event without a Description.
        String runName = description == null ? "<no description from provider>" : description.getDisplayName();
        System.out.println("RunListener.testRunStarted: " + runName);
    }

    @Override
    public void testRunFinished(Result result) {
        System.out.println("RunListener.testRunFinished: tests=" + result.getRunCount()
                + ", failures=" + result.getFailureCount());
    }
}
