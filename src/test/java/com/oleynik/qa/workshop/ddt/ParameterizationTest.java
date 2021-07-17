package com.oleynik.qa.workshop.ddt;

import com.oleynik.qa.workshop.Factorial;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizationTest {
    private long number;
    private long expected;

    public ParameterizationTest(long number, long expected) {
        this.number = number;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{1, 1}, {2, 2}, {3, 6}, {4, 24}, {5, 120}});
    }

    @Test
    public void junit4_ddt_factorial_test() {
        Assert.assertEquals("factorial function works wrong", expected, Factorial.factorial(number));
    }
}
