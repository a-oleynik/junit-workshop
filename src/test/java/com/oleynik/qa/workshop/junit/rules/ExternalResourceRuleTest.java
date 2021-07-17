package com.oleynik.qa.workshop.junit.rules;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

@OrderWith(Alphanumeric.class)
public class ExternalResourceRuleTest {
    @Rule
    public ExternalResource rule = new DBResourceRule();

    @Test
    public void external_resource_rule_multiplication_test() {
        System.out.println("Multiplication test");
        Assert.assertEquals("Multiplication result is wrong", 10, 2 * 5);
    }

    @Test
    public void external_resource_rule_division_test() {
        System.out.println("Division test");
        Assert.assertEquals(1.9, 10 / 5.0, 0.2);// 0
    }

    @Test
    public void external_resource_rule_boolean_test() {
        System.out.println("Boolean test");
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }
}
