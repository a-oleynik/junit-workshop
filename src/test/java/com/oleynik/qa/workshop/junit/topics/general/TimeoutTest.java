package com.oleynik.qa.workshop.junit.topics.general;

import com.oleynik.qa.workshop.junit.topics.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class TimeoutTest {
    @BeforeEach
    public void setUp() {
        Utils.waitFor(1);
        System.out.println("Set up");
        System.out.println("Set up thread id " + Thread.currentThread().threadId());
    }

    @AfterEach
    public void tearDown() {
        Utils.waitFor(1);
        System.out.println("Tear down");
        System.out.println("Tear down thread id " + Thread.currentThread().threadId());
    }

    // assertTimeout runs the block on the calling thread and checks elapsed time
    // after it finishes. It does not interrupt the block or stop a hung operation.
    // assertTimeoutPreemptively runs the block on another thread: ThreadLocal
    // context (for example, a transaction) may not be available there.
    @Test
    void timeout_test() {
        System.out.println("Timeout test");
        System.out.println("Thread id " + Thread.currentThread().threadId());
        assertTimeout(ofSeconds(6), () ->
                Utils.waitFor(2)
        );
    }

    @Test
    public void timeout_test2() {
        System.out.println("Timeout test");
        System.out.println("Thread id " + Thread.currentThread().threadId());
        assertTimeout(ofSeconds(2), () ->
                Utils.waitFor(1)//2
        );
    }

    // The two-second limit applies to this test method, not setUp() or tearDown().
    @Timeout(2)
    @Test
    public void timeout_test3() {
        System.out.println("Timeout test");
        System.out.println("Thread id " + Thread.currentThread().threadId());
                Utils.waitFor(1);//2
    }
}
