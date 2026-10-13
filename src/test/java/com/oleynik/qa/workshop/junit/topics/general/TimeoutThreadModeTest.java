package com.oleynik.qa.workshop.junit.topics.general;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Timeout.ThreadMode.SAME_THREAD;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

class TimeoutThreadModeTest {
    private final ThreadLocal<String> context = new ThreadLocal<>();
    private Thread fixtureThread;

    @BeforeEach
    void set_up() {
        fixtureThread = Thread.currentThread();
        context.set("workshop");
    }

    @Test
    @Timeout(value = 2, threadMode = SAME_THREAD)
    void same_thread_preserves_thread_local_context() {
        assertThat(Thread.currentThread()).isSameAs(fixtureThread);
        assertThat(context.get()).isEqualTo("workshop");
    }

    @Test
    @Timeout(value = 2, threadMode = SEPARATE_THREAD)
    void separate_thread_does_not_inherit_thread_local_context() {
        try {
            assertThat(Thread.currentThread()).isNotSameAs(fixtureThread);
            assertThat(context.get()).isNull();
        } finally {
            // Remove the entry created by get() in this separate thread.
            context.remove();
        }
    }

    @AfterEach
    void tear_down() {
        try {
            // Cleanup runs on the fixture thread in both timeout modes.
            assertThat(Thread.currentThread()).isSameAs(fixtureThread);
            assertThat(context.get()).isEqualTo("workshop");
        } finally {
            context.remove();
        }
    }
}
