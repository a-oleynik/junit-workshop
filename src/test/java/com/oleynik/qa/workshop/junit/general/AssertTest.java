package com.oleynik.qa.workshop.junit.general;

import com.oleynik.qa.workshop.junit.model.User;
import org.junit.jupiter.api.*;

import static java.lang.String.format;

public class AssertTest {

    @Test
    public void assert_equals_multiplication_test() {
        System.out.println("Multiplication test.");
        Assertions.assertEquals(10, 2 * 5, "Multiplication result is wrong");
    }

    @Test
    public void assert_equals_division_test() {
        System.out.println("Division test.");
        Assertions.assertEquals(1.9, 10 / 5.0, 0.2);
    }

    @Test
    public void assert_boolean_test() {
        boolean variable = true;
        Assertions.assertTrue(variable, "variable is not true");
        Assertions.assertFalse(!variable, "!variable is not false");
    }

    @Test
    public void fail_test() {
        boolean isFailed = false;
        if (isFailed) {
            Assertions.fail(format("The test is because isFailed is %s", isFailed));
        }
    }

    @Test
    public void assert_null_test() {
        Object myObject = null;
        Assertions.assertNull(myObject, "My object is not null");
    }

    @Test
    public void assert_not_null_test() {
        Object myObject = new Object();
        Assertions.assertNotNull(myObject, "My object is null");
    }

    @Test
    public void assert_same_test() {
        User user1 = User.builder("Crocodilovich")
                .name("Crocodil")
                .yearOfBirth(1991)
                .build();
        Assertions.assertSame(user1, user1, "The users are not the same");
    }

    @Test
    public void assert_not_the_same_test() {
        User user1 = User.builder("Crocodilovich")
                .name("Crocodil")
                .yearOfBirth(1991)
                .build();
        User user2 = user1.toBuilder().build();
        Assertions.assertNotSame(user1, user2, "The users are not the same");
    }
}
