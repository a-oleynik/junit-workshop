package com.oleynik.qa.workshop.junit.topics.listeners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 * Registered automatically at the build-tool level via Maven Surefire {@code <listener>} property
 * and Gradle {@code useJUnit { listeners = [...] }} — no annotations or base classes are required
 * on test classes. This demonstrates the JUnit 4 build-level equivalent of global unannotated lifecycle
 * hooks, isolated within the {@code autodetect-listener-demo} Maven profile and
 * {@code autodetectListenerDemoTest} Gradle task.
 */
public class AutoDetectedRunListener extends RunListener {

    @Override
    public void testRunStarted(Description description) {
        System.out.println("Before entire run via autodetected listener");
    }

    @Override
    public void testRunFinished(Result result) {
        System.out.println("After entire run via autodetected listener");
    }
}
