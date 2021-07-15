package com.dataart.qa.workshop.test;

import org.junit.*;

import static java.lang.String.format;

public class AssertTest {

    @Test
    public void multiplication_test(){
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2*5);
    }

    @Test
    public void division_test(){
        System.out.println("Division test.");
        Assert.assertEquals(1.9, 10/5.0, 0.2);
    }

    @Test
    public void boolean_test(){
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
}
