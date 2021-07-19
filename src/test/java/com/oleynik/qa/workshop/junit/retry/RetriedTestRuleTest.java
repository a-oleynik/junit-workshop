package com.oleynik.qa.workshop.junit.retry;

import com.oleynik.qa.workshop.junit.annotations.Retry;
import com.oleynik.qa.workshop.junit.rules.RetryTestRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

public class RetriedTestRuleTest {

    @Rule
    public RetryTestRule rule = new RetryTestRule();

    @Test
    @Retry
    public void retry_test_rule_multiplication_test() {
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2 * 5);// 2*6
    }

    @Test
    public void usual_division_test() {
        System.out.println("Division test.");
        Assert.assertEquals(1.9, 10 / 5.0, 0.2);
    }
}
