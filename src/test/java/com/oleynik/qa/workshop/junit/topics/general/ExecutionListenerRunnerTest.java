package com.oleynik.qa.workshop.junit.topics.general;

import com.oleynik.qa.workshop.junit.topics.runners.ExecutionListenerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ExecutionListenerRunner.class)
public class ExecutionListenerRunnerTest {
    private static final String ACTUAL = "Actual string";
    private static final String EXPECTED = "Actual string";// "Expected string"

    @Test
    public void my_runner_the_first_test() {
        Assert.assertEquals("Wrong string found", EXPECTED, ACTUAL);
    }

    @Test
    public void my_runner_the_second_test() {
        Assert.assertEquals("Wrong string found", EXPECTED, ACTUAL);
    }
}
