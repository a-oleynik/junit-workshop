package com.oleynik.qa.workshop.junit.execution.order;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class ExecutionOrderWithTest {
    @Test
    public void execution_order_with_fail_test() {
        boolean isFailed = false;
        if (isFailed) {
            fail(format("The test is because isFailed is %s", isFailed));
        }
    }

    @Test
    @Order(3)
    public void execution_order_with_multiplication_test() {
        System.out.println("Multiplication test.");
        assertEquals(10, 2 * 5, "Multiplication result is wrong");
    }

    @Test
    @Order(1)
    public void execution_order_with_division_test() {
        System.out.println("Division test.");
        assertEquals(2.0, 10 / 5.0, 0);
    }

    @Test
    @Order(2)
    public void execution_order_with_boolean_test() {
        boolean variable = true;
        assertTrue(variable, "variable is not true");
        assertFalse(!variable, "!variable is not false");
    }
}
