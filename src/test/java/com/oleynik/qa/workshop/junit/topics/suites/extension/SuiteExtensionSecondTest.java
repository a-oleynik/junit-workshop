package com.oleynik.qa.workshop.junit.topics.suites.extension;

import com.oleynik.qa.workshop.junit.topics.extensions.SuiteLikeLifecycleExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SuiteLikeLifecycleExtension.class)
public class SuiteExtensionSecondTest {
    @Test
    void second() {
        System.out.println("extension case two");
    }
}
