package com.oleynik.qa.workshop.junit.extensions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;

@OrderWith(Alphanumeric.class)
public class DBResourceExtensionTest {
    @RegisterExtension
    static final DBResourceExtension MY_RESOURCE = new DBResourceExtension();

    @Test
    public void external_resource_rule_multiplication_test() {
        System.out.println("Multiplication test");
        Assertions.assertEquals(10, 2 * 5, "Multiplication result is wrong");
    }

    @Test
    public void external_resource_rule_division_test() {
        System.out.println("Division test");
        Assertions.assertEquals(1.9, 10 / 5.0, 0.2);// 0
    }

    @Test
    public void external_resource_rule_boolean_test() {
        System.out.println("Boolean test");
        boolean variable = true;
        Assertions.assertTrue(variable, "variable is not true");
        Assertions.assertFalse(!variable, "!variable is not false");
    }
}
