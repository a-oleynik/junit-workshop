package com.oleynik.qa.workshop.general;

import com.oleynik.qa.workshop.junit.model.User;
import org.junit.*;

import static java.lang.String.format;

public class AssertTest {

    @Test
    public void assert_equals_multiplication_test(){
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2*5);
    }

    @Test
    public void assert_equals_division_test(){
        System.out.println("Division test.");
        Assert.assertEquals(1.9, 10/5.0, 0.2);
    }

    @Test
    public void assert_boolean_test(){
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }

    @Test
    public void fail_test(){
        boolean isFailed = false;
        if (isFailed) {
            Assert.fail(format("The test is because isFailed is %s", isFailed));
        }
    }

    @Test
    public void assert_null_test(){
        Object myObject = null;
        Assert.assertNull("My object is not null", myObject);
    }

    @Test
    public void assert_not_null_test(){
        Object myObject = new Object();
        Assert.assertNotNull("My object is null", myObject);
    }

    @Test
    public void assert_same_test(){
        User user1 = User.builder("Crocodilovich")
                .name("Crocodil")
                .yearOfBirth(1991)
                .build();
        Assert.assertSame("The users are not the same", user1, user1);
    }

    @Test
    public void assert_not_the_same_test(){
        User user1 = User.builder("Crocodilovich")
                .name("Crocodil")
                .yearOfBirth(1991)
                .build();
        User user2 = user1.toBuilder().build();
        Assert.assertNotSame("The users are not the same", user1, user2);
    }
}
