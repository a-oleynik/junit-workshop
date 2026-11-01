package com.oleynik.qa.workshop.junit.topics.suites.autodetect;

import org.junit.Test;

/**
 * No {@code @RunWith}, no {@code @Rule}, and no shared base class — {@code AutoDetectedRunListener} is attached
 * purely via build-tool configuration (Maven Surefire listener property / Gradle test listener options),
 * enabled for this run by the {@code autodetect-listener-demo} profile / task.
 */
public class AutoDetectedFirstTest {

    @Test
    public void first() {
        System.out.println("autodetect case one");
    }
}
