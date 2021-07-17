package com.dataart.qa.workshop.general;

import com.dataart.qa.workshop.junit.runners.MyRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MyRunner.class)
public class MyRunnerTest {
    private static String actual = "Actual string";
    private static String expected = "Actual string";// "Expected string"

    @Test
    public void my_runner_the_first_test() {
        Assert.assertEquals("Wrong string found", expected, actual);
    }

    @Test
    public void my_runner_the_second_test() {
        Assert.assertEquals("Wrong string found", expected, actual);
    }
}
