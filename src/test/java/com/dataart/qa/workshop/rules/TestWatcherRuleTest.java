package com.dataart.qa.workshop.rules;

import org.junit.*;
import org.junit.rules.TestRule;

public class TestWatcherRuleTest {
    @Rule
    public TestRule watcher = new MonitorRule();

    @Test
    public void test_watcher_multiplication_test(){
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2*5);
    }

    @Test
    public void test_watcher_division_test(){
        System.out.println("Division test.");
        Assert.assertEquals(1.9, 10/5.0, 0.2);// 0
    }

    @Test
    @Ignore
    public void test_watcher_boolean_test(){
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }
}
