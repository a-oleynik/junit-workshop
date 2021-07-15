package com.dataart.qa.workshop.test;

import org.junit.*;

import static java.lang.String.format;

/*@Ignore("Ignoring these tests.")*/
public class IgnoringTest {

    @Ignore
    @Test
    public void ignoring_mulitiplication_test() {
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2 * 5);
    }

    @Test
    public void ignoring_division_test() {
        System.out.println("Division test.");
        Assert.assertEquals(2.0, 10 / 5.0, 0);
    }

    @Test
    public void ignoring_boolean_test() {
        System.out.println("Boolean test.");
        boolean variable = true;
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }

    @Test
    public void ignoring_fail_test() {
        System.out.println("Fail test.");
        boolean isFailed = false;
        if (isFailed) {
            Assert.fail(format("The test is because isFailed is %s", isFailed));
        }
    }
}
