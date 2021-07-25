package com.oleynik.qa.workshop.junit.general;

import com.oleynik.qa.workshop.junit.Utils;
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
        System.out.println("Set up.");
        System.out.println("Set up id " + Thread.currentThread().getId());
    }

    @AfterEach
    public void tearDown() {
        Utils.waitFor(1);
        System.out.println("Tear down.");
        System.out.println("Tear down id " + Thread.currentThread().getId());
    }

    @Test
    public void timeout_test() {
        System.out.println("Timeout test.");
        System.out.println("Test id " + Thread.currentThread().getId());
        assertTimeout(ofSeconds(6), () ->
                Utils.waitFor(2)
        );
    }

    @Test
    public void timeout_test2() {
        System.out.println("Timeout test.");
        System.out.println("Test id " + Thread.currentThread().getId());
        assertTimeout(ofSeconds(2), () ->
                Utils.waitFor(1)//2
        );
    }

    @Timeout(2)
    @Test
    public void timeout_test3() {
        System.out.println("Timeout test.");
        System.out.println("Test id " + Thread.currentThread().getId());
                Utils.waitFor(1);//2
    }
}
