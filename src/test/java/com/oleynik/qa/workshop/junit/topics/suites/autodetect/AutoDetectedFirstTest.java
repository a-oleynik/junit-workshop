package com.oleynik.qa.workshop.junit.topics.suites.autodetect;

import org.junit.jupiter.api.Test;

/**
 * No {@code @ExtendWith} and no shared base class — {@code AutoDetectedRootResourceExtension} is picked up purely
 * via ServiceLoader autodetection, enabled for this run by the {@code autodetect-extension-demo} profile / task.
 */
public class AutoDetectedFirstTest {

    @Test
    void first() {
        System.out.println("autodetect case one");
    }
}
