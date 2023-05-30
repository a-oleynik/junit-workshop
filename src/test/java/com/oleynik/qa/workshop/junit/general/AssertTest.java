package com.oleynik.qa.workshop.junit.general;

import com.oleynik.qa.workshop.junit.model.User;
import org.junit.jupiter.api.*;

import static java.lang.String.format;

public class AssertTest {

    @Test
    void assert_equals_multiplication_test() {
        System.out.println("Multiplication test");
        Assertions.assertEquals(10, 2 * 5, "Multiplication result is wrong");
    }

    @Test
    void assert_equals_division_test() {
        System.out.println("Division test");
        Assertions.assertEquals(1.9, 10 / 5.0, 0.2);
    }

    @Test
    void assert_boolean_test() {
        boolean variable = true;
        Assertions.assertTrue(variable, "variable is not true");
        Assertions.assertFalse(!variable, "!variable is not false");
    }

    @Test
    void fail_test() {
        boolean isFailed = false;
        if (isFailed) {
            Assertions.fail(format("The test is failed because isFailed equals to %s", isFailed));
        }
    }

    @Test
    void assert_null_test() {
        Object myObject = null;
        Assertions.assertNull(myObject, "My object is not null");
    }

    @Test
    void assert_not_null_test() {
        Object myObject = new Object();
        Assertions.assertNotNull(myObject, "My object is null");
    }

    @Test
    void assert_same_test() {
        User user1 = User.builder("Crocodilovich")
                .name("Crocodil")
                .yearOfBirth(1991)
                .build();
        Assertions.assertSame(user1, user1, "The users are not the same");
    }

    @Test
    void assert_not_the_same_test() {
        User user1 = User.builder("Crocodilovich")
                .name("Crocodil")
                .yearOfBirth(1991)
                .build();
        User user2 = user1.toBuilder().build();
        Assertions.assertNotSame(user1, user2, "The users are not the same");
    }
}
