package com.dataart.qa.workshop.test;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class ExceptionRuleTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test()
    public void test_exception(){
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("Index: 0, Size: 0");
        expectedException.expectMessage(containsString("Index: 0"));
        new ArrayList<String>().get(0);
    }

    @Test
    public void test_exception_message(){
        try {
            new ArrayList<String>().get(0);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException){
            Assert.assertThat(indexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
            Assert.fail(indexOutOfBoundsException.getMessage());
        }
    }
}

