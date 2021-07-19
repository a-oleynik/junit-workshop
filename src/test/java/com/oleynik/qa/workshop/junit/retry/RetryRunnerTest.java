package com.oleynik.qa.workshop.junit.retry;

import com.oleynik.qa.workshop.junit.annotations.RetryConfiguration;
import com.oleynik.qa.workshop.junit.runners.RetryRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RetryRunner.class)
public class RetryRunnerTest {

    @Test
    @RetryConfiguration(maxTries = 2)
    public void retry_runner_multiplication_test() {
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2 * 5);// 2*6
    }

    @Test
    @RetryConfiguration(maxTries = 4)
    public void retry_runner_division_test() {
        System.out.println("Division test.");
        Assert.assertEquals(1.9, 10 / 5.0, 0.2);
    }
}
