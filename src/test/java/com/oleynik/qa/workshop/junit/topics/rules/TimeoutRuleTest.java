package com.oleynik.qa.workshop.junit.topics.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeoutRuleTest {
    // Each test gets its own two-second limit, including @Before and @After.
    @Rule
    public Timeout timeout = Timeout.seconds(2);

    private final ThreadLocal<String> context = new ThreadLocal<>();
    private Thread fixtureThread;

    @Before
    public void set_up() {
        fixtureThread = Thread.currentThread();
        context.set("workshop");
    }

    @Test
    public void thread_local_context_is_available_in_test() {
        assertThat(Thread.currentThread()).isSameAs(fixtureThread);
        assertThat(context.get()).isEqualTo("workshop");
    }

    @Test
    public void operation_completes_within_timeout() throws InterruptedException {
        Thread.sleep(100); // Simulate an operation well within the timeout.
        assertThat(context.get()).isEqualTo("workshop");
    }

    @After
    public void tear_down() {
        try {
            assertThat(Thread.currentThread()).isSameAs(fixtureThread);
            assertThat(context.get()).isEqualTo("workshop");
        } finally {
            context.remove();
        }
    }
}
