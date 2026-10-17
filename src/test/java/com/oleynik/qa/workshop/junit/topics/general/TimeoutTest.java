package com.oleynik.qa.workshop.junit.topics.general;

import com.oleynik.qa.workshop.junit.topics.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeoutTest {
    @Before
    public void setUp() {
        Utils.waitFor(1);
        System.out.println("Set up");
        System.out.println("Set up thread id " + Thread.currentThread().getId());
    }

    @After
    public void tearDown() {
        Utils.waitFor(1);
        System.out.println("Tear down");
        System.out.println("Tear down thread id " + Thread.currentThread().getId());
    }

    // JUnit 4 expresses @Test timeout values in milliseconds.
    // The test method runs on a different thread from @Before and @After.
    // ThreadLocal context set up in @Before (for example, a transaction)
    // is not automatically available in the test method.
    @Test(timeout = 6000)
    public void timeout_test() {
        System.out.println("Timeout test");
        System.out.println("Thread id " + Thread.currentThread().getId());
        Utils.waitFor(2);
    }

    @Test(timeout = 2000)
    public void timeout_test2() {
        System.out.println("Timeout test");
        System.out.println("Thread id " + Thread.currentThread().getId());
        Utils.waitFor(1);//4
    }

    // The six-second limit applies to this test method, not setUp() or tearDown().
    @Test(timeout = 6000)
    public void timeout_test3() {
        System.out.println("Timeout test");
        System.out.println("Thread id " + Thread.currentThread().getId());
        Utils.waitFor(2);//4
    }
}
