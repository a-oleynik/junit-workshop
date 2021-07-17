package com.oleynik.qa.workshop.junit.general;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestTest {

    @Test
    public void hamcrest_test(){
        System.out.println("The Hamcrest test.");
        assertThat("Multiplication result is wrong", 2*5, equalTo(10));
        assertThat(2.0,is(10/5.0));
        boolean variable = true;
        assertThat("variable is not true", variable, is(true));
        assertThat("!variable is not false", !variable, is(not(true)));
    }
}
