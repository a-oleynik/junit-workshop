package com.dataart.qa.workshop.test;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class HamcrestTest {

    @Test
    public void hamcrest_test() {
        System.out.println("The Hamcrest test.");
        Assert.assertThat("Multiplication result is wrong", 2 * 5, equalTo(10));
        Assert.assertThat(2.0, is(10 / 5.0));
        boolean variable = true;
        Assert.assertThat("variable is not true", variable, is(true));
        Assert.assertThat("!variable is not false", !variable, is(not(true)));
    }
}
