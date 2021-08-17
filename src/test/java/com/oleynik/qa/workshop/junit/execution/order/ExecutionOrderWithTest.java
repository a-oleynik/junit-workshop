package com.oleynik.qa.workshop.junit.execution.order;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

import static java.lang.String.format;

@OrderWith(Alphanumeric.class)
public class ExecutionOrderWithTest {
    @Test
    public void execution_order_with_multiplication_test() {
        System.out.println("Multiplication test");
        Assert.assertEquals("Multiplication result is wrong", 10, 2 * 5);
    }

    @Test
    public void execution_order_with_division_test() {
        System.out.println("Division test");
        Assert.assertEquals(2.0, 10 / 5.0, 0);
    }

    @Test
    public void execution_order_with_boolean_test() {
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }

    @Test
    public void execution_order_with_fail_test() {
        boolean isFailed = false;
        if (isFailed) {
            Assert.fail(format("The test is because isFailed is %s", isFailed));
        }
    }
}
