package com.oleynik.qa.workshop.junit.execution.order;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static java.lang.String.format;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FixMethodOrderTest {
    @Test
    public void fix_method_order_multiplication_test() {
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2 * 5);
    }

    @Test
    public void fix_method_order_division_test() {
        System.out.println("Division test.");
        Assert.assertEquals(2.0, 10 / 5.0, 0);
    }

    @Test
    public void fix_method_order_boolean_test() {
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }

    @Test
    public void fix_method_order_fail_test() {
        boolean isFailed = false;
        if (isFailed) {
            Assert.fail(format("The test is because isFailed is %s", isFailed));
        }
    }
}
