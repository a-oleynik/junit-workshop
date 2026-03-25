package com.oleynik.qa.workshop.junit.suite.extension;

import com.oleynik.qa.workshop.junit.extensions.SuiteLikeLifecycleExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SuiteLikeLifecycleExtension.class)
public class SuiteExtensionFirstTest {
    @Test
    void first() {
        System.out.println("extension case one");
    }
}
