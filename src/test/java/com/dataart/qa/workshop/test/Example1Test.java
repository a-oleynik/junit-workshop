package com.dataart.qa.workshop.test;

import com.dataart.qa.workshop.junit.runners.MyRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MyRunner.class)
public class Example1Test {
    @Test
    public void example1_the_first_test(){
        Assert.assertEquals("Wrong string found", "Expected string", "Actual string");
    }

    @Test
    public void example1_the_second_test(){
        Assert.assertEquals("Wrong string found", "Actual string", "Actual string");
    }
}
