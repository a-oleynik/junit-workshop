package com.dataart.qa.workshop.test;

import org.junit.*;

public class AssertTest {

    @Test
    public void multiplication_test(){
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2*5);
    }

    @Test
    public void division_test(){
        System.out.println("Division test.");
        Assert.assertEquals(2.0, 10/5.0, 0);
    }

    @Test
    public void boolean_test(){
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }

    @Test
    public void fail_test(){
        Assert.fail("The test is failed somehow.");
    }
}
